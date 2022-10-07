package rx.protections;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import io.qameta.allure.Step;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneNameImpl;
import widgets.protections.sidebar.CreateProtectionSidebar;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.AdSpotPrecondition.adSpot;
import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsInventoryMultipaneTests extends BaseTest {
    private Publisher publisherEmpty;
    private Publisher publisherActive;
    private Publisher publisherInactive;
    private List<Media> mediaActiveList;
    private List<Media> mediaInactiveList;
    private List<AdSpot> adSpotActiveList_0;
    private List<AdSpot> adSpotInactiveList_0;
    private List<AdSpot> adSpotActiveListInactiveMedia_0;
    private List<AdSpot> adSpotInactiveListInactiveMedia_0;

    private List<AdSpot> adSpotActiveList_1;
    private List<AdSpot> adSpotInactiveList_1;

    private ProtectionsPage protectionsPage;
    private CreateProtectionSidebar protectionSidebar;
    private Multipane protectionMultipane;

    private final static Integer MEDIA_ACTIVE_COUNT = 5;
    private final static Integer MEDIA_INACTIVE_COUNT = 3;

    private final static Integer AD_SPOT_ACTIVE_COUNT = 4;
    private final static Integer AD_SPOT_INACTIVE_COUNT = 2;

    public ProtectionsInventoryMultipaneTests() {
        protectionsPage = new ProtectionsPage();
        protectionSidebar = new CreateProtectionSidebar();
        protectionMultipane = new Multipane(MultipaneNameImpl.INVENTORY);

        mediaActiveList = new ArrayList<>();
        mediaInactiveList = new ArrayList<>();
    }

    @BeforeClass
    private void login() {

        publisherEmpty = createPublisher(captionWithSuffix("00autoPubProtections_empty"), true);
        publisherActive = createPublisher(captionWithSuffix("00autoPubProtections_active"), true);
        publisherInactive = createPublisher(captionWithSuffix("00autoPubProtections_inactive"), false);

        mediaActiveList = createMedia(publisherActive, true, MEDIA_ACTIVE_COUNT);
        mediaInactiveList = createMedia(publisherActive, false, MEDIA_INACTIVE_COUNT);

        adSpotActiveList_0 = createAdSpot(mediaActiveList.get(0), true, AD_SPOT_ACTIVE_COUNT);
        adSpotInactiveList_0 = createAdSpot(mediaActiveList.get(0), false, AD_SPOT_INACTIVE_COUNT);

        adSpotActiveList_1 = createAdSpot(mediaActiveList.get(1), true, AD_SPOT_ACTIVE_COUNT);
        adSpotInactiveList_1 = createAdSpot(mediaActiveList.get(1), false, AD_SPOT_INACTIVE_COUNT);

        adSpotActiveListInactiveMedia_0 = createAdSpot(mediaInactiveList.get(0), true, AD_SPOT_ACTIVE_COUNT);
        adSpotInactiveListInactiveMedia_0 = createAdSpot(mediaInactiveList.get(0), false, AD_SPOT_INACTIVE_COUNT);

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionsPage.getNuxtProgress())
                .and("Press 'Create Protection' button")
                .clickOnWebElement(protectionsPage.getCreateProtectionButton())
                .waitSideBarOpened()
                .testEnd();
    }

    @Test(description = "Check Empty Inventory Items List", priority = 0)
    private void checkEmptyInventoryList() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherEmpty.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherEmpty.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .then("Validate inventory items list should be empty")
                .validate(protectionMultipane.countSelectTableItems(), 0)
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Media Inventory Items List", priority = 1)
    private void checkInventoryList() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .then("Validate inventory items list should be empty")
                .validate(protectionMultipane.countSelectTableItems(), MEDIA_ACTIVE_COUNT)
                .testEnd();

        validateMediaList(mediaActiveList);

        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Ad Spots Inventory Items List", priority = 2)
    private void checkAdSpotInventoryList() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();

        validateAdSpotsList(mediaActiveList.get(0), adSpotActiveList_0);
        validateAdSpotsList(mediaActiveList.get(1), adSpotActiveList_1);
        validateAdSpotsCount(mediaActiveList.get(2).getName(), 0);
        validateAdSpotsCount(mediaActiveList.get(3).getName(), 0);


        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Media Inventory Items List without Ad Spots should be empty", priority = 3)
    private void checkAdSpotInventoryListWithoutAdSpots() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();

        validateAdSpotsCount(mediaActiveList.get(2).getName(), 0);
        validateAdSpotsCount(mediaActiveList.get(3).getName(), 0);
        validateAdSpotsCount(mediaActiveList.get(4).getName(), 0);


        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Media Inventory Inactive Items List", priority = 4)
    private void checkMediaInactiveInventoryList() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Set 'Show Inactive' toggle ON")
                .turnToggleOn(protectionMultipane.getShowInactive())
                .then("Validate item list includes inactive inventory")
                .validate(protectionMultipane.countSelectTableItems(), MEDIA_ACTIVE_COUNT + MEDIA_INACTIVE_COUNT)
                .testEnd();
        validateMediaList(mediaActiveList);
        validateInactiveMediaList(mediaInactiveList);
        testStart()
                .and("Set 'Show Inactive' toggle OFF")
                .turnToggleOff(protectionMultipane.getShowInactive())
                .then("Validate item list includes only active inventory")
                .validate(protectionMultipane.countSelectTableItems(), MEDIA_ACTIVE_COUNT)
                .testEnd();
        validateMediaList(mediaActiveList);


        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check AdSpots Inventory Inactive Items List (Media is Active)", priority = 5)
    private void checkAdSpotsInactiveInventoryListActiveMedia() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Set 'Show Inactive' toggle ON")
                .turnToggleOn(protectionMultipane.getShowInactive())
                .testEnd();
        validateAdSpotsCount(mediaActiveList.get(0).getName(),AD_SPOT_INACTIVE_COUNT+AD_SPOT_ACTIVE_COUNT);
        validateInactiveAdSpotsList(mediaActiveList.get(0), adSpotInactiveList_0);
        testStart()
                .and("Set 'Show Inactive' toggle OFF")
                .turnToggleOff(protectionMultipane.getShowInactive())
                .testEnd();
        validateAdSpotsCount(mediaActiveList.get(0).getName(),AD_SPOT_ACTIVE_COUNT);


        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check AdSpots Inventory Inactive Items List (Media is Inactive)", priority = 6)
    private void checkAdSpotsInactiveInventoryListInactiveMedia() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Set 'Show Inactive' toggle ON")
                .turnToggleOn(protectionMultipane.getShowInactive())
                .testEnd();
        validateAdSpotsCount(mediaInactiveList.get(0).getName(),AD_SPOT_INACTIVE_COUNT+AD_SPOT_ACTIVE_COUNT);
        validateInactiveAdSpotsList(mediaInactiveList.get(0), adSpotInactiveListInactiveMedia_0);

        testStart()
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Search Active Media", priority = 7)
    private void checkSearchActiveMedia() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and(String.format("Search inventory by Name = %s",mediaActiveList.get(0).getName()))
                .setValueWithClean(protectionMultipane.getSearchInput(), mediaActiveList.get(0).getName())
                .clickEnterButton(protectionMultipane.getSearchInput())
                .and("Set 'Show Inactive' toggle ON")
                .turnToggleOn(protectionMultipane.getShowInactive())
                .then("Validate item list includes inactive inventory")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(0).getName(), mediaActiveList.get(0).getName())
                .and("Set 'Show Inactive' toggle OFF")
                .turnToggleOff(protectionMultipane.getShowInactive())
                .then("Validate item list includes only active inventory")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(0).getName(), mediaActiveList.get(0).getName())
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Search Inactive Media", priority = 8)
    private void checkSearchInactiveMedia() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Search inventory by Name")
                .setValueWithClean(protectionMultipane.getSearchInput(), mediaInactiveList.get(0).getName())
                .and("Set 'Show Inactive' toggle ON")
                .turnToggleOn(protectionMultipane.getShowInactive())
                .then("Validate item list includes inactive inventory")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(0).getName(), String.format("(Inactive) %s", mediaInactiveList.get(0).getName()))
                .and("Set 'Show Inactive' toggle OFF")
                .turnToggleOff(protectionMultipane.getShowInactive())
                .then("Validate item list includes only active inventory")
                .validate(protectionMultipane.countSelectTableItems(), 0)
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    //:TODO
    @Ignore
    @Test(description = "Check Search Active Ad Spot", priority = 9)
    private void checkSearchActiveAdSpot() {

        testStart()
                .and(String.format("Select Publisher without Inventory '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and(String.format("Search inventory by Name = %s",adSpotActiveList_0.get(0).getName()))
                .setValueWithClean(protectionMultipane.getSearchInput(), adSpotActiveList_0.get(0).getName())
                .clickEnterButton(protectionMultipane.getSearchInput())
                .then("Validate item list")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(0).getName(), mediaActiveList.get(0).getName())
                .validate(protectionMultipane.getSelectTableItemByPositionInList(1).getName(), adSpotActiveList_0.get(0).getName())
                .and("Set 'Show Inactive' toggle OFF")
                .turnToggleOff(protectionMultipane.getShowInactive())
                .then("Validate item list includes only active inventory")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(1).getName(), adSpotActiveList_0.get(0).getName())
                .and("Collapse 'Inventory' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void logout() {

        testStart()
                .clickOnWebElement(protectionSidebar.getCloseIcon())
                .waitSideBarClosed()
                .and("Logout")
                .logOut()
                .testEnd();

        deleteTestData();
    }

    private Publisher createPublisher(String name, Boolean isEnabled) {

        return publisher()
                .createNewPublisher(name, isEnabled)
                .build()
                .getPublisherResponse();
    }

    private List<Media> createMedia(Publisher publisher, Boolean isEnabled, Integer count) {
        List<Media> list = new ArrayList<>();

        while (list.size() < count) {

            list.add(media()
                    .createNewMedia("autoMedia", publisher.getId(), isEnabled)
                    .build()
                    .getMediaResponse());
        }

        return list;
    }

    private List<AdSpot> createAdSpot(Media media, Boolean isEnabled, Integer count) {
        List<AdSpot> list = new ArrayList<>();

        while (list.size() < count) {

            list.add(adSpot()
                    .createNewAdSpot(captionWithSuffix("autoAdSpot"), media.getPublisherId(), media.getId(), isEnabled)
                    .build()
                    .getAdSpotResponse());
        }

        return list;
    }

    @Step("Validate Inventory List")
    private void validateMediaList(List<Media> list) {

        for (int i = 0; i < list.size(); i++) {
            testStart()
                    .validate(protectionMultipane.getSelectTableItemByName(list.get(0).getName()).getName(),
                            list.get(0).getName())
                    .testEnd();
        }
    }

    @Step("Validate Inactive Inventory List")
    private void validateInactiveMediaList(List<Media> list) {

        for (int i = 0; i < list.size(); i++) {
            testStart()
                    .validate(protectionMultipane.getSelectTableItemByName(list.get(0).getName()).getName(),
                            String.format("(Inactive) %s", list.get(0).getName()))
                    .testEnd();
        }
    }

    @Step("Validate ad spot count for media {0}")
    private void validateAdSpotsCount(String mediaName, Integer count) {
        testStart()
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(mediaName).getName())
                .validate(protectionMultipane.countSelectChildTableItems(),  count)
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(mediaName).getName())
                .testEnd();
    }

    @Step("Validate ad spot list for media {0}")
    private void validateAdSpotsList(Media media, List<AdSpot> adSpotList) {

        testStart()
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(media.getName()).getName())
                .testEnd();

        for (int i = 0; i < adSpotList.size(); i++) {
            testStart()
                    .validate(protectionMultipane.getSelectChildTableItemByName(adSpotList.get(0).getName()).getName(),
                            adSpotList.get(0).getName())
                    .testEnd();
        }

        testStart()
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(media.getName()).getName())
                .testEnd();
    }

    @Step("Validate inactive ad spot list for media {0}")
    private void validateInactiveAdSpotsList(Media media, List<AdSpot> adSpotList) {

        testStart()
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(media.getName()).getName())
                .testEnd();

        for (int i = 0; i < adSpotList.size(); i++) {
            testStart()
                    .validate(protectionMultipane.getSelectChildTableItemByName(adSpotList.get(0).getName()).getName(),
                            String.format("(Inactive) %s",adSpotList.get(0).getName()))
                    .testEnd();
        }

        testStart()
                .clickOnWebElement(protectionMultipane.getSelectTableItemByName(media.getName()).getName())
                .testEnd();
    }

    private void deleteTestData() {

        deletePublisher(publisherEmpty.getId());
        deletePublisher(publisherActive.getId());
        deletePublisher(publisherInactive.getId());

        deleteMedia();


    }

    private void deletePublisher(Integer id) {

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id)
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
            //  log.info(String.format("Deleted publisher %s",id));
        }

    }

    private void deleteMedia() {

        for (Media media : mediaActiveList) {
            if (media()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteMedia(media.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                //  log.info(String.format("Deleted publisher %s",media.getId()));
            }
        }

        for (Media media : mediaInactiveList) {
            if (media()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteMedia(media.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                //  log.info(String.format("Deleted publisher %s",media.getId()));
            }
        }
    }

}
