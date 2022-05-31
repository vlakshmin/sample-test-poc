package widgets.common.table;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TableOptionsElements.*;

/**
 * Keep Selectors of UI elements in {@link TableOptionsElements}
 */
public class TableOptions {

    public SelenideElement getTableOptionsButton() {

        return $x(TABLE_OPTIONS_COMPONENTS_BUTTON.getSelector()).as(TABLE_OPTIONS_COMPONENTS_BUTTON.getAlias());
    }

    public SelenideElement getMenuOptions() {

        return $x(TABLE_OPTIONS_MENU.getSelector()).as(TABLE_OPTIONS_MENU.getAlias());
    }

    public SelenideElement getMenuItemCheckbox(String menuItem) {

        return $x(String.format(MENU_ITEM_CHECKBOX.getSelector(), menuItem)).as(MENU_ITEM_CHECKBOX.getAlias());
    }

    public SelenideElement getMenuItem(String menuItem) {

        return $x(String.format(MENU_ITEM.getSelector(), menuItem)).as(MENU_ITEM.getAlias());
    }

    public SelenideElement getStatusItemRadio(String status) {

        return $x(String.format(ITEM_STATUS_RADIO.getSelector(), status)).as(ITEM_STATUS_RADIO.getAlias());
    }

    public SelenideElement getSearchInput() {
        return $x(SEARCH.getSelector()).as(SEARCH.getAlias());
    }

    public SelenideElement getClear() {

        return $x(CLEAR.getSelector()).as(CLEAR.getAlias());
    }

}