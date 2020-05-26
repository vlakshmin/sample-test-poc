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

public class RXSeatsPage extends RXBaseClass  {
	//Utility object
	RXUtile rxUTL ;
	PublisherListPage pubPage;
	
	//Seats page heading
	@FindBy(xpath = "//h1[text()='Seats']")  WebElement seatPageHeader;
	//Search text field
	@FindBy(xpath = "//input[@autocomplete='nope']")  WebElement searchField;
	//Tabs of seats page
	@FindBy(xpath = "//span[text()='Active']")  WebElement activeTab;
	@FindBy(xpath = "//span[text()='Inactive']")  WebElement inActiveTab;
	@FindBy(xpath = "//span[text()='Paused']")  WebElement pausedTab;
	
	//Xpath of rows to get the total number of row and column displayed in the page.
	@FindAll(@FindBy(xpath = "//table/tbody/tr")) List<WebElement> seattableRows;
	@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td")) List<WebElement> seattableColmn;
	
	
	//Create seat form objects
	@FindBy(xpath = "//span/form/div[2]/div[1]/div/span/div/div/div[1]/div/input")  WebElement buyerName;
	@FindBy(xpath = "//span/form/div[2]/div[2]/div/div/div/div[1]/div[1]/input[1]")  WebElement buyerType;
	@FindBy(xpath = "//span/form/div[2]/div[3]/div/div/div/div[1]/div/div[1]/div/div")  WebElement statusActive;
	@FindBy(xpath = "//span/form/div[2]/div[3]/div/div/div/div[1]/div/div[2]/div/div")  WebElement statusInActive;
	@FindBy(xpath = "//span/form/div[2]/div[3]/div/div/div/div[1]/div/div[3]/div/div")  WebElement statusDisabled;
	@FindBy(xpath = "//span/form/div[2]/div[4]/div[1]/div/div/div[1]/div[1]/input[1]")  WebElement enterDSP;
	@FindBy(xpath = "//span/form/div[2]/div[4]/div[2]/div/div/div[1]/div/input")  WebElement enterToken;
	@FindBy(xpath = "//span/form/div[2]/div[5]/div/div/div/div[1]/div/input")  WebElement corporationName;
	@FindBy(xpath = "//span/form/div[2]/div[6]/div/div/div/div[1]/div/input")  WebElement enterWebsite;
	@FindBy(xpath = "//span/form/div[2]/div[7]/div/div/div/div[1]/div/input")  WebElement enterAddressOne;
	@FindBy(xpath = "//span/form/div[2]/div[8]/div/div/div/div[1]/div/input")  WebElement enterAddressTwo;
	@FindBy(xpath = "//span/form/div[2]/div[9]/div/div/div/div[1]/div/input")  WebElement enterCity;
	@FindBy(xpath = "//span/form/div[2]/div[10]/div[1]/div/div/div[1]/div/input")  WebElement postalCode;
	@FindBy(xpath = "//span/form/div[2]/div[10]/div[2]/div/div/div[1]/div[1]/input[1]")  WebElement enterCountry;
	@FindBy(xpath = "//span/form/div[2]/div[10]/div[3]/span/div/div/div[1]/div[1]/input[1]")  WebElement enterCurrency;
	@FindBy(xpath = "//span/form/div[2]/div[11]/div/div/div/div[1]/div/input")  WebElement enterAuthorId;
	@FindBy(xpath = "//span/form/div[2]/div[12]/div/div/div/div[1]/div/input")  WebElement contactEmail;
	@FindBy(xpath = "//span/form/div[3]/button/span")  WebElement saveButton;
	@FindBy(xpath = "//aside/header/div/button/span/i")  WebElement closeBtn;
	
	
	
	//Xpath String of Seat table row value object
	String firstSeatXpath="//table/tbody/tr[";
	String secondSeatXpath="]/td[";
	String thirdSeatXpath="]";
	
	// Buttons on the seats page
	String seatBtn="//header/div/div[1]/button";

	//Forwared button in the table
	String frwdButton="//*[@id=\"app\"]/div/main/div/div/div/div[2]/div/div[2]/div[4]/button/span/i";
	
	//Constant data in Seats page
	public String SeatsHeaderStr="Seats";
	
	//Action object
	Actions act = new Actions(driver);
	
	//Array for test data 
	ArrayList<String> testData = new ArrayList<String>();
	
	//Seat list Objects
	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/span/a")  WebElement buyerSeatName;
	@FindBy(xpath = "//table/tbody/tr[1]/td[2]")  WebElement buyerSeatId;
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")  WebElement buyerSeatCheckBox;
	
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 10000);
	
	//Test data for creating seat
	String byName ="Test Seat";
	String byType = "Merchant";
	String eterDSP = "Taboola";
	String eterToken = "tok";
	String corpoName = "TestingCorpor";
	String eterWebsite = "testingwebsite";
	String eterAddressOne = "Address 1 MG Road";
	String eterAddressTwo = "Address 2 main road"; 
	String eterCity = "Bangalore";
	String poCode = "24";
	String eterCountry = "India";
	String eterCurrency = "Dollars";
	String eterAuthorId = "Auth";
	String ctactEmail = "testingemail";
			
	//Some initialization.
	static int sid=0;
	
	// Initialize page factory
	public RXSeatsPage()
	{
	PageFactory.initElements(driver, this);
	rxUTL=new RXUtile();
	pubPage=new PublisherListPage();
	
	}
	
	
	//Get the text of the Seats page
	public String getPageHeading()
	{
		
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
		System.out.println(elem.getText());
		return elem.getText();
		
	}
	
	//Click on Seats Option under the account option.
	public void clickOnSeatOptionUnAcc()
	{
		if(pubPage.isSeatsDisplayed())
		{
			pubPage.seatOption.click();
		}else
		{
			pubPage.accountOption.click();
			pubPage.seatOption.click();
			
		}

	}
	//Click on Tab - Active, Inactive and Paused.
	public void clickTabActiveInactivePaused(String tabName)
	{
		if(tabName.equalsIgnoreCase("Active"))
		{
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			activeTab.click();
		}else if(tabName.equalsIgnoreCase("Inactive"))
			
		{
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			inActiveTab.click();
		}	
		else if(tabName.equalsIgnoreCase("Paused"))
		{
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			pausedTab.click();
		}	
			
	}
	
	//Get the seats name for search.		
		public String getSeatNameForSearch() {
			
			WebElement elem = wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			String strSrch=firstSeatXpath+seattableRows.size()+secondSeatXpath+3+thirdSeatXpath;
			WebElement webEl= driver.findElement(By.xpath(strSrch));
			return webEl.getText();

		}

		////Get the seats name for search.		
		public void seatNameSearch(String srchSeat) {

			searchField.sendKeys(srchSeat);

		}
		
		//Click on Buttons on the seat page.
		public void clickButtonCreateEditActiveDeactivePaused(String buttonName) throws InterruptedException
		{
			if(buttonName.equalsIgnoreCase("Create")| buttonName.equalsIgnoreCase("Edit") )
			{
				
				String btnStr=seatBtn+"["+1+"]";
				WebElement btnWebEl= driver.findElement(By.xpath(btnStr));
				WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(btnWebEl));
				btnElem.click();
				
			}else if(buttonName.equalsIgnoreCase("Deactivate")| buttonName.equalsIgnoreCase("Activate"))
			{
				
				String btnStr=seatBtn+"["+2+"]";
				WebElement btnWebEl= driver.findElement(By.xpath(btnStr));
				WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(btnWebEl));
				btnElem.click();
				Thread.sleep(10000);
			}	
			else if(buttonName.equalsIgnoreCase("Paused")| buttonName.equalsIgnoreCase("DeactivateFromPaused"))
			{
				String btnStr=seatBtn+"["+3+"]";
				WebElement btnWebEl= driver.findElement(By.xpath(btnStr));
				WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(btnWebEl));
				btnElem.click();
				Thread.sleep(10000);
			}	
				
		}
		
	////Get the get particular row data of a seat table.		
		public ArrayList<WebElement> getParticularSeatRowData(int stId) {
			WebElement elem = wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			String strSrch=firstSeatXpath+seattableRows.size()+secondSeatXpath+3+thirdSeatXpath;
			return rxUTL.getParticularRowData(stId, 
					seattableRows.size(), seattableColmn.size(), firstSeatXpath, secondSeatXpath, thirdSeatXpath, frwdButton);

			}
		
	//Select the seat for modifyi.Active, Deactivate,Pause.		
			public int selectSeatandRetunID() {
				WebElement elem = wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
				String strSrch="";
				String checkBox="";
				try {
					strSrch=firstSeatXpath+seattableRows.size()+secondSeatXpath+2+thirdSeatXpath;
					checkBox=firstSeatXpath+seattableRows.size()+secondSeatXpath+1+thirdSeatXpath;
					} 
					catch (NullPointerException e) 
					{
						System.out.println("There is no seats available in the seat page");
						
						e.printStackTrace();
					}catch (org.openqa.selenium.NoSuchElementException e) 
					{
						System.out.println("There is no seats available in the seat page");
						
						e.printStackTrace();
					}
				
				driver.findElement(By.xpath(checkBox)).click();
				return Integer.parseInt(driver.findElement(By.xpath(strSrch)).getText());
				
			}
				
			//Fill the create seat form
			public void enterVlauesToCreateSeats(String buyName,String buyType,String stsActInActDisabled,String entDSP,String entToken,String corpName,String entWebsite,String entAddressOne,String entAddressTwo,String entCity,String postCode,String entCountry,String entCurrency,String entAuthorId,String contEmail)
			{
				buyerName.sendKeys(buyName);
				buyerType.sendKeys(buyType);
				act.sendKeys(Keys.TAB).build().perform();
				if(stsActInActDisabled.equalsIgnoreCase("Active"))
				{
					statusActive.click();
				}else if(stsActInActDisabled.equalsIgnoreCase("Inactive"))
				{
					statusInActive.click();
				}else if(stsActInActDisabled.equalsIgnoreCase("Disabled"))
				{
					statusDisabled.click();
				}
				
				enterDSP.sendKeys(entDSP);
				act.sendKeys(Keys.TAB).build().perform();
				enterToken.sendKeys(entToken);
				corporationName.sendKeys(corpName);
				enterWebsite.sendKeys(entWebsite);
				enterAddressOne.sendKeys(entAddressOne);
				enterAddressTwo.sendKeys(entAddressTwo);
				enterCity.sendKeys(entCity);
				postalCode.sendKeys(postCode);
				enterCountry.sendKeys(entCountry);
				act.sendKeys(Keys.TAB).build().perform();
				enterCurrency.sendKeys(entCurrency);
				act.sendKeys(Keys.TAB).build().perform();
				enterAuthorId.sendKeys(entAuthorId);
				contactEmail.sendKeys(contEmail);
				
			}
			
			//Click in save button in the create seat form
			public String clkSave() {
				saveButton.click();
				WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
				return saveClick.getText();

			}
			
			//Close the create seat page and return the seat name
			public String closeCreateSeatPage() {
				String byerName=buyerName.getAttribute("value");
				closeBtn.click();
				return byerName;

			}
			
			
			//Provide Test data 
			public ArrayList<String> getTestData()
			{
				int rnNum=RXUtile.getRandomNumberFourDigit();
				testData.add(byName + rnNum );
				testData.add(byType + rnNum);
				testData.add(eterDSP + rnNum );
				testData.add(eterToken + rnNum );
				testData.add(corpoName + rnNum );
				testData.add(eterWebsite + rnNum );
				testData.add(eterAddressOne + rnNum );
				testData.add(eterAddressTwo + rnNum );
				testData.add(eterCity + rnNum );
				testData.add(poCode + rnNum );
				testData.add(eterCountry + rnNum );
				testData.add(eterCurrency + rnNum );
				testData.add(eterAuthorId + rnNum );
				testData.add(ctactEmail + rnNum );
			
				return testData;
				
				
				
			}
			
			//Close the create seat page and return the seat name
			public int getTheSeatIdandClickCheckBox(String bname) {
				
				Boolean elem = wait.until(ExpectedConditions.textToBePresentInElement(buyerSeatName, bname));
				if(elem)
				{
					buyerSeatCheckBox.click();
					sid=Integer.parseInt(buyerSeatId.getText());
				}
				return sid;

			}
			

}
