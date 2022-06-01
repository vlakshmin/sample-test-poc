package pages.inventory.media;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaPageElements {

    DEALS_PAGE_TITLE( "'Media' Page Title", "//h1");

    private String alias;
    private String selector;
}
