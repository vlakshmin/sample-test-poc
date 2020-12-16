package stepDefinitions;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXNavOptions;
import RXPages.RXPrivateAuctionsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class PrivateAuctionPageStepDefinition extends RXBaseClass {


    RXPrivateAuctionsPage auctionPage;
    RXNavOptions navOptions;
    PublisherListPage pubListPgs;
    Logger log = Logger.getLogger(PrivateAuctionPageStepDefinition.class);

    public PrivateAuctionPageStepDefinition() {
        super();
        auctionPage = new RXPrivateAuctionsPage();
        navOptions = new RXNavOptions();
        pubListPgs = new PublisherListPage();

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
    // Verify if user is displayed with media list page on clicking media navigation
    // link

    @When("^Click on Private Auctions option under Sales$")
    public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
        log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
                + pubListPgs.logodisplayed());
        Assert.assertTrue(pubListPgs.logodisplayed());
        navOptions.expandSales();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(navOptions.privateAuctionLabel));
        navOptions.privateAuctionLabel.click();

    }

    @When("^Click on Private Auctions sub menu$")
    public void check_for_Adspot() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(navOptions.privateAuctionLabel));
        navOptions.privateAuctionLabel.click();

    }

    @Then("^User displayed with Private Auctions page$")
    public void user_displayed_with_seats_page() throws Throwable {
        Assert.assertEquals(auctionPage.getPageHeading(), auctionPage.auctionHeaderStr);
        log.info("Auction Page Header is asserted  and it is : " + auctionPage.getPageHeading());

    }

    //Verify enabling abd disabling of an adspot from the overview page

    @When("^Verify enabling and disabling of an auction from the overview page$")
    public void verifyHEnableDiableAdspot() throws InterruptedException {
        for (int i = 0; i <= 1; i++) {
            driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
            List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active");
            String status = coulmnData.get(0).getText();
            switch (status) {
                case "Active":
                    Assert.assertTrue(auctionPage.overviewEditbutton.isDisplayed());
                    Assert.assertTrue(auctionPage.overviewDisablebutton.isDisplayed());
                    auctionPage.clickOverViewDisablebutton();
                    Thread.sleep(3000);
                    List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active");
                    Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
                    break;
                case "Inactive":
                    Assert.assertTrue(auctionPage.overviewEditbutton.isDisplayed());
                    Assert.assertTrue(auctionPage.overviewEnablebutton.isDisplayed());
                    String enableText = auctionPage.overviewEnablebutton.getText().replaceAll("\\s", "");
                    Assert.assertEquals(enableText, "ACTIVATEPRIVATEAUCTION");
                    auctionPage.clickOverViewEnablebutton();
                    Thread.sleep(3000);
                    List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active");
                    Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
                    break;

                default:
                    Assert.assertTrue(false, "The status fields supplied does not match with the input");

            }
        }
    }

    @And("^Open create New Private Auction page$")
    public void openCreateNewPrivateAuctionPage() {
        auctionPage.clickCreateNewPrivateAuctionButton();
    }

    @And("^Select publisher for Private Auction$")
    public void selectPublisherForPrivateAuction() {
        auctionPage.selectPublisher("Viber");
    }

    @Then("^Verify all targeting options are displayed$")
    public void verifyTargetingOptionsDisplayed() {
        driverWait().until(ExpectedConditions.visibilityOf(auctionPage.privateAuctionNameField));
        auctionPage.targetingExpandPanel("Inventory").isDisplayed();
        auctionPage.targetingExpandPanel("Device").isDisplayed();
        auctionPage.targetingExpandPanel("Operating System").isDisplayed();
        auctionPage.targetingExpandPanel("Geo").isDisplayed();
        auctionPage.targetingExpandPanel("Ad Format").isDisplayed();
        auctionPage.targetingExpandPanel("Ad Size").isDisplayed();
    }

    @Then("^Verify select/unselect targeting options items$")
    public void verifySelectUnselectItems(DataTable dt) {
        List<List<String>> data = dt.asLists(String.class);
        data.forEach(e -> auctionPage.checkSelectUnselectForBlock(e.get(0), e.get(1)));
    }

    @Then("^Verify select/unselect all targeting options items$")
    public void verifySelectUnselectAllItems(DataTable dt) {
        List<List<String>> data = dt.asLists(String.class);
        data.forEach(e -> auctionPage.checkSelectUnselectAllForBlock(e.get(0)));
    }
}