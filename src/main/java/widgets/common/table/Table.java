package widgets.common.table;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.ArrayList;
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
public class Table {

    private final TablePagination tablePagination = new TablePagination();
    private final TableOptions tableOptions = new TableOptions();

    private final SelenideElement search = $x(SEARCH.getSelector()).as(SEARCH.getAlias());
    private final SelenideElement clear = $x(CLEAR.getSelector()).as(CLEAR.getAlias());
    private final ElementsCollection rows = $$x(ROWS.getSelector()).as(ROWS.getAlias());
    private final ElementsCollection cellByColumn = $$x(CELL_BY_COLUMN.getSelector()).as(CELL_BY_COLUMN.getAlias());

    public SelenideElement getCheckbox(int row) {

        return $x(String.format(CHECKBOX.getSelector(), row)).as(CHECKBOX.getAlias());
    }

    public ElementsCollection getColumns() {

        return $$x(COLUMNS.getSelector()).as(COLUMNS.getAlias());
    }

    public SelenideElement getTableBody() {

        return $x(BODY.getSelector()).as(BODY.getAlias());
    }

    public SelenideElement getColumnHeader(String columnName) {
        return $x(String.format(COLUMN_HEADER.getSelector(), columnName)).as(COLUMN_HEADER.getAlias());
    }

    //---Table steps--

    public List<String> getColumnsName() {

        return getColumns()
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
    }

    public ElementsCollection getCustomCells(ColumnNames columnName) {
        int columnId = getColumnsName().indexOf(columnName.getName()) + 2;

        return $$x(String.format(CELL_BY_COLUMN.getSelector(), columnId)).as(CELL_BY_COLUMN.getAlias());
    }

    public List<String> getCustomCellsValues(ColumnNames column) {

        return getCustomCells(column)
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
    }

    public String getPaginationTextTotalRows() {
        String text = tablePagination.getPaginationPanel().getText();
        int index = text.lastIndexOf("of");

        return text.substring(index + 3, text.length());
    }

    public Table paginationFirstPage() {
        SelenideElement privBtn = tablePagination.getPrevious();
        while (privBtn.isEnabled()) {
            privBtn.click();
        }

        return this;
    }

    public Table selectFromDropdown(SelenideElement element1, ElementsCollection list, String value) {

        element1.shouldBe(exist, visible).hover().click();
        list.stream()
                .filter(item -> item.shouldBe(visible).getText().equalsIgnoreCase(value) &&
                        !item.shouldBe(visible).getText().equalsIgnoreCase("No records found"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("Type with name '%s' haven't been found in the dropdown", value)))
                .click();

        return this;
    }
}