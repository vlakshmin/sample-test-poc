package widgets.common.table.filter.calendarFilter;

import lombok.Getter;
import widgets.common.datepicker.DatePicker;
import widgets.common.table.filter.abstractt.SinglePaneFilter;

@Getter
public class CalendarFilter extends SinglePaneFilter {

    private DatePicker calendar = new DatePicker();
}
