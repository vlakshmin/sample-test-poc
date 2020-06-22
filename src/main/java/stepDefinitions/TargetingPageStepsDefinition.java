package stepDefinitions;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import RXBaseClass.RXBaseClass;
import RXPages.RXNavOptions;
import RXPages.RXTargetingPage;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TargetingPageStepsDefinition extends RXBaseClass    {
	
	RXTargetingPage targetingPage;
	RXNavOptions navOptions;
	Logger log = Logger.getLogger(TargetingPageStepsDefinition.class);
	
	public TargetingPageStepsDefinition()
	{
		super();
		targetingPage = new RXTargetingPage();
		navOptions = new RXNavOptions();
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
//Verify if user is displayed with targeting list page on clicking targeting navigation link 

@When("^Click on Targeting option under Rules$")
public void click_on_targeting_option_under_Rules() throws Throwable {
	navOptions.expandRules();
	Thread.sleep(2000);
	targetingPage.clickTaretingNavMenu();;
	log.info("Clicked on Targeting option under Rules");
}

@Then("^User displayed with targeting page$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(targetingPage.getPageHeading(), targetingPage.targetingHeaderStr);
	log.info("Targeting Page Header is asserted  and it is : "+ targetingPage.getPageHeading() );

}
//=========================================================================================================





}
