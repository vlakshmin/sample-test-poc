package RXPages;

import RXBaseClass.RXBaseClass;
import cucumber.api.DataTable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class RXBasePage extends RXBaseClass {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindAll(@FindBy(xpath = "//div[@class='v-progress-linear__buffer']"))
    public WebElement tableLoaderBar;
    @FindAll({@FindBy(xpath = "//div[contains(@role,'listbox') and contains(@class,'v-list')]/div")})
    public List<WebElement> dropdownValues;
    @FindBy(xpath = "//tr//td//button[contains(@class,'v-btn--round')]")
    private WebElement detailsButton;
    @FindBy(xpath = "//div[contains(@class,'detailsList')]/ancestor::div[contains(@class, 'menu-wrapper')]")
    private WebElement detailsCard;

    public void createButtonClick(String buttonName, String headerName) throws InterruptedException {
        driver.findElement(By.xpath("//button/span[text()='" + buttonName + "']")).click();
        driverWait().until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
        driverWait().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + headerName + "')]"))));
    }

    public void createButtonClick(String buttonName) throws InterruptedException {
        createButtonClick(buttonName, buttonName);
    }

    public void selectValueFromDropdown(String name) {
        int attempt = 0;

        // Check if list contains parameter name, scroll down if not
        do {
            js.executeScript("arguments[0].scrollIntoView(false)", dropdownValues.get(dropdownValues.size() - 1));
        }
        while (!dropdownValues.stream()
                .map(WebElement::getText)
                .anyMatch(text -> name.equals(text)) && attempt++ < 20);

        // Get web element by name from the method parameter
        WebElement dropDownElementByName = dropdownValues.stream()
                .filter(i -> i.getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(String.format("Dropdown element by the name %s wasn't found.", name)));
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"})", dropDownElementByName);
        driverWait().until(elementToBeClickable(dropDownElementByName));
        dropDownElementByName.click();
    }

    public void waitForPageLoaderToDisappear() {
//        driverWait().until(ExpectedConditions.numberOfElementsToBe(By.className("v-progress-linear__buffer"), 0));
        driverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='v-progress-linear__buffer']")));
    }

    public void hoverOverDetailsButton() {
        driverWait().until(ExpectedConditions.visibilityOf(detailsButton));
        new Actions(driver).moveToElement(detailsButton).build().perform();
        driverWait().until(ExpectedConditions.attributeToBe(detailsButton, "aria-expanded","true"));
    }

    public LinkedHashMap<String,String> getDetailsData() {
        LinkedHashMap<String,String> result;
        result = detailsCard.findElements(By.xpath("//div[@class='header']")).stream()
            .collect(Collectors.toMap(WebElement::getText, e -> {
                WebElement element = e.findElement(By.xpath("./..//span"));
                driverWait().until(ExpectedConditions.elementToBeClickable(element));
                return e.findElement(By.xpath("./..//span")).getText();
            }, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(result);
        return result;
    }

    public boolean areEqual(Map<String, String> first, Map<String, String> second) {
        if (first.size() != second.size()) {
            return false;
        }
        return first.entrySet().stream()
            .allMatch(e -> e.getValue().toLowerCase().equals(second.get(e.getKey()).toLowerCase()));
    }
}
