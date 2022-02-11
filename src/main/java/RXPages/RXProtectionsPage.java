package RXPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RXProtectionsPage extends RXBasePage {
	public String protectionsHeaderStr = "Protections";
	RXPrivateAuctionsPage privateAuctionsPage;
	public String loading = "//main//div[@class='container container--fluid']//table/tbody/tr";
	public String targetAwayRadioBtn = "//label[text()='%s']/preceding-sibling::div";
	public String targetAwayParentDiv = "//label[text()='%s']/parent::div";
	public String valueInSelectTableInProtectionTargeting = "//table[contains(@class,'select-table')]/tbody/tr/td/div[contains(text(),'%s')]/parent::td/following-sibling::td[contains(@class,'options')]";
	public String valueTrElmtInSelectTable = "//table[contains(@class,'select-table')]/tbody/tr/td/div[normalize-space(text())='%s']/ancestor::tr";
	public String valueInIncludedTableInInventoryTargeting = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'included-table')]/tr/td/div[normalize-space(text())='%s' and not(@class='parent-label')]";
	public String valueInIncludedTableInProtectionTargeting = "//table[contains(@class,'included-table')]/tr/td/div[contains(text(),'%s') and not(@class='parent-label')]";
	public String valueInIncludedTableByRowNum = "//table[contains(@class,'included-table')]/tr[%s]/td[1]/div[not(@class='parent-label')]";
	public String cardNameProtectionsTargeting = "//div[not(contains(@style,'none'))]/div/div[contains(@class,'v-card__title') and contains(text(),'%s')]";
	public String cardValueProtectionsTargeting = "//div[not(contains(@style,'none'))]/div/div[contains(@class,'v-card__title') and contains(text(),'%s')]/following-sibling::span";
	public String cardValueInventoryTargeting = "//h3[text()='%s']/following-sibling::div[@class='selectionInfo']/span";
	public String protectionTypeDropdownValue = "//div[@class='v-list-item__content']/div[contains(text(), '%s')]";
	public String errorMsg = "//div[contains(@class,'validation-errors')]/div/div/div/ul/li[text()='%s']";
	public String includeBtn = "//div[normalize-space(text())='%s']/parent::td/following-sibling::td[contains(@class,'options')]/div[contains(@class,'include')]/button[contains(@class,'unchecked')]";
	public String excludeBtn = "//div[normalize-space(text())='%s']/parent::td/following-sibling::td[contains(@class,'options')]/div[contains(@class,'exclude')]/button[contains(@class,'unchecked')]";
	public String clearAllBtn = "//h3[text()='%s']/parent::button/following-sibling::div//span[text()='Clear All']/parent::button";
	public String includeAllBtn = "//h3[text()='%s']/parent::button/following-sibling::div//div[contains(text(),'Include All')]/ancestor::button";
	public String excludeAllBtn = "//h3[text()='%s']/parent::button/following-sibling::div//div[contains(text(),'Exclude All')]/ancestor::button";
	public String allParentItemsInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'select-table')]/tbody/tr[@class='select-row']/td[@class='first']/div";
	public String allItemsInIncludedTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'included-table')]/tr[not(contains(@class,'banner'))]/td[1]/div[not(@class='parent-label')]";
	public String includedItemsInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//td[contains(@class,'included')]/parent::tr/td[@class='first']/div";
	public String excludedItemsInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//td[contains(@class,'excluded')]/parent::tr/td[@class='first']/div";
	public String includedItemsInSelectTableInProtectionTargeting = "//div[contains(@class,'v-card__title') and contains(text(),'%s')]/parent::div/parent::div[not(contains(@style,'none'))]//td[contains(@class,'included')]/parent::tr/td[@class='first']/div";
	public String allItemsInIncludedTableInProtectionTargeting = "//div[contains(@class,'v-card__title') and contains(text(),'%s')]/parent::div/parent::div[not(contains(@style,'none'))]//table[contains(@class,'included-table')]/tr/td[1]/div[not(@class='parent-label')]";
	public String protectionTargetingSection = "//h2[text()='Protection targeting']/ancestor::div[@class='container']";
	public String protectionTypeSelectedValue = "//label[text()='Protection Type']/following-sibling::div[@class='v-select__selections']/div";
	public String includeOrExcludeAllBtnLabel = "//h3[text()='%s']/parent::button/following-sibling::div//button[contains(@class,'select-all')]/span/div[1]";
	public String parentLabelInRightPanel = "//table[contains(@class,'included-table')]//div[normalize-space(text())='%s']/preceding-sibling::div[@class='parent-label']";
	public String previousPanel = "//h3[text()='%s']/ancestor::div[contains(@class,'v-expansion-panel')]/preceding::div[contains(@class,'v-expansion-panel--next-active')]/button/following-sibling::div";
	public String bannerInIncludedTable = "//h3[text()='%s']/parent::button/following-sibling::div//div[@class='results-header']/following-sibling::div[contains(@class,'banner')]";
	public String expandIconInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'select-table')]/tbody/tr/td[@class='nested']/i";
	public String itemCountInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//button[contains(@class,'select-all')]/span/div[@class='item-count']";
	public String allChildForParent = "//div[normalize-space(text())='%s']/ancestor::tbody/tr[contains(@class,'select-row children')]/td[@class='first']/div";
	public String removeIconForValueInIncludedTable = "//table[contains(@class,'included-table')]//div[contains(text(),'%s') and not(@class='parent-label')]/parent::td/following-sibling::td[@class='options']/button";
	public String vIconForParent = "//table[contains(@class,'select-table')]//div[normalize-space(text())='%s']/ancestor::tr/td[@class='nested']/i";
	public String valuesInDetailsPopup = "//div[@class='header' and contains(text(),'%s')]/following-sibling::div//span";
	public String vIconForAllParentInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'select-table')]//td[@class='nested']/i";
	public String inactiveParentInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'select-table')]/tbody/tr[@class='select-row']//span[normalize-space(text())='(Inactive)']";
	public String inactiveChildenInSelectTable = "//h3[text()='%s']/parent::button/following-sibling::div//table[contains(@class,'select-table')]/tbody/tr[contains(@class,'select-row children')]//span[normalize-space(text())='(Inactive)']";
	public String protectionsSearchProgressStr = "//main//div[@class='container container--fluid']//table//tr[@class='v-data-table__progress']";
	public String protectionsSearchProgressTheadStr = "//main//div[@class='container container--fluid']//table/thead";
	public String changePubLoadingStr = "//div[contains(@class,'v-overlay--absolute')]/div";

	@FindBy(xpath = "//div[text()='Protections ']")
    public WebElement protectionsLabel;
	
	// protections page heading
    @FindBy(xpath = "//h1[text()='Protections']")
    public  WebElement protectionsPageHeader;
	
    @FindBy(xpath = "//label[text() = 'Search']/following-sibling::input[@type = 'text']")
    public WebElement protectionsSearchInput;
    
    @FindBy(xpath = "//label[text() = 'Search']/following-sibling::input[@type = 'text']")
    public WebElement protectionsSearchButton;
    
//    @FindBy(xpath = "//*[@class='v-data-table_progress']")
    @FindBy(xpath = "//main//div[@class='container container--fluid']//table//tr[@class='v-data-table__progress']")
    public WebElement protectionsSearchProgress;
    
    @FindBy(xpath = "//main//div[@class='container container--fluid']//table/tbody/tr")
    public WebElement protectionsLoading;
    
    @FindBy(xpath = "//*[@class='v-input__icon v-input__icon--clear']/button")
    public WebElement protectionsSearchClearButton;
    
    @FindBy(xpath = "//div[@class='v-data-footer']/div[@class='v-data-footer__pagination']")
    public WebElement protectionsPagination;
   
    // default value per page
    @FindBy(xpath = "//main//div[@class='v-select__selection v-select__selection--comma']")
    public WebElement rowsPerPageDefault;
    
    @FindBy(xpath = "//div[@class='v-data-footer__icons-after']/button")
    public WebElement nextPage;
    
    @FindBy(xpath = "//button[@aria-label='Previous page']")
    public WebElement previousPage;
    
    @FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//thead//th/span"))
	public List<WebElement> tableHeadersList;
    
    @FindBy(xpath = "//div[@class='table-options']/button")
	public WebElement tableOptions;
    
    @FindBy(xpath = "//div[@id='app']/div[@role='menu']")
	public WebElement tableOptionsMenu;
    
    @FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__content']/button")
	public WebElement protectionsCloseSideDialog;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveProtectionButton;
    
    @FindBy(xpath = "//label[contains(text(), 'Name')]/ancestor::div[@class = 'v-input__slot']/following-sibling::div//div[@class='v-messages__message']")
    public WebElement nameMessage;

	@FindBy(xpath = "//div[contains(@class,'v-list')]/ancestor::div[contains(@class, 'menu-wrapper')]")
	private WebElement detailsCard;

	@FindBy(xpath = "//label[text()='Name']/ancestor::div[@class='row']/following-sibling::div[1]/descendant::label")
	public WebElement protectionTypeLabel;

	@FindBy(xpath = "//label[text()='Protection Type']/following-sibling::div[@class='v-select__selections']")
	public WebElement protectionTypeDropdown;

	@FindBy(xpath = "//div[contains(@class,'menuable__content__active')]")
	public WebElement protectionTypeDropdownDiv;

	@FindBy(xpath = "//label[text()='Protection Type']/following-sibling::div[@class='v-select__selections']/input")
	public WebElement protectionTypeInput;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'menuable__content__active')]/div/div/div/div[@class='v-list-item__title']"))
	public List<WebElement> protectionTypeValuesList;

	@FindBy(xpath = "//thead/tr/th[1]/div/i")
	public WebElement checkbox_i_Column;

	@FindBy(xpath = "//thead/tr/th[1]/div")
	public WebElement checkbox_div_Column;

	@FindAll(@FindBy(xpath = "//table[contains(@class,'included-table')]/tr"))
	public List<WebElement> trElmentListInIncludedTable;

	@FindBy(xpath = "//label[text()='Show Inactive']/ancestor::div[contains(@class,'pane selections')]/div[@class='select-header']/div[@class='options']/button/span/div[1]")
	public WebElement btnLabelBelowShowInactive;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'cardPadding v-card')]/div[not(contains(@style,'none'))]//table[contains(@class,'select-table')]/tbody/tr/td[contains(@class,'options')]"))
	public List<WebElement> allItemsOptionInSelectTableInProtectionTargeting;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'cardPadding v-card')]/div[not(contains(@style,'none'))]//table[contains(@class,'select-table')]/tbody/tr/td[@class='first']/div[not(@class='parent-label')]"))
	public List<WebElement> allItemsValueInSelectTableInProtectionTargeting;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'cardPadding v-card')]/div[not(contains(@style,'none'))]//table[contains(@class,'select-table')]/tbody/tr/td[@class='nested']/i"))
	public List<WebElement> allVIconForParentInAdCategories;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'cardPadding v-card')]/div[not(contains(@style,'none'))]//table[contains(@class,'included-table')]/tr/td[@class='options']/button/span/i"))
	public List<WebElement> allRemoveIconInIncludedTableInProtectionTargeting;

	@FindBy(xpath = "//div[contains(@class,'cardPadding v-card')]/div[not(contains(@style,'none'))]//div[@class='multipane']/preceding-sibling::div[contains(@class,'v-alert')]/div/div[@class='v-alert__content']")
	public WebElement tooLargeAlert;

	@FindBy(xpath = "//label[text()='Show Inactive']/preceding-sibling::div/input")
	public WebElement showInactiveInput;

	@FindBy(xpath = "//label[text()='Show Inactive']/preceding-sibling::div")
	public WebElement showInactiveDiv;

	@FindBy(xpath = "//div[contains(@class,'cardPadding')]/div[not(contains(@style,'none'))]//input[@placeholder='Search']")
	public WebElement searchBoxInProtectionTargeting;

	@FindBy(xpath = "//div[not(contains(@style,'none'))]/div/div[contains(@class,'v-card__title') and contains(text(),'Ad Categories')]/parent::div/following-sibling::span//button[contains(@class,'select-all')]")
	public WebElement includeAllBtnInAdCategories;

	@FindBy(xpath = "//label[text()='Name']")
	public WebElement nameLabel;

	// Explicit Wait
    WebDriverWait wait = new WebDriverWait(driver, 10);
    
 // Initialize page factory
    public RXProtectionsPage() {
        PageFactory.initElements(driver, this);
		privateAuctionsPage = new RXPrivateAuctionsPage();
    }
	
	// Get the text of the Protections page
    public String getPageHeading() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOf(protectionsPageHeader));
//        System.out.println(elem.getText());
        return elem.getText();
    }

	public String getProtectionsName() {
		return driver.findElement(By.xpath("//td/a[contains(@href,'protections')]")).getText();
	}

	public boolean protectionsIsDisplayedViaSearch(WebElement e, String enterSearchName) {
//		System.out.println("e.getText() >>> " + e.getText());
		return e.getText().toLowerCase().contains(enterSearchName.toLowerCase());
	}

	public void waitProtectionsTableLoading() {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(this.protectionsSearchProgressTheadStr), 2));//loading displays
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.protectionsSearchProgressStr)));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(this.protectionsSearchProgressStr))));
//		wait.until(ExpectedConditions.visibilityOf(this.protectionsSearchProgress));
//		wait.until(LoadingDisappear());
//		wait.pollingEvery(Duration.ofMillis(250)).until(LoadingDisappear());
//		this.waitAllProtectionsItemsLoading();
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(this.protectionsSearchProgressTheadStr), 1));//loading disappears
	}

	private Function<? super WebDriver,Boolean> LoadingDisappear() {
      return new ExpectedCondition<Boolean>() {
    	  public Boolean apply(WebDriver driver) {
    		  return !driver.findElement(By.xpath(loading)).getText().contains("Loading");
//			  return !protectionsLoading.getText().contains("Loading");
    		  }
      };
	}

	public void waitAllProtectionsItemsLoading() {
		wait.pollingEvery(Duration.ofMillis(250)).until(AllProtectionsItemsLoading());
	}

	private Function<? super WebDriver,Boolean> AllProtectionsItemsLoading() {
		return new ExpectedCondition<Boolean>() {
	    	  public Boolean apply(WebDriver driver) {
	    		  return !IsElementPresent("//main//div[@class='container container--fluid']//table/thead[2]");
	    		  }
	      };
	}
	
	public boolean IsElementPresent(String xpathString)
    {
        try
        {
        	driver.findElement(By.xpath(xpathString));
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
	
	public boolean IsElementPresent(WebElement element)
    {
        try
        {
        	element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

	public String getProtectionsTotalNum() {
		return protectionsPagination.getText().split(" ")[2];
	}
	
	public int getProtectionsPerPageNum() {
		int start = Integer.parseInt(protectionsPagination.getText().split(" ")[0].split("-")[0]);
		int end = Integer.parseInt(protectionsPagination.getText().split(" ")[0].split("-")[1]);
		return end-start+1;
	}
	
	public List<WebElement> getTotalProtectionsWebElements(){
		return driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr"));
	}

	public List<String> getColumnDatas(String columnName) {
		int headerIndex = 0;
		List<WebElement> numberOFHeaders = tableHeadersList;
		for (int i = 0; i < numberOFHeaders.size(); i++) {
			if (numberOFHeaders.get(i).getText().equals(columnName)) {
				headerIndex = i + 1;
			}
		}
		int columnIndex = headerIndex + 1;
		List<WebElement> coulmnData = driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[" + columnIndex + "]"));
		List<String> dataInEachColumn = new ArrayList<String>();
		for (int j = 0; j < coulmnData.size(); j++) {
			dataInEachColumn.add(coulmnData.get(j).getText().toLowerCase());
		}
		return dataInEachColumn;
	}

	public boolean isSortedInAscendingOrder(List<String> columnDatas, String columnName) {
		for(int i=0;i < columnDatas.size()-1;i++) {
			if (columnName.contains("ID")) {
				if(Integer.parseInt(columnDatas.get(i)) > Integer.parseInt(columnDatas.get(i+1))) {
					return false;
				}
			}
			else if((columnDatas.get(i).compareTo(columnDatas.get(i+1))) > 0){
				return false;
		}
	}
		return true;
	}

	public boolean isSortedInDescendingOrder(List<String> columnDatas, String columnName) {
		for(int i=0;i < columnDatas.size()-1;i++) {
			if (columnName.contains("ID")) {
				if(Integer.parseInt(columnDatas.get(i)) < Integer.parseInt(columnDatas.get(i+1))) {
					return false;
				}
			}
			else if((columnDatas.get(i).compareTo(columnDatas.get(i+1))) < 0){
				return false;
		}
	}
		return true;
	}
	
	public WebElement getColumnHeader(String columnName) {
		return driver.findElement(By.xpath(
				"//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + columnName + "']/parent::th"));
	}

	public void selectColumnInTableOptions(String columnName) {
		driver.findElement(By.xpath("//label[text()='"+columnName+"']/preceding-sibling::div")).click();
		
	}

	public boolean notSeeHeaderInProtectionsTable(String columnName) {
		return IsElementPresent("//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + columnName + "']/parent::th");
	}

	public boolean bothActiveAndInactiveAreDisplayed(String e) {
		if(e.equalsIgnoreCase("Active") || e.equalsIgnoreCase("Inactive")) {
			return true;
		}
		return false;
	}

	public String isInventoryDisplayDefaultValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("//h3[text() = '" + fieldName +"']/parent::button/div[@class='selectionInfo']")).getText();
	}

	public WebElement dropDownPublisher(String listValueIndex) {
		return driver
				.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"));
	}

	public WebElement getHighlightedPublisher() {
		return driver
				.findElement(By.xpath("//div[@class='v-list-item primary--text v-list-item--active v-list-item--link theme--light v-list-item--highlighted']"));
	}

	public void allInventorysDisplayDefaultValue() {
		Assert.assertEquals(isInventoryDisplayDefaultValue("Inventory"),"All inventory is included"); 
		Assert.assertEquals(isInventoryDisplayDefaultValue("Device"),"All devices are included");
		Assert.assertEquals(isInventoryDisplayDefaultValue("Operating System"),"All operating systems are included");
		Assert.assertEquals(isInventoryDisplayDefaultValue("Geo"),"All geos are included");
		Assert.assertEquals(isInventoryDisplayDefaultValue("Ad Size"),"All sizes are included");
	}

	public WebElement getNameColumn(String enteredProtectionsName) {
    	return driver.findElement(By.linkText(enteredProtectionsName));
	}

	public void waitCreateProtectionsClosed() {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
	}
	
	public WebElement protectionsCheckBox(int k) {
		return driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[1]"));
	}

	public WebElement getCreatedProtectionCheckBox(String enteredProtectionsName) {
		return driver.findElement(By.xpath("//a[contains(text(), '"+enteredProtectionsName+"')]/parent::td/parent::tr/td[1]"));
	}
	
	public WebElement toolbarButton(String e) {
		return driver
				.findElement(By.xpath("//span[contains(text() , '" + e + "')]/parent::button"));
	}

	public String protectionsName(int k) {
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[4]/a"))
				.getText();
	}

	public WebElement protectionsActive(int k) {
		return driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[5]"));
	}

	public void waitPublisherNameLoading() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//aside//div[@class='content']//div[@class='v-overlay__content']")));
	}

	public WebElement getElementByXpathWithParameter(String xpath, String param){
		return driver.findElement(By.xpath(String.format(xpath, param)));
	}

	public WebElement getElementByXpathWithParameter(String xpath, String param1, String param2){
		return driver.findElement(By.xpath(String.format(xpath, param1, param2)));
	}

	public List<WebElement> getElementListByXpathWithParameter(String xpath, String param){
		return driver.findElements(By.xpath(String.format(xpath, param)));
	}

	public LinkedHashMap<String,String> getProtectionsDetailsData() {
		LinkedHashMap<String,String> result;
		result = detailsCard.findElements(By.xpath("//div[@class='header']")).stream()
				.collect(Collectors.toMap(WebElement::getText, e -> {
					WebElement element = e.findElement(By.xpath("./..//span"));
					driverWait().until(ExpectedConditions.elementToBeClickable(element));
					return e.findElement(By.xpath("./..//span")).getText();
				}, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("Details === " + result);
		return result;
	}

	public void expandPanelInInventoryTargetingSection(String targetingName){
		if(!privateAuctionsPage.targetingExpandPanel(targetingName).getAttribute("class").contains("active")) {
			privateAuctionsPage.targetingExpandPanel(targetingName).click();
			if(!targetingName.equalsIgnoreCase("Inventory")){
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(this.previousPanel,targetingName))));
			}
		}
	}

	public String putMouseOverItem(String itemName){
		Actions actions = new Actions(driver);
		String item = itemName;

		//expand parent entity
		if(itemName.contains(">")){
			String parent = itemName.split(">")[0].trim();
			this.expandTheSpecifiedParentItemInSelectTable(parent);

			item = itemName.split(">")[1].trim();
		}

		//put mouse hover item
		WebElement itemElemt = this.getElementByXpathWithParameter(this.valueTrElmtInSelectTable, item);
		driverWait().until(ExpectedConditions.visibilityOf(itemElemt));
		actions.moveToElement(itemElemt).build().perform();

		return item;
	}

	public void expandTheSpecifiedParentItemInSelectTable(String parentName){
		WebElement parentElement = this.getElementByXpathWithParameter(this.valueTrElmtInSelectTable, parentName);
		driverWait().until(ExpectedConditions.visibilityOf(parentElement));
		if(!this.getElementByXpathWithParameter(this.vIconForParent, parentName).getAttribute("class").contains("flip")){
			parentElement.click();
		}
	}

	public void verifyIncludeExcludeButtonsDisplayed(String itemName){
		Assert.assertTrue(this.getElementByXpathWithParameter(this.includeBtn, itemName).isDisplayed());
		Assert.assertTrue(this.getElementByXpathWithParameter(this.excludeBtn, itemName).isDisplayed());
	}

	public void includeOrExcludeItemInInventoryTargetingSection(String selectFlag, String targetingName, String itemName){
		String selectStr;
		String item;

		if(selectFlag.equalsIgnoreCase("Include")){
			selectStr = this.includeBtn;
		}else{
			selectStr = this.excludeBtn;
		}

		//Check if panel is expanded,expand it if not
		this.expandPanelInInventoryTargetingSection(targetingName);

		item = this.putMouseOverItem(itemName);

		driverWait().until(ExpectedConditions.elementToBeClickable(this.getElementByXpathWithParameter(selectStr, item))).click();
		driverWait().until(ExpectedConditions.visibilityOf(this.getElementByXpathWithParameter(this.valueInIncludedTableInInventoryTargeting, targetingName, item)));
	}

	public int getItemCountInSelectTable(String targetName){
		String itemCountStr = this.getElementByXpathWithParameter(this.itemCountInSelectTable, targetName).getText().replaceAll("(MEDIA|\\(|\\))", "").trim();
//		System.out.println("itemCountStr >>> " + itemCountStr);
		return Integer.parseInt(itemCountStr);
	}
}
