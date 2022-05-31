package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableElements {

    CHECKBOX( "Row Checkbox", "//table//tr[%s]/td[1]/div//div"),
    ROWS( "Table Rows", "//tbody/tr"),
    COLUMNS( "Table Columns", "//thead/tr/th/span"),
    COLUMN_HEADER( "Column Header", "//div[@class='v-data-table__wrapper']//thead//th/span[text()='%s']/parent::th"),
    BODY( "Table Body", "//body//table[1]");

    private String alias;
    private String selector;
}
