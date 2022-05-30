package widgets.common.table;

import RXBaseClass.RXBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaginationComponent extends RXBaseClass {

    public WebElement getPaginationPanel() {
        return driver.findElement
                (By.xpath("//*[@class='v-data-footer']/div[@class='v-data-footer__pagination']"));
    }

    public String getPaginationPanelText() {
        return driver.findElement
                (By.xpath("//*[@class='v-data-footer']/div[@class='v-data-footer__pagination']")).getText();
    }

    public String getPaginationTextTotalRows() {
        String text = driver.findElement
                (By.xpath("//*[@class='v-data-footer']/div[@class='v-data-footer__pagination']")).getText();
        int index = text.lastIndexOf("of");
        return text.substring(index + 3, text.length());
    }

    public WebElement getRowNumbersList() {
        return driver.findElement
                (By.xpath("//*[@class='v-data-footer']/div[@class='v-data-footer__select']//div[@class='v-select__selections']/div"));
    }

    public void selectRowNumbers(String rowNumbers) {
        driver.findElement(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active']"
                + "//div[@class='v-list-item__title' and text()='" + rowNumbers + "']")).click();
    }

    public WebElement getPageMenu() {
        return driver.findElement(By.xpath("//*[@class='v-data-footer']//div[@class='v-input__slot']"));
    }

    public WebElement getNext() {
        return driver.findElement
                (By.xpath("//div[@class='v-data-footer__icons-after']/button"));
    }

    public WebElement getPrevious() {
        return driver.findElement
                (By.xpath("//div[@class='v-data-footer__icons-before']/button"));
    }
}