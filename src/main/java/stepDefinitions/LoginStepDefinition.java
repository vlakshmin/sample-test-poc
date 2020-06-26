package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import RXBaseClass.RXBaseClass;
import RXPages.ProfilePage;
import RXPages.PublisherListPage;
import RXPages.RXLoginPage;
import RXUtitities.Configuration;
import RXUtitities.RXUtile;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.StepDefinitionMatch;



public class LoginStepDefinition extends RXBaseClass{
	

	RXLoginPage logain;
	PublisherListPage pubListPgs;
	ProfilePage profilePgs;

//	Logger log = Logger.getLogger(LoginStepDefinition.class);

public LoginStepDefinition() {
	super();
}
@Before
public void setup()
{
	
	driverInitialize();
	logain = new RXLoginPage();
	profilePgs = new ProfilePage();

	
	
}
	

	 
     @Given("^Admin user click on Login by entering valid username and password$")
	 public void admin_user_click_on_Login_by_entering_valid_username_and_password() throws Throwable {
    	 Reporter.addStepLog("The user has logged in "+prop.getProperty("url")+" as admin");
    	 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		 pubListPgs = logain.clickLogin(prop.getProperty("username"), prop.getProperty("password"));
		 
		
	 }
	
   
     
     @Given("^Publisher user login to RX UI with valid username and password$")
     public void publisher_user_login_to_RX_UI_with_valid_username_and_password() throws Throwable {
    	 Reporter.addStepLog("The user has logged in "+prop.getProperty("url")+" as publisher");
    	 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		 pubListPgs = logain.clickLogin(prop.getProperty("pbusername"), prop.getProperty("pbpassword"));
		 
	 }
     
	 @Then("^login should be successful and user is displayed the publisher page with Rakuten Exchange$")
	 public void user_is_displayed_the_publisher_page_with_Rakuten_Exchange() throws InterruptedException{
		 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
//		 Thread.sleep(6000);
//		 boolean istrue = pubListPgs.logodisplayed();
//		 System.out.println(istrue);
		 Assert.assertTrue(pubListPgs.logodisplayed());
	 }


	
	 @Given("^Admin user click on Login by entering \"([^\"]*)\" & \"([^\"]*)\"$")
	 public void admin_user_click_on_Login_by_entering(String username, String password) throws Throwable {
		 logain.clickLogin(username,password);
		 
	 }

	 @Then("^login should not be success$")
	 public void login_should_not_be_success() throws Throwable {
		
		 Assert.assertTrue(logain.loginButton.isDisplayed());
//		 driver.close();
//		 driver.quit();
//		 logger.info("Login Page should be displayed");
		 
	 }

	 
	
	  
	 
	  @After(order = 1)
	  public void tearDown(Scenario scenario) throws Throwable {
		  if (scenario.isFailed() | !scenario.isFailed()) {
			  //uncomment this when running in local
//		    Reporter.addScreenCaptureFromPath(System.getProperty("user.dir")+"/"+RXUtile.takeScreenshotAtEndOfTest());
	        Reporter.addScreenCaptureFromPath(System.getenv("CIRCLE_BUILD_URL")+"/artifacts/0/home/circleci/circleCiTesting/"+RXUtile.takeScreenshotAtEndOfTest());
		  }
		  driver.quit();
	  } 

//	  @After(order = 0)
//	  @Then("^Close the browser$")
//	  public void close_the_browser() throws InterruptedException
//	  { 
//		  
//	  
//	  }

//		  
//		  @After(order = 0)
//		  public void AfterSteps() {
////			  profilePgs.clickProfile();
////			  profilePgs.clickOnLogOut();
//			  driver.close();
//		  }

}
