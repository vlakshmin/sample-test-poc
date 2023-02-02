package rx.inventory.adspot;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.common.Currency;
import api.dto.rx.inventory.media.Media;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.adSizes.AdSizesList;
import widgets.common.categories.CategoriesList;
import widgets.common.detailsmenu.menu.TableItemDetailsMenu;
import widgets.common.detailsmenu.menu.sections.DetailsSection;
import widgets.common.table.ColumnNames;
import widgets.common.table.TableData;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static java.lang.String.format;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotVideoCardCreateTests extends BaseTest {
    private Media media;
    private Publisher publisher;
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar adSpotSidebar;
    private TableItemDetailsMenu adSpotTableDetailsMenu;

    final private static String POSITION = "Header";
    final private static String DEFAULT_FLOOR_PRICE = "10.98";

    final private static String VIDEO_PLACEMENT_TYPE = "In-Stream";
    final private static String VIDEO_PLAYBACK_METHOD = "Click Sound On";

    final private static String DEFAULT_VALUE = "Same as default";

    final private static AdSizesList DEFAULT_AD_SIZE = AdSizesList.A300x1050;

    final private static Currency CURRENCY = Currency.JPY;

    final private static CategoriesList CATEGORY_EDUCATION = CategoriesList.EDUCATION;
    final private static CategoriesList CATEGORY_AUTO_REPAIR = CategoriesList.AUTO_REPAIR;

    final private static String AD_SPOT_NAME = captionWithSuffix("4autoAdSpot");

    public AdSpotVideoCardCreateTests() {
        adSpotPage = new AdSpotsPage();
        adSpotSidebar = new EditAdSpotSidebar();
        adSpotTableDetailsMenu = new TableItemDetailsMenu();
    }

    @BeforeClass
    private void init() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("0000autoPub1"), true, CURRENCY, List.of(), List.of())
                .build()
                .getPublisherResponse();

        media = media()
                .createNewMedia(captionWithSuffix("autoMedia"), publisher.getId(), true)
                .build()
                .getMediaResponse();

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotPage.getNuxtProgress())
                .clickOnWebElement(adSpotPage.getCreateAdSpotButton())
                .waitSideBarOpened()
                .testEnd();
    }

    @Test(description = "Create Ad Spot with all filled fields")
    private void createAdSpotWithAllFields() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var tablePagination = adSpotPage.getAdSpotsTable().getTablePagination();

        fillGeneralFields();
        fillVideoCardFields();
        testStart()
                .clickOnWebElement(adSpotSidebar.getSaveButton())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), adSpotPage.getToasterMessage().getPanelError())
                .and("Search new media")
                .setValueWithClean(tableData.getSearch(), AD_SPOT_NAME)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .clickOnTableCellLink(tableData, ColumnNames.AD_SPOT_NAME, AD_SPOT_NAME)
                .waitSideBarOpened()
                .testEnd();

        validateGeneralFieldsValues();
        validateBannerFieldsValues();
        validateNativeFieldsValues();
        validateVideoFieldsValues();

        testStart()
                .clickOnWebElement(adSpotSidebar.getSaveButton())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), adSpotPage.getToasterMessage().getPanelError())
                .testEnd();
    }

    @Test(description = "Check details info: Native", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoNativePanel() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var nativeDetailsSection = adSpotTableDetailsMenu.getNativeDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(nativeDetailsSection, "Inactive");

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Banner", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoBanner() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var bannerDetailsSection = adSpotTableDetailsMenu.getBannerDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(bannerDetailsSection, "Inactive");

        removeMouseCursorFromDetailsIcon();
    }


    @Test(description = "Check details info: Video Floor Price", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoVideoFloorPrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoFloorPriceDetailsSection = adSpotTableDetailsMenu.getVideoFloorPriceDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoFloorPriceDetailsSection, DEFAULT_VALUE);

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Video Min Duration", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoMinDurationPrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoMinDurationDetailsSection = adSpotTableDetailsMenu.getVideoMinDurationDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoMinDurationDetailsSection, "No Limit");

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Video Max Duration", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoMaxDurationPrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoMaxDurationDetailsSection = adSpotTableDetailsMenu.getVideoMaxDurationDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoMaxDurationDetailsSection, "No Limit");

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Video Playback Method", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoPlaybackMethodPrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoPlaybackMethodDetailsSection = adSpotTableDetailsMenu.getVideoPlaybackMethodDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoPlaybackMethodDetailsSection, VIDEO_PLAYBACK_METHOD);

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Video Placement Type Method", dependsOnMethods = "createAdSpotWithAllFields")
    private void checkInfoPlacementTypePrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoPlacementTypeDetailsSection = adSpotTableDetailsMenu.getVideoPlacementTypeDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoPlacementTypeDetailsSection, VIDEO_PLACEMENT_TYPE);

        removeMouseCursorFromDetailsIcon();
    }

    private void hoverMouseCursorOnDetailsIcon(TableData tableData) {
        testStart()
                .and("Hovering mouse cursor on 'Details' column in Pricing Table")
                .hoverMouseOnWebElement(tableData.getCellByPositionInTable(ColumnNames.DETAILS, 0))
                .testEnd();
    }


    private void verifySelectionInDetailsMenuForTableItem(DetailsSection detailsSection, String... expectedItemNames) {

        AtomicInteger currentItemPosition = new AtomicInteger();

        Stream.of(expectedItemNames).forEach(item -> {
            testStart()
                    .then(format("Check that %s with name '%s' is presented in 'Details' Menu on %s position",
                            detailsSection.getDetailsSection().getName(), item, currentItemPosition))
                    .validate(visible, detailsSection.getMenuItemByPositionInList(currentItemPosition.get()).getName())
                    .validate(detailsSection.getMenuItemByPositionInList(currentItemPosition.get()).getName(),
                            expectedItemNames[currentItemPosition.get()])
                    .testEnd();
            currentItemPosition.getAndIncrement();
        });
    }

    @Step("Remove mouse cursor from details icon")
    private void removeMouseCursorFromDetailsIcon() {

        testStart()
                .clickOnWebElement(adSpotPage.getPageTitle())
                .testEnd();
    }

    @Step("Fill general field")
    private void fillGeneralFields() {
        var categories = adSpotSidebar.getCategoriesPanel();
        testStart()
                .and(String.format("Select Publisher '%s'", media.getPublisherName()))
                .selectFromDropdown(adSpotSidebar.getPublisherInput(),
                        adSpotSidebar.getPublisherItems(), media.getPublisherName())
                .and("Fill Ad Spot Name")
                .setValueWithClean(adSpotSidebar.getNameInput(), AD_SPOT_NAME)
                .selectFromDropdown(adSpotSidebar.getRelatedMedia(),
                        adSpotSidebar.getRelatedMediaItems(), media.getName())
                .selectFromDropdown(adSpotSidebar.getPosition(),
                        adSpotSidebar.getPositionItems(), POSITION)
                .clickOnWebElement(adSpotSidebar.getDefaultAdSizes())
                .clickOnWebElement(adSpotSidebar.getAdSizesPanel().getAdSizeCheckbox(DEFAULT_AD_SIZE))
                .clickOnWebElement(adSpotSidebar.getNameInput())
                .setValueWithClean(adSpotSidebar.getDefaultFloorPrice(), DEFAULT_FLOOR_PRICE)
                .clickOnWebElement(adSpotSidebar.getCategories())
                .clickOnWebElement(categories.getCategoryCheckbox(CATEGORY_EDUCATION))
                .clickOnWebElement(categories.getCategoryGroupIcon(CategoriesList.AUTOMOTIVE))
                .clickOnWebElement(categories.getCategoryCheckbox(CATEGORY_AUTO_REPAIR))
                .clickOnWebElement(adSpotSidebar.getNameInput())
                .turnToggleOn(adSpotSidebar.getContentForChildrenToggle())
                .turnToggleOn(adSpotSidebar.getTestModeToggle())
                .testEnd();
    }

    @Step("Fill Video card fields")
    private void fillVideoCardFields() {
        var videoCard = adSpotSidebar.getVideoCard();
        testStart()
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .turnToggleOn(videoCard.getEnabledToggle())
                .and("Fill Video Placement Type")
                .selectFromDropdown(videoCard.getVideoPlacementType(),
                        videoCard.getVideoPlacementTypeItems(), VIDEO_PLACEMENT_TYPE)
                .and("Fill Video Playback Method")
                .scrollIntoView(videoCard.getVideoPlaybackMethods())
                .selectFromDropdown(videoCard.getVideoPlaybackMethods(),
                        videoCard.getVideoPlaybackMethodsItems(), VIDEO_PLAYBACK_METHOD)
                .clickOnWebElement(videoCard.getVideoPanel())
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateGeneralFieldsValues() {
        var categories = adSpotSidebar.getCategoriesPanel();
        testStart()
                .then("")
                .validateAttribute(adSpotSidebar.getActiveToggle(), "aria-checked", "true")
                .validateAttribute(adSpotSidebar.getContentForChildrenToggle(), "aria-checked", "true")
                .validateAttribute(adSpotSidebar.getTestModeToggle(), "aria-checked", "true")
                .validate(adSpotSidebar.getPublisherInput().getText(), media.getPublisherName())
                .validate(adSpotSidebar.getNameInput().getText(), AD_SPOT_NAME)
                .validate(adSpotSidebar.getRelatedMedia().getText(), media.getName())
                .validate(adSpotSidebar.getDefaultAdSizes().getText(), DEFAULT_AD_SIZE.getSize())
                .validateAttribute(adSpotSidebar.getDefaultFloorPrice(), "value", DEFAULT_FLOOR_PRICE)

                .validate(adSpotSidebar.getDefaultFloorPriceCurrency().getText(), publisher.getCurrency())
                .validateList(categories.getCategoriesSelectedItems(), List.of(CATEGORY_AUTO_REPAIR.getName(),
                        CategoriesList.EDUCATION.getName()))
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateBannerFieldsValues() {
        var bannerCard = adSpotSidebar.getBannerCard();

        testStart()
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .validateAttribute(bannerCard.getEnabledToggle(), "aria-checked", "false")
                .validate(bannerCard.getAdSizes().getText(), "")
                .validateAttribute(bannerCard.getFloorPriceField().getFloorPriceInput(), "value", "")
                .validate(bannerCard.getFloorPriceField().getFloorPricePrefix().getText(), publisher.getCurrency())
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateNativeFieldsValues() {
        var nativeCard = adSpotSidebar.getNativeCard();

        testStart()
                .clickOnWebElement(nativeCard.getNativeCardHeader())
                .validateAttribute(nativeCard.getEnabledToggle(), "aria-checked", "false")
                .validateAttribute(nativeCard.getFloorPriceField().getFloorPriceInput(), "value", "")
                .validate(nativeCard.getFloorPriceField().getFloorPricePrefix().getText(), publisher.getCurrency())
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateVideoFieldsValues() {
        var videoCard = adSpotSidebar.getVideoCard();

        testStart()
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .validateAttribute(videoCard.getEnabledToggle(), "aria-checked", "true")
                .validate(videoCard.getVideoAdSizes().getText(), "")
                .validate(videoCard.getVideoPlacementType().getText(), VIDEO_PLACEMENT_TYPE)
                .validateList(videoCard.getVideoPlaybackMethodsSelectedItems(), List.of(VIDEO_PLAYBACK_METHOD))
                .validateAttribute(videoCard.getFloorPriceField().getFloorPriceInput(), "value", "")
                .validate(videoCard.getFloorPriceField().getFloorPricePrefix().getText(), publisher.getCurrency())
                .validateAttribute(videoCard.getMinVideoDuration(), "value", "No Limit")
                .validateAttribute(videoCard.getMaxVideoDuration(), "value", "No Limit")
                .testEnd();
    }

    @Step("Logout")
    private void logout() {
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deletePublisher() {

        logout();
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