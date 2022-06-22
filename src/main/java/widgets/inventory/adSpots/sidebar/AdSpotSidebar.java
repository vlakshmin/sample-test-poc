package widgets.inventory.adSpots.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.AdSpotSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotSidebarElements}
 */
@Getter
public abstract class AdSpotSidebar {

    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement nameInput = $x(AD_SPOT_NAME.getSelector()).as(AD_SPOT_NAME.getAlias());
    private SelenideElement relatedMediaInput = $x(RELATED_MEDIA.getSelector()).as(RELATED_MEDIA.getAlias());
    private SelenideElement filter = $x(FILTER.getSelector()).as(FILTER.getAlias());
    private SelenideElement categories = $x(CATEGORIES.getSelector()).as(CATEGORIES.getAlias());
    private SelenideElement position = $x(POSITION.getSelector()).as(POSITION.getAlias());
    private SelenideElement defaultAdSize = $x(DEFAULT_AD_SIZE.getSelector()).as(DEFAULT_AD_SIZE.getAlias());
    private SelenideElement defaultFloorPrice = $x(DEFAULT_FLOOR_PRICE.getSelector()).as(DEFAULT_FLOOR_PRICE.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
}
