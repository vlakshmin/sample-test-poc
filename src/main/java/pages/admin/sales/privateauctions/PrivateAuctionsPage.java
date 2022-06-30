package pages.admin.sales.privateauctions;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Keep Selectors of UI elements in {@link PrivateAuctionsPageElements}
 */
@Getter
public class PrivateAuctionsPage extends BasePage {

    private SelenideElement pageTitle = $x(PrivateAuctionsPageElements.ADSPOTS_PAGE_TITLE.getSelector()).as(PrivateAuctionsPageElements.ADSPOTS_PAGE_TITLE.getAlias());
}
