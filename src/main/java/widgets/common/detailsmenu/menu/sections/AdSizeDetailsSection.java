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
import static widgets.common.detailsmenu.DetailsSectionName.AD_SIZE;
import static widgets.common.detailsmenu.DetailsSectionName.GEO;
import static widgets.common.detailsmenu.menu.SectionDetailsElements.DETAILS_MENU_ITEMS;

/**
 * Keep Selectors of UI elements in {@link SectionDetailsElements}
 */
public class AdSizeDetailsSection {

    private ElementsCollection menuAdSizeItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), AD_SIZE.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), AD_SIZE.getName()));

    private List<DetailsMenuItem> menuAdSizeList = new ArrayList<>();


    public int countMenuAdSizeItems() {

        return (int) menuAdSizeItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuAdSizeItemsOnPage() {
        countMenuAdSizeItems();
        var position = new AtomicInteger(1);
        if (menuAdSizeList.size() != 0) {
            menuAdSizeList.clear();
        }
        menuAdSizeList.addAll(menuAdSizeItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), AD_SIZE))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuAdSizeItemByPositionInList(int position) {
        countMenuAdSizeItemsOnPage();

        return menuAdSizeList.get(position);
    }

    public DetailsMenuItem getMenuAdSizeItemByName(String name) {
        countMenuAdSizeItemsOnPage();

        return menuAdSizeList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu AdSize item with name '%s' isn't presented in the list", name)));
    }
}
