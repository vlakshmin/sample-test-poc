package helpers;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.testng.ScreenShooter;
import configurations.ConfigurationLoader;
import configurations.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import pages.BasePage;
import pages.LoginPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;


import static api.core.client.HttpClient.getToken;
import static api.core.client.HttpClient.setCredentials;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.testng.Assert.*;

@Slf4j
@Listeners({ScreenShooter.class})
public final class Precondition {

    private Precondition(PreconditionBuilder preconditionBuilder) {
    }

    public static PreconditionBuilder testStart() {

        return new PreconditionBuilder();
    }

    public static class PreconditionBuilder {

        private BasePage basePage = new BasePage();
        private LoginPage loginPage =  new LoginPage();
        private final String ELEMENT_BY_TEXT = "//*[contains(text(),'%s')]";

        public PreconditionBuilder openUrl() {
            open(ConfigurationLoader.getConfig().getBaseUrl());

            return this;
        }

        public PreconditionBuilder given() {

            return this;
        }

        public PreconditionBuilder when() {

            return this;
        }

        public PreconditionBuilder then() {

            return this;
        }
        public PreconditionBuilder and() {

            return this;
        }

        public PreconditionBuilder logIn(User user) {
            loginPage.getLoginInput().should(exist, visible).setValue(user.getMail());
            loginPage.getPasswordInput().should(exist, visible).setValue(user.getPassword()).submit();
            loginPage.getPasswordInput().should(disappear);

            return this;
        }

        public PreconditionBuilder loginWithCookie(User user){
            setCredentials(user);
            String token =  getToken();

            //Code to set cookie here
            return this;
        }

        public PreconditionBuilder clickOnText(String text) {
            log.info("Clicking on text '{}'", text);
            $x(String.format(ELEMENT_BY_TEXT, text))
                    .as(format("'%s' Label", text)).shouldBe(exist, visible).scrollTo().hover().click();

            return this;
        }

        public PreconditionBuilder startsWithText(String text) {
            log.info("Clicking on label that starts with text '{}'", text);
            $x(String.format("//*[starts-with(text(), '%s')]", text))
                    .as(format("'%s' Label", text)).should(exist, visible).click();

            return this;
        }

        public PreconditionBuilder clickOnWebElement(SelenideElement element) {
            log.info("Clicking on webElement '{}' by Xpath: '{}'", element.getAlias(), element.getSearchCriteria());
            element.shouldBe(exist, visible).hover().click();

            return this;
        }

        public PreconditionBuilder clickOnWebElementIfPresent(SelenideElement element) {
            log.info("Clicking on webElement '{}' by Xpath: '{}'", element.getAlias(), element.getSearchCriteria());
            try {
                element.shouldBe(visible, Duration.ofSeconds(5)).click();
            } catch (ElementNotFound | NoSuchElementException e) {
                log.info("WebElement '{}' hasn't been found by Xpath: '{}'", element.getAlias(), element.getSearchCriteria());
                e.printStackTrace();
            }

            return this;
        }

        public PreconditionBuilder hoverMouseOnWebElement(SelenideElement element) {
            log.info("Hovering mouse on webElement '{}' by Xpath: '{}'", element.getAlias(), element.getSearchCriteria());
            element.shouldBe(exist, visible).hover();

            return this;
        }

        public PreconditionBuilder clickOnOneOfWebWebElements(SelenideElement elementOne, SelenideElement elementTwo) {
            try {
                log.info("Clicking on webElement '{}' by Xpath: '{}'",
                        elementOne.getAlias(), elementOne.getSearchCriteria());
                elementOne.shouldBe(exist, visible).hover().click();
            } catch (ElementNotFound | NoSuchElementException e) {
                log.info("Clicking on webElement '{}' by Xpath: '{}'",
                        elementOne.getAlias(), elementOne.getSearchCriteria());
                elementTwo.shouldBe(exist, visible).hover().click();
            }

            return this;
        }

        public PreconditionBuilder validate(String... texts) {
            Stream.of(texts).forEach(text -> {
                log.info("Validating Web element with text '{}' is visible on UI", text);
                assertTrue($x(String.format(ELEMENT_BY_TEXT, text))
                                .as(format("'%s' Label", text)).should(exist, visible).isDisplayed()
                    , String.format("\nWeb element with text '%s' not visible on UI", text));
            });

            return this;
        }

        public PreconditionBuilder validate(SelenideElement element, String text) {
            log.info("Validating Web element '{}' by Xpath: '{}' has text '{}'",
                    element.getAlias(), element.getSearchCriteria(), text);
            element.shouldBe(visible).shouldHave(exactText(text));

            return this;
        }

        public PreconditionBuilder validate(SelenideElement element, Map<String, String> attributes) {
            attributes.forEach((attribute, value) -> {
                log.info("Validating Web element '{}' by Xpath: '{}' has attribute '{}' with value '{}'",
                        element.getAlias(), element.getSearchCriteria(), attribute, value);
                element.shouldHave(Condition.attribute(attribute, value));
            });

            return this;
        }

        public PreconditionBuilder validateContainsText(SelenideElement element, String text) {
            log.info("Validating Web element '{}' by Xpath: '{}' contains text '{}'",
                    element.getAlias(), element.getSearchCriteria(), text);
            element.shouldBe(visible).shouldHave(text(text));

            return this;
        }

        public PreconditionBuilder validateList(ElementsCollection collection, String... texts) {
            log.info("Validating List of Web elements '{}' by Xpath: '{}'",
                    collection.first().getSearchCriteria(), collection.first().getSearchCriteria());
            Stream.of(texts).forEach(elementText -> {
                log.info("Validating Web element '{}' by Xpath: '{}' has text '{}'",
                        collection.first().getAlias(), collection.first().getSearchCriteria(), elementText);
                assertTrue(collection.findBy(text(elementText)).shouldBe(visible).isDisplayed());
            });

            return this;
        }

        public PreconditionBuilder validate(Condition condition, String... texts) {
            log.info("Validating List of Labels '{}' are '{}'",
                    Stream.of(texts).reduce("", String::concat), condition.getName());
            Stream.of(texts).forEach(text -> assertTrue(
                    $x(String.format(ELEMENT_BY_TEXT, text)).shouldBe(visible).is(condition)
                    , String.format("/nText in '%s' has not met condition '%s'", text, condition)));
            return this;
        }

        public PreconditionBuilder validate(SelenideElement... elements) {

            Stream.of(elements).forEach(element ->
                    assertTrue(element.shouldBe(exist).exists() && element.shouldBe(visible).isDisplayed()
                    , element.toString()));

            return this;
        }

        public PreconditionBuilder waitAndValidate(Condition condition, SelenideElement... elements) {
            Stream.of(elements).forEach(element -> assertTrue(element.shouldBe(condition).is(condition)
                    , String.format("\n'%s' with condition '%s'", element, condition)));

            return this;
        }

        public PreconditionBuilder waitSpecifiedTimeAndValidate(Condition condition, int seconds, SelenideElement... elements) {
            Stream.of(elements).forEach(element ->
                    assertTrue(element.shouldBe(condition, Duration.ofSeconds(seconds)).is(condition)
                            , String.format("\n'%s' with condition '%s'", element, condition)));

            return this;
        }

        public PreconditionBuilder waitSideBarOpened() {
            log.info("Waiting Sidebar by Xpath '{}' is opened and visible", basePage.getSidebar().getSearchCriteria());
            basePage.getSidebar().shouldBe(exist, visible);

            return this;
        }

        public PreconditionBuilder waitSideBarClosed() {
            log.info("Waiting Sidebar by Xpath '{}' is closed", basePage.getSidebar().getSearchCriteria());
            basePage.getSidebar().shouldNotBe(visible, exist);

            return this;
        }

        public PreconditionBuilder waitSpecifiedTimeAndValidate(Condition condition, int seconds, ElementsCollection elements) {

            elements.forEach(element -> assertTrue(element.shouldBe(condition, Duration.ofSeconds(seconds)).is(condition)
                            , String.format("\n'%s' with condition '%s'", element, condition)));
            return this;
        }

        public PreconditionBuilder validate(Condition condition, SelenideElement... elements) {

            Stream.of(elements).forEach(element -> assertTrue(element.shouldBe(condition).is(condition)
                    , String.format("\n'%s' with condition '%s'", element, condition)));
            return this;
        }

        public PreconditionBuilder validateTooltip(SelenideElement icon, String tooltip, String value) {

            icon.shouldBe(exist, visible).hover();
            $x(tooltip + String.format("[text()='%s']", value)).should(appear);
            return this;
        }

        public PreconditionBuilder validateAttribute(SelenideElement element, String attributeName, String attributeValue) {
            log.info("Validating element '{}' with xpath '{} has attribute '{}' with value '{}'",
                    element.getAlias(), element.getSearchCriteria(), attributeName, attributeValue);
            element.shouldBe(exist, visible).hover().shouldHave(attribute(attributeName, attributeValue));

            return this;
        }

        public PreconditionBuilder setValue(SelenideElement element, String value) {

            element.should(exist, visible).hover().click();
            element.setValue(value);
            return this;
        }

        public PreconditionBuilder scrollTo(SelenideElement element) {
            element.should(exist, visible).scrollTo();

            return this;
        }

        public PreconditionBuilder clearField(SelenideElement element) {

            element.should(exist).hover().doubleClick().sendKeys(Keys.BACK_SPACE);
            return this;
        }

        public PreconditionBuilder setValueWithClean(SelenideElement element, String value) {
            log.info("Setting value '{}' in field '{}' withXpath '{}'",
                    value, element.getAlias(), element.getSearchCriteria());
            //Todo Add log.info
            //element.should(exist,visible).hover().doubleClick().sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
            element.should(exist,visible).hover().click();
            int i = 0;
            do {
                element.sendKeys(Keys.BACK_SPACE);
                i++;
            } while (i<=30);
            element.should(exist,visible).hover().sendKeys(value);

            return this;
        }

        public PreconditionBuilder clickBrowserBackButton() {

            Selenide.back();
            return this;
        }

        public PreconditionBuilder clickBrowserRefreshButton() {

            Selenide.refresh();
            return this;
        }

        public PreconditionBuilder clickEnterButton(SelenideElement element) {

            element.pressEnter();
            return this;
        }

        public PreconditionBuilder waiter(Condition condition, SelenideElement element) {

            element.shouldBe(condition);
            return this;
        }

        public PreconditionBuilder selectFromDropdown(SelenideElement element1, ElementsCollection list, String value) {

            element1.shouldBe(exist, visible).hover().click();
            list.stream()
                    .filter(item -> item.shouldBe(visible).getText().equalsIgnoreCase(value) &&
                            !item.shouldBe(visible).getText().equalsIgnoreCase("No records found"))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException(
                            format("Type with name '%s' haven't been found in the dropdown", value)))
                    .click();
            return this;
        }

        public PreconditionBuilder setWindowSize(int width, int height) {

            WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(width, height));
            return this;
        }

        public PreconditionBuilder perform(Supplier<SelenideElement> element) {

            element.get();
            return this;
        }

        public PreconditionBuilder actions(Supplier<Actions> actionsSupplier) {

            actionsSupplier.get().perform();
            return this;
        }

        public PreconditionBuilder uploadFileFromDialog(String relativeFilePath){
            $("input[type='file']").uploadFile(
                    new File(relativeFilePath));

            return this;
        }

        public PreconditionBuilder logOut() {

            try {
                this.clickOnWebElement($x("//div[contains(@class,'avatar')]"));
                this.clickOnText("Logout");
            } catch (NoSuchElementException | ElementClickInterceptedException e) {

                return this;
            } catch (UnhandledAlertException e) {
                Selenide.confirm();

                return this;
            }

            return this;
        }

        public Precondition testEnd() {

            return new Precondition(this);
        }
    }
}
