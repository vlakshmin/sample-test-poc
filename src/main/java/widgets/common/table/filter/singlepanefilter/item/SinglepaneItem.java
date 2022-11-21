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
    private SelenideElement includeIcon;

    @Getter(AccessLevel.NONE)
    private int position;
    @Getter(AccessLevel.NONE)
    protected String singlepaneItem;

    public SinglepaneItem(int position) {

        this.position = position;
        this.id = $x(buildXpath(ID.getSelector())).as(format("%s", ID.getAlias(), position));
        this.name = $x(buildXpath(NAME.getSelector())).as(format("%s", NAME.getAlias(), position));
        this.includeIcon = $x(buildXpath(INCLUDE_ICON.getSelector())).as(format("%s", INCLUDE_ICON.getAlias(), position));
    }

    protected String buildXpath(String elementXpath) {

        return format("%s[%s]%s",
                FILTER_ITEMS.getSelector(),
                position,
                elementXpath);
    }
}
