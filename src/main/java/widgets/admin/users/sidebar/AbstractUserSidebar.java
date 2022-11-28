package widgets.admin.users.sidebar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.categories.CategoriesListPanel;
import widgets.common.validationalert.ValidationBottomAlert;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.admin.users.sidebar.AbstractUserSidebarElements.*;
import static widgets.inventory.media.sidebar.MediaSidebarElements.ERROR_ALERT_BY_FIELD_NAME;
import static widgets.inventory.media.sidebar.MediaSidebarElements.TOOLTIP_ICON_BY_FIELD_NAME;

/**
 * Keep Selectors of UI elements in {@link AbstractUserSidebarElements}
 */
@Getter
public abstract class AbstractUserSidebar {

    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement emailInput = $x(EMAIL_INPUT.getSelector()).as(EMAIL_INPUT.getAlias());
    private SelenideElement activeToggle = $x(ACTIVE_TOGGLE.getSelector()).as(ACTIVE_TOGGLE.getAlias());
    private SelenideElement usernameInput = $x(USERNAME_INPUT.getSelector()).as(USERNAME_INPUT.getAlias());
    private SelenideElement passwordInput = $x(PASSWORD_INPUT.getSelector()).as(PASSWORD_INPUT.getAlias());
    private SelenideElement adminRadioButton = $x(ADMIN_RADIOBUTTON.getSelector()).as(ADMIN_RADIOBUTTON.getAlias());
    private SelenideElement crossPublisherRadioButton = $x(CROSS_PUBLISHER_RADIOBUTTON.getSelector()).as(CROSS_PUBLISHER_RADIOBUTTON.getAlias());
    private SelenideElement singlePublisherRadioButton = $x(SINGLE_PUBLISHER_RADIOBUTTON.getSelector()).as(SINGLE_PUBLISHER_RADIOBUTTON.getAlias());

    private ValidationBottomAlert errorAlert = new ValidationBottomAlert();
    private CategoriesListPanel categoriesPanel = new CategoriesListPanel();

    public SelenideElement getTooltipIconByFieldName(String fieldName) {

        return $x(String.format(TOOLTIP_ICON_BY_FIELD_NAME.getSelector(), fieldName))
                .as(String.format(TOOLTIP_ICON_BY_FIELD_NAME.getAlias(), fieldName));
    }

    public SelenideElement getErrorAlertByFieldName(String fieldName) {

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(), fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(), fieldName));
    }
}
