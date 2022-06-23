package widgets.common.multipane.item.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonTableItemElements {

    EXCLUDED_ICON("'Excluded' Icon of row %s","/tr/td[contains(@class,'excluded')]"),
    INCLUDED_ICON("'Included' Icon of row %s","/tr/td[contains(@class,'included')]"),
    EXCLUDE_BUTTON("'Exclude' Button of row %s","/tr/td[contains(@class,'can-exclude')]"),
    INCLUDE_BUTTON("'Include' Button of row %s","/tr/td[contains(@class,'can-include')]");

    private String alias;
    private String selector;
}
