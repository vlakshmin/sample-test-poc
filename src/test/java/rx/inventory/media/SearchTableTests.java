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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import zutils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.*;

@Slf4j
@Listeners({ScreenShooter.class})
public class SearchTableTests extends BaseTest {

    private Publisher publisher;
    private String mediaName;
    private String pubName;
    private List<String> mediaNamesByAsc;
    private List<String> publishersByAsc;
    private List<String> searchByA;

    private MediaPage mediaPage;

    private List<Integer> mediaIds;
    private List<Integer> publishersIds;

    public SearchTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        mediaName = "mediaSS1";
        pubName = "pubSSS2";
        mediaIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

       Media media = MediaPrecondition.media()
                .createNewMedia(createCustomMedia("media","pub"))
                .build()
                .getMediaResponse();
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        media = MediaPrecondition.media()
                .createNewMedia(createCustomMedia(mediaName,pubName))
                .build()
                .getMediaResponse();
        mediaIds.add(media.getId());
        publishersIds.add(media.getPublisherId());

        //expected results for Media Name column
        mediaNamesByAsc  = getAllItemsByParams(mediaName).stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        publishersByAsc = getAllItemsByParams(pubName).stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());

        searchByA  = getAllItemsByParams(mediaName).stream()
                .map(Media::getName)
                .collect(Collectors.toList());
    }

    @Test(testName = "Search by 'Media Name'")
    public void mediaSearchByMediaNameDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();

        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), mediaName)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then(String.format("Ensure that column 'Media Name' contains values with string '%s'",mediaName))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort","ascending")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),mediaNamesByAsc)
                .and(String.format("Ensure that column 'Media Name' contains values with string '%s'",pubName))
                .setValueWithClean(tableData.getSearch(), pubName)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Ensure that column 'Publisher' contains values with string '%s'",pubName))
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),publishersByAsc)
                .and()
                .logOut()
        .testEnd();
    }

    @AfterTest
    private void deleteEntities() {
        for (Integer mediaId : mediaIds) {
              MediaPrecondition.media().
                setCredentials(USER_FOR_DELETION).
                deleteMedia(mediaId);
        };

        for (Integer publisherId : publishersIds) {
            PublisherPrecondition.publisher().
                    setCredentials(USER_FOR_DELETION).
                    deletePublisher(publisherId);
        };
    }

    private MediaRequest createCustomMedia(String name, String publisherName) {

        publisher =  PublisherPrecondition.publisher()
                .createNewPublisher(createCustomPublisher(publisherName))
                .build()
                .getPublisherResponse();

        return  MediaRequest.builder()
                .name(captionWithSuffix(name))
                .publisherId(publisher.getId())
                .platformId(2)
                .url("http://testsearch.com")
                .isEnabled(true)
                .categoryIds(List.of(1, 9))
                .build();
    }

    private PublisherRequest createCustomPublisher(String publisherName) {

        return PublisherRequest.builder()
                .name(captionWithSuffix(publisherName))
                .salesAccountName("ops_persoj")
                .mail(randomMail())
                .isEnabled(true)
                .domain(randomUrl())
                .currency(Currency.JPY.name())
                .categoryIds(List.of(1,9))
                .dspIds(List.of(7))
                .build();
    }

    private List<Media> getAllItemsByParams(String strParams) {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        queryParams.put("sort", "name-asc");
        List<Media> mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList,Media.class);
    }

}
