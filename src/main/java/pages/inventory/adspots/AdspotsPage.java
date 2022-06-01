package pages.inventory.adspots;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.sales.privateauctions.PrivateAuctionsPageElements.ADSPOTS_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link AdspotsPageElements}
 */
@Getter
public class AdspotsPage extends BasePage {

    private SelenideElement pageTitle = $x(ADSPOTS_PAGE_TITLE.getSelector()).as(ADSPOTS_PAGE_TITLE.getAlias());
}
