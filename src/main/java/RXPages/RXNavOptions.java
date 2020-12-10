package RXPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXNavOptions extends RXBaseClass {

	// RX utitlity object.
	RXUtile rxUTL;
	public String tableOptionsLabel;

	// User Info option
	@FindBy(xpath = "//div[1]/div[2]/a/div[2]/div[2]")
	WebElement userInfo;
	@FindBy(xpath = "//div[text()='Admin']")
	WebElement adminNav;
	@FindBy(xpath = "//div[text()='Inventory']")
	WebElement inventoryNav;
	@FindBy(xpath = "//div[text()='Rules']")
	WebElement ruleNav;
	@FindBy(xpath = "//div[text()='Dashboard\n" +
			"            ']")
	WebElement dashBoardNav;

	// Option expension
	@FindBy(xpath = "//div[text()='Admin']/parent::div/following-sibling::div")
	WebElement adminExpensionBtn;
	@FindBy(xpath = "//div[text()='Inventory']/parent::div/following-sibling::div")
	WebElement inventoryExpensionBtn;
	@FindBy(xpath = "//div[text()='Sales']/parent::div/following-sibling::div")
	WebElement salesExpensionBtn;
	@FindBy(xpath = "//div[text()='Rules']/parent::div/following-sibling::div")
	WebElement rulesExpensionBtn;

	// subMenu of Admin main menu
	@FindBy(xpath = "//div[text()='Publishers']")
	public WebElement publisherUndrAdmin;
	@FindBy(xpath = "//div[text()='Users']")
	WebElement usersUndrAdmin;
	@FindBy(xpath = "//div[text()='Demand Sources']")
	WebElement demandSourcesUndrAdmin;
	@FindBy(xpath = "//div[text()='Buyers']")
	WebElement buyersUndrAdmin;

	// subMenu of Inventory main menu
	@FindBy(xpath = "//div[text()='Media']")
	public WebElement mediaUndrInventory;
	@FindBy(xpath = "//div[text()='Ad Spots']")
	public WebElement adspotsUndrInventory;
	@FindBy(xpath = "//a[@href='/sales/private-auctions']")
	public WebElement privateAuctionLabel;
	@FindBy(xpath = "//a[@href='/sales/deals']")
	public WebElement dealsLabel;

	// Create/EditPage common webelements
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	@FindBy(xpath = "//button[@type='submit']/span")
	public WebElement saveButtonTxt;

	// subMenu of Rules main menu
	@FindBy(xpath = "//div[text()='Filters']")
	WebElement filtersUndrRules;
	@FindBy(xpath = "//div[text()='Targeting']")
	WebElement targetingUndrRules;

	// Pagination parameters
	@FindBy(xpath = "//div[@class='v-data-footer__select']//div[@class='v-input__icon v-input__icon--append']")
	WebElement rowsPerPageDropDown;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-after']/button")
	public WebElement nextPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__icons-before']/button")
	public WebElement previousPageNavButton;
	@FindBy(xpath = "//div[@class='v-data-footer__pagination']")
	public WebElement paginationCount;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> tableRowsCount;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td"))
	public List<WebElement> tableColumnsCount;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[2]"))
	public WebElement tableFirstRowName;

	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//thead//th/span"))
	public List<WebElement> tableHeadersList;

	// TableOptions
	@FindBy(xpath = "//div[@class='table-options']//span")
	public WebElement tableOptions;

	public RXNavOptions() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
	}

	// User Infor
	public boolean isUserInfoNavDisplayed() {
		return userInfo.isDisplayed();
	}

	public boolean isAdminNavDisplayed() {
		return adminNav.isDisplayed();
	}

	public boolean isInventoryNavDisplayed() {
		return inventoryNav.isDisplayed();

	}

	public boolean isRuleNavDisplayed() {
		return ruleNav.isDisplayed();

	}

	public boolean isdashBoardDisplayed() {
		return dashBoardNav.isDisplayed();

	}

	// subMenu Publisher of Admin main menu
	public boolean ispublisherUndrAdminDisplayed() {
		return publisherUndrAdmin.isDisplayed();

	}

	// subMenu Users of Admin main menu
	public boolean isusersUndrAdminDisplayed() {
		return usersUndrAdmin.isDisplayed();

	}

	// subMenu Demand Source of Admin main menu
	public boolean isdemandSourcesUndrAdminDisplayed() {
		return demandSourcesUndrAdmin.isDisplayed();

	}

	// subMenu Buyer of Admin main menu
	public boolean isbuyersUndrAdminDisplayed() {
		return buyersUndrAdmin.isDisplayed();

	}

	// subMenu of Inventory main menu

	// subMenu Media of Inventory main menu
	public boolean ismediaUndrInventoryDisplayed() {
		return mediaUndrInventory.isDisplayed();

	}

	// subMenu Adspots of Inventory main menu
	public boolean isadspotsUndrInventoryUndrInventoryDisplayed() {
		return adspotsUndrInventory.isDisplayed();

	}

	// subMenu of Rules main menu
	// subMenu Filters of Rules main menu
	public boolean isfiltersUndrRulesDisplayed() {
		return filtersUndrRules.isDisplayed();

	}

	// subMenu Targeting of Rules main menu
	public boolean istargetingUndrRulesDisplayed() {
		return targetingUndrRules.isDisplayed();

	}

	// Clicking expansion of Admin

	public void expandAdmin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(2000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(adminExpensionBtn));
		element.click();
		

	}

	// Clicking expansion of Inventory

	public void expandInventory() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(inventoryExpensionBtn));
		element.click();

	}
	
	// Clicking expansion of Sales

		public void expandSales() {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(salesExpensionBtn));
			element.click();

		}
	// Clicking expansion of Rules

	public void expandRules() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(rulesExpensionBtn));
		element.click();

	}

	// click on the next page navigation button
	public void clickNextPageNav() {
		if (nextPageNavButton.isDisplayed()) {
			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
			jse2.executeScript("arguments[0].scrollIntoView()", nextPageNavButton);
			nextPageNavButton.click();
		}
	}

	// click on the previous page navigation button
	public void clickPreviousPageNav() {
		if (previousPageNavButton.isDisplayed()) {
			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
			jse2.executeScript("arguments[0].scrollIntoView()", previousPageNavButton);
			previousPageNavButton.click();
		}
	}

	// click on selection dropdown to select the no. of rows per page
	public void clickNoOfPagesDropDown() {
		if (rowsPerPageDropDown.isDisplayed()) {
			rowsPerPageDropDown.click();
		}
	}

	// Get the from and to page no. shown in the current page
	public String getPaginationText() {
		String paginationText = null;
		if (paginationCount.isDisplayed()) {
			paginationText = paginationCount.getText();
		}
		return paginationText;
	}

	public void clickTableOptions() {
		if (tableOptions.isDisplayed()) {
			tableOptions.click();
		}
	}

	public void clickHideShowCheckBox() {

		WebElement hideShowChk = driver.findElement(
				By.xpath("//div[@role='menu']//label[text()='" + tableOptionsLabel + "']/preceding-sibling::div"));
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].scrollIntoView()", hideShowChk);
		hideShowChk.click();
	}

	public String checkTableOptionsChkStatus() {
		WebElement hideShowChk = driver.findElement(By
				.xpath("//div[@role='menu']//label[text()='" + tableOptionsLabel + "']/preceding-sibling::div/input"));
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].scrollIntoView()", hideShowChk);
		String checkStatus = hideShowChk.getAttribute("aria-checked");
		return checkStatus;

	}
	public void clickHideShowRadioBox() {

		WebElement hideShowChk = driver.findElement(
				By.xpath("//div[contains(@class,'radio')]//label[text()='" + tableOptionsLabel + "']/preceding-sibling::div"));
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].scrollIntoView()", hideShowChk);
		hideShowChk.click();
	}

	public String checkTableOptionsRadioChkStatus() {
		WebElement hideShowChk = driver.findElement(By
				.xpath("//div[contains(@class,'radio')]//label[text()='" + tableOptionsLabel + "']/preceding-sibling::div/input"));
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].scrollIntoView()", hideShowChk);
		String checkStatus = hideShowChk.getAttribute("aria-checked");
		return checkStatus;

	}

	public boolean isColumnHeaderDisplayed(String headerName) throws InterruptedException {
		Thread.sleep(3000);
		boolean isDisplayed = driver
				.findElements(
						By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + headerName + "']"))
				.size() != 0;
		return isDisplayed;

	}

	public List<WebElement> getColumnDataMatchingHeader(String filter) throws InterruptedException {
		Thread.sleep(5000);
		int headerIndex = 0;
		List<WebElement> numberOFHeaders = tableHeadersList;
		for (int i = 0; i < numberOFHeaders.size(); i++) {
			if (numberOFHeaders.get(i).getText().equals(filter)) {
				headerIndex = i + 1;
			}
		}
		int columnIndex = headerIndex + 1;
		List<WebElement> coulmnData = driver
				.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[" + columnIndex + "]"));
		return coulmnData;

	}

	public void clickDashBoardNav() {
		if (dashBoardNav.isDisplayed()) {
			dashBoardNav.click();
		}
	}

}
