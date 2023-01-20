package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Epic;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.*;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

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
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaSearchTableTests extends BaseTest {

    private MediaPage mediaPage;
    private List<String> mediaNamesByAsc;
    private List<String> publishersByAsc;

    private List<Integer> mediaIds;
    private List<Integer> publishersIds;

    private static final String PUB_NAME = captionWithSuffix("SSS23");
    private static final String MEDIA_NAME = captionWithSuffix("SS11");

    public MediaSearchTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        mediaIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

        Media media = createCustomMedia(captionWithSuffix("media"), true, captionWithSuffix("autopub"));
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = createCustomMedia(MEDIA_NAME, true, PUB_NAME);
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        //expected results for Media Name column
        mediaNamesByAsc = getAllItemsByParams(MEDIA_NAME, null).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        publishersByAsc = getAllItemsByParams(PUB_NAME, null).stream()
                .map(Media::getPublisherName)
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

    //TODO: need to add tests search+filter applying GS-3465

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
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", strParams);
        queryParams.put("sort", "name-asc");
        if (isEnabled != null) queryParams.put("active", isEnabled);

        return MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();
    }

}
