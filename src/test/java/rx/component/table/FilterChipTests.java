package rx.component.table;

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
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Components")
public class FilterChipTests extends BaseTest {

    private ProtectionsPage protectionPage;

    private List<String> selectedPublishersNameList;
    private List<String> expectedSearchPublisherNamesList;

    private final static String PUBLISHER_NAME = "Rakuten";
    private Integer totalPublishers;

    public FilterChipTests() {
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

    @Test(description = "Check Search in the filter singlepane")
    public void testSearchColumnsFilterComponent() {

        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();

        testStart()
                .and("Select Column Filter 'PUBLISHER'")
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(filter.getColumnsFilterButton())
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
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(filter.getSinglepane().getBackButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .testEnd();
    }

    @Test(description = "Check Chip Widget Component", dependsOnMethods = "testSearchColumnsFilterComponent")
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
                .validateContainsText(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getHeaderLabel(), format("%s:",ColumnNames.PUBLISHER.getName()))
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate list of selected publishers")
                .validate(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).countFilterOptionsChipItems(), 3)
                .and("Select Column Filter 'Status'")
                .scrollIntoView(protectionPage.getProtectionsTable().getTableData().getSearch())
                .clickOnWebElement(filter.getColumnsFilterButton())
                .validate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.STATUS))
                .and("Select Active")
                .selectRadioButton(filter.getActiveBooleanFilter().getActiveRadioButton())
                .clickOnWebElement(filter.getActiveBooleanFilter().getSubmitButton())
                .validate(not(visible), filter.getFilterOptionsMenu())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.STATUS.getName()).getHeaderLabel())
                .validate(table.countFilterChipsItems(), 2)
                .validate(visible, table.getChipItemByName(ColumnNames.STATUS.getName()).getChipFilterOptionItemByName("Active"))
                .testEnd();

        selectedPublishersNameList.forEach(e -> {
            testStart()
                    .validate(exist, table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getChipFilterOptionItemByName(e))
                    .testEnd();
        });
    }

    @Test(description = "Check Reset Chip Widget Component", dependsOnMethods = "testChipWidgetComponent")
    public void testResetChipWidgetComponent() {
        var table = protectionPage.getProtectionsTable().getTableData();

        testStart()
                .and(format("Reset filter %s", ColumnNames.ACTIVE_INACTIVE.getName()))
                .scrollIntoView(table.getChipItemByName(ColumnNames.ACTIVE_INACTIVE.getName()).getCloseIcon())
                .clickOnWebElement(table.getChipItemByName(ColumnNames.ACTIVE_INACTIVE.getName()).getCloseIcon())
                .then(format("Chip '%s' should be disabled", ColumnNames.ACTIVE_INACTIVE.getName()))
                .validate(table.getFilterChips().size(), 1)
                .validate(visible, table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getHeaderLabel())
                .testEnd();
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
