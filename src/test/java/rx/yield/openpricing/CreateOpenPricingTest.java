package rx.yield.openpricing;

import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;
import zutils.FakerUtils;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.*;

@Slf4j
@Listeners({ScreenShooter.class})
public class CreateOpenPricingTest extends BaseTest {

    private OpenPricingPage openPricingPage;
    private CreateOpenPricingSidebar createOpenPricingSidebar;

    private static final String PRICING_NAME = captionWithSuffix("Pricing");
    private static final String ONE_DEVICE_IS_INCLUDED = "1 device(s) are included";

    public CreateOpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        createOpenPricingSidebar = new CreateOpenPricingSidebar();
    }

    @BeforeClass
    @Step("Login ToOpenPricing Page")
    public void loginToOpenPricing() {

        testStart()
                .given("Logging Directly to Open Pricing Page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .waitAndValidate(disappear, openPricingPage.getTableProgressBar())
                .testEnd();
    }

    @Test
    public void createOpenPricingTestWithOneIncludedDevice() {
        var deviceMultipane  = createOpenPricingSidebar.getDeviceMultipane();

        testStart()
                .given("Opening 'Create New Open Pricing sidebar'")
                .openDirectPath(Path.CREATE_OPEN_PRICING)
                .waitSideBarOpened()
                .when("Enter data to all fields of sidebar")
                .selectFromDropdownWithSearch(createOpenPricingSidebar.getPublisherNameDropdown(),
                        createOpenPricingSidebar.getPublisherNameDropdownItems(), "Viber")
                .setValue(createOpenPricingSidebar.getNameInput(),PRICING_NAME)
                .setValue(createOpenPricingSidebar.getFloorPriceField().getFloorPriceInput(), "22")
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
}
