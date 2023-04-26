package rx.yield.openpricing.columnsfilter;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import zutils.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Open Pricing Columns Filter")
public class OpenPricingColumnsFilterCreatedDateWidgetTests extends BaseTest {

    private OpenPricingPage openPricingPage;

    public OpenPricingColumnsFilterCreatedDateWidgetTests() {
        openPricingPage = new OpenPricingPage();
    }

    @BeforeClass
    private void login() {
        var tableColumns = openPricingPage.getOpenPricingTable().getShowHideColumns();

        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .scrollIntoView(openPricingPage.getOpenPricingTable().getTablePagination().getPageMenu())
                .selectFromDropdown(openPricingPage.getOpenPricingTable().getTablePagination().getPageMenu(),
                        openPricingPage.getOpenPricingTable().getTablePagination().getRowNumbersList(), "15")
                .clickOnWebElement(tableColumns.getShowHideColumnsBtn())
                .selectCheckBox(tableColumns.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .testEnd();
    }


    @Test(description = "Check Default State")
    public void testDefaultStateColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Select Column Filter 'Created Date'")
                .scrollIntoView(openPricingPage.getPageTitle())
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.CREATED_DATE))
                .then("Current date should be selected by default")
                .validate(calendar.getMonthOrYearHeaderButton().getText(), StringUtils.getStringMonthYear(currentDate.getMonth(),
                        currentDate.getYear()))
                .validate(calendar.getSelectedDayButton(), String.valueOf(currentDate.getDayOfMonth()))
                .testEnd();
    }

    @Test(description = "Check Previous Month", dependsOnMethods = "testDefaultStateColumnsFilterComponent")
    public void testPreviousMonthColumnsFilterComponent() {
        var calendar = openPricingPage.getOpenPricingTable().getColumnFiltersBlock().getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Click on the Previous Month button")
                .waitAndValidate(visible, calendar.getPreviousMonthButton())
                .clickOnWebElement(calendar.getPreviousMonthButton())
                .then("Should be displayed previous month")
                .validateContainsText(calendar.getMonthOrYearHeaderButton(),StringUtils.getStringMonthYear(currentDate.minusMonths(1).getMonth(),
                                currentDate.minusMonths(1).getYear()))
                .testEnd();
    }

    @Test(description = "Check Back button", dependsOnMethods = "testPreviousMonthColumnsFilterComponent")
    public void testBackButtonColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Click on Back button")
                .clickOnWebElement(filter.getCalendarFilter().getBackButton())
                .then("Columns Menu should appear")
                .validateList(filter.getFilterOptionItems(), List.of(ColumnNames.PUBLISHER.getName(),
                        ColumnNames.STATUS.getName(),
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.UPDATED_DATE.getName()))
                .and("Select Column Filter 'Created Date'")
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.CREATED_DATE))
                .then("Current date should be selected by default")
                .validate(calendar.getMonthOrYearHeaderButton().getText(), StringUtils.getStringMonthYear(currentDate.getMonth(),currentDate.getYear()))
                .validate(calendar.getSelectedDayButton(), String.valueOf(currentDate.getDayOfMonth()))
                .testEnd();
    }

    @Test(description = "Check Next Month", dependsOnMethods = "testBackButtonColumnsFilterComponent")
    public void testNextMonthColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Click on the Next Month button")
                .clickOnWebElement(calendar.getNextMonthButton())
                .then("Should be displayed next month")
                .validateContainsText(calendar.getMonthOrYearHeaderButton(),StringUtils.getStringMonthYear(currentDate.plusMonths(1).getMonth(),
                                currentDate.plusMonths(1).getYear()))
                .testEnd();
    }

    @Test(description = "Select First Day of the current month and click Cancel", dependsOnMethods = "testNextMonthColumnsFilterComponent")
    public void testCancelButtonColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var table = openPricingPage.getOpenPricingTable().getTableData();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Select 1 day of the month and Click on Cancel button")
                .clickOnWebElement(calendar.getDayButtonByValue("1"))
                .clickOnWebElement(filter.getCalendarFilter().getCancelButton())
                .then("Calendar widget should be closed and chip under the table should not appear")
                .waitAndValidate(not(visible), filter.getCalendarFilter().getFilterHeaderLabel())
                .validate(table.getFilterChips().size(), 0)
                .testEnd();
    }

    @Test(description = "Select Period of the current month and click Submit", dependsOnMethods = "testCancelButtonColumnsFilterComponent" )
    public void testPeriodAndSubmitButtonColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var table = openPricingPage.getOpenPricingTable().getTableData();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.CREATED_DATE))
                .and("Select Period Date of the month")
                .clickOnWebElement(calendar.getDayButtonByValue("15"))
                .clickOnWebElement(calendar.getDayButtonByValue("25"))
                .and("Click on Submit")
                .clickOnWebElement(filter.getCalendarFilter().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getHeaderLabel())
                .validate(visible, table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getCloseIcon())
                .validateContainsText(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getHeaderLabel(),
                        format("%s:",ColumnNames.CREATED_DATE.getName()))
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate selected period")
                .validate(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).countFilterOptionsChipItems(), 1)
                .validateContainsText(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getChipFilterOptionItemByPosition(0),
                        format("%s – %s",
                                StringUtils.getDateAsString(currentDate.getYear(), currentDate.getMonth(), 15),
                                StringUtils.getDateAsString(currentDate.getYear(), currentDate.getMonth(), 25)))
                .testEnd();
    }

    @Test(description = "Select Last Day of the current month and click Submit", dependsOnMethods = "testPeriodAndSubmitButtonColumnsFilterComponent")
    public void testSubmitButtonColumnsFilterComponent() {
        var filter = openPricingPage.getOpenPricingTable().getColumnFiltersBlock();
        var table = openPricingPage.getOpenPricingTable().getTableData();
        var calendar = filter.getCalendarFilter().getCalendar();
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        testStart()
                .and("Select Column Filter 'Created Date'")
                .scrollIntoView(openPricingPage.getPageTitle())
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.CREATED_DATE))
                .and("Select Last Date of the month")
                .clickOnWebElement(calendar.getDayButtonByValue(StringUtils.getLastDayOfMonth(currentDate.getYear(),currentDate.getMonth())))
                .and("Click on Submit")
                .clickOnWebElement(filter.getCalendarFilter().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getHeaderLabel())
                .validate(visible, table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getCloseIcon())
                .validateContainsText(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getHeaderLabel(),
                        format("%s:",ColumnNames.CREATED_DATE.getName()))
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate list of selected date")
                .validate(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).countFilterOptionsChipItems(), 1)
                .validate(visible, table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getChipFilterOptionItemByName(
                        StringUtils.getLastDayOfTheMonthDateAsString(currentDate.getYear(),currentDate.getMonth())))
                .testEnd();
    }

    @Test(description = "Delete Update By Chip", dependsOnMethods = "testPeriodAndSubmitButtonColumnsFilterComponent")
    public void testDeleteColumnsFilterComponent() {
        var table = openPricingPage.getOpenPricingTable().getTableData();

        testStart()
                .and("Click 'X' icon on the Created Date chip")
                .clickOnWebElement(table.getChipItemByName(ColumnNames.CREATED_DATE.getName()).getCloseIcon())
                .then("Chip 'Created Date' should be disappear")
                .validate(table.countFilterChipsItems(), 0)
                .testEnd();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}