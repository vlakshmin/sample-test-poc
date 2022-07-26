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
import static widgets.common.detailsmenu.DetailsSectionName.*;
import static widgets.common.detailsmenu.menu.SectionDetailsElements.DETAILS_MENU_ITEMS;

/**
 * Keep Selectors of UI elements in {@link SectionDetailsElements}
 */
public class DemandSourcesDetailsSection {

    private ElementsCollection menuDemandSourcesItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), DEMAND_SOURCES.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), DEMAND_SOURCES.getName()));

    private List<DetailsMenuItem> menuDemandSourcesList = new ArrayList<>();


    public int countMenuDemandSourcesItems() {

        return (int) menuDemandSourcesItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuDemandSourcesItemsOnPage() {
        countMenuDemandSourcesItems();
        var position = new AtomicInteger(1);
        if (menuDemandSourcesList.size() != 0) {
            menuDemandSourcesList.clear();
        }
        menuDemandSourcesList.addAll(menuDemandSourcesItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), DEMAND_SOURCES))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuDemandSourcesItemByPositionInList(int position) {
        countMenuDemandSourcesItemsOnPage();

        return menuDemandSourcesList.get(position);
    }

    public DetailsMenuItem getMenuDemandSourcesItemByName(String name) {
        countMenuDemandSourcesItemsOnPage();

        return menuDemandSourcesList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu AdFormat item with name '%s' isn't presented in the list", name)));
    }
}
