package RXPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	@FindBy(xpath = "//div[2]/div[1]/div[3]/i") WebElement inventoryExpensionBtn;
	@FindBy(xpath = "//div[3]/div[1]/div[3]/i") WebElement rulesExpensionBtn;

	//subMenu of Admin main menu
	@FindBy(xpath = "//div[text()='Publishers']") WebElement publisherUndrAdmin;
	@FindBy(xpath = "//div[text()='Users']") WebElement usersUndrAdmin;
	@FindBy(xpath = "//div[text()='Demand Sources']") WebElement demandSourcesUndrAdmin;
	@FindBy(xpath = "//div[text()='Buyers']") WebElement buyersUndrAdmin;

	//subMenu of Inventory main menu
	@FindBy(xpath = "//div[text()='Media']") WebElement mediaUndrInventory;
	@FindBy(xpath = "//div[text()='Ad Spots']") WebElement adspotsUndrInventory;

	//subMenu of Rules main menu
	@FindBy(xpath = "//div[text()='Filters']") WebElement filtersUndrRules;
	@FindBy(xpath = "//div[text()='Targeting']") WebElement targetingUndrRules;




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
		rulesExpensionBtn.click();

	}


}
