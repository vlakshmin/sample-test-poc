package pages.admin.sales.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DealsPageElements {

    MEDIA_PAGE_TITLE( "'Deals' Page Title", "//h1"),
    CREATE_DEAL_BUTTON("'Create Deal' Button", "//button//span[text()='Create Deal']");

    private String alias;
    private String selector;
}
