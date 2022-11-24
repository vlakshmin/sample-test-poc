package widgets.common.table.filter.chip;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;
import widgets.common.table.ColumnNames;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.filter.FilterOptionsElements.FILTER_OPTION_BY_NAME;
import static widgets.common.table.filter.chip.ChipItemElements.*;

/**
 * Keep Selectors of UI elements in {@link ChipItemElements}
 */
@Getter
public class ChipItem {

    @Getter(AccessLevel.NONE)
    private SelenideElement closeIcon;
    @Getter(AccessLevel.NONE)
    private ElementsCollection chipItems;
    @Getter(AccessLevel.NONE)
    private SelenideElement headerLabel;

    public SelenideElement getHeaderLabel(String header) {

        return $x(String.format(HEADER_LABEL.getSelector(), header))
                .as(String.format(HEADER_LABEL.getAlias(), header));
    }

    public SelenideElement getCloseIcon(String header) {

        return $x(String.format(CLOSE_ICON.getSelector(), header))
                .as(String.format(CLOSE_ICON.getAlias(), header));
    }

    public SelenideElement getChipItems(String header) {

        return $x(String.format(LIST_ITEMS.getSelector(), header))
                .as(String.format(LIST_ITEMS.getAlias(), header));
    }
}
