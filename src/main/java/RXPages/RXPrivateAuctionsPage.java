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

public class RXPrivateAuctionsPage extends RXBaseClass {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	RXDashboardPage dashboardPage;
	public String auctionHeaderStr = "Private Auctions";

	// Seats page heading
	@FindBy(xpath = "//h1[text()='Private Auctions']")
	WebElement auctionPageHeader;
	
	// overview buttons
		@FindBy(xpath = "//button/span[text()='Edit Private Auction']")
		public WebElement overviewEditbutton;
		@FindBy(xpath = "//button/span[text()='Deactivate Private Auction']")
		public WebElement overviewDisablebutton;
		@FindBy(xpath = "//div[@class='portal vue-portal-target']/button[2]/span")
		public WebElement overviewEnablebutton;
		@FindBy(xpath = "//label[text()='Name']/following-sibling::input")
		public WebElement auctionNameField;
		@FindBy(xpath = "//label[text()='Related Packages']/following-sibling::input")
		public WebElement auctionPackages;
		@FindBy(xpath = "//button[@type='submit'][1]")
		public WebElement saveandcloseButton;
		@FindBy(xpath = "//button[@type='submit'][2]")
		public WebElement saveandcreatedealButton;
		@FindBy(xpath = "//label[contains(text(), 'Date Range')]/ancestor::div[@class = 'v-input__slot']")
	    public WebElement dateRangeContainer;
		@FindBy(xpath = "//label[contains(text(), 'Date Range')]/following-sibling::input")
	    public WebElement dateInput;
		
	// Action object
	Actions act = new Actions(driver);

	// Array for test data
	static ArrayList<String> testData = new ArrayList<String>();

	// Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 1000);

	// Initialize page factory
	public RXPrivateAuctionsPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
		pubPage = new PublisherListPage();
		dashboardPage = new RXDashboardPage();

	}

	// Get the text of the media page
	public String getPageHeading() {

		WebElement elem = wait.until(ExpectedConditions.visibilityOf(auctionPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

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
		String enableText = overviewEnablebutton.getText().replaceAll("\\s", "");
		if (enableText.equals("ACTIVATEPRIVATEAUCTION")) {
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
	
	public void selectFifteenDaysRangeInNextMonth() throws InterruptedException {
        openDatePicker();
        selectNextMonth();
        dashboardPage.selectSpecificDateRange("1", "15");
        dateRangeContainer.click();
    }

	 public void selectNextMonth() throws InterruptedException {
	        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.nextMonthBtn));
	        dashboardPage.nextMonthBtn.click();
	        Thread.sleep(3000);
	        
	    }
	 public void openDatePicker() {
	        driverWait().until(ExpectedConditions.visibilityOf(dateInput));
	        dateRangeContainer.click();
	        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.previousMonthBtn));
	    }
}
