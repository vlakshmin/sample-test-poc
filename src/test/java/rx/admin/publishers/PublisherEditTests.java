package rx.admin.publishers;

import io.qameta.allure.Feature;
import pages.Path;
import rx.BaseTest;
import org.testng.annotations.*;
import widgets.common.table.Statuses;
import widgets.common.table.ColumnNames;
import pages.admin.publisher.PublishersPage;
import api.dto.rx.admin.publisher.Publisher;
import widgets.common.categories.CategoriesList;
import com.codeborne.selenide.testng.ScreenShooter;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static com.codeborne.selenide.Condition.*;
import static zutils.FakerUtils.captionWithSuffix;
import static api.preconditionbuilders.PublisherPrecondition.publisher;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature("Publishers")
public class PublisherEditTests extends BaseTest {
    private Publisher publisher;
    private PublishersPage publishersPage;
    private EditPublisherSidebar editPublisherSideBar;

    public PublisherEditTests() {
        publishersPage = new PublishersPage();
        editPublisherSideBar = new EditPublisherSidebar();
    }

    @BeforeMethod
    private void initAndLogin() {

        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .clickBrowserRefreshButton()
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .clickBrowserRefreshButton()
                .testEnd();
    }

    @Test(description = "Create Active Publisher without category", priority = 1)
    private void createActivePublisherWithoutCategory() {

        String publisherName = captionWithSuffix("autoPub");
        publisher = createPublisher(publisherName);

        editAndValidateEditedPublisher(publisherName, true, false);
    }

    @Test(description = "Create Inactive Publisher with category", priority = 2)
    private void createInactivePublisherWithCategory() {

        String publisherName = captionWithSuffix("autoPublisher");
        publisher = createPublisher(publisherName);

        editAndValidateEditedPublisher(publisherName, false, true);
    }

    @Test(description = "Create inactive Publisher without category", priority = 3)
    private void createInactivePublisherWithoutCategory() {

        String publisherName = captionWithSuffix("autoPublisher");
        publisher = createPublisher(publisherName);

        editAndValidateEditedPublisher(publisherName, false, false);
    }

    @Test(description = "Create active Publisher with category", priority = 4)
    private void createActivePublisherWithCategory() {

        String publisherName = captionWithSuffix("autoPublisher");
        publisher = createPublisher(publisherName);

        editAndValidateEditedPublisher(publisherName, true, true);
    }

    @Step("Create Publisher via Api")
    private Publisher createPublisher(String name) {

        return publisher()
                .createNewPublisher(name)
                .build()
                .getPublisherResponse();
    }

    @Step("Edit Publisher")
    private void editAndValidateEditedPublisher(String pubName, boolean isActive, boolean categorySelected) {

        var tableData = publishersPage.getTable().getTableData();
        var categories = editPublisherSideBar.getCategoriesPanel();
        var tableOptions = publishersPage.getTable().getShowHideColumns();
        var tablePagination = publishersPage.getTable().getTablePagination();

        String domainUrl = "https://gmail.com";
        String adOpsPerson = "randomAutoPerson";
        String AdOpsEmail = "randomAutoPub@gmail.com";

        testStart()
                .and(String.format("Search publisher %s", pubName))
                .setValueWithClean(tableData.getSearch(), pubName)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .waitAndValidate(disappear, publishersPage.getTableProgressBar())
                .clickOnTableCellLink(tableData, ColumnNames.NAME, pubName)
                .waitSideBarOpened()
                .then("Check all fields");
        if (!isActive)
            testStart()
                    .turnToggleOff(editPublisherSideBar.getActiveToggle());
        testStart()
                .and("enter publisher name")
                .setValueWithClean(editPublisherSideBar.getNameInput(), pubName)
                .and("enter ad ops person")
                .setValueWithClean(editPublisherSideBar.getAdOpsPerson(), adOpsPerson)
                .and("enter ad ops email")
                .setValueWithClean(editPublisherSideBar.getAdOpsEmail(), AdOpsEmail)
                .and("enter domain url")
                .setValueWithClean(editPublisherSideBar.getDomainInput(), domainUrl)
                .and("select category");
        if (categorySelected)
            testStart()
                    .clickOnWebElement(editPublisherSideBar.getCategories())
                    .clickOnWebElement(categories.getCategoryCheckbox(CategoriesList.EDUCATION));
        testStart()
                .and("click save button")
                .clickOnWebElement(editPublisherSideBar.getSaveButton())
                .scrollIntoView(tableOptions.getShowHideColumnsBtn())
                .and("click show/hide columns button")
                .clickOnWebElement(tableOptions.getShowHideColumnsBtn())
                .and("select unselected checkboxes")
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_BY))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_BY))
                .and("close show/hide columns tab")
                .clickOnWebElement(tableOptions.getShowHideColumnsBtn())
                .and("validate category is correct");
        if (categorySelected)
            testStart()
                    .validate(tableData.getCellByRowValue(ColumnNames.CATEGORY, ColumnNames.NAME, pubName), CategoriesList.ART_ENTERTAIMENTS.getName() + ", "
                            + CategoriesList.AUTOMOTIVE.getName() + ", " + CategoriesList.EDUCATION.getName());
        testStart()
                .and("validate status is correct");
        if (isActive)
            testStart()
                    .validate(tableData.getCellByRowValue(ColumnNames.STATUS, ColumnNames.NAME, pubName), Statuses.ACTIVE.getStatus());
        if (!isActive)
            testStart()
                    .validate(tableData.getCellByRowValue(ColumnNames.STATUS, ColumnNames.NAME, pubName), Statuses.INACTIVE.getStatus());
        testStart()
                .and("validate domain is correct")
                .validate(tableData.getCellByRowValue(ColumnNames.DOMAIN, ColumnNames.NAME, pubName), domainUrl)
                .then("End Test")
                .testEnd();
    }

    @AfterMethod
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}

