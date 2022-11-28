package widgets.common.table.filter.chip;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.table.filter.chip.ChipItemElements.*;

/**
 * Keep Selectors of UI elements in {@link ChipItemElements}
 */
@Getter
public class ChipItem {

    private int position;

    private SelenideElement closeIcon;
    private SelenideElement headerLabel;
    private ElementsCollection chipItems;

    private static final String CHIP_ITEM = "//span[contains(@class,'container-chip align-self-center v-chip')]";

    public ChipItem(int position) {
        this.position = position;
        this.closeIcon = $x(buildXpath(CLOSE_ICON.getSelector())).as(CLOSE_ICON.getAlias());
        this.chipItems =$$x(buildXpath(LIST_ITEMS.getSelector())).as(LIST_ITEMS.getAlias());
        this.headerLabel = $x(buildXpath(HEADER_LABEL.getSelector())).as(HEADER_LABEL.getAlias());;
    }

    public String buildXpath(String elementXpath) {

        return (format("%s[%s]/%s", CHIP_ITEM, position, elementXpath));
    }
}
