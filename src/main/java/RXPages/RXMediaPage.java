package RXPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class RXMediaPage extends RXBasePage {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	public String mediaHeaderStr = "Media";
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// Seats page heading
	@FindBy(xpath = "//h1[text()='Media']")
	public WebElement mediaPageHeader;
	@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]")
	public WebElement noDataAvailable;
	@FindBy(xpath = "//label[text()='Publisher']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement publisherNameField;
	// Xpath of rows to get the total number of row and column displayed in the
	// page.
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> mediaTableRows;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td"))
	public List<WebElement> mediaTableColumns;
	@FindAll(@FindBy(xpath = "//div[contains(@class,'vue-portal-target')]/button/span"))
	public List<WebElement> buttonsInMediaPageHeader;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr/td[6]"))
	public List<WebElement> statusColumnsMediaTable;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr/td[4]"))
	public List<WebElement> publisherColumnsMediaTable;
	@FindBy(xpath = "//label[text()='Search']/following-sibling::input")
	public WebElement searchInput;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//thead/tr/th"))
	public List<WebElement> mediaTableHeaders;

	public String checkboxStringInMediaTable = "//div[@class='v-data-table__wrapper']//tbody/tr[%s]/td[1]/div";
	public String idStringInMediaTable = "//div[@class='v-data-table__wrapper']//tbody/tr[%s]/td[2]";
	public String trStringInMediaTable = "//div[@class='v-data-table__wrapper']//tbody/tr[%s]";
	public String statusByIDInMediaTable = "//td[text()='%s']/parent::tr/td[6]";
	public String statusByMediaNameInMediaTable = "//a[text()='%s']/parent::td/following-sibling::td[3]";


	// overview buttons
	@FindBy(xpath = "//button/span[text()='Edit Media']")
	public WebElement overviewEditbutton;
	@FindBy(xpath = "//span[text()='Deactivate Media']/parent::button")
	public WebElement overviewDisablebutton;
	@FindBy(xpath = "//span[text()='Activate Media']/parent::button")
	public WebElement overviewEnablebutton;

	//Create Media page
	@FindBy(xpath = "//span[text()='Create Media']/parent::button")
	public WebElement createMediaBtn;
	@FindBy(xpath = "//div[contains(@class,'v-toolbar__title')]/div[text()='Create Media']")
	public WebElement createMediaHeader;
	@FindBy(xpath = "//label[contains(@for,'input') and contains(.,'Publisher')]/following-sibling::div[@class='v-select__selections']")
	public WebElement publisherNameDropdown;
	@FindBy(xpath = "//label[contains(@for,'input') and contains(.,'Publisher')]/following-sibling::div[@class='v-select__selections']/input")
	public WebElement publisherNameInput;
	@FindBy(xpath = "//label[text()='Active']/preceding-sibling::div/input")
	public WebElement activeCheckbox;
	@FindAll({@FindBy(xpath = "//div[contains(@role,'listbox') and contains(@class,'v-list')]/div")})
	public List<WebElement> dropdownValues;
	@FindBy(xpath = "//label[text()='Media Name']/following-sibling::input")
	public WebElement mediaNameInput;
	@FindBy(xpath = "//label[text()='Media Type']/following-sibling::div[@class='v-select__selections']")
	public WebElement mediaTypeDropdown;
	@FindBy(xpath = "//label[text()='Media Type']/following-sibling::div[@class='v-select__selections']/input")
	public WebElement mediaTypeInput;
	@FindBy(xpath = "//label[contains(text(),' URL')]/following-sibling::input")
	public WebElement siteURLInput;
	@FindBy(xpath = "//label[text()='Categories']/following-sibling::div[@class='v-select__selections']")
	public WebElement categoriesDropdown;
	@FindBy(xpath = "//label[text()='Categories']/following-sibling::div[@class='v-select__selections']/span")
	public WebElement categoriesValue;
	@FindBy(xpath = "//label[text()='Categories']/following-sibling::div[@class='v-select__selections']/input")
	public WebElement categoriesInput;
	@FindBy(xpath = "//div[contains(@class,'v-banner__text') and contains(text(),'changing the Publisher')]" )
	public WebElement changePublisherBannerMsg;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	//validation errors
	@FindBy(css = "div.v-alert__content > div")
	public WebElement validationErrorsPanel;

	public String validationErrorsCssPath = "div.v-alert__content > div > ul > li";
	public String changePubBannerMsgXpath = "//div[contains(@class,'v-banner__text') and contains(text(),'%s')]";

	//Edit Media
	@FindBy(css = "div.v-toolbar__title")
	public WebElement pageTitle;
	@FindBy(xpath = "//aside/header/div/button")
	public WebElement closeBtn;

	// Action object
	Actions act = new Actions(driver);

	// Array for test data
	static ArrayList<String> testData = new ArrayList<String>();

	// Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 50);

	// Initialize page factory
	public RXMediaPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
		pubPage = new PublisherListPage();

	}

	// Get the text of the media page
	public String getPageHeading() {

		WebElement elem = wait.until(ExpectedConditions.visibilityOf(mediaPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

	}

	public void clickOverViewEditbutton() {
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewEditbutton));
		if (overviewEditbutton.isDisplayed()) {
			overviewEditbutton.click();
		}
	}

	public void clickOverViewEnablebutton() {
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewEnablebutton));
		if (overviewEnablebutton.isDisplayed()) {
			overviewEnablebutton.click();
		}
	}

	public void clickOverViewDisablebutton() {
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewDisablebutton));
		if (overviewDisablebutton.isDisplayed()) {
			overviewDisablebutton.click();
		}
	}

	public WebElement getCategoriesDropdownCheckbox(String categoriesName){
		return driver.findElement(By.xpath("//div[contains(@class,'category-select-item')]/div/div/div[@class='v-list-item__content' and text()='"+ categoriesName +"']/preceding-sibling::div/div"));
	}

	public String getChangePublisherBannerMsg()
	{
		wait.until(ExpectedConditions.visibilityOf(changePublisherBannerMsg));
		return changePublisherBannerMsg.getText().replaceAll("\u3000", "");
	}

	public boolean checkIfErrorIsDisplayed(String error){
		boolean flag = false;
		System.out.println("expected error === " + error);
		for(WebElement elemt : driver.findElements(By.cssSelector(this.validationErrorsCssPath))){
			System.out.println("validation error >>> " + elemt.getText().trim());
			if(elemt.getText().trim().equals(error)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean isElementPresent(String path){
		try{
			driver.findElement(By.cssSelector(path));
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean verifyIfCheckboxIsChecked(int rowNum){
		boolean flag = false;
		String classAttr = driver.findElement(By.xpath(String.format(trStringInMediaTable, rowNum))).getAttribute("class");
		System.out.println("tr class atrribute >>> " + classAttr);
		if(classAttr.contains("selected")){
			flag = true;
		}
		return flag;
	}

	public boolean verifyButtonDisplaysInHeaderOfMediaPage(String btnName){
		boolean flag = false;
		String button;
		for(WebElement btnElemt : this.buttonsInMediaPageHeader){
			button = btnElemt.getText().trim();
			System.out.println("btnElemt.getText().trim() >>> " + button);
			if(button.equalsIgnoreCase(btnName)){
				flag = true;
				break;
			}
		}
		return  flag;
	}

	public boolean verifyHeaderDisplayInMediaTable(String expectedHeader){
		boolean flag = false;
		String actualHeader = "";
		System.out.println("Check if expected header exists ==== " + expectedHeader);
		for(WebElement headerElemt : this.mediaTableHeaders){
			actualHeader = headerElemt.getText().trim();
			System.out.println("headerElemt.getText().trim() >>> " + actualHeader);
			if(expectedHeader.equals(actualHeader)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public String mandatorFieldIsDisabledForCreatePage(String fieldName) {
		return driver
				.findElement(By.xpath("//aside[@class='dialog']//label[text()='" + fieldName + "']"))
				.getAttribute("class");
	}

	public String getMediaName() {
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]/a"))
				.getText().replaceAll("\\s", "");
	}

	public String getPublisherName() {
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[4]"))
				.getText();
	}

	public String getMediaType() {
		return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[5]"))
				.getText();
	}

	public WebElement getElementByXpathWithParameter(String xpath, String parameter){
		return driver.findElement(By.xpath(String.format(xpath, parameter)));
	}
}
