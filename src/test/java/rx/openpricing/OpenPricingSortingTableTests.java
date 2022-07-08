package rx.openpricing;

import api.dto.rx.yield.openpricing.OpenPricing;
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
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;
import zutils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingSortingTableTests extends BaseTest {

//    private OpenPricing openPricing;
    private int totalOpenPricing;
    private List<String> sortIdsByAsc;
    private List<String> sortIdsByDesc;
    private List<String> sortNamesByDesc;
    private List<String> sortNamesByAsc;
    private List<String> sortPublisherNameByDesc;
    private List<String> sortPublisherNameByAsc;
    private List<String> sortActiveInactiveByDesc;
    private List<String> sortActiveInactiveByAsc;
    private List<String> sortFloorPriceDesc;
    private List<String> sortFloorPriceAsc;
    private List<String> sortCreatedByDesc;
    private List<String> sortCreatedByAsc;
    private List<String> sortCreatedDateDesc;
    private List<String> sortCreatedDateAsc;
    private List<String> sortUpdatedDateDesc;
    private List<String> sortUpdatedDateAsc;
    private List<String> sortUpdatedByDesc;
    private List<String> sortUpdatedByAsc;

    private OpenPricingPage openPricingPage;
    private EditOpenPricingSidebar editOpenPricingSidebar;

    public OpenPricingSortingTableTests() {
        openPricingPage = new OpenPricingPage();
        editOpenPricingSidebar = new EditOpenPricingSidebar();
    }

    @BeforeClass
    public void loginAndCreateExpectedResults() throws IOException {

        totalOpenPricing = OpenPricingPrecondition.openPricing()
                .getOpenPricingList()
                .build()
                .getOpenPricingGetAllResponse()
                .getTotal();

        //expected results for Media Name column
        sortNamesByDesc = getAllItemsByParams("name-desc").stream()
                .map(OpenPricing::getName)
                .collect(Collectors.toList());

        sortNamesByAsc = getAllItemsByParams("name-asc").stream()
                .map(OpenPricing::getName)
                .collect(Collectors.toList());

        //Expected result for ID column
        sortIdsByAsc = getAllItemsByParams("id-asc").stream()
                .map(OpenPricing::getId)
                .map(Object::toString)
                .collect(Collectors.toList());

        sortIdsByDesc = getAllItemsByParams("id-desc").stream()
                .map(OpenPricing::getId)
                .map(Object::toString)
                .collect(Collectors.toList());

        //Expected result for  Publisher Name column
        sortPublisherNameByAsc = getAllItemsByParams("publisher_name-asc").stream()
                .map(OpenPricing::getPublisherName)
                .collect(Collectors.toList());

        sortPublisherNameByDesc = getAllItemsByParams("publisher_name-desc").stream()
                .map(OpenPricing::getPublisherName)
                .collect(Collectors.toList());

        sortActiveInactiveByAsc = getAllItemsByParams("active-asc").stream()
                .map(OpenPricing::getActive)
                .map(Object::toString)
                .collect(Collectors.toList());

        sortActiveInactiveByDesc = getAllItemsByParams("active-desc").stream()
                .map(OpenPricing::getActive)
                .map(Object::toString)
                .collect(Collectors.toList());

        sortFloorPriceAsc = getAllItemsByParams("floor_price-asc").stream()
                .map(OpenPricing::getFloorPrice)
                .map(Object::toString)
                .collect(Collectors.toList());

        sortFloorPriceDesc = getAllItemsByParams("floor_price-desc").stream()
                .map(OpenPricing::getFloorPrice)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private List<OpenPricing> getAllItemsByParams(String strParams) throws IOException {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("sort", strParams);
        List<OpenPricing> openPricingList = OpenPricingPrecondition.openPricing()
                .getOpenPricingWithFilter(queryParams)
                .build()
                .getOpenPricingGetAllResponse()
                .getItems();
        String jsonString = ObjectMapperUtils.toJson(openPricingList);

        return ObjectMapperUtils.getCollectionType(jsonString, OpenPricing.class);
    }

    @Test(testName = "Sorting 'Name' column by descending")//passed
    public void OpenPricingSortingByNameDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.NAME.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.NAME.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 25 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-25 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-25 of %s", totalOpenPricing))
                .then("Validate data in column 'Name' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.NAME),
                        sortNamesByDesc.subList(0, 25))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("26-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("26-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Name' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.NAME),
                        sortNamesByDesc.subList(25, 50))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Name' column by ascending")//passed
    public void OpenPricingSortingByNameAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.NAME.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.NAME),
                        sortNamesByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Name' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.NAME),
                        sortNamesByAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Publisher' column by descending")//passed
    public void OpenPricingSortingByPublisherNameDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Publisher'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Publisher' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByDesc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Publisher' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByDesc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Publisher' column by ascending")//passed
    public void OpenPricingSortingByPublisherNameAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Publisher'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Publisher' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Publisher' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        sortPublisherNameByAsc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'ID' column by descending") //passed
    public void OpenPricingSortingByIdDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'ID'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'ID' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByDesc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'ID' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByDesc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'ID' column by ascending")//passed
    public void mediaSortingByIdAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'ID'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'ID' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'ID' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        sortIdsByAsc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Active/Inactive' column by descending")
    public void OpenPricingSortingByActiveInactiveDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Active/Inactive'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Active/Inactive' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        sortActiveInactiveByDesc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'ID' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        sortActiveInactiveByDesc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Active/Inactive' column by ascending")
    public void mediaSortingByActiveInactiveAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Active Inactive'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Active Inactive' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        sortActiveInactiveByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Active Inactive' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        sortActiveInactiveByAsc.subList(50, 100))
                .and()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Floor Price' column by descending")
    public void mediaSortingByFloorPriceDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Floor Price'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Floor Price' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.FLOOR_PRICE),
                        sortFloorPriceDesc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Floor Price' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.FLOOR_PRICE),
                        sortFloorPriceDesc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Floor Price' column by ascending")
    public void mediaSortingByFloorPriceAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Floor price'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.FLOOR_PRICE.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Floor Price' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.FLOOR_PRICE),
                        sortFloorPriceAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Floor Price' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.FLOOR_PRICE),
                        sortFloorPriceAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Created By' column by descending")
    public void OpenPricingSortingByCreatedByDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Created By'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 25 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-25 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-25 of %s", totalOpenPricing))
                .then("Validate data in column 'Created By' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_BY),
                        sortCreatedByDesc.subList(0, 25))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("26-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("26-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Created By' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_BY),
                        sortCreatedByDesc.subList(25, 50))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Created By' column by ascending")
    public void OpenPricingSortingByCreatedByAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Created By'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_BY.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Created By' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_BY),
                        sortCreatedByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Created By' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_BY),
                        sortCreatedByAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Created Date' column by descending")
    public void OpenPricingSortingCreateDateDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Created Date'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 25 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-25 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-25 of %s", totalOpenPricing))
                .then("Validate data in column 'Created Date' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_DATE),
                        sortCreatedDateDesc.subList(0, 25))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("26-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("26-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Created Date' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_DATE),
                        sortCreatedDateDesc.subList(25, 50))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Created Date' column by ascending")
    public void OpenPricingSortingByCreatedDateAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Created Date'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Created Date' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_DATE),
                        sortCreatedDateAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Created Date' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.CREATED_DATE),
                        sortCreatedDateAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Updated Date' column by descending")
    public void OpenPricingSortingUpdatedDateDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Updated Date'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 25 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-25 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-25 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated Date' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_DATE),
                        sortUpdatedDateDesc.subList(0, 25))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("26-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("26-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated Date' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_DATE),
                        sortUpdatedDateDesc.subList(25, 50))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Updated Date' column by ascending")
    public void OpenPricingSortingByUpdatedDateAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Updated Date'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated Date' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_DATE),
                        sortUpdatedDateAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated Date' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_DATE),
                        sortUpdatedDateAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Updated By' column by descending")
    public void OpenPricingSortingUpdatedByDesc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Updated By'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()))
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()),
                        "aria-sort", "ascending")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()),
                        "aria-sort", "descending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 25 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-25 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-25 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated By' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_BY),
                        sortUpdatedByDesc.subList(0, 25))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("26-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("26-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated By' should be sorted by desc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_BY),
                        sortUpdatedByDesc.subList(25, 50))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }

    @Test(testName = "Sorting 'Updated By' column by ascending")
    public void OpenPricingSortingByUpdatedByAsc() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Sort column 'Updated By'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()))
                .then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()),
                        "aria-sort", "ascending")
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and("Select 50 row per page")
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("1-50 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated By' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_BY),
                        sortUpdatedByAsc.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '%s'",
                        String.format("51-100 of %s", totalOpenPricing)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-100 of %s", totalOpenPricing))
                .then("Validate data in column 'Updated By' should be sorted by asc")
                .validateList(tableData.getCustomCells(ColumnNames.UPDATED_BY),
                        sortUpdatedByAsc.subList(50, 100))
                .and("Test Finished")
                .logOut()
                .testEnd();
    }
}