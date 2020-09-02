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

public class RXAdspotsPage extends RXBaseClass  {
	//Utility object
	RXUtile rxUTL ;
	PublisherListPage pubPage;
	public String adspotsHeaderStr="AdSpots";
	
	
	//Seats page heading
		@FindBy(xpath = "//h1[text()='AdSpots']")  WebElement adspotsPageHeader;
	//Xpath of rows to get the total number of row and column displayed in the page.
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> adspotsTableRows;
    @FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td")) 
    public List<WebElement> adspotsTableColumns;
	
	
	
	//Action object
	Actions act = new Actions(driver);
	
	//Array for test data 
	static ArrayList<String> testData = new ArrayList<String>();

	
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 1000);
	
	// Initialize page factory
	public RXAdspotsPage()
	{
	PageFactory.initElements(driver, this);
	rxUTL=new RXUtile();
	pubPage=new PublisherListPage();
	
	}
	
	//Get the text of the media page
	public String getPageHeading()
		{
			
			WebElement elem = wait.until(ExpectedConditions.visibilityOf(adspotsPageHeader));
			System.out.println(elem.getText());
			return elem.getText();
			
		}
	
	
	
}
