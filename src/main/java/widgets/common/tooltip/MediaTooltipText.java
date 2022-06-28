package widgets.common.tooltip;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.warningbanner.ChangePublisherBannerElements;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.tooltip.MediaTooltipTextElements.*;

/**
 * Keep Selectors of UI elements in {@link MediaTooltipTextElements}
 */
@Getter
public class MediaTooltipText {

    private SelenideElement tooltipText = $x(CATEGORY_TOOLTIP_TEXT.getSelector()).as(CATEGORY_TOOLTIP_TEXT.getAlias());
    private SelenideElement tooltip = $x(TOOLTIP.getSelector()).as(TOOLTIP.getAlias());
}
