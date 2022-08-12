package widgets.inventory.adSpots.sidebar.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotVideoCardSidebarElements {

    VIDEO_CARD("'Video' Card", "//div[text()='Video']/../button//i[contains(@class,'v-icon')]"),
    TOOLTIP_VIDEO_AD_SIZES_ICON("Video Card Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_VIDEO_FLOOR_PRICE_ICON("Video Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i");


    private String alias;
    private String selector;
}
