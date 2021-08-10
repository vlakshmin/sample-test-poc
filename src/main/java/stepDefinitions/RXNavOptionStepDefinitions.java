package stepDefinitions;

import RXPages.*;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class RXNavOptionStepDefinitions extends RXBasePage {
	
	RXUsers rxUserPage;
	RXNavOptions rxNavOpt;
	PublisherListPage pubListPgs;
	RXMediaPage mediaPage;
	RXAdspotsPage adspotsPage;
	RXPrivateAuctionsPage auctionPage;
	RXDealsPage dealsPage;
	RXTargetingPage targetingPage;
	Logger log = Logger.getLogger(RXNavOptionStepDefinitions.class);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	String valueInSpecifiedColumn;

	public RXNavOptionStepDefinitions()
	{
		super();
		rxUserPage = new RXUsers();
		rxNavOpt = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		mediaPage = new RXMediaPage();
		adspotsPage = new RXAdspotsPage();
		auctionPage = new RXPrivateAuctionsPage();
		dealsPage = new RXDealsPage();
		targetingPage = new RXTargetingPage();
	}
	
	
	@When("^Check for main menu navigation options\\.$")
	public void check_for_main_menu_navigation_options() throws Throwable {
		 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		log.info("User logged in to check the navigation option :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		
	}

	@Then("^User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options\\.$")
	public void user_Info_Dashboard_Admin_Inventory_and_Rules_are_displayed_as_main_menu_navigation_options() throws Throwable {
		log.info("User Info main menu displayed "+ rxNavOpt.isUserInfoNavDisplayed());
		Assert.assertTrue(rxNavOpt.isUserInfoNavDisplayed());
		log.info("Dashboard main menu displayed "+ rxNavOpt.isdashBoardDisplayed());
		Assert.assertTrue(rxNavOpt.isdashBoardDisplayed());	
		log.info("Admin main menu displayed "+ rxNavOpt.isAdminNavDisplayed());
		Assert.assertTrue(rxNavOpt.isAdminNavDisplayed());	
		log.info("Inventory main menu displayed "+ rxNavOpt.isInventoryNavDisplayed());
		Assert.assertTrue(rxNavOpt.isInventoryNavDisplayed());	
		log.info("Rules main menu displayed "+ rxNavOpt.isRuleNavDisplayed());
		Assert.assertTrue(rxNavOpt.isRuleNavDisplayed());	
	}
	
	
	@When("^Check for Sub menu option under Admin main menu\\.$")
	public void check_for_Sub_menu_option_under_Admin_main_menu() throws Throwable {
		 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
			log.info("User logged in to check the navigation option for Sub mennu option under Admin main menu :"+ pubListPgs.logodisplayed());
			 Assert.assertTrue(pubListPgs.logodisplayed());
			 if(!rxNavOpt.ispublisherUndrAdminDisplayed())
				{
				 log.info("Click on Admain main menu expansion ");
				 rxNavOpt.expandAdmin();
				}
	}

	@Then("^Publisher,Users and Demand Sources are displayed as sub main menu under Admin main menu as navigation options\\.$")
	public void publisher_Users_and_Demand_Sources_are_displayed_as_sub_main_menu_under_Admin_main_menu_as_navigation_options() throws Throwable {
		log.info("Publisher sub menu are displaying under Admin main menu : "+ rxNavOpt.ispublisherUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.ispublisherUndrAdminDisplayed());
		log.info("Users sub menu are displaying under Admin main menu : "+ rxNavOpt.isusersUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.isusersUndrAdminDisplayed());
		log.info("Demand Source sub menu are displaying under Admin main menu : "+ rxNavOpt.isdemandSourcesUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.isdemandSourcesUndrAdminDisplayed());
	}
	
	@When("^Check for Sub menu option under Inventory main menu\\.$")
	public void check_for_Sub_menu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub mennu option under Inventory main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 rxNavOpt.expandInventory();

	}

	@Then("^Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options\\.$")
	public void media_and_Adspot_are_displayed_as_sub_main_menu_under_Inventory_main_menu_as_navigation_options() throws Throwable {
		log.info("Media sub menu are displaying under Inventory main menu : "+ rxNavOpt.ismediaUndrInventoryDisplayed());
		Assert.assertTrue(rxNavOpt.ismediaUndrInventoryDisplayed());
		log.info("Adspots sub menu are displaying under Inventory main menu : "+ rxNavOpt.isadspotsUndrInventoryUndrInventoryDisplayed());
		Assert.assertTrue(rxNavOpt.isadspotsUndrInventoryUndrInventoryDisplayed());
	}
	
	
	@When("^Check for Sub menu option under Rules main menu\\.$")
	public void check_for_Sub_menu_option_under_Rules_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub mennu option under Rules main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 rxNavOpt.expandRules();
	}

	@Then("^Targeting is displayed as sub main menu under Rules main menu as navigation options\\.$")
	public void targeting_is_displayed_as_sub_main_menu_under_Rules_main_menu_as_navigation_options() throws Throwable {
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		log.info("Targeting sub menu are displaying under Rules main menu : "+ rxNavOpt.istargetingUndrRulesDisplayed());
		Assert.assertTrue(rxNavOpt.istargetingUndrRulesDisplayed());
	}


	
	@Then("^User Info,Dashboard,Inventory and Rules are displayed as main menu navigation options\\.$")
	public void user_Info_Dashboard_Inventory_and_Rules_are_displayed_as_main_menu_navigation_options() throws Throwable {
		log.info("User Info main menu displayed "+ rxNavOpt.isUserInfoNavDisplayed());
		Assert.assertTrue(rxNavOpt.isUserInfoNavDisplayed());
		log.info("Dashboard main menu displayed "+ rxNavOpt.isdashBoardDisplayed());
		Assert.assertTrue(rxNavOpt.isdashBoardDisplayed());	
		log.info("Inventory main menu displayed "+ rxNavOpt.isInventoryNavDisplayed());
		Assert.assertTrue(rxNavOpt.isInventoryNavDisplayed());	
		log.info("Rules main menu displayed "+ rxNavOpt.isRuleNavDisplayed());
		Assert.assertTrue(rxNavOpt.isRuleNavDisplayed());	
		
	}
	
	@When("^Check for Sub menu option under Inventory main menu of publisher\\.$")
	public void check_for_Sub_menu_option_under_Inventory_main_menu_of_publisher() throws Throwable {
		
		log.info("User logged in to check the navigation option for Sub mennu option under Publisher main menu :"+ pubListPgs.logodisplayed());
		
			 log.info("Click on Inventory main menu expansion ");
			 rxNavOpt.expandInventory();
			
	}
	
	
	@When("^Check for Sub menu option under Rules main menu of publisher$")
	public void check_for_Sub_menu_option_under_Rules_main_menu_of_publisher() throws Throwable {
		
		log.info("User logged in to check the navigation option for Sub mennu option under Publisher main menu :"+ pubListPgs.logodisplayed());
		
		Assert.assertTrue(pubListPgs.logodisplayed());
		rxNavOpt.expandRules();
	}
	
	@When("^Verify the pagination of the listed rows in the Page with a selection of (.*) rows per page with (.*) columns$")
	public void verifyPagination(String noOfRowsPerPage, int noOfColumns) throws Throwable {
		int finalRowCountPerPage;
		int TotalRowsCount;
		int initialCount ;
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(rxNavOpt.tableFirstRowName));
		rxNavOpt.clickNoOfPagesDropDown();
		driver.findElement(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active']"
				+ "//div[@class='v-list-item__title' and text()='"+noOfRowsPerPage+"']")).click();
		//Thread.sleep(5000);
		waitForPageLoaderToDisappear();
		if(rxNavOpt.tableRowsCount.size()<Integer.parseInt(noOfRowsPerPage)) {
			js.executeScript("arguments[0].scrollIntoView()", rxNavOpt.nextPageNavButton);
			Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", rxNavOpt.nextPageNavButton));
			Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", rxNavOpt.previousPageNavButton));
		}else {
		
		while(true) {
        //Thread.sleep(5000);
		String paginationTextPattern = rxNavOpt.getPaginationText();
		String paginationText = paginationTextPattern.replaceAll("\\s", "");
		System.out.println(paginationText);
		TotalRowsCount = Integer.parseInt(paginationText.substring(paginationText.indexOf('f')+1, paginationText.length()));
		finalRowCountPerPage = Integer.parseInt(paginationText.substring(paginationText.indexOf('-')+1, paginationText.indexOf('o')));
		System.out.println(TotalRowsCount);
		System.out.println(finalRowCountPerPage);
		if(finalRowCountPerPage==TotalRowsCount) {
			Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", rxNavOpt.nextPageNavButton));
			break;
		}
		
		Assert.assertEquals(rxNavOpt.tableColumnsCount.size(),noOfColumns+1);
		Assert.assertEquals(rxNavOpt.tableRowsCount.size(), Integer.parseInt(noOfRowsPerPage));
		rxNavOpt.clickNextPageNav();
		}
		while(true) {
			if((boolean)js.executeScript("return arguments[0].hasAttribute(\"disabled\");", rxNavOpt.nextPageNavButton)) {
				Assert.assertEquals(rxNavOpt.tableColumnsCount.size(),noOfColumns+1);
				rxNavOpt.clickPreviousPageNav();
				Thread.sleep(500);
				
			}else {
			Assert.assertEquals(rxNavOpt.tableColumnsCount.size(),noOfColumns+1);
			Assert.assertEquals(rxNavOpt.tableRowsCount.size(), Integer.parseInt(noOfRowsPerPage));
			String paginationTextPattern = rxNavOpt.getPaginationText();
			String paginationText = paginationTextPattern.replaceAll("\\s", "");
			initialCount = Integer.parseInt(paginationText.substring(0,paginationText.indexOf('-')));
			if(initialCount==1) {
				Assert.assertTrue((boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", rxNavOpt.previousPageNavButton));
				break;
			}
			rxNavOpt.clickPreviousPageNav();
			}
		}
		}
		}

	@Then("^Verify that Details is second column right on ID$")
	public void verifyThatDetailsIsSecondColumnRightOnID() {
		System.out.println("rxNavOpt.idColumn.getText().trim() >>> " + rxNavOpt.idColumn.getText().trim());
		System.out.println("rxNavOpt.detailsColumn.getText().trim() >>> " + rxNavOpt.idColumn.getText().trim());
		Assert.assertEquals(rxNavOpt.idColumn.getText().trim(), "ID");
		Assert.assertEquals(rxNavOpt.detailsColumn.getText().trim(), "Details");
	}

	@When("^Update \"([^\"]*)\" column value in the first row to a unique value in \"([^\"]*)\" page$")
	public void updateColumnValueInTheFirstRowToAUniqueValueInSpecifiedPage(String headerName, String page) {
		WebElement element = null;
		switch(page){
			case "Publishers":
				element = pubListPgs.publisherNameInput;
				break;
			case "Users":
				element = rxUserPage.userName;
				break;
			case "Media":
				element = mediaPage.mediaNameInput;
				break;
			case "Ad Spots":
				element = adspotsPage.adSpotNameField;
				break;
			case "Private Auctions":
				element = auctionPage.auctionNameField;
				break;
			case "Deals":
				element = dealsPage.dealName;
				break;
			case "Targeting Rules":
				element = targetingPage.ruleNameField;
				break;
		}

		int columnIndex = rxNavOpt.getHeaderIndex(headerName) + 1;
		System.out.println("column index >>> " + columnIndex);

		rxNavOpt.getElementByXpathWithParam(rxNavOpt.nameLinkString, String.valueOf(columnIndex)).click();
		driverWait().until(ExpectedConditions.visibilityOf(rxNavOpt.saveButton));

		this.valueInSpecifiedColumn = "TestAuto" + RXUtile.getRandomNumberFourDigit();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(this.valueInSpecifiedColumn);
		System.out.println("Update " + headerName + " column value in the first row in " + page + " page >>> " + this.valueInSpecifiedColumn);

		rxNavOpt.saveButton.click();
		driverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(rxNavOpt.saveBtnString)));
	}

	@When("^Click on next page button$")
	public void clickOnNextPageButton() {
		rxNavOpt.clickNextPageNav();
//		driverWait().until(ExpectedConditions.visibilityOf(rxNavOpt.tableLoaderBar));
		waitForPageLoaderToDisappear();
	}

	@Then("^Check the \"([^\"]*)\" value that is noted in first row does not exist in second page$")
	public void checkTheValueThatIsNotedInFirstRowDoesnotExistInSecondPage(String headerName) throws InterruptedException {
		for(WebElement element : rxNavOpt.getColumnDataMatchingHeader(headerName)){
			if(element.getText().trim().equals(this.valueInSpecifiedColumn)){
				fail("In column " + headerName + ", " + this.valueInSpecifiedColumn + " also exists in second page, please create a unique value.");
			}
		}
	}

	@When("^Enter the \"([^\"]*)\" value that is noted in first row into search textbox$")
	public void enterTheValueThatIsNotedInFirstRowIntoSearchTextbox(String headerName) {
		rxNavOpt.searchValueInListView(this.valueInSpecifiedColumn);
		waitForPageLoaderToDisappear();
	}

	@Then("^Verify the \"([^\"]*)\" value that is noted in first row is returned$")
	public void verifyTheValueThatIsNotedInFirstRowIsReturned(String headerName) throws InterruptedException {
		if (rxNavOpt.getElementByXpathWithParam(rxNavOpt.specifiedColumnIn1stRowString, "1").getText().equals("No data available")) {
			fail("The searched name as " + this.valueInSpecifiedColumn + "is not available");
		} else {
			List<WebElement> coulmnData = rxNavOpt.getColumnDataMatchingHeader(headerName);
			for (int j = 0; j < coulmnData.size(); j++) {
				Assert.assertTrue(coulmnData.get(j).getText().trim().contains(this.valueInSpecifiedColumn));
			}
		}
	}
}
