package pages.inventory.media;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.sales.deals.DealsPageElements.MEDIA_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link MediaPageElements}
 */
@Getter
public class MediaPage extends BasePage {

    private SelenideElement pageTitle = $x(MEDIA_PAGE_TITLE.getSelector()).as(MEDIA_PAGE_TITLE.getAlias());
}
