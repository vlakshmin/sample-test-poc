package widgets.inventory.adSpots.sidebar.card;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotNativeCardSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotNativeCardSidebarElements}
 */
@Getter
public class AdSpotNativeCardSidebar {

    private SelenideElement tooltipNativeFloorPrice = $x(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getSelector())
            .as(TOOLTIP_NATIVE_FLOOR_PRICE_ICON.getAlias());
    private SelenideElement nativeCardHeader = $x(NATIVE_CARD.getSelector()).as(NATIVE_CARD.getAlias());
}
