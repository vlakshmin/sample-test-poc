package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.yield.openPricing.sidebar.UpdateExistingOpenPricingRulesSidebar;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingUploadSidebarTests extends BaseTest {

    private OpenPricingPage openPricingPage;
    private UpdateExistingOpenPricingRulesSidebar openPricingSidebar;
    private Publisher publisher;

    private final String UPLOAD_CSV_TEXT = "This action will analyze the CSV based on the rule names and will " +
            "only overwrite the floor price for the selected publisher. " +
            "In the case that there is not a matching Rule name, that rule will be ignored in the batch upload.";

    public OpenPricingUploadSidebarTests() {
        openPricingPage = new OpenPricingPage();
        openPricingSidebar = new UpdateExistingOpenPricingRulesSidebar();
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


    @Test(description = "Check Upload slide elements by default (Admin)")
    private void checkUploadSlideElementsByDefaultAdmin(){

        testStart()
                .then("Publisher Name dropdown is empty")
                .validate(visible, openPricingSidebar.getPublisherInput())
                .validate(openPricingSidebar.getPublisherInput().getText(), "")
                .then("CSV template is visible")
                .validate(visible, openPricingSidebar.getDownloadCSVTemplateButton())
                .then("Download Existing Open Pricing Rules is not visible")
                .validate(disabled, openPricingSidebar.getDownloadExistingOpenPricingRulesButton())
                .then("'Upload your CSV file' text is present")
                .validate(visible, openPricingSidebar.getUploadCSVText())
               // .then("File input is present")
               // .validate(visible, openPricingSidebar.getCsvFileInput())
                .validate(openPricingSidebar.getCsvFileInput().getText(), "")
                .validate(not(visible), openPricingSidebar.getCsvFileClearIcon())
                .then("Update Pricing Rules button is visible")
                .validate(visible, openPricingSidebar.getUpdateExistingRulesButton())
                .and("Close sidebar")
                .clickOnWebElement(openPricingSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }




    @AfterClass(alwaysRun = true)
    private void deleteTestData() {
        deletePublisher(publisher.getId());
    }

    private void deletePublisher(int id) {
        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id)
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher.getId()));

    }

}
