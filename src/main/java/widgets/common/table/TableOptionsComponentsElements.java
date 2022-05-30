package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableOptionsComponentsElements {

    TABLE_OPTIONS_COMPONENTS_BUTTON( "'Table Options' button", "//button/span[contains(text(),'Table Options')]");

    private String alias;
    private String selector;
}
