package stepDefinitions;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXDashboardPage;
import RXPages.RXLoginPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class DashboardStepDefinition extends RXBaseClass {

    RXDashboardPage dashboardPage;
    PublisherListPage pubListPgs;
    RXNavOptions navOptions;
    RXLoginPage loginPage;
    Logger log = Logger.getLogger(MediaPageStepsDefinition.class);

    public DashboardStepDefinition() {
        super();
        dashboardPage = new RXDashboardPage();
        pubListPgs = new PublisherListPage();
        navOptions = new RXNavOptions();
        loginPage = new RXLoginPage();
    }

    @Given("^Admin user login by entering valid username and password$")
    public void admin_login_by_entering_valid_username_and_password() {
        log.info("The user has logged in "+prop.getProperty("url")+" as admin");
        driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
        loginPage.clickLogin(prop.getProperty("adminUsername"), prop.getProperty("adminPassword"));
    }

    @When("^Click on Dashboard option in Menu$")
    public void check_for_Dashboard_navigation_option_in_main_menu() {
        log.info("User click Dashboard navigation option in main menu :"
                + pubListPgs.logodisplayed());
        Assert.assertTrue(pubListPgs.logodisplayed());
        navOptions.clickDashBoardNav();
    }

    @Then("^Viber is selected as Publisher$")
    public void check_publisher_selected() {
        log.info("Publisher label is displayed and Viber is selected as value");
        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.publisherInput));
        dashboardPage.publisherLabel.isDisplayed();
        Assert.assertEquals(dashboardPage.publisherInput.getAttribute("value"), "Viber");
    }

    @Then("^Date range has default value$")
    public void check_date_input_value() {
        log.info("Date range label is displayed and value is correct");
        dashboardPage.dateLabel.isDisplayed();
        Assert.assertEquals(dashboardPage.dateInput.getAttribute("value"), "2020-11-03 ~ 2020-12-03");
    }

    @Then("^Graph are displayed properly$")
    public void check_graph_display() {
        log.info("Data is displayed in all six graphs");
        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.rateByDayGraph));
        dashboardPage.rateByDayGraph.isDisplayed();
        dashboardPage.trafficOverviewGraph.isDisplayed();
        dashboardPage.revenueByDayGraph.isDisplayed();
        dashboardPage.topTenGraph.isDisplayed();
        dashboardPage.revenueByAdGraph.isDisplayed();
        dashboardPage.ecmpGraph.isDisplayed();
    }

    @Then("^Select Viki as Publisher$")
    public void check_change_publisher_selected() {
        log.info("Select 'Viki' as Publisher and check data in all six graphs");
        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.publisherInput));
        dashboardPage.publisherLabel.isDisplayed();
        dashboardPage.publisherInput.click();
        dashboardPage.publisherInput.sendKeys(Keys.BACK_SPACE);
        dashboardPage.publisherInput.sendKeys("Viki");
        dashboardPage.vikiPublisher.click();
    }

}
