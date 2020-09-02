package stepDefinitions;

import java.util.ArrayList;
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
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdspotsPageStepDefinition extends RXBaseClass    {
	
	RXAdspotsPage adspotsPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(AdspotsPageStepDefinition.class);
	
	public AdspotsPageStepDefinition()
	{
		super();
		adspotsPage = new RXAdspotsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
	//Verify if user is displayed with media list page on clicking media navigation link 

@When("^Click on Adspots option under Inventory$")
public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 navOptions.expandInventory();
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(navOptions.adspotsUndrInventory));
			 navOptions.adspotsUndrInventory.click(); 
		 

	}

@Then("^User displayed with Adspots page$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(adspotsPage.getPageHeading(), adspotsPage.adspotsHeaderStr);
	log.info("Adspots Page Header is asserted  and it is : "+ adspotsPage.getPageHeading() );

}
//=========================================================================================================





}
