package stepDefinitions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.ProfilePage;
import RXPages.PublisherListPage;
import RXPages.RXLoginPage;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PublisherStepDefinitions extends RXBaseClass  {
	
//	ProfilePage proPage;
	RXUtile  utl;
	RXLoginPage logain;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(ProfileStepDefinition.class);
	 static int rn ;
	 static String emailID;
	 static String webSiteNa;
	 static String pubNme;
	 static String saleAcc;
	 static int pId;
	 static ArrayList<WebElement> publist;
	public PublisherStepDefinitions() {
		super();
		
		logain = new RXLoginPage();	
//		proPage = new ProfilePage();
		utl =new RXUtile();
		// driverInitialize();
		pubListPgs = new PublisherListPage();
		
	}
	
	
	/*
	 * @When("^Check for column$") public void check_for_column() throws Throwable {
	 * pubListPgs.clickPublisherSortDesc();
	 * pubListPgs.clickCreateOrEditPublisherBtn("Create");
	 * pubListPgs.createEditIndividualPublisher("ascasc", "adcasd@dc.com",
	 * "adcsadc", "dsvsdv", "India", "Dollars", "UTC");
	 * Assert.assertEquals(pubListPgs.clickSaveOrUpdate(), "UPDATED!"); }
	 */

	
	
	@When("^Check for option publisher ,seats and users\\.$")
	public void check_for_option_publisher_seats_and_users() throws Throwable {
		if(!pubListPgs.isPublisherDisplayed())
		{
		pubListPgs.clickAccount();
		}
	}

	@Then("^Publisher ,Seats and user options are displayed under Account\\.$")
	public void publisher_Seats_and_user_options_are_displayed_under_Account() throws Throwable {
		log.info("Publisher displayed under account is "+ pubListPgs.isPublisherDisplayed());
		Assert.assertTrue(pubListPgs.isPublisherDisplayed());
		log.info("Publisher displayed under account is "+ pubListPgs.isSeatsDisplayed());
		Assert.assertTrue(pubListPgs.isSeatsDisplayed());
		log.info("Publisher displayed under account is "+ pubListPgs.isUserDisplayed());
		Assert.assertTrue(pubListPgs.isUserDisplayed());
		
	}

	
	@When("^Click on publisher option under account\\.$")
	public void click_on_publisher_option_under_account() throws Throwable {
		if(!pubListPgs.isPublisherDisplayed())
		{
		pubListPgs.clickAccount();
		}
		pubListPgs.clickPublisherOption();
	}

	@Then("^Publisher page should display\\.$")
	public void publisher_page_should_display() throws Throwable {
		log.info("Publisher page displayed header is : "+ pubListPgs.getHeaderOfpublisherPageDispl());
		Assert.assertEquals(pubListPgs.getHeaderOfpublisherPageDispl(),pubListPgs.publisherHeader);
	}
	
	
	@When("^Click on create publisher and enter form for individual publisher\\.$")
	public void click_on_create_publisher_and_enter_form_for_individual_publisher() throws Throwable {
		rn = RXUtile.getRandomNumberFourDigit();
		emailID=utl.getEmailString();
		pubListPgs.clickCreateOrEditPublisherBtn("Create");
		pubListPgs.createEditIndividualPublisher("Create", pubListPgs.pubName+rn,emailID,pubListPgs.salesAcc+rn, pubListPgs.pubAdd+rn, pubListPgs.pubCntry, pubListPgs.pubCurrency, pubListPgs.pubzone);
		
	}

	@Then("^Individual publisher is created successfully \\.$")
	public void individual_publisher_is_created_successfully() throws Throwable {
		Assert.assertEquals(pubListPgs.clickSaveOrUpdate(), "UPDATED!");
		log.info("Individual Created Successfully ");
		pubListPgs.closeTheDynamicForm();
		pubListPgs.clickCreateOrEditPublisherBtn("Edit");
		Assert.assertEquals(pubListPgs.publisherName.getAttribute("value"),pubListPgs.pubName+rn);
		log.info("Individual Publisher name asserted "+ pubListPgs.publisherName.getAttribute("value"));
		Assert.assertEquals(pubListPgs.publisherEmail.getAttribute("value"),emailID);
		log.info("Individual Email ID asserted :"+ pubListPgs.publisherEmail.getAttribute("value"));
		Assert.assertEquals(pubListPgs.salesAccountName.getAttribute("value"),pubListPgs.salesAcc+rn);
		log.info("Individual Saled account name asserted :"+ pubListPgs.salesAccountName.getAttribute("value"));
		Assert.assertEquals(pubListPgs.addressEntry.getAttribute("value"),pubListPgs.pubAdd+rn);
		log.info("Individual Address asserted :"+ pubListPgs.addressEntry.getAttribute("value"));
		Assert.assertFalse(pubListPgs.enterCountry.isEnabled());
		log.info("Individual Country is disabled :"+ !pubListPgs.enterCountry.isEnabled());
		Assert.assertFalse(pubListPgs.enterCurrency.isEnabled());
		log.info("Individual Currency is disabled :"+ !pubListPgs.enterCurrency.isEnabled());
		Assert.assertFalse(pubListPgs.enterTimeZone.isEnabled());
		log.info("Individual Time Zone is disabled :"+ !pubListPgs.enterTimeZone.isEnabled());
		pubListPgs.closeTheDynamicForm();
		
		
	}

	@Then("^Individual publisher is reflects publisher list \\.$")
	public void individual_publisher_is_reflects_publisher_list() throws Throwable {
		Assert.assertEquals(pubListPgs.publisherInList.getText(),pubListPgs.pubName+rn);
		log.info("Individual Publisher name asserted in the list :"+ pubListPgs.publisherInList.getText());
		Assert.assertEquals(pubListPgs.salesAccountList.getText(),pubListPgs.salesAcc+rn);
		log.info("Individual Sales account name asserted in the list :"+ pubListPgs.salesAccountList.getText());
		Assert.assertEquals(pubListPgs.emailList.getText(),emailID);
		log.info("Individual Email asserted in the list :"+ pubListPgs.emailList.getText());
	   
	}
	
	
	@When("^Click on create publisher and enter form for corporation publisher\\.$")
	public void click_on_create_publisher_and_enter_form_for_corporation_publisher() throws Throwable {
		rn = RXUtile.getRandomNumberFourDigit();
		emailID=utl.getEmailString();
		webSiteNa="www."+pubListPgs.pubWebSite+rn+".com";
		pubListPgs.clickCreateOrEditPublisherBtn("Create");
		pubListPgs.createEditCorporationPublisher("Create",pubListPgs.pubName+rn,emailID,pubListPgs.salesAcc+rn,pubListPgs.pubCorpName+rn,webSiteNa, pubListPgs.pubAdd+rn, pubListPgs.pubCntry, pubListPgs.pubCurrency, pubListPgs.pubzone);
		
	}

	@Then("^corporation publisher is created successfully \\.$")
	public void corporation_publisher_is_created_successfully() throws Throwable {
		Assert.assertEquals(pubListPgs.clickSaveOrUpdate(), "UPDATED!");
		log.info("Corporation Created Successfully ");
		pubListPgs.closeTheDynamicForm();
		pubListPgs.clickCreateOrEditPublisherBtn("Edit");
		Assert.assertEquals(pubListPgs.publisherName.getAttribute("value"),pubListPgs.pubName+rn);
		log.info("Corporation Publisher name asserted "+ pubListPgs.publisherName.getAttribute("value"));
		Assert.assertEquals(pubListPgs.publisherEmail.getAttribute("value"),emailID);
		log.info("Corporation Email ID asserted :"+ pubListPgs.publisherEmail.getAttribute("value"));
		Assert.assertEquals(pubListPgs.salesAccountName.getAttribute("value"),pubListPgs.salesAcc+rn);
		log.info("Corporation Saled account name asserted :"+ pubListPgs.salesAccountName.getAttribute("value"));
		
		Assert.assertEquals(pubListPgs.corpName.getAttribute("value"),pubListPgs.pubCorpName+rn);
		log.info("Corporation Corporation name asserted :"+ pubListPgs.corpName.getAttribute("value"));
		
		Assert.assertEquals(pubListPgs.corpWebSite.getAttribute("value"),webSiteNa);
		log.info("Corporation Corporation web site :"+ pubListPgs.corpWebSite.getAttribute("value"));
		
		
		Assert.assertEquals(pubListPgs.enterCountry.getAttribute("value"),pubListPgs.pubAdd+rn);
		log.info("Corporation address asserted :"+ pubListPgs.enterCountry.getAttribute("value"));
		
		Assert.assertFalse(!pubListPgs.corpCountry.isDisplayed());
		log.info("Corporation Country is disabled :"+ !pubListPgs.corpCountry.isDisplayed());
		Assert.assertFalse(!pubListPgs.corpCurrency.isDisplayed());
		log.info("Corporation Currency is disabled :"+ !pubListPgs.corpCurrency.isDisplayed());
		Assert.assertFalse(!pubListPgs.corpTimeZone.isDisplayed());
		log.info("Corporation Time Zone is disabled :"+ !pubListPgs.corpTimeZone.isDisplayed());
		pubListPgs.closeTheDynamicForm();
	}

	@Then("^corporation publisher is reflects publisher list \\.$")
	public void corporation_publisher_is_reflects_publisher_list() throws Throwable {
		Assert.assertEquals(pubListPgs.publisherInList.getText(),pubListPgs.pubName+rn);
		log.info("Corporation Publisher name asserted in the list :"+ pubListPgs.publisherInList.getText());
		Assert.assertEquals(pubListPgs.salesAccountList.getText(),pubListPgs.salesAcc+rn);
		log.info("Corporation Sales account name asserted in the list :"+ pubListPgs.salesAccountList.getText());
		Assert.assertEquals(pubListPgs.emailList.getText(),emailID);
		log.info("Corporation Email asserted in the list :"+ pubListPgs.emailList.getText());
	}


	//Scenario:  Verify if user is able to edit the publisher with admin role.
	
		@When("^Edit the publisher by selecting the existing publisher \\.$")
		public void edit_the_publisher_by_selecting_the_publisher() throws Throwable {
			rn = RXUtile.getRandomNumberFourDigit();
			emailID=utl.getEmailString();
			pubListPgs.clickCreateOrEditPublisherBtn("Edit");
			pubListPgs.deleteTextField();
			pubListPgs.createEditIndividualPublisher("Edit", pubListPgs.pubName+rn,emailID,pubListPgs.salesAcc+rn, pubListPgs.pubAdd+rn, pubListPgs.pubCntry, pubListPgs.pubCurrency, pubListPgs.pubzone);
			
		}

		@Then("^publisher is edited successfully \\.$")
		public void publisher_is_edited_successfully() throws Throwable {
			Assert.assertEquals(pubListPgs.clickSaveOrUpdate(), "UPDATED!");
			log.info("Individual Created Successfully ");
			pubListPgs.closeTheDynamicForm();
			pubListPgs.clickCreateOrEditPublisherBtn("");
			Assert.assertEquals(pubListPgs.publisherName.getAttribute("value"),pubListPgs.pubName+rn);
			log.info("Individual Publisher name asserted "+ pubListPgs.publisherName.getAttribute("value"));
			Assert.assertEquals(pubListPgs.publisherEmail.getAttribute("value"),emailID);
			log.info("Individual Email ID asserted :"+ pubListPgs.publisherEmail.getAttribute("value"));
			Assert.assertEquals(pubListPgs.salesAccountName.getAttribute("value"),pubListPgs.salesAcc+rn);
			log.info("Individual Saled account name asserted :"+ pubListPgs.salesAccountName.getAttribute("value"));
			Assert.assertEquals(pubListPgs.addressEntry.getAttribute("value"),pubListPgs.pubAdd+rn);
			log.info("Individual Address asserted :"+ pubListPgs.addressEntry.getAttribute("value"));
			Assert.assertFalse(pubListPgs.enterCountry.isEnabled());
			log.info("Individual Country is disabled :"+ !pubListPgs.enterCountry.isEnabled());
			Assert.assertFalse(pubListPgs.enterCurrency.isEnabled());
			log.info("Individual Currency is disabled :"+ !pubListPgs.enterCurrency.isEnabled());
			Assert.assertFalse(pubListPgs.enterTimeZone.isEnabled());
			log.info("Individual Time Zone is disabled :"+ !pubListPgs.enterTimeZone.isEnabled());
			pubListPgs.closeTheDynamicForm();
		}

		@Then("^Edit details are reflecting in publisher list \\.$")
		public void edit_details_are_reflecting_in_publisher_list() throws Throwable {
			Assert.assertEquals(pubListPgs.publisherInList.getText(),pubListPgs.pubName+rn);
			log.info("Individual Publisher name asserted in the list :"+ pubListPgs.publisherInList.getText());
			Assert.assertEquals(pubListPgs.salesAccountList.getText(),pubListPgs.salesAcc+rn);
			log.info("Individual Sales account name asserted in the list :"+ pubListPgs.salesAccountList.getText());
			Assert.assertEquals(pubListPgs.emailList.getText(),emailID);
			log.info("Individual Email asserted in the list :"+ pubListPgs.emailList.getText());
		}
		
	
// Disable publisher from enabled publisher.

@When("^Click in disable select publisher for disable publisher$")
public void click_in_disable_select_publisher_for_disable_publisher() throws Throwable {
	pubListPgs.clickOnTab("Active");
	pubNme=pubListPgs.publisherInList.getText();
	saleAcc=pubListPgs.salesAccountList.getText();
	emailID=pubListPgs.emailList.getText();
	pId=Integer.parseInt(pubListPgs.publisherIdList.getText());
	System.out.println("Publisher ID Assigned :"+pId);
	pubListPgs.clickbutton("Disabled");
}

@Then("^Disabled publisher is displayed in disabled tab \\.$")
public void disabled_publisher_is_displayed_in_disabled_tab() throws Throwable {
	pubListPgs.clickOnTab("Disabled");  
	publist=pubListPgs.getParticularRowData(pId);
	Assert.assertEquals(publist.get(1).getText(),pubNme);
	log.info("Corporation Publisher name asserted in the list :"+ publist.get(0).getText());
	Assert.assertEquals(publist.get(2).getText(),saleAcc);
	log.info("Corporation Sales account name asserted in the list :"+ publist.get(2).getText());
	Assert.assertEquals(publist.get(3).getText(),emailID);
	log.info("Corporation Email asserted in the list :"+ publist.get(3).getText());
	
	
}
//==============================================================================================

//Enable publisher from disabled publisher.

@When("^Click on Enable by selecting publisher from disabled$")
public void click_on_Enable_by_selecting_publisher() throws Throwable {
	pubListPgs.clickOnTab("Disabled");
	pubNme=pubListPgs.publisherInList.getText();
	saleAcc=pubListPgs.salesAccountList.getText();
	emailID=pubListPgs.emailList.getText();
	pId=Integer.parseInt(pubListPgs.publisherIdList.getText());
	System.out.println("Publisher ID Assigned :"+pId);
	pubListPgs.clickbutton("Enabled");;
}

@Then("^Enabled publisher is displayed in Active tab \\.$")
public void enabled_publisher_is_displayed_in_Active_tab() throws Throwable {
	pubListPgs.clickOnTab("Active"); 
	publist=pubListPgs.getParticularRowData(pId);
	Assert.assertEquals(publist.get(1).getText(),pubNme);
	log.info("Corporation Publisher name asserted in the list :"+ publist.get(0).getText());
	Assert.assertEquals(publist.get(2).getText(),saleAcc);
	log.info("Corporation Sales account name asserted in the list :"+ publist.get(2).getText());
	Assert.assertEquals(publist.get(3).getText(),emailID);
	log.info("Corporation Email asserted in the list :"+ publist.get(3).getText());
}
	
//===================================================================================================================
//Mandatory error message are thrown when fields are not entered properly.

@When("^Click on create publisher and don't fill the field values\\.$")
public void click_on_create_publisher_and_don_t_fill_the_field_values() throws Throwable {
	pubListPgs.clickCreateOrEditPublisherBtn("Create");
	pubListPgs.createEditCorporationPublisher("Create","","","","", "","","", "","");
	
	
}

@Then("^use should be displayed with field in-line mandatory error message\\.$")
public void use_should_be_displayed_with_field_in_line_mandatory_error_message() throws Throwable {
	Assert.assertEquals(pubListPgs.clickSaveOrUpdate(), "SAVE PUBLISHER");
	log.info("Clicked on save publisher");
	Assert.assertEquals(pubListPgs.pubNameMandMsg.getText(),pubListPgs.pubNmErMsg);
	log.info("Mandatory field Publisher name asserted "+ pubListPgs.pubNameMandMsg.getText());
	Assert.assertEquals(pubListPgs.emailMandMsg.getText(),pubListPgs.eMailErMsg);
	log.info("Mandatory field Email ID asserted :"+ pubListPgs.emailMandMsg.getText());
	Assert.assertEquals(pubListPgs.salesAccMandMsg.getText(),pubListPgs.salesAccErMsg);
	log.info("Mandatory field Saled account name asserted :"+ pubListPgs.salesAccMandMsg.getText());
	
	Assert.assertEquals(pubListPgs.corpNameMandMsg.getText(),pubListPgs.corpNameErMsg);
	log.info("Mandatory field Corporation name asserted :"+ pubListPgs.corpNameMandMsg.getText());
	Assert.assertEquals(pubListPgs.corpWebMandMsg.getText(),pubListPgs.corpWebErMsg);
	log.info("Mandatory field Corporation web site asserted :"+ pubListPgs.corpWebMandMsg.getText());
	
	
	Assert.assertEquals(pubListPgs.addressMandMsg.getText(),pubListPgs.addressErMsg);
	log.info("Mandatory field Address asserted :"+ pubListPgs.addressMandMsg.getText());
	Assert.assertEquals(pubListPgs.countryMandMsg.getText(),pubListPgs.countryErMsg);
	log.info("Mandatory field Country is asserted :"+ pubListPgs.countryMandMsg.getText());
	Assert.assertEquals(pubListPgs.currencyMandMsg.getText(),pubListPgs.currencyErMsg);
	log.info("Mandatory field Currency is asserted :"+ pubListPgs.currencyMandMsg.getText());
	Assert.assertEquals(pubListPgs.timeZoneMandMsg.getText(),pubListPgs.timeZoneErMsg);
	log.info("Mandatory field Time Zone is asserted :"+ pubListPgs.timeZoneMandMsg.getText());
}

//==========================================================================================================
//Verify if user is able to search enabled publisher in the active tab with admin role.

@When("^Goto Active tab and search for the publisher\\.$")
public void enter_the_search_string_in_search_field() throws Throwable {
	pubListPgs.clickOnTab("Active");
	pubListPgs.publisherNameSearch(pubListPgs.getPublisherNameForSearch());
	
}
@Then("^Searched result display the publisher\\.$")
public void searched_result_display_the_publisher() throws Throwable {
	Assert.assertEquals(pubListPgs.publisherInList.getText(),pubListPgs.getPublisherNameForSearch());
	log.info("Search publisher name asserted in the list :"+ pubListPgs.publisherInList.getText());
	
}


//==========================================================================================================
// Verify if user is able to search disabled publisher in the disabled tab with admin role.

@When("^Goto Disabled tab and search for the publisher\\.$")
public void goto_Disabled_tab_and_search_for_the_publisher() throws Throwable {
	pubListPgs.clickOnTab("Disabled");
	System.out.println("From Step definition :"+pubListPgs.getPublisherNameForSearch());
	pubListPgs.publisherNameSearch(pubListPgs.getPublisherNameForSearch());
	
}


}


