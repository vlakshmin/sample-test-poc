package widgets.common.table;

import lombok.Getter;
import widgets.common.table.filter.FilterOptions;

@Getter
public class Table {

    TableData tableData = new TableData();
    TableOptions tableOptions = new TableOptions();
    FilterOptions filterOptions = new FilterOptions();
    TablePagination tablePagination = new TablePagination();
}
