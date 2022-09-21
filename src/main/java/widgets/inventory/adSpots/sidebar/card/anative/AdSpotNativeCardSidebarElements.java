package widgets.inventory.adSpots.sidebar.card.anative;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotNativeCardSidebarElements {

    NATIVE_PANEL("'Native' Panel with Elements", "//div[text()='Native']/../../span"),
    ENABLED_TOGGLE("'Native' Active Toggle", "//div[text()='Native']/..//input[@role='switch']"),
    NATIVE_CARD("'Native' Card", "//div[text()='Native']/../button//i[contains(@class,'v-icon')]"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//div[text()='Native']/../..//label[text()='%s']/../../../div[2]/div/div/div"),
    TOOLTIP_NATIVE_FLOOR_PRICE_ICON("Native Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Native']/../..//*[text()='Floor Price']/../../div/span[contains(@class,'v-tooltip')]/../i");

    private String alias;
    private String selector;
}