package widgets.sales.deals.sidebar;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import widgets.sales.deals.buyerscard.BuyersCard;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.sales.deals.sidebar.AbstractDealSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AbstractDealSidebarElements}
 */
@Getter
public abstract class AbstractDealSidebar {

    private SelenideElement nameInput = $x(NAME.getSelector()).as(NAME.getAlias());
    private SelenideElement dspDropdown = $x(DSP_DROPDOWN.getSelector()).as( DSP_DROPDOWN.getAlias());
    private SelenideElement activeToggle = $x(ACTIVE_TOGGLE.getSelector()).as(ACTIVE_TOGGLE.getAlias());
    private SelenideElement saveDealButton = $x(SAVE_DEAL_BUTTON.getSelector()).as(SAVE_DEAL_BUTTON.getAlias());
    private SelenideElement floorPriceInput = $x(FLOOR_PRICE_INPUT.getSelector()).as(FLOOR_PRICE_INPUT.getAlias());
    private SelenideElement currencyDropdown = $x(CURRENCY_DROPDOWN.getSelector()).as(CURRENCY_DROPDOWN.getAlias());
    private SelenideElement publisherDropdown = $x(PUBLISHER_DROPDOWN.getSelector()).as(PUBLISHER_DROPDOWN.getAlias());
    @Getter(AccessLevel.NONE)
    private ElementsCollection buyersCardItems =  $$x(BUYERS_CARD_ITEMS.getSelector()).as(BUYERS_CARD_ITEMS.getAlias());
    private SelenideElement addMoreSeatsButton = $x(ADD_MORE_SEATS_BUTTON.getSelector()).as(ADD_MORE_SEATS_BUTTON.getAlias());
    private SelenideElement privateAuctionDropdown = $x(PRIVATE_AUCTION_DROPDOWN.getSelector()).as(PRIVATE_AUCTION_DROPDOWN.getAlias());

    private ElementsCollection dropDownItems = $$x(DROPDOWN_ITEMS.getSelector()).as(DROPDOWN_ITEMS.getAlias());

    @Getter(AccessLevel.NONE)
    private List<BuyersCard> buyersCardList = new ArrayList<>();

    private int countBuyersCardItems() {

        return (int) buyersCardItems.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countBuyersCardItemsOnPage() {
        countBuyersCardItems();
        var position = new AtomicInteger(1);
        if (buyersCardList.size() != 0) {
            buyersCardList.clear();
        }
        buyersCardList.addAll(buyersCardItems.stream()
                .map(buyersCard -> new BuyersCard(position.getAndIncrement()))
                .collect(Collectors.toList()));
    }

    public BuyersCard getBuyersCardByPositionInList(int position) {
        countBuyersCardItemsOnPage();

        return buyersCardList.get(position);
    }

    public BuyersCard getBuyersCardByAdvertiserName(String advertiserName) {
        countBuyersCardItemsOnPage();

        return buyersCardList.stream()
                .filter(pub -> pub.getAdvertiserNameInput().shouldBe(visible).getText().equalsIgnoreCase(advertiserName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The BuyersCard with name '%s' isn't presented in Sidebar", advertiserName)));
    }
}
