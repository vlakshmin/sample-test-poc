package rx.inventory.adspot;

import api.dto.rx.inventory.adspot.AdSpot;
import api.preconditionbuilders.AdSpotPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Epic;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotSearchTableTests extends BaseTest {

    private AdSpot adSpot;
    private AdSpotsPage adSpotsPage;

    private List<String> searchByA;
    private List<Integer> adSpotIds;
    private List<String> searchBoth;
    private List<String> searchActive;
    private List<Integer> publishersIds;
    private List<String> searchInactive;
    private List<String> publishersByAsc;
    private List<String> adSpotNamesByAsc;

    private static final String SORT_PARAM = "sort";
    private static final String PUB_NAME = "autoSSSDD2";
    private static final String AD_SPOT_NAME = "autoSSDD1";
    private static final String FILTER_SEARCH = "autoRpTT7";
    private static final String ENABLED_ASC = "enabled-asc";
    private static final String ENABLED_DESC = "enabled-desc";

    public AdSpotSearchTableTests() {
        adSpotsPage = new AdSpotsPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        adSpotIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

        adSpot = createCustomAdSpot("adspot_auto", "pub_auto", true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(AD_SPOT_NAME, PUB_NAME, true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(FILTER_SEARCH + "2", FILTER_SEARCH + "2", true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(FILTER_SEARCH + "3", FILTER_SEARCH + "3", true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(FILTER_SEARCH + "4", FILTER_SEARCH + "5", false);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(FILTER_SEARCH + "5", FILTER_SEARCH + "5", false);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        //expected results for Media Name column
        adSpotNamesByAsc = getAllItemsByParams(AD_SPOT_NAME).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        publishersByAsc = getAllItemsByParams(PUB_NAME).stream()
                .map(AdSpot::getPublisherName)
                .collect(Collectors.toList());

        searchByA = getAllItemsByParams("A").stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchActive = getAllItemsByParams(FILTER_SEARCH).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchInactive = getAllItemsByParams(String.format("%s?%s=%s", FILTER_SEARCH, SORT_PARAM, ENABLED_ASC)).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchBoth = getAllItemsByParams(FILTER_SEARCH).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

    }

    @BeforeMethod
    private void login() {
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Search by 'Ad Spot Name'")
    public void adSpotsSearchByAdSpotName() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), AD_SPOT_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .and("Sort column 'Ad spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .then(String.format("Validate data in column 'Ad spot Name' should contain '%s'", AD_SPOT_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.NAME), adSpotNamesByAsc)
                .and("End Test")
                .testEnd();
    }

    @Epic("GS-2943")
    @Test(testName = "Search by 'Publisher'", enabled = false)
    public void adSpotsSearchByPublisher() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), PUB_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .and("Sort column 'Ad Spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()))
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", AD_SPOT_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Publisher' should contain '%s'", PUB_NAME))
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER), publishersByAsc)
                .and("End Test")
                .testEnd();
    }

    @Epic("GS-2943")
    @Test(testName = "Search by 'A'", enabled = false)
    public void adSpotsSearchWithPagination() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        var tablePagination = adSpotsPage.getAdSpotsTable().getTablePagination();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .and("Set value 'A' in search field")
                .setValueWithClean(tableData.getSearch(), "A")
                .clickEnterButton(tableData.getSearch())
                .and("Select 10 row per page")
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "10")
                .waitLoading(visible, adSpotsPage.getTableProgressBar())
                .waitLoading(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-10 of %s'", searchByA.size()))
                .and("Sort column 'Ad Spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()))
                .then(String.format("Validate data in column 'Ad Spot Name' should contain 'A'"))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchByA.subList(0, 10))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, adSpotsPage.getTableProgressBar())
                .waitLoading(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '11-20 of %s'", searchByA.size()))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("11-20 of %s", searchByA.size()))
                .then("Validate data in column 'Media Name' should contain 'A'")
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME),
                        searchByA.subList(10, 20))
                .and("End Test")
                .testEnd();
    }

    @Epic("GS-2943")
    @Test(testName = "Search with filter by status", enabled = false)
    public void adSpotsSearchWithFilterByStatus() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        var tableOptions = adSpotsPage.getAdSpotsTable().getShowHideColumns();
        var filterOptions = adSpotsPage.getAdSpotsTable().getColumnFiltersBlock();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), FILTER_SEARCH)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .and("Sort column 'Ad Spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()))
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", FILTER_SEARCH))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchBoth)
                .and("Set filter 'Active'")
                .clickOnWebElement(filterOptions.getColumnFiltersButton())
                .clickOnWebElement(filterOptions.getFilterOptionByName(ColumnNames.ACTIVE_INACTIVE))
                .selectRadioButton(filterOptions.getActiveBooleanFilter().getActiveRadioButton())
                .clickOnWebElement(filterOptions.getActiveBooleanFilter().getSubmitButton())
                .scrollIntoView(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchActive)
                .and("Set filter 'Inactive'")
                .clickOnWebElement(filterOptions.getColumnFiltersButton())
                .clickOnWebElement(filterOptions.getFilterOptionByName(ColumnNames.ACTIVE_INACTIVE))
                .selectRadioButton(filterOptions.getActiveBooleanFilter().getActiveRadioButton())
                .clickOnWebElement(filterOptions.getActiveBooleanFilter().getSubmitButton())
                .scrollIntoView(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchInactive)
                .and("End Test")
                .testEnd();
    }

    @AfterTest(alwaysRun = true)
    private void deleteEntities() {

        for (Integer adSpotId : adSpotIds) {
            AdSpotPrecondition.adSpot()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteAdSpot(adSpotId);
        }

        for (Integer publisherId : publishersIds) {
            PublisherPrecondition.publisher().
                    setCredentials(USER_FOR_DELETION).
                    deletePublisher(publisherId);
        }
    }

    private AdSpot createCustomAdSpot(String name, String publisherName, Boolean isEnabled) {

        return AdSpotPrecondition.adSpot()
                .createNewAdSpot(name, publisherName, isEnabled)
                .build()
                .getAdSpotResponse();
    }

    private List<AdSpot> getAllItemsByParams(String strParams) {

        return AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(Map.of("search", strParams))
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();
    }
}
