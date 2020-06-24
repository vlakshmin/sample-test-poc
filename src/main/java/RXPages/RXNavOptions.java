package RXPages;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXNavOptions extends RXBaseClass{

	//RX utitlity object.
	RXUtile rxUTL;

	//User Info option
	@FindBy(xpath = "//div[1]/div[2]/a/div[2]/div[2]") WebElement userInfo;
	@FindBy(xpath = "//div[text()='Admin']") WebElement adminNav;
	@FindBy(xpath = "//div[text()='Inventory']") WebElement inventoryNav;
	@FindBy(xpath = "//div[text()='Rules']") WebElement ruleNav;
	@FindBy(xpath = "//div[text()='Dashboard']") WebElement dashBoardNav;

	//Option expension	
	@FindBy(xpath = "//div[1]/div[1]/div[3]/i") WebElement adminExpensionBtn;
	@FindBy(xpath = "//div[text()='Inventory']/parent::div/following-sibling::div") WebElement inventoryExpensionBtn;
	@FindBy(xpath = "//div[3]/div[1]/div[3]/i") WebElement rulesExpensionBtn;

	//subMenu of Admin main menu
	@FindBy(xpath = "//div[text()='Publishers']") WebElement publisherUndrAdmin;
	@FindBy(xpath = "//div[text()='Users']") WebElement usersUndrAdmin;
	@FindBy(xpath = "//div[text()='Demand Sources']") WebElement demandSourcesUndrAdmin;
	@FindBy(xpath = "//div[text()='Buyers']") WebElement buyersUndrAdmin;

	//subMenu of Inventory main menu
	@FindBy(xpath = "//div[text()='Media']") public WebElement mediaUndrInventory;
	@FindBy(xpath = "//div[text()='Ad Spots']") WebElement adspotsUndrInventory;

	//subMenu of Rules main menu
	@FindBy(xpath = "//div[text()='Filters']") WebElement filtersUndrRules;
	@FindBy(xpath = "//div[text()='Targeting']") WebElement targetingUndrRules;
	
	//Pagination parameters
	@FindBy(xpath = "//div[@class='v-data-footer__select']//div[@class='v-input__icon v-input__icon--append']")  WebElement rowsPerPageDropDown;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-after']/button")  public WebElement nextPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-before']/button") public WebElement previousPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__pagination']")  public WebElement paginationCount;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> tableRowsCount;
    @FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td")) 
    public List<WebElement> tableColumnsCount;
    @FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[2]")) 
    public WebElement tableFirstRowName;
	

  


	public RXNavOptions() {
		PageFactory.initElements(driver, this);
		rxUTL=new RXUtile();
	}

	//User Infor
	public boolean isUserInfoNavDisplayed()
	{
		return userInfo.isDisplayed();
	}

	public boolean isAdminNavDisplayed()
	{
		return adminNav.isDisplayed();
	}

	public boolean isInventoryNavDisplayed()
	{
		return inventoryNav.isDisplayed();

	}

	public boolean isRuleNavDisplayed()
	{
		return ruleNav.isDisplayed();

	}

	public boolean isdashBoardDisplayed()
	{
		return dashBoardNav.isDisplayed();

	}


	//subMenu Publisher of Admin main menu
	public boolean ispublisherUndrAdminDisplayed()
	{
		return publisherUndrAdmin.isDisplayed();

	}

	//subMenu Users of Admin main menu
	public boolean isusersUndrAdminDisplayed()
	{
		return usersUndrAdmin.isDisplayed();

	}

	//subMenu Demand Source of Admin main menu
	public boolean isdemandSourcesUndrAdminDisplayed()
	{
		return demandSourcesUndrAdmin.isDisplayed();

	}	

	//subMenu Buyer of Admin main menu
	public boolean isbuyersUndrAdminDisplayed()
	{
		return buyersUndrAdmin.isDisplayed();

	}	

	//subMenu of Inventory main menu

	//subMenu Media of Inventory main menu
	public boolean ismediaUndrInventoryDisplayed()
	{
		return mediaUndrInventory.isDisplayed();

	}	

	//subMenu Adspots of Inventory main menu
	public boolean isadspotsUndrInventoryUndrInventoryDisplayed()
	{
		return adspotsUndrInventory.isDisplayed();

	}	
	//subMenu of Rules main menu		
	//subMenu Filters of Rules main menu
	public boolean isfiltersUndrRulesDisplayed()
	{
		return filtersUndrRules.isDisplayed();

	}	

	//subMenu Targeting of Rules main menu
	public boolean istargetingUndrRulesDisplayed()
	{
		return targetingUndrRules.isDisplayed();

	}	


	//Clicking expansion of Admin

	public void expandAdmin()
	{
		adminExpensionBtn.click();

	}

	// Clicking expansion of Inventory

	public void expandInventory()
	{
		inventoryExpensionBtn.click();

	}
	// Clicking expansion of Rules

	public void expandRules()
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element =wait.until(ExpectedConditions.visibilityOf(rulesExpensionBtn));
		element.click();

	}
	// click on the next page navigation button
	public void clickNextPageNav() {
		if(nextPageNavButton.isDisplayed()) {
			nextPageNavButton.click();
		}
	}
	// click on the previous page navigation button
	public void clickPreviousPageNav() {
		if(previousPageNavButton.isDisplayed()) {
			previousPageNavButton.click();
		}
	}
	
	// click on selection dropdown to select the no. of rows per page
	public void clickNoOfPagesDropDown() {
		if(rowsPerPageDropDown.isDisplayed()) {
			rowsPerPageDropDown.click();
		}
	}
	
	// Get the from and to page no. shown in the current page
	public String getPaginationText() {
		String paginationText = null;
		if(paginationCount.isDisplayed()) {
			paginationText = paginationCount.getText();
		}
		return paginationText;
	}
	


}
