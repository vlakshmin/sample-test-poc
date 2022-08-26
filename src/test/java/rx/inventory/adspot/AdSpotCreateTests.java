package rx.inventory.adspot;

import api.dto.rx.inventory.media.Media;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.adSizes.AdSizesList;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCreateTests extends BaseTest {
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar editAdSpotSidebar;
    private Media media;

    public AdSpotCreateTests() {
        adSpotPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeClass
    private void init() {

        media = media()
                .createNewMedia(captionWithSuffix("autoMedia"))
                .build()
                .getMediaResponse();
    }

    @BeforeMethod
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotPage.getNuxtProgress())
                .clickOnWebElement(adSpotPage.getCreateAdSpotButton())
                .waitSideBarOpened()
                .testEnd();
    }

    @Test(description = "Create Ad Spot with all filled fields", alwaysRun = true)
    private void a(){
        var videoCard = editAdSpotSidebar.getVideoCard();
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var nativeCard = editAdSpotSidebar.getNativeCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
        var adSpotName = captionWithSuffix("autoAdSpot");


    }

    @Step("Fill general fields")
    private void fillGeneralFields() {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        testStart()
                .and(String.format("Select Publisher '%s'", media.getPublisherName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), media.getPublisherName())
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), media.getName())
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")


                .testEnd();
    }

    @Step("Fill Banner card fields")
    private void fillBannerCardFields() {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        testStart()
                .and(String.format("Select Publisher '%s'", media.getPublisherName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), media.getPublisherName())
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), media.getName())
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")


                .testEnd();
    }

    @Step("Fill Native card fields")
    private void fillNativeCardFields() {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        testStart()
                .and(String.format("Select Publisher '%s'", media.getPublisherName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), media.getPublisherName())
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), media.getName())
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")

                .testEnd();
    }

    @Step("Fill Video card fields")
    private void fillVideoCardFields() {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        testStart()
                .and(String.format("Select Publisher '%s'", media.getPublisherName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), media.getPublisherName())
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), media.getName())
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")

                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    private void logout() {
        testStart()
                .and("Close Ad Spot Sidebar")
                .clickOnWebElement(editAdSpotSidebar.getCloseIcon())
                .waitSideBarClosed()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deletePublisher() {

        if (media()
                .setCredentials(USER_FOR_DELETION)
                .deleteMedia(media.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted media %s", media.getId()));

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(media.getPublisherId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", media.getPublisherId()));
    }
}
