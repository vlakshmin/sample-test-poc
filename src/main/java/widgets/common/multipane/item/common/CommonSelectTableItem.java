package widgets.common.multipane.item.common;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.multipane.MultipaneName;
import widgets.common.multipane.item.abstractt.MultipaneItem;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.multipane.item.common.CommonTableItemElements.*;

/**
 * Keep Selectors of UI elements in {@link CommonTableItemElements}
 */
@Getter
public class CommonTableItem extends MultipaneItem {

    private SelenideElement excludedIcon;
    private SelenideElement includedIcon;
    private SelenideElement excludeButton;
    private SelenideElement includeButton;

    public CommonTableItem(int position, String tableItem, MultipaneName  multipaneName) {
        super(position, tableItem, multipaneName);

        this.excludedIcon = $x(buildXpath(EXCLUDED_ICON.getSelector())).as(format("%s%s", EXCLUDED_ICON.getAlias(), position));
        this.includedIcon = $x(buildXpath(INCLUDED_ICON.getSelector())).as(format("%s%s", INCLUDED_ICON.getAlias(), position));
        this.excludeButton = $x(buildXpath(EXCLUDE_BUTTON.getSelector())).as(format("%s%s", EXCLUDE_BUTTON.getAlias(), position));
        this.includeButton = $x(buildXpath(INCLUDE_BUTTON.getSelector())).as(format("%s%s", INCLUDE_BUTTON.getAlias(), position));
    }
}
