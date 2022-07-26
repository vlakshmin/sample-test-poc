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
public class OperatingSystemDetailsSection {

    private ElementsCollection menuOperatingSystemItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), OPERATING_SYSTEM.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), OPERATING_SYSTEM.getName()));

    private List<DetailsMenuItem> menuOperatingSystemList = new ArrayList<>();


    public int countMenuOperatingSystemItems() {

        return (int) menuOperatingSystemItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuOperatingSystemItemsOnPage() {
        countMenuOperatingSystemItems();
        var position = new AtomicInteger(1);
        if (menuOperatingSystemList.size() != 0) {
            menuOperatingSystemList.clear();
        }
        menuOperatingSystemList.addAll(menuOperatingSystemItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), OPERATING_SYSTEM))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuOperatingSystemItemByPositionInList(int position) {
        countMenuOperatingSystemItemsOnPage();

        return menuOperatingSystemList.get(position);
    }

    public DetailsMenuItem getMenuOperatingSystemItemByName(String name) {
        countMenuOperatingSystemItemsOnPage();

        return menuOperatingSystemList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu AdFormat item with name '%s' isn't presented in the list", name)));
    }
}