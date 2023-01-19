package pages.sales.privateauctions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import pages.BasePage;
import pages.admin.publisher.PublishersPageElements;
import widgets.admin.publisher.tableitem.PublisherTableItem;
import widgets.common.table.Table;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Keep Selectors of UI elements in {@link PrivateAuctionsPageElements}
 */
@Getter
public class PrivateAuctionsPage extends BasePage {

    @Getter(AccessLevel.NONE)
    private ElementsCollection privateauctionsItems = $$x(PrivateAuctionsPageElements.PRIVATE_AUCTIONS_ITEMS.getSelector()).as(PrivateAuctionsPageElements.PRIVATE_AUCTIONS_ITEMS.getAlias());
    private SelenideElement pageTitle = $x(PrivateAuctionsPageElements.PRIVATE_AUCTIONS_PAGE_TITLE.getSelector()).as(PrivateAuctionsPageElements.PRIVATE_AUCTIONS_PAGE_TITLE.getAlias());
    private SelenideElement createPublisherButton = $x(PrivateAuctionsPageElements.CREATE_PRIVATE_AUCTIONS_BUTTON.getSelector()).as(PrivateAuctionsPageElements.CREATE_PRIVATE_AUCTIONS_BUTTON.getAlias());

    @Getter(AccessLevel.NONE)
    private List<PublisherTableItem> publisherList = new ArrayList<>();
    private Table table = new Table();
}
