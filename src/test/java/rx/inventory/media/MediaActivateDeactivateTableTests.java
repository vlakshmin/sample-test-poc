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

    private Media mediaActive1;
    private Media mediaInActive1;
    private Media mediaActiveBulkA1;
    private Media mediaActiveBulkA2;
    private Media mediaActiveBulkA3;
    private Media mediaActiveBulkD1;
    private Media mediaActiveBulkD2;
    private Media mediaActiveBulkD3;

    private Media mediaInActiveBulkA1;
    private Media mediaInActiveBulkA2;
    private Media mediaInActiveBulkA3;
    private Media mediaInActiveBulkD1;
    private Media mediaInActiveBulkD2;
    private Media mediaInActiveBulkD3;

    private Media mediaActiveWithInactivePublisher;
    private Media mediaInActiveWithInactivePublisher;

    private MediaPage mediaPage;

    private static final String PREFIX_MEDIA = "autoMediaSingle";
    private static final String PREFIX_MEDIA_INACTIVE_PUBLISHER = "autoMediaInactivePub";
    private static final String PREFIX_MEDIA_BULK_1 = "autoMediaBulk1";
    private static final String PREFIX_MEDIA_BULK_2 = "autoMediaBulk2";
    private static final String CLASS_ATTRIBUTE_FOR_UNCHECKED_CHECKBOX = "v-icon notranslate mdi mdi-checkbox-blank-outline theme--light";

    public MediaActivateDeactivateTableTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        deleteMedia();

        //Creating media Using API
        mediaActive1 = createMedia(PREFIX_MEDIA, true);
        mediaInActive1 = createMedia(PREFIX_MEDIA, false);


        mediaActiveBulkA1 = createMedia(PREFIX_MEDIA_BULK_1, true);
        mediaActiveBulkA2 = createMedia(PREFIX_MEDIA_BULK_1, true);
        mediaActiveBulkA3 = createMedia(PREFIX_MEDIA_BULK_1, true);
        mediaInActiveBulkA1 = createMedia(PREFIX_MEDIA_BULK_1, false);
        mediaInActiveBulkA2 = createMedia(PREFIX_MEDIA_BULK_1, false);
        mediaInActiveBulkA3 = createMedia(PREFIX_MEDIA_BULK_1, false);

        mediaActiveBulkD1 = createMedia(PREFIX_MEDIA_BULK_2, true);
        mediaActiveBulkD2 = createMedia(PREFIX_MEDIA_BULK_2, true);
        mediaActiveBulkD3 = createMedia(PREFIX_MEDIA_BULK_2, true);
        mediaInActiveBulkD1 = createMedia(PREFIX_MEDIA_BULK_2, false);
        mediaInActiveBulkD2 = createMedia(PREFIX_MEDIA_BULK_2, false);
        mediaInActiveBulkD3 = createMedia(PREFIX_MEDIA_BULK_2, false);

        mediaActiveWithInactivePublisher = createMedia(PREFIX_MEDIA_INACTIVE_PUBLISHER, true);
        deactivatePublisher(mediaActiveWithInactivePublisher.getPublisherId());
        mediaInActiveWithInactivePublisher = createMedia(PREFIX_MEDIA_INACTIVE_PUBLISHER, false);
        deactivatePublisher(mediaInActiveWithInactivePublisher.getPublisherId());
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
                .and(String.format("Search media by name '%s'", mediaActive1.getName()))
                .setValueWithClean(tableData.getSearch(), mediaActive1.getName())
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME, mediaActive1.getName())
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
                .and(String.format("Search media by name '%s'", mediaActiveWithInactivePublisher.getName()))
                .setValueWithClean(tableData.getSearch(), mediaActiveWithInactivePublisher.getName())
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME,
                        mediaActiveWithInactivePublisher.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getDeactivateMediaButton())
                .validate(visible, toasterPanel.getPanelError())
                .and("Click on the link 'View More' on toaster message")
                .clickOnWebElement(toasterPanel.getViewErrorDetails())
                .then("Validate error message")
                .validateContainsText(toasterPanel.getMessageStatus(),
                        ErrorMessages.BAD_REQUEST.getText())
                .validateContainsText(toasterPanel.getMessageError(),
                        ErrorMessages.PUBLISHER_IS_DISABLED.getText())
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
                .and(String.format("Search media by name '%s'", mediaInActiveWithInactivePublisher.getName()))
                .setValueWithClean(tableData.getSearch(), mediaInActiveWithInactivePublisher.getName())
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME,
                        mediaInActiveWithInactivePublisher.getName())
                .and("Click 'Deactivate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .validate(visible, toasterPanel.getPanelError())
                .and("Click on the link 'View More' on toaster message")
                .clickOnWebElement(toasterPanel.getViewErrorDetails())
                .then("Validate error message")
                .validateContainsText(toasterPanel.getMessageStatus(), ErrorMessages.BAD_REQUEST.getText())
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
                .and(String.format("Search media by name '%s'", mediaInActive1.getName()))
                .setValueWithClean(tableData.getSearch(), mediaInActive1.getName())
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        "1-1 of 1")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME,
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
                .and(String.format("Search media by name '%s'", PREFIX_MEDIA_BULK_1))
                .setValueWithClean(tableData.getSearch(), PREFIX_MEDIA_BULK_1)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        "1-6 of 6")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME,
                        PREFIX_MEDIA_BULK_1)
                .and("Click 'Activate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .then("Validate media status should be changed on 'Active'")
                .waitAndValidate(visible, tableData.getCheckbox(1)
                        .shouldHave(attributeMatching("class", CLASS_ATTRIBUTE_FOR_UNCHECKED_CHECKBOX)))
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkA1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkA2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkA3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkA1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkA2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkA3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validate(not(exist), mediaPage.getToasterMessage().getPanelError())
                .testEnd();
    }

    @Test(testName = "Deactivate bulk media")
    public void deactivateBulkMedia() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .given()
                .and(String.format("Search media by name '%s'", PREFIX_MEDIA_BULK_2))
                .setValueWithClean(tableData.getSearch(), PREFIX_MEDIA_BULK_2)
                .clickEnterButton(tableData.getSearch())
                .validateContainsText(tablePagination.getPaginationPanel(),
                        "1-6 of 6")
                .selectAllRowsByColumnCellValue(tableData, ColumnNames.MEDIA_NAME,
                        PREFIX_MEDIA_BULK_2)
                .and("Click 'Activate' button")
                .clickOnWebElement(mediaPage.getActivateMediaButton())
                .then("Validate media status should be changed on 'Inactive'")
                .waitAndValidate(visible, tableData.getCheckbox(1)
                        .shouldHave(attributeMatching("class", CLASS_ATTRIBUTE_FOR_UNCHECKED_CHECKBOX)))
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkD1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkD2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaInActiveBulkD3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkD1.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkD2.getName()),
                        Statuses.ACTIVE.getStatus())
                .validateContainsText(tableData.getCellByRowValue(ColumnNames.STATUS,
                                ColumnNames.MEDIA_NAME, mediaActiveBulkD3.getName()),
                        Statuses.ACTIVE.getStatus())
                .validate(not(exist), mediaPage.getToasterMessage().getPanelError())
                .testEnd();
    }

    private int getTotalMedia() {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("search", PREFIX_MEDIA);

        return media()
                .getMediaWithFilter(queryParam)
                .build()
                .getMediaGetAllResponse()
                .getTotal();
    }

    private List<Media> getAllMediaItemsByParams(String strParams) {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", strParams);
        var mediaList = media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList, Media.class);
    }

    private List<Publisher> getAllPublishersItemsByParams(String strParams) {
        Map<String, Object> queryParams = new HashMap();
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
    private void cleanData() {
        deleteMedia();
        //TODO: waiting filter table cleaned
        //   deletePublishers();
    }

    private void deleteMedia() {

        deleteMedia(getAllMediaItemsByParams(PREFIX_MEDIA));
        deleteMedia(getAllMediaItemsByParams(PREFIX_MEDIA_BULK_1));
        deleteMedia(getAllMediaItemsByParams(PREFIX_MEDIA_BULK_2));
        deleteMedia(getAllMediaItemsByParams(PREFIX_MEDIA_INACTIVE_PUBLISHER));
    }

    private void deleteMedia(List<Media> mediaList) {

        for (Media media : mediaList) {
            media()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteMedia(media.getId())
                    .build();
        }
    }

    private void deactivatePublisher(Integer id) {
        publisher().changePublisherStatus(id, false);
    }

    private void deletePublishers() {
        List<Publisher> publishersList = getAllPublishersItemsByParams("autoPublisher");

        for (Publisher publisher : publishersList) {
            publisher()
                    .setCredentials(USER_FOR_DELETION)
                    .deletePublisher(publisher.getId())
                    .build();
        }
    }

    private Media createMedia(String name, Boolean isEnabled) {

        return media()
                .createNewMedia(name, isEnabled)
                .build()
                .getMediaResponse();
    }
}
