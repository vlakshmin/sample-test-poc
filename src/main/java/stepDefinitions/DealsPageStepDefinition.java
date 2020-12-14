package stepDefinitions;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.*;

public class DealsPageStepDefinition extends RXBaseClass {


	RXDealsPage dealsPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(DealsPageStepDefinition.class);

	public DealsPageStepDefinition() {
		super();
		dealsPage = new RXDealsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();

	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Deals option under Sales$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandSales();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.dealsLabel));
		navOptions.dealsLabel.click();

	}

	@When("^Click on Deals sub menu$")
	public void check_for_Adspot() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.dealsLabel));
		navOptions.dealsLabel.click();

	}

	@Then("^User displayed with Deals page$")
	public void user_displayed_with_seats_page() throws Throwable {
		Assert.assertEquals(dealsPage.getPageHeading(), dealsPage.dealHeaderStr);
		log.info("Deal Page Header is asserted  and it is : " + dealsPage.getPageHeading());

	}
	
	//Verify enabling abd disabling of an adspot from the overview page

		@When("^Verify enabling and disabling of a deal from the overview page$")
		public void verifyHEnableDiableAdspot() throws InterruptedException {
			for (int i = 0; i <= 1; i++) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
				List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active");
				String status = coulmnData.get(0).getText();
				switch (status) {
				case "Active":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewDisablebutton.isDisplayed());
					dealsPage.clickOverViewDisablebutton();
					Thread.sleep(3000);
					List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active");
					Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
					break;
				case "Inactive":
					Assert.assertTrue(dealsPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(dealsPage.overviewEnablebutton.isDisplayed());
					String enableText = dealsPage.overviewEnablebutton.getText().replaceAll("\\s", "");
					Assert.assertEquals(enableText, "ACTIVATEDEAL");
					dealsPage.clickOverViewEnablebutton();
					Thread.sleep(3000);
					List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active");
					Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
					break;

				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

				}
			}
		}
		@Then("^Verify the deal overview page contains following columns$")
		public void verifyColumnsNameInList(DataTable dt) throws InterruptedException {
			List<String> columnsNamePresent = new ArrayList<String>();
			List<WebElement> numberOFHeaders = navOptions.tableHeadersList;
			for (int i = 0; i < numberOFHeaders.size(); i++) {
				columnsNamePresent.add(numberOFHeaders.get(i).getText());
			}
			columnsNamePresent.set(6, "Value");
			columnsNamePresent.set(7, "Currency");
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				String adspotName = list.get(i).get("ColumnName");
				System.out.println(columnsNamePresent);
				System.out.println(adspotName);
				Assert.assertTrue(columnsNamePresent.contains(adspotName));


			}
			
			
			
		}

	@When("^Click create a new deal$")
	public void clickCreateDealButton() {
		dealsPage.clickCreateDealButton();
	}

	@Then("^Create deal menu is opened$")
	public void isCreateDealMenuOpened() {
		Assert.assertTrue(dealsPage.isCreateDealMenuOpened());
	}

	@When("^Click on publisher input$")
	public void clickOnPublisherInput() {
		dealsPage.expandPublisherNameList();
	}

	@And("^Select publisher by name: \"([^\"]*)\"$")
	public void selectPublisherByName(String name) {
		dealsPage.selectPublisherByName(name);
	}

}			