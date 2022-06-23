package widgets.common.multipane;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneName {

    DEVICE("Device"),
    AD_SIZE("Ad Size"),
    INVENTORY("Inventory"),
    DEMAND_SOURCES("Demand Sources");


    private String name;
}
