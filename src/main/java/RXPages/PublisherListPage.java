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
	//RX utitlity object.
	RXUtile rxUTL;
	//Publosher page objects.
	@FindBy(xpath = "//div[@class=\"logo\"]")
	WebElement loGo;
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div/div/div[2]/div/div[2]/div[2]")
	WebElement totalRows;
	@FindBy(xpath = "//thead/tr/th[2][@aria-sort=\"descending\"]")
	WebElement sortDesc;
	@FindBy(xpath = "//thead/tr/th[2]/i")
	WebElement clickSort;
	

	//Xpath String of table row value object
	String firstUserXpath="//table/tbody/tr[";
	String secondUserXpath="]/td[";
	String thirdUserXpath="]";

	//Xpath of rows to get the total number of row and column displayed in the page.
	@FindAll(@FindBy(xpath = "//table/tbody/tr")) List<WebElement> userTableRows;
	@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td")) List<WebElement> userTableColmn;
	

	//Search text field:
	@FindBy(xpath = "//header/div/div[1]/div/div/div[1]/input")  WebElement searchField;
	
	//Forwared button in the table
	String frwdButton="//button[@type='button' and @aria-label='Next page']";

	// Mandatory message
	public @FindBy(xpath = "//div[1]/div[1]/span/div/div/div[2]/div/div/div")  WebElement pubNameMandMsg;
	public @FindBy(xpath = "//div[2]/div[1]/span/div/div/div[2]/div/div/div")  WebElement emailMandMsg;
	public @FindBy(xpath = "//div[3]/div[1]/span/div/div/div[2]/div/div/div")  WebElement corpNameMandMsg;
	public @FindBy(xpath = "//div[4]/div[1]/span/div/div/div[2]/div/div/div")  WebElement addressMandMsg;
	public @FindBy(xpath = "//div[5]/div[1]/span/div/div/div[2]/div/div/div")  WebElement countryMandMsg;
	public @FindBy(xpath = "//div[2]/div[2]/span/div/div/div[2]/div/div/div")  WebElement salesAccMandMsg;
	public @FindBy(xpath = "//div[3]/div[2]/span/div/div/div[2]/div/div/div")  WebElement corpWebMandMsg;
	public @FindBy(xpath = "//div[5]/div[2]/span/div/div/div[2]/div/div/div")  WebElement currencyMandMsg;
	public @FindBy(xpath = "//div[5]/div[3]/span/div/div/div[2]/div/div/div")  WebElement timeZoneMandMsg;

	//Mandatory field inline error message.
	public String pubNmErMsg ="The publisher name field is required";
	public String eMailErMsg="The e-mail field is required";
	public String corpNameErMsg="The corporation name field is required";
	public String addressErMsg="The address field is required";
	public String countryErMsg="The country field is required";
	public String salesAccErMsg="The sales account name field is required";
	public String corpWebErMsg="The corporation website field is required";
	public String currencyErMsg="The currency field is required";
	public String timeZoneErMsg="The time zone field is required";


	// Click on check box in the publisher list
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div/I")  WebElement clickCheckbox;
	//	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div/i")  WebElement clickCheckbox;
	public @FindBy(xpath = "//table/tbody/tr[1]/td[2]") WebElement publisherIdList;
	public @FindBy(xpath = "//table/tbody/tr[1]/td[3]") WebElement publisherInList;
	public @FindBy(xpath = "//table/tbody/tr[1]/td[4]") WebElement salesAccountList;
	public @FindBy(xpath = "//table/tbody/tr[1]/td[5]") WebElement emailList;

	//Publisher button
	@FindBy(xpath = "//header/div/div[1]/button[1]")
	WebElement createandeditPublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[2]")
	WebElement impersonatePublisherBtn;
	@FindBy(xpath = "//header/div/div[1]/button[3]")
	WebElement disablePublisherBtn;
	@FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div/div/div[2]/div/div[2]/div[4]/button/span/i")
	WebElement forWardBtn;


	// Create or edit Publisher objects
	@FindBy(xpath = "//aside/header/div/div[1]/div")
	WebElement createEditForm;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[1]/div[1]/span/div/div/div[1]/div/input") WebElement publisherName;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[2]/div[1]/span/div/div/div[1]/div/input") WebElement publisherEmail;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[2]/div[2]/span/div/div/div[1]/div/input") WebElement salesAccountName;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[3]/div/span/div/div/div[1]/div/input") WebElement addressEntry;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[4]/div[1]/span/div/div/div[1]/div[1]/input[1]") WebElement enterCountry;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[4]/div[2]/span/div/div/div[1]/div[1]/input[1]") WebElement enterCurrency;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[4]/div[3]/span/div/div/div[1]/div[1]/input[1]") WebElement enterTimeZone;

	// Create or edit Corporation specific objects
	public @FindBy(xpath = "//div[3]/div[1]/span/div/div/div[1]/div/input") WebElement corpName;
	public @FindBy(xpath = "//div[3]/div[2]/span/div/div/div[1]/div/input") WebElement corpWebSite;
	public @FindBy(xpath = "//div[5]/div[1]/span/div/div/div[1]/div[1]/input[1]") WebElement corpCountry;
	public @FindBy(xpath = "//div[5]/div[2]/span/div/div/div[1]/div[1]/input[1]") WebElement corpCurrency;
	public @FindBy(xpath = "//div[5]/div[3]/span/div/div/div[1]/div[1]/input[1]") WebElement corpTimeZone;
	public @FindBy(xpath = "//aside/div/span/form/div[3]/button/span") WebElement clickSaveOrUpdate;

	// Individual or Corporation
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[1]/div[2]/div/div/div[1]/div/div[1]/div/div") WebElement individualPub;
	public @FindBy(xpath = "//aside/div/span/form/div[2]/div[1]/div[2]/div/div/div[1]/div/div[2]/div/div") WebElement corporationPub;

	// Account options
	@FindBy(xpath = "//div[text()='Publishers']")
	WebElement publisherOption;
	@FindBy(xpath = "//div[text()='Seats']")
	WebElement seatOption;
	@FindBy(xpath = "//div[text()='Users']")
	WebElement userOption;
	@FindBy(xpath = "//div[text()='Accounts']")
	WebElement accountOption;

	// Publisher page heading
	@FindBy(xpath = "//h1[text()='Publishers']")
	WebElement headingOfPublisherPage;
	public String publisherHeader = "Publishers";

	// Close the create or edit dynamic form
	@FindBy(xpath = "//aside/header/div/button/span/i")
	WebElement closeTheDynamicForm;

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

	//Tab in the publisher page
	@FindBy(xpath = "//header/div/div[2]/div/div[2]/div/div[3]/span") WebElement disabledTabPub;
	@FindBy(xpath = "//header/div/div[2]/div/div[2]/div/div[2]/span") WebElement activeTabPub;

	//buttons in publisher page	
	@FindBy(xpath = "//*[@id=\"app\"]/div/header/div/div[1]/button[3]/span") WebElement disabledPub;
	@FindBy(xpath = "//*[@id=\"app\"]/div/header/div/div[1]/button[2]/span") WebElement enableImpPub;

	//Publisher Table Objects
	@FindAll(@FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div/div/div[2]/div/div[1]/table/tbody/tr"))
	List<WebElement> tableRows;
	@FindAll(@FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td"))
	List<WebElement> tableColumn;
	String coulmnNmeBfr = "//table/tbody/tr[";
	String coulmnNmeAft = "]/td";
	String rowNum = "//*[@id=\\\"app\\\"]/div/main/div/div/div/div[2]/div/div[1]/table/tbody/tr";

	//Publisher name for search
	String firstPubX ="//table/tbody/tr[";
	String secondPubX="]/td[3]";

	//Some declarations
	int rownum = 1;
	int totRow = 0;
	WebDriverWait wait = new WebDriverWait(driver, 10000);
	Actions act = new Actions(driver);
	
	//Radio button of Individual and corporation, these ratio button displayed at create publisher page.
	@FindBy(xpath = "//aside/div/span/form/div[2]/div[1]/div[2]/div/div/div[1]/div/div[1]/div/input")  WebElement individualRadio;
	@FindBy(xpath = "//aside/div/span/form/div[2]/div[1]/div[2]/div/div/div[1]/div/div[2]/div/input")  WebElement corporationRadio;

	// Initialize page factory
	public PublisherListPage() {
		PageFactory.initElements(driver, this);
		rxUTL=new RXUtile();
	}

	// return true/false based on image displayed or not.
	public boolean logodisplayed() {
		
		return loGo.isDisplayed();
	}

	// Edit on edit or create publisher.
	public void clickCreateOrEditPublisherBtn(String editOrCreate) throws Exception {
		if (editOrCreate.equalsIgnoreCase("Edit")) {
			Thread.sleep(2000);
			WebElement checkBoxAvailable = wait.until(ExpectedConditions.elementToBeClickable(clickCheckbox));	  
			checkBoxAvailable.click();

		}
		Thread.sleep(2000);
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(createandeditPublisherBtn));
		element2.click();

	}

	// Click on Sort publisher list.
	public void clickPublisherSortDesc() {

		WebElement srtClick = wait.until(ExpectedConditions.visibilityOf(clickSort));
		while (!sortDesc.isDisplayed()) {
			srtClick.click();
		}

	}

	// Click on create or edit individual publisher.
	public void createEditIndividualPublisher(String createEdit, String pName, String eMail, String saleAct,
			String addressPb, String countryPb, String currencyPb, String timeZonePb) {

		publisherName.sendKeys(pName);
		individualPub.click();
		publisherEmail.sendKeys(eMail);
		salesAccountName.sendKeys(saleAct);
		addressEntry.sendKeys(addressPb);

		if (createEdit.equalsIgnoreCase("Create")) {
			enterCountry.sendKeys(countryPb);
			act.sendKeys(Keys.TAB).build().perform();
			enterCurrency.sendKeys(currencyPb);
			act.sendKeys(Keys.TAB).build().perform();
			enterTimeZone.sendKeys(timeZonePb);
			act.sendKeys(Keys.TAB).build().perform();
		}

	}

	// Click on create or edit corporation publisher.
	public void createEditCorporationPublisher(String createEdit, String pName, String eMail, String saleAct,
			String corpName, String corpSite, String addressPb, String countryPb, String currencyPb,
			String timeZonePb) {

		publisherName.sendKeys(pName);
		corporationPub.click();
		publisherEmail.sendKeys(eMail);
		salesAccountName.sendKeys(saleAct);
		addressEntry.sendKeys(corpName);
		corpWebSite.sendKeys(corpSite);
		enterCountry.sendKeys(addressPb);
		if (createEdit.equalsIgnoreCase("Create")) {
			corpCountry.sendKeys(countryPb);
			act.sendKeys(Keys.TAB).build().perform();
			corpCurrency.sendKeys(currencyPb);
			act.sendKeys(Keys.TAB).build().perform();
			corpTimeZone.sendKeys(timeZonePb);
			act.sendKeys(Keys.TAB).build().perform();
		}
	}
	//Click on Save or Update button
	public String clickSaveOrUpdate() {
		WebElement SaveUpdateClick = wait.until(ExpectedConditions.visibilityOf(clickSaveOrUpdate));
		SaveUpdateClick.click();
		WebElement SaveOrUpdateClick = wait.until(ExpectedConditions.visibilityOf(clickSaveOrUpdate));
		return SaveOrUpdateClick.getText();

	}
	//Click on Save or Update button
	public void clickAccount() {
		accountOption.click();

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

	// Click on closing the dynamic form .
	public void closeTheDynamicForm() {

		closeTheDynamicForm.click();

	}

	
	//Method to delete field value
	public void deleteTextField()
	{

		if (individualRadio.isSelected()) 
		{
			rxUTL.clearTextBox(publisherName);
			rxUTL.clearTextBox(publisherEmail);
			rxUTL.clearTextBox(salesAccountName);
			rxUTL.clearTextBox(addressEntry);


		} else if(corporationRadio.isSelected())
		{
			rxUTL.clearTextBox(publisherName); 
			rxUTL.clearTextBox(publisherEmail); 
			rxUTL.clearTextBox(salesAccountName); 
			rxUTL.clearTextBox(addressEntry); 
			rxUTL.clearTextBox(corpWebSite);
			rxUTL.clearTextBox(enterCountry); 

		}
	}
	//Click on Active and Disable tab on publisher tab
	public void clickOnTab(String activeOrDisabled){

		if (activeOrDisabled.equalsIgnoreCase("Active")) {

			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(activeTabPub));
			element2.click();

		} else if (activeOrDisabled.equalsIgnoreCase("Disabled"))
		{

			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(disabledTabPub));
			element2.click();

		}
	}
	//Clicking on Enabled and Disabled button.
	public void clickbutton(String enableDisableImpersonate) throws InterruptedException{

		if (enableDisableImpersonate.equalsIgnoreCase("Enabled")) {

			Thread.sleep(2000);
//			clickCheckbox.click();
			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(enableImpPub));
			element2.click();
			wait.until(ExpectedConditions.invisibilityOf(enableImpPub));
			Thread.sleep(6000);

		} else if (enableDisableImpersonate.equalsIgnoreCase("Disabled"))
		{
			Thread.sleep(2000);
//			clickCheckbox.click();
			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(disabledPub));
			element2.click();
			wait.until(ExpectedConditions.invisibilityOf(disabledPub));
			Thread.sleep(6000);

		}
	}


	// Get the value of specified column.
	public ArrayList<WebElement> getParticularRowData(int pubId) {

		String str = totalRows.getText();
		String[] srt = str.split(" ");
		totRow = Integer.parseInt(srt[2]);
		boolean flag=true;
		/*
		 * for(int i=0;i<=srt.length-1;i++) {
		 * System.out.println("Total Number of Rows "+srt[i]); }
		 */
		ArrayList<WebElement> pubElementvalues = new ArrayList<WebElement>();
		//								while(rownum <= totRow)
		while(flag == true)
		{
			flag=false;
			for(int i=1;i<=tableRows.size();i++)
			{
				String actualColumnXpath=coulmnNmeBfr+i+coulmnNmeAft+"["+2+"]";
				WebElement columnValue= driver.findElement(By.xpath(actualColumnXpath));
				if(Integer.parseInt(columnValue.getText())== pubId)
				{
					for(int j=2;j<=tableColumn.size();j++) 
					{
						String checkStr=coulmnNmeBfr+i+coulmnNmeAft+"["+j+"]";
						WebElement checkClick= driver.findElement(By.xpath(checkStr));
						pubElementvalues.add(checkClick);


					}
					return pubElementvalues;
				}
				rownum=rownum+1;
			}
			if(forWardBtn.isEnabled())
			{
				flag = true;
				forWardBtn.click();
			}
		}
		return null;

	}




	//Get the publisher name for search.		
	public String getPublisherNameForSearch() {

		String strSearch=firstPubX+tableRows.size()+secondPubX;
		WebElement webEle= driver.findElement(By.xpath(strSearch));
		return webEle.getText();

	}

	////Get the publisher name for search.		
	public void publisherNameSearch(String srChString) {

		searchField.sendKeys(srChString);

	}
	
	//Select the publisher and return
	public int selectpublisherAndReturnId(String tmpStr)
	
	{
		
			return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,5,tmpStr);
	
	}
	
	public void updateThePublisherIdInTestData(int newValue)
	{
		rxUTL.updateTestData("PublisherId", Integer.toString(newValue));
		
	}
	
	//get the publisher and return the id from properties file
	public int getThePublisherIdInTestData(String newValue)
	{
		return Integer. parseInt(prop.getProperty("PublisherId"));
		
	}
	
	//Wait for  page header display WebElement webele
	public void waitForCreatedPublisherToBeDisplayed(String webValue)
	{
		wait.until(ExpectedConditions.textToBePresentInElement(emailList, webValue));

	}
	//Wait for  page header display WebElement webele
	public int selectpublisherforEdit(String pubId)
	
	{
		
			return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,2,pubId);
	
	}
	

}
