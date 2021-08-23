package RXPages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXTargetingPage extends RXBasePage  {
	//Utility object
	RXUtile rxUTL ;
	PublisherListPage pubPage;
	public String targetingHeaderStr="Targeting Rules";

	
	
	//Seats page heading
		@FindBy(xpath = "//h1[text()='Targeting Rules']")  WebElement targetingPageHeader;
	//Xpath of rows to get the total number of row and column displayed in the page.
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> targetingTableRows;
    @FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td")) 
    public List<WebElement> targetingTableColumns;
	@FindBy(xpath = "//aside/header/div/button")
	public WebElement closeBtn;
	@FindBy(xpath = "//span[text()='Create Rule']/parent::button")
	public WebElement createRuleBtn;
	
	
	@FindBy(xpath = "//div[contains(@class, 'v-list-item__title') and text()='Targeting']")
	WebElement targetingNav;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']")
	public WebElement publisherNameDropDown;
	@FindBy(xpath = "//label[text()='Rule Name']/following-sibling::input")
	public WebElement ruleNameField;
	@FindBy(xpath = "//h2[text() = 'Targeting']/following-sibling::span//button")
	public WebElement addTargetingButton;
	@FindBy(xpath = "//div[contains(@class, 'v-toolbar__title') and text()='Advertisers']")
	public WebElement advertisersPopup;
	@FindBy(xpath = "//div[contains(@class, 'v-toolbar__title') and text()='Advertisers']/preceding-sibling::button")
	public WebElement closeAdvertisersPopup;
	@FindBy(xpath = "//div[contains(@class,'v-banner__text') and contains(text(),'Changing the Publisher')]" )
	public WebElement changePublisherBannerMsg;
	@FindBy(xpath = "//div[contains(@class,'v-tabs-bar__content')][.//span[contains(text(),'Geography')]]" )
	public WebElement protectSpecificInventoryPopup;

	public String changePubBannerMsgXpath = "//div[contains(@class,'v-banner__text') and contains(text(),'%s')]";
	public String checkboxInInventoryXpath = "//label[text()='%s']/preceding-sibling::div";
	public String checkboxDivInInventoryXpath = "//label[text()='%s']/parent::div";
	public String selectRowValueInProtectSpecificInventoryPopup = "//tr[@class='select-row'][.//div[@title='%s']]";
	public String includedValueInProtectSpecificInventoryPopup = "//table[contains(@class,'included-table')][.//div[@title='%s']]";
	public String createOrEditRuleDialogPopup = "//aside[@class='dialog']";
	public String editRuleDialogHeader = "//aside[@class='dialog']/header//div[contains(text(),'%s')]";
	
	//Action object
	Actions act = new Actions(driver);
	
	//Array for test data 
	static ArrayList<String> testData = new ArrayList<String>();

	
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
	// Initialize page factory
	public RXTargetingPage()
	{
	PageFactory.initElements(driver, this);
	rxUTL=new RXUtile();
	pubPage=new PublisherListPage();
	
	}
	
	//Get the text of the Seats page
	public String getPageHeading()
		{
			
			WebElement elem = wait.until(ExpectedConditions.visibilityOf(targetingPageHeader));
//			System.out.println(elem.getText());
			return elem.getText();
			
		}
	public void clickTaretingNavMenu() {
		if(targetingNav.isDisplayed()) {
			targetingNav.click();
		}
	}

	public void selectAdvertiser(String name) {
		driver.findElement(By.xpath("//div[contains(text() , '" + name + "')]/ancestor::tbody/tr")).click();
//		driver.findElement(By.xpath("//div[@class='v-dialog v-dialog--active']//table[@class='select-table fixed']/tbody/tr")).click();
	}

	public WebElement advertiserIncludedListItem(String itemName) {
		return driver.findElement(By.xpath("//div[contains(@class , 'pane results')]//div[contains(text() , '" + itemName + "')]"));
	}


	public WebElement ruleCheckBox(int k) {
		return driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[1]"));
	}

	public String ruleName(int k) {
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[2]/a"))
				.getText();
	}

	public List<WebElement> ruleNames() {
		return driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[2]/a"));
	}

	public WebElement toolbarButton(String e) {
		return driver
				.findElement(By.xpath("//span[contains(text() , '" + e + "')]/parent::button"));
	}

	public WebElement ruleActive(int k) {
		return driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[3]"));
	}

	public List<WebElement> ruleActives() {
		return driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[3]"));
	}

	public WebElement getElementByXpathWithParameter(String xpath, String parameter){
		return driver.findElement(By.xpath(String.format(xpath, parameter)));
	}

	public String getChangePublisherBannerMsg()
	{
		wait.until(ExpectedConditions.visibilityOf(changePublisherBannerMsg));
		return changePublisherBannerMsg.getText().replaceAll("\u3000", "");
	}
}
