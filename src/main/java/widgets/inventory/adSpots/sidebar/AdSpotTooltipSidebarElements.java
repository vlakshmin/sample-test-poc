package widgets.inventory.adSpots.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotTooltipSidebarElements {

    TOOLTIP_CATEGORIES_ICON("Tooltip Categories Icon",
            "//*[text()='Categories']/../div/*[contains(@class,'tooltip')]/../i"),
    TOOLTIP_DEFAULT_AD_SIZES_ICON("Tooltip Default Ad Sizes Icon",
            "//*[text()='Default Ad Sizes']/../div/span[contains(@class,'tooltip')]/../i"),
    TOOLTIP_DEFAULT_FLOOR_PRICE_ICON("Tooltip Default Floor Price Icon",
            "//*[text()='Default Floor Price']/../../div/*[contains(@class,'tooltip')]/../i"),
    TOOLTIP_PLACEHOLDER("", "//span"),
    TOOLTIP_BANNER_FLOOR_PRICE_ICON("Banner Card  Floor Price Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_VIDEO_FLOOR_PRICE_ICON("Video Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_BANNER_AD_SIZES_ICON("Banner Card  Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_VIDEO_AD_SIZES_ICON("Video Card Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_NATIVE_FLOOR_PRICE_ICON("Native Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Native']/../..//*[text()='Floor Price']/../../div/span[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_CONTENT_FOR_CHILDREN_ICON("Tooltip Content for Children  Icon",
            "//*[text()='Content for Children']/../i");

    private String alias;
    private String selector;
}
