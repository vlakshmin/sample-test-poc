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

public class RXTargetingPage extends RXBaseClass  {
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
	
	
	
	
	@FindBy(xpath = "//div[contains(@class, 'v-list-item__title') and text()='Targeting']")  WebElement targetingNav;
	@FindBy(xpath = "//div[@class='v-data-footer__select']//div[@class='v-input__icon v-input__icon--append']")  WebElement rowsPerPageDropDown;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-after']/button")  public WebElement nextPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-before']/button") public WebElement previousPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__pagination']")  WebElement paginationCount;
	
	
	
	
	
	
	//Action object
	Actions act = new Actions(driver);
	
	//Array for test data 
	static ArrayList<String> testData = new ArrayList<String>();

	
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 1000);
	
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
			System.out.println(elem.getText());
			return elem.getText();
			
		}
	public void clickTaretingNavigation() {
		if(targetingNav.isDisplayed()) {
			targetingNav.click();
		}
	}
	
	public void clickNextPageNavigation() {
		if(nextPageNavButton.isDisplayed()) {
			nextPageNavButton.click();
		}
	}
	public void clickPreviousPageNavigation() {
		if(previousPageNavButton.isDisplayed()) {
			previousPageNavButton.click();
		}
	}
	public void clickNoOfPagesDropDown() {
		if(rowsPerPageDropDown.isDisplayed()) {
			rowsPerPageDropDown.click();
		}
	}
	
	public String getPaginationText() {
		String paginationText = null;
		if(paginationCount.isDisplayed()) {
			paginationText = paginationCount.getText();
		}
		return paginationText;
	}
	
}
