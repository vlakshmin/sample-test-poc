package stepDefinitions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXDealsPage;
import RXPages.RXPrivateAuctionsPage;
import RXPages.RXProtectionsPage;

public class ProtectionsPageStepDefinition  extends RXProtectionsPage{
	RXProtectionsPage protectionsPage;
	RXPrivateAuctionsPage auctionPage;
	PublisherListPage pubListPgs;
	RXAdspotsPage adspotsPage;
	RXDealsPage dealsPage;
	Logger log = Logger.getLogger(PrivateAuctionPageStepDefinition.class);
	String enterSearchName = "";
	String protectionsTotalNum = "";
	String enteredPublisherName = "";
	String enteredProtectionsName = "";
	List<String> enteredProtectionsNameList = new ArrayList<String>();
	LinkedHashMap<String,String> detailsData;
	List<String> advIncludedTable = new ArrayList<String>();
	Map<String,String> enteredDataInCreateProtections = new HashMap<>();

	public ProtectionsPageStepDefinition() {
		super();
		protectionsPage = new RXProtectionsPage();
		pubListPgs = new PublisherListPage();
		adspotsPage = new RXAdspotsPage();
		dealsPage = new RXDealsPage();
		auctionPage = new RXPrivateAuctionsPage();
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@Then("^Protections is present in the left nav menu$")
	public void protections_is_present_in_the_left_nav_menu() {
		log.info("User logged in to check the navigation option for Protections option in main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.protectionsLabel));
		Assert.assertTrue(protectionsPage.protectionsLabel.isDisplayed());
	}

	@When("^Click on Protections option in Menu$")
	public void click_on_Protections_option_in_Menu()  {
		protectionsPage.protectionsLabel.click();
		enteredDataInCreateProtections.clear();
	}

	@Then("^User displayed with Protections page$")
	public void user_displayed_with_Protections_page() {
		Assert.assertEquals(protectionsPage.getPageHeading(), protectionsPage.protectionsHeaderStr);
		log.info("Auction Page Header is asserted  and it is : " + protectionsPage.getPageHeading());
		protectionsPage.waitProtectionsTableLoading();
		protectionsTotalNum = protectionsPage.getProtectionsTotalNum();
	}

	@When("^Search Protections item with \"([^\"]*)\"$")
	public void search_Protections_item_with(String searchType)  {
		String name = protectionsPage.getProtectionsName();
		switch(searchType) {
		case "partly name":
			name = name.substring(0, 2);
			break;
		case "single letter":
			name = name.substring(0, 1);
			break;
		default:
			
		}
		enterSearchName =name;
		if (protectionsPage.protectionsSearchClearButton.getAttribute("disabled") == null) {
			protectionsPage.protectionsSearchClearButton.click();
		}
		protectionsPage.protectionsSearchInput.sendKeys(name);
	}

	@Then("^Verify that exicting Protections item is displayed via \"([^\"]*)\" search$")
	public void verify_that_exicting_Protections_item_is_displayed_via_search(String searchType) {
		List<WebElement> nameData = driver.findElements(By.xpath("a[contains(@href,'protections')]"));
		nameData.forEach(e -> Assert.assertTrue(protectionsPage.protectionsIsDisplayedViaSearch(e,enterSearchName)));
	}

	@When("^Click on Search clear button$")
	public void click_on_Search_clear_button() {
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.protectionsSearchClearButton));
		protectionsPage.protectionsSearchClearButton.click();
	}

	@Then("^Verify that all Protections items are displayed$")
	public void verify_that_all_Protections_items_are_displayed() throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.protectionsSearchProgress));
		protectionsPage.waitAllProtectionsItemsLoading();
		Assert.assertEquals(protectionsPage.getProtectionsTotalNum(),protectionsTotalNum);
	}
	
	@Then("^Verify that default value is (\\d+) items per page$")
	public void verify_that_default_value_is_items_per_page(int arg1) {
		Assert.assertEquals(protectionsPage.rowsPerPageDefault.getText(),String.valueOf(arg1));
	}

	@Then("^Verify that each items value displayes proper amount of items$")
	public void verify_that_each_items_value_displayes_proper_amount_of_items(){
		Assert.assertEquals(protectionsPage.getTotalProtectionsWebElements().size(),protectionsPage.getProtectionsPerPageNum());
	}

	@Then("^Verify that next page/prev page displays proper amount of items$")
	public void verify_that_next_page_prev_page_displays_proper_amount_of_items(){
		int nextPage_loop = 1;
		int prePage_loop = 1;
//		System.out.println("Click on next page button.");
	    while(protectionsPage.nextPage.getAttribute("disabled") == null && nextPage_loop < 3) {
	    	js.executeScript("arguments[0].click()", protectionsPage.nextPage);
	    	wait.until(ExpectedConditions.visibilityOf(protectionsPage.protectionsSearchProgress));
			protectionsPage.waitAllProtectionsItemsLoading();
			Assert.assertEquals(protectionsPage.getTotalProtectionsWebElements().size(),protectionsPage.getProtectionsPerPageNum());
			nextPage_loop++;
	    }
//		System.out.println("Click on previous page button.");
	    while(protectionsPage.previousPage.getAttribute("disabled") == null && prePage_loop < 3) {
	    	js.executeScript("arguments[0].click()", protectionsPage.previousPage);
	    	wait.until(ExpectedConditions.visibilityOf(protectionsPage.protectionsSearchProgress));
	    	protectionsPage.waitAllProtectionsItemsLoading();
			Assert.assertEquals(protectionsPage.getTotalProtectionsWebElements().size(),protectionsPage.getProtectionsPerPageNum());
			prePage_loop++;
	    }
	}

	@Then("^\"([^\"]*)\" column should be sorted in \"([^\"]*)\" order$")
	public void column_should_be_sorted_in_order(String columnName, String sortType) {
	    List<String> columnDatas = protectionsPage.getColumnDatas(columnName);
	    switch(sortType) {
	    case "descending":
	    	Assert.assertTrue(protectionsPage.isSortedInDescendingOrder(columnDatas,columnName));
	    	break;
	    case "ascending":
	    	Assert.assertTrue(protectionsPage.isSortedInAscendingOrder(columnDatas,columnName));
	    	break;
	    default:
	    	Assert.fail(sortType + " is not found.");
	    }
	}

	@When("^Click on \"([^\"]*)\" sorting symbol for \"([^\"]*)\" column$")
	public void click_on_sorting_symbol_for_column(String sortType, String columnName) {
		protectionsPage.getColumnHeader(columnName).click();
    	protectionsPage.waitAllProtectionsItemsLoading();
	}
	
	@When("^Click on Table Options button$")
	public void click_on_Table_Options_button() {
		protectionsPage.tableOptions.click();
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.tableOptionsMenu));
	}

	@When("^Select the columns from Table Options$")
	public void select_the_columns_from_Table_Options(DataTable dt)  {
	    List<String> columns = dt.asList(String.class);
	    columns.forEach(e -> protectionsPage.selectColumnInTableOptions(e));
	}

	@Then("^Should see the header in Protections table$")
	public void should_see_the_header_in_Protections_table(DataTable dt) {
		List<String> columns = dt.asList(String.class);
		columns.forEach(e -> Assert.assertTrue(protectionsPage.getColumnHeader(e).isDisplayed()));
	}

	@Then("^Date format of \"([^\"]*)\" column should be \"([^\"]*)\"$")
	public void date_format_of_column_should_be(String columnName, String dateFormat) {
		List<String> columnDatas = protectionsPage.getColumnDatas(columnName);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		columnDatas.forEach(e -> LocalDate.parse(e, formatter));
	}
	
	@Then("^Should not see the header in Protections table$")
	public void should_not_see_the_header_in_Protections_table(DataTable dt)  {
		List<String> columns = dt.asList(String.class);
		columns.forEach(e -> Assert.assertFalse(protectionsPage.notSeeHeaderInProtectionsTable(e)));
	}
	
	@Then("^\"([^\"]*)\" items are displayed for Active/Inactive column$")
	public void items_are_displayed_for_Active_Inactive_column(String arg1)  {
		protectionsPage.waitAllProtectionsItemsLoading();
		List<String> columns = protectionsPage.getColumnDatas("Active/Inactive");
		columns.forEach(e ->  Assert.assertTrue(e.equalsIgnoreCase(arg1),e+" is not same "+arg1));
	}
	
	@Then("^Both \"([^\"]*)\" and \"([^\"]*)\" items are displayed for Active/Inactive column$")
	public void both_and_items_are_displayed_for_Active_Inactive_column(String arg1, String arg2) {
		protectionsPage.waitAllProtectionsItemsLoading();
		List<String> columns = protectionsPage.getColumnDatas("Active/Inactive");
		columns.forEach(e ->  protectionsPage.bothActiveAndInactiveAreDisplayed(e));
	}
	
	@When("^Click Add Protections Targeting button$")
	public void click_Add_Protections_Targeting_button(){
		wait.until(ExpectedConditions.elementToBeClickable(protectionsPage.addProtectionsTargetingButton)).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='menu']"))));
	}

	@Then("^Verify that following items are present$")
	public void verify_that_following_items_are_present(DataTable dt) {
		List<String> items= dt.asList(String.class);
		items.forEach(e -> Assert.assertTrue(protectionsPage.getMenuItemIcon(e).isDisplayed()));
	}

	@Then("^tooltip \"([^\"]*)\" displays when mouse hovered on \"([^\"]*)\"$")
	public void tooltip_displays_when_mouse_hovered_on(String tooltip, String item){
		Actions action = new Actions(driver);
		action.moveToElement(protectionsPage.getMenuItemIcon(item)).perform();
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.tooltip));
		Assert.assertTrue(tooltip.equals(protectionsPage.tooltip.getText()),tooltip+ ">>>>>" +protectionsPage.tooltip.getText());
	}
	
	@When("^Select \"([^\"]*)\" from Add Protections Targeting$")
	public void select_from_Add_Protections_Targeting(String item) {
		protectionsPage.getMenuItemIcon(item).click();
	}

	@Then("^Verify that \"([^\"]*)\" is enabled$")
	public void verify_that_is_enabled(String item) {
		wait.until(ExpectedConditions.attributeToBe(protectionsPage.getMenuItem(item), "aria-disabled", ""));
	}

	@Then("^Verify that \"([^\"]*)\" is disabled$")
	public void verify_that_is_disabled(String item) {
		wait.until(ExpectedConditions.attributeToBe(protectionsPage.getMenuItem(item), "aria-disabled", "true"));
	}

	@Then("^Delete \"([^\"]*)\" in Create Protections page$")
	public void delete_in_Create_Protections_page(String item){
		wait.until(ExpectedConditions.elementToBeClickable(protectionsPage.getRemoveButton(item))).click();
	}

	@Then("^Verify that each card can be added only one at a time$")
	public void verify_that_each_card_can_be_added_only_one_at_a_time() {
		wait.until(ExpectedConditions.attributeToBe(protectionsPage.addProtectionsTargetingButton, "aria-expanded", "false"));
	}
	
	@Then("^Enter the following data in the Create Protections page$")
	public void enterGenaralCardProtections(DataTable dt) throws Throwable {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");
			String listValueIndex = list.get(i).get("ListValueIndex");
			switch (fieldName) {
			case "Publisher Name":
				wait.until(ExpectedConditions.visibilityOf(adspotsPage.publisherNameDropDown));
				adspotsPage.publisherNameDropDown.click();
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(protectionsPage.dropDownPublisher(listValueIndex)));
					js.executeScript("arguments[0].scrollIntoView()", protectionsPage.dropDownPublisher(listValueIndex));
					protectionsPage.dropDownPublisher(listValueIndex).click();
				} else {
					dealsPage.selectPublisherByName(value);
				}

				enteredPublisherName = adspotsPage.publisherNameField.getText();
//				System.out.println("publisher entered as :" + enteredPublisherName);
				protectionsPage.waitPublisherNameLoading();
				wait.until(ExpectedConditions.visibilityOf(auctionPage.auctionNameField));
				break;
			case "Name":
				while (!auctionPage.auctionNameField.getAttribute("value").equals("")) {
					auctionPage.auctionNameField.sendKeys(Keys.BACK_SPACE);
				}
				if(value.equals("Long value")){
					auctionPage.auctionNameField.sendKeys(RandomStringUtils.randomAlphanumeric(255));
				}else{
					Calendar cal = Calendar.getInstance();
					auctionPage.auctionNameField.sendKeys(value + cal.getTimeInMillis());
				}

				enteredProtectionsName = auctionPage.auctionNameField.getAttribute("value");
//				System.out.println("Entered Auction name:" + enteredProtectionsName);
				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");
			}
			enteredDataInCreateProtections.put("Inventory", "All Inventory are included");
			enteredDataInCreateProtections.put("Devices", "All Devices are included");
			enteredDataInCreateProtections.put("Operating Systems", "All Operating Systems are included");
			enteredDataInCreateProtections.put("Geos", "All Geos are included");
			enteredDataInCreateProtections.put("Ad Sizes", "All Ad Sizes are included");
		}
	}
	
	@Then("^Verify that all data is reseted$")
	public void verify_that_all_data_is_reseted() {
		//verify name is reset
		wait.until(ExpectedConditions.attributeToBe(auctionPage.auctionNameField, "value", ""));
		//verify Active is reset
		Assert.assertEquals(auctionPage.toggleFieldIsEnabledForCreatePage("Active"),"true");
		//verify Inventory is reset
		protectionsPage.allInventorysDisplayDefaultValue();
		//verify Protection targeting is reset
		verify_that_is_enabled("Advertiser");
		verify_that_is_enabled("Ad Category");
		verify_that_is_enabled("All Ads");
	}
	
	@Then("^Verify that search with \"([^\"]*)\" works properly for Publisher dropdown$")
	public void verify_that_search_works_properly_for_Publisher_dropdown(String searchName) {
		wait.until(ExpectedConditions.visibilityOf(adspotsPage.publisherNameDropDown));
		adspotsPage.publisherNameDropDown.click();
		Actions action = new Actions(driver);
		action.sendKeys(searchName).perform();
		Assert.assertTrue(protectionsPage.getHighlightedPublisher().getText().startsWith(searchName));
		protectionsPage.protectionsCloseSideDialog.click();
		protectionsPage.waitCreateProtectionsClosed();
	}
	
	@Then("^Verify that all elements are present and have proper default value$")
	public void verify_that_all_elements_are_present_and_have_proper_default_value() {
		Assert.assertTrue(adspotsPage.publisherNameDropDown.isDisplayed());
		Assert.assertTrue(auctionPage.auctionNameField.isDisplayed());
		Assert.assertTrue(auctionPage.toggleField("Active").isDisplayed());
		Assert.assertTrue(protectionsPage.addProtectionsTargetingButton.isDisplayed());
		Assert.assertTrue(protectionsPage.saveProtectionButton.isDisplayed());
		protectionsPage.allInventorysDisplayDefaultValue();
	}
	
	@Then("^Click on Save Protection button$")
	public void click_on_Save_Protection_button() {
		protectionsPage.saveProtectionButton.click();
	}

	@Then("^Verify that error apear for Name$")
	public void verify_that_error_apear_for_Name() {
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.nameMessage));
	}
	
	@Then("^Verify the created Protection data is matching with its overview list values$")
	public void verify_the_created_Protection_data_is_matching_with_its_overview_list_values() {
		protectionsPage.waitCreateProtectionsClosed();
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.getNameColumn(enteredProtectionsName)));
	}
	
	@When("^Select the created Protection$")
	public void select_the_created_Protection() {
		protectionsPage.getCreatedProtectionCheckBox(enteredProtectionsName).click();;
	}

	@When("^Click on \"([^\"]*)\" button in Protections list page$")
	public void click_on_button_in_Protections_list_page(String buttonName) {
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.toolbarButton(buttonName)));
		protectionsPage.toolbarButton(buttonName).click();
	}

	@Then("^Select one \"([^\"]*)\" Protections item$")
	public void select_one_Protections_item(String active) {
		enteredProtectionsNameList.clear();
		List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[5]"));
		for (int k = 0; k < listActives.size(); k++) {
			String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
			if (active.equals(reqActive)) {
				protectionsPage.protectionsCheckBox(k+1).click();
				enteredProtectionsNameList.add(protectionsPage.protectionsName(k+1));
				break;
			}
		}
	}

	@Then("^Verify that following buttons are present in Protections list page$")
	public void verify_that_following_buttons_are_present_in_Protections_list_page(DataTable dt) {
		List<String> buttons = dt.asList(String.class);
		buttons.forEach(e -> Assert.assertTrue(protectionsPage.toolbarButton(e).isDisplayed(), e + " is not present."));
	}

	@When("^Click \"([^\"]*)\" button in Protections list page$")
	public void click_button_in_Protections_list_page(String buttonName){
		protectionsPage.toolbarButton(buttonName).click();
		if(!buttonName.equals("Edit Protections")) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text() , '" + buttonName + "')]/parent::button")));
		}
	}

	@Then("^Edit Protections pop up is present$")
	public void edit_Protections_pop_up_is_present() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + enteredProtectionsNameList.get(0) + "')]"))));
	}

	@Then("^\"([^\"]*)\" is displayed for the created Protections$")
	public void is_displayed_for_the_created_Protections(String arg1) {
		List<WebElement> listOfNames = driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[3]/a"));
		for(int i=0;i<enteredProtectionsNameList.size();i++) {
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");
				if (enteredProtectionsNameList.get(i).equals(reqName)) {
					Assert.assertEquals(protectionsPage.protectionsActive(k+1).getText(), arg1, protectionsPage.protectionsActive(k+1).getText() +" is displayed for the created protections");
					break;
				}
			}
		}
	}

	@Then("^Select (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" Protections items$")
	public void select_and_Protections_items(int num1, String inactive, int num3, String active) {
		enteredProtectionsNameList.clear();
		List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[5]"));
		for (int k = 0; k < listActives.size(); k++) {
			String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
			if (active.equals(reqActive) && num3 > 0) {
				protectionsPage.protectionsCheckBox(k+1).click();
				enteredProtectionsNameList.add(protectionsPage.protectionsName(k+1));
				num3--;
			}
			if(inactive.equals(reqActive) && num1 > 0) {
				protectionsPage.protectionsCheckBox(k+1).click();
//				System.out.println(protectionsPage.protectionsName(k+1));
				enteredProtectionsNameList.add(protectionsPage.protectionsName(k+1));
				num1--;
			}
			if(num1 == 0 && num3 == 0) {
				break;
			}
		}
		Assert.assertEquals(num1,0,num1 +" Inactive Protections is not selected.");
		Assert.assertEquals(num3,0,num3 +" Active Protections is not selected.");
	}

	@Then("^Click on the Create Protections button$")
	public void click_on_the_following_create_button() throws Throwable {
		createButtonClick("Create Protections");
	}

	@Then("^Verify that warning banner is not displayed under Publisher name$")
	public void verify_that_warning_banner_is_not_displayed_under_Publisher_name() throws Throwable {
		wait.until(
				ExpectedConditions.invisibilityOf(auctionPage.warningBannerUnderPublishername));
	}

	@When("^Close Create Protections page$")
	public void close_Create_Protections_page() throws Throwable {
		protectionsPage.protectionsCloseSideDialog.click();
		protectionsPage.waitCreateProtectionsClosed();
	}

	@When("^Hover over the Details icon in Protections page$")
	public void hoverOverTheDetailsIcon() {
//		driver.navigate().refresh();
		adspotsPage.hoverOverDetailsButton();
		detailsData = protectionsPage.getProtectionsDetailsData();
	}

	@When("^Enable \"([^\"]*)\" radio button in Add Protections Targeting section$")
	public void enableRadioBtnInAddProtectionsTargetingSection(String btnName) {
		wait.until(ExpectedConditions.visibilityOf(protectionsPage.getElementByXpathWithParameter(protectionsPage.targetAwayParentDiv, btnName)));
		if(!protectionsPage.getElementByXpathWithParameter(protectionsPage.targetAwayParentDiv, btnName).getAttribute("class").contains("active")){
			protectionsPage.getElementByXpathWithParameter(protectionsPage.targetAwayRadioBtn, btnName).click();
		}
	}

	@When("^Select the following advertisers from left panel in Add Protections Targeting section$")
	public void selectTheFollowingAdvFromLeftPanelInAddProtectionsTargetingSection(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String value = "";
		for (Map<String, String> stringMap : list) {
			String advName = stringMap.get("Advertiser");
			advIncludedTable.add(advName);
			WebElement advElemt = protectionsPage.getElementByXpathWithParameter(protectionsPage.advInSelectTable, advName);
			js.executeScript("arguments[0].scrollIntoView()", advElemt);
			advElemt.click();
			wait.until(ExpectedConditions.visibilityOf(protectionsPage.getElementByXpathWithParameter(protectionsPage.advInIncludedTable, advName)));
		}

		String cardValue = protectionsPage.getElementByXpathWithParameter(protectionsPage.cardTitleProtectionsTargeting, "Advertiser").getText().trim();
		if(cardValue.contains("Whitelist")){
			value = cardValue.replace("Whitelist", "Allowing");
		}else if(cardValue.contains("Block")){
			value = cardValue.replace("Block", "Blocking");
		}
		enteredDataInCreateProtections.put("Advertisers", value);
	}

	@Then("^Verify the protections details data is correct$")
	public void VerifyTheProtectionsDetailsDataIsCorrect() {
		Assert.assertTrue(areEqual(detailsData,enteredDataInCreateProtections));
	}

	@Then("^Verify that details popup contain Advertisers section and advertisers that were selected before displayed in \"([^\"]*)\" X Advertisers section$")
	public void VerifyThatDetailsPopupContainAdvertisersSectionAndAdvertisersThatWereSelectedBeforeDisplayedInBlockingOrAllowingAdvertisersSection(String arg0) {
		Assert.assertEquals(enteredDataInCreateProtections.get("Advertisers").toLowerCase(), detailsData.get("Advertisers").toLowerCase());
	}
}
