package widgets.common.filter.activebooleanfilter;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.filter.activebooleanfilter.ActiveBooleanFilterElements.*;


/**
 * Keep Selectors of UI elements in {@link ActiveBooleanFilterElements}
 */
@Getter
public class ActiveBooleanFilter {

    private SelenideElement filterHeaderLabel = $x(FILTER_HEADER.getSelector()).as(FILTER_HEADER.getAlias());
    private SelenideElement backButton = $x(BACK_BUTTON.getSelector()).as(BACK_BUTTON.getAlias());
    private SelenideElement activeRadioButtonButton = $x(ACTIVE_RADIO_BUTTON.getSelector()).as(ACTIVE_RADIO_BUTTON.getAlias());
    private SelenideElement inactiveRadioButton = $x(INACTIVE_RADIO_BUTTON.getSelector()).as(INACTIVE_RADIO_BUTTON.getAlias());
    private SelenideElement cancelButton = $x(CANCEL_BUTTON.getSelector()).as(CANCEL_BUTTON.getAlias());
    private SelenideElement submitButton = $x(SUBMIT_BUTTON.getSelector()).as(SUBMIT_BUTTON.getAlias());

}
