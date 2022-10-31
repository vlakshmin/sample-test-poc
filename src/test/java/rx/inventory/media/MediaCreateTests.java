package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.*;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import widgets.inventory.media.sidebar.MediaTypes;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
@Link("https://rakutenadvertising.atlassian.net/browse/GS-3017")
@Epic("v1.26.0/GS-3017")
public class MediaCreateTests extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private Publisher publisher;

    public MediaCreateTests() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    private void initAndLogin() {
        publisher = publisher()
                .createNewPublisher(captionWithSuffix("0002autoPub"))
                .build()
                .getPublisherResponse();

        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .clickBrowserRefreshButton()
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .clickBrowserRefreshButton()
                .testEnd();
    }

    @Test(description = "Create Media with 'IOS' media type")
    private void createMediaIOSMediaType() {
        var mediaName = captionWithSuffix("autoMediaIOS");
        var appStoreURL = "https://play.google.com/store/apps/";
        var bundle = "com.viber.voip";
        createAndCheckCreatedMedia(mediaName, appStoreURL, bundle, MediaTypes.IOS.getName());
    }

    @Test(description = "Create Media with 'IOS Web View' media type")
    private void createMediaIOSWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaIOSWebView");
        var appStoreURL = "https://play.google.com/store/apps/";
        var bundle = "com.viber.voip";
        createAndCheckCreatedMedia(mediaName, appStoreURL, bundle, MediaTypes.IOS_WEB_VIEW.getName());
    }

    @Test(description = "Create Media with 'Android' media type")
    private void createMediaAndroidMediaType() {
        var mediaName = captionWithSuffix("autoMediaAndroid");
        var appStoreURL = "https://play.google.com/store/apps/";
        var bundle = "com.viber.voip";
        createAndCheckCreatedMedia(mediaName, appStoreURL, bundle, MediaTypes.ANDROID.getName());
    }

    @Test(description = "Create Media with 'Android Web View' media type")
    private void createMediaAndroidWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaAndroidWebView");
        var appStoreURL = "https://play.google.com/store/apps/";
        var bundle = "com.viber.voip";
        createAndCheckCreatedMedia(mediaName, appStoreURL, bundle, MediaTypes.ANDROID_WEB_VIEW.getName());
    }

    @Test(description = "Create Media with 'PC Web' media type")
    private void createMediaPCWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaPCWeb");
        var appStoreURL = "https://checkmedia.com";
        createAndCheckCreatedMedia(mediaName, appStoreURL, "", MediaTypes.PC_WEB.getName());
    }

    @Test(description = "Create Media with 'Mobile Web' media type")
    private void createMediaMobileWebViewMediaType() {
        var mediaName = captionWithSuffix("autoMediaMobileWeb");
        var appStoreURL = "https://checkmedia.com";
        createAndCheckCreatedMedia(mediaName, appStoreURL, "", MediaTypes.MOBILE_WEB.getName());
    }


    @Step("Create Media {0} with media type {1}")
    private void createAndCheckCreatedMedia(String mediaName, String url, String bundle, String mediaType) {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tableOptions = mediaPage.getMediaTable().getTableOptions();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        testStart()
                .clickOnWebElement(mediaPage.getCreateMediaButton())
                .waitSideBarOpened()
                .selectFromDropdown(editMediaSidebar.getPublisherInput(),
                        editMediaSidebar.getPublisherItems(), publisher.getName())
                .and("Fill Name")
                .setValueWithClean(editMediaSidebar.getNameInput(), mediaName)
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), mediaType)

                .testEnd();

        if (!mediaType.equals("PC Web") && (!mediaType.equals("Mobile Web"))) {
            testStart()
                    .setValueWithClean(editMediaSidebar.getAppStoreURL(), url)
                    .setValueWithClean(editMediaSidebar.getBundleInput(), bundle)
                    .testEnd();
        } else {
            testStart()
                    .setValueWithClean(editMediaSidebar.getSiteURL(), url)
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
                .setValueWithClean(tableData.getSearch(), mediaName)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, mediaName)
                .waitSideBarOpened()
                .then("Check all fields")
                .validate(editMediaSidebar.getPublisherInput(), publisher.getName())
                .validateAttribute(editMediaSidebar.getNameInput(), "value", mediaName)
                .validate(editMediaSidebar.getMediaType(), mediaType)
                .testEnd();

        if (!mediaType.equals("PC Web") && (!mediaType.equals("Mobile Web"))) {
            testStart()
                    .validateAttribute(editMediaSidebar.getAppStoreURL(), "value", url)
                    .validateAttribute(editMediaSidebar.getBundleInput(), "value", bundle)
                    .testEnd();
        } else {
            testStart()
                    .validateAttribute(editMediaSidebar.getSiteURL(), "value", url)
                    .testEnd();
        }
        testStart()

                .and("Click Save")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), mediaPage.getToasterMessage().getPanelError())
                .and("Show column 'Site/App Store URL'")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .then("Validate data in table")
                .validate(tableData.getCellByRowValue(ColumnNames.STATUS, ColumnNames.MEDIA_NAME, mediaName), Statuses.ACTIVE.getStatus())
                .validate(tableData.getCellByRowValue(ColumnNames.PUBLISHER, ColumnNames.MEDIA_NAME, mediaName), publisher.getName())
                .validate(tableData.getCellByRowValue(ColumnNames.PLATFORM, ColumnNames.MEDIA_NAME, mediaName), mediaType)
                .validate(tableData.getCellByRowValue(ColumnNames.SITE_APP_STORE_URL, ColumnNames.MEDIA_NAME, mediaName), url)
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
