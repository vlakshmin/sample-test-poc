package widgets.yield.openPricing.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricingSidebarElements {

    FLOOR_PRICE("'Floor Price' Input", "//label[text()='Floor Price']/../input"),
    SAVE_BUTTON("'Save Open Pricing' Button", "//button/span[contains(text(),'save')]"),
    OPEN_PRICING_FIELD_NAME("'Open Pricing '%s'' Input", "//label[text()='%s']/../input"),
    ERROR_BY_FILED_NAME("'%s' Field Error", "//label[text()='%s']/../../..//div[@role='alert']"),
    PUBLISHER_NAME_DROPDOWN("'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    DROPDOWN_ITEMS("Dropdown Items", "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']");


    private String alias;
    private String selector;
}
