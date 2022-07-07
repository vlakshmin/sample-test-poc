package widgets.inventory.media.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaSidebarElements {

    TOOLTIP_PLACEHOLDER("Tooltip Placeholder","//span"),
    SITE_URL( "'Site URL' Input", "//label[text()='Site URL']/../input"),
    MEDIA_TYPE("'Media Type' Input", "//label[text()='Media Type']/../div"),
    MEDIA_NAME("'Media Name' Input", "//label[text()='Media Name']/../input"),
    CATEGORIES("'Categories' Input", "//label[text()='Categories']/../input"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    TOOLTIP_ICON_BY_FIELD_NAME("Tooltip '%s' Icon",
            "//label[text()='%s']/../div/span[@class='v-tooltip v-tooltip--bottom']/../i"),
    APP_STORE_URL( "'App Store URL' Input", "//label[text()='App Store URL']/../input"),
    SAVE_BUTTON("'Save Media' Button",  "//button/span[contains(text(),'Save Media')]"),
    PUBLISHER_NAME( "'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    ACTIVE_TOGGLE("'Active' Toggle",  "//label[text()='Active']/..//input[@role='switch']/..");


    private String alias;
    private String selector;
}
