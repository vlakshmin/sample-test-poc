package pages.yield.openpricing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricingPageElements {

    OPEN_PRICE_PAGE_TITLE("'Open Pricing Rules' Page Title", "//h1"),
    OPEN_PRICING_ITEMS("Open Pricing Items", "//tbody/tr"),
    CREATE_OPEN_PRICING_BUTTON("'Create Open Pricing Rule' Button", "//button//span[text()='CREATE OPEN PRICING RULE']"),
    EDIT_OPEN_PRICING_BUTTON("'Edit Open Pricing Rule' Button", "//button//span[text()='Edit Open Pricing Rule']"),
    ACTIVATE_OPEN_PRICING_BUTTON("'Activate OpenPricing' Button", "//button//span[text()='Activate']"),
    DEACTIVATE_OPEN_PRICING_BUTTON("'Deactivate OpenPricing' Button", "//button//span[text()='Deactivate']");

    private String alias;
    private String selector;
}
