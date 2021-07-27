package RXPages;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXProtectionsPage extends RXBasePage {
	public String protectionsHeaderStr = "Protections";
	RXPrivateAuctionsPage privateAuctionsPage;
	public String loading = "//main//div[@class='container container--fluid']//table/tbody/tr";
	
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
    @FindBy(xpath = "//main//div[@class='container container--fluid']//table/thead[2]")
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
    
    @FindBy(xpath = "//div[@class='v-tooltip__content menuable__content__active v-tooltip__content--fixed']")
	public WebElement tooltip;
    
    @FindBy(xpath = "//span[contains(text(),'Add Protections Targeting')]/parent::button")
    public WebElement addProtectionsTargetingButton;
    
    @FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__content']/button")
	public WebElement protectionsCloseSideDialog;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveProtectionButton;
    
    @FindBy(xpath = "//label[contains(text(), 'Name')]/ancestor::div[@class = 'v-input__slot']/following-sibling::div//div[@class='v-messages__message']")
    public WebElement nameMessage;
    
	 // Explicit Wait
    WebDriverWait wait = new WebDriverWait(driver, 10);
    
 // Initialize page factory
    public RXProtectionsPage() {
        PageFactory.initElements(driver, this);

    }
	
	// Get the text of the Protections page
    public String getPageHeading() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOf(protectionsPageHeader));
        System.out.println(elem.getText());
        return elem.getText();
    }

	public String getProtectionsName() {
		return driver.findElement(By.xpath("//td/a[contains(@href,'protections')]")).getText();
	}

	public boolean protectionsIsDisplayedViaSearch(WebElement e, String enterSearchName) {
		return e.getText().contains(enterSearchName);
	}

	public void waitProtectionsTableLoading() {
		wait.until(LoadingDisappear());
//		wait.pollingEvery(Duration.ofMillis(250)).until(LoadingDisappear());
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
//			if (columnName.contains("ID")) {
//				dataInEachColumn.add(Integer.parseInt(coulmnData.get(j).getText()));
//			}
////   	 else if(columnName.contains("Date")) {
////   		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
////   		 dataInEachColumn.add(dateFormatter.parse(coulmnData.get(j).getText()));
////   	 }
//			else {
//				dataInEachColumn.add(coulmnData.get(j).getText().toLowerCase());
//			}
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
	
	public WebElement getMenuItemIcon(String item) {
		return driver.findElement(By.xpath("//div[contains(text(), '"+item+"')]/i"));
	}

	public WebElement getMenuItem(String item) {
		return driver.findElement(By.xpath("//div[contains(text(), '"+item+"')]/parent::div"));
	}
	
	public WebElement getRemoveButton(String item) {
    	if(item.equals("Advertiser")){
    		return driver.findElement(By.xpath("//div[contains(text(), '"+item+"')]/parent::div/parent::div/span//button"));
		}else{
			return driver.findElement(By.xpath("//div[contains(text(), '"+item+"')]/following-sibling::button"));
		}

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
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[3]/a"))
				.getText();
	}

	public WebElement protectionsActive(int k) {
		return driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[5]"));
	}
}
