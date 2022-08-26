package widgets.inventory.adSpots.sidebar.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotBannerCardSidebarElements {

    BANNER_PANEL("'Banner' Panel with Elements", "//div[text()='Banner']/../../span"),
    ENABLED_TOGGLE("'Banner' Active Toggle", "//div[text()='Banner']/..//input[@role='switch']"),
    BANNER_CARD("'Banner' Card", "//div[text()='Banner']/../button//i[contains(@class,'v-icon')]"),
    TOOLTIP_BANNER_AD_SIZES_ICON("Banner Card  Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//div[text()='Banner']/../..//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    TOOLTIP_BANNER_FLOOR_PRICE_ICON("Banner Card  Floor Price Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    BANNER_AD_SIZES("'Banner' Ad Sizes Button", "//div[text()='Banner']/../..//label[text()='Ad Sizes']/../div/div"),
    BANNER_AD_SIZES_INPUT("'Banner' Ad Sizes Input", "//div[text()='Banner']/../..//label[text()='Ad Sizes']/../div/input"),
    BANNER_FLOOR_PRICE("'Banner' Floor Price Input", "//div[text()='Banner']/../..//label[text()='Floor Price']/../../div/input");


    private String alias;
    private String selector;
}
