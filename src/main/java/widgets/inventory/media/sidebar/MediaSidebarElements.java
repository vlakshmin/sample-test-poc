package widgets.inventory.media.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaSidebarElements {

    MEDIA_TYPE("'Media Type' ", "//label[text()='Media Type']/../div"),
    SITE_URL( "'Site URL' Input", "//label[text()='Site URL']/../input"),
    BUNDLE("'Bundle' Input", "//label[text()='Bundle']/../input"),
    MEDIA_NAME("'Media Name' Input", "//label[text()='Media Name']/../input"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    CATEGORIES("'Categories' Field", "//label[text()='Categories']/../div"),
    PUBLISHER_NAME( "'Publisher Name'", "//label[text()='Publisher Name']/../div"),
    TOOLTIP_ICON_BY_FIELD_NAME("Tooltip '%s' Icon",
            "//label[text()='%s']/../div/span[@class='v-tooltip v-tooltip--bottom']/../i"),
    CATEGORIES_INPUT("'Categories' Input", "//label[text()='Categories']/../div/input"),
    APP_STORE_URL( "'App Store URL' Input", "//label[text()='App Store URL']/../input"),
    SAVE_BUTTON("'Save Media' Button",  "//button/span[contains(text(),'Save Media')]"),
    MEDIA_TYPE_INPUT("'Media Type' Input", "//label[text()='Media Type']/../div/input"),
    PUBLISHER_ITEMS( "'Publisher's Items' Input",
            "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']"),
    ACTIVE_TOGGLE("'Active' Toggle",  "//label[text()='Active']/..//input[@role='switch']/../input"),
    PUBLISHER_NAME_INPUT( "'Publisher Name' Input", "//label[text()='Publisher Name']/../div/input"),
    MEDIA_TYPE_ITEMS("'Media Type Items'",
            "//div[contains(@class,'menuable__content__activ')]//div[contains(@class,'v-list-item')]"),
    CLOSE_ICON("'Close Ad Spot' Icon", "//div[@class='v-toolbar__content']/button[contains(@class,'v-btn--round theme--dark')]");

    private String alias;
    private String selector;
}
