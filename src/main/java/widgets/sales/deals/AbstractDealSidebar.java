package widgets.sales.deals;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.sales.deals.AbstractDealSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AbstractDealSidebarElements}
 */
@Getter
public abstract class AbstractDealSidebar {

    private SelenideElement nameInput = $x(NAME.getSelector()).as(NAME.getAlias());
    private SelenideElement publisherDropdown = $x(PUBLISHER_DROPDOWN.getSelector()).as(PUBLISHER_DROPDOWN.getAlias());
    private SelenideElement activeToggle = $x(ACTIVE_TOGGLE.getSelector()).as(ACTIVE_TOGGLE.getAlias());
    private SelenideElement privateAuctionDropdown = $x(PRIVATE_AUCTION_DROPDOWN.getSelector()).as(PRIVATE_AUCTION_DROPDOWN.getAlias());
    private SelenideElement floorPriceInput = $x(FLOOR_PRICE_INPUT.getSelector()).as(FLOOR_PRICE_INPUT.getAlias());
    private SelenideElement currencyDropdown = $x(CURRENCY_DROPDOWN.getSelector()).as(CURRENCY_DROPDOWN.getAlias());
    private SelenideElement dspDropdown = $x(DSP_DROPDOWN.getSelector()).as( DSP_DROPDOWN.getAlias());
    private SelenideElement addMoreSeats = $x(ADD_MORE_SEATS_BUTTON.getSelector()).as(ADD_MORE_SEATS_BUTTON.getAlias());

    private ElementsCollection dropDownItems = $$x(DROPDOWN_ITEMS.getSelector()).as(DROPDOWN_ITEMS.getAlias());

}
