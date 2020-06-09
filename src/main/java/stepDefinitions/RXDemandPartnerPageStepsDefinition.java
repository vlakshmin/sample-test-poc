package stepDefinitions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.RXDemanPartnerAdminPage;
import RXPages.RXSeatsPage;
import RXPages.RXUsers;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RXDemandPartnerPageStepsDefinition extends RXBaseClass     {
	
	RXSeatsPage seatsPage;
	RXUsers rxUserPage;
	Logger log = Logger.getLogger(RXDemandPartnerPageStepsDefinition.class);
	RXDemanPartnerAdminPage demandPartnerPage;
	
	//Variable for tmp starage
	static String bidderName;
	
	//Explicitly wait
	WebDriverWait wait = new WebDriverWait(driver, 6000);
	
	//Temp variable
	static int dsp_id;
	static String biddername;
	static String bidCountry;
	static String bidCurrency;
	static String bidFormat;
	static String bidPlatform;
	static int bidRqAdjRate;
	static int bidTimeOut;
	static ArrayList<WebElement> dspTableData = new ArrayList<WebElement>();
	
	public RXDemandPartnerPageStepsDefinition()
	{
		super();
		rxUserPage = new RXUsers();
		seatsPage = new RXSeatsPage();
		demandPartnerPage=new RXDemanPartnerAdminPage();
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
	//=========================================================================================================	
	// Verify if user is displayed with disabled publisher in the disabled tab with admin role.

	@When("^Click on Demand Partner option$")
	public void click_on_Demand_Partner() throws Throwable {
		
		demandPartnerPage.clickOnDemanPartnermainOption();
		log.info("Clicked on Demand partner option ");
	}

	@Then("^User is displayed with Demand page$")
	public void user_displayed_with_Demand_page() throws Throwable {
		Assert.assertEquals(demandPartnerPage.getTheDemandPartnerPageHeader(), demandPartnerPage.demandPartnerPageHear);
		log.info("Demand Partner Page Header is asserts  and it is : "+ demandPartnerPage.getTheDemandPartnerPageHeader() );

	}
	//=========================================================================================================	
	//  Verify if user is able to search Active Bidder by name  with admin login.
	
	@Then("^Click on Active tab of demand page$")
	public void click_on_Active_tab_of_demand_page() throws Throwable {
		demandPartnerPage.waitForDemanPageToBeDisplayed(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
		log.info("Click on active tab");
		rxUserPage.clickOnTab("Active");
		log.info("Pick the bidder for search");
		bidderName=rxUserPage.getUserNameForSearch(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
		
		
	}

	@Then("^Search for the Bidder$")
	public void search_for_the_Bidder() throws Throwable {
		log.info("Search the bidder :"+bidderName);
		rxUserPage.seatNameSearch(bidderName);
	}

	@Then("^Assert the bidder details in user page table view$")
	public void assert_the_bidder_details_in_user_page_table_view() throws Throwable {
		try {
			Assert.assertEquals(rxUserPage.getUserNameForSearch(demandPartnerPage.getWebElementOfDemandPartnerPageHeader()),bidderName);
			log.info("Search result of bidder name asserted  :"+ rxUserPage.getUserNameForSearch(demandPartnerPage.getWebElementOfDemandPartnerPageHeader()));
			}
			catch (org.openqa.selenium.NoSuchElementException e) 
			{
				System.out.println("There is no bidder available in the seat page");
				
			
			}
	}

	//=========================================================================================================	
	// Verify if user is able to search Disabled Bidder by name  with admin login
	
	@Then("^Click on disabled tab of demand page$")
	public void Click_on_disabled_tab_of_demand_page() throws Throwable {
		demandPartnerPage.waitForDemanPageToBeDisplayed(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
		log.info("Click on disabled tab");
		rxUserPage.clickOnTab("Disabled");
		log.info("Pick the bidder for search");
		bidderName=rxUserPage.getUserNameForSearch(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
		
		
	}
//================================================================================================================
	
	@Then("^Select one of the Bidder$")
	public void Select_one_of_the_Bidder() throws Throwable {
		demandPartnerPage.waitForDemanPageToBeDisplayed(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
//		log.info("Click on active tab");
//		rxUserPage.clickOnTab("Active");
		dsp_id=demandPartnerPage.selectBidderIdAndReturnId();
		log.info("DSP ID selected is :"+dsp_id);
		dspTableData.clear();
		log.info("Putting rowdata into arraylist ");
		try {
		dspTableData=demandPartnerPage.getParticularRowData(dsp_id);
		biddername=dspTableData.get(1).getText();
		log.info("Bidder name asserted in tab when selected for modify :"+ biddername);
		bidCountry=dspTableData.get(2).getText();
		log.info("Bidder country asserted in tab when selected for modify :"+ bidCountry);
		bidCurrency=dspTableData.get(3).getText();
		log.info("Bidder currency asserted in tab when selected for modify :"+ bidCurrency);
		bidFormat=dspTableData.get(4).getText();
		log.info("Bidder format asserted in tab when selected for modify :"+ bidFormat);
		bidPlatform=dspTableData.get(5).getText();
		log.info("Bidder platform asserted in tab when selected for modify :"+ bidPlatform);
		bidRqAdjRate=Integer.parseInt(dspTableData.get(6).getText());
		log.info("Bidder Request adjustment rate asserted in tab when selected for modify :"+ bidRqAdjRate);
		bidTimeOut=Integer.parseInt(dspTableData.get(7).getText());
		log.info("Bidder timeout asserted in tab when selected for modify :"+ bidTimeOut);
		}catch(WebDriverException e) 
		{
//			e.printStackTrace();
			System.out.println("DSP ID doesn't exist :"+dsp_id);
		}
		


	}

	@Then("^Click on disable dsp button$")
	public void Click_on_disable_dsp_button() throws Throwable {
		log.info("Selected bidder is disabled ");
		demandPartnerPage.clickButtonCreateEditDisable("Disable");
	}

	@Then("^Assert the bidder details in the table view$")
	public void assert_the_bidder_details_in_the_table_view() throws Throwable {
	
		log.info("Putting rowdata into arraylist ");
		try {
		dspTableData=demandPartnerPage.getParticularRowData(dsp_id);
		}catch(WebDriverException e) 
		{
//			e.printStackTrace();
			System.out.println("DSP ID doesn't exist :"+dsp_id);
		}
		
		Assert.assertEquals(dspTableData.get(1).getText(),biddername);
		log.info("Bidder name asserted in tab after modify :"+ dspTableData.get(1).getText());
		Assert.assertEquals(dspTableData.get(2).getText(),bidCountry);
		log.info("Bidder country asserted in tab after modify :"+ dspTableData.get(2).getText());
		Assert.assertEquals(dspTableData.get(3).getText(),bidCurrency);
		log.info("Bidder currency asserted in tab after modify :"+ dspTableData.get(3).getText());
		Assert.assertEquals(dspTableData.get(4).getText(),bidFormat);
		log.info("Bidder format asserted in tab after modify :"+ dspTableData.get(4).getText());
		Assert.assertEquals(dspTableData.get(5).getText(),bidPlatform);
		log.info("Bidder platform asserted in tab after modify :"+ dspTableData.get(5).getText());
		Assert.assertEquals(Integer.parseInt(dspTableData.get(6).getText()),bidRqAdjRate);
		log.info("Bidder Request adjustment rate asserted in tab after modif :"+ Integer.parseInt(dspTableData.get(6).getText()));
		Assert.assertEquals(Integer.parseInt(dspTableData.get(7).getText()),bidTimeOut);
		log.info("Bidder timeout asserted in tab after modif :"+ Integer.parseInt(dspTableData.get(7).getText()));
		
	}
	
	
	@Then("^Click on active dsp button$")
	public void click_on_active_dsp_button() throws Throwable {
		log.info("Selected bidder is Enable ");
		demandPartnerPage.clickButtonCreateEditDisable("Enable");
	}

	@Then("^Click on active tab of demand page$")
	public void click_on_active_tab_of_demand_page() throws Throwable {
		demandPartnerPage.waitForDemanPageToBeDisplayed(demandPartnerPage.getWebElementOfDemandPartnerPageHeader());
		log.info("Click on active tab");
		rxUserPage.clickOnTab("Active");

	}	

	
}
