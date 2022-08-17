package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import widgets.inventory.media.sidebar.MediaTypes;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaEditTests extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private Publisher publisher;
    private Media media;

    private static String URL = "https://play.google.com/store/apps/";
    private static String BUNDLE = "com.viber.voip";

    public MediaEditTests() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    private void initAndLogin() {
        publisher = publisher()
                .createNewPublisher(captionWithSuffix("1autoPub"))
                .build()
                .getPublisherResponse();

        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .testEnd();

    }

    @Test(description = "Create Media with 'IOS' media type")
    private void editMediaIOSMediaType() {
        var mediaName = captionWithSuffix("autoMediaIOS");
        media = createMedia(mediaName, MediaTypes.IOS.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.IOS.getName(), MediaTypes.MOBILE_WEB.getName(),
                mediaName + "Updated1", URL + "Updated1", "");
    }

    @Test(description = "Edit Media with 'IOS Web View' media type")
    private void editMediaIOSWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaIOSWebView");
        media = createMedia(mediaName, MediaTypes.IOS_WEB_VIEW.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.IOS_WEB_VIEW.getName(), MediaTypes.MOBILE_WEB.getName(),
                mediaName + "Updated2", URL + "Updated2", "com.app.updated");
    }

    @Test(description = "Edit Media with 'Android' media type")
    private void createMediaAndroidMediaType() {
        var mediaName = captionWithSuffix("autoMediaAndroid");
        media = createMedia(mediaName, MediaTypes.ANDROID.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.ANDROID.getName(), MediaTypes.MOBILE_WEB.getName(),
                mediaName + "Updated3", URL + "Updated3", "com.app.updated");
    }

    @Test(description = "Edit Media with 'Android Web View' media type")
    private void editMediaAndroidWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaAndroidWebView");
        media = createMedia(mediaName, MediaTypes.ANDROID_WEB_VIEW.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.ANDROID_WEB_VIEW.getName(), MediaTypes.MOBILE_WEB.getName(),
                mediaName + "Updated4", URL + "Updated4", "com.app.updated");
    }

    @Test(description = "Edit Media with 'PC Web' media type")
    private void editMediaPCWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaPCWeb");

        media = createMedia(mediaName, MediaTypes.PC_WEB.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.PC_WEB.getName(), MediaTypes.ANDROID.getName(),
                mediaName + "Updated5", URL + "Updated5", "com.app.updated");
    }

    @Test(description = "Edit Media with 'Mobile Web' media type")
    private void editMediaMobileWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaMobileWeb");
        var appStoreURL = "https://checkmedia.com";

        media = createMedia(mediaName, MediaTypes.MOBILE_WEB.getName(), URL, BUNDLE);

        editMedia(media, MediaTypes.MOBILE_WEB.getName(), MediaTypes.ANDROID.getName(),
                mediaName + "Updated6", URL + "Updated6", "com.app.updated");
    }


    @Step("Create Media via Api")
    private Media createMedia(String name, String mediaType, String url, String bundle) {


        return media()
                .createNewMedia(name, mediaType, url, bundle, true)
                .build()
                .getMediaResponse();
    }

    @Step("Edit Media")
    private void editMedia(Media media, String mediaType, String mediaTypeUpdated,
                           String mediaNameUpdated, String urlUpdated, String bundleUpdated) {

        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var tableOptions = mediaPage.getMediaTable().getTableOptions();

        testStart()
                .and(String.format("Search media %s", media.getName()))
                .setValueWithClean(tableData.getSearch(), media.getName())
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, media.getName())
                .waitSideBarOpened()
                .then("Check all fields")
                .validateAttribute(editMediaSidebar.getActiveToggle(), "aria-checked", "true")
                .validate(disabled, editMediaSidebar.getPublisherNameInput())
                .validate(editMediaSidebar.getPublisherInput(), media.getPublisherName())
                .validateAttribute(editMediaSidebar.getNameInput(), "value", media.getName())
                .validate(editMediaSidebar.getMediaType(), mediaType)
                .testEnd();

        if (!mediaType.equals("PC Web") && (!mediaType.equals("Mobile Web"))) {
            testStart()
                    .validateAttribute(editMediaSidebar.getAppStoreURL(), "value", media.getUrl())
                    .validateAttribute(editMediaSidebar.getBundleInput(), "value", media.getAppBundleId())
                    .testEnd();
        } else {
            testStart()
                    .validateAttribute(editMediaSidebar.getSiteURL(), "value", media.getUrl())
                    .testEnd();
        }
        testStart()
                .then("Edit all fields")
                .turnToggleOff(editMediaSidebar.getActiveToggle())
                .setValueWithClean(editMediaSidebar.getNameInput(), mediaNameUpdated)
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), mediaTypeUpdated)

                .testEnd();

        if (!mediaTypeUpdated.equals("PC Web") && (!mediaTypeUpdated.equals("Mobile Web"))) {
            testStart()
                    .setValueWithClean(editMediaSidebar.getAppStoreURL(), urlUpdated)
                    .setValueWithClean(editMediaSidebar.getBundleInput(), bundleUpdated)
                    .testEnd();
        } else {
            testStart()
                    .setValueWithClean(editMediaSidebar.getSiteURL(), urlUpdated)
                    .testEnd();
        }

        testStart()
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .and("Error message is absent")
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .and("Search new media")
                .setValueWithClean(tableData.getSearch(), mediaNameUpdated)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")

                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, mediaNameUpdated)
                .waitSideBarOpened()
                .then("Check all fields")
                .validateAttribute(editMediaSidebar.getActiveToggle(), "aria-checked", "false")
                .validate(disabled, editMediaSidebar.getPublisherNameInput())
                .validate(editMediaSidebar.getPublisherInput(), media.getPublisherName())
                .validateAttribute(editMediaSidebar.getNameInput(), "value", mediaNameUpdated)
                .validate(editMediaSidebar.getMediaType(), mediaTypeUpdated)
                .testEnd();

        if (!mediaTypeUpdated.equals("PC Web") && (!mediaTypeUpdated.equals("Mobile Web"))) {
            testStart()
                    .validateAttribute(editMediaSidebar.getAppStoreURL(), "value", urlUpdated)
                    .validateAttribute(editMediaSidebar.getBundleInput(), "value", bundleUpdated)
                    .testEnd();
        } else {
            testStart()
                    .validateAttribute(editMediaSidebar.getSiteURL(), "value", urlUpdated)
                    .testEnd();
        }

        testStart()
                .and("Click Save")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Show column 'Site/App Store URL'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .then("Validate data in table")
                .validate(tableData.getCellByRowValue(ColumnNames.ID, ColumnNames.MEDIA_NAME, mediaNameUpdated), media.getId().toString())
                .validate(tableData.getCellByRowValue(ColumnNames.STATUS, ColumnNames.MEDIA_NAME, mediaNameUpdated), Statuses.INACTIVE.getStatus())
                .validate(tableData.getCellByRowValue(ColumnNames.PUBLISHER, ColumnNames.MEDIA_NAME, mediaNameUpdated), media.getPublisherName())
                .validate(tableData.getCellByRowValue(ColumnNames.PLATFORM, ColumnNames.MEDIA_NAME, mediaNameUpdated), mediaTypeUpdated)
                .validate(tableData.getCellByRowValue(ColumnNames.SITE_APP_STORE_URL, ColumnNames.MEDIA_NAME, mediaNameUpdated), urlUpdated)
                .clickOnWebElement(tableData.getClear())
                .then("Validate that text in table footer '1-20 of X")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-20 of ")
                .testEnd();
    }


    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();

    }
}
