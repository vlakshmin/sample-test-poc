package pages.yield.openpricing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricingPageElements {

    OPEN_PRICE_PAGE_TITLE("'Open Pricing Rules' Page Title", "//h1"),
    OPEN_PRICING_ITEMS("Open Pricing Items", "//tbody/tr"),
    CREATE_OPEN_PRICING_BUTTON("'Create Open Pricing Rule' Button", "//button//span[text()='Create Open Pricing Rule']"),
    EDIT_OPEN_PRICING_BUTTON("'Edit Open Pricing Rule' Button", "//button//span[text()='Edit Open Pricing Rule']");


    private String alias;
    private String selector;
}
