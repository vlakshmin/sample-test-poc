package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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

    private final String UPLOAD_CSV_TEXT = "This action will analyze the CSV based on the rule names and will " +
            "only overwrite the floor price for the selected publisher. " +
            "In the case that there is not a matching Rule name, that rule will be ignored in the batch upload.";

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
    }

    @BeforeMethod
    private void loginUI() {

        testStart()
                .given("Open Open Pricing page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Open Upload")
                .clickOnWebElement(openPricingPage.getUploadOpenPricingButton())
                .clickOnWebElement(openPricingPage.getUpdateOpenPricingLink())
                .waitSideBarOpened()
                .testEnd();
    }


    @Test(description = "Update existing open pricing rules")
    private void updateExistingOpenPricingRules(){
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        String filename = "3 out of 3.csv";
        openFile(filename);

        testStart()
                .and(String.format("Select Publisher %s",publisher.getName()))
                .selectFromDropdown(openPricingUploadSidebar.getPublisherNameDropdown(),
                        openPricingUploadSidebar.getPublisherNameDropdownItems(), publisher.getName())
                .uploadFileFromDialog(openPricingUploadSidebar.getCsvFileInput(),RESOURCES_DIRECTORY+filename)
                .and("Click 'Update Existing Open Pricing Rules'")
                .clickOnWebElement(openPricingUploadSidebar.getUpdateExistingRulesButton())
                .and("Close sidebar")
                .clickOnWebElement(openPricingUploadSidebar.getCloseIcon())
                .waitSideBarClosed()

                .setValueWithClean(tableData.getSearch(),openPricingList.get(0).getName())
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.NAME, openPricingList.get(0).getName())
                .waitSideBarOpened()
                .then("Check Floor Price")
                .validateAttribute(openPricingSidebar.getFloorPriceField().getFloorPriceInput(),"value",
                        fileData.get(openPricingList.get(0).getName()))
                .and("Close Open Pricing Sidebar")
                .clickOnWebElement(openPricingSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }


    @AfterClass(alwaysRun = true)
    private void deleteTestData() {
        deleteOpenPricingRules();
        deletePublisher(publisher.getId());
    }

    private void deletePublisher(int id) {

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id)
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", id));
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

    private void generateOpenPricingFromFile(String filename){

    }

    private OpenPricing createOpenPricing(String name, Double floorPrice){

        return openPricing()
                .createNewOpenPricing(name, floorPrice, publisher)
                .build()
                .getOpenPricingResponse();
    }

    private void openFile(String fileName) {

        openPricingList = new ArrayList<>();
        fileData = new HashMap<>();

        try {
            FileReader filereader = new FileReader(RESOURCES_DIRECTORY + fileName);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                System.out.println(row[0]);
                openPricingList.add(createOpenPricing(row[0],0.00));
                fileData.put(row[0], row[1]);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
