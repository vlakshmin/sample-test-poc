package widgets.inventory.adSpots.sidebar.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotNativeCardSidebarElements {

    NATIVE_CARD("'Native' Card", "//div[text()='Native']/../button//i[contains(@class,'v-icon')]"),
    TOOLTIP_NATIVE_FLOOR_PRICE_ICON("Native Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Native']/../..//*[text()='Floor Price']/../../div/span[contains(@class,'v-tooltip')]/../i");

    private String alias;
    private String selector;
}
