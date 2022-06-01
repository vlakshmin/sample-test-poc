package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TableOptionsElements {

    TABLE_OPTIONS_COMPONENTS_BUTTON( "'Table Options' button", "//button/span[contains(text(),'Table Options')]"),
    MENU_ITEM_CHECKBOX( "Menu Item Checkbox", "//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div/input"),
    MENU_ITEM_CHECKBOX_( "Menu Item Checkbox_", "//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div/i"),
    ITEM_STATUS_RADIO( "Menu item 'Status' RadioButton", "//*[@class='v-radio radio theme--light']//label[text()='%s']/../div"),
    SEARCH( "Search Textbox", "//*[@class='v-text-field__slot']/label[text()='Search']/../input"),
    CLEAR( "Clear Icon", "//*[@class='v-input__icon v-input__icon--clear']/button"),
    MENU_ITEM( "Menu item", "//*[@class='v-list v-sheet theme--light']//*[@class='v-input__slot']/label[text()='%s']/../div"),
    TABLE_OPTIONS_MENU( "Table Options Menu", "//*[@class='v-list v-sheet theme--light']");

    private String alias;
    private String selector;
}
