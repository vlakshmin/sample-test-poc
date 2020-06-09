package RXPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXDemanPartnerAdminPage extends RXBaseClass  {
	//Utility object
		RXUtile rxUTL ;
		PublisherListPage pubPage;
		RXUsers rxUserPage;
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, 6000);
		
		//Action object
		Actions act = new Actions(driver);
		
		//Main Option Demand PartnerObjectes
		@FindBy(xpath = "//div[text()='Demand Partners']")  WebElement demandPartnetMainOption;
		
		//Header of user page 
		@FindBy(xpath = "//h1[text()='Demand']")  WebElement demandPartnerPageHeader;
		
		//Search text field
		@FindBy(xpath = "//input[@autocomplete='nope']")  WebElement searchFieldInDemanPartnerPage;
		
		//Tabs of seats page
		@FindBy(xpath = "//span[text()='Active']")  WebElement activeTabInUserPage;
		@FindBy(xpath = "//span[text()='Disabled']")  WebElement disabledTabInUserPage;
		
		// Buttons on the User page
		String usersBtn="//header/div/div[1]/button";
		
		
		//Xpath String of table row value object
		String firstUserXpath="//table/tbody/tr[";
		String secondUserXpath="]/td[";
		String thirdUserXpath="]";

		//Xpath of rows to get the total number of row and column displayed in the page.
		@FindAll(@FindBy(xpath = "//table/tbody/tr")) List<WebElement> userTableRows;
		@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td")) List<WebElement> userTableColmn;
		
		//Forwared button in the table
		String frwdButton="//button[@type='button' and @aria-label='Next page']";
		
		//Constant data in Seats page
		public final String demandPartnerPageHear="Demand";
		static ArrayList<WebElement> tesData = new ArrayList<WebElement>();
		static int dspid;
		
		
		public RXDemanPartnerAdminPage()
		{
			PageFactory.initElements(driver, this);
			rxUTL=new RXUtile();
			pubPage=new PublisherListPage();
			rxUserPage = new RXUsers();

		}
		
		
		//Click on Deman Partner Option under.
		public void clickOnDemanPartnermainOption()
		{
			demandPartnetMainOption.click();

		}
		
		//Click on Deman Partner Option under.
				public String getTheDemandPartnerPageHeader()
				{
					return demandPartnerPageHeader.getText();

				}
		
				public WebElement getWebElementOfDemandPartnerPageHeader()
				{
					return demandPartnerPageHeader;

				}
				
				
				//Wait for demand page header display.
				public void waitForDemanPageToBeDisplayed(WebElement webele)
				{
					wait.until(ExpectedConditions.visibilityOf(webele));

				}
		
				
				//Wait for demand page header display.
				public int selectBidderIdAndReturnId()
				
				{
					String strSrch=firstUserXpath+userTableRows.size()+secondUserXpath+3+thirdUserXpath;
					dspid=Integer.parseInt(prop.getProperty("dispId"));
					tesData=rxUTL.getParticularRowData(dspid, 
							userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton);
					if(tesData!=null)
					{
						return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,2,prop.getProperty("dispId"));
					}
					
					return (Integer) null;
				}
				
			////Get the particular row data of a table view.		
				public ArrayList<WebElement> getParticularRowData(int stId) {
					String strSrch=firstUserXpath+userTableRows.size()+secondUserXpath+3+thirdUserXpath;
					return rxUTL.getParticularRowData(stId, 
							userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton);

				}
				
				
				//Click on Buttons on the page.
				public void clickButtonCreateEditDisable(String buttonName) throws InterruptedException
				{
		
					if(buttonName.equalsIgnoreCase("Edit") || buttonName.equalsIgnoreCase("Create"))
					{

						String btnStr=usersBtn+"["+1+"]";
						WebElement btnWebEl= driver.findElement(By.xpath(btnStr));
						WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(btnWebEl));
						btnElem.click();

					}else if(buttonName.equalsIgnoreCase("Disable")| buttonName.equalsIgnoreCase("Enable"))
					{

						String btnStr=usersBtn+"["+2+"]";
						WebElement btnWebEl= driver.findElement(By.xpath(btnStr));
						WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(btnWebEl));
						btnElem.click();
						Thread.sleep(10000);
					}	

				}
				
}
