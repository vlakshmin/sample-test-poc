package pages.yield.openpricing;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.yield.openpricing.OpenPricingPageElements.OPEN_PRICE_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link OpenPricingPageElements}
 */
@Getter
public class OpenPricingPage extends BasePage {

    private SelenideElement pageTitle = $x(OPEN_PRICE_PAGE_TITLE.getSelector()).as(OPEN_PRICE_PAGE_TITLE.getAlias());
}
