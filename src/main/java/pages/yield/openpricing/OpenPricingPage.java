package pages.yield.openpricing;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import pages.BasePage;
import widgets.common.table.Table;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static pages.inventory.adspots.AdSpotsPageElements.ADSPOT_ITEMS;
import static pages.inventory.adspots.AdSpotsPageElements.CREATE_ADSPOT_BUTTON;
import static pages.yield.openpricing.OpenPricingPageElements.OPEN_PRICE_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link OpenPricingPageElements}
 */
@Getter
public class OpenPricingPage extends BasePage {

    @Getter(AccessLevel.NONE)
    private SelenideElement pageTitle = $x(OPEN_PRICE_PAGE_TITLE.getSelector()).as(OPEN_PRICE_PAGE_TITLE.getAlias());
    private ElementsCollection openPricingItems = $$x(ADSPOT_ITEMS.getSelector()).as(ADSPOT_ITEMS.getAlias());
    private SelenideElement createAdSpotButton = $x(CREATE_ADSPOT_BUTTON.getSelector()).as(CREATE_ADSPOT_BUTTON.getAlias());
    private Table openPricingTable = new Table();

}
