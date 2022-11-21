package widgets.common.table.filter.singlepanefilter;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import widgets.common.table.filter.abstractt.BaseFilter;
import widgets.common.table.filter.singlepanefilter.item.SinglepaneItem;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.table.filter.singlepanefilter.SinglepaneElements.*;


/**
 * Keep Selectors of UI elements in {@link SinglepaneElements}
 */

@Getter
public class Singlepane extends BaseFilter {

    private SelenideElement searchInput = $x(SEARCH_INPUT.getSelector()).as(SEARCH_INPUT.getAlias());
    private SelenideElement includedIcon = $x(INCLUDE_ALL_BUTTON.getSelector()).as(INCLUDE_ALL_BUTTON.getAlias());
    private SelenideElement clearAllButton = $x(CLEAR_ALL_BUTTON.getSelector()).as(CLEAR_ALL_BUTTON.getAlias());
    private SelenideElement searchIcon = $x(SEARCH_ICON.getSelector()).as(SEARCH_ICON.getAlias());
    ;
    private SelenideElement includeAllButton = $x(INCLUDE_ALL_BUTTON.getSelector()).as(INCLUDE_ALL_BUTTON.getAlias());
    private SelenideElement itemsQuantityString = $x(INCLUDED_ITEMS_QUANTITY_STRING.getSelector()).as(INCLUDED_ITEMS_QUANTITY_STRING.getAlias());
    ;

    private ElementsCollection filterItems = $$x(FILTER_ITEMS.getSelector()).as(FILTER_ITEMS.getAlias());
    ;
    @Getter
    private List<SinglepaneItem> filterItemsList = new ArrayList<>();

    public SinglepaneItem getFilterItemByPositionInList(int position) {

        return filterItemsList.get(position);
    }

    public int countIncludedItems() {
        int count = 0;

        if (filterItems.size() > 0)
            count = (int) filterItems.shouldBe(CollectionCondition.allMatch("Wait to collection element to be visible",
                            WebElement::isDisplayed))
                    .stream()
                    .map(se -> se.shouldBe(exist).scrollTo().shouldBe(visible))
                    .count();

        return count;
    }

    private void addIncludeFilterItemsToList() {
        countIncludedItems();
        var position = new AtomicInteger(1);
        if (filterItemsList.size() != 0) {
            filterItemsList.clear();
        }
        filterItemsList.addAll(filterItems.stream()
                .map(e -> new SinglepaneItem(position.getAndIncrement()))
                .collect(Collectors.toList()));
    }

    public SinglepaneItem getFilterItemByName(String filterItemName) {
        addIncludeFilterItemsToList();

        return filterItemsList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText()
                        .equalsIgnoreCase(filterItemName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The FilterItem with name '%s' isn't presented in Filter Table of Singlepane",
                                filterItemName)));
    }
}
