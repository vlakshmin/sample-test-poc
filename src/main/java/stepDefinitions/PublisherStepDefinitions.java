package stepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	 static String adOpsMail;
	 static String pubName;
	 static String adOpsPerson;
	 static String publisherID;
	 static ArrayList<WebElement> publist = new ArrayList<WebElement>();

	WebDriverWait wait = new WebDriverWait(driver, 50);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	List<String> activePubIDList = new ArrayList<>();
	List<String> inactivePubIDList = new ArrayList<>();

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
						pubListPgs.publisherNameInput.sendKeys(Keys.CONTROL + "a");
						pubListPgs.publisherNameInput.sendKeys(pubName);
						System.out.println("enter publisher name >>> " + pubName);
						break;
					case "Ad Ops Person":
						pubListPgs.adOpsPersonInput.sendKeys(Keys.CONTROL + "a");
						pubListPgs.adOpsPersonInput.sendKeys(enteredValue);
						adOpsPerson = enteredValue;
						break;
					case "Ad Ops Email":
						pubListPgs.adOpsEmailInput.sendKeys(Keys.CONTROL + "a");
						pubListPgs.adOpsEmailInput.sendKeys(enteredValue);
						adOpsMail = enteredValue;
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
						pubListPgs.domainInput.sendKeys(Keys.CONTROL + "a");
						pubListPgs.domainInput.sendKeys(enteredValue);
						break;
					case "Categories":
						pubListPgs.categoriesDropdown.click();
						pubListPgs.getElementByXpathWithParameter(pubListPgs.categoriesValueString, enteredValue).click();
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
		driverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(pubListPgs.loadingXpathString)));
		driverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(pubListPgs.pubNameLinkStringInPubTable,pubName))));
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
					pubListPgs.getElementByXpathWithParameter(pubListPgs.checkboxColumnByRowNumber, String.valueOf(rowNum)).click();
					pubName = pubListPgs.getElementByXpathWithParameter(pubListPgs.pubNameColumnByRowNumber, String.valueOf(rowNum)).getText().trim();
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
	}

	@Then("^Verify that all items are sorted alphabetically$")
	public void verifyThatAllItemsAreSortedAlphabetically() throws InterruptedException {
		String preItem;
		String nextItem = "";
		pubListPgs.scrollDownInDropdown();
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
		publisherID = pubListPgs.getElementByXpathWithParameter(pubListPgs.idColumnByPubName,pubName).getText().trim();
		System.out.println("publisherID >>> " + publisherID);
		pubListPgs.getElementByXpathWithParameter(pubListPgs.pubNameLinkStringInPubTable, pubName).click();
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

	@Then("^Verify that \"([^\"]*)\" as a value displayed in Active column in publisher list view$")
	public void verifyThatActiveInactiveAsAValueDisplayedInActiveColumnInPublisherListView(String status) {
		Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.activeColumnStringInPubTable, pubName).getText().trim(), status);
	}

    @Then("^Verify that below errors are displayed near Save Publisher button$")
    public void verifyThatAllErrorsAreDisplayedNearSavePublisherButton(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",pubListPgs.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(pubListPgs.checkIfErrorIsDisplayed(e.getValue())));
    }

	@Then("^Verify that below errors are not displayed near Save Publisher button$")
	public void verifyThatBelowErrorsAreNotDisplayedNearSavePublisherButton(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",pubListPgs.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertFalse(pubListPgs.checkIfErrorIsDisplayed(e.getValue())));
	}

	@Then("^Verify no validation errors display in Create Publisher page$")
	public void verifyNoValidationErrorsDisplayInCreatePublisherPage() {
		js.executeScript("arguments[0].scrollIntoView();",pubListPgs.savePublisherBtn);
		Assert.assertFalse(pubListPgs.isElementPresent(pubListPgs.validationErrorsCssPath));
	}

	@When("^Select \"([^\"]*)\" \"([^\"]*)\" publisher in list view$")
	public void selectPublisherInListView(String count, String status) {
		int rowNum = 0;
		String pubID = "";
		int loop = Integer.parseInt(count);
		for(int i = 0; i < loop; i++){
			System.out.println("select "+ status + " publisher loop >>> " + loop);
			for(int j = 0; j < pubListPgs.statusColumnsPublisherTable.size(); j++){
				String value = pubListPgs.statusColumnsPublisherTable.get(j).getText().trim();
				System.out.println("status column value >>> " + value);
				if(value.equals(status)){
					rowNum = j + 1;
					System.out.println(status + " in row number >>> " + rowNum);
					if(!pubListPgs.verifyIfCheckboxIsChecked(rowNum)){
						pubListPgs.getElementByXpathWithParameter(pubListPgs.checkboxColumnByRowNumber, String.valueOf(rowNum)).click();
						pubID = pubListPgs.getElementByXpathWithParameter(pubListPgs.idColumnByRowNumber, String.valueOf(rowNum)).getText().trim();
						if(status.equals("Active")){
							System.out.println("Store Active publisher ID "  + pubID + " to activePubIDList");
							this.activePubIDList.add(pubID);
						}else{
							System.out.println("Store Inactive publisher ID "  + pubID +" to inactivePubIDList");
							this.inactivePubIDList.add(pubID);
						}
						break;
					}
				}
			}
		}
	}

	@Then("^Verify the following buttons are present in Publisher page$")
	public void verifyTheFollowingButtonsArePresentInPublisherPage(DataTable dt) {
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(pubListPgs.verifyButtonDisplaysInHeaderOfMediaPage(e.getValue())));
	}

	@Then("^Verify Edit Publisher page displays$")
	public void verifyEditPublisherPageDisplays() {
		wait.until(ExpectedConditions.visibilityOf(pubListPgs.savePublisherBtn));
		Assert.assertTrue(pubListPgs.pageTitle.getText().contains("Edit Publisher"));
	}

	@When("^Close Edit Publisher page$")
	public void closeEditPublisherPage() {
		pubListPgs.closeEditPubtBtn.click();
	}

	@When("^Click on \"([^\"]*)\" button in Publisher page$")
	public void clickOnPublisherButtonInPublisherPage(String arg0) {
		pubListPgs.getElementByXpathWithParameter(pubListPgs.activateInactivateBtnString, arg0).click();
		wait.until(ExpectedConditions.visibilityOf(pubListPgs.createPublisherBtn));
	}

	@Then("^Verify the selected \"([^\"]*)\" publisher change to \"([^\"]*)\" status in Publisher list view$")
	public void verifyTheSelectedPublisherChangeToStatusInPublisherListView(String status, String expectedStatus) {
		if(status.equals("Inactive")){
			for(String id : this.inactivePubIDList){
				System.out.println("Publisher ID >>> " + id);
				Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.statusByIDInPubTable,id).getText().trim(), expectedStatus);
			}
			this.inactivePubIDList.clear();
		}else{
			for(String id : this.activePubIDList){
				System.out.println("Publisher ID >>> " + id);
				Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.statusByIDInPubTable,id).getText().trim(), expectedStatus);
			}
			this.activePubIDList.clear();
		}
	}

	@When("^\"([^\"]*)\" the Active toggle button in Create Publisher page$")
	public void disableEnableTheActiveToggleButtonInCreatePublisherPage(String arg0) {
		String flag = pubListPgs.activeCheckbox.getAttribute("aria-checked");
		System.out.println("pubListPgs.activeCheckbox.getAttribute(\"aria-checked\") >>> " + flag);
		switch (arg0){
			case "Disable":
				if(flag.equals("true")){
					pubListPgs.activeToggleBtn.click();
				}
				break;
			case "Enable":
				if(flag.equals("false")){
					pubListPgs.activeToggleBtn.click();
				}
				break;
		}
	}

    @Then("^Verify following columns are displayed by default in the Publishers overview page$")
    public void verifyFollowingColumnsAreDisplayedByDefaultInThePublishersOverviewPage(DataTable dt) {
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(pubListPgs.verifyHeaderDisplayInPublisherOverviewPage(e.getValue())));
    }

	@Then("^Verify that update was successful for edit publisher$")
	public void verifyThatUpdateWasSuccessfulForEditPublisher() {
		Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.pubNameColumnByID, publisherID).getText().trim(), pubName);
		Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.adOpsPersonColumnByID, publisherID).getText().trim(), adOpsPerson);
		Assert.assertEquals(pubListPgs.getElementByXpathWithParameter(pubListPgs.adOpsMailColumnByID, publisherID).getText().trim(), adOpsMail);
	}
}


