package widgets.common.table;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.BasePageElements.LOGO;
import static widgets.common.table.TableOptionsElements.*;

/**
 * Keep Selectors of UI elements in {@link TableOptionsElements}
 */
@Getter
public class TableOptions extends BasePage {
//
    private SelenideElement search = $x(SEARCH.getSelector()).as(SEARCH.getAlias());
    private SelenideElement tableOptionsBtn = $x(TABLE_OPTIONS_COMPONENTS_BUTTON.getSelector()).as(TABLE_OPTIONS_COMPONENTS_BUTTON.getAlias());
//    private SelenideElement menuOptions = $x(TABLE_OPTIONS_MENU.getSelector()).as(TABLE_OPTIONS_MENU.getAlias());
//    private SelenideElement menuItemCheckbox = $x(String.format(MENU_ITEM_CHECKBOX.getSelector(), menuItem)).as(MENU_ITEM_CHECKBOX.getAlias());
//    private SelenideElement menuItem = $x(String.format(MENU_ITEM.getSelector(), menuItem)).as(MENU_ITEM.getAlias());
//
    public SelenideElement getMenuItemState(String menuItem) {

        return $x(String.format(MENU_ITEM_CHECKBOX.getSelector(), menuItem)).as(MENU_ITEM_CHECKBOX.getAlias());
    }

    public SelenideElement getMenuItem(String menuItem) {

        return $x(String.format(MENU_ITEM_CHECKBOX_.getSelector(), menuItem)).as(MENU_ITEM_CHECKBOX_.getAlias());
    }
//
//    public SelenideElement getStatusItemRadio(String status) {
//
//        return $x(String.format(ITEM_STATUS_RADIO.getSelector(), status)).as(ITEM_STATUS_RADIO.getAlias());
//    }
//
//    public SelenideElement getClear() {
//
//        return $x(CLEAR.getSelector()).as(CLEAR.getAlias());
//    }

}