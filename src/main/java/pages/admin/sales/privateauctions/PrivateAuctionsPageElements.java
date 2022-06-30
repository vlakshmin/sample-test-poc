package pages.admin.sales.privateauctions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrivateAuctionsPageElements {

    ADSPOTS_PAGE_TITLE( "'Private Auctions' Page Title", "//h1");

    private String alias;
    private String selector;
}
