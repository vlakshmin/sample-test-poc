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
import RXPages.PublisherListPage;
import RXPages.RXMediaPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MediaPageStepsDefinition extends RXBaseClass {

	RXMediaPage mediaPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(MediaPageStepsDefinition.class);

	public MediaPageStepsDefinition() {
		super();
		mediaPage = new RXMediaPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();

	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Media option under Inventory$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandInventory();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.mediaUndrInventory));
		navOptions.mediaUndrInventory.click();

	}

	@When("^Click on Media sub menu$")
	public void check_for_Media() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(navOptions.mediaUndrInventory));
		navOptions.mediaUndrInventory.click();

	}

	@Then("^User displayed with media page$")
	public void user_displayed_with_seats_page() throws Throwable {
		Assert.assertEquals(mediaPage.getPageHeading(), mediaPage.mediaHeaderStr);
		log.info("Targeting Page Header is asserted  and it is : " + mediaPage.getPageHeading());

	}
//=========================================================================================================

	@When("^\"(.*)\" a media from the media overview page$")
	public void verifyHEnableDiableMedia(String action) throws InterruptedException {

		driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
		List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Status");
		String status = coulmnData.get(0).getText();
		if (action.equalsIgnoreCase("Enable") && status.equals("Inactive")
				|| action.equalsIgnoreCase("Disable") && status.equals("Active")) {

			switch (status) {
			case "Active":
				Assert.assertTrue(mediaPage.overviewEditbutton.isDisplayed());
				Assert.assertTrue(mediaPage.overviewDisablebutton.isDisplayed());
				mediaPage.clickOverViewDisablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Status");
				Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
				break;
			case "Inactive":
				Assert.assertTrue(mediaPage.overviewEditbutton.isDisplayed());
				Assert.assertTrue(mediaPage.overviewEnablebutton.isDisplayed());
				mediaPage.clickOverViewEnablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Status");
				Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

}
