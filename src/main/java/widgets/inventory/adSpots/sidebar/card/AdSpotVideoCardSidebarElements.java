package widgets.inventory.adSpots.sidebar.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotVideoCardSidebarElements {

    VIDEO_PANEL("'Video' Panel with Elements", "//div[text()='Video']/../../span"),
    VIDEO_PLACEMENT_TYPE_ITEMS("'Video' Placement Type Items",
            "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']"),
    VIDEO_PLAYBACK_METHODS_ITEMS("'Video' Playback Methods Items",
            "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']"),
    VIDEO_PLAYBACK_METHODS_SELECTED_ITEMS("'Video' Playback Methods Selected Items",
            "//label[text()='Video Playback Methods']/..//div[@class='v-select__selections']/div"),
    ENABLED_TOGGLE("'Video' Active Toggle", "//div[text()='Video']/..//input[@role='switch']"),
    MIN_VIDEO_DURATION("Minimum Video Duration (seconds)",
            "//div[text()='Video']/../..//label[text()='Minimum Video Duration (seconds)']/../../div/input[1]"),
    MAX_VIDEO_DURATION("Maximum Video Duration (seconds)",
            "//div[text()='Video']/../..//label[text()='Maximum Video Duration (seconds)']/../../div/input[1]"),
    VIDEO_CARD("'Video' Card", "//div[text()='Video']/../button//i[contains(@class,'v-icon')]"),
    TOOLTIP_VIDEO_AD_SIZES_ICON("Video Card Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    VIDEO_AD_SIZES("'Video' Ad Sizes", "//div[text()='Video']/../..//label[text()='Ad Sizes']/../div[1]"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//div[text()='Video']/../..//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    TOOLTIP_VIDEO_FLOOR_PRICE_ICON("Video Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    VIDEO_PLACEMENT_TYPE("'Video' Placement Type", "//label[text()='Video Placement Type']/../../div/div[2]"),
    VIDEO_PLAYBACK_METHODS("'Video' Playback Methods", "//label[text()='Video Playback Methods']/../../div/div[2]"),
    VIDEO_FLOOR_PRICE("'Video' Floor Price", "//div[text()='Video']/../..//label[text()='Floor Price']/../../div/input"),
    VIDEO_FLOOR_PRICE_CURRENCY("'Video' Floor Price Currency", "//div[text()='Video']/../..//label[text()='Floor Price']/../../div/div");


    private String alias;
    private String selector;
}
