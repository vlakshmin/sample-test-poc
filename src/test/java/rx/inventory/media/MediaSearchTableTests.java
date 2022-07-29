package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.publisher.PublisherRequest;
import api.dto.rx.common.Currency;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.inventory.media.MediaRequest;
import api.preconditionbuilders.MediaPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.MediaPage;
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
import static zutils.FakerUtils.*;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaSearchTableTests extends BaseTest {

    private Publisher publisher;
    private static final String MEDIA_NAME = "SS1";
    private static final String PUB_NAME = "SSS2";
    private static final String FILTER_SEARCH = "RpT5";
    private List<String> mediaNamesByAsc;
    private List<String> publishersByAsc;
    private List<String> searchByA;
    private List<String> searchActive;
    private List<String> searchInactive;
    private List<String> searchBoth;

    private MediaPage mediaPage;

    private List<Integer> mediaIds;
    private List<Integer> publishersIds;

    public MediaSearchTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        mediaIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

        Media media = createCustomMedia("media",true,"pub");
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media =createCustomMedia(MEDIA_NAME, true, PUB_NAME);
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = createCustomMedia(FILTER_SEARCH + "2",true,FILTER_SEARCH + "2");
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = createCustomMedia(FILTER_SEARCH + "3",true,FILTER_SEARCH + "3");
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = createCustomMedia(FILTER_SEARCH + "4",false,FILTER_SEARCH + "5");
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = createCustomMedia(FILTER_SEARCH + "5", false, FILTER_SEARCH + "5");
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        //expected results for Media Name column
        mediaNamesByAsc = getAllItemsByParams(MEDIA_NAME, null).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        publishersByAsc = getAllItemsByParams(PUB_NAME, null).stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());

        searchByA = getAllItemsByParams("A", null).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        searchActive = getAllItemsByParams(FILTER_SEARCH, true).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        searchInactive = getAllItemsByParams(FILTER_SEARCH, false).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        searchBoth = getAllItemsByParams(FILTER_SEARCH, null).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

    }

    @BeforeMethod
    private void login() {
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .testEnd();
    }

    @AfterMethod
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Search by 'Media Name'")
    public void mediaSearchByMediaName() {
        var tableData = mediaPage.getMediaTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), MEDIA_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then(String.format("Validate data in column 'Media Name' should contain '%s'", MEDIA_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), mediaNamesByAsc)
                .and("End Test")
                .testEnd();
    }

    @Test(testName = "Search by 'Publisher'")
    public void mediaSearchByPublisher() {
        var tableData = mediaPage.getMediaTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), PUB_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then(String.format("Validate data in column 'Media Name' should contain '%s'", MEDIA_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Publisher' should contain '%s'", PUB_NAME))
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER), publishersByAsc)
                .and("End Test")
                .testEnd();
    }

    @Test(testName = "Search by 'A'")
    public void mediaSearchWithPaginatinaton() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Set value 'A' in search field")
                .setValueWithClean(tableData.getSearch(), "A")
                .clickEnterButton(tableData.getSearch())
                .and("Select 10 row per page")
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "10")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-10 of %s'", searchByA.size()))
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then(String.format("Validate data in column 'Media Name' should contain 'A'"))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), searchByA.subList(0, 10))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '11-20 of %s'", searchByA.size()))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("11-20 of %s", searchByA.size()))
                .then("Validate data in column 'Media Name' should contain 'A'")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        searchByA.subList(10, 20))
                .and("End Test")
                .testEnd();
    }

    @Test(testName = "Search with filter by status")
    public void mediaSearchWithFilterByStatus() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tableOptions = mediaPage.getMediaTable().getTableOptions();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), FILTER_SEARCH)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then(String.format("Validate data in column 'Media Name' should contain '%s'", FILTER_SEARCH))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), searchBoth)
                .and("Set filter 'Active'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectRadioButton(tableOptions.getStatusItemRadio(Statuses.ACTIVE))
                .scrollIntoView(tableData.getSearch())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Media Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), searchActive)
                .and("Set filter 'Inactive'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectRadioButton(tableOptions.getStatusItemRadio(Statuses.INACTIVE))
                .scrollIntoView(tableData.getSearch())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate data in column 'Media Name' should contain '%s'", FILTER_SEARCH))
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), searchInactive)
                .and("End Test")
                .testEnd();
    }

    @AfterTest
    private void deleteEntities() {
        for (Integer mediaId : mediaIds) {
            MediaPrecondition.media().
                    setCredentials(USER_FOR_DELETION).
                    deleteMedia(mediaId);
        }

        for (Integer publisherId : publishersIds) {
            PublisherPrecondition.publisher().
                    setCredentials(USER_FOR_DELETION).
                    deletePublisher(publisherId);
        }
    }

    private Media createCustomMedia(String name, Boolean isEnabled, String publisherName) {
        Publisher publisher = createCustomPublisher(publisherName);

        return MediaPrecondition.media()
                .createNewMedia(name, publisher.getId(), isEnabled)
                .build()
                .getMediaResponse();
    }

    private Publisher createCustomPublisher(String publisherName) {

        return PublisherPrecondition.publisher()
                .createNewPublisher(publisherName)
                .build()
                .getPublisherResponse();
    }

    private List<Media> getAllItemsByParams(String strParams, Boolean isEnabled) {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        queryParams.put("sort", "name-asc");
        if (isEnabled != null) queryParams.put("enabled", isEnabled);
        List<Media> mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList, Media.class);
    }

}
