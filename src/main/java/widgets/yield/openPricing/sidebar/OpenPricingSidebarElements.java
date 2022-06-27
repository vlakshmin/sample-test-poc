package widgets.yield.openPricing.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricingSidebarElements {

    OPEN_PRICING_NAME("'Open Pricing Name' Input", "//label[text()='Name']/../input"),
    PUBLISHER_NAME("'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    FLOOR_PRICE("'Floor Price' Input", "//label[text()='Floor Price']/../input"),
    SAVE_BUTTON("'Save Ad Spot' Button", "//button/span[contains(text(),'save')]");

    private String alias;
    private String selector;
}
