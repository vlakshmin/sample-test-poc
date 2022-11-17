package rx.inventory.adspot;

import api.dto.rx.admin.publisher.Publisher;
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
import widgets.common.detailsmenu.menu.TableItemDetailsMenu;
import widgets.common.detailsmenu.menu.sections.DetailsSection;
import widgets.common.table.ColumnNames;
import widgets.common.table.TableData;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

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
public class AdSpotBannerCardCreateTests extends BaseTest {
    private Media media;
    private Publisher publisher;
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar adSpotSidebar;
    private TableItemDetailsMenu adSpotTableDetailsMenu;

    final private static String POSITION = "Header";
    final private static String DEFAULT_FLOOR_PRICE = "10.98";

    final private static AdSizesList DEFAULT_AD_SIZE = AdSizesList.A300x1050;

    final private static String DEFAULT_VALUE = "Same as default";

    final private static String AD_SPOT_NAME = captionWithSuffix("4autoAdSpot");

    public AdSpotBannerCardCreateTests() {
        adSpotPage = new AdSpotsPage();
        adSpotSidebar = new EditAdSpotSidebar();
        adSpotTableDetailsMenu = new TableItemDetailsMenu();
    }

    @BeforeClass
    private void init() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("000000autoPub1"))
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
    private void createAdSpotWithBannerCardOnlyFields() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var tablePagination = adSpotPage.getAdSpotsTable().getTablePagination();

        fillGeneralRequeredFields();
        fillBannerCardFields();

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

    @Test(description = "Check details info: Native", dependsOnMethods = "createAdSpotWithBannerCardOnlyFields")
    private void checkInfoNativePanel() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var nativeDetailsSection = adSpotTableDetailsMenu.getNativeDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(nativeDetailsSection, "Inactive");

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Banner Ad Size", dependsOnMethods = "createAdSpotWithBannerCardOnlyFields")
    private void checkInfoBannerAdSize() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var bannerDetailsSection = adSpotTableDetailsMenu.getBannerAdSizeDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(bannerDetailsSection, DEFAULT_VALUE);

        removeMouseCursorFromDetailsIcon();
    }

    @Test(description = "Check details info: Banner Floor Price", dependsOnMethods = "createAdSpotWithBannerCardOnlyFields")
    private void checkInfoBannerFloorPrice() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var bannerDetailsSection = adSpotTableDetailsMenu.getBannerFloorPriceDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(bannerDetailsSection, DEFAULT_VALUE);

        removeMouseCursorFromDetailsIcon();
    }


    @Test(description = "Check details info: Video", dependsOnMethods = "createAdSpotWithBannerCardOnlyFields")
    private void checkInfoVideo() {
        var tableData = adSpotPage.getAdSpotsTable().getTableData();
        var videoFloorPriceDetailsSection = adSpotTableDetailsMenu.getVideoDetailsSection();

        hoverMouseCursorOnDetailsIcon(tableData);

        verifySelectionInDetailsMenuForTableItem(videoFloorPriceDetailsSection, "Inactive");

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
    private void fillGeneralRequeredFields() {
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
                .clickOnWebElement(adSpotSidebar.getNameInput())
                .testEnd();
    }

    @Step("Fill Banner card fields")
    private void fillBannerCardFields() {
        var bannerCard = adSpotSidebar.getBannerCard();
        testStart()
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .turnToggleOn(bannerCard.getEnabledToggle())
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateGeneralFieldsValues() {
        var categories = adSpotSidebar.getCategoriesPanel();

        testStart()
                .then("")
                .validateAttribute(adSpotSidebar.getActiveToggle(), "aria-checked", "true")
                .validateAttribute(adSpotSidebar.getContentForChildrenToggle(), "aria-checked", "false")
                .validateAttribute(adSpotSidebar.getTestModeToggle(), "aria-checked", "false")
                .validate(adSpotSidebar.getPublisherInput().getText(), media.getPublisherName())
                .validate(adSpotSidebar.getNameInput().getText(), AD_SPOT_NAME)
                .validate(adSpotSidebar.getRelatedMedia().getText(), media.getName())
                .validate(adSpotSidebar.getDefaultAdSizes().getText(), DEFAULT_AD_SIZE.getSize())
                .validateAttribute(adSpotSidebar.getDefaultFloorPrice(), "value", DEFAULT_FLOOR_PRICE)
                .validate(adSpotSidebar.getDefaultFloorPriceCurrency().getText(), publisher.getCurrency())
                .validate(categories.getCategoriesSelectedItems().size(), 0)

                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateBannerFieldsValues() {
        var bannerCard = adSpotSidebar.getBannerCard();

        testStart()
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .validateAttribute(bannerCard.getEnabledToggle(), "aria-checked", "true")
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
                .validate(nativeCard.getFloorPriceField().getFloorPricePrefix().getText(), publisher.getCurrency())
                .testEnd();
    }

    @Step("validateGeneralFieldsValues")
    private void validateVideoFieldsValues() {
        var videoCard = adSpotSidebar.getVideoCard();

        testStart()
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())
                .validateAttribute(videoCard.getEnabledToggle(), "aria-checked", "false")
                .validate(videoCard.getVideoAdSizes().getText(), "")
                .validateAttribute(videoCard.getFloorPriceField().getFloorPriceInput(), "value", "")

                .validate(videoCard.getVideoPlacementType().getText(), "")
                .validate(videoCard.getVideoPlaybackMethodsSelectedItems().size(), 0)
                .validateAttribute(videoCard.getFloorPriceField().getFloorPriceInput(), "value", "")
                .validate(videoCard.getFloorPriceField().getFloorPricePrefix().getText(), publisher.getCurrency())
                .validateAttribute(videoCard.getMinVideoDuration(), "value", "No Limit")
                .validateAttribute(videoCard.getMaxVideoDuration(), "value", "No Limit")
                .testEnd();
    }

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
