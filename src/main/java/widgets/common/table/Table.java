package widgets.common.table;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class Table {
    TableOptions tableOptions = new TableOptions();
    TableData tableData = new TableData();
    TablePagination tablePagination = new TablePagination();

}
