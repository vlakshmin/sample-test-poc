package stepDefinitions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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
//	 static int rn ;
	 static String rn ;
	 static String emailID;
	 static String webSiteNa;
	 static String pubNme;
	 static String saleAcc;
	 static int pId;
	 static ArrayList<WebElement> publist = new ArrayList<WebElement>();
	public PublisherStepDefinitions() {
		super();
		
		logain = new RXLoginPage();	
//		proPage = new ProfilePage();
		utl =new RXUtile();
		// driverInitialize();
		pubListPgs = new PublisherListPage();
		
	}
	

	
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
	
	


}


