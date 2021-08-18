package stepDefinitions;


import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;
import RXPages.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import static org.testng.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DealsPageStepDefinition extends RXBaseClass {


	RXUtile rxUTL;
	RXDealsPage dealsPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(DealsPageStepDefinition.class);

	public DealsPageStepDefinition() {
		super();
		dealsPage = new RXDealsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		rxUTL =new RXUtile();

	}
	WebDriverWait wait = new WebDriverWait(driver, 10);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public String enteredPublisher;
	public String enteredDSP;
	private Map<String,List<Boolean>> floorPriceValues = new HashMap<>();
	private LinkedHashMap<String,String> detailsData = new LinkedHashMap<>();
	public List<String> activeDealIDList = new ArrayList<>();
	public List<String> inactiveDealIDList = new ArrayList<>();
	public String startDate;
	public String endDate;
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Deals option under Sales$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandSales();
		wait.until(ExpectedConditions.visibilityOf(navOptions.dealsLabel));
		navOptions.dealsLabel.click();

	}

	@When("^Click on Deals sub menu$")
	public void check_for_Adspot() throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(navOptions.dealsLabel));
		navOptions.dealsLabel.click();

	}

	@Then("^User displayed with Deals page$")
	public void user_displayed_with_seats_page() throws Throwable {
		Assert.assertEquals(dealsPage.getPageHeading(), dealsPage.dealHeaderStr);
		log.info("Deal Page Header is asserted  and it is : " + dealsPage.getPageHeading());

	}
	
	//Verify enabling abd disabling of an adspot from the overview page

		@When("^Verify enabling and disabling of a deal from the overview page$")
		public void verifyEnableDisableDeal() throws InterruptedException {
			for (int i = 0; i <= 1; i++) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
				List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active");
				String status = coulmnData.get(0).getText();
				switch (status) {
				case "Active":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewDisablebutton.isDisplayed());
					dealsPage.clickOverViewDisablebutton();
					driverWait().until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
					List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active");
					Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
					break;
				case "Inactive":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewEnablebutton.isDisplayed());
					String enableText = dealsPage.overviewButtons.getText().replaceAll("\\s", "");
					Assert.assertEquals(enableText, "EDITDEALDEACTIVATEDEALACTIVATEDEAL");
					dealsPage.clickOverViewEnablebutton();
					driverWait().until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
					List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active");
					Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
					break;

				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

				}
			}
		}
		@Then("^Verify the deal overview page contains following columns$")
		public void verifyColumnsNameInList(DataTable dt) throws InterruptedException {
			List<String> columnsNamePresent = new ArrayList<>();
			List<WebElement> numberOfHeaders = navOptions.tableHeadersList;
			numberOfHeaders.forEach(e -> columnsNamePresent.add(e.getText()));
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (Map<String,String> map: list) {
				String columnName = map.get("ColumnName");
//				System.out.println(columnName);
				Assert.assertTrue(columnsNamePresent.contains(columnName));
			}
		}

	@When("^Click create a new deal$")
	public void clickCreateDealButton() {
		dealsPage.clickCreateDealButton();
	}

	@When("^Hover over deal details button$")
	public void hoverOverDealButton() {
		driver.navigate().refresh();
		dealsPage.hoverOverDetailsButton();
		detailsData = dealsPage.getDealsDetailsData();
	}
	@When("^Get deal details data$")
	public void getDealDetailsData() {
		detailsData = dealsPage.getDealsDetailsData();
	}
	@Then("^Verify deal details data is correct$")
	public void verifyDetailsData() {
		Assert.assertTrue(RXDealsPage.getBuyersEnteredValues().equals(detailsData));
	}
	@Then("^Create deal menu is opened$")
	public void isCreateDealMenuOpened() {
		Assert.assertTrue(dealsPage.isCreateDealMenuOpened());
	}

	@Then("^Edit deal menu is opened$")
	public void isEditDealMenuOpened() {
		Assert.assertTrue(dealsPage.isEditDealMenuOpened());
	}

	@When("^Click on publisher input$")
	public void clickOnPublisherInput() {
		dealsPage.expandPublisherNameList();
	}

	@And("^Select publisher by name: \"(.*)\"$")
	public void selectPublisherByName(String name) throws Throwable {
		 dealsPage.selectPublisherByName(name); 

	}

    @Then("^Verify the currency is correct$")
    public void verifyCurrencyIsValid() {
        dealsPage.isCurrencyNameValid(dealsPage.getCurrencyText());
    }

    @Then("^Verify required fields$")
    public void verifyRequiredFields() throws InterruptedException {
        dealsPage.clickSaveDealButton();
        js.executeScript("arguments[0].scrollIntoView();",dealsPage.publisherNameInput );
//        Thread.sleep(5000);
        Assert.assertTrue(dealsPage.verifyRequiredFields());
    }

	@Then("^Select Private Auction by name: \"([^\"]*)\"$")
	public void select_privateAuction_by_name(String name) throws Throwable {
		dealsPage.selectPrivateAuctionByName(name);
	}
	
	@Then("^Select DSP by name: \"([^\"]*)\"$")
	public void select_DSP_by_name(String name) throws Throwable {
		dealsPage.selectDSPByName(name);
	}

	@Then("^enter the following values$")
	public void enter_the_following_values(DataTable dt) throws Throwable {
		String value;
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (Map<String, String> stringMap : list) {
			for (String key : stringMap.keySet()) {
				value = stringMap.get(key);
//				System.out.println("=== select/enter value for " + key + " ===");
				switch (key) {
					case "publisher":
						dealsPage.selectPublisherByName(value);
						enteredPublisher = dealsPage.publisherNamesEntered.getText();
						break;
					case "PrivateAuction":
						dealsPage.selectPrivateAuctionByName(value);
						break;
					case "DSPValue":
						dealsPage.selectDSPByName(value);
						enteredDSP = dealsPage.dspValueDiv.getText();
						break;
					case "EntDealName":
						dealsPage.enterDealName(value + rxUTL.getRandomNumberFourDigit());
						break;
					case "Values":
						dealsPage.enterValue(value);
						break;
				}
			}
		}
	}

	@Then("^enter the following DSP buyer details\\.$")
	public void enter_the_following_DSP_buyer_details(DataTable dt) {
		dealsPage.enterDSPValues(dt.asMaps(String.class, String.class).get(0));
	}
	@Then("^enter the original DSP buyer details\\.$")
	public void enter_the_original_DSP_buyer_details(DataTable dt) {
		dealsPage.enterDSPValues(dt.asMaps(String.class, String.class).get(0), true);
	}
	@Then("^enter the original DSP buyer details with clear\\.$")
	public void enter_the_original_DSP_buyer_details_with_clear(DataTable dt) {
		dealsPage.enterDSPValues(dt.asMaps(String.class, String.class).get(0), true,false,true);
	}
	@Then("^enter the (.*) character as DSP buyer details$")
	public void enter_the_char_as_DSP_buyer_details(int length, DataTable dt) {
		dealsPage.enterDSPValues(modifyMapByBase(dt.asMaps(String.class, String.class).get(0),"abcdefghijklmnopqrstuvwxyz1234567890", length), true);
	}
	@Then("^enter the random int as DSP buyer details$")
	public void enter_the_random_int_as_DSP_buyer_details(DataTable dt) {
		dealsPage.enterDSPValues(modifyMapByBase(dt.asMaps(String.class, String.class).get(0),"1234567890", 30), true);
	}
	@Then("^enter the following DSP buyer details with clear\\.$")
	public void enter_the_following_DSP_buyer_details_clear(DataTable dt) {
		dealsPage.enterDSPValues(dt.asMaps(String.class, String.class).get(0), false, false, true);
	}
	@Then("^enter previously used DSP buyer details using autofill\\.$")
	public void enter_previously_used_DSP_buyer_details_using_autofill() {
		dealsPage.enterDSPValues(RXDealsPage.getBuyersEnteredValues(), true, true);
	}

	@Then("^enter text DSP buyer details\\.$")
	public void enter_text_DSP_buyer_details() {
		dealsPage.enterDSPValues(RXDealsPage.getBuyersEnteredValues(), true);
	}
	@Then("^enter previously used DSP buyer details using autofill and clear\\.$")
	public void enter_previously_used_DSP_buyer_details_clear() {
		dealsPage.enterDSPValues(RXDealsPage.getBuyersEnteredValues(), true, true, true);
	}
	@When("^change the publisher name to \"([^\"]*)\"$")
	public void change_the_publisher_name_to(String publName) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.publisherNameInput );
		dealsPage.expandPublisherNameList();
		dealsPage.selectPublisherByName(publName);
	}
	
	@Then("^\"([^\"]*)\" the DSP buyer$")
	public void the_DSP_buyer(String enableDisable) throws Throwable {
		dealsPage.clickBuyerActivationToggle(enableDisable);
	}
	
	@Then("^Verify the following message is displayed when the publisher changed for deal$")
	public void verify_the_following_message_is_displayed_when_the_publisher_changed_for_deal(DataTable dt) throws Throwable {
		
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
//			System.out.println("Banner Message "+ dealsPage.getChangePublisherBannerMsg());
			Assert.assertEquals(dealsPage.getChangePublisherBannerMsg(), expectedMessage);

		}
	}

	@Then("^Verify the following message is not displayed when the publisher changed for deal$")
	public void verify_the_following_message_is_not_displayed_when_the_publisher_changed_for_deal(DataTable dt) throws Throwable {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
//			System.out.println("Check if Banner Message is displayed >>> "+ expectedMessage);
			Assert.assertFalse(dealsPage.getElementByXpathWithParameter(dealsPage.changePubBannerMsgXpath, expectedMessage).isDisplayed());

		}
	}
	
	@Then("^Select \"([^\"]*)\" on the publisher change banner displayed for deal$")
	public void select_on_the_publisher_change_banner_displayed_for_deal(String cancelOrAccept) throws Throwable {
	    String cancOrAcce=dealsPage.cancelOrAcceptChangePublisherBannerMsg(cancelOrAccept);
	    if(cancOrAcce.equalsIgnoreCase("Accepted"))
	    {
	    	enteredPublisher=dealsPage.publisherNamesEntered.getText();
	    }
	}
	@Then("^Verify the following general value with the created data of deal$")
	public void verify_the_following_general_value_with_the_created_data_of_deal(DataTable dt) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dealName);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			
			case "Publisher Name":
//				System.out.println("Entered Publisher Name :"+ enteredPublisher);
				Assert.assertEquals(dealsPage.publisherNamesEntered.getText(),enteredPublisher);
				break;
			case "Deal Name":
//				System.out.println("Entered Deal Name :"+ dealsPage.EntereddealName);
				Assert.assertEquals(dealsPage.dealName.getAttribute("value"), dealsPage.EntereddealName);

				break;
			case "Private Auction":
//				System.out.println("Entered Private Auction :"+ dealsPage.enteredPrivateAuct);
				Assert.assertEquals(dealsPage.privateActionFieldValue.getText(), dealsPage.enteredPrivateAuct);

				break;
			case "Date Range":
//				System.out.println("Entered enteredDateRange :"+ dealsPage.enteredDateRange);
				Assert.assertEquals(dealsPage.dateRange.getAttribute("value"), dealsPage.enteredDateRange);

				break;
			case "Value":
//				System.out.println("Entered enteredValue :"+ dealsPage.enteredValue);
				Assert.assertEquals(dealsPage.value.getAttribute("value"), dealsPage.enteredValue);

				break;
			case "Currency":
//				System.out.println("Entered currencyFiledValue :"+ dealsPage.currencyFiledValue);
				Assert.assertEquals(dealsPage.currencyValue.getText(), dealsPage.currencyFiledValue);

				break;
			case "DSP":
//				System.out.println("Entered enteredDSPValue :"+ dealsPage.enteredDSPValue);
				Assert.assertEquals(dealsPage.dspValueDiv.getText(), enteredDSP);

				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}
	
	@Then("^Verify the following buyers details with the created data of deal$")
	public void verify_the_following_buyers_details_with_the_created_data_of_deal(DataTable dt) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDomainAdvertiserPassthroughString);
		// Get element value from the Buyer form and compare to values from Data Table
		getDataFromTable(dt).forEach(e ->
						Assert.assertEquals(RXDealsPage.getDSPBuyerFieldElement(
							e.getValue()).getAttribute("value"),
							dealsPage.getBuyersEnteredValues().getOrDefault(e.getValue(), "")));
		}

	@Then("^Verify the following buyers details with the created long data of deal are truncated$")
	public void verify_the_following_buyers_long_details_are_truncated(DataTable dt) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDomainAdvertiserPassthroughString);
		// Get element value from the Buyer form and compare to values from Data Table
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(RXDealsPage.getDSPBuyerFieldElement(
						e.getValue()).getAttribute("value").length() == 255));
	}

	@Then("^Verify the following buyers details with the created data of deal using autofill$")
	public void verify_the_following_buyers_details_with_the_created_data_of_deal_using_autofill(DataTable dt) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDomainAdvertiserPassthroughString);
		// Get element value from the Buyer form and compare to values from Data Table
		getDataFromTable(dt).forEach(e -> Assert.assertTrue(RXDealsPage.getDSPBuyerFieldElement(
						e.getValue()).getAttribute("value")
						.startsWith(dealsPage.getBuyersEnteredValues().get(e.getValue()))));;
	}
	@Then("^Verify the buyer is \"([^\"]*)\"$")
	public void verify_the_buyer_is(String status) {
		// XOR operator is used, if both conditions return different values, assertion will fail
		Assert.assertFalse(status.equalsIgnoreCase("enabled") ^ dealsPage.isBuyerEnabled());
	}
	@Then("^Verify the following general values are reset to default values$")
	public void verify_the_following_general_values_are_reset_to_default_values(DataTable dt) throws Throwable {
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dealName);
		wait.until(ExpectedConditions.visibilityOf(dealsPage.dealName));
//		dealsPage.dealName.click();
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			
			case "Publisher Name":
				Assert.assertEquals(dealsPage.publisherNamesEntered.getText(),enteredPublisher);
				break;
			case "Deal Name":
				
				Assert.assertEquals(dealsPage.dealName.getAttribute("value"),"");
				break;
			
			case "Private Auction": 
				WebElement privateAuct= driver.findElement(By.xpath(
			  "//label[text()='Private Auction']/following-sibling::div[@class='v-select__selections']"
			  )); Assert.assertEquals(privateAuct.getText(),""); 
			  break; 
			case "Date Range":
			  
			  	Assert.assertEquals(dealsPage.dateRange.getAttribute("value"), "");
			    break; 
			case "Value":
				Assert.assertEquals(dealsPage.value.getAttribute("value"),""); 
			    break; 
			case "Currency": 
				Assert.assertNotNull(dealsPage.currencyValue.getText()); 
			    break;
			case "DSP": 
				WebElement valDSP= driver.findElement(By.xpath(
			  "//label[text()='DSP']/following-sibling::div[1]"));
			  Assert.assertEquals(valDSP.getText(),""); 
			  break;
			 
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}
	@Then("^Verify the following buyers details are reset to default values$")
	public void verify_the_following_buyers_details_are_reset_to_default_values(DataTable dt) throws Throwable {
//		js.executeScript("arguments[0].scrollIntoView();",dealsPage.relatedProposal);
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDomainAdvertiserPassthroughString);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			switch (fieldName) {			
			case "DSP Seat ID":
				Assert.assertEquals(dealsPage.dSPSeatID.getAttribute("value"),"");
				break;
			case "DSP Seat Name":
				Assert.assertEquals(dealsPage.dSPSeatName.getAttribute("value"), "");
				break;
			case "Advertiser ID":
				Assert.assertEquals(dealsPage.AdvertiserId.getAttribute("value"), "");
				break;
			case "Advertiser Name":
				Assert.assertEquals(dealsPage.advertiserName.getAttribute("value"), "");
				break;
			case "DSP Seat Passthrough String":
				Assert.assertEquals(dealsPage.dSPSeatPassthroughString.getAttribute("value"), "");
				break;
			case "DSP Domain Advertiser Passthrough String":
				Assert.assertEquals(dealsPage.dSPDomainAdvertiserPassthroughString.getAttribute("value"), "");
				break;
				/*
				 * case "Related Proposal":
				 * Assert.assertEquals(dealsPage.relatedProposal.getAttribute("value"), "");
				 * break; 
				 */
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify the following general deal details are not fillable$")
	public void Verify_the_following_general_deal_details_are_not_fillable(DataTable dt) throws Throwable {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Deal Name":
				Assert.assertFalse(dealsPage.dealName.isEnabled());
				break;
			case "Private Auction":
				Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Private Auction']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
				break;
			case "Date Range":
				Assert.assertFalse(dealsPage.dateRange.isEnabled());
				break;
			case "Value":
				Assert.assertFalse(dealsPage.value.isEnabled());
				break;
			case "Currency":
				Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Currency']/following-sibling::div/input")).isEnabled());
				break;
			case "DSP":
				Assert.assertFalse(driver.findElement(By.xpath("//label[text()='DSP']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
				break;	
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}
	@Then("^Verify the following buyer details of deal are not fillable$")
	public void verify_the_following_buyer_details_of_deeal_are_not_fillable(DataTable dt) throws Throwable {
//		js.executeScript("arguments[0].scrollIntoView();",dealsPage.relatedProposal);
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDomainAdvertiserPassthroughString);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			switch (fieldName) {			
			case "DSP Seat ID":
				Assert.assertFalse(dealsPage.dSPSeatID.isEnabled());
				break;
			case "DSP Seat Name":
				Assert.assertFalse(dealsPage.dSPSeatName.isEnabled());
				break;
			case "Advertiser ID":
				Assert.assertFalse(dealsPage.AdvertiserId.isEnabled());
				break;
			case "Advertiser Name":
				Assert.assertFalse(dealsPage.advertiserName.isEnabled());
				break;
			case "DSP Seat Passthrough String":
				Assert.assertFalse(dealsPage.dSPSeatPassthroughString.isEnabled());
				break;
			case "DSP Domain Advertiser Passthrough String":
				Assert.assertFalse(dealsPage.dSPDomainAdvertiserPassthroughString.isEnabled());
				break;
			/*
			 * case "Related Proposal":
			 * Assert.assertFalse(dealsPage.relatedProposal.isEnabled()); break;
			 */			
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}

	@When("^change the DSP name to \"([^\"]*)\"$")
	public void change_the_DSP_name_to(String dealName) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dspDropDown );
		dealsPage.selectDSPByName(dealName);
	}

	@Then("^Verify the following message is displayed when the DSP changed for deal$")
	public void verify_the_following_message_is_displayed_when_the_DSP_changed_for_deal(DataTable dt) throws Throwable {
		
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
//			System.out.println("Banner Message "+ dealsPage.getChangeDSPBannerMsg());
			Assert.assertEquals(dealsPage.getChangeDSPBannerMsg(), expectedMessage);

		}
	}
	@Then("^Select \"([^\"]*)\" on the DSP change banner displayed for deal$")
	public void select_on_the_DSP_change_banner_displayed_for_deal(String cancelOrAccept) throws Throwable {
	    String cancOrAcce=dealsPage.cancelOrAcceptChangeDSPBannerMsg(cancelOrAccept);
	    if(cancOrAcce.equalsIgnoreCase("Accepted"))
	    {
	    	enteredDSP=dealsPage.dspValueDiv.getText();
	    }
	}
	
	@When("^click on Save deal$")
	public void click_on_Save_deal() throws Throwable {
		dealsPage.saveDeal();
	}
	@Then("^Verify deal contains copy deal id message$")
	public void verify_deal_id_has_copy_message() throws Throwable {
//		System.out.println(dealsPage.getBannerMessage());
		Assert.assertTrue(dealsPage.getBannerMessage().toLowerCase().contains("copy deal id"));
	}
	@When("^Verify banner message about inactive buyers$")
	public void verify_banner_message_with_inactive_buyers() throws Throwable {
		Assert.assertTrue(dealsPage.getAlertMessage().toLowerCase().contains("an active deal requires at least one enabled buyer"));
	}
	@When("^copy the deal ID$")
	public void copy_the_deal_ID() throws Throwable {
		dealsPage.copyDealIDToClipBoard();
	}
	
	@When("^search the deal ID$")
	public void search_the_deal_ID() throws Throwable {
		dealsPage.pasteDealIdToSearch();
		
	}
	
	//table/tbody/tr[1]/td[3]/span/a
	@When("^Select the deal and click on edit$")
	public void Select_the_deal_and_click_on_edit() throws Throwable {
		dealsPage.clickOnDealNameInListView();
		wait.until(ExpectedConditions.visibilityOf(dealsPage.saveButton));
	}
	
	@Then("^Verify Publisher,Private Auction and DSP are non-editable$")
	public void Verify_Publisher_Private_Auction_and_DSP_are_non_editable() throws Throwable {
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Private Auction']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='DSP']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
		
	}


	@Then("^Verify Publisher field is not editable and preselected with \"([^\"]*)\"$")
	public void Verify_Publisher_field_is_not_editable_and_preselected_with(String pubName) throws Throwable {
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/input")).isEnabled());
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/div")).getText(),"Viber" );
	}

	@When("^Enter correct floor prices values: \"(.*)\"$")
	public void enterCorrectFloorPrices(List<Integer> prices) {
		floorPriceValues.put("Positive", dealsPage.enterFloorPrices(prices));
	}

	@When("^Enter incorrect floor prices values: \"(.*)\"$")
	public void enterIncorrectFloorPrices(List<Integer> prices) {
		floorPriceValues.put("Negative", dealsPage.enterFloorPrices(prices));
	}
	
	@Then("^Verify Floor Prices$")
	public void verifyFloorPrices() {
		Assert.assertFalse(floorPriceValues.get("Positive").contains(true)
			&& floorPriceValues.get("Negative").contains(false),
			"Error while verifying floor prices");
	}
	
	  @Then("^Click on Add more seats ten times$") 
	  public void click_on_Add_more_seats_time() throws Throwable 
	  {
		  for(int i=1;i<=9;i++)
			{
			  	dealsPage.clickAddMoreSeats(); 
//			  	System.out.println("Count time"+i);
			}
	  }

	@Then("^Click on Add more seats button$")
	public void click_on_Add_more_seats_button() throws Throwable
	{
		dealsPage.clickAddMoreSeats();
	}

	
	  @Then("^Verify that the Add more seats is disabled and ten DSP panels are added$") 
	  public void verify_that_the_Add_more_seats_is_disabled_and_ten_DSP_panels_are_added() throws Throwable
	  {
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.addMoreSeats);
		  Assert.assertTrue(dealsPage.addMoreSeats.getAttribute("disabled").equalsIgnoreCase("true"));

		  for(int i=1;i<=10;i++) 
		  {
		  wait.until(ExpectedConditions.visibilityOf(dealsPage.addedDSPPanel(i)));
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.addedDSPPanel(i));
		  Assert.assertTrue(dealsPage.addedDSPPanel(i).isDisplayed()); 
		  }
	  }
	  
	  @Then("^Enabled added seats$")
	  public void click_on_Enabled_added_seats() throws Throwable {
		  for(int i=1;i<=10;i++) 
		  {
		  wait.until(ExpectedConditions.elementToBeClickable(dealsPage.enableDSPdisable(i)));
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.enableDSPdisable(i));
		  dealsPage.enableDSPdisable(i).click();
		  }
	  }

	  @Then("^Verify that the added seat is enabled$")
	  public void verify_that_the_added_seat_is_enabled() throws Throwable {
		  for(int i=1;i<=10;i++) 
		  {
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPEnable(i));
		  Assert.assertTrue(dealsPage.dSPEnable(i).isEnabled()); 
		  }
	  }
	  @Then("^Disabled added seats$")
	  public void click_on_disabled_added_seats() throws Throwable {
		  for(int i=1;i<=10;i++) 
		  {
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.enableDSPdisable(i));
		  dealsPage.enableDSPdisable(i).click();
		  }
	  }

	  @Then("^Verify that the added seat is disabled$")
	  public void verify_that_the_added_seat_is_disabled() throws Throwable {
		  for(int i=1;i<=10;i++) 
		  {
		  js.executeScript("arguments[0].scrollIntoView();",dealsPage.dSPDisable(i));
		  Assert.assertTrue(dealsPage.dSPDisable(i).isEnabled()); 
		  }
	  }
	  
	  @Then("^Delete the added seats$")
	  public void click_on_delete_the_added_seats() throws Throwable {
		  for(int i=1;i<=10;i++) 
		  {
			            js.executeScript("arguments[0].scrollIntoView();",dealsPage.buyerDelete(1));
			  		    dealsPage.buyerDelete(1).click();
		  }
	  }

	  @Then("^Verify that the added seat is deleted$")
	  public void verify_that_the_added_seat_is_deleted() throws Throwable {
		  boolean flag= false;
		  try {
			  dealsPage.addedDSPPanel(1).isEnabled();
			  flag=false;
		  }catch(NoSuchElementException e)
		  {
			  flag=true;
		  }
		  Assert.assertTrue(flag); 
	  }


	@Then("^Verify \"([^\"]*)\" seat panels are displayed$")
	public void verify_seat_panels_are_displayed(String arg0) throws Throwable {
		int buyersCount = dealsPage.getBuyersCardPaddingElemts();
//		System.out.println("Buyer count >>> " + buyersCount);
		if(buyersCount != Integer.parseInt(arg0)){
			fail(buyersCount + " seat panels are displayed, not " + arg0);
		}
	}

	@Then("^Verify no buyers entities were saved$")
	public void verify_no_buyers_entities_were_saved() {
		int buyersCount = dealsPage.getBuyersCardPaddingElemts();
		String buyerCardLabel = dealsPage.buyerCardLabel.getText().trim();
//		System.out.println("Buyer count >>> " + buyersCount);
//		System.out.println("Buyer Card Label >>> " + buyerCardLabel);
		if(buyersCount != 1){
			fail("More than one buyers entities are displayed.");
		}
		if(!buyerCardLabel.equals("")){
			fail("Buyer values are displayed.");
		}
	}

	@When("^Disabled \"([^\"]*)\" added seats$")
	public void disabled_added_seats(String arg0) throws Throwable {
		for(int i=1;i<=Integer.parseInt(arg0);i++) {
			js.executeScript("arguments[0].scrollIntoView();",dealsPage.enableDSPdisable(i));
			dealsPage.enableDSPdisable(i).click();
		}
	}

	@Then("^Verify \"([^\"]*)\" added seats are \"([^\"]*)\"$")
	public void verify_added_seats_are_disabled(String count, String status) throws Throwable {
//		WebElement element = null;
//		for(int i=1;i<=Integer.parseInt(count);i++) {
//			System.out.println("verify_added_seats_are_disabled i >>> " + i);
//			if(status.equals("disabled")){
//				element = dealsPage.dSPDisable(i);
//			}else{
//				element = dealsPage.dSPEnable(i);
//			}
//			js.executeScript("arguments[0].scrollIntoView();", element);
//			Assert.assertTrue(element.isEnabled());
//		}

		String elemetXpath;
		if(status.equals("disabled")){
			elemetXpath = dealsPage.dSPDisable;
		}else{
			elemetXpath = dealsPage.dSPEnable;
		}
		Assert.assertEquals(String.valueOf(driver.findElements(By.xpath(elemetXpath)).size()),count);
	}

	@Then("^Verify entity page is disabled$")
	public void verify_entity_page_is_disabled() {
		//Publisher name
//		System.out.println("Publisher Name field's class attribute >>> " + dealsPage.publisherNamesEntered.getAttribute("class"));
		if( !dealsPage.publisherNamesEntered.getAttribute("class").contains("disabled")){
			fail("Publisher Name in Create Deal is not disabled.");
		}

		//Deal Name
//		System.out.println("Deal Name field's enabled >>> " + dealsPage.dealName.isEnabled());
		if( dealsPage.dealName.isEnabled()){
			fail("Deal Name in Create Deal is not disabled.");
		}

		//Floor Price
//		System.out.println("Floor Price field's enabled >>> " + dealsPage.value.isEnabled());
		if( dealsPage.value.isEnabled()){
			fail("Floor Price in Create Deal is not disabled.");
		}

		//Currency
//		System.out.println("Currency field's class attribute >>> " + dealsPage.currencyValue.getAttribute("class"));
		if( !dealsPage.currencyValue.getAttribute("class").contains("disabled")){
			fail("Currency in Create Deal is not disabled.");
		}

		//DSP
//		System.out.println("DSP field's enabled >>> " + dealsPage.dspValueInput.isEnabled());
		if( dealsPage.dspValueInput.isEnabled()){
			fail("DSP in Create Deal is not disabled.");
		}

		//Add more seats button
//		System.out.println("Add more seats button's enabled >>> " + dealsPage.addMoreSeats.isEnabled());
		if( dealsPage.addMoreSeats.isEnabled()){
			fail("Add more seats button in Create Deal is not disabled.");
		}

		//Enable button
//		System.out.println("Enable button's enabled >>> " + dealsPage.enableBtnInBuyerPanel.isEnabled());
		if( dealsPage.enableBtnInBuyerPanel.isEnabled()){
			fail("Enable button in Create Deal is not disabled.");
		}

		//DSP Seat ID
//		System.out.println("DSP Seat ID's enabled >>> " + dealsPage.dSPSeatID.isEnabled());
		if( dealsPage.dSPSeatID.isEnabled()){
			fail("DSP Seat ID in Create Deal is not disabled.");
		}

		//DSP Seat Name
//		System.out.println("DSP Seat Name's enabled >>> " + dealsPage.dSPSeatName.isEnabled());
		if( dealsPage.dSPSeatName.isEnabled()){
			fail("DSP Seat Name in Create Deal is not disabled.");
		}

		//Advertiser ID
//		System.out.println("Advertiser ID's enabled >>> " + dealsPage.AdvertiserId.isEnabled());
		if( dealsPage.AdvertiserId.isEnabled()){
			fail("Advertiser ID in Create Deal is not disabled.");
		}

		//Advertiser Name
//		System.out.println("Advertiser Name's enabled >>> " + dealsPage.advertiserName.isEnabled());
		if( dealsPage.advertiserName.isEnabled()){
			fail("Advertiser Name in Create Deal is not disabled.");
		}

		//DSP Seat Passthrough String
//		System.out.println("DSP Seat Passthrough String's enabled >>> " + dealsPage.dSPSeatPassthroughString.isEnabled());
		if( dealsPage.dSPSeatPassthroughString.isEnabled()){
			fail("DSP Seat Passthrough String in Create Deal is not disabled.");
		}

		//DSP Domain Advertiser Passthrough String
//		System.out.println("DSP Domain Advertiser Passthrough String's enabled >>> " + dealsPage.dSPDomainAdvertiserPassthroughString.isEnabled());
		if( dealsPage.dSPDomainAdvertiserPassthroughString.isEnabled()){
			fail("DSP Domain Advertiser Passthrough String in Create Deal is not disabled.");
		}

		//Delete button
//		System.out.println("Delete button's enabled >>> " + dealsPage.deleteBtnInBuyerPanel.isEnabled());
		if( dealsPage.deleteBtnInBuyerPanel.isEnabled()){
			fail("Delete button in Create Deal is not disabled.");
		}

		//Save Deal button
//		System.out.println("Save Deal button's enabled >>> " + dealsPage.saveButton.isEnabled());
		if( dealsPage.saveButton.isEnabled()){
			fail("Save Deal button in Create Deal is not disabled.");
		}

	}

	@When("^Enter the following values for Single Publisher user$")
	public void enter_the_following_value_for_Single_Publisher_user(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);

		for (Map<String, String> stringMap : list) {
			String privAucName = stringMap.get("PrivateAuction");
			String dSPvalue = stringMap.get("DSPValue");
			String dealName = stringMap.get("EntDealName") + rxUTL.getRandomNumberFourDigit();
			String dealValue = stringMap.get("Values");
			wait.until(ExpectedConditions.elementToBeClickable(dealsPage.dealName));
			dealsPage.selectPrivateAuctionByName(privAucName);
			dealsPage.selectDSPByName(dSPvalue);
			enteredDSP = dealsPage.dspValueDiv.getText();
			dealsPage.enterDealName(dealName);
			dealsPage.enterValue(dealValue);
		}
	}

	@When("^Click on any deal name that Currency is not \"([^\"]*)\" in Deals list$")
	public void click_on_any_deal_name_in_Deals_list(String currency) {
		int rownum = 0;
		for(int i = 0; i < dealsPage.currencyColumnList.size(); i++){
//			System.out.println("dealsPage.currencyColumnList.get(i).getText() >>> " + dealsPage.currencyColumnList.get(i).getText().trim());
			if(!dealsPage.currencyColumnList.get(i).getText().trim().equals(currency)){
				rownum = i+1;
				break;
			}
		}
		dealsPage.getDealNameColumnLink(rownum).click();
		wait.until(ExpectedConditions.elementToBeClickable(dealsPage.dealName));
	}

	@When("^Select \"([^\"]*)\" from Currency$")
	public void select_value_from_Currency(String arg0) throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(dealsPage.addMoreSeats));
		dealsPage.currencyValue.click();
		dealsPage.getCurrencyDropdownValue(arg0).click();
	}

	@Then("^Verify the following message is displayed when the Currency changed for deal$")
	public void verify_the_following_message_is_displayed_when_the_Currency_changed_for_deal(DataTable dt) throws Throwable {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (Map<String, String> stringMap : list) {
			String expectedMessage = stringMap.get("Message");
//			System.out.println("the actual result of Currency Banner Message >>> " + dealsPage.getChangeCurrencyBannerMsg());
			Assert.assertEquals(dealsPage.getChangeCurrencyBannerMsg(), expectedMessage);
		}
	}

	@Then("^Verify the validation errors display$")
	public void verify_the_validation_errors_display(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(dealsPage.checkIfErrorIsDisplayed(e.getValue())));
	}

	@Then("^Verify the validation error does not display$")
	public void verify_the_validation_error_does_not_display(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertFalse(dealsPage.checkIfErrorIsDisplayed(e.getValue())));
	}

	@Then("^The Currency field is not null$")
	public void verify_Currency_field_is_not_null() {
		wait.until(ExpectedConditions.elementToBeClickable(dealsPage.addMoreSeats));
		Assert.assertNotNull(dealsPage.currencyValue.getText());
	}

	@When("^Enter \"([^\"]*)\" into Name$")
	public void enter_value_into_Name(String arg0) throws Throwable {
		String dealName = arg0 + rxUTL.getRandomNumberFourDigit();
		dealsPage.enterDealName(dealName);
	}

	@When("^Enter \"([^\"]*)\" into Floor Price$")
	public void enter_value_into_Floor_Price(String arg0) throws Throwable {
		dealsPage.enterValue(arg0);
	}

	@Then("^Verify no validation errors display$")
	public void verify_no_validation_errors_display() {
        js.executeScript("arguments[0].scrollIntoView();",dealsPage.saveButton);
		Assert.assertFalse(dealsPage.isElementPresent(dealsPage.validationErrorsCssPath));
	}

	@Then("^Verify only one error message displays below Date Range input$")
	public void verify_only_one_error_message_displays_below_Date_Range_input(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dateRange);
		wait.until(ExpectedConditions.visibilityOf(dealsPage.dateRangeErrorMsg));
		String msg = dealsPage.dateRangeErrorMsg.getText().trim();
//		System.out.println("dealsPage.dateRangeErrorMsg.getText().trim() >>> " + msg);
		getDataFromTable(dt).forEach(e ->
				Assert.assertEquals(msg,e.getValue()));
	}

	@Then("^Verify the change Publisher banner message is not displayed$")
	public void verify_the_change_Publisher_banner_message_is_not_displayed(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.publisherNameInput);
		Assert.assertFalse(dealsPage.changePublisherBannerMsg.isDisplayed());
	}

	@When("^Hover over on DSP info icon$")
	public void hover_over_on_DSP_info_icon() {
		dealsPage.hoverOverOnDSPInfoICon();
	}

	@Then("^Verify the DSP information displays$")
	public void verify_the_DSP_information_displays(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (Map<String, String> stringMap : list) {
			String expectedMessage = stringMap.get("Message");
//			System.out.println("The actual result of the DSP info Message >>> " + dealsPage.dspInfo.getText().trim());
			Assert.assertEquals(dealsPage.dspInfo.getText().trim(), expectedMessage);
		}
	}

	@Then("^Verify the error message displays below Floor Price input$")
	public void verifyTheErrorMessageDisplaysBelowFloorPriceInput(DataTable dt) {
		wait.until(ExpectedConditions.visibilityOf(dealsPage.floorPriceErrorMsg));
		String msg = dealsPage.floorPriceErrorMsg.getText().trim();
//		System.out.println("dealsPage.floorPriceErrorMsg.getText().trim() >>> " + msg);
		getDataFromTable(dt).forEach(e ->
				Assert.assertEquals(msg,e.getValue()));
	}

	@When("^Click on DSP input$")
	public void clickOnDSPInput() {
		dealsPage.dspDropDown.click();
	}

	@When("^Type \"([^\"]*)\" in DSP input$")
	public void typeInDSPInput(String arg0) {
		Actions action = new Actions(driver);
		action.sendKeys(arg0).perform();
		Assert.assertTrue(dealsPage.highlightedDropdownValue.getText().startsWith(arg0));
	}

	@When("^Select 2nd value that below the highlighted dropdown value$")
	public void select2ndValueThatBelowTheHighlightedDropdownValue() {
		String secondValue = dealsPage.valueBelowHighlightedDropdownValue.getText().trim();
//		System.out.println(" dealsPage.valueBelowHighlightedDropdownValue.getText() >>> " + secondValue);
		dealsPage.valueBelowHighlightedDropdownValue.click();

		String selectedValue = dealsPage.dspValueDiv.getText().trim();
//		System.out.println(" dealsPage.dspValueDiv.getText() >>> " + selectedValue);

		Assert.assertEquals(selectedValue, secondValue);
	}

	@Then("^Verify the following message is not displayed when select the second DSP value$")
	public void verifyTheFollowingMessageIsNotDisplayedWhenSelectTheSecondDSPValue(DataTable dt) {
		Assert.assertFalse(dealsPage.changeDSPBannerMsg.isDisplayed());
	}

    @When("^Select \"([^\"]*)\" \"([^\"]*)\" deal in list view$")
    public void selectDealInListView(String count, String status) {
		int rowNum = 0;
		String dealID = "";
		int loop = Integer.parseInt(count);
		for(int i = 0; i < loop; i++) {
//			System.out.println("select " + status + " deal loop >>> " + loop);
			for(int j = 0; j < dealsPage.statusColumnsInDealsList.size(); j++){
				String value = dealsPage.statusColumnsInDealsList.get(j).getText().trim();
//				System.out.println("status column value >>> " + value);
				if(value.equals(status)){
					rowNum = j + 1;
//					System.out.println(status + " in row number >>> " + rowNum);
					if(!dealsPage.verifyIfCheckboxIsChecked(rowNum)){
						dealsPage.getElementByXpathWithParameter(dealsPage.checkboxByRowNum, String.valueOf(rowNum)).click();
						dealID = dealsPage.getElementByXpathWithParameter(dealsPage.idByRowNumber, String.valueOf(rowNum)).getText().trim();
						if(status.equals("Active")){
//							System.out.println("Store Active deal ID"  + dealID + " to activeDealIDList");
							this.activeDealIDList.add(dealID);
						}else{
//							System.out.println("Store Inactive deal ID"  + dealID +" to inactiveDealIDList");
							this.inactiveDealIDList.add(dealID);
						}
						break;
					}
				}
			}
		}
    }

	@Then("^Verify the following buttons are present in page header$")
	public void verifyTheFollowingButtonsArePresentInPageHeader(DataTable dt) {
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(dealsPage.verifyButtonDisplaysInPageHeader(e.getValue())));
	}

	@When("^Click on \"([^\"]*)\" button in Deals page$")
	public void clickOnButtonInDealsPage(String arg0) {
		switch (arg0){
			case "Edit Deal":
				wait.until(ExpectedConditions.visibilityOf(dealsPage.overviewEditbutton));
				dealsPage.overviewEditbutton.click();
				wait.until(ExpectedConditions.visibilityOf(dealsPage.saveButton));
				break;
			case "Deactivate Deal":
				wait.until(ExpectedConditions.visibilityOf(dealsPage.overviewDisablebutton));
				dealsPage.overviewDisablebutton.click();
				wait.until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
				break;
			case "Activate Deal":
				wait.until(ExpectedConditions.visibilityOf(dealsPage.overviewEnablebutton));
				dealsPage.overviewEnablebutton.click();
				wait.until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
				break;
			case "Deactivate Deals":
				wait.until(ExpectedConditions.visibilityOf(dealsPage.deactivateDealsbutton));
				dealsPage.deactivateDealsbutton.click();
				wait.until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
				break;
			case "Activate Deals":
				wait.until(ExpectedConditions.visibilityOf(dealsPage.activateDealsbutton));
				dealsPage.activateDealsbutton.click();
				wait.until(ExpectedConditions.visibilityOf(dealsPage.createDealButton));
				break;
		}
	}

	@Then("^Verify Edit Deal page displays$")
	public void verifyEditDealPageDisplays() {
		wait.until(ExpectedConditions.visibilityOf(dealsPage.saveButton));
		Assert.assertTrue(dealsPage.pageTitle.getText().contains("Edit Deal"));
	}

	@When("^Close \"([^\"]*)\" Deal page$")
	public void closeEditOrCreateDealPage(String arg0) {
		wait.until(ExpectedConditions.elementToBeClickable(dealsPage.closeBtn));
		dealsPage.closeBtn.click();
	}

	@Then("^Verify the selected \"([^\"]*)\" deal change to \"([^\"]*)\" status in Deals list view$")
	public void verifyTheSelectedDealChangeToStatusInDealsListView(String status, String expectedStatus) {
		if(status.equals("Inactive")){
			for(String id : this.inactiveDealIDList){
//				System.out.println("Deal ID >>> " + id);
				Assert.assertEquals(dealsPage.getElementByXpathWithParameter(dealsPage.statusByID, id).getText().trim(), expectedStatus);
			}
			this.inactiveDealIDList.clear();
		}else{
			for(String id : this.activeDealIDList){
//				System.out.println("Deal ID >>> " + id);
				Assert.assertEquals(dealsPage.getElementByXpathWithParameter(dealsPage.statusByID, id).getText().trim(), expectedStatus);
			}
			this.activeDealIDList.clear();
		}
	}

	@When("^Click on Date Range input$")
	public void clickOnDateRangeInput() {
		wait.until(ExpectedConditions.elementToBeClickable(dealsPage.dateRange));
		dealsPage.dateRange.click();
	}

	@And("^Select current and next date in Date Range picker table$")
	public void selectCurrentAndNextDateInDateRangePickerTable() {
		wait.until(ExpectedConditions.visibilityOf(dealsPage.dateRangePickerTable));
		dealsPage.dateRangeHighlightDate.click();
		dealsPage.dateRangeNextToHighlightDate.click();
	}

	@Then("^Verify the default start date in Date Range should be today in Create Deal page$")
	public void verifyTheDefaultStartDateInDateRangeShouldBeTodayInCreateDealPage() {
		String dateRangeValue = dealsPage.dateRange.getAttribute("value");
//		System.out.println("dateRangeValue >>> " + dateRangeValue);
		startDate = dateRangeValue.split(" ")[0].trim();
//		System.out.println("startDate in Date Range >>> " + startDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = LocalDate.now(ZoneId.of("GMT")).format(formatter);
//		System.out.println("today >>> " + today);
		Assert.assertEquals(startDate, today);
	}

	@Then("^Verify end date should be 90 days in the future in Create Deal page$")
	public void verifyEndDateShouldBe90DaysInTheFutureInCreateDealPage() throws ParseException {
		String dateRangeValue = dealsPage.dateRange.getAttribute("value");
//		System.out.println("dateRangeValue >>> " + dateRangeValue);
		endDate = dateRangeValue.split(" ")[2].trim();
//		System.out.println("endDate in Date Range >>> " + endDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Assert.assertEquals(ChronoUnit.DAYS.between(start, end), 90);
	}
}
