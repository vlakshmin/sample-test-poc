package rx.protections.columnsfilter;

import api.dto.rx.protection.Protection;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static api.preconditionbuilders.ProtectionsPrecondition.protection;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Protections Columns Filter")
public class ProtectionsColumnsFilterCombinationsTests extends BaseTest {

    private ProtectionsPage protectionPage;

    private List<Protection> allProtectionsList = new ArrayList<>();

    public ProtectionsColumnsFilterCombinationsTests() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login() {
        var tableColumns = protectionPage.getProtectionsTable().getShowHideColumns();

        allProtectionsList = getAllProtectionBE();

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(tableColumns.getShowHideColumnsBtn())
                .selectCheckBox(tableColumns.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .and("Select 10 rows per page")
                .scrollIntoView(protectionPage.getProtectionsTable().getTablePagination().getPageMenu())
                .selectFromDropdown(protectionPage.getProtectionsTable().getTablePagination().getPageMenu(),
                        protectionPage.getProtectionsTable().getTablePagination().getRowNumbersList(), "10")

                .testEnd();
    }

    @Test(description = "Combination filters only for Active Protections")
    public void activeProtectionsCombinationFilters() {
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var tableData = protectionPage.getProtectionsTable().getTableData();
        var publishersActiveProtectionsList = getPublishersWithProtectionFilter(Map.of("active", true));

        testStart()
                .and("Select Column Filter 'Status'")
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.STATUS))
                .and("Select Active option")
                .selectRadioButton(filter.getActiveBooleanFilter().getActiveRadioButton())
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("Check filtered data")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        getProtectionsIdsWithFilterBE(Map.of("active", true, "limit", 10)))
                .and("Select 20 rows per page")
                .scrollIntoView(protectionPage.getProtectionsTable().getTablePagination().getPageMenu())
                .selectFromDropdown(protectionPage.getProtectionsTable().getTablePagination().getPageMenu(),
                        protectionPage.getProtectionsTable().getTablePagination().getRowNumbersList(), "15")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        getProtectionsIdsWithFilterBE(Map.of("active", true, "limit", 15)))
                .and("Select Column Filter 'Publisher'")
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .and("Search 1 publisher")
                .setValueWithClean(filter.getSinglepane().getSearchInput(), publishersActiveProtectionsList.get(0).getPublisherName())
                .validate(not(visible), protectionPage.getTableProgressBar())
                .clickOnWebElement(filter.getSinglepane()
                        .getFilterItemByName(publishersActiveProtectionsList.get(0).getPublisherName()).getName())
                .and("Search 2 publisher")
                .setValueWithClean(filter.getSinglepane().getSearchInput(), publishersActiveProtectionsList.get(1).getPublisherName())
                .validate(not(visible), protectionPage.getTableProgressBar())
                .waitAndValidate(not(visible), filter.getSinglepane().getFilterItemByName(publishersActiveProtectionsList.get(0).getPublisherName()).getName())
                .waitAndValidate(visible, filter.getSinglepane().getFilterItemByName(publishersActiveProtectionsList.get(1).getPublisherName()).getName())
                .validateContainsText(filter.getSinglepane().getItemsTotalQuantityLabel(), "(1)")
                .validate(not(visible), protectionPage.getTableProgressBar())
                .clickOnWebElement(filter.getSinglepane()
                        .getFilterItemByName(publishersActiveProtectionsList.get(1).getPublisherName()).getName())
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .and("Select 100 rows per page")
                .scrollIntoView(protectionPage.getProtectionsTable().getTablePagination().getPageMenu())
                .selectFromDropdown(protectionPage.getProtectionsTable().getTablePagination().getPageMenu(),
                        protectionPage.getProtectionsTable().getTablePagination().getRowNumbersList(), "25")
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .then("Check filtered data")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        getProtectionsIdsWithFilterBE(Map.of(
                                "active", true,
                                "limit", 25,
                                "sort", "id-asc",
                                format("publisher_id=%s&publisher_id", publishersActiveProtectionsList.get(1).getPublisherId()),
                                publishersActiveProtectionsList.get(0).getPublisherId())))
                .testEnd();
    }

    private List<Protection> getAllProtectionBE() {

        return protection()
                .getAllProtectionsList()
                .build()
                .getProtectionsGetAllResponse()
                .getItems();
    }

    private List<String> getProtectionsIdsWithFilterBE(Map params) {

        return protection()
                .getProtectionsWithFilter(params)
                .build()
                .getProtectionsGetAllResponse()
                .getItems()
                .stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
    }

    private List<Protection> getPublishersWithProtectionFilter(Map params) {

        return protection()
                .getProtectionsWithFilter(params)
                .build()
                .getProtectionsGetAllResponse()
                .getItems()
                .stream()
                .filter(distinctByKey(Protection::getPublisherId))
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
