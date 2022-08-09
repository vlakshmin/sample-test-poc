package rx.inventory.media;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.tooltip.MediaTooltipText;
import widgets.inventory.adSpots.sidebar.AdSpotSidebarElements;
import widgets.inventory.media.sidebar.EditMediaSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckToolTips extends BaseTest {
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;

    @BeforeClass
    private void init() {
        mediaPage = new MediaPage();
        editMediaSidebar = new EditMediaSidebar();
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

//    @Test(description = "'Categories' Tooltip Text")
//    private void categoriesTooltip(){
//        verifyTooltip(editMediaSidebar.getTooltipCategories(),
//                MediaTooltipText.CATEGORIES.getText());
//    }
//
//    @Test(description = "'Site URL' Tooltip Text")
//    private void siteURLTooltip(){
//        verifyTooltip(editMediaSidebar.getTooltipContentForChildren(),
//                MediaTooltipText.SITE_URL.getText());
//    }
//
//    @Test(description = "'Bundle' Tooltip Text")
//    private void bundleTooltip(){
//        verifyTooltip(editMediaSidebar.getTooltipDefaultAdSizes(),
//                MediaTooltipText.BUNDLE.getText());
//    }
//
//    @Test(description = "'App Store URL' Tooltip Text")
//    private void appStoreURLTooltip(){
//        verifyTooltip(editMediaSidebar.getTooltipDefaultFloorPrice(),
//                MediaTooltipText.APP_STORE_URL.getText());
//    }

    @Step("Verify Tooltip Text")
    private void verifyTooltip(SelenideElement field, String expectedText){
        testStart()
                .scrollIntoView(field)
                .validateTooltip(field,
                        AdSpotSidebarElements.TOOLTIP_PLACEHOLDER.getSelector(), expectedText)
                .testEnd();
    }

 //   @AfterMethod
//    private void logout(){
//        testStart()
//                .clickOnWebElement(editMediaSidebar.getCloseIcon())
//                .waitSideBarClosed()
//                .logOut()
//                .testEnd();
//    }

}
