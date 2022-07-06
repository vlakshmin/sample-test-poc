package widgets.yield.openPricing.sidebar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneName;
import widgets.yield.openPricing.floorprice.FloorPriceField;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static widgets.yield.openPricing.sidebar.OpenPricingSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link OpenPricingSidebarElements}
 */
@Getter
public abstract class OpenPricingSidebar {

    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement nameInput = $x(OPEN_PRICING_NAME.getSelector()).as(OPEN_PRICING_NAME.getAlias());
    private ElementsCollection publisherNameDropdownItems = $$x(DROPDOWN_ITEMS.getSelector()).as(DROPDOWN_ITEMS.getAlias());
    private SelenideElement publisherNameDropdown = $x(PUBLISHER_NAME_DROPDOWN.getSelector()).as(PUBLISHER_NAME_DROPDOWN.getAlias());

    private FloorPriceField floorPriceField = new FloorPriceField();

    private Multipane geoMultipane = new Multipane(MultipaneName.GEO);
    private Multipane deviceMultipane = new Multipane(MultipaneName.DEVICE);
    private Multipane adSizeMultipane = new Multipane(MultipaneName.AD_SIZE);
    private Multipane adFormatMultipane = new Multipane(MultipaneName.AD_FORMAT);
    private Multipane inventoryMultipane = new Multipane(MultipaneName.INVENTORY);
    private Multipane demandSourcesMultipane = new Multipane(MultipaneName.DEMAND_SOURCES);
    private Multipane operatingSystemMultipane = new Multipane(MultipaneName.DEMAND_SOURCES);

    public SelenideElement getErrorLabelByFieldName(OpenPricingField field) {

        return $x(format(OPEN_PRICING_NAME.getSelector(), field.getName()))
                .as(format(OPEN_PRICING_NAME.getAlias(), field.getName()));
    }

}
