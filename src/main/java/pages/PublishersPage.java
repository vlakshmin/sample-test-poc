package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import widgets.EditPublisherSidebar;
import widgets.PublisherTableItem;

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

@Getter
public class PublishersPage extends BasePage{

    //Todo move selectors to Enumerations
    private SelenideElement pageTitle = $x("//h1");
    @Getter(AccessLevel.NONE)
    private ElementsCollection publisherItems = $$x("//tbody/tr");
    private SelenideElement createPublisherButton = $x("//button//span[text()='Create Publisher']");
    @Getter(AccessLevel.NONE)
    private List<PublisherTableItem> publisherList = new ArrayList<>();
    private EditPublisherSidebar editPublisherSidebar = new EditPublisherSidebar();


    public int countPublisherItems() {

        return (int) publisherItems.shouldBe(
                CollectionCondition.allMatch("Wait to collection element to be visible", WebElement::isDisplayed),
                        CollectionCondition.size(20))
                .stream()
                .map(se -> se.shouldBe(exist,visible))
                .count();
    }

    private void countPublisherItemsOnPage() {
        countPublisherItems();
        var position = new AtomicInteger(1);
        if (publisherList.size() != 0) {
            publisherList.clear();
        }
        publisherList.addAll(publisherItems.stream()
                .map(publisher -> new PublisherTableItem(position.getAndIncrement()))
                .collect(Collectors.toList()));
    }

    public PublisherTableItem getPublisherItemByPositionInList(int position) {
        countPublisherItemsOnPage();

        return publisherList.get(position);
    }

    public PublisherTableItem getPublisherItemByName(String name) {
        countPublisherItemsOnPage();

        return publisherList.stream()
                .filter(pub -> pub.getPublisherName().shouldBe(visible).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        format("The Publisher with name '%s' isn't presented in the list of Publisher", name)));
    }

    public SelenideElement getPageTitle() {
        return this.pageTitle;
    }

    public List<PublisherTableItem> getPublisherList() {
        return this.publisherList;
    }

    public SelenideElement getCreatePublisherButton() {
        return this.createPublisherButton;
    }

    public ElementsCollection getPublisherItems() {
        return this.publisherItems;
    }
}
