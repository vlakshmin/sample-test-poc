package widgets.inventory.adSpots.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotSidebarElements {

    TOOLTIP_PLACEHOLDER("","//span"),
    FILTER( "'Filter' Input", "//label[text()='Filter']/../input"),
    POSITION( "'Position' Input", "//label[text()='Position']/../input"),
    CATEGORIES("'Categories' Input", "//label[text()='Categories']/../input"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    TOOLTIP_DEFAULT_FLOOR_PRICE_ICON("Tooltip Default Floor Price Icon",
            "//*[text()='Default Floor Price']/../../div/*[contains(@class,'tooltip')]/../i"),
    TOOLTIP_CONTENT_FOR_CHILDREN_ICON("Tooltip Content for Children  Icon",
            "//*[text()='Content for Children']/../i"),
    TOOLTIP_DEFAULT_AD_SIZES_ICON("Tooltip Default Ad Sizes Icon",
            "//*[text()='Default Ad Sizes']/../div/span[contains(@class,'tooltip')]/../i"),
    TOOLTIP_CATEGORIES_ICON("Tooltip Categories Icon",
            "//*[text()='Categories']/../div/*[contains(@class,'tooltip')]/../i"),
    AD_SPOT_NAME("'Ad Spot Name' Input", "//label[text()='Ad Spot Name']/../input"),
    RELATED_MEDIA("'Related Media' Input", "//label[text()='Related Media']/../div"),
    PUBLISHER_NAME( "'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    SAVE_BUTTON("'Save Ad Spot' Button",  "//button/span[contains(text(),'Save Ad Spot')]"),
    DEFAULT_AD_SIZES( "'Default Ad Sizes' Input", "//label[text()='Default Ad Sizes']/../input"),
    ACTIVE_TOGGLE("'Active' Toggle",  "//label[text()='Active']/..//input[@role='switch']/.."),
    VIDEO_CARD( "'Video' Card", "//div[text()='Video']/../button//i[contains(@class,'v-icon')]"),
    BANNER_CARD( "'Banner' Card", "//div[text()='Banner']/../button//i[contains(@class,'v-icon')]"),
    NATIVE_CARD( "'Native' Card", "//div[text()='Native']/../button//i[contains(@class,'v-icon')]"),
    TOOLTIP_BANNER_FLOOR_PRICE_ICON("Banner Card  Floor Price Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_VIDEO_FLOOR_PRICE_ICON("Video Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Floor Price']/../../div/*[contains(@class,'v-tooltip')]/../i"),
    DEFAULT_FLOOR_PRICE( "'Default Floor Price' Input", "//label[text()='Default Floor Price']/../input"),
    TOOLTIP_BANNER_AD_SIZES_ICON("Banner Card  Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Banner']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_VIDEO_AD_SIZES_ICON("Video Card Ad Sizes Tooltip '%s' Icon",
            "//div[text()='Video']/../..//*[text()='Ad Sizes']/../div/*[contains(@class,'v-tooltip')]/../i"),
    TOOLTIP_NATIVE_FLOOR_PRICE_ICON("Native Card Floor Price Tooltip '%s' Icon",
            "//div[text()='Native']/../..//*[text()='Floor Price']/../../div/span[contains(@class,'v-tooltip')]/../i"),
    CLOSE_ICON("'Close Ad Spot' Icon",  "//div[@class='v-toolbar__content']/button[contains(@class,'v-btn--round theme--dark')]");


    private String alias;
    private String selector;
}
