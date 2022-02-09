package stepDefinitions;

import RXPages.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class PrivateAuctionPageStepDefinition extends RXPrivateAuctionsPage {

	RXPrivateAuctionsPage auctionPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	RXAdspotsPage adspotsPage;
	RXDealsPage dealsPage;
	Logger log = Logger.getLogger(PrivateAuctionPageStepDefinition.class);
	String enteredPublisherName;
	String enteredAuctionName;
	String enteredAuctionPackages;
	String enteredAuctionDates;
	List<String> enteredAuctionNameList = new ArrayList<String>();;

	public PrivateAuctionPageStepDefinition() {
		super();
		auctionPage = new RXPrivateAuctionsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		adspotsPage = new RXAdspotsPage();
		dealsPage = new RXDealsPage();

	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, 10);
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Private Auctions option under Sales$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandSales();
		wait.until(ExpectedConditions.visibilityOf(navOptions.privateAuctionLabel));
		navOptions.privateAuctionLabel.click();

	}

	@When("^Click on Private Auctions sub menu$")
	public void check_for_Adspot() throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(navOptions.privateAuctionLabel));
		navOptions.privateAuctionLabel.click();

	}

	@Then("^User displayed with Private Auctions page$")
	public void user_displayed_with_seats_page() throws Throwable {
		Assert.assertEquals(auctionPage.getPageHeading(), auctionPage.auctionHeaderStr);
		log.info("Auction Page Header is asserted  and it is : " + auctionPage.getPageHeading());

	}

	// Verify enabling abd disabling of an adspot from the overview page
	@When("^Click on the Pricate Auction create button$")
	public void createPrivateAuctionButtonClick() throws InterruptedException {
		createButtonClick("Create Private Auction");
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
	public void enterGenaralCardAuction(DataTable dt) throws Throwable {
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
					js.executeScript("arguments[0].scrollIntoView()", dropDownPublisher);
					dropDownPublisher.click();
				} else {
					dealsPage.selectPublisherByName(value);
				}
				Thread.sleep(5000);
				enteredPublisherName = adspotsPage.publisherNameField.getText();
//				System.out.println("publisher entered as :" + enteredPublisherName);
				wait.until(ExpectedConditions.visibilityOf(auctionPage.auctionNameField));
				break;

			case "Name":
//					js.executeScript("arguments[0].setAttribute('value', '')",adspotsPage.adSpotNameField);
				while (!auctionPage.auctionNameField.getAttribute("value").equals("")) {
					auctionPage.auctionNameField.sendKeys(Keys.BACK_SPACE);
				}
				Calendar cal = Calendar.getInstance();
				auctionPage.auctionNameField.sendKeys(value + cal.getTimeInMillis());
				enteredAuctionName = auctionPage.auctionNameField.getAttribute("value");
//				System.out.println("Entered Auction name:" + enteredAuctionName);
				break;
			case "Related Packages":
				while (!auctionPage.auctionPackages.getAttribute("value").equals("")) {
					auctionPage.auctionPackages.sendKeys(Keys.BACK_SPACE);
				}
				auctionPage.auctionPackages.sendKeys(value);
				enteredAuctionPackages = auctionPage.auctionPackages.getText();
//				System.out.println("Entered Auction packages:" + enteredAuctionPackages);
				break;

			case "Date Range":
				auctionPage.selectFifteenDaysRangeInNextMonth();
				enteredAuctionDates = auctionPage.dateInput.getAttribute("value");
//				System.out.println("Entered Auction dates:" + enteredAuctionDates);
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@When("^Click on Save Private Auction & Close button$")
	public void clickSaveBtn() throws Throwable {
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(auctionPage.saveandcloseButton));
		auctionPage.saveandcloseButton.click();

	}

	@When("^Click on Save and wait for dialog to close$")
	public void clickSaveBtnDialogClose() throws Throwable {
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(auctionPage.saveandcloseButton));
		js.executeScript("arguments[0].scrollIntoView()", auctionPage.saveandcloseButton);
		auctionPage.saveandcloseButton.click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
//		Thread.sleep(5000);

	}

	@When("^Click on Save Private Auction & Create Deal button and verify create deal page is opened$")
	public void clickSaveBtnCreateDeal() throws Throwable {
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(auctionPage.saveandcreatedealButton));
		auctionPage.saveandcreatedealButton.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//aside[@class='dialog']/header//div[contains(text(),'Create Deal')]"))));
		Assert.assertEquals(dealsPage.publisherNamesEntered.getText(), enteredPublisherName);
		Assert.assertEquals(dealsPage.privateActionFieldValue.getText(), enteredAuctionName);

	}

	@Then("^Verify following fields are not enabled for create page$")
	public void verifyMandatorFields(DataTable dt) throws InterruptedException {
		Thread.sleep(1000);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String isDisabled = auctionPage.mandatorFieldIsDisabledForCreatePage(fieldName);


			Assert.assertTrue(isDisabled.contains("disabled"));

		}

	}

	@Then("^Verify input values for following toggle fields in create page$")
	public void verifyDefaultValues(DataTable dt) throws InterruptedException {
		Thread.sleep(1000);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String active = list.get(i).get("Active");
			String isEnabled = auctionPage.toggleFieldIsEnabledForCreatePage(fieldName);



			if (active.equalsIgnoreCase("Yes")) {
				Assert.assertEquals(isEnabled, "true");

			} else {
				Assert.assertEquals(isEnabled, "false");
			}

		}

	}

	@Then("^\"(.*)\" following toggle fields in create page$")
	public void changeToggleFields(String enable, DataTable dt) throws InterruptedException {
		Thread.sleep(1000);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String isEnabled = auctionPage.toggleFieldIsEnabledForCreatePage(fieldName);



			if (enable.equalsIgnoreCase("Enable") && isEnabled.equals("false")) {

				auctionPage.toggleField(fieldName)
						.click();
			} else if (enable.equalsIgnoreCase("Disable") && isEnabled.equals("true")) {

						auctionPage.toggleField(fieldName)
						.click();
			}

		}
	}

	@Then("^Verify the following columns value with the created data for the general card of private auction$")
	public void verifyGeneralCardValues(DataTable dt) throws InterruptedException, ParseException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Name":
				Assert.assertEquals(auctionPage.auctionNameField.getAttribute("value"), enteredAuctionName);

				break;
			case "Publisher Name":
				Assert.assertEquals(adspotsPage.publisherNameField.getText(), enteredPublisherName);

				break;
			case "Related Packages":
				Assert.assertEquals(auctionPage.auctionPackages.getText(), enteredAuctionPackages);

				break;
			case "Date Range":
//				System.out.println("Date range entered" + auctionPage.dateInput.getAttribute("value"));
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
				Assert.assertEquals(auctionPage.dateInput.getAttribute("value").trim(), "");

				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}

	@When("^Click on the created auction name in the overview page$")
	public void clickNameOverview() throws Throwable {
		try {

			String enteredName = enteredAuctionName.replaceAll("\\s", "");
			List<WebElement> listOfNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[4]/a"));
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");

				if (enteredName.equals(reqName)) {
					listOfNames.get(k).click();
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + enteredAuctionName + "')]"))));
			Thread.sleep(4000);
		} catch (NullPointerException e) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]/a")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));

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

	@When("^Verify the created private auction data is matching with its overview list values$")
	public void verifyOverviewValues() throws Throwable {
		String adSpotName = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[4]/a"))
				.getText().replaceAll("\\s", "");
		String entergedName = enteredAuctionName.replaceAll("\\s", "");
		Assert.assertEquals(adSpotName, entergedName);
		String publisherName = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[5]"))
				.getText();
		Assert.assertEquals(publisherName, enteredPublisherName);
	}

	@When("^Verify clicking on Create a deal banner opens create deal entity page")
	public void verifyCreateDealBanner() {
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[@class='v-banner__content']//div[@class='v-banner__text']"))));
		String bannerText = driver
				.findElement(By.xpath("//div[@class='v-banner__content']//div[@class='v-banner__text']")).getText();
		Assert.assertTrue(bannerText.contains("Click the button to create a new deal"));
		driver.findElement(By.xpath("//div[@class='v-banner__content']//button")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//aside[@class='dialog']/header//div[contains(text(),'Create Deal')]"))));
		Assert.assertEquals(dealsPage.publisherNamesEntered.getText(), enteredPublisherName);
		Assert.assertEquals(dealsPage.privateActionFieldValue.getText(), enteredAuctionName);
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

	@When("^Hover on the Details column of the created private auction data$")
	public void hover_on_the_Details_column_of_the_created_private_auction_data() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]")))
				.perform();
	}

	@Then("^Verify that Details display the following data for each targeting$")
	public void verify_that_Details_display_the_following_data_for_each_targeting(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		wait.until(ExpectedConditions
				.visibilityOf(auctionPage.detailsForInventory));

		auctionPage.details.forEach(e -> auctionPage.verify_that_Details_matched(e, list.get(0)));
	}

	@And("^Select targeting options items$")
	public void selectItems(DataTable dt) {
		List<List<String>> data = dt.asLists(String.class);
		data.forEach(e -> auctionPage.selectForBlock(e.get(0), e.get(1)));
	}

	@Then("^Verify that warning banner is under Publisher name$")
	public void verify_that_warning_banner_is_under_Publisher_name(){
		wait.until(
				ExpectedConditions.visibilityOf(auctionPage.warningBannerUnderPublishername));
		Assert.assertTrue(auctionPage.warningBannerUnderPublishername.isDisplayed());
	}

	@Then("^Verify following Targeting in create Private Auction page is disabled$")
	public void verify_following_Targeting_in_create_Private_Auction_page_is_disabled(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
//			System.out.println(fieldName);
			auctionPage.targetingExpandPanel(fieldName).click();
			wait.until(
					ExpectedConditions.visibilityOf(auctionPage.targetingExpandPanelContent(fieldName)));
			String isDisabled = auctionPage.targetingFieldIsDisabledForCreatePage(fieldName);
				Assert.assertEquals(isDisabled, "scrollable disabled");
		}
	}

	@Then("^Verify \"([^\"]*)\" button in create Private Auction page is disabled$")
	public void verify_button_in_create_Private_Auction_page_is_disabled(String arg1) {
		String isDisabled = "";
	   switch(arg1) {
	   case "Save Private Auction & Close":
		   isDisabled = auctionPage.saveandcloseButton.getAttribute("disabled");
		   break;
	   case "Save Private Auction & Create Deal":
		   isDisabled = auctionPage.saveandcreatedealButton.getAttribute("disabled");
		   break;
	   default:
		   Assert.fail("can not find the button "+ arg1);
	   }
	   Assert.assertEquals(isDisabled, "true");
	}

	@Then("^Verify following errors are displayed near save button$")
	public void verify_following_errors_are_displayed_near_save_button(DataTable dt) {
		List<String> data = dt.asList(String.class);
		data.forEach(e -> auctionPage.error_are_displayed_near_save_button(e));
	}

	@Then("^Verify that error disapear according to fields filled$")
	public void verify_that_error_disapear_according_to_fields_filled(DataTable dt) {
		List<String> data = dt.asList(String.class);
		data.forEach(e -> auctionPage.error_disapear_according_to_fields_filled(e));
	}

	@Then("^Check only one error \"([^\"]*)\" is present for date$")
	public void check_only_one_error_is_present_for_date(String error) {
		Assert.assertEquals(auctionPage.dateMessage.getText(),error);
	}

	@Then("^Verify that warning banner is not under Publisher name$")
	public void verify_that_warning_banner_is_not_under_Publisher_name()  {
		Assert.assertFalse(auctionPage.warningBannerUnderPublishername.isDisplayed());
	}

	@Then("^Select one \"([^\"]*)\" Private Auctions item$")
	public void select_one_Private_Auctions_item(String active) {
		enteredAuctionNameList.clear();
		List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[7]"));
		for (int k = 0; k < listActives.size(); k++) {
				String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
				if (active.equals(reqActive)) {
					auctionPage.privateAuctionsCheckBox(k+1).click();
					enteredAuctionNameList.add(auctionPage.adSpotName(k+1));
					break;
				}
		}
	}

	@Then("^Verify the edited private auction data is matching with its overview list values$")
	public void verify_the_edited_private_auction_data_is_matching_with_its_overview_list_values() throws Throwable {
		String adSpotName = "";
		String entergedName = enteredAuctionName.replaceAll("\\s", "");
		List<WebElement> listActives = driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[4]/a"));
		for (int k = 0; k < listActives.size(); k++) {
			adSpotName = listActives.get(k).getText().replaceAll("\\s", "");
			if (entergedName.equals(adSpotName)) {
				break;
			}
		}
		Assert.assertEquals(adSpotName, entergedName);

	}

	@Then("^Verify that following buttons are present in Private Auctions list page$")
	public void verify_that_following_buttons_are_present_in_Private_Auctions_list_page(DataTable dt) {
		List<String> buttons = dt.asList(String.class);
		buttons.forEach(e -> Assert.assertTrue(auctionPage.toolbarButton(e).isDisplayed(), e + " is not present."));
	}

	@When("^Click \"([^\"]*)\" button in Private Auctions list page$")
	public void click_button_in_Private_Auctions_list_page(String buttonName) {
		auctionPage.toolbarButton(buttonName).click();
		if(!buttonName.equals("Edit Private Auction")) {
			wait.until(ExpectedConditions.invisibilityOf(auctionPage.toolbarButton(buttonName)));
		}
	}

	@Then("^Edit Private Auction pop up is present$")
	public void edit_Private_Auction_pop_up_is_present() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + enteredAuctionNameList.get(0) + "')]"))));
	}

	@Then("^\"([^\"]*)\" is displayed for the created private auctions$")
	public void is_displayed_for_the_created_private_auction(String arg1) {
		List<WebElement> listOfNames = driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[4]/a"));
		for(int i=0;i<enteredAuctionNameList.size();i++) {
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");
				if (enteredAuctionNameList.get(i).equals(reqName)) {
					Assert.assertEquals(auctionPage.privateAuctionsActive(k+1).getText(), arg1, auctionPage.privateAuctionsActive(k+1).getText() +" is displayed for the created private auction");;
					break;
				}
			}
		}

	}

	@Then("^Select (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" Private Auctions items$")
	public void select_and_Private_Auctions_items(int num1, String inactive, int num3, String active) throws Throwable {
		enteredAuctionNameList.clear();
		List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[7]"));
		for (int k = 0; k < listActives.size(); k++) {
				String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
				if (active.equals(reqActive) && num3 > 0) {
					auctionPage.privateAuctionsCheckBox(k+1).click();
					enteredAuctionNameList.add(auctionPage.adSpotName(k+1));
					num3--;
				}
				if(inactive.equals(reqActive) && num1 > 0) {
					auctionPage.privateAuctionsCheckBox(k+1).click();
//					System.out.println(auctionPage.adSpotName(k+1));
					enteredAuctionNameList.add(auctionPage.adSpotName(k+1));
					num1--;
				}
				if(num1 == 0 && num3 == 0) {
					break;
				}
		}
		Assert.assertEquals(num1,0,num1 +" Inactive Private Auctions is not selected.");
		Assert.assertEquals(num3,0,num3 +" Active Private Auctions is not selected.");
	}

	@When("^Open \"([^\"]*)\" under Targeting in Private Auctions page$")
	public void open_under_Targeting_in_Private_Auctions_page(String targetingName) throws Throwable {
		auctionPage.expandTargetingPanel(targetingName);
	}

	@Then("^Verify that only Active media and adspot are displayed$")
	public void verify_that_only_Active_media_and_adspot_are_displayed() throws Throwable {
		auctionPage.expand_All_Inventory_Media_Items();
		auctionPage.verify_media_and_adspot_are_Active();
	}

	@When("^Type \"([^\"]*)\" in Inventory search box$")
	public void type_in_Inventory_search_box(String arg1) throws Throwable {
		auctionPage.targetingBlockSearchInput("Inventory").click();
		wait.until(ExpectedConditions.visibilityOf(targetingBlockSearchInput("Inventory")));
		auctionPage.targetingBlockSearchInput("Inventory").sendKeys(arg1);
		wait.until(ExpectedConditions.textToBePresentInElement(listCounter("Inventory"), "1"));
	}

	@Then("^\"([^\"]*)\" App is displayed and opened with childs$")
	public void app_is_displayed_and_opened_with_childs(String arg1) throws Throwable {
		auctionPage.inventory_Media_is_displayed(arg1);
		auctionPage.inventory_Media_are_opened_with_childs();
	}

	@Then("^Verify that Active/Inactive media and adspot are displayed$")
	public void verify_that_Active_Inactive_media_and_adspot_are_displayed() throws Throwable {
		auctionPage.Active_Inactive_media_and_adspot_are_displayed();
	}

}