package widgets.inventory.adSpots.sidebar.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotNativeCardSidebarElements {

    NATIVE_FLOOR_PRICE("'Native' Floor Price Input",
            "//div[text()='Native']/../..//label[text()='Floor Price']/../../div/input"),
    NATIVE_FLOOR_PRICE_CURRENCY("'Native' Floor Price Currency Label",
            "//div[text()='Native']/../..//label[text()='Floor Price']/../../div/div"),
    NATIVE_PANEL("'Native' Panel with Elements", "//div[text()='Native']/../../span"),
    ENABLED_TOGGLE("'Native' Active Toggle", "//div[text()='Native']/..//input[@role='switch']"),
    NATIVE_CARD("'Native' Card", "//div[text()='Native']/../button//i[contains(@class,'v-icon')]"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//div[text()='Native']/../..//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    TOOLTIP_NATIVE_FLOOR_PRICE_ICON("Native Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Native']/../..//*[text()='Floor Price']/../../div/span[contains(@class,'v-tooltip')]/../i");

    private String alias;
    private String selector;
}
