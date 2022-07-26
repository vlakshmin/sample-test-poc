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
public class AdFormatDetailsSection {

    private ElementsCollection menuAdFormatItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), AD_FORMAT.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), AD_FORMAT.getName()));

    private List<DetailsMenuItem> menuAdFormatList = new ArrayList<>();


    public int countMenuAdFormatItems() {

        return (int) menuAdFormatItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuAdFormatItemsOnPage() {
        countMenuAdFormatItems();
        var position = new AtomicInteger(1);
        if (menuAdFormatList.size() != 0) {
            menuAdFormatList.clear();
        }
        menuAdFormatList.addAll(menuAdFormatItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), AD_FORMAT))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuAdFormatItemByPositionInList(int position) {
        countMenuAdFormatItemsOnPage();

        return menuAdFormatList.get(position);
    }

    public DetailsMenuItem getMenuAdFormatItemByName(String name) {
        countMenuAdFormatItemsOnPage();

        return menuAdFormatList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu AdFormat item with name '%s' isn't presented in the list", name)));
    }
}
