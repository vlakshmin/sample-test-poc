package widgets.common.table;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TableElements.*;

/**
 * Keep Selectors of UI elements in {@link TableElements}
 */
public class Table extends BasePage {

    public SelenideElement getCheckbox(int row) {

        return $x(String.format(CHECKBOX.getSelector(), row)).as(CHECKBOX.getAlias());
    }

    public List<SelenideElement> getDataRows() {

        return $$x(ROWS.getSelector()).as(ROWS.getAlias());
    }

    public List<SelenideElement> getColumns() {

        return $$x(COLUMNS.getSelector()).as(COLUMNS.getAlias());
    }

    public SelenideElement getTableBody() {

        return $x(BODY.getSelector()).as(BODY.getAlias());
    }

    public SelenideElement getColumnHeader(String columnName) {
        return $x(String.format(COLUMN_HEADER.getSelector(), columnName)).as(COLUMN_HEADER.getAlias());
    }

}