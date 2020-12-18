package stepDefinitions;


import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;
import RXPages.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

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
	WebDriverWait wait = new WebDriverWait(driver, 1000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public String enteredPublisher;
	public String enteredDSP;
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Deals option under Sales$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandSales();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.dealsLabel));
		navOptions.dealsLabel.click();

	}

	@When("^Click on Deals sub menu$")
	public void check_for_Adspot() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
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
		public void verifyHEnableDiableAdspot() throws InterruptedException {
			for (int i = 0; i <= 1; i++) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
				List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active");
				String status = coulmnData.get(0).getText();
				switch (status) {
				case "Active":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewDisablebutton.isDisplayed());
					dealsPage.clickOverViewDisablebutton();
					Thread.sleep(3000);
					List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active");
					Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
					break;
				case "Inactive":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewEnablebutton.isDisplayed());
					String enableText = dealsPage.overviewEnablebutton.getText().replaceAll("\\s", "");
					Assert.assertEquals(enableText, "ACTIVATEDEAL");
					dealsPage.clickOverViewEnablebutton();
					Thread.sleep(3000);
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
			List<String> columnsNamePresent = new ArrayList<String>();
			List<WebElement> numberOFHeaders = navOptions.tableHeadersList;
			for (int i = 0; i < numberOFHeaders.size(); i++) {
				columnsNamePresent.add(numberOFHeaders.get(i).getText());
			}
			columnsNamePresent.set(6, "Value");
			columnsNamePresent.set(7, "Currency");
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String adspotName = list.get(i).get("ColumnName");
				System.out.println(columnsNamePresent);
				System.out.println(adspotName);
				Assert.assertTrue(columnsNamePresent.contains(adspotName));


			}
			
			
			
		}

	@When("^Click create a new deal$")
	public void clickCreateDealButton() {
		dealsPage.clickCreateDealButton();
	}

	@Then("^Create deal menu is opened$")
	public void isCreateDealMenuOpened() {
		Assert.assertTrue(dealsPage.isCreateDealMenuOpened());
	}

	@When("^Click on publisher input$")
	public void clickOnPublisherInput() {
		dealsPage.expandPublisherNameList();
	}

	@And("^Select publisher by name: \"(.*)\"$")
	public void selectPublisherByName(String name) throws Throwable {
		 dealsPage.selectPublisherByName(name); 
		
	}

	
	@Then("^Select privateAuction by name: \"([^\"]*)\"$")
	public void select_privateAuction_by_name(String name) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		dealsPage.selectPrivateAuctionByName(name);
	}
	
	@Then("^Select DSP by name: \"([^\"]*)\"$")
	public void select_DSP_by_name(String name) throws Throwable {
		dealsPage.selectDSPByName(name);
	}
	
	@Then("^enter the following values$")
	public void enter_the_following_values(DataTable dt) throws Throwable {
List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		
		for (int i = 0; i < list.size(); i++) 
		{
			
			String publName = list.get(i).get("publisher");
			String privAucName = list.get(i).get("PrivateAuction");
			String dSPvalue = list.get(i).get("DSPValue");
			String dealName = list.get(i).get("EntDealName")+ rxUTL.getRandomNumberFourDigit();
			String dealValue = list.get(i).get("Values");
			dealsPage.selectPublisherByName(publName);
			enteredPublisher=dealsPage.publisherNamesEntered.getText();
			wait.until(ExpectedConditions.elementToBeClickable(dealsPage.dealName));
			dealsPage.selectPrivateAuctionByName(privAucName);
			dealsPage.selectDSPByName(dSPvalue);
			enteredDSP=dealsPage.dspValue.getText();
			dealsPage.enterDealName(dealName);
			dealsPage.enterValue(dealValue);
			

		}
	    
	}

	@Then("^enter the following DSP buyer details\\.$")
	public void enter_the_following_DSP_buyer_details(DataTable dt) throws Throwable {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		
		for (int i = 0; i < list.size(); i++) 
		{
			String dSPSeatID = list.get(i).get("dSPSeatID");
			String dSPSeatName = list.get(i).get("dSPSeatName");
			String AdvertiserId = list.get(i).get("AdvertiserId");
			String advertiserName = list.get(i).get("advertiserName");
			String dSPSeatPassthroughString = list.get(i).get("dSPSeatPassthroughString");
			String dSPDomainAdvertiserPassthroughString = list.get(i).get("dSPDomainAdvertiserPassthroughString");
			String relatedProposal = list.get(i).get("relatedProposal");
			dealsPage.enterDSPValues(dSPSeatID,dSPSeatName,AdvertiserId,advertiserName,dSPSeatPassthroughString,dSPDomainAdvertiserPassthroughString,relatedProposal);		

		}
	}
	@When("^change the publisher name to \"([^\"]*)\"$")
	public void change_the_publisher_name_to(String publName) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.publisherNameInput );	
		dealsPage.expandPublisherNameList();
		dealsPage.selectPublisherByName(publName);
	}
	
	@Then("^\"([^\"]*)\" the DSP buyer$")
	public void the_DSP_buyer(String enableDisable) throws Throwable {
		dealsPage.dSPbuyerEnableDisable(enableDisable);
	}
	
	@Then("^Verify the following message is displayed when the publisher changed for deal$")
	public void verify_the_following_message_is_displayed_when_the_publisher_changed_for_deal(DataTable dt) throws Throwable {
		
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
			System.out.println("Banner Message "+ dealsPage.getChangePublisherBannerMsg());
			Assert.assertEquals(dealsPage.getChangePublisherBannerMsg(), expectedMessage);

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
				System.out.println("Entered Publisher Name :"+ enteredPublisher);
				Assert.assertEquals(dealsPage.publisherNamesEntered.getText(),enteredPublisher);
				break;
			case "Deal Name":
				System.out.println("Entered Deal Name :"+ dealsPage.EntereddealName);
				Assert.assertEquals(dealsPage.dealName.getAttribute("value"), dealsPage.EntereddealName);

				break;
			case "Private Auction":
				System.out.println("Entered Private Auction :"+ dealsPage.enteredPrivateAuct);
				Assert.assertEquals(dealsPage.privateActionFieldValue.getText(), dealsPage.enteredPrivateAuct);

				break;
			case "Date Range":
				System.out.println("Entered enteredDateRange :"+ dealsPage.enteredDateRange);
				Assert.assertEquals(dealsPage.dateRange.getAttribute("value"), dealsPage.enteredDateRange);

				break;
			case "Value":
				System.out.println("Entered enteredValue :"+ dealsPage.enteredValue);
				Assert.assertEquals(dealsPage.value.getAttribute("value"), dealsPage.enteredValue);

				break;
			case "Currency":
				System.out.println("Entered currencyFiledValue :"+ dealsPage.currencyFiledValue);
				Assert.assertEquals(dealsPage.currencyValue.getText(), dealsPage.currencyFiledValue);

				break;
			case "DSP":
				System.out.println("Entered enteredDSPValue :"+ dealsPage.enteredDSPValue);
				Assert.assertEquals(dealsPage.dspValue.getText(), enteredDSP);

				break;
			
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}
	
	@Then("^Verify the following buyers details with the created data of deal$")
	public void verify_the_following_buyers_details_with_the_created_data_of_deal(DataTable dt) throws Throwable {
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.relatedProposal);
	
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i).get("FieldName");

				switch (fieldName) {
				
				case "DSP Seat ID":
					System.out.println("Entered enteredDSPSeatID :"+ dealsPage.enteredDSPSeatID);
					Assert.assertEquals(dealsPage.dSPSeatID.getAttribute("value"),dealsPage.enteredDSPSeatID);
					break;
				case "DSP Seat Name":
					System.out.println("Entered enteredDSPSeatName :"+ dealsPage.enteredDSPSeatName);
					Assert.assertEquals(dealsPage.dSPSeatName.getAttribute("value"), dealsPage.enteredDSPSeatName);

					break;
				case "Advertiser ID":
					System.out.println("Entered enteredAdvertiserId :"+ dealsPage.enteredAdvertiserId);
					Assert.assertEquals(dealsPage.AdvertiserId.getAttribute("value"), dealsPage.enteredAdvertiserId);

					break;
				case "Advertiser Name":
					System.out.println("Entered enteredDateRange :"+ dealsPage.enteredAdvertiserName);
					Assert.assertEquals(dealsPage.advertiserName.getAttribute("value"), dealsPage.enteredAdvertiserName);

					break;
				case "DSP Seat Passthrough String":
					System.out.println("Entered enteredDSPSeatPassthroughString :"+ dealsPage.enteredDSPSeatPassthroughString);
					Assert.assertEquals(dealsPage.dSPSeatPassthroughString.getAttribute("value"), dealsPage.enteredDSPSeatPassthroughString);

					break;
				case "DSP Domain Advertiser Passthrough String":
					System.out.println("Entered currencyFiledValue :"+ dealsPage.enteredDSPDomainAdvertiserPassthroughString);
					Assert.assertEquals(dealsPage.dSPDomainAdvertiserPassthroughString.getAttribute("value"), dealsPage.enteredDSPDomainAdvertiserPassthroughString);

					break;
				case "Related Proposal":
					System.out.println("Entered enteredRelatedProposal :"+ dealsPage.enteredRelatedProposal );
					Assert.assertEquals(dealsPage.relatedProposal.getAttribute("value"), dealsPage.enteredRelatedProposal );

					break;
				
				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

				}
			}
		}
	@Then("^Verify the buyer is \"([^\"]*)\"$")
	public void verify_the_buyer_is(String status) throws Throwable {
		if(status.equalsIgnoreCase("Enabled"))
		{
		Assert.assertTrue(dealsPage.dSPbuyerEnabledOrDisabled());
		}else if(status.equalsIgnoreCase("Disabled"))
		{
			Assert.assertFalse(dealsPage.dSPbuyerEnabledOrDisabled());
		}
	}
	@Then("^Verify the following general values are reset to default values$")
	public void verify_the_following_general_values_are_reset_to_default_values(DataTable dt) throws Throwable {
		
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.dealName);
		wait.until(ExpectedConditions.visibilityOf(dealsPage.dealName));
		dealsPage.dealName.click();
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
			  
			  	Assert.assertEquals(dealsPage.dateRange.getAttribute("value"), " GMT");
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
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.relatedProposal);		
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
			case "Related Proposal":
				Assert.assertEquals(dealsPage.relatedProposal.getAttribute("value"), "");
				break;			
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
		js.executeScript("arguments[0].scrollIntoView();",dealsPage.relatedProposal);		
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
			case "Related Proposal":
				Assert.assertFalse(dealsPage.relatedProposal.isEnabled());
				break;			
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
			System.out.println("Banner Message "+ dealsPage.getChangeDSPBannerMsg());
			Assert.assertEquals(dealsPage.getChangeDSPBannerMsg(), expectedMessage);

		}
	}
	@Then("^Select \"([^\"]*)\" on the DSP change banner displayed for deal$")
	public void select_on_the_DSP_change_banner_displayed_for_deal(String cancelOrAccept) throws Throwable {
	    String cancOrAcce=dealsPage.cancelOrAcceptChangeDSPBannerMsg(cancelOrAccept);
	    if(cancOrAcce.equalsIgnoreCase("Accepted"))
	    {
	    	enteredDSP=dealsPage.dspValue.getText();
	    }
	}
	
	@When("^click on Save deal$")
	public void click_on_Save_deal() throws Throwable {
//		System.out.println("Deal ID :"+ dealsPage.saveDeal());
		Assert.assertEquals(dealsPage.saveDeal(), "UPDATED!");
		
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
}	
