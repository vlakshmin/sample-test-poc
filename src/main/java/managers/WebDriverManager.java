package managers;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import configurations.ConfigurationLoader;
import configurations.EnvConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static configurations.ConfigurationLoader.getConfig;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.openqa.selenium.remote.CapabilityType.TAKES_SCREENSHOT;

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
            driver = WebDriverRunner.getWebDriver();
            try {
                log.info("Accepting browser alerts if they are presented");
                driver.switchTo().alert().accept();
                log.info("Closing webDriver in thread {}", Thread.currentThread().getId());
                driver.quit();
            } catch (NoAlertPresentException e) {
                log.info("Closing webDriver in thread {}", Thread.currentThread().getId());
                driver.quit();
            }
        } else {
            log.info("WebDriver will not close the session due to holdBrowserOpened = 'true'");
        }
    }

    public String getBrowserLogs() {
        log.info("Collecting Browser Log");

        return WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER)
                .getAll().stream()
                .map(LogEntry::toString)
                .collect(Collectors.joining("\n"));
    }

    public void attachTheBrowserConsoleLog() {
        log.info("Attaching to Allure Report");
        try {
            InputStream is = new ByteArrayInputStream(this.getBrowserLogs().getBytes(StandardCharsets.UTF_8));
            addAttachment("Browser log", is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
