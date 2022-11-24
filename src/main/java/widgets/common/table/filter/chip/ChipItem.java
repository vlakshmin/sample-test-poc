package widgets.common.table.filter.chip;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.filter.chip.ChipItemElements.*;

/**
 * Keep Selectors of UI elements in {@link ChipItemElements}
 */
@Getter
public class ChipItem {

    private SelenideElement closeIcon;
    private SelenideElement headerLabel;
    private ElementsCollection chipItems;

    public ChipItem(String name) {

        this.closeIcon = $x(String.format(CLOSE_ICON.getSelector(), name))
                .as(String.format(CLOSE_ICON.getAlias(), name));
        this.chipItems = $$x(String.format(LIST_ITEMS.getSelector(), name))
                .as(String.format(LIST_ITEMS.getAlias(), name));
        this.headerLabel = $x(String.format(HEADER_LABEL.getSelector(), name))
                .as(String.format(HEADER_LABEL.getAlias(), name));
    }
}
