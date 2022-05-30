package widgets.common.table;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TablePaginationElements.PAGE_MENU;

/**
 * Keep Selectors of UI elements in {@link TablePaginationElements}
 */
public class TablePagination {

    public SelenideElement getPageMenu() {

        return $x(PAGE_MENU.getSelector()).as(PAGE_MENU.getAlias());
    }
    // Todo refactor as getPageMenu. Keep selectors in PaginationElements enumeration
    /*
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


    public WebElement getNext() {
        return driver.findElement
                (By.xpath("//div[@class='v-data-footer__icons-after']/button"));
    }

    public WebElement getPrevious() {
        return driver.findElement
                (By.xpath("//div[@class='v-data-footer__icons-before']/button"));
    }

     */
}