package rx.inventory.adspot;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.common.Currency;
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
import widgets.common.categories.CategoriesList;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import java.util.List;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class ChangingPublisherInSidebarTests extends BaseTest {
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar editAdSpotSidebar;
    private Media media1;
    private Media media2;
    private Publisher publisher1;
    private Publisher publisher2;

    final private static String DEFAULT_FLOOR_PRICE = "10";
    final private static String BANNER_FLOOR_PRICE = "12";
    final private static String VIDEO_FLOOR_PRICE = "15";
    final private static String NATIVE_FLOOR_PRICE = "18";
    private final static List<Integer> DSP_IDS_PUBLISHER = List.of(9, 11, 13);
    private final static List<Integer> CATEGORY_IDS = List.of(1, 9);

    final private static String BANNER_TEXT =
            "By changing the Publisher the form will be reset and the previous changes will not be saved.";

    public ChangingPublisherInSidebarTests() {
        adSpotPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeClass
    private void init() {
        publisher1 = publisher()
                .createNewPublisher(captionWithSuffix("0000autoPub1"), true,
                        Currency.JPY, CATEGORY_IDS, DSP_IDS_PUBLISHER)
                .build()
                .getPublisherResponse();

        publisher2 = publisher()
                .createNewPublisher(captionWithSuffix("0000autoPub2"), true,
                        Currency.EUR, CATEGORY_IDS, DSP_IDS_PUBLISHER)
                .build()
                .getPublisherResponse();

        media1 = media()
                .createNewMedia(captionWithSuffix("auto1Media"), publisher1.getId(), true)
                .build()
                .getMediaResponse();

        media2 = media()
                .createNewMedia(captionWithSuffix("auto2Media"), publisher2.getId(), true)
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


    @Test(description = "Change Publisher and check fields", alwaysRun = true)
    public void adSpotChangePublisher() {

        testStart()
                .clickBrowserRefreshButton()
                .and(String.format("Select Publisher '%s'", publisher1.getName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), publisher1.getName())
                .testEnd();

        fillGeneralFields(publisher1.getName(), media1.getName());
        fillBannerFormat();
        fillVideoFormat();
        fillNativeFormat();
        changePublisherAndClickAccept(publisher2.getName());
        validateFieldsAreCleanedAndRelatedMediaListIsCorrect(media2.getName());
        changePublisherAndClickAccept(publisher1.getName());
        validateFieldsAreCleanedAndRelatedMediaListIsCorrect(media1.getName());
        changePublisherAndClickAccept(publisher2.getName());
        validateFieldsAreCleanedAndRelatedMediaListIsCorrect(media2.getName());
        changePublisherAndClickAccept(publisher1.getName());
        validateFieldsAreCleanedAndRelatedMediaListIsCorrect(media1.getName());
        changePublisherAndClickCancel(publisher2.getName());
        validateFieldsValuesShouldNotBeChanged(publisher1.getName(), media1.getName());
        changePublisherAndClickAccept(publisher2.getName());
        validateFieldsAreCleanedAndRelatedMediaListIsCorrect(media2.getName());
        fillGeneralFields(publisher2.getName(), media2.getName());
        changePublisherAndClickCancel(publisher1.getName());
        validateFieldsValuesShouldNotBeChanged(publisher2.getName(), media2.getName());
    }


    @Step("Fill general fields")
    private void fillGeneralFields(String publisherName, String mediaName) {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        var categories = editAdSpotSidebar.getCategoriesPanel();
        testStart()
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), mediaName)
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), DEFAULT_FLOOR_PRICE)
                .clickOnWebElement(editAdSpotSidebar.getCategories())
                .clickOnWebElement(categories.getCategoryCheckbox(CategoriesList.EDUCATION))
                .clickOnWebElement(categories.getCategoryGroupIcon(CategoriesList.AUTOMOTIVE))
                .clickOnWebElement(categories.getCategoryCheckbox(CategoriesList.AUTO_REPAIR))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .testEnd();
    }

    @Step("Change Publisher on {0} and click Accept")
    private void changePublisherAndClickAccept(String publisherName) {
        var changePublisherBanner = editAdSpotSidebar.getChangePublisherBanner();

        testStart()
                .and(String.format("Select Publisher %s", publisherName))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), publisherName)
                .then("Check that warning banner appears")
                .validate(visible, changePublisherBanner.getBannerText(BANNER_TEXT))
                .then("Click 'Accept' on Warning Banner")
                .clickOnWebElement(changePublisherBanner.getAcceptButton(BANNER_TEXT))
                .testEnd();
    }

    @Step("Change Publisher on {0} and click Cancel")
    private void changePublisherAndClickCancel(String publisherName) {
        var changePublisherBanner = editAdSpotSidebar.getChangePublisherBanner();

        testStart()
                .and(String.format("Select Publisher %s", publisherName))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), publisherName)
                .then("Check that warning banner appears")
                .validate(visible, changePublisherBanner.getBannerText(BANNER_TEXT))
                .then("Click 'Accept' on Warning Banner")
                .clickOnWebElement(changePublisherBanner.getCancelButton(BANNER_TEXT))
                .testEnd();
    }

    @Step("Related media list should correspond Publisher and all fields should be cleaned")
    private void validateFieldsAreCleanedAndRelatedMediaListIsCorrect(String mediaName) {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var nativeCard = editAdSpotSidebar.getNativeCard();
        testStart()
                .then("Name should be cleaned")
                .validate(editAdSpotSidebar.getNameInput(), "")
                .then("Default Floor Price should be cleaned")
                .validate(editAdSpotSidebar.getDefaultFloorPrice(), "")
                .then("Default Floor Price should be cleaned")
                .validate(editAdSpotSidebar.getDefaultAdSizesInput(), "")
                .then("Categories should be cleaned")
                .validate(editAdSpotSidebar.getCategories().getText(), "")
                .then("Position should be cleaned")
                .validate(editAdSpotSidebar.getPositionInput().getText(), "")
                .validateAttribute(videoCard.getEnabledToggle(), "aria-checked", "false")
                .validate(videoCard.getVideoPlacementType(), "")
                .validate(videoCard.getVideoPlaybackMethods(), "")
                .validate(videoCard.getVideoFloorPrice(), "")
                .validate(videoCard.getMaxVideoDuration(), "")
                .validate(videoCard.getMinVideoDuration(), "")
                .validate(videoCard.getVideoAdSizes(), "")
                .validateAttribute(nativeCard.getEnabledToggle(), "aria-checked", "false")
                .validate(nativeCard.getFloorPrice(), "")
                .validateAttribute(bannerCard.getEnabledToggle(), "aria-checked", "false")
                .validate(bannerCard.getAdSizes(), "")
                .validate(bannerCard.getFloorPrice(), "")

                .and("Select Related Media")
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), mediaName)
                .testEnd();

    }

    @Step("Expand Banner Card and fill fields")
    private void fillBannerFormat() {
        var bannerCard = editAdSpotSidebar.getBannerCard();
        testStart()
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .turnToggleOn(bannerCard.getEnabledToggle())
                //  .clickOnWebElement(bannerCard.getAdSizes())
                //   .clickOnWebElement(bannerCard.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x60))
                .setValueWithClean(bannerCard.getFloorPrice(), BANNER_FLOOR_PRICE)
                .testEnd();
    }

    @Step("Expand Video Card and fill fields")
    private void fillVideoFormat() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        testStart()
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .turnToggleOn(videoCard.getEnabledToggle())
                .and("Fill Video Placement Type")
                .selectFromDropdown(videoCard.getVideoPlacementType(),
                        videoCard.getVideoPlacementTypeItems(), "In-Stream")
                .and("Fill Video Playback Method")
                .scrollIntoView(videoCard.getVideoPlaybackMethods())
                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
                        videoCard.getVideoPlaybackMethodsItems(), "Click Sound On")
                .setValueWithClean(videoCard.getVideoFloorPrice(), VIDEO_FLOOR_PRICE)
                .testEnd();

    }

    @Step("Expand Native Card and fill fields")
    private void fillNativeFormat() {
        var nativeCard = editAdSpotSidebar.getNativeCard();
        testStart()
                .clickOnWebElement(nativeCard.getNativeCardHeader())
                .turnToggleOn(nativeCard.getEnabledToggle())
                .setValueWithClean(nativeCard.getFloorPrice(), NATIVE_FLOOR_PRICE)
                .testEnd();
    }

    @Step("")
    private void validateFieldsValuesShouldNotBeChanged(String publisherName, String mediaName) {
        testStart()
                .validate(editAdSpotSidebar.getPublisherInput().getText(), publisherName)
                .validate(editAdSpotSidebar.getRelatedMediaInput().getText(), mediaName)
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
    private void deleteTestData() {
        deletePublisher(publisher1.getId());
        deletePublisher(publisher2.getId());
        deleteMedia(media1.getId());
        deleteMedia(media2.getId());

    }

    private void deletePublisher(int id) {
        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id)
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher1.getId()));

    }

    private void deleteMedia(int id) {
        if (media()
                .setCredentials(USER_FOR_DELETION)
                .deleteMedia(id)
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted media %s", id));

    }
}
