package widgets.common.multipane.item.included;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IncludedTableItemElements {

    PARENT_LABEL("'Parent' Label of row %s in 'Included/Excluded' list in Multipane","/tr/td[contains(@class,'can-exclude')]"),
    REMOVE_BUTTON("'Remove' Button of row %s in 'Included/Excluded' list in Multipane","/tr/td[contains(@class,'can-include')]");

    private String alias;
    private String selector;
}
