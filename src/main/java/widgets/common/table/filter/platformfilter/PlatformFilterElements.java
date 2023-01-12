
package widgets.common.table.filter.platformfilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformFilterElements {

    IOS_PLATFORM_CHECkBOX("'iOS' platform checkbox in 'Platform' Filter","//div[contains(text(),'iOS')]/../..//input"),
    ANDROID_PLATFORM_CHECkBOX("'Android' platform checkbox in 'Platform' Filter","//div[contains(text(),'Android')]/../..//input"),
    MOBILE_WEB_CHECkBOX("'Mobile Web' platform checkbox in 'Platform' Filter","//div[contains(text(),'Mobile Web')]/../..//input");

    private String alias;
    private String selector;
}
