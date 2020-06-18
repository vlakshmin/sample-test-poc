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
	targetingPage.clickTaretingNavigation();;
	log.info("Clicked on Targeting option under Rules");
}

@Then("^User displayed with targeting page$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(targetingPage.getPageHeading(), targetingPage.targetingHeaderStr);
	log.info("Targeting Page Header is asserted  and it is : "+ targetingPage.getPageHeading() );

}
//=========================================================================================================



@When("^Verify the pagination of the listed rows in the Page with a selection of (.*) rows per page$")
public void verifyPagination(String noOfRowsPerPage) throws Throwable {
	int finalRowCountPerPage;
	int TotalRowsCount;
	int initialCount ;
	targetingPage.clickNoOfPagesDropDown();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active']"
			+ "//div[@class='v-list-item__title' and text()='"+noOfRowsPerPage+"']")).click();
	while(true) {
	Thread.sleep(2000);
	Assert.assertEquals(targetingPage.targetingTableColumns.size(),3);
	Assert.assertEquals(targetingPage.targetingTableRows.size(), Integer.parseInt(noOfRowsPerPage));
	String paginationTextPattern = targetingPage.getPaginationText();
	String paginationText = paginationTextPattern.replaceAll("\\s", "");
	System.out.println(paginationText);
	TotalRowsCount = Integer.parseInt(paginationText.substring(paginationText.indexOf('f')+1, paginationText.length()));
	finalRowCountPerPage = Integer.parseInt(paginationText.substring(paginationText.indexOf('-')+1, paginationText.indexOf('o')));
	System.out.println(TotalRowsCount);
	System.out.println(finalRowCountPerPage);
	if(finalRowCountPerPage==TotalRowsCount) {
		Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", targetingPage.nextPageNavButton));
		break;
	}
	targetingPage.clickNextPageNavigation();
	}
	while(true) {
		Assert.assertEquals(targetingPage.targetingTableColumns.size(),3);
		Assert.assertEquals(targetingPage.targetingTableRows.size(), Integer.parseInt(noOfRowsPerPage));
		String paginationTextPattern = targetingPage.getPaginationText();
		String paginationText = paginationTextPattern.replaceAll("\\s", "");
		initialCount = Integer.parseInt(paginationText.substring(0,1));
		if(initialCount==1) {
			Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", targetingPage.previousPageNavButton));
			break;
		}
		targetingPage.clickPreviousPageNavigation();
		}
	}

}
