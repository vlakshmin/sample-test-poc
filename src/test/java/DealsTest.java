import api.dto.rx.admin.publisher.Publisher;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import pages.dashbord.DashboardPage;
import pages.sales.deals.DealsPage;
import rx.BaseTest;
import widgets.admin.publisher.sidebar.CreatePublisherSidebar;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import widgets.sales.deals.CreateDealSidebar;
import zutils.FakerUtils;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class DealsTest extends BaseTest {

    private DashboardPage dashboardsPage;
    private DealsPage dealsPage;
    private CreateDealSidebar createDealSidebar;

    public DealsTest(){
        dealsPage = new DealsPage();
        dashboardsPage = new DashboardPage();
        createDealSidebar = new CreateDealSidebar();
    }
    @Test
    public void createDealTest(){

        //Opening Browser and Create Deal
        testStart()
                .given()
                .openDirectPath(Path.DEALS)
                .logIn(TEST_USER)
                .validate(visible, dashboardsPage.getLogo())
                .validate(TEST_USER.getMail())
                .waitAndValidate(disappear, dashboardsPage.getNuxtProgress())
                .and()
                .waitAndValidate(disappear, dashboardsPage.getTableProgressBar())
                .clickOnWebElement(dealsPage.getCreateNewDealButton())
                .waitSideBarOpened()
                .and()
                .selectFromDropdown(
                        createDealSidebar.getPublisherDropdown(),
                        createDealSidebar.getDropDownItems(),
                        "Beryl Ryan")
        .testEnd();

        //allure serve
    }
}
