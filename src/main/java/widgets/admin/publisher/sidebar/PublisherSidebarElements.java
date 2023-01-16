package widgets.admin.publisher.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublisherSidebarElements {

    DOMAIN("'Publisher Domain' Input", "//label[text()='Domain']/../input"),
    MAIL("'Publisher Mail' Input", "//label[text()='Ad Ops Email']/../input"),
    NAME("'Publisher Name' Input", "//label[text()='Name']/../input"),
    CURRENCY_DROPDOWN("'Currency' Dropdown", "//label[text()='Currency']/../div"),
    CURRENCY("'Publisher Currency' Input", "//label[text()='Currency']/../input"),
    AD_OPS_PERSON("'Ad Ops Person' Input", "//label[text()='Ad Ops Person']/../input"),
    CATEGORIES("'Categories' Input", "//label[text()='Categories']/../div[1]"),
    SAVE_BUTTON("'Save Publisher' Button", "//button/span[contains(text(),'Save Publisher')]"),
    ACTIVE_TOGGLE("'Active' Toggle", "//label[text()='Active']/..//input[@role='switch']/../input"),
    CLOSE_ICON("'Close Publisher' Icon", "//header/div/button/span/i"),
    CURRENCY_DROPDOWN_ITEMS("'Currency' Dropdown Items'", "//div[contains(@class,'menuable__content__activ')]//div[contains(@id,'list-item')]"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'", "//label[text()='%s']/../../..//child::div[contains(@class,'v-messages__message')]");

    private String alias;
    private String selector;
}
