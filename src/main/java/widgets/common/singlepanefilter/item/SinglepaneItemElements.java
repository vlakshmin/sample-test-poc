package widgets.common.singlepanefilter.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SinglepaneItemElements {

    NAME("'Name' Label of row %s in table list in Singlepane", "//td/div"),
    ID("'ID' Label of row %s in table list in Singlepane", "//td[2]/div"),
    INCLUDE_ICON("'Include' Label of row %s in table list in Multipane", "//tr[@class='select-row']/td[3]");

    private String alias;
    private String selector;
}
