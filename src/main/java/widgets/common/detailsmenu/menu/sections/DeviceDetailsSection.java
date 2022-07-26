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
import static widgets.common.detailsmenu.DetailsSectionName.DEVICE;
import static widgets.common.detailsmenu.DetailsSectionName.INVENTORY;
import static widgets.common.detailsmenu.menu.SectionDetailsElements.DETAILS_MENU_ITEMS;

/**
 * Keep Selectors of UI elements in {@link SectionDetailsElements}
 */
public class DeviceDetailsSection {

    private ElementsCollection menuDeviceItems = $$x(format(DETAILS_MENU_ITEMS.getSelector(), DEVICE.getName()))
                    .as(format(DETAILS_MENU_ITEMS.getAlias(), INVENTORY.getName()));

    private List<DetailsMenuItem> menuDeviceList = new ArrayList<>();


    public int countMenuDeviceItems() {

        return (int) menuDeviceItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countMenuDeviceItemsOnPage() {
        countMenuDeviceItems();
        var position = new AtomicInteger(1);
        if (menuDeviceList.size() != 0) {
            menuDeviceList.clear();
        }
        menuDeviceList.addAll(menuDeviceItems.stream()
                .map(publisher -> new DetailsMenuItem(position.getAndIncrement(), DEVICE))
                .collect(Collectors.toList()));
    }

    public DetailsMenuItem getMenuDeviceItemByPositionInList(int position) {
        countMenuDeviceItemsOnPage();

        return menuDeviceList.get(position);
    }

    public DetailsMenuItem getMenuDeviceItemByName(String name) {
        countMenuDeviceItemsOnPage();

        return menuDeviceList.stream()
                .filter(pub -> pub.getName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Menu Device item with name '%s' isn't presented in the list", name)));
    }
}
