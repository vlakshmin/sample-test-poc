package widgets.common.filter.abstractt;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.filter.abstractt.BaseFilterElements.*;


/**
 * Keep Selectors of UI elements in {@link BaseFilterElements}
 */
@Getter
public abstract class BaseFilter {

    private SelenideElement backButton = $x(BACK_BUTTON.getSelector()).as(BACK_BUTTON.getAlias());
    private SelenideElement cancelButton = $x(CANCEL_BUTTON.getSelector()).as(CANCEL_BUTTON.getAlias());
    private SelenideElement submitButton = $x(SUBMIT_BUTTON.getSelector()).as(SUBMIT_BUTTON.getAlias());
    private SelenideElement filterHeaderLabel = $x(FILTER_HEADER.getSelector()).as(FILTER_HEADER.getAlias());

}
