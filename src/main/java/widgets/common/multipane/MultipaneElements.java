package widgets.common.multipane;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneElements {

    INCLUDE_ALL_BUTTON( "'Include All' button in Multipane", "//span/div[contains(text(),'Include All')]/.."),
    CLEAR_ALL_BUTTON( "'Clear All' button in Multipane", "//span[contains(text(),'Clear All')]"),
    SEARCH_INPUT("'Search' Input in Multipane", "//input[@placeholder='Search']"),
    ITEMS_QUANTITY_STRING("'Items Quantity' String in Multipane", "//div[@class='item-count']"),
    SHOW_INACTIVE_TOGGLE("'Show Inactive' Toggle in Multipane",  "//input[@role='switch']/..");

    private String alias;
    private String selector;
}
