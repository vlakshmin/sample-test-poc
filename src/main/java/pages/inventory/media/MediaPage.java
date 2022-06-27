package pages.inventory.media;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import pages.BasePage;
import widgets.common.table.Table;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static pages.inventory.media.MediaPageElements.*;

/**
 * Keep Selectors of UI elements in {@link MediaPageElements}
 */
@Getter
public class MediaPage extends BasePage {

    @Getter(AccessLevel.NONE)
    private SelenideElement pageTitle = $x(MEDIA_PAGE_TITLE.getSelector()).as(MEDIA_PAGE_TITLE.getAlias());
    private ElementsCollection mediaItems = $$x(MEDIA_ITEMS.getSelector()).as(MEDIA_ITEMS.getAlias());
    private SelenideElement createMediaButton = $x(CREATE_MEDIA_BUTTON.getSelector()).as(CREATE_MEDIA_BUTTON.getAlias());
    private SelenideElement editMediaButton = $x(EDIT_MEDIA_BUTTON.getSelector()).as(EDIT_MEDIA_BUTTON.getAlias());
    private SelenideElement activateMediaButton = $x(ACTIVATE_MEDIA_BUTTON.getSelector()).as(ACTIVATE_MEDIA_BUTTON.getAlias());
    private SelenideElement deactivateMediaButton = $x(DEACTIVATE_MEDIA_BUTTON.getSelector()).as(DEACTIVATE_MEDIA_BUTTON.getAlias());
    private Table mediaTable = new Table();
}
