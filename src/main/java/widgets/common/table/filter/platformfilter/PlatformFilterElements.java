
package widgets.common.table.filter.platformfilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformFilterElements {

    CTV_PLATFORM_CHECkBOX("'CTV' platform checkbox in 'Platform' Filter","//div[contains(text(),'CTV')]/../..//input"),
    IOS_PLATFORM_CHECkBOX("'iOS' platform checkbox in 'Platform' Filter","//div[contains(text(),'iOS')]/../..//input"),
    PC_WEB_PLATFORM_CHECkBOX("'PC Web' platform checkbox in 'Platform' Filter","//div[contains(text(),'PC Web')]/../..//input"),
    ANDROID_PLATFORM_CHECkBOX("'Android' platform checkbox in 'Platform' Filter","//div[contains(text(),'Android')]/../..//input"),
    MOBILE_WEB_PLATFORM_CHECkBOX("'Mobile Web' platform checkbox in 'Platform' Filter","//div[contains(text(),'Mobile Web')]/../..//input"),
    IOS_WEB_VIEW_PLATFORM_CHECkBOX("'iOS Web View' platform checkbox in 'Platform' Filter","//div[contains(text(),'iOS Web View')]/../..//input"),
    ANDROID_WEB_VIEW_PLATFORM_CHECkBOX("'Android Web View' platform checkbox in 'Platform' Filter","//div[contains(text(),'Android Web View')]/../..//input");

    private String alias;
    private String selector;
}
