package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.ProfilePage;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXLoginPage;
import RXPages.RXNavOptions;
import RXPages.RXAdspotsPage;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PublisherStepDefinitions extends RXBaseClass  {
	
//	ProfilePage proPage;
	RXUtile  utl;
	RXLoginPage logain;
	PublisherListPage pubListPgs;
	RXNavOptions navOptions;
	RXAdspotsPage adspotsPage;
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
		navOptions = new RXNavOptions();
		adspotsPage = new RXAdspotsPage();
		
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

	
	@When("^Click on publisher option under account$")
	public void click_on_publisher_option_under_account() throws Throwable {
		navOptions.expandAdmin();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.publisherUndrAdmin));
		navOptions.publisherUndrAdmin.click();

	}
	@When("^Click on publisher option from left menu$")
	public void click_on_publisher_optionMenu() throws Throwable {
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.publisherUndrAdmin));
		navOptions.publisherUndrAdmin.click();

	}

	@Then("^Publisher page should be displayed$")
	public void publisher_page_should_display() throws Throwable {
		log.info("Publisher page displayed header is : "+ pubListPgs.getHeaderOfpublisherPageDispl());
		Assert.assertEquals(pubListPgs.getHeaderOfpublisherPageDispl(),pubListPgs.publisherHeader);
	}
	
	@When("^Click on publisher name \"(.*)\" in the publisher overview page$")
	public void clickNameOverview(String name) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {

			String enteredName = name.replaceAll("\\s", "");
			List<WebElement> listOfNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[3]/span/a"));
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");

				if (enteredName.equals(reqName)) {
					listOfNames.get(k).click();
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//aside[@class='dialog']/header//div[text()='" + name + "']"))));
			Thread.sleep(4000);
		} catch (NullPointerException e) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]/span/a")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));

		}
	}

	
	@When("^\"(.*)\" a publisher from the publisher overview page$")
	public void verifyHEnableDiableAdspot(String action) throws InterruptedException {
		
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
			List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active");
			String status = coulmnData.get(0).getText();
			if(action.equalsIgnoreCase("Enable")&& status.equals("Inactive") ||
					action.equalsIgnoreCase("Disable")&& status.equals("Active")) {
				
			
			switch (status) {
			case "Active":
				Assert.assertTrue(pubListPgs.overviewEditbutton.isDisplayed());
				Assert.assertTrue(pubListPgs.overviewDisablebutton.isDisplayed());
				pubListPgs.clickOverViewDisablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active");
				Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
				break;
			case "Inactive":
				Assert.assertTrue(pubListPgs.overviewEditbutton.isDisplayed());
				Assert.assertTrue(pubListPgs.overviewEnablebutton.isDisplayed());
				pubListPgs.clickOverViewEnablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active");
				Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
			}
		}
	
	@When("^Close toast message$")
	public void closeToastMessage() throws InterruptedException {
		
			pubListPgs.clickCloseToastMessageButton();
			
				
		}
	@When("^Close Adspot entity page$")
	public void closeEntityPage() throws InterruptedException {
		
		adspotsPage.adSpotCloseSideDialog.click();
				
		}

}


