package RXPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class RXDemandSourcesPage extends RXBasePage{
    public String valueInSelectTable = "//table[contains(@class,'select-table')]/tbody/tr/td/div[normalize-space(text())='%s']/parent::td/following-sibling::td[contains(@class,'options')]";
    public String valueInIncludedTable = "//table[contains(@class,'included-table')]/tr/td/div[contains(text(),'%s')]";
    public String removeIconForValueInIncludedTable = "//table[contains(@class,'included-table')]//div[normalize-space(text())='%s']/parent::td/following-sibling::td[@class='options']/button";

    //Header of Demand Sources page
    @FindBy(xpath = "//h1[text()='Demand']")
    public WebElement demandPageHeader;
    @FindBy(xpath = "//label[text()='Request Adjustment Rate']/following-sibling::input")
    public WebElement requestAdjustmentRate;
    @FindBy(xpath = "//button[@type='submit']/span")
    public WebElement saveButton;

    //Search text field
    @FindBy(xpath = "//input[@autocomplete='nope']")
    public WebElement searchField;

    //Endpoint URI input
    @FindBy(xpath = "//label[text()='Endpoint URI']/following-sibling::input")
    public WebElement endpointURIText;

    //Endpoint URI error
    @FindBy(xpath = "//div[@class='v-messages__message']")
    public WebElement endpointURIError;

    @FindBy(xpath = "//label[text()='Country']/following-sibling::input")
    public WebElement countrySelector;

    @FindBy(xpath = "//button[contains(@class,'select-all')]")
    public WebElement includeAllBtn;

    @FindBy(xpath = "//span[text()='Clear All']/parent::button")
    public WebElement clearAllBtn;

    @FindAll(@FindBy(xpath = "//table[contains(@class,'select-table')]/tbody/tr[@class='select-row']/td[@class='first']/div"))
    public List<WebElement> allItemsListInSelectTable;

    @FindAll(@FindBy(xpath = "//table[contains(@class,'included-table')]/tr/td/div"))
    public List<WebElement> allItemsListInIncludedTable;

    @FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[3]/a")
    public WebElement theFirstItemOnBidderColumn;

    //Explicit Wait
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public RXDemandSourcesPage(){
        PageFactory.initElements(driver, this);
    }

    public String getUserPageHeading()
    {
        WebElement elem = wait.until(ExpectedConditions.visibilityOf(demandPageHeader));
//        System.out.println(elem.getText());
        return elem.getText();
    }

    public List<WebElement> demandActives() {
        return driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[10]"));
    }

    public WebElement demandActive(int k) {
        return driver
                .findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[10]"));
    }

    public WebElement demandCheckBox(int k) {
        return driver
                .findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[1]"));
    }

    public String demandNo(int k) {
        return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[2]"))
                .getText();
    }

    public List<WebElement> demandNos() {
        return driver
                .findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[2]"));
    }

    public WebElement toolbarButton(String e) {
            return driver
                    .findElement(By.xpath("//span[contains(text() , '" + e + "')]/parent::button"));
    }

    public WebElement getBidder_column(int k){
        return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']/table/tbody/tr["+k+"]/td[3]"));
    }

   public WebElement getBidder_column(String arg1) {
       return driver
               .findElement(By.linkText(arg1));
    }

    public WebElement editPagePresent(){
        return driver.findElement(By.xpath("//aside[@class='dialog']"));
    }

    public WebElement demandItem(int i) {
        return driver
                .findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+i+"]"));
    }

    public WebElement getStatusRadioButton(String statusName) {
        return driver.findElement(By.xpath("//label[text()='"+statusName+"']/preceding-sibling::div"));
    }

    public WebElement getToggle(String toggles) {
        return driver.findElement(By.xpath("//label[text()='"+toggles+"']/preceding-sibling::div"));
    }

    public String Is_DSP_Toggle_active(String toggles) {
        return driver.findElement(By.xpath("//label[text()='"+toggles+"']/preceding-sibling::div/input")).getAttribute("aria-checked");
    }

    public String Is_DSP_Toggle_disable(String toggles) {
        return driver.findElement(By.xpath("//label[text()='"+toggles+"']/preceding-sibling::div/input")).getAttribute("disabled");
    }

    public String IsRadioButtonSelected(String statusName) {
        return driver.findElement(By.xpath("//label[text()='"+statusName+"']/preceding-sibling::div/input")).getAttribute("aria-checked");
    }

    public WebElement tableOptionsCheckbox(String columnName){
        return driver.findElement(By.xpath("//label[text()='"+columnName+"']/preceding-sibling::div"));
    }

    public void selectTableOptionsCheckbox(String e) {
        if(!this.istableOptionsCheckboxSelected(e).equals("true")){
            this.tableOptionsCheckbox(e).click();
        }
    }

    public String istableOptionsCheckboxSelected(String columnName){
        return driver.findElement(By.xpath("//label[text()='"+columnName+"']/preceding-sibling::div/input")).getAttribute("aria-checked");
    }

    public void is_columns_displayed_on_the_Demand_sources_page(String columnName) {
        Assert.assertTrue(driver.findElement(By.xpath("//thead[@class='v-data-table-header']/tr/th/span[text()='"+columnName+"']")).isDisplayed());
    }

    public boolean IsElementPresent(WebElement element)
    {
        try
        {
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
}
