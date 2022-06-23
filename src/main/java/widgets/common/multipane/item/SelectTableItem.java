package widgets.common.multipane.item;

import lombok.Getter;
import widgets.common.multipane.MultipaneName;
import widgets.common.multipane.item.common.CommonTableItem;

public class SelectTableItem extends CommonTableItem {

    private static final String SELECT_TABLE_ITEM = "h3[text()='%s']/../..//table[contains(@class,'select-table')]/tbody";
    //descendant::h3[text()='%s']/../..//table[contains(@class,'select-table')]/tbody

    public SelectTableItem(int position, MultipaneName  multipaneName) {
        super(position, SELECT_TABLE_ITEM, multipaneName);
    }

    //Todo remove it  after Debug
    public static void main(String[] args) {
        SelectTableItem item = new SelectTableItem(2, MultipaneName.INVENTORY);
        System.out.println(item.buildXpath("//td[2]/div"));
    }
}
