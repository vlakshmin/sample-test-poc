package widgets.inventory.adSpots.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotSidebarElements {

    FILTER("'Filter' Input", "//label[text()='Filter']/../input"),
    POSITION("'Position' Input", "//label[text()='Position']/../div/input"),
    CATEGORIES("'Categories' Element", "//label[text()='Categories']/../div[1]"),
    CATEGORIES_INPUT("'Categories' Input", "//label[text()='Categories']/../div/input"),
    ERROR_ALERT_BY_FIELD_NAME("Error Alert under Field '%s'",
            "//label[text()='%s']/../../..//child::div[@class='v-messages__message']"),
    AD_SPOT_NAME("'Ad Spot Name' Input", "//label[text()='Ad Spot Name']/../input"),
    RELATED_MEDIA("'Related Media' Input", "//label[text()='Related Media']/../div/input"),
    PUBLISHER_NAME("'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    SAVE_BUTTON("'Save Ad Spot' Button", "//button/span[contains(text(),'Save Ad Spot')]"),
    ACTIVE_TOGGLE("'Active' Toggle", "//label[text()='Active']/..//input[@role='switch']"),
    DEFAULT_AD_SIZES("'Default Ad Sizes' Input", "//label[text()='Default Ad Sizes']/../div/input"),
    DEFAULT_FLOOR_PRICE("'Default Floor Price' Input", "//label[text()='Default Floor Price']/../input"),
    CONTENT_FOR_CHILDREN("'Content For Children' Toggle", "//span[text()='Content for Children']/../../div/input[@role='switch']"),
    TEST_MODE("'Test Mode' Toggle", "//label[text()='Test Mode']/..//input[@role='switch']"),
    CLOSE_ICON("'Close Ad Spot' Icon", "//div[@class='v-toolbar__content']/button[contains(@class,'v-btn--round theme--dark')]");


    private String alias;
    private String selector;
}
