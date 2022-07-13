package widgets.yield.openPricing.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricingSidebarElements {

    FLOOR_PRICE("'Floor Price' Input", "//label[text()='Floor Price']/../input"),
    OPEN_PRICING_NAME("'Open Pricing Name' Input", "//label[text()='Name']/../input"),
    SAVE_BUTTON("'Save Open Pricing' Button", "//button/span[contains(text(),'save')]"),
    ERROR_BY_FILED_NAME("'%s' Field Error", "//label[text()='%s']/../../..//div[@role='alert']"),
    PUBLISHER_NAME_DROPDOWN("'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    DROPDOWN_ITEMS("Dropdown Items", "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']");


    private String alias;
    private String selector;
}