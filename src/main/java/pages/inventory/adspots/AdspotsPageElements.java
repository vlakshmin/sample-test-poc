package pages.inventory.adspots;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdspotsPageElements {

    PRIVATE_AUCTIONS_PAGE_TITLE( "'Adspots' Page Title", "//h1");

    private String alias;
    private String selector;
}
