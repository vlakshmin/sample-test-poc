package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TablePaginationElements {

    PAGE_MENU( "Page Menu", "//*[@class='v-data-footer']//div[@class='v-input__slot']");

    private String alias;
    private String selector;
}
