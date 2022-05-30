package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableComponentsElements {

    CHECKBOX( "Row Checkbox", "//table//tr[%s]/td[1]/div//div");

    private String alias;
    private String selector;
}
