package widgets.common.table;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TableOptionsElements.TABLE_OPTIONS_COMPONENTS_BUTTON;

/**
 * Keep Selectors of UI elements in {@link TableOptionsElements}
 */
public class TableOptions {

    public SelenideElement getTableOptionsButton() {

        return $x(TABLE_OPTIONS_COMPONENTS_BUTTON.getSelector()).as(TABLE_OPTIONS_COMPONENTS_BUTTON.getAlias());
    }

    // Todo refactor as getTableOptionsButton. Keep selectors in TableOptionsComponentElements enumeration

    /*
    public WebElement getMenuOptions() {
        return driver.findElement(By.xpath("//*[@class='v-list v-sheet theme--light']"));
    }

    public WebElement getMenuItemState(String menuItem) {
        return driver.findElement
                (By.xpath(String.format("//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div/input", menuItem)));
    }

    public WebElement getMenuItem(String menuItem) {
        return driver.findElement
                (By.xpath(String.format("//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div", menuItem)));
    }

    public WebElement getStatusItem(String status) {
        return driver.findElement
                (By.xpath(String.format("//*[@class='v-radio radio theme--light']//label[text()='%s']/../div", status)));
    }

    public WebElement getSearchInput() {
        return driver.findElement
                (By.xpath("//*[@class='v-text-field__slot']/label[text()='Search']/../input"));
    }

    public WebElement getClear() {
        return driver.findElement
                (By.xpath("//*[@class='v-input__icon v-input__icon--clear']/button"));
    }
     */
}