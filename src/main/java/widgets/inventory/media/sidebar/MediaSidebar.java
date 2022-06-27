package widgets.inventory.media.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.media.sidebar.MediaSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link MediaSidebarElements}
 */
@Getter
public abstract class MediaSidebar {

    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement nameInput = $x(MEDIA_NAME.getSelector()).as(MEDIA_NAME.getAlias());
    private SelenideElement mediaTypeInput = $x(MEDIA_TYPE.getSelector()).as(MEDIA_TYPE.getAlias());
    private SelenideElement siteURL = $x(SITE_URL.getSelector()).as(SITE_URL.getAlias());
    private SelenideElement hintSiteURL = $x(HINT_SITE_URL.getSelector()).as(HINT_SITE_URL.getAlias());
    private SelenideElement categories = $x(CATEGORIES.getSelector()).as(CATEGORIES.getAlias());
    private SelenideElement hintCategories = $x(HINT_CATEGORIES.getSelector()).as(HINT_CATEGORIES.getAlias());
    private SelenideElement appStoreURL = $x(APP_STORE_URL.getSelector()).as(APP_STORE_URL.getAlias());
    private SelenideElement hintAppStoreURL = $x(HINT_APP_STORE_URL.getSelector()).as(HINT_APP_STORE_URL.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
}
