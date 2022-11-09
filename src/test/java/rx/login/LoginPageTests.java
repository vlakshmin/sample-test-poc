package rx.login;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.admin.user.UserRole;
import api.preconditionbuilders.PublisherPrecondition;
import api.preconditionbuilders.UsersPrecondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import configurations.User;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.Path;
import pages.dashbord.DashboardPage;
import pages.forgotpassword.ForgotPasswordPage;
import pages.profile.ProfilePage;
import rx.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static api.preconditionbuilders.UsersPrecondition.user;
import static configurations.User.*;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class LoginPageTests extends BaseTest {

    private List<UserDto> listUsers = new ArrayList<>();
    private Publisher publisher;
    private ForgotPasswordPage forgotPasswordPage;
    private ProfilePage profilePage;
    private UserDto admin;
    private UserDto singleUser;
    private UserDto crossUser;

    DashboardPage dashboardPage;
    LoginPage loginPage;

    public LoginPageTests(){
        dashboardPage = new DashboardPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();
        loginPage = new LoginPage();
    }

    @BeforeClass
    public void createTestData(){

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("00autoPub1"))
                .build()
                .getPublisherResponse();

        singleUser = UsersPrecondition.user()
                .createSinglePublisherUser(publisher.getId())
                .build()
                .getUserResponse();

        admin = createUser(UserRole.ADMIN);
        crossUser = createUser(UserRole.CROSS_PUBLISHER);
        listUsers.add(singleUser);
        listUsers.add(crossUser);
        listUsers.add(admin);
    }

    @Test(description = "Verify Login Form", priority = 1)
    public void loginFormValidationTest() {
        testStart()
                .openUrl()
                .then()
                .validate(Condition.visible, loginPage.getLoginInput())
                .validate(Condition.visible, loginPage.getPasswordInput())
                .validate(Condition.visible, loginPage.getLogInButton())
                .validate(Condition.visible, loginPage.getSignupButton())
                .validate(Condition.visible, loginPage.getForgotPasswordButton())
                .when()
                .clickOnWebElement(loginPage.getForgotPasswordButton())
                .then()
                .validate(Condition.visible, forgotPasswordPage.getForgotpasswordSubmitButton())
                .validate(Condition.visible, forgotPasswordPage.getForgotpasswordCancelButton())
                .when()
                .clickOnWebElement(forgotPasswordPage.getForgotpasswordCancelButton())
                .clickBrowserRefreshButton()
                .setValueWithClean(loginPage.getLoginInput(),TEST_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),TEST_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();
    }

    @Test(description = "Verify Login Page with invalid Email", priority = 2)
    public void loginInvalidUserTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),WRONG_EMAIL_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),WRONG_EMAIL_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .then()
                .validate(Condition.visible, loginPage.getFailedButton())
                .validateContainsText(loginPage.getFailedButton(), "FAILED!")
                .when()
                .clickBrowserRefreshButton()
                .setValueWithClean(loginPage.getLoginInput(),TEST_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),TEST_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();
    }

    @Test(description = "Verify Login Page with invalid Email", priority = 2)
    public void loginInvalidPasswordTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),WRONG_PASSWORD_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),WRONG_PASSWORD_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .then()
                .validate(Condition.visible, loginPage.getFailedButton())
                .validateContainsText(loginPage.getFailedButton(), "FAILED!")
                .when()
                .clickBrowserRefreshButton()
                .setValueWithClean(loginPage.getLoginInput(),TEST_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),TEST_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();
    }

    @Test(description = "Verify Login Page with valid Single Publisher User", priority = 3)
    public void loginSinglePubUserTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),singleUser.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),"Password1")
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();
        verifyLoginPage(singleUser, UserRole.SINGLE_PUBLISHER.getDefinition());
    }

    @Test(description = "Verify Login Page with valid Cross Publisher User", priority = 4)
    public void loginCrossPubUserTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),crossUser.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),"Password1")
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();

        verifyLoginPage(crossUser, UserRole.CROSS_PUBLISHER.getDefinition());
    }

    @Test(description = "Verify Login Page with valid Admin Publisher User", alwaysRun = true, priority = 5)
    public void loginAdminPubUserTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),admin.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),"Password1")
                .clickEnterButton(loginPage.getPasswordInput())
                .testEnd();

        verifyLoginPage(admin, UserRole.ADMIN.getDefinition());
    }

    @Test(description = "Verify Success button after login", alwaysRun = true, priority = 6)
    public void verifySuccessButtonTest() {
        testStart()
                .openUrl()
                .setValueWithClean(loginPage.getLoginInput(),TEST_USER.getMail())
                .setValueWithClean(loginPage.getPasswordInput(),TEST_USER.getPassword())
                .clickEnterButton(loginPage.getPasswordInput())
                .then()
                .validate(Condition.visible, loginPage.getSuccessButton())
                .validateContainsText(loginPage.getSuccessButton(), "SUCCESS!")
                .testEnd();
    }


    private void verifyLoginPage(UserDto user, String roleDefinition) {
        testStart()
                .then()
                .validate(Condition.visible, dashboardPage.getLogo())
                .validate(user.getMail())
                .when()
                .openDirectPath(Path.PROFILE)
                .then("Verify user Settings card")
                .validate(profilePage.getPageTitle(), user.getName())
                .validate(profilePage.getEmailLabel(), user.getMail())
                .validate(profilePage.getRoleLabel(), roleDefinition)
                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    private void logout(){
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @Step("Create user")
    private UserDto createUser(UserRole role){

        return UsersPrecondition.user()
                       .createNewUser(role)
                        .build()
                        .getUserResponse();
    }

    @AfterTest(alwaysRun = true)
    private void deleteEntities() {
        if (listUsers != null) {
            System.out.println(listUsers.size());
            for (UserDto users : listUsers) {
                System.out.println("User ID to delete : "+users.getId());
                deleteUser(users.getId());
            }
        }
    }

    private void deleteUser(Integer id) {
        user()
                .setCredentials(USER_FOR_DELETION)
                .deleteUser(id);
    }

}
