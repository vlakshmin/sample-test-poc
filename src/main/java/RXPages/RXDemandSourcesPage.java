package RXPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RXDemandSourcesPage extends RXBasePage{
    //Header of Demand Sources page
    @FindBy(xpath = "//h1[text()='Demand']")
    public WebElement demandPageHeader;
    @FindBy(xpath = "//label[text()='Request Adjustment Rate']/following-sibling::input")
    public WebElement requestAdjustmentRate;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    //Explicit Wait
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public RXDemandSourcesPage(){
        PageFactory.initElements(driver, this);
    }

    public String getUserPageHeading()
    {
        WebElement elem = wait.until(ExpectedConditions.visibilityOf(demandPageHeader));
        System.out.println(elem.getText());
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

    public String getRequestAdjustmentRateInList(int k){
        return driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr["+k+"]/td[8]"))
                .getText();
    }
}
