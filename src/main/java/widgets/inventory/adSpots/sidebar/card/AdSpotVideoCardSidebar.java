package widgets.inventory.adSpots.sidebar.card;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.adSizes.AdSizesListPanel;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotVideoCardSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotVideoCardSidebarElements}
 */
@Getter
public class AdSpotVideoCardSidebar {

    private SelenideElement videoPanel = $x(VIDEO_PANEL.getSelector())
            .as(VIDEO_PANEL.getAlias());
    private SelenideElement videoAdSizes = $x(VIDEO_AD_SIZES.getSelector())
            .as(VIDEO_AD_SIZES.getAlias());
    private SelenideElement enabledToggle = $x(ENABLED_TOGGLE.getSelector())
            .as(ENABLED_TOGGLE.getAlias());
    private SelenideElement videoFloorPrice = $x(VIDEO_FLOOR_PRICE.getSelector())
            .as(VIDEO_FLOOR_PRICE.getAlias());
    private SelenideElement videoFloorPriceCurrency = $x(VIDEO_FLOOR_PRICE_CURRENCY.getSelector())
            .as(VIDEO_FLOOR_PRICE_CURRENCY.getAlias());
    private SelenideElement minVideoDuration = $x(MIN_VIDEO_DURATION.getSelector())
            .as(MIN_VIDEO_DURATION.getAlias());
    private SelenideElement maxVideoDuration = $x(MAX_VIDEO_DURATION.getSelector())
            .as(MAX_VIDEO_DURATION.getAlias());
    private SelenideElement videoPlacementType = $x(VIDEO_PLACEMENT_TYPE.getSelector())
            .as(VIDEO_PLACEMENT_TYPE.getAlias());
    private SelenideElement videoPlaybackMethods = $x(VIDEO_PLAYBACK_METHODS.getSelector())
            .as(VIDEO_PLAYBACK_METHODS.getAlias());
    private SelenideElement tooltipVideoAdSizes = $x(TOOLTIP_VIDEO_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_VIDEO_AD_SIZES_ICON.getAlias());
    private SelenideElement videoCardHeader = $x(VIDEO_CARD.getSelector()).as(VIDEO_CARD.getAlias());
    private SelenideElement tooltipVideoFloorPrice = $x(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getAlias());
    private ElementsCollection videoPlacementTypeItems = $$x(VIDEO_PLACEMENT_TYPE_ITEMS.getSelector())
            .as(VIDEO_PLACEMENT_TYPE_ITEMS.getAlias());
    private ElementsCollection videoPlaybackMethodsItems = $$x(VIDEO_PLAYBACK_METHODS_ITEMS.getSelector())
            .as(VIDEO_PLAYBACK_METHODS_ITEMS.getAlias());
    private ElementsCollection videoPlaybackMethodsSelectedItems = $$x(VIDEO_PLAYBACK_METHODS_SELECTED_ITEMS.getSelector())
            .as(VIDEO_PLAYBACK_METHODS_SELECTED_ITEMS.getAlias());
    private AdSizesListPanel adSizesPanel = new AdSizesListPanel();

    public SelenideElement getErrorAlertByFieldName(String fieldName){

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(),fieldName));
    }
}
