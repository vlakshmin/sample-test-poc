package RXPages;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RXPrivateAuctionsPage extends RXBaseClass {
    // Utility object
    RXUtile rxUTL;
    PublisherListPage pubPage;
    RXDashboardPage dashboardPage;
    public String auctionHeaderStr = "Private Auctions";
    List<List<WebElement>> listOfLists = new ArrayList<>();

    // Seats page heading
    @FindBy(xpath = "//h1[text()='Private Auctions']")
    WebElement auctionPageHeader;

    // overview buttons
    @FindBy(xpath = "//button/span[text()='Edit Private Auction']")
    public WebElement overviewEditbutton;
    @FindBy(xpath = "//button/span[text()='Deactivate Private Auction']")
    public WebElement overviewDisablebutton;
    @FindBy(xpath = "//div[@class='portal vue-portal-target']/button[2]/span")
    public WebElement overviewEnablebutton;
    @FindBy(xpath = "//label[text()='Name']/following-sibling::input")
    public WebElement auctionNameField;
    @FindBy(xpath = "//label[text()='Related Packages']/following-sibling::input")
    public WebElement auctionPackages;
    @FindBy(xpath = "//button[@type='submit'][1]")
    public WebElement saveandcloseButton;
    @FindBy(xpath = "//button[@type='submit'][2]")
    public WebElement saveandcreatedealButton;
    @FindBy(xpath = "//label[contains(text(), 'Date Range')]/ancestor::div[@class = 'v-input__slot']")
    public WebElement dateRangeContainer;
    @FindBy(xpath = "//label[contains(text(), 'Date Range')]/following-sibling::input")
    public WebElement dateInput;
    @FindBy(xpath = "//label[text() = 'Publisher Name']/ancestor::div[@role = 'button']" +
            "//div[@class = 'v-select__selections']")
    public WebElement privateAuctionNameField;
    @FindBy(xpath = "//label[text() = 'Publisher Name']/ancestor::div[@role = 'button']//input[@type = 'text']")
    public WebElement privateAuctionNameInput;
    @FindBy(xpath = "//span[text() = 'Create Private Auction']/ancestor::button")
    public WebElement createPrivateAuctionButton;

    public WebElement publisherNameSearchListItem(String publisherName) {
        return driver.findElement(By.xpath("//div[@class = 'v-list-item__title' and text() = '" + publisherName + "']"));
    }

    public WebElement targetingExpandPanel(String targetingName) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]"));
    }

    public WebElement targetingPanelBlock(String targetingName) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]"));
    }

    public WebElement targetingBlockButton(String targetingName, String targetingBlockButton) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//div[contains(text(), '" +
                targetingBlockButton + "')]"));
    }

    public WebElement targetingIncludedBlockButton(String targetingName, String targetingBlockButton) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//span[contains(text(), '" +
                targetingBlockButton + "')]"));
    }

    public WebElement listCounter(String targetingName) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//div[@class = 'item-count']"));
    }

    public WebElement includedCounter(String targetingName) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//div[@class = 'selectionInfo']"));
    }

    public WebElement targetingBlockSearchInput(String targetingName) {
        return driver.findElement(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//input"));
    }

    public WebElement targetingBlockSearchInputClear(String targetingName) {
        return targetingPanelBlock(targetingName).findElement(By.xpath("//button[@aria-label = 'clear icon']"));
    }

    public WebElement targetingBlockListItem(String itemName) {
        return driver.findElement(By.xpath("//div[contains(text() , '" + itemName + "')]/ancestor::tbody"));
    }

    public WebElement targetingBlockIncludedListItem(String itemName) {
        return driver.findElement(By.xpath("//div[contains(@class , 'pane results')]//div[contains(text() , '" + itemName + "')]"));
    }

    public WebElement targetingBlockIncludedListItemClear(String itemName) {
        return driver.findElement(By.xpath("//div[contains(@class , 'pane results')]//div[contains(text() , '" + itemName +
                "')]/ancestor::table//button"));
    }

    public List<WebElement> targetingBlockItemsList(String targetingName) {
        return driver.findElements(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//td[@class = 'first']/ancestor::tbody"));
    }

    public List<WebElement> targetingBlockIncludedItemsList(String targetingName) {
        return driver.findElements(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]//table[@class = 'included-table fixed']//td[text()]"));
    }

    // Action object
    Actions act = new Actions(driver);

    // Array for test data
    static ArrayList<String> testData = new ArrayList<String>();

    // Explicit Wait
    WebDriverWait wait = new WebDriverWait(driver, 1000);

    // Initialize page factory
    public RXPrivateAuctionsPage() {
        PageFactory.initElements(driver, this);
        rxUTL = new RXUtile();
        pubPage = new PublisherListPage();
        dashboardPage = new RXDashboardPage();

    }

    // Get the text of the media page
    public String getPageHeading() {

        WebElement elem = wait.until(ExpectedConditions.visibilityOf(auctionPageHeader));
        System.out.println(elem.getText());
        return elem.getText();

    }

    public void clickOverViewEditbutton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(overviewEditbutton));
        if (overviewEditbutton.isDisplayed()) {
            overviewEditbutton.click();
        }
    }

    public void clickOverViewEnablebutton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(overviewEnablebutton));
        String enableText = overviewEnablebutton.getText().replaceAll("\\s", "");
        if (enableText.equals("ACTIVATEPRIVATEAUCTION")) {
            overviewEnablebutton.click();
        }
    }

    public void clickOverViewDisablebutton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(overviewDisablebutton));
        if (overviewDisablebutton.isDisplayed()) {
            overviewDisablebutton.click();
        }
    }

    public void selectFifteenDaysRangeInNextMonth() throws InterruptedException {
        openDatePicker();
        selectNextMonth();
        dashboardPage.selectSpecificDateRange("1", "15");
        dateRangeContainer.click();
    }

    public void selectPublisher(String publisherName) {
        driverWait().until(ExpectedConditions.visibilityOf(privateAuctionNameField));
        privateAuctionNameField.click();
        privateAuctionNameInput.sendKeys(publisherName);
        wait.until(ExpectedConditions.visibilityOf(publisherNameSearchListItem(publisherName)));
        publisherNameSearchListItem(publisherName).click();
    }

    public void clickCreateNewPrivateAuctionButton() {
        driverWait().until(ExpectedConditions.visibilityOf(createPrivateAuctionButton));
        createPrivateAuctionButton.click();
    }

    public void selectTargetingBlockListItem(String itemName) {
        targetingBlockListItem(itemName).click();
    }

    public void clickTargetingBlockIncludedListItemClear(String itemName) {
        targetingBlockIncludedListItemClear(itemName).click();
    }

    public void checkSelectUnselectForBlock(String targetingName, String itemName) {
        targetingExpandPanel(targetingName).click();
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockListItem(itemName)));
        selectTargetingBlockListItem(itemName);
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockIncludedListItem(itemName)));
        Assert.assertTrue(targetingBlockIncludedListItem(itemName).isDisplayed());
        selectTargetingBlockListItem(itemName);
        Assert.assertFalse(isTargetingBlockIncludedListItem(itemName));
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockListItem(itemName)));
        selectTargetingBlockListItem(itemName);
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockIncludedListItem(itemName)));
        Assert.assertTrue(targetingBlockIncludedListItem(itemName).isDisplayed());
        clickTargetingBlockIncludedListItemClear(itemName);
        Assert.assertFalse(isTargetingBlockIncludedListItem(itemName));
        targetingExpandPanel(targetingName).click();
        Assert.assertFalse(isTargetingBlockOpen(targetingName));
    }

    public boolean isTargetingBlockIncludedListItem(String itemName) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        boolean result = driver.findElements(By.xpath("//table[@class = 'included-table']//td[contains(text() , '" +
                itemName + "')]")).size() != 0;
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return result;
    }

    public boolean isTargetingBlockOpen(String targetingName) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        boolean result = driver.findElements(By.xpath("//h3[text() = '" + targetingName +
                "']/ancestor::button[contains(@class, 'v-expansion-panel-header flex')]/ancestor::" +
                "div[contains(@class, 'v-expansion-panel--active')]")).size() != 0;
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return result;
    }

    public void checkSelectUnselectAllForBlock(String targetingName) {
        targetingExpandPanel(targetingName).click();
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockButton(targetingName,
                "Include All")));
        targetingBlockButton(targetingName, "Include All").click();
        isAllItemAdded(targetingName);
        targetingIncludedBlockButton(targetingName, "Clear All").click();
        Assert.assertFalse(isItemAdded(targetingName));
        targetingExpandPanel(targetingName).click();
        Assert.assertFalse(isTargetingBlockOpen(targetingName));
    }

    public void checkSearchForBlock(String targetingName, String itemName) {
        targetingExpandPanel(targetingName).click();
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockSearchInput(targetingName)));
        targetingBlockSearchInput(targetingName).click();
        driverWait().until(ExpectedConditions.visibilityOf(targetingBlockSearchInput(targetingName)));
        targetingBlockSearchInput(targetingName).sendKeys(itemName);
        driverWait().until(ExpectedConditions.textToBePresentInElement(listCounter(targetingName), "1"));
        targetingBlockButton(targetingName, "Include All").click();
        driverWait().until(ExpectedConditions.textToBePresentInElement(includedCounter(targetingName), "1"));
        Assert.assertTrue(isItemAdded(targetingName));
        Assert.assertEquals(targetingBlockIncludedListItem(itemName).getText(), itemName);
        targetingExpandPanel(targetingName).click();
        Assert.assertFalse(isTargetingBlockOpen(targetingName));

    }

    public boolean isItemAdded(String targetingName) {
        try {
            return (NumberFormat.getInstance().parse(includedCounter(targetingName).getText())).intValue() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void isAllItemAdded(String targetingName) {
        for (int i = 0; i < targetingBlockItemsList(targetingName).size(); i++) {
            Assert.assertEquals(targetingBlockItemsList(targetingName).get(i).getText(), targetingBlockIncludedItemsList(targetingName).get(i).getText());
        }
    }

    public void collectIncludedItems(String targetingName) {
        listOfLists.add(targetingBlockIncludedItemsList(targetingName));
    }

    public void selectNextMonth() throws InterruptedException {
        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.nextMonthBtn));
        dashboardPage.nextMonthBtn.click();
        Thread.sleep(3000);

    }

    public void openDatePicker() {
        driverWait().until(ExpectedConditions.visibilityOf(dateInput));
        dateRangeContainer.click();
        driverWait().until(ExpectedConditions.visibilityOf(dashboardPage.previousMonthBtn));
    }
}
