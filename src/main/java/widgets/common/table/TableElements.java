package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableElements {

    BUTTON_SUFFIX("Button Suffix","/button"),
    SEARCH( "Search Textbox", "//*[@class='v-text-field__slot']/label[text()='Search']/../input"),
    CLEAR( "Clear Icon", "//*[@class='v-input__icon v-input__icon--clear']/button"),
    CHECKBOX( "Row Checkbox", "//table//tr[%s]/td[1]/div//div"),
    ROWS( "Table Rows", "//tbody/tr"),
    CELL_BY_COLUMN( "Cell By Column", "//tbody/tr/td[%s]"),
    COLUMNS( "Table Columns", "//thead/tr/th/span"),
    COLUMN_HEADER( "Column Header", "//div[@class='v-data-table__wrapper']//thead//th/span[text()='%s']/parent::th"),
    BODY( "Table Body", "//body//table[1]");

    private String alias;
    private String selector;
}
