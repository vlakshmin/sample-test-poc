package RXPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXUsers extends RXBaseClass {

	//Utility object
	RXUtile rxUTL ;
	PublisherListPage pubPage;

	//User option under Accounts
	@FindBy(xpath = "//div[text()='Users']")  WebElement userOptionUnderAccounts;

	//Header of user page 
	@FindBy(xpath = "//h1[text()='Users']")  WebElement userPageHeader;

	//Search text field
	@FindBy(xpath = "//input[@autocomplete='nope']")  WebElement searchFieldInUserPage;

	//Tabs of seats page
	@FindBy(xpath = "//span[text()='Active']")  WebElement activeTabInUserPage;
	@FindBy(xpath = "//span[text()='Disabled']")  WebElement disabledTabInUserPage;

	//Xpath String of Seat table row value object
	String firstUserXpath="//table/tbody/tr[";
	String secondUserXpath="]/td[";
	String thirdUserXpath="]";

	//Xpath of rows to get the total number of row and column displayed in the page.
	@FindAll(@FindBy(xpath = "//table/tbody/tr")) List<WebElement> userTableRows;
	@FindAll(@FindBy(xpath = "//table/tbody/tr[1]/td")) List<WebElement> userTableColmn;

	// Buttons on the User page
	String usersBtn="//header/div/div[1]/button";
	//Forwared button in the table
	String frwdButton="//button[@type='button' and @aria-label='Next page']";

	//User list Objects
	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/span/a")  WebElement userNameFrmList;

	//Create user page
	//span[1]/div/div/div[1]/div[1]/div[1]/div
	@FindBy(xpath = "//form//input[@type='radio' and @value='1']")  WebElement admUser;
	@FindBy(xpath = "//form/div[2]/div/div/div[1]/div/div[1]/div/div")  WebElement adminUser;
	@FindBy(xpath = "//form//input[@type='radio' and @value='0']")  WebElement publisUser;
	@FindBy(xpath = "//form/div[2]/div/div/div[1]/div/div[2]/div/div")  WebElement publisherUser;	
	@FindBy(xpath = "//*[@id=\"input-364\"]")  WebElement publisSelect;
	@FindBy(xpath = "//span[1]/div/div/div[1]/div[1]/div[1]")  WebElement publsSelect;
	@FindBy(xpath = "//span[1]/div/div/div[1]/div[1]/div[2]/div/i")  public WebElement dropDwonSelect;
	@FindBy(xpath = "//label[text()='Username']/following-sibling::input[@type='text']")  public WebElement userName;
	@FindBy(xpath = "//label[text()='Email']/following-sibling::input[@type='text']")  public WebElement userEmail;
	@FindBy(xpath = "//label[text()='Password']/following-sibling::input[@type='password']")  public WebElement userPassword;
	@FindBy(xpath = "//span[2]/div/div/div[2]/div/div/div")  WebElement accountNameMandatoryMsg;
	@FindBy(xpath = "//span[3]/div/div/div[2]/div/div/div")  WebElement eMailMandatoryMsg;
	@FindBy(xpath = "//span[1]/div/div/div[2]/div/div/div")  WebElement publisherMandatoryMsg;
	@FindBy(xpath = "//button[@type='submit']")  public WebElement saveButton;
	@FindBy(xpath = "//aside/header/div/button/span/i")  WebElement closeBtn;
	@FindBy(xpath = "//div[@class='table-options']/button")
		public WebElement tableOptions;
	    
	@FindBy(xpath = "//div[@id='app']/div[@role='menu']")
		public WebElement tableOptionsMenu;
	
	
	//Enable and disable feature for the user
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr/td/div[@class='v-data-table__checkbox v-simple-checkbox']/i"))
	public List<WebElement> userCheckBoxList;
	
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr/td[6]"))
	public List<WebElement> userActiveFieldList;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']/button[2]//span")  public WebElement enableDisableButton;

	
	//Array for test data 
	static ArrayList<String> usertestData = new ArrayList<String>();

	//Test data 
	static String pubName="Adolfo Koepp_updated";
	String userAccName="TestAccount";
	String userPwd="Password1";
	static String xpathFirstDropDownItem="//div[text()='";
	static String xpathSecondDropDownItem="']";
	static String editPubName="Kobo";
	static String publisherSelect;
	//Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 6000);
	
	//some variable
	static String rnNum;
	static int userId;

	//Action object
	Actions act = new Actions(driver);

	// Initialize page factory
	public RXUsers()
	{
		PageFactory.initElements(driver, this);
		rxUTL=new RXUtile();
		pubPage=new PublisherListPage();

	}





	//Get the text of the Seats page
	public String getUserPageHeading()
	{

		WebElement elem = wait.until(ExpectedConditions.visibilityOf(userPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

	}

	//Click on Users Option under the account option.
	public void clickOnUserOptionUnAcc()
	{
		if(pubPage.isUserDisplayed())
		{
			pubPage.userOption.click();
		}else
		{
			pubPage.accountOption.click();
			pubPage.userOption.click();

		}

	}
	
	//Wait for User page header display.
	public void waitForUserPageToBeDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(userPageHeader));

	}

	//Click on Active and Disable tab
	public void clickOnTab(String activeOrDisabled){

		if (activeOrDisabled.equalsIgnoreCase("Active")) {
//			wait.until(ExpectedConditions.visibilityOf(userPageHeader));
			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(activeTabInUserPage));
			element2.click();

		} else if (activeOrDisabled.equalsIgnoreCase("Disabled"))
		{
//			wait.until(ExpectedConditions.visibilityOf(userPageHeader));
			WebElement element2 = wait.until(ExpectedConditions.visibilityOf(disabledTabInUserPage));
			element2.click();

		}
	}

	//Get the Users name for search.		
	public String getUserNameForSearch(WebElement webele) {

		WebElement elem = wait.until(ExpectedConditions.visibilityOf(webele));
		String strSrch=firstUserXpath+userTableRows.size()+secondUserXpath+3+thirdUserXpath;
		WebElement webEl= driver.findElement(By.xpath(strSrch));
		return webEl.getText();

	}

	////Get the users name for search.		
	public void seatNameSearch(String srchSeat) {

		searchFieldInUserPage.sendKeys(srchSeat);

	}

	//Click on Buttons on the User page.
	public void clickButtonCreateEditDisableUser(String buttonName) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(userPageHeader));
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

	//Select the user for modify.Enable or Disable.		
	public int selectUserandRetunID(String usName) {
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(userPageHeader));
		String strSrch="";
		String checkBox="";
		String useName="";
		try {
			useName=firstUserXpath+1+secondUserXpath+3+thirdUserXpath;
			strSrch=firstUserXpath+1+secondUserXpath+2+thirdUserXpath;
			checkBox=firstUserXpath+1+secondUserXpath+1+thirdUserXpath;

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
		if(driver.findElement(By.xpath(useName)).getText().equalsIgnoreCase(usName))
		{
		driver.findElement(By.xpath(checkBox)).click();
		userId= Integer.parseInt(driver.findElement(By.xpath(strSrch)).getText());
		}
		return userId;
	}


	//Fill the create user form
	public void enterVlauesToCreateUser(String publName,String accName,String eMail,String pwd,String roleOrEditPublisher)
	{

		if(roleOrEditPublisher.equalsIgnoreCase("Admin"))
		{
			adminUser.click();
			userName.sendKeys(accName);
			userEmail.sendKeys(eMail);
			userPassword.sendKeys(pwd);
		}else if(roleOrEditPublisher.equalsIgnoreCase("Publisher"))
		{
			publisherUser.click();
			userName.sendKeys(accName);
			userEmail.sendKeys(eMail);
			userPassword.sendKeys(pwd);
			publisherSelect=xpathFirstDropDownItem+publName+xpathSecondDropDownItem;
			WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(dropDwonSelect));
			btnElem.click();
			driver.findElement(By.xpath(publisherSelect)).click();
			System.out.println("Publisher selected :"+driver.findElement(By.xpath(publisherSelect)).getText());
			act.sendKeys(Keys.TAB).build().perform();
			
		}else if(roleOrEditPublisher.equalsIgnoreCase("EditPublisher"))
		{
			userName.sendKeys(accName);
			userEmail.sendKeys(eMail);
			userPassword.sendKeys(pwd);
		}

	}


	////Get the particular row data of a seat table.		
	public ArrayList<WebElement> getParticularUserRowData(int stId) {
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(userPageHeader));
		String strSrch=firstUserXpath+userTableRows.size()+secondUserXpath+3+thirdUserXpath;
		return rxUTL.getParticularRowData(stId, 
				userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton);

	}


	//Click in save button 
	public String clkSave(String adminOrPublisher) {
		
		WebElement saveClick;
		if(adminOrPublisher.equalsIgnoreCase("Create"))
		{
			saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
			saveClick.click();
			saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
			return saveClick.getText();
		}else if(adminOrPublisher.equalsIgnoreCase("Edit"))
		{
			saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
			return saveClick.getText();	
		}
		return null;
	}


	//Provide Test data 
	public ArrayList<String> getTestData(String createOrEdit)
	{
		if(createOrEdit.equalsIgnoreCase("Create"))
		{
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		rnNum=RXUtile.getRandomNumberFourDigit();
		usertestData.clear();
		usertestData.add(pubName);
		usertestData.add(userAccName + rnNum );
		usertestData.add(rxUTL.getEmailString());
		usertestData.add(userPwd);
		return usertestData;
		}else if(createOrEdit.equalsIgnoreCase("Edit"))
		{
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		rnNum=RXUtile.getRandomNumberFourDigit();
		usertestData.clear();
		usertestData.add(editPubName);
		usertestData.add(userAccName + rnNum );
		usertestData.add(rxUTL.getEmailString());
		usertestData.add(userPwd);
		return usertestData;
		}
		return null;

	}


	//Provide Test data 
	public ArrayList<String> getTheValueFrmCreateUserPage() throws InterruptedException
	{

		usertestData.clear();
		WebElement saveClick = wait.until(ExpectedConditions.visibilityOf(saveButton));
		if(saveClick.isDisplayed() && admUser.isSelected())
		{
			usertestData.add(userName.getAttribute("value"));
			usertestData.add(userEmail.getAttribute("value"));
			usertestData.add(userPassword.getAttribute("value"));
		}else if(saveClick.isDisplayed() && publisUser.isSelected())
		{
			System.out.println("Entered Publisher "+ driver.findElement(By.xpath(publisherSelect)).getText());
			usertestData.add(driver.findElement(By.xpath(publisherSelect)).getText());
			usertestData.add(userName.getAttribute("value"));
			usertestData.add(userEmail.getAttribute("value"));
			usertestData.add(userPassword.getAttribute("value"));
		}
		return usertestData;

	}


	//Clear value of user fields

	public void deleteFieldValues()
	{
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		if(admUser.isSelected())
		{
			wait.until(ExpectedConditions.visibilityOf(saveButton));
			System.out.println("Admin user enabled :"+publisherUser.isSelected());
			rxUTL.clearTextBox(userName);
			rxUTL.clearTextBox(userEmail);
			rxUTL.clearTextBox(userEmail);
		}else if(publisUser.isSelected())
		{
			wait.until(ExpectedConditions.visibilityOf(saveButton));
			
			rxUTL.clearTextBox(userName);
			rxUTL.clearTextBox(userEmail);
			rxUTL.clearTextBox(userEmail);
			
			if(publsSelect.getText().equalsIgnoreCase(pubName))
			{
				publisherSelect=xpathFirstDropDownItem+editPubName+xpathSecondDropDownItem;
				WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(dropDwonSelect));
				btnElem.click();
				WebElement selectDropeDown =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(publisherSelect))));
				System.out.println("Publisher user enabled :"+selectDropeDown.getText());
				selectDropeDown.click();
				act.sendKeys(Keys.TAB).build().perform();
				
			}else 
			{
				publisherSelect=xpathFirstDropDownItem+pubName+xpathSecondDropDownItem;
				WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(dropDwonSelect));
				btnElem.click();
				WebElement selectDropeDown =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(publisherSelect))));
				System.out.println("Publisher user enabled :"+selectDropeDown.getText());
				selectDropeDown.click();
				act.sendKeys(Keys.TAB).build().perform();
				editPubName=pubName;
				
			}
			
		}

	}

	//Fetch the mandatory fields message on not filling them
	public String getFieldValueMandMsgFrmCreateUserPage(String fieldName) throws InterruptedException
	{
		String retnStr="";
		wait.until(ExpectedConditions.visibilityOf(saveButton));

		if(fieldName.equalsIgnoreCase("Account Name"))
		{
			retnStr=accountNameMandatoryMsg.getText();
		}else if(fieldName.equalsIgnoreCase("Publisher"))
		{
			retnStr=publisherMandatoryMsg.getText();
		}else if(fieldName.equalsIgnoreCase("Email"))
		{
			retnStr=eMailMandatoryMsg.getText();
		}
		return retnStr;
	}

	//Clicking the option Admin and Publisher
	public void clickOnAdminOrPublisher(String roleName)
	{

		wait.until(ExpectedConditions.visibilityOf(saveButton));


		if(roleName.equalsIgnoreCase("Admin"))
		{
			adminUser.click();
		}else if(roleName.equalsIgnoreCase("Publisher"))
		{
			publisherUser.click();
		}
	}

	//Close the create user page 
	public void closeCreateUserPage() {
		closeBtn.click();
		wait.until(ExpectedConditions.visibilityOf(userPageHeader));
		wait.until(ExpectedConditions.visibilityOf(activeTabInUserPage));
	}



	//Select the user based on role.		
	public int selectTheUserBasedOnRole(String roleName) {
		wait.until(ExpectedConditions.visibilityOf(userPageHeader));
		return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,5,roleName);

	}

	public void selectPublisherFrmCreateForm()
	{
		publisherSelect=xpathFirstDropDownItem+editPubName+xpathSecondDropDownItem;
		WebElement btnElem = wait.until(ExpectedConditions.visibilityOf(dropDwonSelect));
		btnElem.click();
		WebElement selectDropeDown =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(publisherSelect))));
		System.out.println("Publisher user enabled :"+selectDropeDown.getText());
		selectDropeDown.click();
		act.sendKeys(Keys.TAB).build().perform();
	}

	
	
	
	
	//Select the User and return
	public int selectUserAndReturnId(String tmpStr)
	
	{
		
			return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,3,tmpStr);
	
	}
	//Update the User id to RX properties file
	public void updateTheUserIdInTestData(String activeSeatID, int newValue)
	{
		rxUTL.updateTestData(activeSeatID, Integer.toString(newValue));
		
	}
	
	//get the User and return the id from properties file
	public int getTheUserIdInTestData(String newValue)
	{
		return Integer. parseInt(prop.getProperty(newValue));
		
	}
	
	//Wait for  page header display WebElement webele
	public int selectUserforEdit(String userId)
	
	{
		
			return rxUTL.selectParticularRowData(userTableRows.size(), userTableColmn.size(), firstUserXpath, secondUserXpath, thirdUserXpath, frwdButton,2,userId);
	
	}
	
	//Click on the checkbox for user with index number
	public void clickUserCheckBox(int index)
	{

		wait.until(ExpectedConditions.visibilityOf(userCheckBoxList.get(index-1)));
		userCheckBoxList.get(index-1).click();

		
	}
	//Click on the enable or disable button
	public void clickEnableDisableButton()
	{

		wait.until(ExpectedConditions.visibilityOf(enableDisableButton));
		enableDisableButton.click();

		
	}
	//Get the text of the enable or disable button
	public String getEnableDisableBtnText()
	{

		WebElement elem = wait.until(ExpectedConditions.visibilityOf(enableDisableButton));
		System.out.println(elem.getText());
		String text = elem.getText().replaceAll("\\s", "").toUpperCase();
		return text;

	}
	
	//Get the text of the Active filed with index for user
		public String getActiveFieldText(int index)
		{

			WebElement elem = wait.until(ExpectedConditions.visibilityOf(userActiveFieldList.get(index-1)));
			System.out.println(elem.getText());
			String text = elem.getText().replaceAll("\\s", "");
			return text;

		}

		public WebElement userCheckBox(int i) {
			return driver
					.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+i+"]/td[1]"));
		}

		public String userName(int i) {
			return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+i+"]/td[3]/a"))
					.getText();
		}

		public WebElement toolbarButton(String e) {
			return driver
					.findElement(By.xpath("//span[contains(text() , '" + e + "')]/parent::button"));
		}

		public WebElement userActive(int i) {
			return driver
					.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+i+"]/td[7]"));
		}
		
		public String toggleFieldIsEnabledForCreatePage(String fieldName) {
	    	return driver
					.findElement(
							By.xpath("//aside[@class='dialog']//label[text()='" + fieldName + "']/parent::div//input"))
					.getAttribute("aria-checked");
	    }
		
		public WebElement toggleField(String fieldName) {
	    	return driver.findElement(
					By.xpath("//aside[@class='dialog']//label[text()='" + fieldName + "']/parent::div/div"));
	    }

		public String getActiveWithName(String enteredUserName) {
			return driver.findElement(By.xpath("//a[text()='"+enteredUserName+"']/parent::td/parent::tr/td[6]")).getText();
		}

		public WebElement getColumnHeader(String columnName) {
			return driver.findElement(By.xpath(
					"//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + columnName + "']/parent::th"));
		}
		
		public void selectColumnInTableOptions(String columnName) {
			driver.findElement(By.xpath("//label[text()='"+columnName+"']/preceding-sibling::div")).click();
			
		}
	
}
