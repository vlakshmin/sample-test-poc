package rx;

import api.entities.rx.protection.ProtectionResponse;
import api.services.protections.ProtectionsPrecondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$x;
import static configurations.User.TEST_USER;
import static helpers.Precondition.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsTest extends BaseTest{

    private MainPage mainPage;
    private ProtectionResponse protectionResponse;

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
                .then()
                .clickOnText("Protections")
                .waitAndValidate(Condition.visible, $x(String.format("//*[contains(text(),'%s')]", protectionResponse.getName())))
                .clickOnText(protectionResponse.getName())
                .validate(Condition.visible, $x(mainPage.getLogo()))
                .validate(TEST_USER.getMail())
                .testEnd();

        //allure serve
    }

}
