package widgets.common.table.filter.chip;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChipItemElements {

    LIST_ITEMS("Chip's Items","//span[(@class='v-chip__content')and(contains(text(),'%s'))]" +
            "/descendant::span[@class='chip-list-container']/span/span[@class='v-chip__content']"),
    CLOSE_ICON("Close Icon","//span[(@class='v-chip__content')and(contains(text(),'%s'))]/button"),
    HEADER_LABEL("Header Label '%s'","//span[(@class='v-chip__content')and(contains(text(),'%s'))]");

    private String alias;
    private String selector;
}
