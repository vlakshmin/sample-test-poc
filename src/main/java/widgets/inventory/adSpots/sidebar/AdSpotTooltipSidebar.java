package widgets.inventory.adSpots.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.AdSpotTooltipSidebarElements.*;


/**
 * Keep Selectors of UI elements in {@link AdSpotTooltipSidebarElements}
 */
@Getter
public class AdSpotTooltipSidebar {

    private SelenideElement tooltipCategories = $x(TOOLTIP_CATEGORIES_ICON.getSelector())
            .as(TOOLTIP_CATEGORIES_ICON.getAlias());
    private SelenideElement tooltipVideoAdSizes = $x(TOOLTIP_VIDEO_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_VIDEO_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipBannerAdSizes = $x(TOOLTIP_BANNER_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_BANNER_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipDefaultAdSizes = $x(TOOLTIP_DEFAULT_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_DEFAULT_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipVideoFloorPrice = $x(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipBannerFloorPrice = $x(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipNativeFloorPrice = $x(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipDefaultFloorPrice = $x(TOOLTIP_DEFAULT_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_DEFAULT_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement tooltipContentForChildren = $x(TOOLTIP_CONTENT_FOR_CHILDREN_ICON.getSelector())
            .as(TOOLTIP_CONTENT_FOR_CHILDREN_ICON.getAlias());
}
