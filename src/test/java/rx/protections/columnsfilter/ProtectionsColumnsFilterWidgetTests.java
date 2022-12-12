package rx.protections.columnsfilter;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import zutils.StringUtils;

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
public class ProtectionsColumnsFilterWidgetTests extends BaseTest {

    private ProtectionsPage protectionPage;

    private List<String> selectedPublishersNameList;
    private List<String> expectedSearchPublisherNamesList;

    private final static String PUBLISHER_NAME = "Rakuten";
    private Integer totalPublishers;

    public ProtectionsColumnsFilterWidgetTests() {
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
                .scrollIntoView(protectionPage.getLogo())
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
                .scrollIntoView(filter.getSinglepane().getBackButton())
                .clickOnWebElement(filter.getSinglepane().getBackButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .testEnd();
    }

    @Test(description = "Check Chip Widget Component", dependsOnMethods = "testSearchPublisherColumnsFilterComponent")
    public void testPublisherChipWidgetComponent() {
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

        testStart()
                .clickOnWebElement(table.getChipItemByName(ColumnNames.PUBLISHER.getName()).getCloseIcon())
                .testEnd();
    }

    @Test(description = "Check Active/Inactive Chip Widget Component")
    public void testActiveInactiveChipWidgetComponent() {
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var table = protectionPage.getProtectionsTable().getTableData();

        testStart()
                .and("Select Column Filter 'Active/Inactive'")
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.ACTIVE_INACTIVE))
                .then("Title should be displayed")
                .validate(filter.getActiveBooleanFilter().getFilterHeaderLabel(), StringUtils.getFilterHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .then("All options should be unselected")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"false")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"false")
                .and("Select Active")
                .clickOnWebElement(filter.getActiveBooleanFilter().getActiveRadioButton())
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"true")
                .and("Select Inactive")
                .clickOnWebElement(filter.getActiveBooleanFilter().getInactiveRadioButton())
                .then("Only one option should be selected")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"false")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"true")
                .and("Click on Back")
                .clickOnWebElement(filter.getBooleanFilter().getBackButton())
                .then("Columns Menu should appear")
                .validateList(filter.getFilterOptionItems(), List.of(ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.MANAGED_BY_SYSTEM_ADMIN.getName()))
                .and("Select Column Filter 'Active/Inactive'")
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.ACTIVE_INACTIVE))
                .then("All options should be reset and unselected")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"false")
                .validate(filter.getActiveBooleanFilter().getActiveRadioButton().getAttribute("aria-checked"),"false")
                .and("Select Inactive")
                .clickOnWebElement(filter.getActiveBooleanFilter().getInactiveRadioButton())
                .validate(filter.getActiveBooleanFilter().getInactiveRadioButton().getAttribute("aria-checked"),"true")
                .and("Click on Submit")
                .clickOnWebElement(filter.getActiveBooleanFilter().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.ACTIVE_INACTIVE.getName()).getHeaderLabel())
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate value on chip")
                .validate(table.getChipItemByName(ColumnNames.ACTIVE_INACTIVE.getName()).getChipFilterOptionItemByName("Inactive"))
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

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
