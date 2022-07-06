package rx.inventory.media;

import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaTableTests extends BaseTest {

    private Media media;
    private int totalMedia;
    private List<String> ids;
    private MediaPage mediaPage;
    private Integer totalActiveMedia;
    private EditMediaSidebar editMediaSidebar;

    public MediaTableTests() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    public void createNewMedia() {

        totalMedia = MediaPrecondition.media()
                .getAllMediaList()
                .build()
                .getMediaGetAllResponse()
                .getTotal();

        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("sort", "id-asc");

        List<Media> mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems().subList(0,100);
        mediaList.stream().forEach(System.out::println);

        int a=2;
    }

    @Test
    public void mediaSorting() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Sort column 'ID'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 100 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "100")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'", String.format("1-100 of %s", totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(), String.format("1-100 of %s", totalMedia))
                .then("Validate data in column 'ID' should be sorted by asc")
                //  .validateListContent(tableData.getCustomCells(ColumnNames.ID), ids)

                .and()
                .testEnd();

        //allure serve
    }

}
