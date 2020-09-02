package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	WebDriverWait wait = new WebDriverWait(driver,30);
	
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


@When("^User click on table options button$")
public void clickTableOptions() throws InterruptedException {
	Thread.sleep(10000);
	wait.until(ExpectedConditions.visibilityOf(navOptions.tableOptions));
	navOptions.clickTableOptions();
}
@When("^Verify that column (.*) can be hidden and shown$")
public void verifyHideShow(String filter) throws InterruptedException {
	navOptions.tableOptionsLabel = filter;
	Thread.sleep(5000);
	navOptions.clickHideShowCheckBox();
	Thread.sleep(2000);
	String isChecked = navOptions.checkTableOptionsChkStatus();
	switch(isChecked) {
	  case "false":
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), false);
		     navOptions.clickHideShowCheckBox();
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), true);
			break;
	  case "true":
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), true);
		     navOptions.clickHideShowCheckBox();
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), false);
			break;
	   
	  default:
	    Assert.assertTrue(false, "The status fields supplied does not match with the input");
	
    }
}
@When("^Verify that column (.*) only shows relevant rows in the table with filter (.*)$")
public void verifyShowStats(String column, String filter) throws InterruptedException {
	navOptions.tableOptionsLabel = filter;
	Thread.sleep(5000);
	navOptions.clickHideShowCheckBox();
	Thread.sleep(2000);
	String isChecked = navOptions.checkTableOptionsChkStatus();
	switch(isChecked) {
	  case "true":
		     List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader(column);
		     for(int i=0;i<coulmnData.size();i++) {
		    	 Assert.assertEquals(coulmnData.get(i).getText(), filter);
				}
		    
			break;
	   
	  default:
	    Assert.assertTrue(false, "The status fields supplied does not match with the input");
	
    }

}

}
