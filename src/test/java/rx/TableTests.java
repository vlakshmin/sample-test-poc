package rx;

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
import widgets.common.table.*;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class TableTests extends BaseTest {

    private Publisher publisher;
    private PublishersPage publishersPage;
    private Table table;
    private TablePagination tablePagination;
    private TableOptions tableOptions;

    public TableTests() {
        publishersPage = new PublishersPage();
        table = new Table();
        tableOptions = new TableOptions();
        tablePagination = new TablePagination();
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
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CATEGORY))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ID))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DOMAIN))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CURRENCY))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.AD_OPS_PERSON))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.MAIL))
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .validateList((ElementsCollection) table.getColumns(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.CATEGORY.getName(),
                        ColumnNames.ACTIVE.getName(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DOMAIN.getName(),
                        ColumnNames.CURRENCY.getName(),
                        ColumnNames.AD_OPS_PERSON.getName(),
                        ColumnNames.MAIL.getName())

                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CATEGORY))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DOMAIN))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CURRENCY))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.AD_OPS_PERSON))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.MAIL))
                .validate(not(visible), table.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.CATEGORY.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.ACTIVE.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.DOMAIN.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.CURRENCY.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.AD_OPS_PERSON.getName()))
                .validate(not(visible), table.getColumnHeader(ColumnNames.MAIL.getName()))
                .validate(visible, table.getColumnHeader(ColumnNames.ID.getName()))
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .logOut()
        .testEnd();

    }

    @Test
    public void checkPagenation() {
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectRadioButton(tableOptions.getStatusItemRadio(Statuses.ACTIVE))
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectFromDropdown(tablePagination.getPageMenu(), tablePagination.getRowNumbersList(), "10")
                .waitAndValidate(disappear, publishersPage.getTableProgressBar())
            //    .waitAndValidate(not(visible), publishersPage.getTableProgressBar())
                .validateList(table.getRows(), 10)
                .validateListContainsTextOnly(table.getCustomCells(ColumnNames.ACTIVE),
                        Statuses.ACTIVE.getStatus())
                .clickOnWebElement(tablePagination.getNext())
                .waitAndValidate(disappear, publishersPage.getTableProgressBar())
           //     .waitAndValidate(not(visible), publishersPage.getTableProgressBar())
                .validateListContainsTextOnly(table.getCustomCells(ColumnNames.ACTIVE),
                        Statuses.ACTIVE.getStatus())
                .logOut()
        .testEnd();

    }

    @Test
    public void checkSearch() {
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .setValueWithClean(table.getSearch(), publisher.getName())
                .clickEnterButton(table.getSearch())
                .waitAndValidate(disappear, publishersPage.getTableProgressBar())
             //   .waitAndValidate(not(visible), publishersPage.getTableProgressBar())
                .validateListContainsTextOnly(table.getCustomCells(ColumnNames.PUBLISHER),
                       publisher.getName())
                .and()
                //.clickOnWebElement(publishersPage.getPublisherItemByName(publisher.getName()).getPublisherName())
                .clickOnTableCellLink(ColumnNames.PUBLISHER, publisher.getName())
                .waitSideBarOpened()
                .clickOnWebElement(publishersPage.getEditPublisherSidebar().getSaveButton())
                .waitSideBarClosed()
                .logOut()
        .testEnd();

    }


}
