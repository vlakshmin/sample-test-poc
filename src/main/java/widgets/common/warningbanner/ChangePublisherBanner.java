package widgets.common.warningbanner;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

import static widgets.common.warningbanner.ChangePublisherBannerElements.*;


/**
 * Keep Selectors of UI elements in {@link ChangePublisherBannerElements}
 */
@Getter
public class ChangePublisherBanner {

    private SelenideElement activeCheckBox = $x(WARNING_ICON.getSelector()).as(WARNING_ICON.getAlias());
    private SelenideElement cancelButton = $x(CANCEL_BUTTON.getSelector()).as(CANCEL_BUTTON.getAlias());
    private SelenideElement acceptButton = $x(ACCEPT_BUTTON.getSelector()).as(ACCEPT_BUTTON.getAlias());
    private SelenideElement changePublisherLabel = $x(CHANGE_PUBLISHER_LABEL.getSelector()).as(CHANGE_PUBLISHER_LABEL.getAlias());
}
