package pages.inventory.adspots;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import pages.BasePage;
import widgets.common.table.Table;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static pages.inventory.adspots.AdSpotsPageElements.*;
import static pages.inventory.media.MediaPageElements.ACTIVATE_MEDIA_BUTTON;
import static pages.inventory.media.MediaPageElements.DEACTIVATE_MEDIA_BUTTON;


/**
 * Keep Selectors of UI elements in {@link AdSpotsPageElements}
 */
@Getter
public class AdSpotsPage extends BasePage {

    @Getter(AccessLevel.NONE)
    private SelenideElement pageTitle = $x(ADSPOTS_PAGE_TITLE.getSelector()).as(ADSPOTS_PAGE_TITLE.getAlias());
    private ElementsCollection adSpotsItems = $$x(ADSPOT_ITEMS.getSelector()).as(ADSPOT_ITEMS.getAlias());
    private SelenideElement createAdSpotButton = $x(CREATE_ADSPOT_BUTTON.getSelector()).as(CREATE_ADSPOT_BUTTON.getAlias());
    private SelenideElement activateAdSpotButton = $x(ACTIVATE_AD_SPOT_BUTTON.getSelector()).as(ACTIVATE_AD_SPOT_BUTTON.getAlias());
    private SelenideElement deactivateAdSpotButton = $x(DEACTIVATE_AD_SPOT_BUTTON.getSelector()).as(DEACTIVATE_AD_SPOT_BUTTON.getAlias());
    private Table adSpotsTable = new Table();
}
