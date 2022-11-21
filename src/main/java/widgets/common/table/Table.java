package widgets.common.table;

import lombok.Getter;
import widgets.common.table.filter.FilterOptions;

@Getter
public class Table {

    TableData tableData = new TableData();
    FilterOptions filterOptions = new FilterOptions();
    ShowHideColumns showHideColumns = new ShowHideColumns();
    TablePagination tablePagination = new TablePagination();
}
