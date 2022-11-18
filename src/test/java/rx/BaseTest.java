package rx;

import zutils.VariablesStore;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import managers.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import pages.login.LoginPage;

@Slf4j
public abstract class BaseTest {

    protected SoftAssert SA;
    protected LoginPage loginPage;
    protected VariablesStore variablesStore;
    protected WebDriverManager webDriverManager;

    @BeforeClass
    @Step("SetUp")
    public void setUp() {
        SA = new SoftAssert();
        loginPage = new LoginPage();
        variablesStore = new VariablesStore();
        String currentTestClassName = this.getClass().asSubclass(this.getClass()).getName();
        webDriverManager = new WebDriverManager(currentTestClassName);

        log.info("Current test class {}", currentTestClassName);
        log.info("Adding Selenide listener");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(
                        false)
        );
    }

    @Step("Close WebDriver Session")
    protected void closeTheSession() {
        webDriverManager.closeWebDriverSession();
    }

    @AfterClass(alwaysRun = true)
    public void closeSessionAndConsoleLogAttach() {
        attachTheBrowserConsoleLog();
        closeTheSession();
    }

    @Step("Attach The Browser Console log")
    protected void attachTheBrowserConsoleLog() {
        webDriverManager.attachTheBrowserConsoleLog();
    }
}
