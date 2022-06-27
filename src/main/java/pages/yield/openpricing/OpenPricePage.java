package pages.yield.openpricing;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.yield.openpricing.OpenPricePageElements.OPEN_PRICE_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link OpenPricePageElements}
 */
@Getter
public class OpenPricePage extends BasePage {

    private SelenideElement pageTitle = $x(OPEN_PRICE_PAGE_TITLE.getSelector()).as(OPEN_PRICE_PAGE_TITLE.getAlias());
}
