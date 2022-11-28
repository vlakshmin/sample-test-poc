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
import widgets.admin.users.sidebar.CreateUserSidebar;
import widgets.common.table.ColumnNames;
import widgets.common.table.Statuses;
import widgets.inventory.media.sidebar.CreateMediaSidebar;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;
import static zutils.FakerUtils.randomMail;

@Slf4j
@Listeners({ScreenShooter.class})
public class UsersCreationTests extends BaseTest {
    private UsersPage usersPage;

    private List<UserDto> listUsers = new ArrayList<>();
    private Publisher publisher;
    private CreateMediaSidebar createMediaSidebar;


    private CreateUserSidebar createUserSidebar;


    public UsersCreationTests() {
    	usersPage = new UsersPage();
        createUserSidebar = new CreateUserSidebar();
        createMediaSidebar = new CreateMediaSidebar();
    }

    @BeforeClass
    private void initAndLogin() {
        testStart()
                .given()
                .openDirectPath(Path.USER)
                .clickBrowserRefreshButton()
                .logIn(TEST_USER)
                .waitAndValidate(disappear, usersPage.getNuxtProgress())
                .clickBrowserRefreshButton()
                .testEnd();
    }

    @Test(description = "Create Single-Publisher active User")
    public void createActiveSingleUser() {
        var userName = captionWithSuffix("autoUserSingle");
        createAndCheckCreatedUser(userName, UserRole.SINGLE_PUBLISHER.getDefinition(), randomMail(), Statuses.ACTIVE.name());
    }

    @Test(description = "Create Cross-Publisher active User")
    public void createActiveCrossUser() {
        var userName = captionWithSuffix("autoUserCross");
        createAndCheckCreatedUser(userName, UserRole.CROSS_PUBLISHER.getDefinition(), randomMail(), Statuses.ACTIVE.name());
    }

    @Test(description = "Create Admin-Publisher active User")
    public void createActiveAdminUser() {
        var userName = captionWithSuffix("autoUserAdmin");
        createAndCheckCreatedUser(userName, UserRole.ADMIN.getDefinition(), randomMail(), Statuses.ACTIVE.name());
    }

    @Test(description = "Create Single-Publisher inactive User")
    public void createInactiveSingleUser() {
        var userName = captionWithSuffix("autoUserSingleInactive");
        createAndCheckCreatedUser(userName, UserRole.SINGLE_PUBLISHER.getDefinition(), randomMail(), Statuses.INACTIVE.name());
    }

    @Test(description = "Create Cross-Publisher active User")
    public void createInactiveCrossUser() {
        var userName = captionWithSuffix("autoUserCrossInactive");
        createAndCheckCreatedUser(userName, UserRole.CROSS_PUBLISHER.getDefinition(), randomMail(), Statuses.INACTIVE.name());
    }

    @Test(description = "Create Admin-Publisher active User")
    public void createInactiveAdminUser() {
        var userName = captionWithSuffix("autoUserAdminInactive");
        createAndCheckCreatedUser(userName, UserRole.ADMIN.getDefinition(), randomMail(), Statuses.INACTIVE.name());
    }

    @Step("Create User {0} with user type {1} and status {3}")
    private void createAndCheckCreatedUser(String userName, String userType, String emailAddress, String status) {
        var tableData = usersPage.getUsersTable().getTableData();
        var tableOptions = usersPage.getUsersTable().getShowHideColumns();
        var tablePagination = usersPage.getUsersTable().getTablePagination();

        if (userType.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            publisher = publisher()
                    .createNewPublisher(captionWithSuffix("00003-autoPub"))
                    .build()
                    .getPublisherResponse();
        }

        testStart()
                .clickOnWebElement(usersPage.getCreateUserButton())
                .waitSideBarOpened()
                .setValueWithClean(createUserSidebar.getUsernameInput(), userName)
                .setValueWithClean(createUserSidebar.getEmailInput(), emailAddress)
                .setValueWithClean(createUserSidebar.getPasswordInput(), "Password1")
                .testEnd();

        if(status.equals(Statuses.INACTIVE.name()))
        {
            testStart()
                    .turnToggleOff(createUserSidebar.getActiveToggle())
                    .testEnd();

        }

        if (userType.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            testStart()
                    .selectFromDropdown(createMediaSidebar.getPublisherInput(),
                            createMediaSidebar.getPublisherDropdownItems(), publisher.getName())
                    .and("Fill Name")
                    .testEnd();
        } else if (userType.equals(UserRole.CROSS_PUBLISHER.getDefinition())) {
            testStart()
                    .selectRadioButton(createUserSidebar.getCrossPublisherRadioButton())
                    .testEnd();
        }
        else {
            testStart()
                    .selectRadioButton(createUserSidebar.getAdminRadioButton())
                    .testEnd();
        }
        testStart()
                .clickOnWebElement(createUserSidebar.getSaveButton())
                .and("Error message is absent")
                .waitAndValidate(not(visible), createUserSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .and("Search new media")
                .setValueWithClean(tableData.getSearch(), userName)
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.NAME, userName)
                .waitSideBarOpened()
                .then("Check all fields")
                .validateAttribute(createUserSidebar.getUsernameInput(), "value", userName)
                .validateAttribute(createUserSidebar.getEmailInput(), "value", emailAddress)
                .testEnd();

        if (userType.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            testStart()
                    .validate(createMediaSidebar.getPublisherInput(), publisher.getName())
                    .testEnd();
        }
        testStart()
                .and("Click Save")
                .clickOnWebElement(createUserSidebar.getSaveButton())
                .waitAndValidate(not(visible), createUserSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .and("Toaster Error message is absent")
                .waitAndValidate(not(visible), usersPage.getToasterMessage().getPanelError())
                .and("Show column 'Created Date'")
                .clickOnWebElement(tableOptions.getShowHideColumnsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .then("Validate data in table")
                .validate(tableData.getCellByRowValue(ColumnNames.ACTIVE_INACTIVE, ColumnNames.NAME, userName), status)
                .testEnd();

        if (userType.equals(UserRole.SINGLE_PUBLISHER.getDefinition())) {
            testStart()
                    .validate(tableData.getCellByRowValue(ColumnNames.PUBLISHER, ColumnNames.NAME, userName), publisher.getName())
                    .testEnd();
        }

        testStart()
                .validate(tableData.getCellByRowValue(ColumnNames.EMAIL, ColumnNames.NAME, userName), emailAddress)
                .validate(tableData.getCellByRowValue(ColumnNames.ROLE, ColumnNames.NAME, userName), userType)
                .clickOnWebElement(tableData.getClear())
                .then("Validate that text in table footer '1-20 of X")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-20 of ")
                .testEnd();
    }


    @AfterClass(alwaysRun = true)
    public void logout(){
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

}
