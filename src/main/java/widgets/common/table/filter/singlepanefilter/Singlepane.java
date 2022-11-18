package widgets.common.table.filter.singlepanefilter;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.table.filter.abstractt.BaseFilter;

/**
 * Keep Selectors of UI elements in {@link SinglepaneElements}
 */

@Getter
public class Singlepane extends BaseFilter {


    private SelenideElement searchInput;
    private SelenideElement includedIcon;
    private SelenideElement includedBanner;
    private SelenideElement panelNameLabel;
    private SelenideElement clearAllButton;
    private SelenideElement clearSearchIcon;
    private SelenideElement includeAllButton;
    private SelenideElement selectionInfoLabel;
    private SelenideElement selectionInfoExcludedLabel;

    private SelenideElement itemsQuantityString;

    private static final String SINGLEPANE_ROOT = "//h3[text()='%s']/../..";

}
