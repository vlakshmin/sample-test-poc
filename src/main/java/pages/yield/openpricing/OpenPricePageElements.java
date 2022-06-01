package pages.yield.openpricing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenPricePageElements {

    OPEN_PRICE_PAGE_TITLE( "'Open Price' Page Title", "//h1");

    private String alias;
    private String selector;
}
