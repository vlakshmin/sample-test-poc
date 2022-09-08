package widgets.inventory.adSpots.sidebar.card;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.adSizes.AdSizesListPanel;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotBannerCardSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotBannerCardSidebarElements}
 */
@Getter
public class AdSpotBannerCardSidebar {

    private SelenideElement bannerPanel = $x(BANNER_PANEL.getSelector())
            .as(BANNER_PANEL.getAlias());
    private SelenideElement adSizes = $x(BANNER_AD_SIZES.getSelector())
            .as(BANNER_AD_SIZES.getAlias());
    private SelenideElement enabledToggle = $x(ENABLED_TOGGLE.getSelector())
            .as(ENABLED_TOGGLE.getAlias());
    private SelenideElement floorPrice = $x(BANNER_FLOOR_PRICE.getSelector())
            .as(BANNER_FLOOR_PRICE.getAlias());
    private SelenideElement floorPriceCurrency = $x(BANNER_FLOOR_PRICE_CURRENCY.getSelector())
            .as(BANNER_FLOOR_PRICE_CURRENCY.getAlias());
    private SelenideElement adSizesInput = $x(BANNER_AD_SIZES_INPUT.getSelector())
            .as(BANNER_AD_SIZES_INPUT.getAlias());
    private SelenideElement tooltipBannerAdSizes = $x(TOOLTIP_BANNER_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_BANNER_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipBannerFloorPrice = $x(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_BANNER_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement bannerCardHeader = $x(BANNER_CARD.getSelector()).as(BANNER_CARD.getAlias());
    private AdSizesListPanel adSizesPanel = new AdSizesListPanel();

    public SelenideElement getErrorAlertByFieldName(String fieldName){

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(),fieldName));
    }
}
