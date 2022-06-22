package rx;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openPricing.OpenPricing;
import api.preconditionbuilders.OpenPricingPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import pages.dashbord.DashboardPage;
import pages.yield.openpricing.OpenPricingPage;
import widgets.admin.publisher.sidebar.CreatePublisherSidebar;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingTest extends BaseTest{

    private OpenPricing openPricing;
    private OpenPricingPage openPricingPage;
    private EditOpenPricingSidebar editOpenPricingSidebar;
    private CreateOpenPricingSidebar createOpenPricingSidebar;


    @BeforeClass
    public void createNewPublisher(){
        //Creating publisher to edit Using API
        openPricing = OpenPricingPrecondition.openPricing()
                .createNewOpenPricing()
                .build()
                .getOpenPricingResponse();
    }

    @Test
    public void editOpenPricingTest(){

        //Opening Browser and Edit the protection created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)


                .testEnd();

        //allure serve
    }
}
