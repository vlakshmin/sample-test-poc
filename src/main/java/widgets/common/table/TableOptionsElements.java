package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TableOptionsElements {

    TABLE_OPTIONS_COMPONENTS_BUTTON( "'Table Options' button", "//button/span[contains(text(),'Table Options')]"),
    OPTIONS_LIST( "'Table Options' List", "//*[@class='v-menu__content theme--light menuable__content__active']"),
    MENU_ITEM_CHECKBOX( "Menu Item Checkbox", "//label[text()='%s']/..//input"),
    ITEM_STATUS_RADIO( "Menu item 'Status' RadioButton", "//div[@role='radiogroup']//label[text()='%s']/..//input"),
    MENU_ITEM( "Menu item", "//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div"),
    TABLE_OPTIONS_MENU( "Table Options Menu", "//*[@class='v-list v-sheet theme--light']");

    private String alias;
    private String selector;


}
