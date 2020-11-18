package RXPages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

	// Forwared button in the table
	String frwdButton = "//button[@type='button' and @aria-label='Next page']";

	// Publisher button
	@FindBy(xpath = "//header/div/div[1]/button[1]")
	WebElement createandeditPublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[2]")
	WebElement impersonatePublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[3]")
	WebElement disablePublisherBtn;

	// overview buttons
	@FindBy(xpath = "//button/span[text()='Edit Publisher']")
	public WebElement overviewEditbutton;
	@FindBy(xpath = "//button/span[text()='Deactivate Publisher']")
	public WebElement overviewDisablebutton;
	@FindBy(xpath = "//button/span[text()='Activate Publisher']")
	public WebElement overviewEnablebutton;
	@FindBy(xpath = "//div[contains(@class, 'toast-item')]//button[2]/span")
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
	String firstPubX = "//table/tbody/tr[";
	String secondPubX = "]/td[3]";

	// Some declarations
	int rownum = 1;
	int totRow = 0;
	WebDriverWait wait = new WebDriverWait(driver, 10000);
	Actions act = new Actions(driver);

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

	public void clickOverViewEnablebutton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewEnablebutton));
		if (overviewEnablebutton.isDisplayed()) {
			overviewEnablebutton.click();
		}
	}

	public void clickOverViewDisablebutton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(overviewDisablebutton));
		if (overviewDisablebutton.isDisplayed()) {
			overviewDisablebutton.click();
		}
	}
	public void clickCloseToastMessageButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(closeToastButton));
		if (closeToastButton.isDisplayed()) {
			closeToastButton.click();
		}
	}

}
