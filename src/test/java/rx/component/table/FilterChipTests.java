package rx.component.table;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.table.filter.singlepanefilter.item.SinglepaneItem;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Components")
public class FilterChipTests extends BaseTest {

    private ProtectionsPage protectionPage;

    private List<String> selectedPublishers;

    public FilterChipTests() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Check Filter Chip widget")
    public void testFilterChipWidgetComponent() {

        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var table = protectionPage.getProtectionsTable().getTableData();

        testStart()
                .and("Select Column Filter 'PUBLISHER'")
                .clickOnWebElement(filter.getColumnFiltersButton())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .and("Select Publishers")
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(1).getName())
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(2).getName())
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(3).getName())
                .testEnd();

        selectedPublishers = filter
                .getSinglepane()
                .getIncludedItems()
                .stream()
                .map(SinglepaneItem::getName)
                .map(e -> e.getText())
                .collect(Collectors.toList());

        testStart()
                .and("Click on Submit")
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getHeaderLabel())
                .validateListSize(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getChipItems(), selectedPublishers.size())
                .testEnd();
        validateChipContent(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getChipItems());

        testStart()

                .and(format("Reset filter %s", ColumnNames.PUBLISHER.getName()))

                .clickOnWebElement(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getCloseIcon())
                .then(format("Chip '%s' should be disabled", ColumnNames.PUBLISHER.getName()))
                .validate(table.getFilterChips().size(),0)
                .testEnd();
    }

    @Step("Validate Filter Chip Content")
    private void validateChipContent(ElementsCollection list) {
        AtomicInteger index = new AtomicInteger(0);

        list.stream().forEach(item -> {
            testStart()
                    .then(format("Value '%s' should be presented in chip", selectedPublishers.get(index.get())))
                    .validate(item.getText(), selectedPublishers.get(index.getAndIncrement()))
                    .testEnd();
        });
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
