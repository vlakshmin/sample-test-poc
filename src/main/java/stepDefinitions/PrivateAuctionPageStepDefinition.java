package stepDefinitions;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXNavOptions;
import RXPages.RXPrivateAuctionsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class PrivateAuctionPageStepDefinition extends RXBaseClass {


	RXPrivateAuctionsPage auctionPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	RXAdspotsPage adspotsPage;
	Logger log = Logger.getLogger(PrivateAuctionPageStepDefinition.class);
	String enteredPublisherName;
	String enteredAuctionName;
	String enteredAuctionPackages;
	String enteredAuctionDates;

	public PrivateAuctionPageStepDefinition() {
		super();
		auctionPage = new RXPrivateAuctionsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		adspotsPage = new RXAdspotsPage();


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

		@Then("^Enter the following data in the general card of private auction$")
		public void enterGenaralCardAuction(DataTable dt) throws InterruptedException, ParseException {
			WebDriverWait wait = new WebDriverWait(driver, 35);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");
				String value = list.get(i).get("Value");
				String listValueIndex = list.get(i).get("ListValueIndex");

				switch (fieldName) {
				case "Publisher Name":
					WebElement dropDownPublisher;
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.publisherNameDropDown));
					adspotsPage.publisherNameDropDown.click();
					if (value.equalsIgnoreCase("ListValue")) {

						wait.until(ExpectedConditions.visibilityOf(
								driver.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"))));
						dropDownPublisher = driver
								.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"));

					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(
								By.xpath("//div[@role='listbox']/div//div[contains(text(),'" + value + "')]"))));
						dropDownPublisher = driver
								.findElement(By.xpath("//div[@role='listbox']/div//div[contains(text(),'" + value + "')]"));
					}
					js.executeScript("arguments[0].scrollIntoView()", dropDownPublisher);
					dropDownPublisher.click();
					Thread.sleep(5000);
					enteredPublisherName = adspotsPage.publisherNameField.getText();
					System.out.println("publisher entered as :" + enteredPublisherName);
					wait.until(ExpectedConditions.visibilityOf(auctionPage.auctionNameField));
					break;

				case "Name":
//					js.executeScript("arguments[0].setAttribute('value', '')",adspotsPage.adSpotNameField);
					while (!auctionPage.auctionNameField.getAttribute("value").equals("")) {
						auctionPage.auctionNameField.sendKeys(Keys.BACK_SPACE);
					}
					auctionPage.auctionNameField.sendKeys(value);
					enteredAuctionName = adspotsPage.adSpotNameHeader.getText();
					System.out.println("Entered Auction name:" + enteredAuctionName);
					break;
				case "Related Packages":
					while (!auctionPage.auctionPackages.getAttribute("value").equals("")) {
						auctionPage.auctionPackages.sendKeys(Keys.BACK_SPACE);
					}
					auctionPage.auctionPackages.sendKeys(value);
					enteredAuctionPackages = auctionPage.auctionPackages.getText();
					System.out.println("Entered Auction packages:" + enteredAuctionPackages);
					break;

				case "Date Range":
					auctionPage.selectFifteenDaysRangeInNextMonth();
					enteredAuctionDates = auctionPage.dateInput.getAttribute("value");
					System.out.println("Entered Auction dates:" + enteredAuctionDates);
					break;


				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

				}

			}
		}
		@When("^Click on Save Private Auction & Close button$")
		public void clickSaveBtn() throws Throwable {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(auctionPage.saveandcloseButton));
			auctionPage.saveandcloseButton.click();

		}

		@When("^Click on Save Private Auction & Create Deal button$")
		public void clickSaveBtnCreateDeal() throws Throwable {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(auctionPage.saveandcreatedealButton));
			auctionPage.saveandcreatedealButton.click();

		}

		@Then("^Verify following fields are not enabled for create page$")
		public void verifyMandatorFields(DataTable dt) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(1000);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");
				String isDisabled = driver.findElement(
						By.xpath("//aside[@class='dialog']//label[text()='"+fieldName+"']")).getAttribute("class");
				Assert.assertTrue(isDisabled.contains("disabled"));

			}

		}
		@Then("^Verify input values for following toggle fields in create page$")
		public void verifyDefaultValues(DataTable dt) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(1000);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");
				String active = list.get(i).get("Active");
				String isEnabled = driver.findElement(
						By.xpath("//aside[@class='dialog']//label[text()='"+fieldName+"']/parent::div//input")).getAttribute("aria-checked");
				if(active.equalsIgnoreCase("Yes")) {
				Assert.assertEquals(isEnabled,"true");

				}else {
					Assert.assertEquals(isEnabled,"false");
				}

			}

		}

		@Then("^\"(.*)\" following toggle fields in create page$")
		public void changeToggleFields(String enable,DataTable dt) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(1000);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");
				String isEnabled = driver.findElement(By.xpath("//aside[@class='dialog']//label[text()='"+fieldName+"']/parent::div//input")).getAttribute("aria-checked");
				if(enable.equalsIgnoreCase("Enable")&&isEnabled.equals("false")) {
							driver.findElement(
									By.xpath("//aside[@class='dialog']//label[text()='"+fieldName+"']/parent::div/div")).click();
				}else if(enable.equalsIgnoreCase("Disable")&& isEnabled.equals("true"))
				{
					driver.findElement(By.xpath("//aside[@class='dialog']//label[text()='"+fieldName+"']/parent::div/div")).click();
				}

			}
}



		@Then("^Verify the following columns value with the created data for the general card of private auction$")
		public void verifyGeneralCardValues(DataTable dt) throws InterruptedException, ParseException {
			WebDriverWait wait = new WebDriverWait(driver, 35);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");

				switch (fieldName) {
				case "Name":
					Assert.assertEquals(adspotsPage.adSpotNameHeader.getText(), enteredAuctionName);

					break;
				case "Publisher Name":
					Assert.assertEquals(adspotsPage.publisherNameField.getText(), enteredPublisherName);

					break;
				case "Related Packages":
					Assert.assertEquals(auctionPage.auctionPackages.getText(), enteredAuctionPackages);

					break;
				case "Date Range":
					System.out.println("Date range entered" + auctionPage.dateInput.getAttribute("value"));
					Assert.assertEquals(auctionPage.dateInput.getAttribute("value"), enteredAuctionDates);

					break;

				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

				}
			}
		}

		@Then("^Verify the following columns values for the general card of private auction is empty$")
		public void verifyGeneralCardValuesEmpty(DataTable dt) throws InterruptedException, ParseException {

			boolean isPresent;
			WebDriverWait wait = new WebDriverWait(driver, 35);
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");

				switch (fieldName) {
				case "Name":
					Assert.assertEquals(adspotsPage.adSpotNameHeader.getText(), "Create Private Auction");

					break;
				case "Related Packages":
					Assert.assertEquals(auctionPage.auctionPackages.getAttribute("value"), "");

					break;
				case "Date Range":
					Assert.assertEquals(auctionPage.dateInput.getAttribute("value").trim(), "GMT");

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

	@Then("^Verify search targeting options items$")
	public void verifySearchItems(DataTable dt) {
		List<List<String>> data = dt.asLists(String.class);
		data.forEach(e -> auctionPage.checkSearchForBlock(e.get(0), e.get(1)));
	}
}