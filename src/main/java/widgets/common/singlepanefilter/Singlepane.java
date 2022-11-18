package widgets.common.singlepanefilter;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

/**
 * Keep Selectors of UI elements in {@link SinglepaneElements}
 */

@Getter
public class Singlepane {


    protected SinglepaneName singlepaneName;

    private SelenideElement searchInput;
    private SelenideElement excludedIcon;
    private SelenideElement includedIcon;
    private SelenideElement showInactive;
    private SelenideElement excludedBanner;
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
