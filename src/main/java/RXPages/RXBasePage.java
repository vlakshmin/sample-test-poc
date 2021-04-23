package RXPages;

import RXBaseClass.RXBaseClass;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RXBasePage extends RXBaseClass {

    public void createButtonClick(String buttonName, String headerName) throws InterruptedException {
        driver.findElement(By.xpath("//button/span[text()='" + buttonName + "']")).click();
        driverWait().until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
        driverWait().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + headerName + "')]"))));
    }

    public void createButtonClick(String buttonName) throws InterruptedException {
        createButtonClick(buttonName, buttonName);
    }
}
