package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.yield.openPricing.sidebar.UpdateExistingOpenPricingRulesSidebar;

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
    private UpdateExistingOpenPricingRulesSidebar openPricingSidebar;
    private Publisher publisher;

    private final String UPLOAD_CSV_TEXT = "This action will analyze the CSV based on the rule names and will " +
            "only overwrite the floor price for the selected publisher. " +
            "In the case that there is not a matching Rule name, that rule will be ignored in the batch upload.";

    public OpenPricingUploadTests() {
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


    @Test(description = "Update existing open pricing rules")
    private void updateExistingOpenPricingRules(){

        testStart()
                .and(String.format("Select Publisher %s",publisher.getName()))
                .selectFromDropdown(openPricingSidebar.getPublisherNameDropdown(),
                        openPricingSidebar.getPublisherNameDropdownItems(), publisher.getName())

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

    private void generateOpenPricingFromFile(String filename){

    }

    private OpenPricing createOpenPricing(String name, Integer floorPrice){

        return openPricing().createNewOpenPricing().build().getOpenPricingResponse();

    }
}
