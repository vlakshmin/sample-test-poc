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
import widgets.errormessages.ErrorMessages;
import zutils.ObjectMapperUtils;

import java.util.*;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.*;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaActivateDeactivateTableTests extends BaseTest {

    private int totalMedia;
    private List<Media> mediaList;
    private List<Publisher> publishersList;

    private Media mediaActive1;
    private Media mediaInActive1;
    private Media mediaActiveBulk1;
    private Media mediaActiveBulk2;
    private Media mediaActiveBulk3;

    private Media mediaInActiveBulk1;
    private Media mediaInActiveBulk2;
    private Media mediaInActiveBulk3;

    private Media mediaActiveWithInactivePublisher;
    private Media mediaInActiveWithInactivePublisher;

    private MediaPage mediaPage;

    private static final String PREFIX_MEDIA = "autoMediaSingle";
    private static final String PREFIX_MEDIA_BULK = "autoMediaBulk";
    private static final String CHECKBOX_CLASS = "v-icon notranslate mdi mdi-checkbox-blank-outline theme--light";

    public MediaActivateDeactivateTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        deleteMedia();
     //   deletePublishers();
        //Creating media Using API
        mediaActive1 = createMedia(PREFIX_MEDIA,true);
        mediaInActive1 = createMedia(PREFIX_MEDIA,false);

        mediaActiveWithInactivePublisher = createMedia(PREFIX_MEDIA,true);
        deactivatePublisher(mediaActiveWithInactivePublisher.getPublisherName(),
                mediaActiveWithInactivePublisher.getPublisherId());
        mediaInActiveWithInactivePublisher = createMedia(PREFIX_MEDIA, false);
        deactivatePublisher(mediaInActiveWithInactivePublisher.getPublisherName(),
                mediaInActiveWithInactivePublisher.getPublisherId());

        mediaActiveBulk1 = createMedia(PREFIX_MEDIA_BULK,true);
        mediaActiveBulk2 = createMedia(PREFIX_MEDIA_BULK,true);
        mediaActiveBulk3 = createMedia(PREFIX_MEDIA_BULK,true);
        mediaInActiveBulk1 = createMedia(PREFIX_MEDIA_BULK,false);
        mediaInActiveBulk2 = createMedia(PREFIX_MEDIA_BULK,false);
        mediaInActiveBulk3 = createMedia(PREFIX_MEDIA_BULK,false);

        totalMedia = getTotalMedia();
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

    @Test(testName = "Deactivate single media")
    public void deactivateSingleMedia() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", mediaActive1))
                .setValueWithClean(tableData.getSearch(), PREFIX_MEDIA)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-20 of %s",totalMedia))
                .selectAllRowsByColumnCellValue(tableData,ColumnNames.MEDIA_NAME,mediaActive1.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getDeactivateMediaButton())
                .then("Validate media status should be changed on 'Inactive'")
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActive1.getName()),
                                Statuses.INACTIVE.getStatus())
                .testEnd();
    }

    @Test(testName = "Deactivate single media with inactive publisher")
    public void deactivateSingleMediaWithInactivePublisher() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var toasterPanel = mediaPage.getToasterMessage();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", mediaActiveWithInactivePublisher))
                .setValueWithClean(tableData.getSearch(), PREFIX_MEDIA)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-20 of %s",totalMedia))
                .selectAllRowsByColumnCellValue(tableData,ColumnNames.MEDIA_NAME,mediaActiveWithInactivePublisher.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getDeactivateMediaButton())
                .validate(visible, toasterPanel.getPanelError())
                .and("Click on the link 'View More' on toaster message")
                .clickOnWebElement(toasterPanel.getViewErrorDetails())
                .then("Validate error message")
                .validateContainsText(toasterPanel.getMessageStatus(),ErrorMessages.BAD_REQUEST.getText())
                .validateContainsText(toasterPanel.getMessageError(), ErrorMessages.PUBLISHER_IS_DISABLED.getText())
                .and("Close toaster message")
                .clickOnWebElement(toasterPanel.getRemoveIcon())
                .then("Validate media status should not be changed on 'Inactive'")
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveWithInactivePublisher.getName()),
                        Statuses.ACTIVE.getStatus())
                .testEnd();
    }

    @Test(testName = "Activate single media with inactive publisher")
    public void activateSingleMediaWithInactivePublisher() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var toasterPanel = mediaPage.getToasterMessage();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", mediaInActiveWithInactivePublisher))
                .setValueWithClean(tableData.getSearch(), PREFIX_MEDIA)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-20 of %s",totalMedia))
                .selectAllRowsByColumnCellValue(tableData,ColumnNames.MEDIA_NAME,
                        mediaInActiveWithInactivePublisher.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .validate(visible, toasterPanel.getPanelError())
                .and("Click on the link 'View More' on toaster message")
                .clickOnWebElement(toasterPanel.getViewErrorDetails())
                .then("Validate error message")
                .validateContainsText(toasterPanel.getMessageStatus(),ErrorMessages.BAD_REQUEST.getText())
                .validateContainsText(toasterPanel.getMessageError(), ErrorMessages.PUBLISHER_IS_DISABLED.getText())
                .and("Close toaster message")
                .clickOnWebElement(toasterPanel.getRemoveIcon())
                .then("Validate media status should not be changed on 'Active'")
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME,
                                mediaInActiveWithInactivePublisher.getName()),
                        Statuses.INACTIVE.getStatus())
                .testEnd();
    }

    @Test(testName = "Activate single media")
    public void activateSingleMedia() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", mediaInActive1))
                .setValueWithClean(tableData.getSearch(), mediaInActive1.getName())
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        "1-1 of 1")
                .selectAllRowsByColumnCellValue(tableData,ColumnNames.MEDIA_NAME,
                        mediaInActive1.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .then("Validate media status should be changed on 'Active'")
                .waitAndValidate(disappear, mediaPage.getTableProgressBar())
                .and(String.format("Search media by name '%s'", mediaInActive1))
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                        ColumnNames.MEDIA_NAME, mediaInActive1.getName()),
                        Statuses.ACTIVE.getStatus())
                .testEnd();
    }

    @Test(testName = "Activate bulk media")
    public void activateBulkMedia() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", PREFIX_MEDIA_BULK))
                .setValueWithClean(tableData.getSearch(),PREFIX_MEDIA_BULK)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        "1-6 of 6")
                .selectAllRowsByColumnCellValue(tableData,ColumnNames.MEDIA_NAME,
                        PREFIX_MEDIA_BULK)
                .and("Click 'Activate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .then("Validate media status should be changed on 'Active'")
                .waitAndValidate(visible,tableData.getCheckbox(1)
                        .shouldHave(attributeMatching("class",CHECKBOX_CLASS)))
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulk1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulk2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulk3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulk1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulk2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulk3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validate(not(exist),mediaPage.getToasterMessage().getPanelError())
                .testEnd();
    }

    private int getTotalMedia() {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("search",PREFIX_MEDIA);

        return media()
                .getMediaWithFilter(queryParam)
                .build()
                .getMediaGetAllResponse()
                .getTotal();
    }

    private List<Media> getAllItemsByParams(String strParams) {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        var mediaList = media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList, Media.class);
    }

    private List<Publisher> getAllPublishersItemsByParams(String strParams) {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        var publisherList = publisher()
                .getPublisherWithFilter(queryParams)
                .build()
                .getPublisherGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(publisherList, Publisher.class);
    }


    @AfterMethod
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @AfterClass
    private void cleanData(){
        deleteMedia();

    }

    private void deleteMedia(){
        mediaList  = getAllItemsByParams(PREFIX_MEDIA_BULK);

        for (Media media : mediaList) {
            media().
                    setCredentials(USER_FOR_DELETION).
                    deleteMedia(media.getId());
        }

    }

    private void deletePublishers(){
        publishersList = getAllPublishersItemsByParams("autoPublisher");

        for (Publisher publisher: publishersList) {
            publisher().
                    setCredentials(USER_FOR_DELETION).
                    deletePublisher(publisher.getId());
        }
    }

    private Media createMedia(String name,Boolean status) {

        PublisherRequest publisherRequest = getPublisherRequest(captionWithSuffix("autoPublisher"),true);

        Publisher publisher = publisher()
                .createNewPublisher(publisherRequest)
                .build()
                .getPublisherResponse();

        MediaRequest request = MediaRequest.builder()
                .name(captionWithSuffix(name))
                .publisherId(publisher.getId())
                .platformId(2)
                .url("http://localhost:5016")
                .isEnabled(status)
                .categoryIds(List.of(1, 9))
                .build();

        return MediaPrecondition.media()
                        .createNewMedia(request)
                        .build()
                        .getMediaResponse();
    }

    private void deactivatePublisher(String name, Integer id){

        PublisherRequest publisherRequest = getPublisherRequest(name,false);

        PublisherPrecondition.publisher()
                .updatePublisher(publisherRequest,id)
                .build()
                .getPublisherResponse();
    }

    private PublisherRequest getPublisherRequest(String name, Boolean isEnabled){

        return PublisherRequest.builder()
                .name(name)
                .salesAccountName("person_auto")
                .mail(randomMail())
                .isEnabled(isEnabled)
                .domain(randomUrl())
                .currency(Currency.JPY.name())
                .categoryIds(List.of(1, 9))
                .dspIds(List.of(7))
                .build();
    }
}
