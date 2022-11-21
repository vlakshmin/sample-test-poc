package widgets.common.table;

import lombok.Getter;
import widgets.common.table.filter.FilterOptions;
import widgets.common.table.filter.activebooleanfilter.ActiveBooleanFilter;
import widgets.common.table.filter.booleanfilter.BooleanFilter;

@Getter
public class Table {

    TableData tableData = new TableData();
    FilterOptions filterOptions = new FilterOptions();
    ShowHideColumns showHideColumns = new ShowHideColumns();
    TablePagination tablePagination = new TablePagination();
}
