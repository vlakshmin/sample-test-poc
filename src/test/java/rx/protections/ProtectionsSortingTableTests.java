package rx.protections;

import api.dto.rx.protection.Protection;
import api.preconditionbuilders.ProtectionsPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;


@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsSortingTableTests extends BaseTest {
    private static final int MIN_COUNT_PROTECTIONS = 60;
    private int totalProtections;
    private int[] idsToDelete = {};
    private List<String> sortIdsByAsc;
    private List<String> sortIdsByDesc;
    private List<String> sortPublisherNameByDesc;
    private List<String> sortPublisherNameByAsc;
    private final ProtectionsPage protectionsPage;

    private static final String ASC = "ascending";
    private static final String DESC = "descending";

    private  ProtectionsPrecondition.ProtectionsPreconditionBuilder protection;

    public ProtectionsSortingTableTests() {
        protectionsPage = new ProtectionsPage();
    }

    @BeforeClass
    private void loginAndCreateExpectedResults() {
        protection = ProtectionsPrecondition.protection();
        protection.setCredentials(TEST_USER);
        int count = MIN_COUNT_PROTECTIONS - getTotalProtections();
        if (count > 0) {
            idsToDelete = new int[count];
            generateProtections(count, idsToDelete);
        }

        totalProtections = getTotalProtections();

        sortIdsByAsc = getIdsByAsc();
        sortIdsByDesc = getIdsByDesc();

        sortPublisherNameByAsc = getPublisherNameByAsc();
        sortPublisherNameByDesc = getPublisherNameByDesc();
    }

    @Test(testName = "Sorting 'ID' column by descending")
    public void protectionsSortingByIdDesc() {
        sortByDescColumnByName(ColumnNames.ID);
        validateSortData(ColumnNames.ID, DESC, sortIdsByDesc);
    }

    @Test(testName = "Sorting 'ID' column by ascending")
    public void protectionsSortingByIdAsc() {
        sortByAscColumnByName(ColumnNames.ID);
        validateSortData(ColumnNames.ID, ASC, sortIdsByAsc);
    }

    @Test(testName = "Sorting 'Publisher' column by ascending")
    public void protectionsSortingByPublisherNameAsc() {
        sortByAscColumnByName(ColumnNames.PUBLISHER);
        validateSortData(ColumnNames.PUBLISHER, ASC, sortPublisherNameByAsc);
    }

    @Test(testName = "Sorting 'Publisher' column by descending")
    public void protectionsSortingByPublisherNameDesc() {
        sortByDescColumnByName(ColumnNames.PUBLISHER);
        validateSortData(ColumnNames.PUBLISHER, DESC, sortPublisherNameByDesc);
    }

    @BeforeMethod
    private void login() {
        var table = protectionsPage.getProtectionsTable().getTableOptions();
        var tableData = protectionsPage.getProtectionsTable().getTableData();
        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionsPage.getNuxtProgress())
                .testEnd();
    }

    @AfterMethod
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @Step("Sort column {0} by Name DESC")
    private void sortByDescColumnByName(ColumnNames columnName) {
        var tableData = protectionsPage.getProtectionsTable().getTableData();

        testStart().given().and(String.format("Sort column '%s'", columnName))
                .clickOnWebElement(tableData.getColumnHeader(columnName.getName()))
                .testEnd();

        if (columnName.getName().equals("ID")) {
            testStart()
                    .clickOnWebElement(tableData.getColumnHeader(columnName.getName()))
                    .testEnd();
        }

        testStart()
                .then("Ensure that sort by descending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(columnName.getName()),
                        "aria-sort", ASC)
                .clickOnWebElement(tableData.getColumnHeader(columnName.getName()))
                .validateAttribute(tableData.getColumnHeader(columnName.getName()),
                        "aria-sort", DESC)
                .waitAndValidate(disappear, protectionsPage.getNuxtProgress())
                .testEnd();
    }

    @Step("Sort column {0} by Name ASC")
    private void sortByAscColumnByName(ColumnNames columnName) {
        var tableData = protectionsPage.getProtectionsTable().getTableData();

        testStart()
                .given()
                .and(String.format("Sort column '%s'", columnName))
                .clickOnWebElement(tableData.getColumnHeader(columnName.getName()))
                .testEnd();

        if (columnName.getName().equals("ID")) {
            testStart()
                    .clickOnWebElement(tableData.getColumnHeader(columnName.getName()))
                    .testEnd();
        }

        testStart().then("Ensure that sort by ascending: validate column attribute value")
                .validateAttribute(tableData.getColumnHeader(columnName.getName()),
                        "aria-sort", ASC)
                .waitAndValidate(disappear, protectionsPage.getNuxtProgress())
                .testEnd();
    }

    @Step("Validate data in column {0} sorted by {1}")
    private void validateSortData(ColumnNames columnName, String sortType, List<String> expectedResultList) {
        var tableData = protectionsPage.getProtectionsTable().getTableData();
        var tablePagination = protectionsPage.getProtectionsTable().getTablePagination();

        testStart()
                .given()
                .waitAndValidate(disappear, protectionsPage.getNuxtProgress())
                .and("Select 50 row per page")
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), "50")
                .waitLoading(visible, protectionsPage.getTableProgressBar())
                .waitLoading(disappear, protectionsPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '1-50 of %s'",
                        totalProtections))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("1-50 of %s", totalProtections))
                .then(String.format("Validate data in column '%s' should be sorted by %s",
                        columnName.getName(), sortType))
                .validateList(tableData.getCustomCells(columnName),
                        expectedResultList.subList(0, 50))
                .and("Check next page")
                .clickOnWebElement(tablePagination.getNext())
                .waitLoading(visible, protectionsPage.getTableProgressBar())
                .waitLoading(disappear, protectionsPage.getTableProgressBar())
                .then(String.format("Validate that text in table footer '51-%s of %s'",
                        Math.min(100, totalProtections), totalProtections))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format("51-%s of %s", Math.min(100, totalProtections), totalProtections))
                .then(String.format("Validate data in column '%s' should be sorted by %s",
                        columnName.getName(), sortType))
                .validateList(tableData.getCustomCells(columnName),
                        expectedResultList.subList(50, Math.min(100, totalProtections)))
                .testEnd();
    }

    private int getTotalProtections() {

        return getProtectionsListSize();
    }

    private List<String> getIdsByAsc() {

        return getAllItemsByParams("id-asc")
                .stream()
                .map(Protection::getId)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private List<String> getIdsByDesc() {

        return getAllItemsByParams("id-desc")
                .stream()
                .map(Protection::getId)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private List<String> getPublisherNameByAsc() {

        return getAllItemsByParams("publisher_name-asc")
                .stream()
                .map(Protection::getPublisherName)
                .collect(Collectors.toList());
    }

    public Integer getProtectionsListSize() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sort", "id-desc");

        return ProtectionsPrecondition.protection()
                .getProtectionsWithFilter(queryParams)
                .build()
                .getProtectionsGetAllResponse()
                .getItems().size();
    }

    private List<String> getPublisherNameByDesc() {

        return getAllItemsByParams("publisher_name-desc")
                .stream()
                .map(Protection::getPublisherName)
                .collect(Collectors.toList());
    }

    private List<Protection> getAllItemsByParams(String strParams) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sort", strParams);

        return ProtectionsPrecondition
                .protection()
                .getProtectionsWithFilter(queryParams)
                .build()
                .getProtectionsGetAllResponse()
                .getItems();
    }

    private void generateProtections(int count, int... idsToDelete) {

        for (int i = 0; i < count; i++) {
            var builder = protection.createNewRandomProtection().build();
            var response = builder.getProtectionsResponse();
            if (response != null) {
                idsToDelete[i]=response.getId();
            }
        }
    }

    @AfterClass
    private void deleteEntities() {
        protection.setCredentials(USER_FOR_DELETION);
        Arrays.stream(idsToDelete).forEach(id -> protection.deleteProtection(id).build());
    }
}



