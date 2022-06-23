package widgets.common.multipane.item.multipane;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneItemElements {

    NAME("'Name' Label of row %s", "//td[2]/div"),
    TYPE("'Type' Label of row %s", "//td[3]/div"),
    EXCLUDED_ICON("'Excluded' Icon of row %s","//td[contains(@class,'excluded')]"),
    INCLUDED_ICON("'Included' Icon of row %s","//td[contains(@class,'included')]"),
    EXCLUDE_BUTTON("'Exclude' Button of row %s","//td[contains(@class,'can-exclude')]"),
    INCLUDE_BUTTON("'Include' Button of row %s","//td[contains(@class,'can-include')]");

    private String alias;
    private String selector;
}
