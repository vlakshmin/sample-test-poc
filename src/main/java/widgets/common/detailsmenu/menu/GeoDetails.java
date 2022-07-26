package widgets.common.detailsmenu.menu;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import widgets.common.detailsmenu.item.DetailsMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static widgets.common.detailsmenu.DetailsSectionName.GEO;
import static widgets.common.detailsmenu.DetailsSectionName.INVENTORY;
import static widgets.common.detailsmenu.menu.SectionDetailsElements.DETAILS_MENU_ITEMS;

/**
 * Keep Selectors of UI elements in {@link SectionDetailsElements}
 */
public class GeoDetails {

    private ElementsCollection menuGeoItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), GEO.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), GEO.getName()));

    private List<DetailsMenuItem> menuGeoList = new ArrayList<>();


    public int countMenuGeoItems() {

        return (int) menuGeoItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuGeoItemsOnPage() {
        countMenuGeoItems();
        var position = new AtomicInteger(1);
        if (menuGeoList.size() != 0) {
            menuGeoList.clear();
        }
        menuGeoList.addAll(menuGeoItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), GEO))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuGeoItemByPositionInList(int position) {
        countMenuGeoItemsOnPage();

        return menuGeoList.get(position);
    }

    public DetailsMenuItem getMenuGeoItemByName(String name) {
        countMenuGeoItemsOnPage();

        return menuGeoList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu Geo item with name '%s' isn't presented in the list", name)));
    }
}
