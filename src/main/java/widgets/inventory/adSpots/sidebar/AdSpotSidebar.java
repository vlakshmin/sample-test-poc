package widgets.inventory.adSpots.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.AdSpotSidebarElements.*;
import static widgets.inventory.adSpots.sidebar.AdSpotSidebarElements.ERROR_ALERT_BY_FIELD_NAME;

/**
 * Keep Selectors of UI elements in {@link AdSpotSidebarElements}
 */
@Getter
public abstract class AdSpotSidebar {

    private SelenideElement filter = $x(FILTER.getSelector()).as(FILTER.getAlias());
    private SelenideElement position = $x(POSITION.getSelector()).as(POSITION.getAlias());
    private SelenideElement closeIcon = $x(CLOSE_ICON.getSelector()).as(CLOSE_ICON.getAlias());
    private SelenideElement categories = $x(CATEGORIES.getSelector()).as(CATEGORIES.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement nameInput = $x(AD_SPOT_NAME.getSelector()).as(AD_SPOT_NAME.getAlias());
    private SelenideElement videoCardHeader = $x(VIDEO_CARD.getSelector()).as(VIDEO_CARD.getAlias());
    private SelenideElement tooltipBannerAdSizes = $x(TOOLTIP_BANNER_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_BANNER_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipBannerFloorPrice = $x(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipNativeFloorPrice = $x(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipVideoAdSizes = $x(TOOLTIP_VIDEO_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_VIDEO_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipVideoFloorPrice = $x(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement bannerCardHeader = $x(BANNER_CARD.getSelector()).as(BANNER_CARD.getAlias());
    private SelenideElement nativeCardHeader = $x(NATIVE_CARD.getSelector()).as(NATIVE_CARD.getAlias());
    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement relatedMediaInput = $x(RELATED_MEDIA.getSelector()).as(RELATED_MEDIA.getAlias());
    private SelenideElement tooltipDefaultFloorPrice = $x(TOOLTIP_DEFAULT_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_DEFAULT_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement defaultAdSizes = $x(DEFAULT_AD_SIZES.getSelector()).as(DEFAULT_AD_SIZES.getAlias());
    private SelenideElement defaultFloorPrice = $x(DEFAULT_FLOOR_PRICE.getSelector()).as(DEFAULT_FLOOR_PRICE.getAlias());
    private SelenideElement tooltipContentForChildren = $x(TOOLTIP_CONTENT_FOR_CHILDREN_ICON.getSelector())
            .as(TOOLTIP_CONTENT_FOR_CHILDREN_ICON.getAlias());
    private SelenideElement tooltipCategories = $x(TOOLTIP_CATEGORIES_ICON.getSelector())
            .as(TOOLTIP_CONTENT_FOR_CHILDREN_ICON.getAlias());
    private SelenideElement tooltipDefaultAdSizes = $x(TOOLTIP_DEFAULT_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_DEFAULT_AD_SIZES_ICON.getAlias());
    public SelenideElement getErrorAlertByFieldName(String fieldName){

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(),fieldName));
    }
}
