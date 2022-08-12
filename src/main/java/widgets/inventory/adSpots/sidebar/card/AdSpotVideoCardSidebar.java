package widgets.inventory.adSpots.sidebar.card;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotVideoCardSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotVideoCardSidebarElements}
 */
@Getter
public class AdSpotVideoCardSidebar {

    private SelenideElement videoCardHeader = $x(VIDEO_CARD.getSelector()).as(VIDEO_CARD.getAlias());
    private SelenideElement tooltipVideoAdSizes = $x(TOOLTIP_VIDEO_AD_SIZES_ICON.getSelector())
            .as(TOOLTIP_VIDEO_AD_SIZES_ICON.getAlias());
    private SelenideElement tooltipVideoFloorPrice = $x(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_VIDEO_FLOOR_PRICE_ICON.getAlias());
}
