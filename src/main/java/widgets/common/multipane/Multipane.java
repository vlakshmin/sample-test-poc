package widgets.common.multipane;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.multipane.MultipaneElements.*;

/**
 * Keep Selectors of UI elements in {@link MultipaneElements}
 */
@Getter
public class Multipane {

    private String panelName;

    private SelenideElement searchInput;
    private SelenideElement clearAllButton;
    private SelenideElement includeAllButton;
    private SelenideElement itemsQuantityString;

    private static final String MULTIPANE_ROOT = "//button/h3[text()='%s']/../../..";
    //button/h3[text()='%s']/../../..

    private Multipane(String panelName){
        this.panelName =  panelName;

        this.searchInput =  $x(buildXpath(SEARCH_INPUT.getSelector())).as(SEARCH_INPUT.getAlias());
        this.clearAllButton = $x(buildXpath(CLEAR_ALL_BUTTON.getSelector())).as(CLEAR_ALL_BUTTON.getAlias());
        this.includeAllButton = $x(buildXpath(INCLUDE_ALL_BUTTON.getSelector())).as(INCLUDE_ALL_BUTTON.getAlias());
        this.itemsQuantityString =  $x(buildXpath(ITEMS_QUANTITY_STRING.getSelector())).as(ITEMS_QUANTITY_STRING.getAlias());

    }

    // Todo refactor as getIncludeAllButton. Keep selectors in MultipaneElements enumeration

    public String buildXpath(String elementXpath) {

        return (format("%s%s", format(MULTIPANE_ROOT, panelName), elementXpath));
    }
}