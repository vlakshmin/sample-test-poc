package rx.protections.columnsfilter;

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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Components")
public class ProtectionsColumnsFilterCalendarWidgetTests extends BaseTest {

    private ProtectionsPage protectionPage;

    public ProtectionsColumnsFilterCalendarWidgetTests() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login() {
        var tableColumns = protectionPage.getProtectionsTable().getShowHideColumns();

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(tableColumns.getShowHideColumnsBtn())
                .selectCheckBox(tableColumns.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .selectCheckBox(tableColumns.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .clickOnWebElement(tableColumns.getShowHideColumnsBtn())
                .testEnd();
    }


    @Test(description = "Check Updated Date")
    public void testUpdateDateColumnsFilterComponent() {
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var table = protectionPage.getProtectionsTable().getTableData();
        var calendar = filter.getCalendarFilter().getCalendar();

        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDate initial = LocalDate.of(currentDate.getYear(),currentDate.getMonth(),currentDate.getDayOfMonth());

        testStart()
                .and("Select Column Filter 'Updated Date'")
                .scrollIntoView(protectionPage.getProtectionPageTitle())
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.UPDATED_DATE))
                .then("Current date should be selected by default")
                .validate(calendar.getMonthOrYearHeaderButton().getText(),format("%s %s",
                        currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US),currentDate.getYear()))
                .validate(calendar.getSelectedDayButton(),Integer.toString(currentDate.getDayOfMonth()))
                .and("Click on the Next Month button")
                .clickOnWebElement(calendar.getNextMonthButton())
                .then("Should be displayed next month")
                .validate(calendar.getMonthOrYearHeaderButton().getText(),format("%s %s",
                        currentDate.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.US),
                        currentDate.getMonth().getValue()==12 ? currentDate.getYear()+1 : currentDate.getYear()))
                .and("Click on Back button")
                .clickOnWebElement(filter.getCalendarFilter().getBackButton())
                .then("Columns Menu should appear")
                .validateList(filter.getFilterOptionItems(), List.of(ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.MANAGED_BY_SYSTEM_ADMIN.getName(),
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.UPDATED_DATE.getName()))
                .and("Select Column Filter 'Updated Date'")
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.UPDATED_DATE))
                .then("Current date should be selected by default")
                .validate(calendar.getMonthOrYearHeaderButton().getText(),format("%s %s",
                        currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US),currentDate.getYear()))
                .validate(calendar.getSelectedDayButton(),Integer.toString(currentDate.getDayOfMonth()))
                .and("Click on the Previous Month button")
                .clickOnWebElement(calendar.getPreviousMonthButton())
                .then("Should be displayed previous month")
                .validate(calendar.getMonthOrYearHeaderButton().getText(),format("%s %s",
                        currentDate.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.US),
                        currentDate.getMonth().getValue()==1 ? currentDate.getYear()-1 : currentDate.getYear()))
                .and("Select 1 day of the month and Click on Cancel button")
                .clickOnWebElement(calendar.getDayButtonByValue("1"))
                .clickOnWebElement(filter.getCalendarFilter().getCancelButton())
                .then("Calendar widget should be closed and chip under the table should not appear")
                .waitAndValidate(not(visible), filter.getCalendarFilter().getFilterHeaderLabel())
                .validate(table.getFilterChips().size(),0)
                .and("Select Column Filter 'Updated Date'")
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.UPDATED_DATE))
                .and("Select Last Date of the month")
                .clickOnWebElement(calendar.getDayButtonByValue(String.valueOf(initial.with(lastDayOfMonth()).getDayOfMonth())))
                .and("Click on Submit")
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .validate(visible, table.getChipItemByName(ColumnNames.UPDATED_DATE.getName()).getHeaderLabel())
                .validate(table.countFilterChipsItems(), 1)
                .then("Validate list of selected date")
                .validate(table.getChipItemByName(ColumnNames.UPDATED_DATE.getName()).countFilterOptionsChipItems(), 1)
                .validate(exist, table.getChipItemByName(ColumnNames.UPDATED_DATE.getName()).getChipFilterOptionItemByName(
                        initial.with(lastDayOfMonth()).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .testEnd();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
