package rx.protections;

import api.dto.rx.protection.Protection;
import api.preconditionbuilders.ProtectionsPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.dashbord.DashboardPage;
import pages.protections.ProtectionsPage;
import rx.BaseTest;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class ProtectionsTest extends BaseTest {

    private DashboardPage dashboardPage;
    private Protection protectionResponse;
    private ProtectionsPage protectionsPage;

    public ProtectionsTest() {
        dashboardPage = new DashboardPage();
        protectionsPage = new ProtectionsPage();
    }

    @Test
    public void editProtectionTest() {

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
                .validate(visible, dashboardPage.getLogo())
                .validate(TEST_USER.getMail())
                .waitAndValidate(disappear, dashboardPage.getNuxtProgress())
                .then()
                .clickOnText("Protections")
                .waitAndValidate(disappear, protectionsPage.getTableProgressBar())
                .testEnd();

        //allure serve
    }

}
