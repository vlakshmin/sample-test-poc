package widgets.common.multipane.item.included;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IncludedTableItemElements {

    PARENT_LABEL("'Parent' Label of row %s in 'Included/Excluded' list in Multipane","/td/div[@class='parent-label']"),
    REMOVE_BUTTON("'Remove' Button of row %s in 'Included/Excluded' list in Multipane","/td[contains(@class,'options')]//i");

    private String alias;
    private String selector;
}
