package widgets.common.detailsmenu.menu;

import lombok.Getter;
import widgets.common.detailsmenu.menu.sections.AdFormatDetailsSection;
import widgets.common.detailsmenu.menu.sections.AdSizeDetailsSection;
import widgets.common.detailsmenu.menu.sections.GeoDetailsSection;
import widgets.common.detailsmenu.menu.sections.InventoryDetailsSection;

@Getter
public class TableItemDetailsMenu {

    private GeoDetailsSection geoDetailsSection = new GeoDetailsSection();
    private AdSizeDetailsSection adSizeDetailsSection = new AdSizeDetailsSection();
    private AdFormatDetailsSection adFormatDetailsSection = new AdFormatDetailsSection();
    private InventoryDetailsSection inventoryDetailsSection = new InventoryDetailsSection();




    //    DEMAND_SOURCES("Demand Sources"),
    //    OPERATING_SYSTEM("Operating System");
}
