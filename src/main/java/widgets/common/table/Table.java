package widgets.common.table;

import lombok.Getter;
import widgets.common.table.filter.ColumnFiltersBlock;

@Getter
public class Table {

    TableData tableData = new TableData();
    ColumnFiltersBlock columnFiltersBlock = new ColumnFiltersBlock();
    ShowHideColumns showHideColumns = new ShowHideColumns();
    TablePagination tablePagination = new TablePagination();
}
