package widgets.protections.advertiser;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneName;
import widgets.common.multipane.MultipaneNameImpl;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.protections.advertiser.ProtectionTypeElements.*;


/**
 * Keep Selectors of UI elements in {@link ProtectionTypeElements}
 */
@Getter
public class ProtectionType extends Multipane {

    private SelenideElement searchInput;
    private SelenideElement excludedIcon;
    private SelenideElement includedIcon;
    private SelenideElement excludedBanner;

    public ProtectionType(MultipaneNameImpl protectionTypeName){
        super(protectionTypeName);

        this.searchInput =  $x(buildXpath(SEARCH_INPUT.getSelector())).as(SEARCH_INPUT.getAlias());
        this.excludedIcon = $x(buildXpath(EXCLUDED_ICON.getSelector())).as(EXCLUDED_ICON.getAlias());
        this.includedIcon = $x(buildXpath(INCLUDED_ICON.getSelector())).as(INCLUDED_ICON.getAlias());
        this.excludedBanner = $x(buildXpath(EXCLUDED_BANNER.getSelector())).as(EXCLUDED_BANNER.getAlias());
    }

}