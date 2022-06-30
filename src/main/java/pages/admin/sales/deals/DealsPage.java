package pages.admin.sales.deals;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.admin.sales.deals.DealsPageElements.CREATE_DEAL_BUTTON;
import static pages.admin.sales.deals.DealsPageElements.MEDIA_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link DealsPageElements}
 */
@Getter
public class DealsPage extends BasePage {

    private SelenideElement pageTitle = $x(MEDIA_PAGE_TITLE.getSelector()).as(MEDIA_PAGE_TITLE.getAlias());
    private SelenideElement createNewDealButton = $x(CREATE_DEAL_BUTTON.getSelector()).as(CREATE_DEAL_BUTTON.getAlias());
}
