package widgets.common.filter.activebooleanfilter;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.filter.abstractt.BaseFilter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.filter.activebooleanfilter.ActiveBooleanFilterElements.*;


/**
 * Keep Selectors of UI elements in {@link ActiveBooleanFilterElements}
 */
@Getter
public class ActiveBooleanFilter extends BaseFilter {


    private SelenideElement activeRadioButtonButton = $x(ACTIVE_RADIO_BUTTON.getSelector()).as(ACTIVE_RADIO_BUTTON.getAlias());
    private SelenideElement inactiveRadioButton = $x(INACTIVE_RADIO_BUTTON.getSelector()).as(INACTIVE_RADIO_BUTTON.getAlias());

}
