package rx.admin.publishers;

import pages.Path;
import rx.BaseTest;
import io.qameta.allure.Feature;
import widgets.common.table.Statuses;
import widgets.common.table.ColumnNames;
import pages.admin.publisher.PublishersPage;
import widgets.common.categories.CategoriesList;
import com.codeborne.selenide.testng.ScreenShooter;
import widgets.admin.publisher.sidebar.CurrencyType;
import widgets.admin.publisher.sidebar.CreatePublisherSidebar;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static com.codeborne.selenide.Condition.*;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature("Publishers")

public class PublisherCreateTests extends BaseTest {
    private String pubName = "randomAutoPub";
    private String domainUrl = "https://gmail.com";
    private String adOpsPerson = "randomAutoPerson";
    private String AdOpsEmail = "randomAutoPub@gmail.com";

    private PublishersPage publishersPage;
    private CreatePublisherSidebar createPublisherSidebar;

    public PublisherCreateTests() {
        publishersPage = new PublishersPage();
        createPublisherSidebar = new CreatePublisherSidebar();
    }

    @BeforeClass
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

    @Test(description = "Create Active Publisher without category Test", priority = 1)
    private void createActivePublisherWithoutCategory() {

        createAndValidatedCreatedPublisher(captionWithSuffix(pubName), true, false);
    }

    @Test(description = "Create Inactive Publisher with category Test", priority = 2)
    private void createInactivePublisherWithCategory() {

        createAndValidatedCreatedPublisher(captionWithSuffix(pubName), false, true);
    }

    @Test(description = "Create inactive Publisher without category Test", priority = 3)
    private void createInactivePublisherWithoutCategory() {

        createAndValidatedCreatedPublisher(captionWithSuffix(pubName), false, false);
    }

    @Test(description = "Create active Publisher with category Test", priority = 4)
    private void createActivePublisherWithCategory() {

        createAndValidatedCreatedPublisher(captionWithSuffix(pubName), true, true);
    }

    @Step("Create Media {0} with platform type {1}")
    private void createAndValidatedCreatedPublisher(String pubName, boolean isActive, boolean categorySelected) {
        var tableData = publishersPage.getTable().getTableData();
        var categories = createPublisherSidebar.getCategoriesPanel();
        var tableOptions = publishersPage.getTable().getShowHideColumns();

        testStart()
                .and("click on create publisher button")
                .clickOnWebElement(publishersPage.getCreatePublisherButton())
                .waitSideBarOpened()
                .and("turn active on");
        if (!isActive)
            testStart()
                    .turnToggleOff(createPublisherSidebar.getActiveToggle());
        testStart()
                .and("enter publisher name")
                .setValue(createPublisherSidebar.getNameInput(), pubName)
                .and("enter ad ops person")
                .setValue(createPublisherSidebar.getAdOpsPerson(), adOpsPerson)
                .and("enter ad ops email")
                .setValue(createPublisherSidebar.getAdOpsEmail(), AdOpsEmail)
                .and("enter domain url")
                .setValueWithClean(createPublisherSidebar.getDomainInput(), domainUrl)
                .and("select currency")
                .selectFromDropdown(createPublisherSidebar.getCurrencyDropdown(),
                        createPublisherSidebar.getCurrencyDropdownItems(), CurrencyType.USD.getType())
                .and("select category");
        if (categorySelected)
            testStart()
                    .clickOnWebElement(createPublisherSidebar.getCategories())
                    .clickOnWebElement(categories.getCategoryCheckbox(CategoriesList.ART_ENTERTAIMENTS));
        testStart()
                .and("click save button")
                .clickOnWebElement(createPublisherSidebar.getSaveButton())
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
                    .validate(tableData.getCellByRowValue(ColumnNames.CATEGORY, ColumnNames.NAME, pubName), CategoriesList.ART_ENTERTAIMENTS.getName());
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

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}

