package rx.yield.openpricing;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.detailsmenu.menu.TableItemDetailsMenu;
import widgets.common.multipane.Multipane;
import widgets.common.multipane.MultipaneName;
import widgets.common.table.ColumnNames;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static rx.enums.MultipaneConstants.*;
import static java.lang.String.format;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class CreateOpenPricingTest extends BaseTest {

    private Publisher newlyCreatedPublisher;
    private OpenPricingPage openPricingPage;
    private TableItemDetailsMenu pricingTableDetailsMenu;
    private CreateOpenPricingSidebar createOpenPricingSidebar;

    private static final String EMPTY_STRING = "";
    private static final String PRICING_NAME = captionWithSuffix("Pricing");

    public CreateOpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        pricingTableDetailsMenu = new TableItemDetailsMenu();
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

    @Test(priority = 0)
    @Step("Add one device to new Pricing")
    public void addOneDeviceToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getDeviceMultipane(), ONE_DEVICE_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 1)
    @Step("Add one inventory to new Pricing")
    public void addOneInventoryToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getInventoryMultipane(), ONE_MEDIA_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 2)
    @Step("Add one operating system to new Pricing")
    public void addOneOperatingSystemToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getOperatingSystemMultipane(), ONE_OPERATING_SYSTEM_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 3)
    @Step("Add one geo to new Pricing")
    public void addOneGeoToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getGeoMultipane(), ONE_GEO_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 4)
    @Step("Add one adSize to new Pricing")
    public void addOneAdSizeToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getAdSizeMultipane(), ONE_AD_SIZE_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 5)
    @Step("Add one adFormat to new Pricing")
    public void addOneAdFormatToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getAdFormatMultipane(), ONE_AD_FORMAT_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 6)
    @Step("Add one demand Source to new Pricing")
    public void addOneDemandSourceToPricingTest() {
        verifyItemSelectionInMultipane(createOpenPricingSidebar.getDemandSourcesMultipane(), ONE_DEMAND_SOURCE_IS_INCLUDED.setQuantity(1));
    }

    @Test(priority = 7)
    @Step("Click on 'Save' button  open Pricing")
    public void saveOpenPricingTest() {
        testStart()
                .and("Checking functionality of 'Save' button")
                .clickOnWebElement(createOpenPricingSidebar.getSaveButton())
                .validate(not(visible),createOpenPricingSidebar.getSaveButton())
                .validate(visible, "Updated!")
                .waitSideBarClosed()
                .testEnd();
    }

    @Test(priority = 8, dependsOnMethods = "saveOpenPricingTest")
    @Step("Verify new Pricing in table")
    public void checkOpenPricingTableTest() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();

        testStart()
                .then("Checking data of newly created pricing in Table")
                .validate(tableData.getCustomCells(ColumnNames.NAME).get(0), PRICING_NAME)
                .validate(tableData.getCustomCells(ColumnNames.PUBLISHER).get(0), "Viber")
                .validate(tableData.getCustomCells(ColumnNames.FLOOR_PRICE).get(0), "22.00 USD")
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0), "Active")
                .testEnd();
    }

    @Test(priority = 9, dependsOnMethods = "saveOpenPricingTest")
    @Step("Verify 'createdBy' column in  Pricing in table")
    public void checkCreatedByTest() {
        var pricingTable = openPricingPage.getOpenPricingTable();
        var tableData = pricingTable.getTableData();

        testStart()
                .and("Adding 'createdBy' column in to Pricing  Table")
                .clickOnWebElement(pricingTable.getTableOptions().getTableOptionsBtn())
                .selectCheckBox(pricingTable.getTableOptions().getMenuItemCheckbox(ColumnNames.CREATED_BY))
                .clickOnWebElement(pricingTable.getTableOptions().getTableOptionsBtn())
                .then("Check that user under testing is presented in table")
                .validate(tableData.getCustomCells(ColumnNames.CREATED_BY).get(0), TEST_USER.getMail())
                .testEnd();
    }

    @Test(priority = 10, dependsOnMethods = "saveOpenPricingTest")
    @Step("Verify 'updatedBy' column  Pricing in table")
    public void checkUpdatedByTest() {
        var pricingTable = openPricingPage.getOpenPricingTable();
        var tableData = pricingTable.getTableData();

        testStart()
                .and("Adding 'updatedBy' column in to Pricing  Table")
                .clickOnWebElement(pricingTable.getTableOptions().getTableOptionsBtn())
                .selectCheckBox(pricingTable.getTableOptions().getMenuItemCheckbox(ColumnNames.UPDATED_BY))
                .clickOnWebElement(pricingTable.getTableOptions().getTableOptionsBtn())
                .then("Check that user under testing is not presented in table")
                .validate(tableData.getCustomCells(ColumnNames.UPDATED_BY).get(0), EMPTY_STRING)
                .testEnd();
    }

    @Test(priority = 11, dependsOnMethods = "saveOpenPricingTest")
    @Step("Verify 'Inventory' Items in Details' menu in Pricing in table")
    public void checkInventoryMenuDetailsTest() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var inventoryDetailsSection = pricingTableDetailsMenu.getInventoryDetailsSection();

        testStart()
                .and("Hovering mouse cursor on 'Details' column in Pricing  Table")
                .hoverMouseOnWebElement(tableData.getCellByPositionInTable(ColumnNames.DETAILS, 0 ))
                .then("Check that Selected inventory is presented in Details Menu")
                .validate(visible,inventoryDetailsSection.getMenuInventoryItemByPositionInList(0).getName())
                .validate(visible,inventoryDetailsSection.getMenuInventoryItemByPositionInList(0).getIncludedIcon())
                .validate(not(visible),inventoryDetailsSection.getMenuInventoryItemByPositionInList(0).getExcludedIcon())
                .testEnd();
    }

    private void verifyItemSelectionInMultipane(Multipane multipane, String expectedPanelNameLabel) {

        testStart()
                .clickOnWebElement(multipane.getPanelNameLabel())
                .testEnd();

        var firstItemToSelect = multipane.getSelectTableItemByPositionInList(0);
        var demandSourceRelatedOnlyCondition =
                multipane.getMultipaneName().equals(MultipaneName.DEMAND_SOURCES) ? visible : not(visible);

        testStart()
                .then(format("Validate initial state of Items to select in '%s' Multipane", multipane.getMultipaneName()))
                .scrollIntoView(firstItemToSelect.getName())
                .hoverMouseOnWebElement(firstItemToSelect.getName())
                .validate(not(visible), firstItemToSelect.getExcludedIcon())
                .validate(not(visible), firstItemToSelect.getIncludedIcon())
                .validate(demandSourceRelatedOnlyCondition, firstItemToSelect.getActiveIcon())
                .validate(not(visible), firstItemToSelect.getInactiveIcon())
                .validate(demandSourceRelatedOnlyCondition, firstItemToSelect.getAssociatedWithPublisherIcon())
                .validate(visible, firstItemToSelect.getIncludeButton())
                .validate(not(visible), firstItemToSelect.getExcludeButton())
                .and()
                .clickOnWebElement(firstItemToSelect.getIncludeButton())
                .then(format("Verify available %s' list", multipane.getMultipaneName()))
                .validate(visible, firstItemToSelect.getIncludedIcon())
                .validate(not(visible), firstItemToSelect.getExcludedIcon())
                .validate(demandSourceRelatedOnlyCondition, firstItemToSelect.getActiveIcon())
                .validate(not(visible), firstItemToSelect.getInactiveIcon())
                .validate(demandSourceRelatedOnlyCondition, firstItemToSelect.getAssociatedWithPublisherIcon())
                .validate(not(visible), firstItemToSelect.getIncludeButton())
                .validate(not(visible), firstItemToSelect.getExcludeButton())
                .testEnd();

        var firstSelectedItem = multipane.getIncludedExcludedTableItemByPositionInList(0);

        testStart()
                .then(format("Verify added %s' List", multipane.getMultipaneName()))
                .validate(visible, firstSelectedItem.getName())
                //Todo enable after refactor
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
