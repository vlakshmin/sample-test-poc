package rx;

import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.dashbord.DashboardPage;
import pages.sales.deals.DealsPage;
import widgets.sales.deals.sidebar.CreateDealSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class DealsTest extends BaseTest {

    private DashboardPage dashboardsPage;
    private DealsPage dealsPage;
    private CreateDealSidebar createDealSidebar;

    public DealsTest() {
        dealsPage = new DealsPage();
        dashboardsPage = new DashboardPage();
        createDealSidebar = new CreateDealSidebar();
    }


    @Test
    public void createDealTest() {

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
                .selectFromDropdownWithSearch(createDealSidebar.getPublisherDropdown(),
                        createDealSidebar.getDropDownItems(),
                        "Viber")
                .setValue(createDealSidebar.getNameInput(), captionWithSuffix("Deal"))
                .selectFromDropdownByPosition(createDealSidebar.getPrivateAuctionDropdown(),
                        createDealSidebar.getDropDownItems(), 0)
                .selectFromDropdownByPosition(createDealSidebar.getCurrencyDropdown(),
                        createDealSidebar.getDropDownItems(), 0)
                .and()
                .setValue(createDealSidebar.getBuyersCardByPositionInList(0).getAdvertiserNameInput(), captionWithSuffix("Seat"))
                .and()
                .clickOnWebElement(createDealSidebar.getAddMoreSeatsButton())
                .clickOnWebElement(createDealSidebar.getCloseSideBarButton())
                .waitSideBarClosed()
                .testEnd();

        //allure serve
    }

    @Test
    public void datePickerTest() {

        var dateRangeField = createDealSidebar.getDateRangeField();
        //Opening Browser and Check Deals DatePicker
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
                .selectFromDropdown(createDealSidebar.getPublisherDropdown(), createDealSidebar.getDropDownItems(), "Beryl Ryan")
                .clickOnWebElement(dateRangeField.getDateRangeInput())
                .clickOnWebElement(dateRangeField.getDayButtonByValue("12"))
                .then()
                .clickOnWebElement(dateRangeField.getDateRangeInput())
                .clickOnWebElement(createDealSidebar.getCloseSideBarButton())
                .waitSideBarClosed()
                .testEnd();

        //allure serve
    }

    @AfterMethod
    public void logOut() {
        testStart()
                .logOut()
                .testEnd();
    }
}
