package widgets.admin.publisher.sidebar;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.admin.publisher.sidebar.PublisherSidebarElements.*;
import static widgets.admin.publisher.sidebar.PublisherSidebarElements.AD_OPS_PERSON;

/**
 * Keep Selectors of UI elements in {@link PublisherSidebarElements}
 */
@Getter
public abstract class PublisherSidebar {

    private SelenideElement mailInput = $x(MAIL.getSelector()).as(MAIL.getAlias());
    private SelenideElement nameInput = $x(NAME.getSelector()).as(NAME.getAlias());
    private SelenideElement domainInput = $x(DOMAIN.getSelector()).as(DOMAIN.getAlias());
    private SelenideElement currency = $x(CURRENCY.getSelector()).as(CURRENCY.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement adOpsInput = $x(AD_OPS_PERSON.getSelector()).as(AD_OPS_PERSON.getAlias());
}