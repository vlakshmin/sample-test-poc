package widgets.common.detailsmenu.menu;

import lombok.Getter;
import widgets.common.detailsmenu.menu.sections.*;

@Getter
public class TableItemDetailsMenu {

    private GeoDetailsSection geoDetailsSection = new GeoDetailsSection();
    private AdSizeDetailsSection adSizeDetailsSection = new AdSizeDetailsSection();
    private AdFormatDetailsSection adFormatDetailsSection = new AdFormatDetailsSection();
    private InventoryDetailsSection inventoryDetailsSection = new InventoryDetailsSection();
    private DemandSourcesDetailsSection demandSourcesDetailsSection = new DemandSourcesDetailsSection();
    private OperatingSystemDetailsSection operatingSystemDetailsSection = new OperatingSystemDetailsSection();

}
