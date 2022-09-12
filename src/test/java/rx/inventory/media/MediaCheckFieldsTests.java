package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.*;
import rx.BaseTest;
import widgets.errormessages.ErrorMessages;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import widgets.inventory.media.sidebar.MediaTypes;

import java.util.List;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckFieldsTests extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private Publisher publisher;

    private static String URL = "https://play.google.com/store/apps/";
    private static String BUNDLE = "com.viber.voip";

    public MediaCheckFieldsTests() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    private void init() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("00000002autoPub1"))
                .build()
                .getPublisherResponse();
    }

    @BeforeMethod
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .clickOnWebElement(mediaPage.getCreateMediaButton())
                .waitSideBarOpened()
                .testEnd();
    }

    @Test(description = "Check fields by default")
    private void checkDefaultFields() {
        testStart()
                .then("Validate fields by default")
                .validate(disabled, editMediaSidebar.getActiveToggle())
                .validateAttribute(editMediaSidebar.getActiveToggle(), "aria-checked", "true")
                .validate(visible, editMediaSidebar.getPublisherInput())
                .validate(editMediaSidebar.getPublisherInput(), "")
                .validate(disabled, editMediaSidebar.getMediaTypeInput())
                .validate(editMediaSidebar.getMediaTypeInput(), "")
                .validate(disabled, editMediaSidebar.getNameInput())
                .validate(editMediaSidebar.getNameInput(), "")
                .validate(disabled, editMediaSidebar.getSiteURL())
                .validate(editMediaSidebar.getSiteURL(), "")
                .validate(disabled, editMediaSidebar.getCategoriesInput())
                .testEnd();
    }

    @Test(description = "Check required fields")
    private void checkRequiredFields() {
        var errorsList = editMediaSidebar.getErrorAlert().getErrorsList();

        testStart()
                .then("Validate required fields")
                .and("Click 'Save' with all empty fields")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then("Validate errors for all required fields in Error Panel")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlert().getErrorPanel())
                .validateListSize(errorsList, 4)
                .validateList(errorsList, List.of(
                        ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText())
                )
                .then("Validate error under the 'Publisher' field")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Publisher Name"), ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText())
                .and(String.format("Select Publisher '%s'", publisher.getName()))
                .selectFromDropdown(editMediaSidebar.getPublisherInput(),
                        editMediaSidebar.getPublisherItems(), publisher.getName())
                .then("Validate error under the 'Publisher field' disappeared")
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlertByFieldName("Publisher Name"))
                .and("Click 'Save'")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then("Validate errors for 3 required fields in Error Panel (Media Name, Media Type, Site URL)")
                .validateListSize(errorsList, 3)
                .validateList(errorsList, List.of(
                        ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText())
                )
                .then("Validate error under the 'Media Name' field")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName("Media Name"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Media Name"), ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText())
                .then("Validate error under the 'Media Type' field")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName("Media Type"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Media Type"), ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText())
                .then("Validate error under the 'Site URL' field")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Site URL"), ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText())
                .and(String.format("Fill Name with value %s", "mediaName"))
                .setValueWithClean(editMediaSidebar.getNameInput(), "mediaName")
                .then("Validate error under the 'Media Name' disappeared")
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlertByFieldName("Media Name"))
                .then("Validate errors for 3 required fields in Error Panel (Media Type, Site URL)")
                .validateListSize(errorsList, 2)
                .validateList(errorsList, List.of(
                        ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText())
                )
                .and("Select Media Type 'Android'")
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), MediaTypes.ANDROID.getName())
                .then("Validate error under the 'Media Type' disappeared")
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlertByFieldName("Media Type"))
                .then("Validate errors for 1 required field in Error Panel (App Store URL)")
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.APP_STORE_URL_REQUIRED_ERROR_ALERT.getText())
                )
                .and("Select Media Type 'Mobile Web'")
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), MediaTypes.MOBILE_WEB.getName())
                .then("Validate errors for 1 required field in Error Panel (Site URL)")
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText())
                )
                .and(String.format("Fill 'Site URL' field with wrong value '%s'", "wrong url"))
                .setValueWithClean(editMediaSidebar.getSiteURL(), "wrong url")
                .then("Validate errors in Error Panel that 'Site URL' value is wrong")
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.SITE_URL_ERROR_ALERT.getText())
                )
                .then("Validate error under the field that 'Site URL' value is wrong")
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Site URL"), ErrorMessages.SITE_URL_ERROR_ALERT.getText())
                .and(String.format("Fill 'Site URL' field with valid value '%s'", URL))
                .setValueWithClean(editMediaSidebar.getSiteURL(), URL)
                .then("Validate errors disappeared")
                .waitAndValidate(not(visible), editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(not(visible), editMediaSidebar.getErrorAlert().getErrorPanel())

                .testEnd();
    }

    @Test(description = "Check errors then switch Media type")
    private void switchMediaTypeAndCheckError() {
        var errorsList = editMediaSidebar.getErrorAlert().getErrorsList();
        var appStoreURLErrorMsg = ErrorMessages.APP_STORE_URL_REQUIRED_ERROR_ALERT.getText();
        var siteURLErrorMsg = ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText();

        testStart()
                .and(String.format("Select Publisher '%s'", publisher.getName()))
                .selectFromDropdown(editMediaSidebar.getPublisherInput(),
                        editMediaSidebar.getPublisherItems(), publisher.getName())
                .and(String.format("Fill Name with value '%s'", "mediaName"))
                .setValueWithClean(editMediaSidebar.getNameInput(), "mediaName")
                .testEnd();

        changeMediaTypeAndValidateErrors(MediaTypes.ANDROID.getName(), appStoreURLErrorMsg, "App Store URL");
        changeMediaTypeAndValidateErrors(MediaTypes.ANDROID_WEB_VIEW.getName(), appStoreURLErrorMsg, "App Store URL");
        changeMediaTypeAndValidateErrors(MediaTypes.PC_WEB.getName(), siteURLErrorMsg, "Site URL");
        changeMediaTypeAndValidateErrors(MediaTypes.IOS.getName(), appStoreURLErrorMsg, "App Store URL");
        changeMediaTypeAndValidateErrors(MediaTypes.MOBILE_WEB.getName(), siteURLErrorMsg, "Site URL");
        changeMediaTypeAndValidateErrors(MediaTypes.IOS_WEB_VIEW.getName(), appStoreURLErrorMsg, "App Store URL");
    }

    @Step("Change Media Type {0}")
    private void changeMediaTypeAndValidateErrors(String mediaType, String errorMsg, String fieldName) {
        var errorsList = editMediaSidebar.getErrorAlert().getErrorsList();

        testStart()
                .and(String.format("Select Media Type '%s'", mediaType))
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), mediaType)
                .and("Click 'Save'")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then(String.format("Validate Error Message appeared in Error panel :'%s'", errorMsg))
                .validateList(errorsList, List.of(errorMsg))
                .then(String.format("Validate Error Message is '%s' appeared under the field '%s'",
                        errorMsg, errorMsg))
                .waitAndValidate(visible, editMediaSidebar.getErrorAlertByFieldName(fieldName))
                .validate(editMediaSidebar.getErrorAlertByFieldName(fieldName), errorMsg)
                .testEnd();
    }

    @AfterMethod
    private void logout() {
        testStart()
                .and("Close Media Sidebar")
                .clickOnWebElement(editMediaSidebar.getCloseIcon())
                .waitSideBarClosed()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass
    private void deletePublisher() {
        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(publisher.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher.getId()));
    }

}
