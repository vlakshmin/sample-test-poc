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
import widgets.common.multipane.Multipane;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class CreateOpenPricingTest extends BaseTest {

    private OpenPricingPage openPricingPage;
    private CreateOpenPricingSidebar createOpenPricingSidebar;

    private static final String PRICING_NAME = captionWithSuffix("Pricing");
    private static final String ONE_DEVICE_IS_INCLUDED = "1 device(s) are included";
    private static final String ONE_MEDIA_IS_INCLUDED = "1 media are included";
    private static final String ONE_OPERATING_SYSTEM_IS_INCLUDED = "1 operating system(s) are included";

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
    public void createOpenPricingWithOneDeviceIncludedTest() {
        var deviceMultipane = createOpenPricingSidebar.getDeviceMultipane();

        verifyItemSelectionInMultipane(deviceMultipane, ONE_DEVICE_IS_INCLUDED);
    }

    @Test
    public void createOpenPricingTestWithOneInventoryIncludedTest() {
        var inventoryMultipane = createOpenPricingSidebar.getInventoryMultipane();

        verifyItemSelectionInMultipane(inventoryMultipane, ONE_MEDIA_IS_INCLUDED);
    }

    @Test
    public void createOpenPricingWithOneOsIncludedDTest() {
        var osMultipane = createOpenPricingSidebar.getInventoryMultipane();

       // verifyItemSelectionInMultipane(osMultipane, ONE_OPERATING_SYSTEM_IS_INCLUDED);
    }

    private void verifyItemSelectionInMultipane(Multipane multipane, String expectedPanelNameLabel) {

        testStart()
                .clickOnWebElement(multipane.getPanelNameLabel())
                .testEnd();

        var firstInventoryToSelect = multipane.getSelectTableItemByPositionInList(0);

        testStart()
                .then(format("Validate initial state of Items to select in '%s' Multipane", multipane.getMultipaneName()))
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
                .then(format("Verify available %s' list", multipane.getMultipaneName()))
                .validate(visible, firstInventoryToSelect.getIncludedIcon())
                .validate(not(visible), firstInventoryToSelect.getExcludedIcon())
                .validate(not(visible), firstInventoryToSelect.getActiveIcon())
                .validate(not(visible), firstInventoryToSelect.getInactiveIcon())
                .validate(not(visible), firstInventoryToSelect.getAssociatedWithPublisherIcon())
                .validate(not(visible), firstInventoryToSelect.getIncludeButton())
                .validate(not(visible), firstInventoryToSelect.getExcludeButton())
                .testEnd();

        var firstSelectedItem = multipane.getIncludedExcludedTableItemByPositionInList(0);

        testStart()
                .then(format("Verify added %s' List", multipane.getMultipaneName()))
                .validate(visible, firstSelectedItem.getName())
                //.validate(not(visible), firstSelectedItem.getType())
                .validate(not(visible), firstSelectedItem.getParentLabel())
                .validate(visible, firstSelectedItem.getRemoveButton())
                .validate(enabled, multipane.getClearAllButton())
                .and(format("Collups '%s' Multipane", multipane.getMultipaneName()))
                .clickOnWebElement(multipane.getPanelNameLabel())
                .then(format("Validate '%s' Multipane Panel Strings", multipane.getMultipaneName()))
                .validateContainsText(multipane.getPanelNameLabel(), expectedPanelNameLabel)
                .testEnd();
    }

}
