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
    private ProfilePage profilePage;
    private UserDto admin;
    private UserDto singleUser;
    private UserDto crossUser;

    DashboardPage dashboardPage;
    LoginPage loginPage;

    public LoginPageTests(){
        dashboardPage = new DashboardPage();
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
    }

    @Test(description = "Verify Login Page with valid Single Publisher User", priority = 1)
    public void loginSinglePubUserTest() {
     //   verifyLoginPage(UserRole.SINGLE_PUBLISHER, "Single-Publisher");
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
  //      verifyLoginPage(UserRole.CROSS_PUBLISHER, "Cross-Publishers");

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
   //     verifyLoginPage(UserRole.ADMIN, UserRole.ADMIN.getDefinition());
    }


    private void verifyLoginPage(UserDto user, String roleDefinition) {

        testStart()
//                .openUrl()
//                .setValueWithClean(loginPage.getLoginInput(),user.getMail())
//                .setValueWithClean(loginPage.getPasswordInput(),"Password1")
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

//    private void verifyLoginPage(UserRole role, String role_definiton) {
//        createUser(role);
//        testStart()
//                .openUrl()
//                .logIn(TEMP_USER)
//                .then()
//                .validate(Condition.visible, dashboardPage.getLogo())
//                .validate(TEMP_USER.getMail())
//                .when()
//                .openDirectPath(Path.PROFILE)
//                .then("Verify user Settings card")
//                .validate(profilePage.getPageTitle(), user.getName())
//                .validate(profilePage.getEmailLabel(), user.getMail())
//                .validate(profilePage.getRoleLabel(), role_definiton)
//                .and()
//                .logOut()
//                .testEnd();
//    }

//    private void createUsers(UserRole role) {
//        switch(role.getRole()) {
//            case 0:
//                publisher = publisher()
//                        .createNewPublisher(captionWithSuffix("000000000autoPub1"))
//                        .build()
//                        .getPublisherResponse();
//
//                user = UsersPrecondition.user()
//                        .createSinglePublisherUser(publisher.getId())
//                        .build()
//                        .getUserResponse();
//                listUsers.add(user);
//                System.out.println(listUsers);
//                break;
//            case 1: case 4:
//                user = UsersPrecondition.user()
//                        .createNewUser(role)
//                        .build()
//                        .getUserResponse();
//                listUsers.add(user);
//                break;
//        }
//    }

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
