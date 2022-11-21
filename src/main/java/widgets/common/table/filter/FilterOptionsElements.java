package widgets.common.table.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterOptionsElements {

    FILTER_OPTIONS_ITEMS( "'Filter Options' Elements", "//div[@role='menuitem']"),
    FILTER_OPTION_BY_NAME("'%s' Filter Option", "//div[@role='menuitem']//div[text()='%s']"),
    FILTER_OPTIONS_MENU( "Filter Options Menu", "//*[@class='v-list v-sheet theme--light']"),
    COLUMN_FILTERS_BUTTON( "'Column Filters' button", "//button/span[contains(text(),'Column Filters')]/..");

    private String alias;
    private String selector;
}
