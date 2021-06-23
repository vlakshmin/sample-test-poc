package stepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXLoginPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

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
	 static String pubName;
	 static String saleAcc;
	 static String publisherID;
	 static ArrayList<WebElement> publist = new ArrayList<WebElement>();

	WebDriverWait wait = new WebDriverWait(driver, 50);

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

	
	@When("^Click on publisher option under Admin$")
	public void click_on_publisher_option_under_Admin() throws Throwable {
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


	@When("^Click on Create Publisher button$")
	public void clickOnCreatePublisherButton() {
		wait.until(visibilityOf(pubListPgs.createPublisherBtn));
		pubListPgs.createPublisherBtn.click();
		wait.until(visibilityOf(pubListPgs.createPublisherHeader));
	}

	@And("^Enter the following values in Create Publisher page$")
	public void enterTheFollowingValuesInCreatePublisherPage(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String enteredValue = "";
		for(Map<String, String> stringMap : list){
			for(String key : stringMap.keySet()){
				enteredValue = stringMap.get(key);
				System.out.println("=== select/enter value for " + key + " ===");
				switch (key){
					case "Publisher Name":
						pubName = enteredValue + RXUtile.getRandomNumberFourDigit();
						pubListPgs.publisherNameInput.sendKeys(pubName);
						System.out.println("enter publisher name >>> " + pubName);
						break;
					case "Ad Ops Person":
						pubListPgs.adOpsPersonInput.sendKeys(enteredValue);
						break;
					case "Ad Ops Email":
						pubListPgs.adOpsEmailInput.sendKeys(enteredValue);
						break;
					case "Currency":
						wait.until(ExpectedConditions.elementToBeClickable(pubListPgs.currencyDropdown));
						pubListPgs.currencyDropdown.click();
						pubListPgs.selectValueFromDropdown(enteredValue);
						break;
					case "Demand Source":
						List<String> valueList = new ArrayList<String>();
						pubListPgs.demandSourceDropdown.click();
						if(enteredValue.contains(",")){
							String[] valueArray = enteredValue.split(",");
							valueList.addAll(Arrays.asList(valueArray));
						}else{
							valueList.add(enteredValue);
						}
						for (String value : valueList){
							pubListPgs.selectValueFromDropdown(value);
						}
						pubListPgs.publisherNameInput.click();
						break;
					case "Domain":
						pubListPgs.domainInput.sendKeys(enteredValue);
						break;
					case "Categories":
						pubListPgs.categoriesDropdown.click();
						pubListPgs.getCategoriesDropdownCheckbox(enteredValue).click();
						pubListPgs.publisherNameInput.click();
						break;
				}
			}
		}
	}

	@When("^Click on Save Publisher button$")
	public void clickOnSavePublisherButton() {
		pubListPgs.savePublisherBtn.click();
	}

	@Then("^Verify that save publisher is successful$")
	public void verifyThatSavePublisherIsSuccessful() throws InterruptedException {
		pubListPgs.waitCreatePublisherPageDisappear();
		Thread.sleep(5000);
		Assert.assertTrue(pubListPgs.checkIfPublisherExist(pubName));
	}

	@When("^Select \"([^\"]*)\" publisher in list view$")
	public void selectPublisherInListView(String arg0) throws Throwable {
		int rowNum = 0;
		int loop = Integer.parseInt(arg0);
		for(int i = 0; i < loop; i++){
			for(int j = 0; j < pubListPgs.publishersTableTrElemts.size(); j++){
				rowNum = j + 1;
				System.out.println(" row number >>> " + rowNum);
				if(!pubListPgs.verifyIfCheckboxIsChecked(rowNum)){
					pubListPgs.getCheckboxInSpecifiedRowInPublishersTable(rowNum).click();
					pubName = pubListPgs.getPublisherNameElemtByRowNumber(rowNum).getText().trim();
					break;
				}
			}
		}
	}

	@When("^Click on Edit Publisher button$")
	public void clickOnEditPublisherButton() {
		wait.until(ExpectedConditions.visibilityOf(pubListPgs.overviewEditbutton));
		pubListPgs.overviewEditbutton.click();
		wait.until(ExpectedConditions.visibilityOf(pubListPgs.savePublisherBtn));
	}

    @When("^Clear the Demand Source values$")
    public void clearTheDemandSourceValues() {
	    List<String> selectedValueList = pubListPgs.getTheSelectedDemandSource();
	    pubListPgs.demandSourceDropdown.click();
	    //uncheck the selected value
	    for(String value : selectedValueList){
			pubListPgs.selectValueFromDropdown(value);
		}
        pubListPgs.publisherNameInput.click();
	}

	@When("^Click on Demand Source dropdown$")
	public void clickOnDemandSourceDropdown() throws InterruptedException {
		pubListPgs.demandSourceDropdown.click();
		Thread.sleep(2000);
	}

	@Then("^Verify that all items are sorted alphabetically$")
	public void verifyThatAllItemsAreSortedAlphabetically() throws InterruptedException {
		String preItem;
		String nextItem = "";
		pubListPgs.scrollDownInDropdown();
		Thread.sleep(2000);
		int size = pubListPgs.getDemandSourceDropdownItem().size();
		System.out.println("pubListPgs.getDemandSourceDropdownItem().size() >>> " + size);
		for(int i = 0; i < size; i ++){
			System.out.println("i >>> " + i);
			preItem = nextItem;
			nextItem = pubListPgs.getDemandSourceDropdownItem().get(i).getText().trim();
			System.out.println("preItem >>> " + preItem);
			System.out.println("nextItem >>> " + nextItem);
			if(!preItem.equals("")){
				Assert.assertTrue(preItem.compareToIgnoreCase(nextItem) <= 0);
			}
		}
		//click publisher field to close Demand Source dropdown
		pubListPgs.publisherNameInput.click();
	}

	@When("^Click on the newly created publisher in list view$")
	public void clickOnTheNewlyCreatedPublisherInListView() {
		pubListPgs.getPublisherNameLinkByText(pubName).click();
		wait.until(ExpectedConditions.visibilityOf(pubListPgs.savePublisherBtn));
	}

	@Then("^Verify that selected Demand Sources are sorted alphabetically$")
	public void verifyThatSelectedDemandSourcesAreSortedAlphabetically() {
		String preItem = "";
		String nextItem = "";
		List<String> selectedValueList = pubListPgs.getTheSelectedDemandSource();
		for(int i = 0; i < selectedValueList.size(); i ++){
			System.out.println("i >>> " + i);
			preItem = nextItem;
			nextItem = selectedValueList.get(i);
			System.out.println("preItem >>> " + preItem);
			System.out.println("nextItem >>> " + nextItem);
			if(!preItem.equals("")){
				Assert.assertTrue(preItem.compareToIgnoreCase(nextItem) <= 0);
			}
		}
	}

	@Then("^Verify that Active toggle set to true in Create Publisher page$")
	public void verifyThatActiveToggleSetToTrueInCreatePublisherPage() {
		Assert.assertEquals(pubListPgs.activeCheckbox.getAttribute("aria-checked"), "true");
	}

	@Then("^Verify that Active as a value displayed in Active column in publisher list view$")
	public void verifyThatActiveAsAValueDisplayedInActiveColumnInPublisherListView() {
		Assert.assertEquals(pubListPgs.getActiveColumnByPublisherName(pubName).getText().trim(), "Active");
	}
}


