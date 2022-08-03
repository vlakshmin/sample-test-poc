package rx.inventory.adspot;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
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
    private void init() {
        adSpotsPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeMethod
    private void login() {
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

    @Test(description = "'Categories' Tooltip Text")
    private void categoriesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipCategories(),
                AdSpotTooltipText.CATEGORIES.getText());
    }

    @Test(description = "'Content for Children' Tooltip Text")
    private void contentForChildrenTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipContentForChildren(),
                AdSpotTooltipText.CONTENT_FOR_CHILDREN.getText());
    }

    @Test(description = "'Default Ad Sizes' Tooltip Text")
    private void defaultAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipDefaultAdSizes(),
                AdSpotTooltipText.DEFAULT_AD_SIZES.getText());
    }

    @Test(description = "'Default Floor Price' Tooltip Text")
    private void defaultFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipDefaultFloorPrice(),
                AdSpotTooltipText.DEFAULT_FLOOR_PRICE.getText());
    }

    @Test(description = "'Native Card. 'Floor Price' Tooltip Text'")
    private void nativeFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipNativeFloorPrice(),
                AdSpotTooltipText.NATIVE_FLOOR_PRICE.getText());
    }

    @Test(description = "Banner Card. 'Ad Sizes' Tooltip Text")
    private void bannerAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipBannerAdSizes(),
                AdSpotTooltipText.BANNER_AD_SIZE.getText());
    }

    @Test(description = "Banner Card. 'Floor Price' Tooltip Text")
    private void bannerFloorPriceTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipBannerFloorPrice(),
                AdSpotTooltipText.BANNER_FLOOR_PRICE.getText());
    }

    @Test(description = "Video Card. 'Floor Price' Tooltip Text")
    private void videoFloorPriceTooltip(){
       verifyTooltip(editAdSpotSidebar.getTooltipVideoFloorPrice(),
               AdSpotTooltipText.VIDEO_FLOOR_PRICE.getText());
    }

    @Test(description = "Video Card. 'Ad Sizes' Tooltip Text")
    private void videoAdSizesTooltip(){
        verifyTooltip(editAdSpotSidebar.getTooltipVideoAdSizes(),
                AdSpotTooltipText.VIDEO_AD_SIZE.getText());
    }

    @Step("Verify Tooltip Text")
    private void verifyTooltip(SelenideElement field, String expectedText){
        testStart()
                .scrollIntoView(field)
                .validateTooltip(field,
                        AdSpotSidebarElements.TOOLTIP_PLACEHOLDER.getSelector(), expectedText)
                .testEnd();
    }

    @AfterMethod
    private void logout(){
        testStart()
                .clickOnWebElement(editAdSpotSidebar.getCloseIcon())
                .waitSideBarClosed()
                .logOut()
                .testEnd();
    }

}
