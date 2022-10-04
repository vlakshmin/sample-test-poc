package widgets.admin.demand.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.admin.demand.sidebar.EditDemandSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link EditDemandSidebarElements}
 */
@Getter
public abstract class EditDemandSidebar {

    private SelenideElement syncUrlInput = $x(SYNK_URL.getSelector()).as(SYNK_URL.getAlias());
    private SelenideElement bidderInput = $x(BIDDER.getSelector()).as(BIDDER.getAlias());
    private SelenideElement currency = $x(CURRENCY.getSelector()).as(CURRENCY.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement endpointURIInput = $x(EDDPOINT_URI_INPUT.getSelector()).as(EDDPOINT_URI_INPUT.getAlias());
    private SelenideElement dataCenterDropdown = $x(DATA_CENTER_DROPDOWN.getSelector()).as(DATA_CENTER_DROPDOWN.getAlias());
}
