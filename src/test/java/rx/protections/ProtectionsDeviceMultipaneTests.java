package rx.protections;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.device.Device;
import api.dto.rx.inventory.adspot.AdSpot;
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
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import rx.enums.MultipaneConstants;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneNameImpl;
import widgets.protections.sidebar.CreateProtectionSidebar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static api.preconditionbuilders.AdSpotPrecondition.adSpot;
import static api.preconditionbuilders.DevicePrecondition.device;
import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsDeviceMultipaneTests extends BaseTest {
    private Publisher publisherEmpty;
    private Publisher publisherActive;
    private Publisher publisherInactive;
    private List<Device> deviceList;

    private ProtectionsPage protectionsPage;
    private CreateProtectionSidebar protectionSidebar;
    private Multipane protectionMultipane;

    public ProtectionsDeviceMultipaneTests() {

        protectionsPage = new ProtectionsPage();
        protectionSidebar = new CreateProtectionSidebar();
        protectionMultipane = new Multipane(MultipaneNameImpl.DEVICE);
    }

    @BeforeClass
    private void login() {

        publisherEmpty = createPublisher(captionWithSuffix("00autoPubProtections_empty"), true);
        publisherActive = createPublisher(captionWithSuffix("00autoPubProtections_active"), true);
        publisherInactive = createPublisher(captionWithSuffix("00autoPubProtections_inactive"), false);

        deviceList = getDevices();

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

    @Test(description = "Check Device Items List is filled and disabled by default", priority = 0)
    private void checkDefaultDeviceList() {

        testStart()
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .then("Validate device items list should not be empty")
                .validate(protectionMultipane.countSelectTableItems(), deviceList.size())
                .validate(disabled, protectionMultipane.getSearchInput())
                .validate(disabled, protectionMultipane.getIncludeAllButton())
                .and("Collapse 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Device Items List", priority = 1)
    private void checkDeviceList() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .then("Validate device items list should not be empty")
                .validate(protectionMultipane.countSelectTableItems(), deviceList.size())
                .testEnd();

        validateDeviceList(deviceList);

        testStart()
                .and("Collapse 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Search Device", priority = 2)
    private void checkSearchDevice() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and(String.format("Search device by Name = %s",deviceList.get(0).getName()))
                .setValueWithClean(protectionMultipane.getSearchInput(), deviceList.get(0).getName())
                .clickEnterButton(protectionMultipane.getSearchInput())
                .then("Validate item list includes device")
                .validate(protectionMultipane.countSelectTableItems(), 1)
                .validate(protectionMultipane.getSelectTableItemByPositionInList(0).getName(), deviceList.get(0).getName())
                .and("Clear Search field")
                .setValueWithClean(protectionMultipane.getSearchInput(),"")
                .clickEnterButton(protectionMultipane.getSearchInput())
                .validate(protectionMultipane.countSelectTableItems(), deviceList.size())
                .testEnd();

        validateDeviceList(deviceList);

        testStart()
                .and("Collapse 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Multipane Text 'Include All'", priority = 3)
    private void checkMultipaneText() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .then("Validate text above items panel")
                .validate(protectionMultipane.getSelectionInfoExcludedLabel().getText(),
                        "")
                .and("Include All media")
                .clickOnWebElement(protectionMultipane.getIncludeAllButton())
                .then("Validate 'Selected' items panel")
                .validate(protectionMultipane.countSelectTableItems(), protectionMultipane.countIncludedExcludedTableItems())
                .then("Validate text above items panel")
                .validate(protectionMultipane.getSelectionInfoExcludedLabel().getText(),
                        MultipaneConstants.DEVICES_ARE_INCLUDED.setQuantity(deviceList.size()))
                .and("Collapse 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Multipane Text 'Clear All'", priority = 4)
    private void checkMultipaneTextClearAll() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Include All devices")
                .clickOnWebElement(protectionMultipane.getIncludeAllButton())
                .validate(protectionMultipane.countIncludedExcludedTableItems(), deviceList.size())
                .validate(protectionMultipane.countSelectTableItems(), deviceList.size())
                .and("Clear All devices")
                .clickOnWebElement(protectionMultipane.getClearAllButton())
                .validate(protectionMultipane.getSelectionInfoExcludedLabel().getText(), MultipaneConstants.ALL_DEVICES_INCLUDED.setQuantity())
                .validate(protectionMultipane.countSelectTableItems(), deviceList.size())
                .validate(protectionMultipane.countIncludedExcludedTableItems(), 0)
                .and("Collapse 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Multipane Text (include not all items)", priority = 4)
    private void checkMultipaneTextNotAllItems() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                        .testEnd();
                includeOneByOneItems(deviceList);

                testStart()
                    .and("Collapse 'Device' multipane")
                    .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .testEnd();
    }

    @Test(description = "Check Multipane Text (exclude not all items)", priority = 5)
    private void checkMultipaneTextExcludeNotAllItems() {

        testStart()
                .and(String.format("Select Publisher '%s'", publisherActive.getName()))
                .selectFromDropdown(protectionSidebar.getPublisherInput(),
                        protectionSidebar.getPublisherItems(), publisherActive.getName())
                .and("Expand 'Device' multipane")
                .clickOnWebElement(protectionMultipane.getPanelNameLabel())
                .and("Include All devices")
                .clickOnWebElement(protectionMultipane.getIncludeAllButton())
                .testEnd();

        excludeOneByOneItems(deviceList);

        testStart()
                .and("Collapse 'Device' multipane")
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

    private List<Device> getDevices() {

       return device()
                    .getDeviceLList()
                    .build()
                    .getDeviceGetAllResponse()
               .getItems();
    }

    @Step("Validate Device List")
    private void validateDeviceList(List<Device> list) {

        list.stream().forEach( e ->
        {
            testStart()
                    .validate(exist, protectionMultipane.getSelectTableItemByName(e.getName()).getName())
                    .testEnd();
        });
    }

    @Step("Include all items one by one")
    private void includeOneByOneItems(List<Device> list){
        var updated = new AtomicInteger(0);
        list.stream().forEach( e ->
        {
            var selectedItem = protectionMultipane.getSelectTableItemByPositionInList(updated.get());
            testStart()
                    .waiter(visible, selectedItem.getName())
                    .hoverMouseOnWebElement(selectedItem.getName())
                    .then()
                  //  .validate(not(visible), selectedItem.getExcludedIcon())
                    .validate(not(visible), selectedItem.getIncludedIcon())
                    .validate(not(visible), selectedItem.getActiveIcon())
                    .validate(not(visible), selectedItem.getInactiveIcon())
                    .validate(not(visible), selectedItem.getAssociatedWithPublisherIcon())
                    .validate(visible, selectedItem.getIncludeButton())
                  //  .validate(visible, selectedItem.getExcludeButton())
                    .and()
                    .clickOnWebElement(selectedItem.getIncludeButton())
                    .validate(protectionMultipane.getSelectionInfoExcludedLabel(), (updated.get() == 0)?
                                    MultipaneConstants.ONE_DEVICE_IS_INCLUDED.setQuantity(1) :
                                    MultipaneConstants.DEVICES_ARE_INCLUDED.setQuantity(updated.get()+1))
                    .validate(exist, selectedItem.getName())
                    .testEnd();
            updated.incrementAndGet();
        });
    }

    @Step("Exclude all items one by one")
    private void excludeOneByOneItems(List<Device> list){
        var updated = new AtomicInteger(0);
        list.stream().forEach( e ->
        {
            var selectedItem = protectionMultipane.getIncludedExcludedTableItemByPositionInList(updated.get());
            testStart()
                    .waiter(visible, selectedItem.getName())
                    .hoverMouseOnWebElement(selectedItem.getName())
                    .then()
                    .validate(visible, selectedItem.getRemoveButton())
                    .then()
                    .validate(protectionMultipane.getSelectionInfoExcludedLabel(), (updated.get() == 0)?
                            MultipaneConstants.ONE_DEVICE_IS_INCLUDED.setQuantity(1) :
                            MultipaneConstants.DEVICES_ARE_INCLUDED.setQuantity(updated.get()-1))
                    .validate(not(exist), selectedItem.getName())
                    .testEnd();
            updated.decrementAndGet();
        });
    }

    private void deleteTestData() {

        deleteMedia();
        deletePublisher(publisherEmpty.getId());
        deletePublisher(publisherActive.getId());
        deletePublisher(publisherInactive.getId());
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
    }
}
