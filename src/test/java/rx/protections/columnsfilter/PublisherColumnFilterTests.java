package rx.protections.columnsfilter;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Components")
public class PublisherColumnFilterTests extends BaseTest {

    private ProtectionsPage protectionPage;

    private List<String> selectedPublishersNameList;
    private List<String> expectedSearchPublisherNamesList;

    private final static String PUBLISHER_NAME = "Rakuten";
    private Integer totalPublishers;

    public PublisherColumnFilterTests() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login() {

        expectedSearchPublisherNamesList = getFilterPublishersListFromBE(PUBLISHER_NAME);
        totalPublishers = getTotalPublishersFromBE();

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Check Search Publisher")
    public void testSearchPublisherColumnsFilterComponent() {

        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();

        testStart()
                .and("Select Column Filter 'PUBLISHER'")
                .scrollIntoView(filter.getColumnFiltersButton())
                .clickOnWebElement(filter.getColumnFiltersButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .and(format("Search by Name '%s'", PUBLISHER_NAME))
                .setValueWithClean(filter.getSinglepane().getSearchInput(), PUBLISHER_NAME)
                .clickEnterButton(filter.getSinglepane().getSearchInput())
                .validate(filter.getSinglepane().countIncludedItems(), expectedSearchPublisherNamesList.size())
                .testEnd();

        expectedSearchPublisherNamesList.forEach(e -> {
            testStart()
                    .validate(exist, filter.getSinglepane().getFilterItemByName(e).getName())
                    .testEnd();
        });

        testStart()
                .and("Clear Search")
                .clearField(filter.getSinglepane().getSearchInput())
                .then("Check total publishers count, search result should be reset")
                .validate(not(visible), protectionPage.getTableProgressBar())
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(), format("(%s)",totalPublishers))
                .scrollIntoView(filter.getSinglepane().getBackButton())
                .clickOnWebElement(filter.getSinglepane().getBackButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .testEnd();
    }

    @Test(description = "Check Chip Widget Component", dependsOnMethods = "testSearchPublisherColumnsFilterComponent")
    public void testChipWidgetComponent() {
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var table = protectionPage.getProtectionsTable().getTableData();

        testStart()
                .and("Select Column Filter 'PUBLISHER'")
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .and("Select Publishers")
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(1).getName())
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(2).getName())
                .clickOnWebElement(filter.getSinglepane().getFilterItemByPositionInList(3).getName())
                .testEnd();

        selectedPublishersNameList = List.of(filter.getSinglepane().getFilterItemByPositionInList(1).getName().text(),
                filter.getSinglepane().getFilterItemByPositionInList(2).getName().text(),
                filter.getSinglepane().getFilterItemByPositionInList(3).getName().text());

        testStart()
                .and("Click on Submit")
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getHeaderLabel())
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate list of selected publishers")
                .validate(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).countFilterOptionsChipItems(), 3)
                .testEnd();

        selectedPublishersNameList.forEach(e -> {
            testStart()
                    .validate(exist, table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getChipFilterOptionItemByName(e))
                    .testEnd();
        });
    }

    private List<String> getFilterPublishersListFromBE(String name) {

        return publisher()
                .getPublisherWithFilter(Map.of("name", name))
                .build()
                .getPublisherGetAllResponse()
                .getItems().stream().map(pub -> pub.getName()).collect(Collectors.toList());
    }

    private Integer getTotalPublishersFromBE() {

        return publisher()
                .getPublishersList()
                .build()
                .getPublisherGetAllResponse()
                .getTotal();
    }

   // @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
