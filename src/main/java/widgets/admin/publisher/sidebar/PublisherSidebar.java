package widgets.admin.publisher.sidebar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.validationalert.ValidationBottomAlert;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.admin.publisher.sidebar.PublisherSidebarElements.*;
import static widgets.admin.publisher.sidebar.PublisherSidebarElements.AD_OPS_PERSON;
import static widgets.inventory.media.sidebar.MediaSidebarElements.CATEGORIES_INPUT;
import static widgets.inventory.media.sidebar.MediaSidebarElements.ERROR_ALERT_BY_FIELD_NAME;

/**
 * Keep Selectors of UI elements in {@link PublisherSidebarElements}
 */
@Getter
public abstract class PublisherSidebar {

    private SelenideElement nameInput = $x(NAME.getSelector()).as(NAME.getAlias());
    private SelenideElement adOpsEmail = $x(MAIL.getSelector()).as(MAIL.getAlias());
    private SelenideElement domainInput = $x(DOMAIN.getSelector()).as(DOMAIN.getAlias());
    private SelenideElement currency = $x(CURRENCY.getSelector()).as(CURRENCY.getAlias());
    private SelenideElement closeIcon = $x(CLOSE_ICON.getSelector()).as(CLOSE_ICON.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement adOpsPerson = $x(AD_OPS_PERSON.getSelector()).as(AD_OPS_PERSON.getAlias());
    private SelenideElement activeToggle = $x(ACTIVE_TOGGLE.getSelector()).as(ACTIVE_TOGGLE.getAlias());
    private SelenideElement categoriesInput = $x(CATEGORIES_INPUT.getSelector()).as(CATEGORIES_INPUT.getAlias());
    private SelenideElement currencyDropdown = $x(CURRENCY_DROPDOWN.getSelector()).as(CURRENCY_DROPDOWN.getAlias());
    private ElementsCollection currencyDropdownItems = $$x(CURRENCY_DROPDOWN_ITEMS.getSelector()).as(CURRENCY_DROPDOWN_ITEMS.getAlias());

    private ValidationBottomAlert errorAlert = new ValidationBottomAlert();

    public SelenideElement getErrorAlertByFieldName(String fieldName) {

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(), fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(), fieldName));
    }

}
