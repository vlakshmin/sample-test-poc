package widgets.admin.demand.sidebar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.admin.demand.multipane.DemandSourceTypeMultipane;
import widgets.admin.demand.requesadjustmantratefield.RequestAdjustmentRateField;
import widgets.admin.demand.timeoutfield.TimeoutMsField;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.admin.demand.multipane.DemandSourceTypeNameImpl.*;
import static widgets.admin.demand.sidebar.EditDemandSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link EditDemandSidebarElements}
 */
@Getter
public class EditDemandSidebar {

    private SelenideElement bidderInput = $x(BIDDER.getSelector()).as(BIDDER.getAlias());
    private SelenideElement syncUrlInput = $x(SYNC_URL.getSelector()).as(SYNC_URL.getAlias());
    private SelenideElement closeIcon = $x(CLOSE_ICON.getSelector()).as(CLOSE_ICON.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement formatLabel = $x(FORMAT_LABEL.getSelector()).as(FORMAT_LABEL.getAlias());
    private SelenideElement currency = $x(CURRENCY_DROPDOWN.getSelector()).as(CURRENCY_DROPDOWN.getAlias());
    private SelenideElement endpointURIInput = $x(EDDPOINT_URI_INPUT.getSelector()).as(EDDPOINT_URI_INPUT.getAlias());
    private SelenideElement activeRadioButton = $x(ACTIVE_RADIO_BUTTON.getSelector()).as(ACTIVE_RADIO_BUTTON.getAlias());
    private SelenideElement dataCenterDropdown = $x(DATA_CENTER_DROPDOWN.getSelector()).as(DATA_CENTER_DROPDOWN.getAlias());
    private ElementsCollection publisherNameDropdownItems = $$x(DROPDOWN_ITEMS.getSelector()).as(DROPDOWN_ITEMS.getAlias());
    private SelenideElement inactiveRadioButton = $x(INACTIVE_RADIO_BUTTON.getSelector()).as(INACTIVE_RADIO_BUTTON.getAlias());
    private SelenideElement onboardingRadioButton = $x(ONBOARDING_RADIO_BUTTON.getSelector()).as(ONBOARDING_RADIO_BUTTON.getAlias());

    private SelenideElement pmpSupportToggle = $x( PMP_SUPPORT_TOGGLE.getSelector()).as(PMP_SUPPORT_TOGGLE.getAlias());
    private SelenideElement syncRequiredToggle = $x(SYNC_REQUIRED_TOGGLE.getSelector()).as(SYNC_REQUIRED_TOGGLE.getAlias());
    private SelenideElement idfaRequiredToggle = $x(IDFA_REQUIRED_TOGGLE.getSelector()).as(IDFA_REQUIRED_TOGGLE.getAlias());
    private SelenideElement nonProgrammaticToggle = $x( NON_PROGRAMMATIC_TOGGLE.getSelector()).as( NON_PROGRAMMATIC_TOGGLE.getAlias());
    private SelenideElement tokenGenerationToggle = $x( TOKEN_GENERATION_TOGGLE.getSelector()).as( TOKEN_GENERATION_TOGGLE.getAlias());


    private SelenideElement iosCheckBox = $x(IOS_CHECKBOX.getSelector()).as(IOS_CHECKBOX.getAlias());
    private SelenideElement videoCheckBox = $x(VIDEO_CHECKBOX.getSelector()).as(VIDEO_CHECKBOX.getAlias());
    private SelenideElement bannerCheckBox = $x(BANNER_CHECKBOX.getSelector()).as(BANNER_CHECKBOX.getAlias());
    private SelenideElement nativeCheckBox = $x(NATIVE_CHECKBOX.getSelector()).as(NATIVE_CHECKBOX.getAlias());
    private SelenideElement pcWebCheckBox =  $x(PC_WEB_CHECKBOX.getSelector()).as(PC_WEB_CHECKBOX.getAlias());
    private SelenideElement androidCheckBox = $x(ANDROID_CHECKBOX.getSelector()).as(ANDROID_CHECKBOX.getAlias());
    private SelenideElement mobileWebCheckBox = $x(MOBILE_WEB_CHECKBOX.getSelector()).as(MOBILE_WEB_CHECKBOX.getAlias());

    private TimeoutMsField timeoutMsField = new TimeoutMsField();
    private RequestAdjustmentRateField requestAdjustmentRateField = new RequestAdjustmentRateField();

    private DemandSourceTypeMultipane allowedCountriesMultipane = new DemandSourceTypeMultipane(ALLOWED_COUNTRIES);

}
