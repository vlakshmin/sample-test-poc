package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.inventory.media.MediaRequest;
import api.preconditionbuilders.MediaPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import zutils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class SearchTableTests extends BaseTest {

    private Publisher publisher;
    private String mediaName;
    private String pubName;
    private List<String> sortNamesByAsc;

    private MediaPage mediaPage;


    public SearchTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        mediaName = "mediaSS1";
        pubName = "pubSSS2";


       Media media = MediaPrecondition.media()
                .createNewMedia(createCustomMedia(mediaName,"pub"))
                .build()
                .getMediaResponse();

        //expected results for Media Name column
        sortNamesByAsc  = getAllItemsByParams(mediaName).stream()
                .map(Media::getName)
                .collect(Collectors.toList());
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
        String jsonString = ObjectMapperUtils.toJson(mediaList);

       return ObjectMapperUtils.getCollectionType(jsonString,Media.class);
    }

    @Test(testName = "Sorting 'Media Name' column by descending")
    public void mediaSearchByMediaNameDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

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
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort","ascending")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),sortNamesByAsc)
                .and()
                .logOut()
        .testEnd();
    }

    private MediaRequest createCustomMedia(String name, String publisherName) {

        publisher =  PublisherPrecondition.publisher()
                .createNewPublisher()
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

}
