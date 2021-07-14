package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import RXPages.RXAdspotsPage;
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
import RXPages.RXMediaPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MediaPageStepsDefinition extends RXMediaPage {

	RXMediaPage mediaPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	RXAdspotsPage adspotsPage;
	RXUtile rxUTL;
	Logger log = Logger.getLogger(MediaPageStepsDefinition.class);

	public MediaPageStepsDefinition() {
		super();
		mediaPage = new RXMediaPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		adspotsPage = new RXAdspotsPage();
		rxUTL = new RXUtile();
	}
	WebDriverWait wait = new WebDriverWait(driver, 30);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public String enteredPublisher;
	public String enteredMediaName;
	public String enteredMediaType;
	public String enteredSiteURL;
	public String enteredCategory;
	public String mediaID;
	List<String> activeMediaIDList = new ArrayList<>();
	List<String> inactiveMediaIDList = new ArrayList<>();
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Media option under Inventory$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandInventory();
//		WebDriverWait wait = new WebDriverWait(driver, 30);
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
	public void verifyHEnableDisableMedia(String action) throws InterruptedException {

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

	@When("^Click on Create Media button$")
	public void clickOnCreateMediaButton() {
		wait.until(visibilityOf(mediaPage.createMediaBtn));
		mediaPage.createMediaBtn.click();
		wait.until(visibilityOf(mediaPage.createMediaHeader));
	}

	@When("^Enter the following values in Create Media page$")
	public void enterTheFollowingValuesInCreateMediaPage(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String enteredValue = "";
		for(Map<String, String> stringMap : list){
			for(String key : stringMap.keySet()){
				enteredValue = stringMap.get(key);
				System.out.println("=== select/enter value for " + key + " ===");
				switch (key){
					case "Publisher":
						mediaPage.publisherNameDropdown.click();
						mediaPage.selectValueFromDropdown(enteredValue);
						this.enteredPublisher = enteredValue;
						break;
					case "Media Name":
						while (!mediaPage.mediaNameInput.getAttribute("value").equals("")) {
							mediaPage.mediaNameInput.sendKeys(Keys.BACK_SPACE);
						}
						this.enteredMediaName = enteredValue+ RXUtile.getRandomNumberFourDigit();
						wait.until(ExpectedConditions.elementToBeClickable(mediaPage.mediaNameInput));
						mediaPage.mediaNameInput.sendKeys(this.enteredMediaName);
						break;
					case "Media Type":
						mediaPage.mediaTypeDropdown.click();
						mediaPage.selectValueFromDropdown(enteredValue);
						this.enteredMediaType = enteredValue;
						break;
					case "Site URL":
						wait.until(ExpectedConditions.elementToBeClickable(mediaPage.siteURLInput));
						mediaPage.siteURLInput.sendKeys(enteredValue);
						this.enteredSiteURL = enteredValue;
						break;
					case "Categories":
						mediaPage.categoriesDropdown.click();
						mediaPage.getCategoriesDropdownCheckbox(enteredValue).click();
						this.enteredCategory = enteredValue;
						break;
				}
			}
		}
	}

	@When("^Click on Save Media button$")
	public void clickOnSaveMediaButton() {
		mediaPage.saveButton.click();
	}

	@And("^Click on Publisher input$")
	public void clickOnPublisherInput() {
		mediaPage.publisherNameDropdown.click();
	}

	@Then("^Verify the following message is displayed when the publisher changed for Media$")
	public void verifyTheFollowingMessageIsDisplayedWhenThePublisherChangedForMedia(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (Map<String, String> stringMap : list) {
			String expectedMessage = stringMap.get("Message");
			System.out.println("Banner Message "+ mediaPage.getChangePublisherBannerMsg());
			Assert.assertEquals(mediaPage.getChangePublisherBannerMsg(), expectedMessage);
		}
	}

	@Then("^Verify the Create Media entity page is disabled$")
	public void verifyTheCreateMediaEntityPageIsDisabled() {
		//Publisher
		System.out.println("Publisher field's enabled >>> " + mediaPage.publisherNameInput.isEnabled());
		Assert.assertFalse(mediaPage.publisherNameInput.isEnabled());

		//Active button
		System.out.println("Active button's enabled >>> " + mediaPage.activeCheckbox.isEnabled());
		Assert.assertFalse(mediaPage.activeCheckbox.isEnabled());

		//Media Name
		System.out.println("Media Name field's enabled >>> " + mediaPage.mediaNameInput.isEnabled());
		Assert.assertFalse(mediaPage.mediaNameInput.isEnabled());

		//Media Type
		System.out.println("Media Type dropdown's enabled >>> " + mediaPage.mediaTypeInput.isEnabled());
		Assert.assertFalse(mediaPage.mediaTypeInput.isEnabled());

		//Site URL
		System.out.println("Site URL field's enabled >>> " + mediaPage.siteURLInput.isEnabled());
		Assert.assertFalse(mediaPage.siteURLInput.isEnabled());

		//Categories
		System.out.println("Categories field's enabled >>> " + mediaPage.categoriesInput.isEnabled());
		Assert.assertFalse(mediaPage.categoriesInput.isEnabled());

		//Save Media
		System.out.println("Save Media button's enabled >>> " + mediaPage.saveButton.isEnabled());
		Assert.assertFalse(mediaPage.saveButton.isEnabled());
	}

	@Then("^Verify the Create Media page is filled with data$")
	public void verifyTheCreateMediaPageIsFilledWithData() {
		//Publisher
		System.out.println("Publisher field's value >>> " + mediaPage.publisherNameDropdown.getText());
		Assert.assertEquals(mediaPage.publisherNameDropdown.getText().trim(), this.enteredPublisher);

		//Media Name
		System.out.println("Media Name field's value >>> " + mediaPage.mediaNameInput.getAttribute("value"));
		Assert.assertEquals(mediaPage.mediaNameInput.getAttribute("value"), this.enteredMediaName);

		//Media Type
		System.out.println("Media Type field's value >>> " + mediaPage.mediaTypeDropdown.getText().trim());
		Assert.assertEquals(mediaPage.mediaTypeDropdown.getText().trim(), this.enteredMediaType);

		//Site URL
		System.out.println("Site URL field's value >>> " + mediaPage.siteURLInput.getAttribute("value"));
		Assert.assertEquals(mediaPage.siteURLInput.getAttribute("value"), this.enteredSiteURL);

		//Categories
		System.out.println("Categories field's value >>> " + mediaPage.categoriesDropdown.getText().trim());
		Assert.assertEquals(mediaPage.categoriesDropdown.getText().trim(), this.enteredCategory);
	}

	@Then("^Verify the validation errors display near Save Media button$")
	public void verifyTheValidationErrorsDisplayNearSaveMediaButton(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",mediaPage.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(mediaPage.checkIfErrorIsDisplayed(e.getValue())));
	}

	@When("^Select publisher by name: \"([^\"]*)\" in Create Media page$")
	public void selectPublisherByNameInCreateMediaPage(String arg0) throws Throwable {
		mediaPage.publisherNameDropdown.click();
		mediaPage.selectValueFromDropdown(arg0);
	}

	@Then("^Verify the validation error does not display in Create Media page$")
	public void verifyTheValidationErrorDoesNotDisplayInCreateMediaPage(DataTable dt) {
		js.executeScript("arguments[0].scrollIntoView();",mediaPage.validationErrorsPanel);
		getDataFromTable(dt).forEach(e ->
				Assert.assertFalse(mediaPage.checkIfErrorIsDisplayed(e.getValue())));
	}

	@When("^Enter \"([^\"]*)\" into Media Name in Create Media page$")
	public void enterIntoMediaNameInCreateMediaPage(String arg0) throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(mediaPage.mediaNameInput));
		mediaPage.mediaNameInput.sendKeys(arg0 + RXUtile.getRandomNumberFourDigit());
	}

	@When("^Select Media Type by value: \"([^\"]*)\" in Create Media page$")
	public void selectMediaTypeByValueInCreateMediaPage(String arg0) throws Throwable {
		mediaPage.mediaTypeDropdown.click();
		mediaPage.selectValueFromDropdown(arg0);
	}

	@When("^Enter \"([^\"]*)\" into Site URL in Create Media page$")
	public void enterIntoSiteURLInCreateMediaPage(String arg0) throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(mediaPage.siteURLInput));
		mediaPage.siteURLInput.sendKeys(arg0);
	}

	@Then("^Verify no validation errors display in Create Media page$")
	public void verifyNoValidationErrorsDisplayInCreateMediaPage() {
		js.executeScript("arguments[0].scrollIntoView();",mediaPage.saveButton);
		Assert.assertFalse(mediaPage.isElementPresent(mediaPage.validationErrorsCssPath));
	}

	@When("^Select \"([^\"]*)\" \"([^\"]*)\" media in list view$")
	public void selectActiveMediaInListView(String count, String status) throws Throwable {
		int rowNum = 0;
		String mediaID = "";
		int loop = Integer.parseInt(count);
		for(int i = 0; i < loop; i++){
			System.out.println("select "+ status + " media loop >>> " + loop);
			for(int j = 0; j < mediaPage.statusColumnsMediaTable.size(); j++){
				String value = mediaPage.statusColumnsMediaTable.get(j).getText().trim();
				System.out.println("status column value >>> " + value);
				if(value.equals(status)){
					rowNum = j + 1;
					System.out.println(status + " in row number >>> " + rowNum);
					if(!mediaPage.verifyIfCheckboxIsChecked(rowNum)){
						mediaPage.getCheckboxInSpecifiedRowInMediaTable(rowNum).click();
						mediaID = mediaPage.getMediaIDElemtByRowNumber(rowNum).getText().trim();
						if(status.equals("Active")){
							System.out.println("Store Active media ID"  + mediaID + " to activeMediaIDList");
							this.activeMediaIDList.add(mediaID);
						}else{
							System.out.println("Store Inactive media ID"  + mediaID +" to inactiveMediaIDList");
							this.inactiveMediaIDList.add(mediaID);
						}
						break;
					}
				}
			}
		}
	}

	@Then("^Verify the following buttons are present in Media page$")
	public void verifyTheFollowingButtonsArePresentInMediaPage(DataTable dt) {
		getDataFromTable(dt).forEach(e ->
				Assert.assertTrue(mediaPage.verifyButtonDisplaysInHeaderOfMediaPage(e.getValue())));
	}

	@When("^Click on Edit Media button$")
	public void clickOnEditMediaButton() {
		mediaPage.clickOverViewEditbutton();
	}

	@Then("^Verify Edit Media page displays$")
	public void verifyEditMediaPageDisplays() {
		wait.until(ExpectedConditions.visibilityOf(mediaPage.saveButton));
		Assert.assertTrue(mediaPage.pageTitle.getText().contains("Edit Media"));
	}

	@When("^Close Edit Media page$")
	public void closeEditMediaPage() {
		mediaPage.closeEditMediatBtn.click();
	}

	@When("^Click on \"([^\"]*)\" Media button$")
	public void clickOnDeactivateActivateMediaButton(String arg0) throws Throwable {
		if(arg0.equals("Deactivate")){
			mediaPage.clickOverViewDisablebutton();
		}else{
			mediaPage.clickOverViewEnablebutton();
		}
		wait.until(ExpectedConditions.visibilityOf(mediaPage.createMediaBtn));
	}

	@Then("^Verify the selected \"([^\"]*)\" media change to \"([^\"]*)\" status in Media list view$")
	public void verifyTheSelectedMediaChangeToStatusInMediaListView(String status, String expectedStatus) throws Throwable {
		if(status.equals("Inactive")){
			for(String id : this.inactiveMediaIDList){
				System.out.println("Media ID >>> " + id);
				Assert.assertEquals(mediaPage.getStatusElemtByID(id).getText().trim(), expectedStatus);
			}
			this.inactiveMediaIDList.clear();
		}else{
			for(String id : this.activeMediaIDList){
				System.out.println("Media ID >>> " + id);
				Assert.assertEquals(mediaPage.getStatusElemtByID(id).getText().trim(), expectedStatus);
			}
			this.activeMediaIDList.clear();
		}
	}

	@When("^Enter \"([^\"]*)\" into Search input in Media page$")
	public void enterIntoSearchInputInMediaPage(String arg0) throws Throwable {
		mediaPage.searchInput.sendKeys(arg0);
		Thread.sleep(2000);
	}

	@Then("^Verify that search results displayed and publisher in search results \"([^\"]*)\"$")
	public void verifyThatSearchResultsDisplayedAndPublisherInSearchResults(String arg0) throws Throwable {
		int itemCount = mediaPage.mediaTableRows.size();
		String classAttribute = mediaPage.mediaTableRows.get(0).getAttribute("class");
		System.out.println("mediaPage.mediaTableRows.size() >>> " + itemCount);
		System.out.println("The first item class attribute >>> " + classAttribute);
		if(itemCount == 1 && classAttribute.contains("empty")){
			Assert.fail("No data available");
		}else{
			for(WebElement publisherElemt : mediaPage.publisherColumnsMediaTable){
				Assert.assertEquals(publisherElemt.getText().trim(), arg0);
			}
		}
	}

	@Then("^Verify following columns are displayed by default in the Media list$")
	public void verifyFollowingColumnsAreDisplayedByDefaultInTheMediaList(DataTable dt) {
        getDataFromTable(dt).forEach(e ->
                Assert.assertTrue(mediaPage.verifyHeaderDisplayInMediaTable(e.getValue())));
	}

	@Then("^Verify that Active toggle set to true$")
	public void verifyThatActiveToggleSetToTrue() {
		Assert.assertEquals(mediaPage.activeCheckbox.getAttribute("aria-checked"), "true");
	}

	@Then("^Verify that Active as a value displayed in Status column$")
	public void verifyThatActiveAsAValueDisplayedInStatusColumn() {
		wait.until(ExpectedConditions.visibilityOf(mediaPage.createMediaBtn));
		Assert.assertEquals(mediaPage.getStatusElemtByMediaName(this.enteredMediaName).getText().trim(), "Active");
	}
	@When("^Search Media with name \"([^\"]*)\"$")
	public void search_Media_with_name(String mediaName) throws Throwable {
		adspotsPage.searchAdspots(mediaName);
		waitForPageLoaderToDisappear();
	}

	@Then("^Verify that Media \"([^\"]*)\" is displayed$")
	public void verify_that_Media_is_displayed(String mediaName) throws Throwable {
		List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Media Name");
		for (int j = 0; j < coulmnData.size(); j++) {
			Assert.assertEquals(coulmnData.get(j).getText().trim(), mediaName);
		}
	}

	@Then("^Verify that no results are displayed$")
	public void verify_that_no_results_are_displayed() throws Throwable {
		Assert.assertEquals(mediaPage.noDataAvailable.getText(), "No data available");
	}

	@Then("^Verify that Media can be Enabled and Disabled from list$")
	public void verify_that_Media_can_be_Enabled_and_Disabled_from_list() throws Throwable {
		for (int i = 0; i <= 1; i++) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
			List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Status");
			String status = coulmnData.get(0).getText();
			switch (status) {
				case "Active":
					Assert.assertTrue(mediaPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(mediaPage.overviewDisablebutton.isDisplayed());
					mediaPage.clickOverViewDisablebutton();
					waitForPageLoaderToDisappear();
//					Thread.sleep(3000);
					List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Status");
					Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
					break;
				case "Inactive":
					Assert.assertTrue(mediaPage.overviewEditbutton.isDisplayed());
					Assert.assertTrue(mediaPage.overviewEnablebutton.isDisplayed());
					String enableText = mediaPage.overviewEnablebutton.getText().replaceAll("\\s", "");
//					Assert.assertEquals(enableText, "ACTIVATEPRIVATEAUCTION");
					mediaPage.clickOverViewEnablebutton();
					waitForPageLoaderToDisappear();   
//					Thread.sleep(3000);
					List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Status");
					Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
					break;

				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify Publisher name field is disabled on Create Media page$")
	public void verify_Publisher_name_field_is_disabled_on_Create_Media_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		String isPubNameDisabled = mediaPage.publisherNameField.getAttribute("class");
		String value = mediaPage.publisherNameField.getText();
		Assert.assertTrue(isPubNameDisabled.contains("disabled"));
		Assert.assertFalse(value.isEmpty());
	}

	@Then("^Verify following fields are not enabled for create Media page$")
	public void verify_following_fields_are_not_enabled_for_create_Media_page(DataTable dt) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(1000);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String isDisabled = mediaPage.mandatorFieldIsDisabledForCreatePage(fieldName);
			Assert.assertTrue(isDisabled.contains("disabled"),fieldName+" is not disable.");
		}
	}

	@Then("^Verify every fields go to default state$")
	public void verify_every_fields_go_to_default_state() throws Throwable {
		//Publisher
		System.out.println("Publisher field's value >>> " + mediaPage.publisherNameDropdown.getText());
		Assert.assertEquals(mediaPage.publisherNameDropdown.getText().trim(), this.enteredPublisher);

		//Media Name
		System.out.println("Media Name field's value >>> " + mediaPage.mediaNameInput.getAttribute("value"));
		Assert.assertEquals(mediaPage.mediaNameInput.getAttribute("value"), "");

		//Media Type
		System.out.println("Media Type field's value >>> " + mediaPage.mediaTypeDropdown.getText().trim());
		Assert.assertEquals(mediaPage.mediaTypeDropdown.getText().trim(), "");

		//Site URL
		System.out.println("Site URL field's value >>> " + mediaPage.siteURLInput.getAttribute("value"));
		Assert.assertEquals(mediaPage.siteURLInput.getAttribute("value"), "");

		//Categories
		System.out.println("Categories field's value >>> " + mediaPage.categoriesDropdown.getText().trim());
		Assert.assertEquals(mediaPage.categoriesDropdown.getText().trim(), "");
	}

	@Then("^Verify the created media data is matching with its overview list values$")
	public void verify_the_created_media_data_is_matching_with_its_overview_list_values() throws Throwable {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		String mediaName = mediaPage.getMediaName();
		Assert.assertEquals(mediaName, this.enteredMediaName);
		String publisherName = mediaPage.getPublisherName();
		Assert.assertEquals(publisherName, this.enteredPublisher);
		String mediaType = mediaPage.getMediaType();
		Assert.assertEquals(mediaType, this.enteredMediaType);
	}

	@Then("^Click on the created media name in the overview page$")
	public void click_on_the_created_media_name_in_the_overview_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			String enteredName = this.enteredMediaName.replaceAll("\\s", "");
			List<WebElement> listOfNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[3]/a"));
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");

				if (enteredName.equals(reqName)) {
					listOfNames.get(k).click();
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + this.enteredMediaName + "')]"))));
				System.out.println("??????"+driver.findElement(
						By.xpath("//label[text()='Categories']/following-sibling::div[@class='v-select__selections']/span")).getAttribute("class"));
		} catch (NullPointerException e) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]/a")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));

		}
	}
	}
