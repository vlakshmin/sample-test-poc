package rx.openpricing;

import api.dto.rx.yield.openPricing.OpenPricing;
import api.preconditionbuilders.OpenPricingPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingTest extends BaseTest {
    private OpenPricing openPricing;
    private OpenPricingPage openPricingPage;
    private EditOpenPricingSidebar editOpenPricingSidebar;

    public OpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        editOpenPricingSidebar = new EditOpenPricingSidebar();
    }

    @BeforeClass
    public void createOpenPricing() {
        openPricing = OpenPricingPrecondition.openPricing()
                .createNewOpenPricing()
                .build()
                .getOpenPricingResponse();
    }

    @Test(enabled = true)
    public void checkPagination() {
        var table = openPricingPage.getOpenPricingTable().getTableOptions();
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "10")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 10)
                .clickOnWebElement(tablePagination.getNext())
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "20")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 20)
                .clickOnWebElement(tablePagination.getNext())
                .validateListSize(tableData.getRows(), 20)
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "15")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 15)
                .clickOnWebElement(tablePagination.getNext())
                .validateListSize(tableData.getRows(), 15)
                .clickOnWebElement(tablePagination.getPrevious())
                .validateListSize(tableData.getRows(), 15)
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 25)
                .clickOnWebElement(tablePagination.getNext())
                .validateListSize(tableData.getRows(), 25)
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 50)
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "100")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then()
                .validateListSize(tableData.getRows(), 100)
                .clickOnWebElement(tablePagination.getNext())
                .validateListSize(tableData.getRows(), 100)
                .logOut()
                .testEnd();
    }

    @Test(enabled = true)
    public void verifyColumnOrder() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .when()
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "10")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateContainsText(tablePagination.getPaginationPanel(), "1-10 of ")
                .validateListSize(tableData.getColumns(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DETAILS.getName(),
                        ColumnNames.NAME.getName(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.FLOOR_PRICE.getName())
                .validate((tableData.getColumns()).get(0), ColumnNames.ID.getName())
                .validate((tableData.getColumns()).get(1), ColumnNames.DETAILS.getName())
                .validate((tableData.getColumns()).get(2), ColumnNames.NAME.getName())
                .validate((tableData.getColumns()).get(3), ColumnNames.PUBLISHER.getName())
                .validate((tableData.getColumns()).get(4), ColumnNames.ACTIVE_INACTIVE.getName())
                .validate((tableData.getColumns()).get(5), ColumnNames.FLOOR_PRICE.getName())
                .scrollIntoView(tableOptions.getTableOptionsBtn())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())//
                .validateListSize(tableOptions.getTableOptionMenuItems(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DETAILS.getName(),
                        ColumnNames.NAME.getName(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.FLOOR_PRICE.getName(),
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.CREATED_BY.getName(),
                        ColumnNames.UPDATED_DATE.getName(),
                        ColumnNames.UPDATED_BY.getName())
                .then()
                .testEnd();
    }

    @Test(enabled = true)
    public void checkColumns() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "10")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateContainsText(tablePagination.getPaginationPanel(), "1-10 of ")
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ID))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ID.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DETAILS))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.DETAILS.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.NAME))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE_INACTIVE))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.FLOOR_PRICE))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_BY))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .then()
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_BY))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()))
                .then()
                .validateListSize(tableOptions.getTableOptionMenuItems(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DETAILS.getName(),
                        ColumnNames.NAME.getName(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.FLOOR_PRICE.getName(),
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.CREATED_BY.getName(),
                        ColumnNames.UPDATED_DATE.getName(),
                        ColumnNames.UPDATED_BY.getName())
                .then()
                .testEnd();
    }

    @Test(enabled = false)
    public void verifySortingID() {
        editOpenPricingSidebar = new EditOpenPricingSidebar();
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .clickOnWebElement(tableData.getColumnHeader("ID"));
    }

    @Test(enabled = false)
    public void verifySortingName() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyPublisher() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyActiveInactive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyFloorPrice() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyFilter() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE))
                .validate(Statuses.ACTIVE.getStatus())
                .clickOnWebElement(tableOptions.getTableOptionsBtn());


    }

    @Test(enabled = false)
    public void verifySearch() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = true)
    public void verifySingleDeactivatePublisherActive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
//                .scrollIntoView(tableData.getCheckbox(1))tableData.getCheckbox(1)
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
//                .scrollIntoView(openPricingPage.getDeactivateButton())
                .clickOnWebElement(openPricingPage.getDeactivateButton())
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0), "Inactive")
                .then()
                .testEnd();


    }

    @Test(enabled = true)
    public void verifySingleActivatePublisherActive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
                .clickOnWebElement(openPricingPage.getActivateButton())
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0), "Active")
                .then()
                .testEnd();
    }

    @Test(enabled = true)
    public void verifyBulkDeactivatePublisherActive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(2))
                .clickOnWebElement(tableData.getCheckbox(3))
                .clickOnWebElement(openPricingPage.getDeactivateButton())
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0), "Inactive")
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(1), "Inactive")
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(2), "Inactive")
                .then()
                .testEnd();


    }

    @Test(enabled = true)
    public void verifyBulkActivatePublisherActive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(2))
                .clickOnWebElement(tableData.getCheckbox(3))
                .clickOnWebElement(openPricingPage.getActivateButton())
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0), "Active")
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(1), "Active")
                .validate(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(2), "Active")
                .then()
                .testEnd();
    }

    @Test(enabled = false)
    public void verifySingleDeactivatePublisherInactive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
                .clickOnWebElement(openPricingPage.getActivateButton())
                .validate(tableData.getCustomCells(ColumnNames.STATUS).get(0), "Active")
                .then()
                .testEnd();

    }

    @Test(enabled = false)
    public void verifySingleActivatePublisherInactive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, tableData.getCheckbox(1))
                .clickOnWebElement(tableData.getCheckbox(1))
                .clickOnWebElement(openPricingPage.getDeactivateButton())
                .validate(tableData.getCustomCells(ColumnNames.STATUS).get(0), "Inactive")
                .then()
                .testEnd();
    }

    @Test(enabled = false)
    public void verifyBulkDeactivatePublisherInactive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyBulkActivatePublisherInactive() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = false)
    public void verifyRuleExpand() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and();
    }

    @Test(enabled = true)
    public void verifyetc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .waitAndValidate(appear, openPricingPage.getCreateOpenPricingButton())
                .clickOnWebElement(openPricingPage.getCreateOpenPricingButton());

//                .validateAttribute(editOpenPricingSidebar.getNameInput(), "value", openPricing.getName())
//                .validate(editOpenPricingSidebar.getPublisherInput(), openPricing.getPublisherName())
//                .validateAttribute(editOpenPricingSidebar.getFloorPrice(), "value", openPricing.getFloorPrice().toString())
//                .clickOnWebElement(editOpenPricingSidebar.getSaveButton())
//                .waitSideBarClosed()
//                .testEnd();
    }


}
