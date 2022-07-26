package widgets.common.detailsmenu.menu.sections;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import widgets.common.detailsmenu.item.DetailsMenuItem;
import widgets.common.detailsmenu.menu.SectionDetailsElements;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static widgets.common.detailsmenu.DetailsSectionName.INVENTORY;
import static widgets.common.detailsmenu.menu.SectionDetailsElements.*;

/**
 * Keep Selectors of UI elements in {@link SectionDetailsElements}
 */
public class InventoryDetailsSection {

    private ElementsCollection menuInventoryItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), INVENTORY.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), INVENTORY.getName()));

    private List<DetailsMenuItem> menuInventoryList = new ArrayList<>();


    public int countMenuInventoryItems() {

        return (int) menuInventoryItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuInventoryItemsOnPage() {
        countMenuInventoryItems();
        var position = new AtomicInteger(1);
        if (menuInventoryList.size() != 0) {
            menuInventoryList.clear();
        }
        menuInventoryList.addAll(menuInventoryItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), INVENTORY))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuInventoryItemByPositionInList(int position) {
        countMenuInventoryItemsOnPage();

        return menuInventoryList.get(position);
    }

    public DetailsMenuItem getMenuInventoryItemByName(String name) {
        countMenuInventoryItemsOnPage();

        return menuInventoryList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu Inventory item with name '%s' isn't presented in the list", name)));
    }
}
