package RXPages;

import RXBaseClass.RXBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserRolesPage extends RXBaseClass {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public UserRolesPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href,'profile')]")
    public WebElement userInfo;
    @FindBy(xpath = "//div[@class='v-list-item__title'][text()='Publishers']")
    public WebElement publisherNameMainMenu;
    @FindBy(xpath = "//span[contains(text(),'Logout')]")
    public WebElement logoutBtn;
    @FindAll(@FindBy(xpath = "//div[contains(@class,'v-card')]//div[@class='v-list-item__subtitle']"))
    public List<WebElement> userInfoSubTitleList;
    @FindAll(@FindBy(xpath = "//div[@class='v-card__title']"))
    public List<WebElement> userInfoCardTitleList;
    @FindBy(xpath = "//div[@class='v-list-item__subtitle' and contains(text(),'Publisher')]/preceding-sibling::div/a")
    public WebElement pubNameLinkInUserInfo;
    @FindAll({@FindBy(xpath = "//div[contains(@role,'listbox') and contains(@class,'v-list')]/div")})
    public List<WebElement> dropdownValues;
    @FindBy(xpath = "//header/div[@class='v-toolbar__content']/button")
    public WebElement closeBtn;
    @FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::input")
    public WebElement pubNameInput;
    @FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/div")
    public WebElement pubNameValue;

    @FindBy(xpath = "//label[text()='Currency']/following-sibling::input")
    public WebElement currencyInput;
    @FindBy(xpath = "//label[text()='Categories']/following-sibling::div/input")
    public WebElement categoriesInput;
    @FindBy(xpath = "//label[text()='Demand Source']/following-sibling::div/input")
    public WebElement demandSourceInput;

    public String closeBtnString = "//header/div[@class='v-toolbar__content']/button";
    public String radioBtnString = "//div[@role='radiogroup']//label/span[contains(text(),'%s')]";
    public String subMenuString = "//div[@class='v-list-item__title'][text()='%s']";

    public boolean checkIfValueExist(List<WebElement> elementList, String value){
        boolean flag = false;
        for(WebElement element : elementList){
            if(element.getText().trim().equals(value)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void clickCloseButton(){
        System.out.println("Close create page");
        this.closeBtn.click();
        driverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.closeBtnString)));
    }

    public void scrollDownInDropdown(){
        int attempt = 0;
        do {
            js.executeScript("arguments[0].scrollIntoView(false)", dropdownValues.get(dropdownValues.size() - 1));
        }
        while (attempt++ < 20);
    }

    public boolean isElementPresent(String path){
        try{
            driver.findElement(By.xpath(path));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }
}
