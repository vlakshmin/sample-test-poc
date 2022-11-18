package widgets.common.singlepanefilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SinglepaneElements {

    SEARCH_INPUT("'Search' Input in Singlepane", "//input[@placeholder='Search']"),
    HEADER("Filter header in Singlepane","//div[@class='v-toolbar__title filter-title']"),
    SUBMIT_BUTTON("Submit' Button in Singlepane", "//button/span[contains(text(),'Submit')]"),
    CANCEL_BUTTON("'Cancel' Button in Singlepane", "//button/span[contains(text(),'Cancel')]"),
    CLEAR_ALL_BUTTON( "'Clear All' button in Multipane", "//span/div[contains(text(),'Clear All')]"),
    SEARCH_ICON("'Clear Search' Input Icon in Singlepane", "//div/i[contains(@class,'mdi-magnify')]"),
    BACK_BUTTON("'Back' Button in Singlepane", "//span/i[contains(@class,'mdi-keyboard-backspace')]"),
    PANEL_NAME_LABEL("'Filter Name' Label in Singlepane", "//div/div[contains(@class,'filter-title')]"),
    INCLUDE_ALL_BUTTON( "'Include All' button in Singlepane", "//span/div[contains(text(),'Include All')]"),
    TOTAL_ITEMS_QUANTITY_STRING("'Total Items Quantity' String in Singlepane", "//span/div[contains(text(),'Include All')]/../div[@class='item-count']"),
    INCLUDED_ITEMS_QUANTITY_STRING("'Included Items Quantity' String in Singlepane", "//span/div[contains(text(),'Clear All')]/../div[@class='item-count']"),

    SELECT_TABLE_ITEMS("Select Table Elements","//table[contains(@class,'select-table')]/tbody");

    private String alias;
    private String selector;
}
