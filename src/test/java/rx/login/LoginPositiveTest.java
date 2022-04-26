package rx.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import rx.BaseTest;

import static com.codeborne.selenide.Selenide.$x;
import static configurations.User.TEST_USER;
import static helpers.Precondition.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class LoginPositiveTest extends BaseTest {

    MainPage mainPage;

    public LoginPositiveTest(){
        mainPage = new MainPage();
    }

    @Test
    @Step("Login Positive Tests")
    public void loginPositiveTest() {
        testStart()
                .openUrl()
                .logIn(TEST_USER)
                .then()
                .validate(Condition.visible, $x(mainPage.getLogo()))
                .validate(TEST_USER.getMail())
        .testEnd();
    }

    @AfterClass
    @Step("LogOut")
    public void logOut() {
        testStart()
                .logOut()
       .testEnd();
    }
}
