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
	
	
	//Xpath String of table row value object
		String firstUserXpath="//table/tbody/tr[";
		String secondUserXpath="]/td[";
		String thirdUserXpath="]";

		//Xpath of rows to get the total number of row and column displayed in the page.
		@FindAll(@FindBy(xpath = "//table/tbody/tr")) List<WebElement> userTableRows;
		@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td")) List<WebElement> userTableColmn;
		
	
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
	public @FindBy(xpath = "//span/form/div[2]/div[11]/div/div/div/div[1]/div/input")  WebElement enterAuthorId;
	@FindBy(xpath = "//span/form/div[2]/div[12]/div/div/div/div[1]/div/input")  WebElement contactEmail;
	@FindBy(xpath = "//span/form/div[3]/button/span")  WebElement saveButton;
	@FindBy(xpath = "//aside/header/div/button/span/i")  WebElement closeBtn;
	
	// Xpath of status to check whether slected or not selected
	
	@FindBy(xpath = "//aside/div/span/form/div[2]/div[3]/div/div/div/div[1]/div/div[1]/div/input")  WebElement statActive;
	@FindBy(xpath = "//aside/div/span/form/div[2]/div[3]/div/div/div/div[1]/div/div[2]/div/input")  WebElement statInActive;
	@FindBy(xpath = "//aside/div/span/form/div[2]/div[3]/div/div/div/div[1]/div/div[3]/div/input")  WebElement statDisabled;
	@FindBy(xpath = "/html/body/div/div/div/div/main/div/div/div/div[2]/div/div[1]/table/thead/tr/th[1]/div")  WebElement checkBoxIsSelected;
	
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
	static ArrayList<String> testData = new ArrayList<String>();
	
	//Seat list Objects
	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/span/a")  WebElement buyerSeatName;
//	@FindBy(xpath = "//table/tbody/tr[1]/td[2]")  WebElement buyerSeatId;
////	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")  WebElement buyerSeatCheckBox;
//	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div/I")  WebElement buyerSeatCheckBox;
	
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 1000);
	
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
	static int sid;
	static String rnNum;
	
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
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
		}else
		{
			pubPage.accountOption.click();
			pubPage.seatOption.click();
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			
		}

	}
	//Click on Tab - Active, Inactive and Paused.
	public void clickTabActiveInactivePaused(String tabName) throws InterruptedException
	{
		if(tabName.equalsIgnoreCase("Active"))
		{
//			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(activeTab));
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(activeTab));	  
			checkBoxAvailable.click();
			
		}else if(tabName.equalsIgnoreCase("Inactive"))
			
		{
//			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(inActiveTab));
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(inActiveTab));	  
			checkBoxAvailable.click();
			
		}	
		else if(tabName.equalsIgnoreCase("Paused"))
		{
//			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(pausedTab));
			wait.until(ExpectedConditions.visibilityOf(seatPageHeader));
			WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(pausedTab));	  
			checkBoxAvailable.click();
		
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
		
	////Get the particular row data of a seat table.		
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
//					strSrch=firstSeatXpath+seattableRows.size()+secondSeatXpath+2+thirdSeatXpath;
//					checkBox=firstSeatXpath+seattableRows.size()+secondSeatXpath+1+thirdSeatXpath;
					strSrch=firstSeatXpath+1+secondSeatXpath+2+thirdSeatXpath;
					checkBox=firstSeatXpath+1+secondSeatXpath+1+thirdSeatXpath;
					
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
					WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(statusActive));	  
					checkBoxAvailable.click();
//					statusActive.click();
				}else if(stsActInActDisabled.equalsIgnoreCase("Inactive"))
				{
					WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(statusInActive));	  
					checkBoxAvailable.click();
//					statusInActive.click();
				}else if(stsActInActDisabled.equalsIgnoreCase("Disabled"))
				{
					WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(statusDisabled));	  
					checkBoxAvailable.click();
//					statusDisabled.click();
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
				wait.until(ExpectedConditions.visibilityOf(pausedTab));
				return byerName;

			}
			
			
			//Provide Test data 
			public ArrayList<String> getTestData()
			{
				WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
				rnNum=RXUtile.getRandomNumberFourDigit();
				testData.clear();
				testData.add(byName + rnNum );
				testData.add(byType);
				testData.add(eterDSP);
				testData.add(eterToken + rnNum );
				testData.add(corpoName + rnNum );
				testData.add("www."+eterWebsite +rnNum +".com");
				testData.add(eterAddressOne + rnNum );
				testData.add(eterAddressTwo + rnNum );
				testData.add(eterCity + rnNum );
				testData.add(poCode + rnNum );
				testData.add(eterCountry);
				testData.add(eterCurrency);
				testData.add(eterAuthorId + rnNum );
				testData.add(rxUTL.getEmailString());
			
				return testData;
				
				
				
			}
			
			//Close the create seat page and return the seat name
			public int getTheSeatIdandClickCheckBox(String bname) throws InterruptedException {
				String strSrch="";
				String checkBox="";
				strSrch=firstSeatXpath+1+secondSeatXpath+2+thirdSeatXpath;
				checkBox=firstSeatXpath+1+secondSeatXpath+1+thirdSeatXpath;
				Thread.sleep(2000);
				Boolean elem = wait.until(ExpectedConditions.textToBePresentInElement(buyerSeatName, bname));
				System.out.println("Element present :"+elem);
				if(elem)
				{
	
				WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(checkBox))));	
				checkBoxAvailable.click();
				
				sid=Integer.parseInt(driver.findElement(By.xpath(strSrch)).getText());

			}
				return sid;
			}
			
			
			//Provide Test data 
			public ArrayList<String> getTheValueFrmCreateSeatPage() throws InterruptedException
			{
				Thread.sleep(2000);
				testData.clear();
				WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
				if(saveClick.isDisplayed())
				{
				testData.add(buyerName.getAttribute("value"));
				testData.add(buyerType.getAttribute("value"));
				testData.add(enterDSP.getAttribute("value"));
				testData.add(enterToken.getAttribute("value"));
				testData.add(corporationName.getAttribute("value"));
				testData.add(enterWebsite.getAttribute("value"));
				testData.add(enterAddressOne.getAttribute("value"));
				testData.add(enterAddressTwo.getAttribute("value"));
				testData.add(enterCity.getAttribute("value"));
				testData.add(postalCode.getAttribute("value"));
				testData.add(enterCountry.getAttribute("value"));
				testData.add(enterCurrency.getAttribute("value"));
				testData.add(enterAuthorId.getAttribute("value"));
				testData.add(contactEmail.getAttribute("value"));
				}
				return testData;

			}
			
			//Check whether status is selected or not
			public boolean getTheStatus(String statusSeat) throws InterruptedException
			{
				boolean flag=false;
				Thread.sleep(2000);
				WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
				if(saveClick.isDisplayed())
				{
					if(statActive.isSelected() && statusSeat.equalsIgnoreCase("Active"))
					{
						
						flag=true;
						System.out.println("Active Seat :"+ flag);
					}else if(statInActive.isSelected() && statusSeat.equalsIgnoreCase("Inactive"))
					{
						
						flag=true;
						System.out.println("Inactive Seat :"+ flag);
					}else if(statDisabled.isSelected() && statusSeat.equalsIgnoreCase("Disabled"))
					{
						
						flag=true;
						System.out.println("Disabled Seat :"+ flag);
					}
				}
				System.out.println("Final Seat :"+ flag);
				return flag;

			}
			
			//Clear value of seat fields
			
			public void deleteFieldValues()
			{
				WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(buyerName));
				
				rxUTL.clearTextBox(buyerName);
				rxUTL.clearTextBox(buyerType);
				rxUTL.clearTextBox(enterDSP);
				act.sendKeys(Keys.TAB).build().perform();
				rxUTL.clearTextBox(enterToken);
				rxUTL.clearTextBox(corporationName);
				rxUTL.clearTextBox(enterWebsite);
				rxUTL.clearTextBox(enterAddressOne);
				rxUTL.clearTextBox(enterAddressTwo);
				rxUTL.clearTextBox(enterCity);
				rxUTL.clearTextBox(postalCode);
				rxUTL.clearTextBox(enterCountry);
				act.sendKeys(Keys.TAB).build().perform();
				rxUTL.clearTextBox(enterCurrency);
				act.sendKeys(Keys.TAB).build().perform();
				rxUTL.clearTextBox(enterAuthorId);
				rxUTL.clearTextBox(contactEmail);
				
			}

			//Select the seat and return
			public int selectSeatAndReturnId(String tmpStr)
			
			{
				
					return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,3,tmpStr);
			
			}
			//Update the seat id to RX properties file
			public void updateTheSeatIdInTestData(String activeSeatID, int newValue)
			{
				rxUTL.updateTestData(activeSeatID, Integer.toString(newValue));
				
			}
			
			//get the seat and return the id from properties file
			public int getTheSeatIdInTestData(String newValue)
			{
				return Integer. parseInt(prop.getProperty(newValue));
				
			}
			
			//Wait for  page header display WebElement webele
			public int selectSeatforEdit(String seatId)
			
			{
				
					return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,2,seatId);
			
			}

}
