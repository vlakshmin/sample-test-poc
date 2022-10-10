package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;
import widgets.yield.openPricing.sidebar.UpdateExistingOpenPricingRulesSidebar;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.preconditionbuilders.OpenPricingPrecondition.openPricing;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingUploadTests extends BaseTest {

    private OpenPricingPage openPricingPage;
    private UpdateExistingOpenPricingRulesSidebar openPricingUploadSidebar;
    private EditOpenPricingSidebar openPricingSidebar;
    private Publisher publisher;
    private List<OpenPricing> openPricingList;
    private Map<String, String> fileData;

    private final String RESOURCES_DIRECTORY = "src/test/resources/csvfiles/openpricing/";

    public OpenPricingUploadTests() {
        openPricingPage = new OpenPricingPage();
        openPricingUploadSidebar = new UpdateExistingOpenPricingRulesSidebar();
        openPricingSidebar = new EditOpenPricingSidebar();
    }

    @BeforeClass
    private void createTestData() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("000autoPub1"))
                .build()
                .getPublisherResponse();

        openPricingList = new ArrayList<>();

        testStart()
                .given("Open Open Pricing page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .testEnd();
    }

    @DataProvider(name = "Positive Upload")
    public Object[][] uploadFiles() {
        return new Object[][]{
                {"3 out of 3.csv", 0.00, ""},
                {"1 out of 3 decimal.csv", 1.50, ""},
                {"decimal into int.csv", 1.15, "Upload CSV update decimal floorPrice to int"},
                {"zero decimal.csv", 0.00, "Upload CSV update by zero decimal floorPrice"},
                {"zero to int.csv", 0.00, "Upload CSV update zero floorPrice to int"},
                {"zero decimal.csv", 0.00, "Upload CSV update zero floorPrice to max decimal"},
                {"zero decimal.csv", 0.00, "Upload CSV update zero floorPrice by Stringified number value"},
        };
    }

    @Test(description = "Positive: Update existing open pricing rules", dataProvider = "Positive Upload")
    private void updateExistingOpenPricingRulesPositive(String filename, Double floorPrice, String descr) {

        log.info(descr);

        openFile(filename);
        uploadData(filename);
        //updateOpenPricing(floorPrice);

        validateFloorPrice(openPricingList.get(0).getName());
        validateFloorPrice(openPricingList.get(1).getName());
        validateFloorPrice(openPricingList.get(2).getName());
    }

    @Step("Upload Data")
    private void uploadData(String filename) {

        testStart()
                .and("Open Upload")
                .clickOnWebElement(openPricingPage.getUploadOpenPricingButton())
                .clickOnWebElement(openPricingPage.getUpdateOpenPricingLink())
                .waitSideBarOpened()
                .and(String.format("Select Publisher %s", publisher.getName()))
                .selectFromDropdown(openPricingUploadSidebar.getPublisherNameDropdown(),
                        openPricingUploadSidebar.getPublisherNameDropdownItems(), publisher.getName())
                .uploadFileFromDialog(openPricingUploadSidebar.getCsvFileInput(), RESOURCES_DIRECTORY + filename)
                .and("Click 'Update Existing Open Pricing Rules'")
                .clickOnWebElement(openPricingUploadSidebar.getUpdateExistingRulesButton())
                .testEnd();
    }


    @Step("Validate floor price updated")
    private void validateFloorPrice(String ruleName) {

        var tableData = openPricingPage.getOpenPricingTable().getTableData();

        testStart()
                .setValueWithClean(tableData.getSearch(), ruleName)
                .clickEnterButton(tableData.getSearch())
                .then("Wait data loading")
                .validateContainsText(tableData.getCellByPositionInTable(ColumnNames.NAME, 0), ruleName)
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.NAME, ruleName)
                .waitSideBarOpened()
                .then("Check Floor Price")
                .validateAttribute(openPricingSidebar.getFloorPriceField().getFloorPriceInput(), "value",
                        fileData.get(ruleName))
                .and("Close Open Pricing Sidebar")
                .clickOnWebElement(openPricingSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    private void deleteTestData() {
        deleteOpenPricingRules();
    }

    @AfterClass(alwaysRun = true)
    private void deletePublisher() {

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(publisher.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher.getId()));
    }

    private void deleteOpenPricingRules() {

        for (OpenPricing rule : openPricingList) {
            if (openPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteOpenPricing(rule.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                log.info(String.format("Deleted open pricing %s", rule.getId()));
        }
    }

    private OpenPricing createOpenPricing(String name, Double floorPrice) {

        return openPricing()
                .createNewOpenPricing(name, floorPrice, publisher)
                .build()
                .getOpenPricingResponse();
    }

    private void updateOpenPricing(Double floorPrice) {

        for (OpenPricing rule : openPricingList) {

            var updatedRule = updateRule(rule, floorPrice);

            openPricing()
                    .updateOpenPricing(updatedRule)
                    .build()
                    .getOpenPricingResponse();
        }
    }

    private OpenPricing updateRule(OpenPricing rule, Double floorPrice) {

        rule.setFloorPrice(floorPrice);

        return rule;
    }

    private void openFile(String fileName) {

        fileData = new HashMap<>();

        try {
            FileReader filereader = new FileReader(RESOURCES_DIRECTORY + fileName);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                System.out.println(row[0]);
                if (!row[0].isEmpty()) {
                    openPricingList.add(createOpenPricing(row[0], 0.00));
                    fileData.put(row[0], row[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
