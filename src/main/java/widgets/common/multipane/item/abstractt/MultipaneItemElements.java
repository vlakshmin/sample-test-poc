package widgets.common.multipane.item.abstractt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneItemElements {

    NAME("'Name' Label of row %s in table list in Multipane", "//td/div"),
    INVENTORY_NAME("'Name' Label of row %s in table list in Multipane", "//td[2]/div"),
    INVENTORY_TYPE("'Type' Label of row %s in table list in Multipane", "//td[3]/div"),
    DEMAND_SOURCES_NAME("'Name' Label of row %s in table list in Multipane", "//td//span[2]");

    private String alias;
    private String selector;
}
