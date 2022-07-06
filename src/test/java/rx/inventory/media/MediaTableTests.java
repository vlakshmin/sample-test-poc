package rx.inventory.media;

import aj.org.objectweb.asm.TypeReference;
import api.dto.GenericResponse;
import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.restassured.mapper.ObjectMapper;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.ObjectMapperUtils.getCollectionType;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaTableTests extends BaseTest {

    private Media media;
    private int totalMedia;
    private List<String> sortIdsByAsc;
    private List<String> sortIdsByDesc;
    private List<String> sortNamesByDesc;
    private List<String> sortNamesByAsc;

    private List<String> sortPublisherNameByDesc;
    private List<String> sortPublisherNameByAsc;

    private List<String> sortURLByDesc;
    private List<String> sortURLByAsc;

    private MediaPage mediaPage;
    private Integer totalActiveMedia;
    private EditMediaSidebar editMediaSidebar;

    public MediaTableTests() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    public void createNewMedia() throws IOException {

        totalMedia = MediaPrecondition.media()
                .getAllMediaList()
                .build()
                .getMediaGetAllResponse()
                .getTotal();

        //expected results for Media Name column
        sortNamesByDesc  = getAllItemsByParams("name-desc").stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        sortNamesByAsc  = getAllItemsByParams("name-asc").stream()
                .map(Media::getName)
                .collect(Collectors.toList());

        //Expected result for ID column
        sortIdsByAsc  = getAllItemsByParams("id-asc").stream()
                .map(Media::getId)
                .map(x->x.toString())
                .collect(Collectors.toList());

        sortIdsByDesc  = getAllItemsByParams("id-desc").stream()
                .map(Media::getId)
                .map(x->x.toString())
                .collect(Collectors.toList());

        //Expected result for  Publisher Name column
        sortPublisherNameByAsc  = getAllItemsByParams("publisher_name-asc").stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());

        sortPublisherNameByDesc  = getAllItemsByParams("publisher_name-desc").stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());

        //Expected result for URL column
        sortURLByAsc  = getAllItemsByParams("url-asc").stream()
                .map(Media::getUrl)
                .collect(Collectors.toList());

        sortURLByDesc  = getAllItemsByParams("url-desc").stream()
                .map(Media::getUrl)
                .collect(Collectors.toList());
    }

    private List<Media> getAllItemsByParams(String strParams) throws IOException {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("sort", strParams);
        List<Media> mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();
        String jsonString = ObjectMapperUtils.toJson(mediaList);

       return ObjectMapperUtils.getCollectionType(jsonString,Media.class);
    }

    @Test
    public void mediaSortingByMediaName() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),"aria-sort","ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),"aria-sort","descending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'", String.format("1-50 of %s", totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(), String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), sortNamesByDesc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'", String.format("51-100 of %s", totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(), String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME), sortNamesByDesc.subList(50,100))
                .and()
                .testEnd();

        //allure serve
    }

}
