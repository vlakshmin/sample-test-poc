package widgets.common.multipane.item;

import widgets.common.multipane.MultipaneName;
import widgets.common.multipane.item.common.CommonSelectTableItem;

public class SelectTableItem extends CommonSelectTableItem {

    private static final String SELECT_TABLE_ITEM = "h3[text()='%s']/../..//table[contains(@class,'select-table')]/tbody";
    //descendant::h3[text()='%s']/../..//table[contains(@class,'select-table')]/tbody

    public SelectTableItem(int position, MultipaneName multipaneNameImpl) {
        super(position, SELECT_TABLE_ITEM, multipaneNameImpl);
    }
}
