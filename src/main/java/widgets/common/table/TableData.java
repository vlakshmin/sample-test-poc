package widgets.common.table;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.table.TableElements.*;

/**
 * Keep Selectors of UI elements in {@link TableElements}
 */
@Getter
public class TableData {

    private final SelenideElement search = $x(SEARCH.getSelector()).as(SEARCH.getAlias());
    private final SelenideElement clear = $x(CLEAR.getSelector()).as(CLEAR.getAlias());
    private final ElementsCollection rows = $$x(ROWS.getSelector()).as(ROWS.getAlias());
    private final ElementsCollection cellByColumn = $$x(CELL_BY_COLUMN.getSelector()).as(CELL_BY_COLUMN.getAlias());
    private final ElementsCollection columns = $$x(COLUMNS.getSelector()).as(COLUMNS.getAlias());
    private final SelenideElement tableBody = $x(BODY.getSelector()).as(BODY.getAlias());


    public SelenideElement getCheckbox(int row) {

        return $x(String.format(CHECKBOX.getSelector(), row)).as(CHECKBOX.getAlias());
    }

    public SelenideElement getColumnHeader(String columnName) {

        return $x(String.format(COLUMN_HEADER.getSelector(), columnName)).as(COLUMN_HEADER.getAlias());
    }

    public ElementsCollection getCustomCells(ColumnNames columnName) {
        int columnId = getColumns()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList())
                .indexOf(columnName.getName()) + 2;

        return $$x(String.format(CELL_BY_COLUMN.getSelector(), columnId)).as(CELL_BY_COLUMN.getAlias());
    }

    public SelenideElement getFirstActiveRow() {
        for (SelenideElement element : rows) {
            var tableCell = element.find(By.xpath("//td[6]"));
            if (tableCell.getText().equalsIgnoreCase("Active")) {
                return element;
            }
        }
        return null;
    }

    public SelenideElement getFirstActiveRowBtn() {

        return getFirstActiveRow().find(By.xpath("//td[6]"));
    }
}