package widgets.common.multipane.item;

import widgets.common.multipane.MultipaneName;
import widgets.common.multipane.item.common.CommonTableItem;


public class SelectChildTableItem extends CommonTableItem {

    private static final String SELECT_CHILD_TABLE_ITEM = "h3[text()='%s']/../..//table[@class='select-table fixed']/tbody/tr[contains(@class,'children')]";
    //descendant::h3[text()='%s']/../..//table[@class='select-table fixed']/tbody/tr[contains(@class,'children')][%s]//td[2]/div

    public SelectChildTableItem(int position, MultipaneName multipaneName) {
        super(position, SELECT_CHILD_TABLE_ITEM, multipaneName);
    }

    //Todo remove it  after Debug
    public static void main(String[] args) {
        SelectChildTableItem item = new SelectChildTableItem(2, MultipaneName.INVENTORY);
        System.out.println(item.buildXpath("//td[2]/div"));
    }
}
