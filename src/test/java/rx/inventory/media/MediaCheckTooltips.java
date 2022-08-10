package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.tooltip.MediaTooltipText;
import widgets.inventory.media.sidebar.*;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckTooltips extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private MediaTooltipSidebar mediaTooltipSidebar;
    private Publisher publisher;

    public MediaCheckTooltips() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
        mediaTooltipSidebar = new MediaTooltipSidebar();
    }

    @BeforeClass
    private void init() {
        publisher = publisher()
                .createNewPublisher(captionWithSuffix("1autoPub"))
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
                .selectFromDropdown(editMediaSidebar.getPublisherInput(),
                        editMediaSidebar.getPublisherItems(), publisher.getName())
                .testEnd();
    }

    @Test(description = "'Categories' Tooltip Text")
    private void categoriesTooltip() {
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.ANDROID.getName(),
                MediaTooltipText.CATEGORIES.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.IOS.getName(),
                MediaTooltipText.CATEGORIES.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.MOBILE_WEB.getName(),
                MediaTooltipText.CATEGORIES.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.PC_WEB.getName(),
                MediaTooltipText.CATEGORIES.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.IOS_WEB_VIEW.getName(),
                MediaTooltipText.CATEGORIES.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipCategories(), MediaTypes.ANDROID_WEB_VIEW.getName(),
                MediaTooltipText.CATEGORIES.getText());
    }

    @Test(description = "'Site URL' Tooltip Text")
    private void siteURLTooltip() {
        verifyTooltip(mediaTooltipSidebar.getTooltipSiteURL(), MediaTypes.MOBILE_WEB.getName(),
                MediaTooltipText.SITE_URL.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipSiteURL(), MediaTypes.PC_WEB.getName(),
                MediaTooltipText.SITE_URL.getText());
    }

    //TODO: GS-2439
//    @Test(description = "'Bundle' Tooltip Text")
//    private void bundleTooltip(){
//        verifyTooltip(mediaTooltipSidebar.getTooltipBundle(), MediaTypes.ANDROID.getName(),
//                MediaTooltipText.BUNDLE.getText());
//        verifyTooltip(mediaTooltipSidebar.getTooltipBundle(), MediaTypes.IOS.getName(),
//                MediaTooltipText.BUNDLE.getText());
//        verifyTooltip(mediaTooltipSidebar.getTooltipBundle(), MediaTypes.IOS_WEB_VIEW.getName(),
//                MediaTooltipText.BUNDLE.getText());
//        verifyTooltip(mediaTooltipSidebar.getTooltipBundle(), MediaTypes.ANDROID_WEB_VIEW.getName(),
//                MediaTooltipText.BUNDLE.getText());
//    }

    @Test(description = "'App Store URL' Tooltip Text")
    private void appStoreURLTooltip() {
        verifyTooltip(mediaTooltipSidebar.getTooltipAppStoreURL(), MediaTypes.ANDROID.getName(),
                MediaTooltipText.APP_STORE_URL.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipAppStoreURL(), MediaTypes.IOS.getName(),
                MediaTooltipText.APP_STORE_URL.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipAppStoreURL(), MediaTypes.IOS_WEB_VIEW.getName(),
                MediaTooltipText.APP_STORE_URL.getText());
        verifyTooltip(mediaTooltipSidebar.getTooltipAppStoreURL(), MediaTypes.ANDROID_WEB_VIEW.getName(),
                MediaTooltipText.APP_STORE_URL.getText());
    }

    @Step("Verify Tooltip Text")
    private void verifyTooltip(SelenideElement field, String mediaType, String expectedText) {
        testStart()
                .selectFromDropdown(editMediaSidebar.getMediaTypeInput(),
                        editMediaSidebar.getMediaTypeItems(), mediaType)
                .scrollIntoView(field)
                .validateTooltip(field,
                        MediaTooltipSidebarElements.TOOLTIP_PLACEHOLDER.getSelector(), expectedText)
                .testEnd();
    }

    @AfterMethod
    private void logout() {
        testStart()
                .clickOnWebElement(editMediaSidebar.getCloseIcon())
                .waitSideBarClosed()
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
