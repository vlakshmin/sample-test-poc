package managers;

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
        this.currentTestClassName = testDisplayName;
        setProxyEnabled(config.getEnableProxy());
        setSelenoidEnabled(config.getEnableSelenoid());
    }

    private void setSelenoidEnabled(boolean enabled) {
        if (enabled) {
            Configuration.driverManagerEnabled = false;
            getProvider();
        } else {
            Configuration.driverManagerEnabled = true;
            Configuration.browser = config.getBrowser();
            Configuration.startMaximized = true;
        }
    }

    private void setProxyEnabled(boolean enabled) {
        if (enabled) {
            Configuration.proxyEnabled = true;
            Configuration.fileDownload = PROXY;
        } else {
            Configuration.proxyEnabled = false;
        }
    }

    private void getProvider() {

        String currentBrowser = config.getBrowser();
        switch (currentBrowser) {
            case "chrome":
                Configuration.browser = CustomProvider.class.getName();
                break;
            case "edge":
                Configuration.browser = CustomProviderEdge.class.getName();
                break;
            default:
                Configuration.browser = CustomProvider.class.getName();
        }
    }

    public static class CustomProvider implements WebDriverProvider {

        @Override
        public WebDriver createDriver(final DesiredCapabilities capabilities) {
            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);


            chromeOptions.setCapability(TAKES_SCREENSHOT, true);
            chromeOptions.setCapability("enableLog", true);
            chromeOptions.setCapability("env", Collections.singletonList("VERBOSE=true"));
            chromeOptions.setCapability("timeZone", "Europe/Kiev");

            RemoteWebDriver driver;
            try {
                driver = new RemoteWebDriver(
                        new URL(format("%s:%s/wd/hub", config.getSelenoidHost(), config.getSelenoidPort())), chromeOptions);
                driver.setFileDetector(new LocalFileDetector());
            } catch (final MalformedURLException e) {
                throw new RuntimeException("Unable to create remote driver", e);
            }
            return driver;
        }
    }

    public static class CustomProviderEdge implements WebDriverProvider {

        @Override
        public WebDriver createDriver(final DesiredCapabilities capabilities) {
            final EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.merge(capabilities);
            capabilities.setCapability("browserName", "MicrosoftEdge");
            capabilities.setCapability("browserVersion", "89.0");
            edgeOptions.setCapability("enableVNC", true);
            edgeOptions.setCapability("enableLog", true);
            edgeOptions.setCapability("timeZone", "Europe/Kiev");
            edgeOptions.setCapability(TAKES_SCREENSHOT, true);
            edgeOptions.setCapability("env", Collections.singletonList("VERBOSE=true"));
            edgeOptions.setCapability("enableVideo", true);
            edgeOptions.setCapability("videoName",
                    format("%s-%s.mp4", currentTestClassName, Thread.currentThread().getId()));
            edgeOptions.setCapability("name",
                    format("%s-%s.mp4", currentTestClassName, Thread.currentThread().getId()));
            RemoteWebDriver driver;
            try {
                driver = new RemoteWebDriver(
                        new URL(format("%s:%s/wd/hub", config.getSelenoidHost(), config.getSelenoidPort())), edgeOptions);
                driver.setFileDetector(new LocalFileDetector());
            } catch (final MalformedURLException e) {
                throw new RuntimeException("Unable to create remote driver", e);
            }
            return driver;
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

    public void attachTheVideo() {
        step("Video of test execution is available by link:");
        step(format("%s:8080/video/%s-%s.mp4",
                ConfigurationLoader.getConfig().getSelenoidHost(), currentTestClassName, Thread.currentThread().getId()));
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
