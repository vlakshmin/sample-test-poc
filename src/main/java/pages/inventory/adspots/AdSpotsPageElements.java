package pages.inventory.adspots;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotsPageElements {

    ADSPOTS_PAGE_TITLE("'Ad Spots' Page Title", "//h1"),
    ADSPOT_ITEMS("Ad Spot Items", "//tbody/tr"),
    CREATE_ADSPOT_BUTTON("'Create Ad Spot' Button", "//button//span[text()='Create Ad Spot']"),
    EDIT_ADSPOT_BUTTON("'Edit Ad Spot' Button", "//button//span[text()='Edit Ad Spot']");

    private String alias;
    private String selector;
}
