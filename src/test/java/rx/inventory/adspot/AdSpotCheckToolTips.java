package rx.inventory.adspot;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.tooltip.AdSpotTooltipText;
import widgets.inventory.adSpots.sidebar.AdSpotSidebarElements;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCheckToolTips extends BaseTest {
    private AdSpotsPage adSpotsPage;
    private EditAdSpotSidebar editAdSpotSidebar;

    @BeforeClass
    private void login(){
        adSpotsPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .clickOnWebElement(adSpotsPage.getCreateAdSpotButton())
                .waitSideBarOpened()
                .clickOnWebElement(editAdSpotSidebar.getBannerCardHeader())
                .clickOnWebElement(editAdSpotSidebar.getNativeCardHeader())
                .scrollIntoView(editAdSpotSidebar.getVideoCardHeader())
                .clickOnWebElement(editAdSpotSidebar.getVideoCardHeader())
                .testEnd();
    }

    @Test
    private void categoriesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipCategories(),
                AdSpotTooltipText.CATEGORIES.getText());
    }

    @Test
    private void contentForChildrenTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipContentForChildren(),
                AdSpotTooltipText.CONTENT_FOR_CHILDREN.getText());
    }

    @Test
    private void defaultAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipDefaultAdSizes(),
                AdSpotTooltipText.DEFAULT_AD_SIZES.getText());
    }

    @Test
    private void defaultFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipDefaultFloorPrice(),
                AdSpotTooltipText.DEFAULT_FLOOR_PRICE.getText());
    }

    @Test
    private void bannerAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipBannerAdSizes(),
                AdSpotTooltipText.BANNER_AD_SIZE.getText());
    }

    @Test
    private void bannerFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipBannerFloorPrice(),
                AdSpotTooltipText.BANNER_FLOOR_PRICE.getText());
    }

    @Test
    private void nativeFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipNativeFloorPrice(),
                AdSpotTooltipText.NATIVE_FLOOR_PRICE.getText());
    }

    @Test
    private void videoFloorPriceTooltip(){
       verifyTooltip(editAdSpotSidebar.getTooltipVideoFloorPrice(),
               AdSpotTooltipText.VIDEO_FLOOR_PRICE.getText());
    }

    @Test
    private void videoAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipVideoAdSizes(),
                AdSpotTooltipText.VIDEO_AD_SIZE.getText());
    }

    @Step
    private void verifyTooltip(SelenideElement field, String expectedText){
        testStart()
                .validateTooltip(field,
                        AdSpotSidebarElements.TOOLTIP_PLACEHOLDER.getSelector(), expectedText)
                .testEnd();
    }


    @AfterClass
    private void logout(){
        testStart()
                .clickOnWebElement(editAdSpotSidebar.getCloseIcon())
                .waitSideBarClosed()
                .logOut()
                .testEnd();
    }

}
