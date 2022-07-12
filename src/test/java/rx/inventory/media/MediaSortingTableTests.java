package rx.inventory.media;

import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import zutils.ObjectMapperUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaSortingTableTests extends BaseTest {

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

    public MediaSortingTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        totalMedia = getTotalMedia();

        //expected results for Media Name column
        sortNamesByDesc  = getNamesByDesc();
        sortNamesByAsc  = getNamesByAsc();

        //Expected result for ID column
        sortIdsByAsc  = getIdsByAsc();
        sortIdsByDesc  = getIdsByDesc();

        //Expected result for  Publisher Name column
        sortPublisherNameByAsc  = getPublisherNameByAsc();
        sortPublisherNameByDesc  = getPublisherNameByDesc();

        //Expected result for URL column
        sortURLByAsc  = getUrlByAsc();
        sortURLByDesc  = getUrlByDesc();
    }

    @BeforeMethod
    private void login(){
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
        .testEnd();
    }
    @AfterMethod
    private void logOut(){
        testStart()
                .given()
                .logOut()
        .testEnd();
    }

    @Test(testName = "Sorting 'Media Name' column by descending")
    public void mediaSortingByMediaNameDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort","ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort","descending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                       totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        sortNamesByDesc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                     totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        sortNamesByDesc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'Media Name' column by ascending")
    public void mediaSortingByMediaNameAsc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'Media Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()),
                        "aria-sort","ascending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        sortNamesByAsc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Media Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        sortNamesByAsc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'Publisher' column by ascending")
    public void mediaSortingByPublisherNameAsc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'Publisher'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort","ascending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                         totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Publisher' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByAsc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Publisher' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByAsc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'Publisher' column by descending")
    public void mediaSortingByPublisherNameDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'Publisher'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort","ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort","descending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Publisher' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByDesc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Publisher' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByDesc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'ID' column by descending")
    public void mediaSortingByIdDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'ID'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort","ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort","descending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                       totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'ID' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByDesc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'ID' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByDesc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'ID' column by ascending")
    public void mediaSortingByIdAsc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and("Sort column 'ID'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort","ascending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'ID' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByAsc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'ID' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByAsc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'Site/App Store URL' column by descending")
    public void mediaSortingByUrlDesc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var tableOptions = mediaPage.getMediaTable().getTableOptions();

        testStart()
                .given()
                .and("Show column 'Site/App Store URL'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .and("Sort column 'Site/App Store URL'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()),
                        "aria-sort","ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()),
                        "aria-sort","descending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Site/App store URL' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.SITE_APP_STORE_URL),
                        sortURLByDesc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Site/App store URL' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.SITE_APP_STORE_URL),
                        sortURLByDesc.subList(50,100))
        .testEnd();
    }

    @Test(testName = "Sorting 'Site/App Store URL' column by ascending")
    public void mediaSortingByUrlAsc() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var tableOptions = mediaPage.getMediaTable().getTableOptions();

        testStart()
                .given()
                .and("Show column 'Site/App Store URL'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .and("Sort column 'Site/App store URL'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()),
                        "aria-sort","ascending")
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalMedia))
                .then("Validate data in column 'Site/App store URL' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.SITE_APP_STORE_URL),
                        sortURLByAsc.subList(0,50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-100 of %s'",
                        totalMedia))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalMedia))
                .then("Validate data in column 'Site/App store URL' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.SITE_APP_STORE_URL),
                        sortURLByAsc.subList(50,100))
        .testEnd();
    }

    private int getTotalMedia(){

       return MediaPrecondition.media()
                .getAllMediaList()
                .build()
                .getMediaGetAllResponse()
                .getTotal();
    }

    private List<String> getNamesByDesc(){

        return   getAllItemsByParams("name-desc").stream()
                .map(Media::getName)
                .collect(Collectors.toList());
    }

    private List<String> getNamesByAsc(){

        return  getAllItemsByParams("name-asc").stream()
                .map(Media::getName)
                .collect(Collectors.toList());
    }

    private List<String> getIdsByAsc(){

        return   getAllItemsByParams("id-asc").stream()
                .map(Media::getId)
                .map(x->x.toString())
                .collect(Collectors.toList());
    }

    private List<String> getIdsByDesc(){

        return   getAllItemsByParams("id-desc").stream()
                .map(Media::getId)
                .map(x->x.toString())
                .collect(Collectors.toList());
    }

    private List<String> getPublisherNameByAsc(){

        return   getAllItemsByParams("publisher_name-asc").stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());
    }

    private List<String> getPublisherNameByDesc(){

        return   getAllItemsByParams("publisher_name-desc").stream()
                .map(Media::getPublisherName)
                .collect(Collectors.toList());
    }

    private List<String> getUrlByAsc(){

        return   getAllItemsByParams("url-asc").stream()
                .map(Media::getUrl)
                .collect(Collectors.toList());
    }

    private List<String> getUrlByDesc(){

        return   getAllItemsByParams("url-desc").stream()
                .map(Media::getUrl)
                .collect(Collectors.toList());
    }
    private List<Media> getAllItemsByParams(String strParams) {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("sort", strParams);
        var mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList,Media.class);
    }

}
