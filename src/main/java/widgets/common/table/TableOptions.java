package widgets.common.table;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TableOptionsElements.*;

/**
 * Keep Selectors of UI elements in {@link TableOptionsElements}
 */
@Getter
public class TableOptions {

    private SelenideElement tableOptionsBtn = $x(TABLE_OPTIONS_COMPONENTS_BUTTON.getSelector()).as(TABLE_OPTIONS_COMPONENTS_BUTTON.getAlias());
    private SelenideElement menuOptions = $x(TABLE_OPTIONS_MENU.getSelector()).as(TABLE_OPTIONS_MENU.getAlias());
    private SelenideElement optionsList = $x(OPTIONS_LIST.getSelector()).as(OPTIONS_LIST.getAlias());

    public SelenideElement getMenuItemCheckbox(ColumnNames column) {

        return $x(String.format(MENU_ITEM_CHECKBOX.getSelector(), column.getName())).as(MENU_ITEM_CHECKBOX.getAlias());
    }

    public SelenideElement getStatusItemRadio(Statuses status) {

        return $x(String.format(ITEM_STATUS_RADIO.getSelector(), status.getStatus())).as(ITEM_STATUS_RADIO.getAlias());
    }

}