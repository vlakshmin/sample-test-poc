package widgets.inventory.adSpots.sidebar.card;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotBannerCardSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotBannerCardSidebarElements}
 */
@Getter
public class AdSpotBannerCardSidebar {

    private SelenideElement bannerPanel = $x(BANNER_PANEL.getSelector())
            .as(BANNER_PANEL.getAlias());
    private SelenideElement tooltipBannerAdSizes = $x(TOOLTIP_BANNER_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_BANNER_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipBannerFloorPrice = $x(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement bannerCardHeader = $x(BANNER_CARD.getSelector()).as(BANNER_CARD.getAlias());
}
