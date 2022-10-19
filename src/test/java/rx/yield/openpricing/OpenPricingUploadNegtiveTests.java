package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.errormessages.ErrorMessages;
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;
import widgets.yield.openPricing.sidebar.UpdateExistingOpenPricingRulesSidebar;

import java.util.ArrayList;
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
public class OpenPricingUploadNegtiveTests extends BaseTest {

    private Publisher publisher;
    private OpenPricingPage openPricingPage;
    private List<OpenPricing> openPricingList;
    private EditOpenPricingSidebar openPricingSidebar;
    private UpdateExistingOpenPricingRulesSidebar openPricingUploadSidebar;

    private final String RESOURCES_DIRECTORY = "src/test/resources/csvfiles/openpricing/";

    public OpenPricingUploadNegtiveTests() {
        openPricingPage = new OpenPricingPage();
        openPricingUploadSidebar = new UpdateExistingOpenPricingRulesSidebar();
        openPricingSidebar = new EditOpenPricingSidebar();
    }

    @BeforeClass
    private void createTestData() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("000autoPub2"))
                .build()
                .getPublisherResponse();

        openPricingList = new ArrayList<>();

        openPricingList.add(createOpenPricing("upload auto one", 4.44));
        openPricingList.add(createOpenPricing("upload auto two", 4.44));
        openPricingList.add(createOpenPricing("upload auto three", 4.44));

        testStart()
                .given("Open Open Pricing page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .testEnd();
    }

    @DataProvider(name = "Negative Upload")
    public Object[][] uploadFilesNegative() {
        return new Object[][]{
                {"by too large decimal.csv", "Upload CSV update zero floorPrice by too large decimal", "Row 3, Floor Price '9999999.99': A valid price is between 0.00 and 999,999.99"},
                {"by too large int.csv", "Upload CSV update zero floorPrice by too large int", "Row 3, Floor Price '99999999.00': A valid price is between 0.00 and 999,999.99"},
                {"by negative integer.csv", "Upload CSV update zero floorPrice by negative int", "Row 3, Floor Price '-999999.00': A valid price is between 0.00 and 999,999.99"},
                {"by negative decimal.csv", "Upload CSV update zero floorPrice by negative decimal", "Row 3, Floor Price '-9999.99': A valid price is between 0.00 and 999,999.99"},
                {"by negative zero.csv", "Upload CSV update zero floorPrice by negative zero", "Row 3, Floor Price '-0.01': A valid price is between 0.00 and 999,999.99"},
                {"by string value.csv", "Upload CSV update zero floorPrice by String value", "the CSV file is incorrectly formatted"},
                {"by empty floor price.csv", "Upload CSV update floorPrice by empty value", "Row 3, Floor Price '': not a valid value"},
                {"by empty open pricing name.csv", "Upload CSV update by empty open-pricing-name value", "Row 3, Rule Name '': could not match the open pricing Rule Name to the publisher"},
                {"by null floor price.csv", "Upload CSV update floorPrice by null value", "Row 3, Floor Price 'null': not a valid value"},
                {"by empty floor price header.csv", "Upload CSV incorrect floor price header", "the CSV file has incorrect headers"},
                {"by incorrect quantity of headers.csv", "Upload CSV incorrect quantity of headers", "the CSV file is incorrectly formatted"},
                {"by incorrect quantity of colunms.csv", "Upload CSV incorrect quantity of columns", "the CSV file does not have the correct number of columns"},
                {"by empty CSV file.csv", "Upload empty CSV file", "the CSV file is empty"},
                {"by no data.csv", "Upload CSV file with no data", "the CSV file contains no data"},
                {"by non-existent among others.csv", "Upload CSV update by non-existent pricing among others",
                        "Row 4, Rule Name 'upload auto four': could not match the open pricing Rule Name to the publisher"},
                {"by empty pricing name header.csv", "Upload CSV with empty pricing name header", "the CSV file has incorrect headers"},
                {"duplicated rule names.csv", "Upload CSV update by duplicated pricing names", "Row 3, Floor Price 'upload auto one': the same Rule Name already exists in row 2"},
        };
    }

    @Test(description = "Negative: Update existing open pricing rules", dataProvider = "Negative Upload")
    private void updateExistingOpenPricingRulesNegative(String filename, String descr, String errorMsg) {

        log.info(descr);

        uploadData(filename);

        checkErrorAlert(errorMsg);
        closeSideBar();

        openPricingList
                .stream()
                .forEach(e ->
                        checkDataIsNotChanged(e.getName()));
    }

    @Test(description = "Negative: The uploaded file is not a CSV file")
    private void uploadIsNotCSVNegative() {

        uploadData("non-csv.shell");

        testStart()
                .waitAndValidate(visible, openPricingPage.getToasterMessage().getPanelError())
                .clickOnWebElement(openPricingPage.getToasterMessage().getViewErrorDetails())
                .validate(openPricingPage.getToasterMessage().getMessageError(), "{ \"code\": 415, \"message\": \"unsupported media type\", " +
                        "\"error\": \"The uploaded file is not a CSV file\" }")
                .clickOnWebElement(openPricingPage.getToasterMessage().getRemoveIcon())
                .waitAndValidate(not(visible), openPricingPage.getToasterMessage().getPanelError())
                .testEnd();

        closeSideBar();
    }

    @Test(description = "Negative: check errors if required fields are not selected")
    private void checkRequiredFields() {
        var errorsList = openPricingUploadSidebar.getErrorAlert().getErrorsList();

        testStart()
                .and("Open Upload sidebar")
                .clickOnWebElement(openPricingPage.getUploadOpenPricingButton())
                .clickOnWebElement(openPricingPage.getUpdateOpenPricingLink())
                .waitSideBarOpened()
                .and("Click 'Update Pricing Rules'")
                .clickOnWebElement(openPricingUploadSidebar.getUpdateExistingRulesButton())
                .then("Validate errors for all required fields in Error Panel")
                .waitAndValidate(visible, openPricingUploadSidebar.getErrorAlert().getErrorPanel())
                .validateListSize(errorsList, 2)
                .validateList(errorsList, List.of(
                        ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.CSV_FILE_ERROR_ALERT.getText())
                )
                .then("Validate error under the 'Publisher' field")
                .waitAndValidate(visible, openPricingUploadSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validate(openPricingUploadSidebar.getErrorAlertByFieldName("Publisher Name"), ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText())
                .validate(openPricingUploadSidebar.getErrorAlertByFieldName("CSV"), ErrorMessages.CSV_FILE_ERROR_ALERT.getText())
                .and(String.format("Select Publisher '%s'", publisher.getName()))
                .selectFromDropdown(openPricingUploadSidebar.getPublisherInput(),
                        openPricingUploadSidebar.getPublisherNameDropdownItems(), publisher.getName())
                .then("Validate error under the 'Publisher field' disappeared")
                .waitAndValidate(not(visible), openPricingUploadSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.CSV_FILE_ERROR_ALERT.getText())
                )
                .uploadFileFromDialog(openPricingUploadSidebar.getCsvFileInput(), RESOURCES_DIRECTORY + "/by too large int.csv")
                .then("Validate errors disappeared")
                .waitAndValidate(not(visible), openPricingUploadSidebar.getErrorAlertByFieldName("CSV"))
                .waitAndValidate(not(visible), openPricingUploadSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validate(not(visible), openPricingUploadSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Step("Check Error Message")
    private void checkErrorAlert(String errorMsg) {
        var errorsList = openPricingUploadSidebar.getErrorAlert().getErrorsList();

        testStart()
                .then("Validate errors in Error Panel")
                .waitAndValidate(visible, openPricingUploadSidebar.getErrorAlert().getErrorPanel())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(errorMsg))
                .testEnd();
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
                .uploadFileFromDialog(openPricingUploadSidebar.getCsvFile(), RESOURCES_DIRECTORY + filename)
                .and("Click 'Update Existing Open Pricing Rules'")
                .clickOnWebElement(openPricingUploadSidebar.getUpdateExistingRulesButton())
                .testEnd();
    }


    @Step("Check Floor Price is not changed")
    private void checkDataIsNotChanged(String ruleName) {

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
                        "4.44")
                .and("Close sidebar")
                .clickOnWebElement(openPricingUploadSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }

    private void closeSideBar() {

        testStart()
                .and("Close sidebar")
                .clickOnWebElement(openPricingUploadSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deletePublisher() {

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(publisher.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher.getId()));

        deleteOpenPricingRules();
    }

    private void deleteOpenPricingRules() {

        if (openPricingList.size() > 0) {
            for (OpenPricing rule : openPricingList) {
                if (openPricing()
                        .setCredentials(USER_FOR_DELETION)
                        .deleteOpenPricing(rule.getId())
                        .build()
                        .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                    log.info(String.format("Deleted open pricing %s", rule.getId()));
            }
        }
    }

    private OpenPricing createOpenPricing(String name, Double floorPrice) {

        return openPricing()
                .createNewOpenPricing(name, floorPrice, publisher)
                .build()
                .getOpenPricingResponse();
    }
}
