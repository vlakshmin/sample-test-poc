package widgets.common.multipane.item.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonTableItemElements {

    EXCLUDED_ICON("'Excluded' Icon of row %s in table list in Multipane","/tr/td[contains(@class,'excluded')]"),
    INCLUDED_ICON("'Included' Icon of row %s in table list in Multipane","/tr/td[contains(@class,'included')]"),
    EXCLUDE_BUTTON("'Exclude' Button of row %s in table list in Multipane","/tr/td[3]/div[contains(@class,'exclude')]/button[2]"),
    INCLUDE_BUTTON("'Include' Button of row %s in table list in Multipane","/tr/td[3]/div[contains(@class,'include')]/button[2]");

    private String alias;
    private String selector;
}
