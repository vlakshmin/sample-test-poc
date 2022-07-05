package widgets.inventory.media.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.validationalert.ValidationBottomAlert;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.media.sidebar.MediaSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link MediaSidebarElements}
 */
@Getter
public abstract class MediaSidebar {

    private SelenideElement siteURL = $x(SITE_URL.getSelector()).as(SITE_URL.getAlias());
    private SelenideElement nameInput = $x(MEDIA_NAME.getSelector()).as(MEDIA_NAME.getAlias());
    private SelenideElement categories = $x(CATEGORIES.getSelector()).as(CATEGORIES.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement mediaTypeInput = $x(MEDIA_TYPE.getSelector()).as(MEDIA_TYPE.getAlias());
    private SelenideElement appStoreURL = $x(APP_STORE_URL.getSelector()).as(APP_STORE_URL.getAlias());
    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement tooltipPlaceholder = $x(TOOLTIP_PLACEHOLDER.getSelector()).as(TOOLTIP_PLACEHOLDER.getAlias());
    private ValidationBottomAlert errorAlert;
    public SelenideElement getTooltipIconByFieldName(String fieldName){

        return $x(String.format(TOOLTIP_ICON_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(TOOLTIP_ICON_BY_FIELD_NAME.getAlias(),fieldName));
    }

    public SelenideElement getErrorAlertByFieldName(String fieldName){

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(),fieldName));
    }
}
