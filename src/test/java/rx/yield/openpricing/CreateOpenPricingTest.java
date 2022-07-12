package rx.yield.openpricing;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class CreateOpenPricingTest extends BaseTest {

    private OpenPricingPage openPricingPage;
    private CreateOpenPricingSidebar createOpenPricingSidebar;

    private static final String PRICING_NAME = captionWithSuffix("Pricing");
    private static final String ONE_DEVICE_IS_INCLUDED = "1 device(s) are included";
    private static final String ONE_MEDIA_IS_INCLUDED = "1 media(s) are included";

    public CreateOpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        createOpenPricingSidebar = new CreateOpenPricingSidebar();
    }

    @BeforeClass
    @Step("Login ToOpenPricing Page")
    public void loginToCreateOpenPricingSidebar() {

        testStart()
                .given("Logging Directly to Open Pricing Page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .waitAndValidate(disappear, openPricingPage.getTableProgressBar())
                .when("Opening 'Create New Open Pricing sidebar'")
                .openDirectPath(Path.CREATE_OPEN_PRICING)
                .waitSideBarOpened()
                .and("Enter data to all fields of sidebar")
                .selectFromDropdownWithSearch(createOpenPricingSidebar.getPublisherNameDropdown(),
                        createOpenPricingSidebar.getPublisherNameDropdownItems(), "Viber")
                .setValue(createOpenPricingSidebar.getNameInput(), PRICING_NAME)
                .setValue(createOpenPricingSidebar.getFloorPriceField().getFloorPriceInput(), "22")
                .testEnd();
    }

    @Test
    public void createOpenPricingTestWithOneIncludedDevice() {
        var deviceMultipane = createOpenPricingSidebar.getDeviceMultipane();

        testStart()
                .clickOnWebElement(deviceMultipane.getPanelNameLabel())
                .testEnd();

        var firstDeviceToSelect = deviceMultipane.getSelectTableItemByPositionInList(0);

        testStart()
                .then("Validate initial state of Items to select in Multipane")
                .hoverMouseOnWebElement(firstDeviceToSelect.getName())
                .validate(not(visible), firstDeviceToSelect.getExcludedIcon())
                .validate(not(visible), firstDeviceToSelect.getIncludedIcon())
                .validate(not(visible), firstDeviceToSelect.getActiveIcon())
                .validate(not(visible), firstDeviceToSelect.getInactiveIcon())
                .validate(not(visible), firstDeviceToSelect.getAssociatedWithPublisherIcon())
                .validate(visible, firstDeviceToSelect.getIncludeButton())
                .validate(not(visible), firstDeviceToSelect.getExcludeButton())
                .and()
                .clickOnWebElement(firstDeviceToSelect.getIncludeButton())
                .then("Verify available devices' list")
                .validate(visible, firstDeviceToSelect.getIncludedIcon())
                .validate(not(visible), firstDeviceToSelect.getExcludedIcon())
                .validate(not(visible), firstDeviceToSelect.getActiveIcon())
                .validate(not(visible), firstDeviceToSelect.getInactiveIcon())
                .validate(not(visible), firstDeviceToSelect.getAssociatedWithPublisherIcon())
                .validate(not(visible), firstDeviceToSelect.getIncludeButton())
                .validate(not(visible), firstDeviceToSelect.getExcludeButton())
                .testEnd();

        var firstSelectedDevice = deviceMultipane.getIncludedExcludedTableItemByPositionInList(0);

        testStart()
                .then("Verify added devices List")
                .validate(visible, firstSelectedDevice.getName())
                //.validate(not(visible), firstSelectedDevice.getType())
                .validate(not(visible), firstSelectedDevice.getParentLabel())
                .validate(visible, firstSelectedDevice.getRemoveButton())
                .validate(enabled, deviceMultipane.getClearAllButton())
                .and("Collups Device Multipane")
                .clickOnWebElement(deviceMultipane.getPanelNameLabel())
                .then("Validate Multipane Panel Strings")
                //.validateContainsText(deviceMultipane.getPanelNameLabel(), ONE_DEVICE_IS_INCLUDED)
                .testEnd();

    }

    @Test
    public void createOpenPricingTestWithOneIncludedDInventory() {
        var inventoryMultipane = createOpenPricingSidebar.getInventoryMultipane();

        testStart()
                .clickOnWebElement(inventoryMultipane.getPanelNameLabel())
                .testEnd();

        var firstInventoryToSelect = inventoryMultipane.getSelectTableItemByPositionInList(0);

        testStart()
                .then("Validate initial state of Items to select in Multipane")
                .hoverMouseOnWebElement(firstInventoryToSelect.getName())
                .validate(not(visible), firstInventoryToSelect.getExcludedIcon())
                .validate(not(visible), firstInventoryToSelect.getIncludedIcon())
                .validate(not(visible), firstInventoryToSelect.getActiveIcon())
                .validate(not(visible), firstInventoryToSelect.getInactiveIcon())
                .validate(not(visible), firstInventoryToSelect.getAssociatedWithPublisherIcon())
                .validate(visible, firstInventoryToSelect.getIncludeButton())
                .validate(not(visible), firstInventoryToSelect.getExcludeButton())
                .and()
                .clickOnWebElement(firstInventoryToSelect.getIncludeButton())
                .then("Verify available Inventories' list")
                .validate(visible, firstInventoryToSelect.getIncludedIcon())
                .validate(not(visible), firstInventoryToSelect.getExcludedIcon())
                .validate(not(visible), firstInventoryToSelect.getActiveIcon())
                .validate(not(visible), firstInventoryToSelect.getInactiveIcon())
                .validate(not(visible), firstInventoryToSelect.getAssociatedWithPublisherIcon())
                .validate(not(visible), firstInventoryToSelect.getIncludeButton())
                .validate(not(visible), firstInventoryToSelect.getExcludeButton())
                .testEnd();

        var firstSelectedInventory = inventoryMultipane.getIncludedExcludedTableItemByPositionInList(0);

        testStart()
                .then("Verify added inventories List")
                .validate(visible, firstSelectedInventory.getName())
                //.validate(not(visible), firstSelectedDevice.getType())
                .validate(not(visible), firstSelectedInventory.getParentLabel())
                .validate(visible, firstSelectedInventory.getRemoveButton())
                .validate(enabled, inventoryMultipane.getClearAllButton())
                .and("Collups Inventory Multipane")
                .clickOnWebElement(inventoryMultipane.getPanelNameLabel())
                .then("Validate Multipane Panel Strings")
                .validateContainsText(inventoryMultipane.getPanelNameLabel(), ONE_DEVICE_IS_INCLUDED)
                .testEnd();

    }

    private void verifyItemSelectionInMultipane() {

        var inventoryMultipane = createOpenPricingSidebar.getInventoryMultipane();

        testStart()
                .clickOnWebElement(inventoryMultipane.getPanelNameLabel())
                .testEnd();

        var firstInventoryToSelect = inventoryMultipane.getSelectTableItemByPositionInList(0);

        testStart()
                .then("Validate initial state of Items to select in Multipane")
                .hoverMouseOnWebElement(firstInventoryToSelect.getName())
                .validate(not(visible), firstInventoryToSelect.getExcludedIcon())
                .validate(not(visible), firstInventoryToSelect.getIncludedIcon())
                .validate(not(visible), firstInventoryToSelect.getActiveIcon())
                .validate(not(visible), firstInventoryToSelect.getInactiveIcon())
                .validate(not(visible), firstInventoryToSelect.getAssociatedWithPublisherIcon())
                .validate(visible, firstInventoryToSelect.getIncludeButton())
                .validate(not(visible), firstInventoryToSelect.getExcludeButton())
                .and()
                .clickOnWebElement(firstInventoryToSelect.getIncludeButton())
                .then("Verify available Inventories' list")
                .validate(visible, firstInventoryToSelect.getIncludedIcon())
                .validate(not(visible), firstInventoryToSelect.getExcludedIcon())
                .validate(not(visible), firstInventoryToSelect.getActiveIcon())
                .validate(not(visible), firstInventoryToSelect.getInactiveIcon())
                .validate(not(visible), firstInventoryToSelect.getAssociatedWithPublisherIcon())
                .validate(not(visible), firstInventoryToSelect.getIncludeButton())
                .validate(not(visible), firstInventoryToSelect.getExcludeButton())
                .testEnd();

        var firstSelectedInventory = inventoryMultipane.getIncludedExcludedTableItemByPositionInList(0);

        testStart()
                .then("Verify added inventories List")
                .validate(visible, firstSelectedInventory.getName())
                //.validate(not(visible), firstSelectedDevice.getType())
                .validate(not(visible), firstSelectedInventory.getParentLabel())
                .validate(visible, firstSelectedInventory.getRemoveButton())
                .validate(enabled, inventoryMultipane.getClearAllButton())
                .and("Collups Inventory Multipane")
                .clickOnWebElement(inventoryMultipane.getPanelNameLabel())
                .then("Validate Multipane Panel Strings")
                .validateContainsText(inventoryMultipane.getPanelNameLabel(), ONE_DEVICE_IS_INCLUDED)
                .testEnd();
    }

}
