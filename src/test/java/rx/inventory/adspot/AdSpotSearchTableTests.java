package rx.inventory.adspot;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.adspot.AdSpot;
import api.preconditionbuilders.AdSpotPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;
import zutils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotSearchTableTests extends BaseTest {

    private Publisher publisher;
    private static final String AD_SPOT_NAME = "SSDD1";
    private static final String PUB_NAME = "SSSDD2";
    private static final String FILTER_SEARCH = "RpTT7";
    private List<String> adSpotNamesByAsc;
    private List<String> publishersByAsc;
    private List<String> searchByA;
    private List<String> searchActive;
    private List<String> searchInactive;
    private List<String> searchBoth;

    private AdSpotsPage adSpotsPage;

    private List<Integer> adSpotIds;
    private List<Integer> publishersIds;

    public AdSpotSearchTableTests() {
        adSpotsPage = new AdSpotsPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        adSpotIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

        AdSpot adSpot = createCustomAdSpot("adspot_auto", "pub_auto", true);
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
        adSpotNamesByAsc = getAllItemsByParams(AD_SPOT_NAME, null).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        publishersByAsc = getAllItemsByParams(PUB_NAME, null).stream()
                .map(AdSpot::getPublisherName)
                .collect(Collectors.toList());

        searchByA = getAllItemsByParams("A", null).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchActive = getAllItemsByParams(FILTER_SEARCH, true).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchInactive = getAllItemsByParams(FILTER_SEARCH, false).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());

        searchBoth = getAllItemsByParams(FILTER_SEARCH, null).stream()
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

    @AfterMethod
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Search by 'Ad Spot Name'")
    public void adspotsSearchByAdSpotName() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), AD_SPOT_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .and("Sort column 'Ad spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()))
                .then(String.format("Validate data in column 'Ad spot Name' should contain '%s'", AD_SPOT_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), adSpotNamesByAsc)
                .and("End Test")
                .testEnd();
    }

    @Test(testName = "Search by 'Publisher'")
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

    @Test(testName = "Search by 'A'")
    public void adSpotsSearchWithPaginatinaton() {
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

    @Test(testName = "Search with filter by status")
    public void adSpotsSearchWithFilterByStatus() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        var tableOptions = adSpotsPage.getAdSpotsTable().getTableOptions();
        var tablePagination = adSpotsPage.getAdSpotsTable().getTablePagination();

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
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectRadioButton(tableOptions.getStatusItemRadio(Statuses.ACTIVE))
                .scrollIntoView(tableData.getSearch())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchActive)
                .and("Set filter 'Inactive'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectRadioButton(tableOptions.getStatusItemRadio(Statuses.INACTIVE))
                .scrollIntoView(tableData.getSearch())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Ad Spot Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME), searchInactive)
                .and("End Test")
                .testEnd();
    }

    @AfterTest
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

    private List<AdSpot> getAllItemsByParams(String strParams, Boolean isEnabled) {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        queryParams.put("sort", "name-asc");

        if (isEnabled != null) queryParams.put("enabled", isEnabled);
        List<AdSpot> adSpotList = AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(queryParams)
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(adSpotList, AdSpot.class);
    }

}
