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
import widgets.errormessages.ErrorMessages;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import java.util.List;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disabled;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCheckFieldsTests extends BaseTest {
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar editAdSpotSidebar;
    private Media media1;
    private Media media2;

    public AdSpotCheckFieldsTests() {
        adSpotPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeClass
    private void init() {

        media1 = media()
                .createNewMedia(captionWithSuffix("auto1Media"))
                .build()
                .getMediaResponse();

        media2 = media()
                .createNewMedia(captionWithSuffix("auto2Media"))
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


    @Test(description = "Check fields by default", alwaysRun = true)
    private void checkDefaultFields() {
        testStart()
                .then("Validate fields by default")
                .validate(disabled, editAdSpotSidebar.getActiveToggle())
                .validateAttribute(editAdSpotSidebar.getActiveToggle(), "aria-checked", "true")
                .validate(visible, editAdSpotSidebar.getPublisherInput())
                .validate(editAdSpotSidebar.getPublisherInput(), "")
                .validate(disabled, editAdSpotSidebar.getCategoriesInput())
                .validate(disabled, editAdSpotSidebar.getNameInput())
                .validate(disabled, editAdSpotSidebar.getRelatedMediaInput())
                .validate(disabled, editAdSpotSidebar.getPositionInput())
                .validate(disabled, editAdSpotSidebar.getDefaultAdSizesInput())
                .validate(disabled, editAdSpotSidebar.getDefaultFloorPrice())
                .validate(disabled, editAdSpotSidebar.getTestModeToggle())
                .validate(disabled, editAdSpotSidebar.getContentForChildrenToggle())
                .validateAttribute(editAdSpotSidebar.getBannerCard().getBannerPanel(), "style", "display: none;")
                .validateAttribute(editAdSpotSidebar.getVideoCard().getVideoPanel(), "style", "display: none;")
                .validateAttribute(editAdSpotSidebar.getNativeCard().getNativePanel(), "style", "display: none;")
                .testEnd();
    }

    @Test(description = "Check required fields", alwaysRun = true)
    private void checkRequiredFields() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var nativeCard = editAdSpotSidebar.getNativeCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
        var adSpotName = captionWithSuffix("autoAdSpot");

        testStart()
                .and("Expand all cards")
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .clickOnWebElement(nativeCard.getNativeCardHeader())
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())

                .and("Click 'Save'")
                .scrollIntoView(editAdSpotSidebar.getSaveButton())
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate errors for all required fields in Error Panel")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .validateListSize(errorsList, 6)
                .validateList(errorsList, List.of(
                        ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.AD_SPOT_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.RELATED_MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.POSITION_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_AD_SIZE_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )
                .then("Validate error under the 'Publisher' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Publisher Name"))
                .validate(editAdSpotSidebar.getErrorAlertByFieldName("Publisher Name"),
                        ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText())
                .and(String.format("Select Publisher '%s'", media1.getPublisherName()))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), media1.getPublisherName())
                .then("Validate error under the 'Publisher field' disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Publisher Name"))
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Ad Spot Name' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Ad Spot Name"))
                .then("Validate error under the 'Related Media' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Related Media"))
                .then("Validate error under the 'Position' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Position"))
                .then("Validate error under the 'Default Ad size' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Ad Sizes"))
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validateListSize(errorsList, 5)
                .validateList(errorsList, List.of(
                        ErrorMessages.AD_SPOT_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.RELATED_MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.POSITION_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_AD_SIZE_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .then("Validate error under the 'Ad Spot field' disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Ad Spot Name"))
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Related Media' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Related Media"))
                .then("Validate error under the 'Position' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Position"))
                .then("Validate error under the 'Default Ad size' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Ad Sizes"))
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validateListSize(errorsList, 4)
                .validateList(errorsList, List.of(
                        ErrorMessages.RELATED_MEDIA_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.POSITION_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_AD_SIZE_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )

                .and("Select Related Media")
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), media1.getName())
                .then("Validate error under the 'Related Media field' disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Related Media"))
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Position' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Position"))
                .then("Validate error under the 'Default Ad size' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Ad Sizes"))
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validateListSize(errorsList, 3)
                .validateList(errorsList, List.of(
                        ErrorMessages.POSITION_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_AD_SIZE_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )

                .and("Select Position")
                .selectFromDropdown(editAdSpotSidebar.getPosition(),
                        editAdSpotSidebar.getPositionItems(), "Header")
                .then("Validate error under the 'Position field' disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Position"))
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Default Ad size' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Ad Sizes"))
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validateListSize(errorsList, 2)
                .validateList(errorsList, List.of(
                        ErrorMessages.DEFAULT_AD_SIZE_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )

                .and("Select Ad Size")
                .clickOnWebElement(editAdSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(editAdSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(AdSizesList.A120x20))
                .clickOnWebElement(editAdSpotSidebar.getNameInput())
                .then("Validate error under the 'Default Ad Sizes' field disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Ad Sizes"))
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.DEFAULT_FLOOR_PRICE_ERROR_ALERT.getText())
                )


                .and("Fill Default Floor Price")
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")
                .then("Validate error under the 'Default Floor Price' field disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .then("Validate errors disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Test(description = "Check Video Card fields", alwaysRun = true)
    private void checkVideoCardFields() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var nativeCard = editAdSpotSidebar.getNativeCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .and("Expand Video Card")
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .turnToggleOn(videoCard.getEnabledToggle())
                .turnToggleOn(bannerCard.getEnabledToggle())
                .turnToggleOn(nativeCard.getEnabledToggle())
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Video Placement Type' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Video Placement Type"))
                .then("Validate error under the 'Video Playback Methods' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Video Playback Methods"))
                .validateListSize(errorsList, 2)
                .validateList(errorsList, List.of(
                        ErrorMessages.VIDEO_PLACEMENT_TYPE_ERROR_ALERT.getText(),
                        ErrorMessages.VIDEO_PLAYBACK_METHOD_ERROR_ALERT.getText())
                )
                .and("Fill Video Placement Type")
                .selectFromDropdown(videoCard.getVideoPlacementType(),
                        videoCard.getVideoPlacementTypeItems(), "In-Stream")
                .then("Validate error under the 'Video Placement Type' field disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Video Placement Type"))
                .then("Validate error under the 'Video Playback Methods' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Video Playback Methods"))
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.VIDEO_PLAYBACK_METHOD_ERROR_ALERT.getText())
                )

                .and("Fill Video Playback Method")
                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
                        videoCard.getVideoPlaybackMethodsItems(), "Click Sound On")

                .then("Validate errors disappeared")
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Video Playback Methods"))
                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

// @Ignore
//    @Test(description = "Check Minimum Value Default Floor Price", alwaysRun = true)
//    private void checkMinValueDefaultFloorPrice() {
//        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
//
//        fillGeneralFields(media1.getPublisherName(), media1.getName());
//        testStart()
//                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "-1")
//                .and("Click 'Save'")
//                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
//                .then("Validate error under the 'Default Floor Price' field")
//                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
//                .validate(editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"),
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                .validateListSize(errorsList, 1)
//                .validateList(errorsList, List.of(
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                )
////                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")
////                .then("Validate error under the 'Default Floor Price' field disappeared")
////                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
////                .then("Validate errors disappeared")
////                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
////                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
//                .testEnd();
//    }

//    @Ignore
//    @Test(description = "Check Minimum Value Banner Floor Price", alwaysRun = true)
//    private void checkMinValueBannerFloorPrice() {
//        var bannerCard = editAdSpotSidebar.getBannerCard();
//        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
//
//        fillGeneralFields(media1.getPublisherName(), media1.getName());
//        testStart()
//                .clickOnWebElement(bannerCard.getBannerCardHeader())
//                .turnToggleOn(bannerCard.getEnabledToggle())
//                .setValueWithClean(bannerCard.getFloorPrice(), "-1")
//                .and("Click 'Save'")
//                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
//                .then("Validate error under the 'Floor Price' field")
//                .waitAndValidate(visible, bannerCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(bannerCard.getErrorAlertByFieldName("Floor Price"),
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                .validateListSize(errorsList, 1)
//                .validateList(errorsList, List.of(
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                )
////                .setValueWithClean(bannerCard.getFloorPrice(), "0")
////                .then("Validate error under the 'Floor Price' field disappeared")
////                .waitAndValidate(not(visible), bannerCard.getErrorAlertByFieldName("Floor Price"))
////                .then("Validate errors disappeared")
////                .waitAndValidate(not(visible), bannerCard.getErrorAlertByFieldName("Floor Price"))
////                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
//                .testEnd();
//    }

//    @Ignore
//    @Test(description = "Check Minimum Value Native Floor Price", alwaysRun = true)
//    private void checkMinValueNativeFloorPrice() {
//        var nativeCard = editAdSpotSidebar.getNativeCard();
//        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
//
//        fillGeneralFields(media1.getPublisherName(), media1.getName());
//        testStart()
//                .clickOnWebElement(nativeCard.getNativeCardHeader())
//                .turnToggleOn(nativeCard.getEnabledToggle())
//                .setValueWithClean(nativeCard.getFloorPrice(), "-1")
//                .and("Click 'Save'")
//                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
//                .then("Validate error under the 'Floor Price' field")
//                .waitAndValidate(visible, nativeCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(nativeCard.getErrorAlertByFieldName("Floor Price"),
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                .validateListSize(errorsList, 1)
//                .validateList(errorsList, List.of(
//                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
//                )
////                .setValueWithClean(nativeCard.getFloorPrice(), "0")
////                .then("Validate error under the 'Floor Price' field disappeared")
////                .waitAndValidate(not(visible), nativeCard.getErrorAlertByFieldName("Floor Price"))
////                .then("Validate errors disappeared")
////                .waitAndValidate(not(visible), nativeCard.getErrorAlertByFieldName("Floor Price"))
////                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
//                .testEnd();
//    }

    @Test(description = "Check Minimum Value Video Floor Price", alwaysRun = true)
    private void checkMinValueVideoFloorPrice() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .turnToggleOn(videoCard.getEnabledToggle())
                .and("Fill Video Placement Type")
                .selectFromDropdown(videoCard.getVideoPlacementType(),
                        videoCard.getVideoPlacementTypeItems(), "In-Stream")
                .and("Fill Video Playback Method")
                .scrollIntoView(videoCard.getVideoPlaybackMethods())
                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
                        videoCard.getVideoPlaybackMethodsItems(), "Click Sound On")
                .setValueWithClean(videoCard.getVideoFloorPrice(), "-1")
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Floor Price' field")
                .waitAndValidate(visible, videoCard.getErrorAlertByFieldName("Floor Price"))
                .validate(videoCard.getErrorAlertByFieldName("Floor Price"),
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                )
//                .setValueWithClean(videoCard.getVideoFloorPrice(), "0")
//                .then("Validate error under the 'Floor Price' field disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Floor Price"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Test(description = "Check Maximum Value Default Floor Price", alwaysRun = true)
    private void checkMaxValueDefaultFloorPrice() {
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "1000000")
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Default Floor Price' field")
                .waitAndValidate(visible, editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
                .validate(editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"),
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                )
//                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "")
//                .setValueWithClean(editAdSpotSidebar.getDefaultFloorPrice(), "0")
//                .then("Validate error under the 'Default Floor Price' field disappeared")
//                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlertByFieldName("Default Floor Price"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Test(description = "Check Maximum Value Banner Floor Price", alwaysRun = true)
    private void checkMaxValueBannerFloorPrice() {
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .turnToggleOn(bannerCard.getEnabledToggle())
                .setValueWithClean(bannerCard.getFloorPrice(), "1000000")
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Floor Price' field")
                .waitAndValidate(visible, bannerCard.getErrorAlertByFieldName("Floor Price"))
                .validate(bannerCard.getErrorAlertByFieldName("Floor Price"),
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                )
//                .setValueWithClean(bannerCard.getFloorPrice(), "0")
//                .then("Validate error under the 'Floor Price' field disappeared")
//                .waitAndValidate(not(visible), bannerCard.getErrorAlertByFieldName("Floor Price"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), bannerCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Test(description = "Check Maximum Value Native Floor Price", alwaysRun = true)
    private void checkMaxValueNativeFloorPrice() {
        var nativeCard = editAdSpotSidebar.getNativeCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .clickOnWebElement(nativeCard.getNativeCardHeader())
                .turnToggleOn(nativeCard.getEnabledToggle())
                .setValueWithClean(nativeCard.getFloorPrice(), "1000000")
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Floor Price' field")
                .waitAndValidate(visible, nativeCard.getErrorAlertByFieldName("Floor Price"))
                .validate(nativeCard.getErrorAlertByFieldName("Floor Price"),
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                )
//                .setValueWithClean(nativeCard.getFloorPrice(), "0")
//                .then("Validate error under the 'Floor Price' field disappeared")
//                .waitAndValidate(not(visible), nativeCard.getErrorAlertByFieldName("Floor Price"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), nativeCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }

    @Test(description = "Check Maximum Value Video Floor Price", alwaysRun = true)
    private void checkMaxValueVideoFloorPrice() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();

        fillGeneralFields(media1.getPublisherName(), media1.getName());
        testStart()
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .turnToggleOn(videoCard.getEnabledToggle())
                .and("Fill Video Placement Type")
                .selectFromDropdown(videoCard.getVideoPlacementType(),
                        videoCard.getVideoPlacementTypeItems(), "In-Stream")
                .and("Fill Video Playback Method")
                .scrollIntoView(videoCard.getVideoPlaybackMethods())
                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
                        videoCard.getVideoPlaybackMethodsItems(), "Click Sound On")
                .setValueWithClean(videoCard.getVideoFloorPrice(), "1000000")
                .and("Click 'Save'")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .then("Validate error under the 'Floor Price' field")
                .waitAndValidate(visible, videoCard.getErrorAlertByFieldName("Floor Price"))
                .validate(videoCard.getErrorAlertByFieldName("Floor Price"),
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.MIN_MAX_VALUE_AD_SPOT_FLOOR_PRICE.getText())
                )
//                .setValueWithClean(videoCard.getVideoFloorPrice(), "0")
//                .then("Validate error under the 'Floor Price' field disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Floor Price"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Floor Price"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .testEnd();
    }
//@Ignore
//    @Test(description = "Check Minimum Video Duration Value", alwaysRun = true)
//    private void checkMinVideoDurationValue() {
//        var videoCard = editAdSpotSidebar.getVideoCard();
//        var errorsList = editAdSpotSidebar.getErrorAlert().getErrorsList();
//
//        fillGeneralFields(media1.getPublisherName(), media1.getName());
//        testStart()
//                .clickOnWebElement(videoCard.getVideoCardHeader())
//                .turnToggleOn(videoCard.getEnabledToggle())
//                .and("Fill Video Placement Type")
//                .selectFromDropdown(videoCard.getVideoPlacementType(),
//                        videoCard.getVideoPlacementTypeItems(), "In-Stream")
//                .and("Fill Video Playback Method")
//                .scrollIntoView(videoCard.getVideoPlaybackMethods())
//                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
//                        videoCard.getVideoPlaybackMethodsItems(), "Click Sound On")
//                .clickOnText("Video")
//                .setValueWithClean(videoCard.getMinVideoDuration(),"-1")
//                .clickOnText("Banner")
//                .then("Validate error under the 'Minimum Video Duration' field")
////                .waitAndValidate(visible, videoCard.getErrorAlertByFieldName("Minimum Video Duration (seconds)"))
////                .validate(videoCard.getErrorAlertByFieldName("Minimum Video Duration (seconds)"),
////                        ErrorMessages.DURATION_ERROR_ALERT.getText())
//                .validateListSize(errorsList, 1)
//                .clickOnText("Banner")
//                .validateList(errorsList, List.of(
//                        ErrorMessages.DURATION_ERROR_ALERT.getText())
//                )
//                .setValueWithClean(videoCard.getVideoFloorPrice(), "0")
//                .then("Validate error under the 'Minimum Video Duration (seconds)' field disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Minimum Video Duration (seconds)"))
//                .then("Validate errors disappeared")
//                .waitAndValidate(not(visible), videoCard.getErrorAlertByFieldName("Minimum Video Duration (seconds)"))
//                .validate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
//                .testEnd();
//    }
//
//    @Ignore
//    @Test(description = "Switch Publisher and Accept reseted fields", alwaysRun = true)
//    public void switchPublisherAccept(){
//        var changePublisherBanner = editAdSpotSidebar.getChangePublisherBanner();
//
//        fillGeneralFields(media1.getPublisherName(), media1.getName());
//        testStart()
//                .and(String. format("Select Publisher %s",media2.getPublisherName()))
//                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
//                editAdSpotSidebar.getPublisherItems(), media2.getPublisherName())
//                .then("Check that warning banner appears")
//                .validate(visible,changePublisherBanner.getChangePublisherLabel())
//                .then("Click 'Accept' on Warning Banner")
//                .clickOnWebElement(changePublisherBanner.getAcceptButton())
//                .and("Select Related Media")
//                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
//                editAdSpotSidebar.getRelatedMediaItems(), media2.getName())
//                .testEnd();
//    }
//

    @Step("Fill general fields")
    private void fillGeneralFields(String publisherName, String mediaName) {
        var adSpotName = captionWithSuffix("4autoAdSpot");
        testStart()
                .and(String.format("Select Publisher '%s'", publisherName))
                .selectFromDropdown(editAdSpotSidebar.getPublisherInput(),
                        editAdSpotSidebar.getPublisherItems(), publisherName)
                .and("Fill Ad Spot Name")
                .setValueWithClean(editAdSpotSidebar.getNameInput(), adSpotName)
                .selectFromDropdown(editAdSpotSidebar.getRelatedMedia(),
                        editAdSpotSidebar.getRelatedMediaItems(), mediaName)
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
                .deleteMedia(media1.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted media %s", media1.getId()));

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(media1.getPublisherId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", media1.getPublisherId()));
    }
}
