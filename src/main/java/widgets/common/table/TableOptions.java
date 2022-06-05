package widgets.common.table;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.BasePageElements.LOGO;
import static widgets.common.table.TableOptionsElements.*;

/**
 * Keep Selectors of UI elements in {@link TableOptionsElements}
 */
@Getter
public class TableOptions {


    private SelenideElement tableOptionsBtn = $x(TABLE_OPTIONS_COMPONENTS_BUTTON.getSelector()).as(TABLE_OPTIONS_COMPONENTS_BUTTON.getAlias());
    private SelenideElement menuOptions = $x(TABLE_OPTIONS_MENU.getSelector()).as(TABLE_OPTIONS_MENU.getAlias());
    private SelenideElement optionsList = $x(OPTIONS_LIST.getSelector()).as(OPTIONS_LIST.getAlias());
    @Getter(AccessLevel.NONE)
    private SelenideElement menuItemCheckbox;

    public SelenideElement getMenuItemCheckbox(ColumnNames column) {

        return $x(String.format(MENU_ITEM_CHECKBOX.getSelector(),column.getName())).as(MENU_ITEM_CHECKBOX.getAlias());
    }

    public SelenideElement getCheckboxParent(String param){

        return $x(String.format("//label[text()='%s']/../div",param)).parent();
    }

    public SelenideElement getStatusItemRadio(Statuses status) {

        return $x(String.format(ITEM_STATUS_RADIO.getSelector(), status.getStatus())).as(ITEM_STATUS_RADIO.getAlias());
    }


}