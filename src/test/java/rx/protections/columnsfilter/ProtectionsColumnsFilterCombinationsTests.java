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
import java.util.stream.Collectors;

import static api.preconditionbuilders.ProtectionsPrecondition.protection;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
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

    @Test(description = "Combination filters only for Inactive Protections")
    public void defaultColumnsFilter(){
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();
        var tableData = protectionPage.getProtectionsTable().getTableData();

        testStart()
                .and("Select Column Filter 'Status'")
                .clickOnWebElement(filter.getColumnsFilterButton())
                .waitAndValidate(visible, filter.getFilterOptionsMenu())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.STATUS))
                .and("Select Inactive option")
                .selectRadioButton(filter.getActiveBooleanFilter().getInactiveRadioButton())
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("Check filtered data")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.ID.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.ID),
                        allProtectionsList.stream().filter(e -> e.getActive().equals("false")).map(pr -> pr.getId().toString()).collect(Collectors.toList()))
                .testEnd();
    }

    private List<Protection> getAllProtectionBE(){

        return protection()
                .getAllProtectionsList()
                .build()
                .getProtectionsGetAllResponse()
                .getItems();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
