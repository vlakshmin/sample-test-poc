package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.errormessages.ErrorMessages;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import widgets.inventory.media.sidebar.MediaTypes;

import java.util.ArrayList;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckFields extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private Publisher publisher;

    private static String URL = "https://play.google.com/store/apps/";
    private static String BUNDLE = "com.viber.voip";

    public MediaCheckFields() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
    }

    @BeforeClass
    private void init() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("1autoPub1"))
                .build()
                .getPublisherResponse();
    }

    @BeforeMethod
    private void login(){

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
    private void checkDefaultFields(){
        testStart()
                .then("Validate fields by default")
                .validate(disabled,editMediaSidebar.getActiveToggle())
                .validateAttribute(editMediaSidebar.getActiveToggle(),"aria-checked","true")
                .validate(visible,editMediaSidebar.getPublisherInput())
                .validate(editMediaSidebar.getPublisherInput(),"")
                .validate(disabled,editMediaSidebar.getMediaTypeInput())
                .validate(editMediaSidebar.getMediaTypeInput(),"")
                .validate(disabled,editMediaSidebar.getNameInput())
                .validate(editMediaSidebar.getNameInput(),"")
                .validate(disabled,editMediaSidebar.getSiteURL())
                .validate(editMediaSidebar.getSiteURL(),"")
                .validate(disabled,editMediaSidebar.getCategoriesInput())
                .testEnd();
    }

    @Test(description = "Check required fields")
    private void checkRequiredFields(){
        var errorsList = editMediaSidebar.getErrorAlert().getErrorsList();

        testStart()
                .then("Validate required fields")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .waitAndValidate(visible, editMediaSidebar.getErrorAlert().getErrorPanel())
     //           .validate(editMediaSidebar.getErrorAlert().getHeaderError(),"The form has the following validation errors:")
                .validateListSize(errorsList,4)
                .validateList(errorsList,  new ArrayList<>() {
                            {
                                add(ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText());
                                add(ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText());
                                add(ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText());
                                add(ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText());
                            }
                        })
                .waitAndValidate(visible,editMediaSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Publisher Name"),ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText())
                .selectFromDropdown(editMediaSidebar.getPublisherInput(),
                        editMediaSidebar.getPublisherItems(), publisher.getName())
                .waitAndValidate(not(visible),editMediaSidebar.getErrorAlertByFieldName("Publisher Name"))
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .validateListSize(errorsList,3)
                .validateList(errorsList,  new ArrayList<>() {
                    {
                        add(ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText());
                        add(ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText());
                        add(ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText());
                    }
                })
                .waitAndValidate(visible,editMediaSidebar.getErrorAlertByFieldName("Media Name"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Media Name"),ErrorMessages.MEDIA_NAME_ERROR_ALERT.getText())
                .waitAndValidate(visible,editMediaSidebar.getErrorAlertByFieldName("Media Type"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Media Type"),ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText())
                .waitAndValidate(visible,editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Media Type"),ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText())

                .and("Fill Name")
                .setValueWithClean(editMediaSidebar.getNameInput(), "mediaName")
                .waitAndValidate(not(visible),editMediaSidebar.getErrorAlertByFieldName("Media Name"))
                .validateListSize(errorsList,2)
                .validateList(errorsList,  new ArrayList<>() {
                    {
                        add(ErrorMessages.MEDIA_TYPE_ERROR_ALERT.getText());
                        add(ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText());
                    }
                })
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), MediaTypes.ANDROID.getName())
                .waitAndValidate(not(visible),editMediaSidebar.getErrorAlertByFieldName("Media Type"))
                .validateListSize(errorsList,1)
                .validateList(errorsList,  new ArrayList<String>() {
                    {
                        add(ErrorMessages.APP_STORE_URL_REQUIRED_ERROR_ALERT.getText());
                    }
                })
                .selectFromDropdown(editMediaSidebar.getMediaType(),
                        editMediaSidebar.getMediaTypeItems(), MediaTypes.MOBILE_WEB.getName())
                .validateListSize(errorsList,1)
                .validateList(errorsList,  new ArrayList<String>() {
                    {
                        add(ErrorMessages.SITE_URL_REQUIRED_ERROR_ALERT.getText());
                    }
                })
                .setValueWithClean(editMediaSidebar.getSiteURL(), "wrong url")
                .validateListSize(errorsList,1)
                .validateList(errorsList,  new ArrayList<>() {
                    {
                        add(ErrorMessages.SITE_URL_ERROR_ALERT.getText());
                    }
                })
                .waitAndValidate(visible,editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(editMediaSidebar.getErrorAlertByFieldName("Site URL"),ErrorMessages.SITE_URL_ERROR_ALERT.getText())
                .setValueWithClean(editMediaSidebar.getSiteURL(), URL)
                .waitAndValidate(not(visible),editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validate(not(visible),editMediaSidebar.getErrorAlert().getErrorPanel())

                .testEnd();
    }

    @Test(description = "Check errors then switch Media type")
    private void switchMediaTypeAndCheckError(){

    }

    @AfterMethod
    private void logout() {
        testStart()
                .and("Close Media Sidebar")
                .clickOnWebElement(editMediaSidebar.getCloseIcon())
                .waitSideBarClosed()
                .logOut()
                .testEnd();

    }

}
