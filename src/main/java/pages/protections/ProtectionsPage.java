package pages.protections;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.yield.dynamicprice.DynamicPricePageElements.DYNAMIC_PRICE_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link ProtectionsPageElements}
 */
@Getter
public class ProtectionsPage extends BasePage {

    private SelenideElement pageTitle = $x(DYNAMIC_PRICE_PAGE_TITLE.getSelector()).as(DYNAMIC_PRICE_PAGE_TITLE.getAlias());
}
