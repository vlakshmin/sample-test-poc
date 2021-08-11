package RXPages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class PublisherListPage extends RXBaseClass {
	// RX utitlity object.
	RXUtile rxUTL;
	// Publosher page objects.
	@FindBy(xpath = "//div[@class=\"logo\"]")
	WebElement loGo;

	// Xpath String of table row value object
	String firstUserXpath = "//table/tbody/tr[";
	String secondUserXpath = "]/td[";
	String thirdUserXpath = "]";

	// Xpath of rows to get the total number of row and column displayed in the
	// page.
	@FindAll(@FindBy(xpath = "//table/tbody/tr"))
	List<WebElement> userTableRows;
	@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td"))
	List<WebElement> userTableColmn;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr/td[5]"))
	public List<WebElement> statusColumnsPublisherTable;
	@FindAll(@FindBy(xpath = "//div[contains(@class,'vue-portal-target')]/button/span"))
	public List<WebElement> buttonsInPubPageHeader;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//thead/tr/th"))
	public List<WebElement> publisherTableHeaders;

	//Edit Publisher
	@FindBy(css = "div.v-toolbar__title")
	public WebElement pageTitle;
	@FindBy(xpath = "//aside/header/div/button")
	public WebElement closeEditPubtBtn;

	// Forwared button in the table
	String frwdButton = "//button[@type='button' and @aria-label='Next page']";

	// Publisher button
	@FindBy(xpath = "//header/div/div[1]/button[1]")
	WebElement createandeditPublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[2]")
	WebElement impersonatePublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[3]")
	WebElement disablePublisherBtn;
	@FindBy(xpath = "//span[text()='Create Publisher']/parent::button")
	public WebElement createPublisherBtn;

	// overview buttons
	@FindBy(xpath = "//span[text()='Edit Publisher']/parent::button")
	public WebElement overviewEditbutton;
	@FindBy(xpath = "//div[contains(@class, 'toast-wrapper')]//a[@class='remove']/i")
	public WebElement closeToastButton;
	
	// Account options
	@FindBy(xpath = "//div[text()='Publishers']")
	WebElement publisherOption;
	@FindBy(xpath = "//div[text()='Seats']")
	WebElement seatOption;
	@FindBy(xpath = "//div[text()='Users']")
	WebElement userOption;
	@FindBy(xpath = "//div[text()='Admin']")
	WebElement accountOption;

	// Publisher page heading
	@FindBy(xpath = "//h1[text()='Publishers']")
	WebElement headingOfPublisherPage;
	public String publisherHeader = "Publishers";

	//Create Publisher page
	@FindBy(xpath = "//div[contains(@class,'v-toolbar__title')]/div[contains(text(),'Create Publisher')]")
	public WebElement createPublisherHeader;
	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::input")
	public WebElement publisherNameInput;
	@FindBy(xpath = "//label[text()='Ad Ops Person']/following-sibling::input")
	public WebElement adOpsPersonInput;
	@FindBy(xpath = "//label[text()='Ad Ops Email']/following-sibling::input")
	public WebElement adOpsEmailInput;
	@FindBy(xpath = "//label[text()='Domain']/following-sibling::input")
	public WebElement domainInput;
	@FindBy(xpath = "//label[text()='Currency']/following-sibling::div[@class='v-input__append-inner']")
	public WebElement currencyDropdown;
	@FindBy(xpath = "//label[(text()='Categories')]/following-sibling::div[@class='v-select__selections']")
	public WebElement categoriesDropdown;
	@FindBy(xpath = "//label[text()='Demand Source']/following-sibling::div[@class='v-input__append-inner']")
	public WebElement demandSourceDropdown;
	@FindAll({@FindBy(xpath = "//label[text()='Demand Source']/following-sibling::div[@class='v-select__selections']/span/span")})
	public List<WebElement> demandSourceValues;
	@FindAll({@FindBy(xpath = "//div[contains(@role,'listbox') and contains(@class,'v-list')]/div")})
	public List<WebElement> dropdownValues;
	@FindBy(xpath = "//span[contains(text(),'Save Publisher')]/parent::button")
	public WebElement savePublisherBtn;
	@FindBy(xpath = "//label[text()='Active']/preceding-sibling::div/input")
	public WebElement activeCheckbox;
	@FindBy(xpath = "//label[text()='Active']/preceding-sibling::div")
	public WebElement activeToggleBtn;

	// Data for create or edit publisher.
	public String pubName = "Publisher Test";
	public String salesAcc = "Sales Account Test";
	public String pubEmail = "pubemail@emaitest.com";
	public String pubCorpName = "Publisher Corporation";
	public String pubWebSite = "corpwebsite";
	public String pubAdd = "Publisher Chicago home nuumber";
	public String pubCntry = "India";
	public String pubCurrency = "Dollars";
	public String pubzone = "UTC";

	// Publisher name for search
	@FindAll({@FindBy(xpath = "//table/tbody/tr/td[3]/a")})
	public List<WebElement> publisherColumns;
	@FindAll({@FindBy(xpath = "//table/tbody/tr")})
	public List<WebElement> publishersTableTrElemts;

	public String createPubString = "//div[contains(@class,'v-toolbar__title')]/div[contains(text(),'Create Publisher')]";
	public String trStringInPublishersTable = "//table/tbody/tr[%s]";
	public String checkboxColumnByRowNumber = "//table/tbody/tr[%s]/td[1]/div";
	public String pubNameColumnByRowNumber = "//table/tbody/tr[%s]/td[3]";
	public String categoriesValueString = "//div[contains(@class,'category-select-item')]/div/div/div[@class='v-list-item__content' and text()='%s']/preceding-sibling::div/div";
	public String demandSourceItemString = "//div[@role='listbox']/div/div/div[@class='v-list-item__title']";
	public String pubNameLinkStringInPubTable = "//table/tbody/tr/td/a[text()='%s']";
	public String activeColumnStringInPubTable = "//a[text()='%s']/parent::td/following-sibling::td[2]";
	public String idColumnByPubName = "//a[text()='%s']/parent::td/preceding-sibling::td[1]";
	public String pubNameColumnByID = "//td[text()='%s']/following-sibling::td[1]";
	public String adOpsPersonColumnByID = "//td[text()='%s']/following-sibling::td[6]";
	public String adOpsMailColumnByID = "//td[text()='%s']/following-sibling::td[7]";

	public String idColumnByRowNumber = "//div[@class='v-data-table__wrapper']//tbody/tr[%s]/td[2]";
	public String statusByIDInPubTable = "//td[text()='%s']/parent::tr/td[5]";

	public String activateInactivateBtnString = "//span[text()='%s']/parent::button";

	//validation errors
	@FindBy(css = "div.v-alert__content > div")
	public WebElement validationErrorsPanel;
	public String validationErrorsCssPath = "div.v-alert__content > div > ul > li";

	public String loadingXpathString = "//main//div[@class='container container--fluid']//table/thead[2]";

	// Some declarations
	int rownum = 1;
	int totRow = 0;
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions act = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// Initialize page factory
	public PublisherListPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
	}

	// return true/false based on image displayed or not.
	public boolean logodisplayed() {

		return loGo.isDisplayed();
	}

	// Click on Save or Update button
	public void clickAccount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(accountOption));
		if (accountOption.isDisplayed()) {
			accountOption.click();
		}

	}

	// Is option publisher displayed.
	public boolean isPublisherDisplayed() {
		return (publisherOption.isDisplayed());

	}

	// Click on publisher displayed.
	public void clickPublisherOption() {
		publisherOption.click();

	}

	// is option seats displayed.
	public boolean isSeatsDisplayed() {
		return (seatOption.isDisplayed());

	}

	// is option User displayed.
	public boolean isUserDisplayed() {
		return (userOption.isDisplayed());

	}

	// Get the header of publisher page .
	public String getHeaderOfpublisherPageDispl() {
		return (headingOfPublisherPage.getText());

	}

	// Select the publisher and return
	public int selectpublisherAndReturnId(String tmpStr)

	{

		return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath,
				secondUserXpath, thirdUserXpath, frwdButton, 5, tmpStr);

	}

	public void updateThePublisherIdInTestData(int newValue) {
		rxUTL.updateTestData("PublisherId", Integer.toString(newValue));

	}

	// get the publisher and return the id from properties file
	public int getThePublisherIdInTestData(String newValue) {
		return Integer.parseInt(prop.getProperty("PublisherId"));

	}

	// Wait for page header display WebElement webele
	public int selectpublisherforEdit(String pubId)

	{

		return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath,
				secondUserXpath, thirdUserXpath, frwdButton, 2, pubId);

	}

	public void clickOverViewEditbutton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewEditbutton));
		if (overviewEditbutton.isDisplayed()) {
			overviewEditbutton.click();
		}
	}
	public void clickCloseToastMessageButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(closeToastButton));
		if (closeToastButton.isDisplayed()) {
			closeToastButton.click();
		}
	}

	public void selectValueFromDropdown(String name) {
		int attempt = 0;

		// Check if list contains parameter name, scroll down if not
		do {
			js.executeScript("arguments[0].scrollIntoView(false)", dropdownValues.get(dropdownValues.size() - 1));
		}
		while (!dropdownValues.stream()
				.map(WebElement::getText)
				.anyMatch(text -> name.equals(text)) && attempt++ < 20);

		// Get web element by name from the method parameter
		WebElement dropDownElementByName = dropdownValues.stream()
				.filter(i -> i.getText().equalsIgnoreCase(name))
				.findFirst()
				.orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(String.format("The option %s wasn't found.", name)));
		js.executeScript("arguments[0].scrollIntoView({block: \"center\"})", dropDownElementByName);
		wait.until(elementToBeClickable(dropDownElementByName));
		dropDownElementByName.click();
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
		String classAttr = driver.findElement(By.xpath(String.format(trStringInPublishersTable, rowNum))).getAttribute("class");
//		System.out.println("tr class atrribute >>> " + classAttr);
		if(classAttr.contains("selected")){
			flag = true;
		}
		return flag;
	}

	public List<WebElement> getDemandSourceDropdownItem(){
		return driver.findElements(By.xpath(demandSourceItemString));
	}

	public void scrollDownInDropdown(){
		int attempt = 0;
		do {
			js.executeScript("arguments[0].scrollIntoView(false)", dropdownValues.get(dropdownValues.size() - 1));
		}
		while (attempt++ < 20);
	}

	public List<String> getTheSelectedDemandSource(){
		List<String> selectedValueList = new ArrayList<String>();
		String selectedValue = "";
//		System.out.println("pubListPgs.demandSourceValues.size() >>> " + this.demandSourceValues.size());
		for(WebElement valueElemt : this.demandSourceValues){
			selectedValue = valueElemt.getText().trim();
//			System.out.println("valueElemt.getText().trim() >>> " + selectedValue);
			selectedValueList.add(selectedValue);
		}
		return selectedValueList;
	}

	public boolean checkIfErrorIsDisplayed(String error){
		boolean flag = false;
//		System.out.println("Check if the expected error displays === " + error);
		for(WebElement elemt : driver.findElements(By.cssSelector(validationErrorsCssPath))){
//			System.out.println("validation error >>> " + elemt.getText().trim());
			if(elemt.getText().trim().equals(error)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean verifyButtonDisplaysInHeaderOfMediaPage(String btnName){
		boolean flag = false;
		String button;
//		System.out.println("Check if button exist >>> " + btnName);
		for(WebElement btnElemt : this.buttonsInPubPageHeader){
			button = btnElemt.getText().trim();
//			System.out.println("btnElemt.getText().trim() >>> " + button);
			if(button.equalsIgnoreCase(btnName)){
				flag = true;
				break;
			}
		}
		return  flag;
	}

	public boolean verifyHeaderDisplayInPublisherOverviewPage(String expectedHeader){
		boolean flag = false;
		String actualHeader = "";
//		System.out.println("Check if expected header exists ==== " + expectedHeader);
		for(WebElement headerElemt : this.publisherTableHeaders){
			actualHeader = headerElemt.getText().trim();
//			System.out.println("headerElemt.getText().trim() >>> " + actualHeader);
			if(expectedHeader.equals(actualHeader)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public WebElement getElementByXpathWithParameter(String xpath, String parameter) {
		return driver.findElement(By.xpath(String.format(xpath, parameter)));
	}
}
