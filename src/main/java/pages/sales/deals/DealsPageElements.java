package pages.sales.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DealsPageElements {

    MEDIA_PAGE_TITLE( "'Deals' Page Title", "//h1");

    private String alias;
    private String selector;
}
