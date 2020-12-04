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

	
}
