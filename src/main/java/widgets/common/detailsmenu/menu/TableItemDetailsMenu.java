package widgets.common.detailsmenu.menu;

import lombok.Getter;
import widgets.common.detailsmenu.menu.sections.DetailsSection;

import static widgets.common.detailsmenu.DetailsSectionName.*;

@Getter
public class TableItemDetailsMenu {

    private DetailsSection geoDetailsSection = new DetailsSection(GEO);
    private DetailsSection adSizeDetailsSection = new DetailsSection(AD_SIZE);
    private DetailsSection adFormatDetailsSection = new DetailsSection(AD_FORMAT);
    private DetailsSection inventoryDetailsSection = new DetailsSection(INVENTORY);
    private DetailsSection demandSourcesDetailsSection = new DetailsSection(DEMAND_SOURCES);
    private DetailsSection operatingSystemDetailsSection = new DetailsSection(OPERATING_SYSTEM);
}
