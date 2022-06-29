package widgets.yield.openPricing.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.yield.openPricing.sidebar.OpenPricingSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link OpenPricingSidebarElements}
 */
@Getter
public abstract class OpenPricingSidebar {

    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement nameInput = $x(OPEN_PRICING_NAME.getSelector()).as(OPEN_PRICING_NAME.getAlias());
    private SelenideElement floorPrice = $x(FLOOR_PRICE.getSelector()).as(FLOOR_PRICE.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement publisherInputErrorLabel;
    private SelenideElement nameInputErrorLabel;
    private SelenideElement floorPriceInputErrorLabel;

}
