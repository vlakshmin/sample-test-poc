package widgets.protections.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneNameImpl;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.protections.sidebar.ProtectionsSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link ProtectionsSidebarElements}
 */
@Getter
public abstract class ProtectionsSidebar {

    private SelenideElement notes = $x(NOTES.getSelector()).as(NOTES.getAlias());
    private SelenideElement nameInput = $x(NAME.getSelector()).as(NAME.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement publisherNameDropdown = $x(PUBLISHER_NAME_DROPDOWN.getSelector()).as(PUBLISHER_NAME_DROPDOWN.getAlias());
    private SelenideElement protectionTypeDropdown = $x(PROTECTION_TYPE_DROPDOWN.getSelector()).as(PROTECTION_TYPE_DROPDOWN.getAlias());
    private SelenideElement managedBySystemAdminOnly = $x(MANAGED_BY_SYSTEM_ADMIN_ONLY.getSelector()).as(MANAGED_BY_SYSTEM_ADMIN_ONLY.getAlias());

    private Multipane geoMultipane = new Multipane(MultipaneNameImpl.GEO);
    private Multipane deviceMultipane = new Multipane(MultipaneNameImpl.DEVICE);
    private Multipane adSizeMultipane = new Multipane(MultipaneNameImpl.AD_SIZE);
    private Multipane adFormatMultipane = new Multipane(MultipaneNameImpl.AD_FORMAT);
    private Multipane inventoryMultipane = new Multipane(MultipaneNameImpl.INVENTORY);
    private Multipane demandSourcesMultipane = new Multipane(MultipaneNameImpl.DEMAND_SOURCES);
    private Multipane operatingSystemMultipane = new Multipane(MultipaneNameImpl.OPERATING_SYSTEM);
}
