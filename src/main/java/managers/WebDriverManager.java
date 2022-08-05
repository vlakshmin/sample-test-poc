package managers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import configurations.EnvConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static configurations.ConfigurationLoader.getConfig;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.Thread.currentThread;

@Slf4j
public class WebDriverManager {

    private WebDriver driver;
    private static String currentTestClassName;
    private static EnvConfiguration config = getConfig();

    public WebDriverManager(String testDisplayName) {
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.holdBrowserOpen = config.getHoldBrowserOpen();
        Configuration.reopenBrowserOnFail = config.getReopenBrowserOnFail();
        Configuration.screenshots = config.getScreenshots();
        Configuration.timeout = config.getTimeout();
        Configuration.headless = config.getHeadless();
        currentTestClassName = testDisplayName;
        setProxyEnabled(config.getEnableProxy());
        Configuration.startMaximized = true;
        Configuration.driverManagerEnabled = true;
        Configuration.browser = config.getBrowser();
    }

    private void setProxyEnabled(boolean enabled) {
        if (enabled) {
            Configuration.proxyEnabled = true;
            Configuration.fileDownload = PROXY;
        } else {
            Configuration.proxyEnabled = false;
        }
    }

    public void closeWebDriverSession() {
        log.info("Current test class {}", currentTestClassName);
        if (!config.getHoldBrowserOpen()) {
            log.info("Checking weather WebDriver session has been started in thread {}", currentThread().getId());
            try {
                driver = WebDriverRunner.getWebDriver();
                this.closeBrowserAlertsIfPresent();

            } catch (NullPointerException e) {
                log.warn("WebDriver is null  in thread {}", currentThread().getId());
            } catch (NoSuchSessionException e) {
                log.warn("WebDriver Session hasn't been started in thread {}. Nothing to close", currentThread().getId());
            } catch (IllegalStateException e) {
                log.warn("No WebDriver is bound to current thread: '{}'. You need to call open(url) first.", currentThread().getId());
            }
        } else {
            log.info("WebDriver will not close the session due to holdBrowserOpened = 'true'");
        }
    }

    public String getBrowserLogs() {
        //Todo add check if Selenide has been started
        log.info("Collecting Browser Log");
        String browserLogString = "";
        try {
            browserLogString = driver.manage().logs().get(LogType.BROWSER)
                    .getAll().stream()
                    .map(LogEntry::toString)
                    .collect(Collectors.joining("\n"));
        } catch (NullPointerException e) {
            log.warn("WebDriver is null  in thread {}", currentThread().getId());
        } catch (NoSuchSessionException e) {
            log.warn("WebDriver Session hasn't been started in thread {}. Nothing to close", currentThread().getId());
        } catch (IllegalStateException e) {
            log.warn("No WebDriver is bound to current thread: '{}'. You need to call open(url) first.", currentThread().getId());
        }

        return browserLogString;
    }

    public void attachTheBrowserConsoleLog() {
        try {
            if (driver != null) {
                log.info("Attaching Browser Console log to Allure Report");
                InputStream is = new ByteArrayInputStream(this.getBrowserLogs().getBytes(StandardCharsets.UTF_8));
                addAttachment("Browser log", is);
            } else {
                log.warn("Can't attach browser log because Browser wasn't started");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeBrowserAlertsIfPresent() {
        try {
            log.info("Accepting browser alerts if they are presented");
            driver.switchTo().alert().accept();
            log.info("Closing webDriver session in thread {}", currentThread().getId());
            driver.quit();
        } catch (NoAlertPresentException e) {
            log.warn("Closing webDriver session in thread {}", currentThread().getId());
            driver.quit();
        }
    }
}
