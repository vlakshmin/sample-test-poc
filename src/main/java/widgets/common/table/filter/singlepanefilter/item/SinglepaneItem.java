package widgets.common.table.filter.singlepanefilter.item;

import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

import static widgets.common.table.filter.singlepanefilter.SinglepaneElements.FILTER_ITEMS;
import static widgets.common.table.filter.singlepanefilter.item.SinglepaneItemElements.*;

/**
 * Keep Selectors of UI elements in {@link SinglepaneItemElements}
 */
@Getter
public class SinglepaneItem {

    private SelenideElement id;
    private SelenideElement name;
    private SelenideElement includeButton;

    @Getter(AccessLevel.NONE)
    private int position;
    @Getter(AccessLevel.NONE)
    protected String singlepaneItem;

    public SinglepaneItem(int position, String columnFilterName) {

        this.position = position;

        switch (columnFilterName) {
            case "ID":
                this.id = $x(buildXpath(format(ID.getSelector(), 2))).as(ID.getAlias());
                this.name = $x(buildXpath(format(NAME.getSelector(), 3))).as(NAME.getAlias());
                this.includeButton = $x(buildXpath(format(INCLUDE_ICON.getSelector(), 4))).as(INCLUDE_ICON.getAlias());
                break;
            default:
                this.name = $x(buildXpath(format(NAME.getSelector(), 2))).as(NAME.getAlias());
                this.includeButton = $x(buildXpath(format(INCLUDE_ICON.getSelector(), 3))).as(INCLUDE_ICON.getAlias());
        }
    }

    protected String buildXpath(String elementXpath) {

        return format("%s[%s]%s",
                FILTER_ITEMS.getSelector(),
                position,
                elementXpath);
    }
}
