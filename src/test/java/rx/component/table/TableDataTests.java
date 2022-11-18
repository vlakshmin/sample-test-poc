package rx.component.table;

import api.dto.rx.admin.publisher.Publisher;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import rx.BaseTest;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class TableDataTests extends BaseTest {

    private Publisher publisher;
    private PublishersPage publishersPage;
    private EditPublisherSidebar editPublisherSidebar;

    public TableDataTests() {
        publishersPage = new PublishersPage();
        editPublisherSidebar = new EditPublisherSidebar();
    }

    @BeforeClass
    public void createNewPublisher() {

        //Creating publisher to edit Using API
        publisher = PublisherPrecondition.publisher()
                .createNewPublisher()
                .build()
                .getPublisherResponse();
    }

    @Test
    public void checkColumns() {
        var table = publishersPage.getTable().getTableOptions();
        var tableData = publishersPage.getTable().getTableData();
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .clickOnWebElement(table.getTableOptionsBtn())
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.CATEGORY))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.ID))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.DOMAIN))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.CURRENCY))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.AD_OPS_PERSON))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.MAIL))
                .clickOnWebElement(table.getTableOptionsBtn())
                .then()
                .validateListSize((ElementsCollection) tableData.getColumns(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.CATEGORY.getName(),
                        ColumnNames.ACTIVE.getName(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DOMAIN.getName(),
                        ColumnNames.CURRENCY.getName(),
                        ColumnNames.AD_OPS_PERSON.getName(),
                        ColumnNames.MAIL.getName())

                .clickOnWebElement(table.getTableOptionsBtn())
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.CATEGORY))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.DOMAIN))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.CURRENCY))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.AD_OPS_PERSON))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.MAIL))
                .then()
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CATEGORY.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ACTIVE.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.DOMAIN.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CURRENCY.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.AD_OPS_PERSON.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.MAIL.getName()))
                .validate(visible, tableData.getColumnHeader(ColumnNames.ID.getName()))
                .and()
                .clickOnWebElement(table.getTableOptionsBtn())
                .logOut()
                .testEnd();

    }

    @Test
    public void checkPagination() {
        var table = publishersPage.getTable().getTableOptions();
        var tableData = publishersPage.getTable().getTableData();
        var tablePagenation = publishersPage.getTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .clickOnWebElement(table.getTableOptionsBtn())
                .selectRadioButton(table.getStatusItemRadio(Statuses.ACTIVE))
                .clickOnWebElement(table.getTableOptionsBtn())
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "10")
                .waitLoading(visible, publishersPage.getTableProgressBar())
                .waitLoading(disappear, publishersPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 10)
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        Statuses.ACTIVE.getStatus())
                .clickOnWebElement(tablePagenation.getNext())
                .waitLoading(visible, publishersPage.getTableProgressBar())
                .waitLoading(disappear, publishersPage.getTableProgressBar())
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE),
                        Statuses.ACTIVE.getStatus())
                .logOut()
                .testEnd();

    }

    @Test
    public void checkSearch() {
        var table = publishersPage.getTable().getTableOptions();
        var tableData = publishersPage.getTable().getTableData();
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .selectCheckBox(tableData.getCheckbox(1))
                .setValueWithClean(tableData.getSearch(), publisher.getName())
                .clickEnterButton(tableData.getSearch())
                .waitLoading(visible, publishersPage.getTableProgressBar())
                .waitLoading(disappear, publishersPage.getTableProgressBar())
                .then()
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.PUBLISHER),
                        publisher.getName())
                .and()
                .clickOnTableCellLink(tableData, ColumnNames.PUBLISHER, publisher.getName())
                .waitSideBarOpened()
                .clickOnWebElement(editPublisherSidebar.getSaveButton())
                .waitSideBarClosed()
                .logOut()
                .testEnd();

    }


}
