package rx;

import api.dto.rx.protection.Protection;
import api.preconditionbuilders.ProtectionsPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsTest extends BaseTest{

    private MainPage mainPage;
    private Protection protectionResponse;

    public ProtectionsTest(){
        mainPage = new MainPage();
    }

    @Test
    public void editProtectionTest(){

        //Creating protection to edit Using API
        protectionResponse = ProtectionsPrecondition.protection()
                .createNewRandomProtection()
                .build()
                .getProtectionsResponse();

        //Opening Browser and Edit the protection created from Precondition
        testStart()
                .given()
                .openUrl()
                .logIn(TEST_USER)
                .validate(visible, mainPage.getLogo())
                .validate(TEST_USER.getMail())
                .waitAndValidate(disappear, mainPage.getNuxtProgress())
                .then()
                .clickOnText("Protections")
                .waitAndValidate(disappear, mainPage.getTableProgressBar())
        .testEnd();

        //allure serve
    }

}
