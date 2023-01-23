package widgets.common.table.filter.platformfilter;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.table.filter.abstractt.BaseFilter;

import static com.codeborne.selenide.Selenide.$x;


/**
 * Keep Selectors of UI elements in {@link PlatformFilterElements}
 */
@Getter
public class PlatformFilter extends BaseFilter {

    private SelenideElement iOSCheckBox = $x(PlatformFilterElements.IOS_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.IOS_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement ctvWebCheckBox = $x(PlatformFilterElements.CTV_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.CTV_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement pcWebCheckBox = $x(PlatformFilterElements.PC_WEB_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.PC_WEB_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement androidCheckBox = $x(PlatformFilterElements.ANDROID_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.ANDROID_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement mobileWebCheckBox = $x(PlatformFilterElements.MOBILE_WEB_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.MOBILE_WEB_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement iOSWebViewCheckBox = $x(PlatformFilterElements.IOS_WEB_VIEW_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.IOS_WEB_VIEW_PLATFORM_CHECkBOX.getAlias());
    private SelenideElement androidWebViewCheckBox = $x(PlatformFilterElements.ANDROID_WEB_VIEW_PLATFORM_CHECkBOX.getSelector())
            .as(PlatformFilterElements.ANDROID_WEB_VIEW_PLATFORM_CHECkBOX.getAlias());
}
