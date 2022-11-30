package rx.admin.users;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.admin.user.UserRole;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.admin.users.UsersPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.admin.users.sidebar.EditUserSidebar;
import widgets.common.table.Statuses;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static api.preconditionbuilders.UsersPrecondition.*;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class UsersEditTests extends BaseTest {

    private UsersPage usersPage;
    private UserDto user;
    private Publisher publisher;
    private EditUserSidebar editUserSidebar;


    public UsersEditTests() {
        usersPage = new UsersPage();
        editUserSidebar = new EditUserSidebar();
    }

    @BeforeClass
    public void initAndLogin() {
        testStart()
                .given()
                .openDirectPath(Path.USER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, usersPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Create Single-publisher Active User and update to Cross-Publisher and deactivate user")
    public void editSinglePublisherUserToCross() {
        user = createSingleUser();
        editUser(user, UserRole.SINGLE_PUBLISHER, UserRole.CROSS_PUBLISHER);
    }

    @Test(description = "Create Single-publisher Active User and update to Admin-Publisher and deactivate user")
    public void editSinglePublisherUserToAdmin() {
        user = createSingleUser();
        editUser(user, UserRole.SINGLE_PUBLISHER, UserRole.ADMIN);
    }

    @Test(description = "Create Cross-publisher Active User and update to Admin-Publisher and deactivate user")
    public void editCrossPublisherUserToAdmin() {
        user = createAdminUser(UserRole.CROSS_PUBLISHER);
        editUser(user, UserRole.CROSS_PUBLISHER, UserRole.ADMIN);
    }

    @Test(description = "Create Cross-publisher Active User and update to Single-Publisher and deactivate user")
    public void editCrossPublisherUserToSingle() {
        user = createAdminUser(UserRole.CROSS_PUBLISHER);
        editUser(user, UserRole.CROSS_PUBLISHER, UserRole.SINGLE_PUBLISHER);
    }

    @Test(description = "Create Admin-publisher Active User and update to Cross-Publisher and deactivate user")
    public void editAdminPublisherUserToCross() {
        user = createAdminUser(UserRole.ADMIN);
        editUser(user, UserRole.ADMIN, UserRole.CROSS_PUBLISHER);
    }

    @Test(description = "Create Cross-publisher Active User and update to Single-Publisher and deactivate user")
    public void editAdminPublisherUserToSingle() {
        user = createAdminUser(UserRole.ADMIN);
        editUser(user, UserRole.ADMIN, UserRole.SINGLE_PUBLISHER);
    }

    @Step("Create Media via Api")
    private UserDto createSingleUser() {
        publisher = publisher()
                .createNewPublisher(captionWithSuffix("00003-autoPub"))
                .build()
                .getPublisherResponse();

        return user()
                .createSinglePublisherUser(publisher.getId())
                .build()
                .getUserResponse();
    }
    private UserDto createAdminUser(UserRole role) {
            return user()
                    .createNewUser(role)
                    .build()
                    .getUserResponse();
    }

    @Step("Edit User")
    private void editUser(UserDto user, UserRole role, UserRole updatedRole) {

        var tableData = usersPage.getUsersTable().getTableData();
        var tablePagination = usersPage.getUsersTable().getTablePagination();
        var tableOptions = usersPage.getUsersTable().getShowHideColumns();
        var userNameUpdated = user.getName()+"updated";
        var emailUpdated = user.getMail().split("@")[0]+"updated@test.com";


        testStart()
                .and(String.format("Search user %s", user.getName()))
                .setValueWithClean(tableData.getSearch(), user.getName())
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.NAME, user.getName())
                .waitSideBarOpened()
                .then("Check all fields")
                .validateAttribute(editUserSidebar.getActiveToggle(), "aria-checked", "true")
                .validateAttribute(editUserSidebar.getUsernameInput(), "value", user.getName())
                .validate(editUserSidebar.getEmailInput().getText(), user.getMail())
                .testEnd();
        if (role.getRole().equals(UserRole.SINGLE_PUBLISHER)) {
            testStart()
                    .validate(checked, editUserSidebar.getSinglePublisherRadioButton())
                    .validate(enabled, editUserSidebar.getPublisherNameInput())
                    .validate(editUserSidebar.getPublisherInput(), user.getPublisherName())
                    .testEnd();
        }

        testStart()
                .then("Edit all fields")
                .turnToggleOff(editUserSidebar.getActiveToggle())
                .setValueWithClean(editUserSidebar.getUsernameInput(), userNameUpdated)
                .setValueWithClean(editUserSidebar.getEmailInput(), emailUpdated)
                .selectRadioButton(editUserSidebar.getCrossPublisherRadioButton())
                .testEnd();
        if (updatedRole.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            publisher = publisher()
                    .createNewPublisher(captionWithSuffix("00003-autoPub"))
                    .build()
                    .getPublisherResponse();

            testStart()
                    .selectRadioButton(editUserSidebar.getSinglePublisherRadioButton())
                    .selectFromDropdown(editUserSidebar.getPublisherInput(),
                            editUserSidebar.getPublisherDropdownItems(), publisher.getName())
                    .testEnd();
        }
        else if (updatedRole.equals(UserRole.CROSS_PUBLISHER)) {
            testStart()
                    .selectRadioButton(editUserSidebar.getCrossPublisherRadioButton())
                    .testEnd();
        }
        else {
            testStart()
                    .selectRadioButton(editUserSidebar.getAdminRadioButton())
                    .testEnd();
        }

        testStart()
                .clickOnWebElement(editUserSidebar.getSaveButton())
                .and("Error message is absent")
                .waitAndValidate(not(visible), editUserSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .and("Search new media")
                .setValueWithClean(tableData.getSearch(), userNameUpdated)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.NAME, userNameUpdated)
                .waitSideBarOpened()
                .then("Check all fields")
                .validateAttribute(editUserSidebar.getActiveToggle(), "aria-checked", "false")
                .validateAttribute(editUserSidebar.getUsernameInput(), "value", userNameUpdated)
                .testEnd();

        testStart()
                .and("Click Save")
                .clickOnWebElement(editUserSidebar.getSaveButton())
                .waitAndValidate(not(visible), editUserSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .validate(tableData.getCellByRowValue(ColumnNames.ID, ColumnNames.NAME, userNameUpdated), user.getId().toString())
                .validate(tableData.getCellByRowValue(ColumnNames.ACTIVE_INACTIVE, ColumnNames.NAME, userNameUpdated), Statuses.INACTIVE.getStatus())
                .validate(tableData.getCellByRowValue(ColumnNames.NAME, ColumnNames.NAME, userNameUpdated), userNameUpdated)
                .testEnd();

        if (updatedRole.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            testStart()
                    .validate(tableData.getCellByRowValue(ColumnNames.PUBLISHER, ColumnNames.NAME, userNameUpdated), user.getPublisherName())
                    .testEnd();
        }

        testStart()
                .clickOnWebElement(tableData.getClear())
                .then("Validate that text in table footer '1-20 of X")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-20 of ")
                .testEnd();
    }

    @AfterTest(alwaysRun = true)
    private void deleteEntities() {
        deleteUser(user.getId());
    }

    private void deleteUser(Integer id) {
        user()
                .setCredentials(USER_FOR_DELETION)
                .deleteUser(id)
                .build();
    }

    @AfterClass
    public void logout() {
        testStart()
                .logOut()
                .testEnd();

    }
}

