package helpers;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.testng.ScreenShooter;
import configurations.ConfigurationLoader;
import configurations.User;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import pages.LoginPage;

import java.io.File;
import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
            $x(loginPage.getLoginInput()).as("Get login field").should(exist, visible).setValue(user.getMail());
            $x(loginPage.getPasswordInput()).should(exist, visible).setValue(user.getPassword()).submit();
            $x(loginPage.getPasswordInput()).should(disappear);

            return this;
        }

        public PreconditionBuilder clickOnText(String text) {
            log.info("Clicking on text '{}'", text);
            $x(String.format(ELEMENT_BY_TEXT, text)).scrollTo().shouldBe(exist, visible).hover().click();

            return this;
        }

        public PreconditionBuilder startsWithText(String text) {
            log.info("Clicking on label that starts with text '{}'", text);
            $x(String.format("//*[starts-with(text(), '%s')]", text)).should(exist, visible).click();

            return this;
        }

        public PreconditionBuilder clickOnWebElement(SelenideElement element) {
            log.info("Clicking on webElement '{}'", element.getAttribute("class"));
            element.shouldBe(exist, visible).hover().click();

            return this;
        }

        public PreconditionBuilder clickOnWebElementIfPresent(SelenideElement element) {
            log.info("Clicking on webElement '{}'", element.getAttribute("class"));
            try {
                element.shouldBe(visible, Duration.ofSeconds(5)).click();
            } catch (ElementNotFound | NoSuchElementException e) {
                e.printStackTrace();
            }

            return this;
        }

        public PreconditionBuilder hoverMouseOnWebElement(SelenideElement element) {
            log.info("Hovering on webElement '{}'", element.getAttribute("class"));
            element.shouldBe(exist, visible).hover();

            return this;
        }

        public PreconditionBuilder clickOnOneOfWebWebElements(SelenideElement elementOne, SelenideElement elementTwo) {
            try {
                log.info("Clicking on webElement '{}'", elementOne.getAttribute("class"));
                elementOne.shouldBe(exist, visible).hover().click();
            } catch (ElementNotFound | NoSuchElementException e) {
                log.info("Clicking on webElement '{}'", elementTwo.getAttribute("class"));
                elementTwo.shouldBe(exist, visible).hover().click();
            }

            return this;
        }

        public PreconditionBuilder clickOnOneWebElementAfterAnother(SelenideElement elementOne, SelenideElement elementTwo) {
            try {
                log.info("Hovering on webElement '{}'", elementOne.getAttribute("class"));
                elementOne.shouldBe(exist, visible).hover().click();
            } catch (ElementNotFound | NoSuchElementException e) {
                log.info("Hovering on webElement '{}'", elementTwo.getAttribute("class"));
                elementTwo.shouldBe(exist, visible).hover().click();
                elementOne.shouldBe(exist, visible).hover().click();
            }

            return this;
        }

        public PreconditionBuilder validate(String... texts) {
            Stream.of(texts).forEach(text -> assertTrue($x(String.format(ELEMENT_BY_TEXT, text)).should(exist, visible).isDisplayed()
                    , String.format("\nWeb element with text '%s' not visible", text)));

            return this;
        }

        public PreconditionBuilder validate(SelenideElement element, String text) {

            element.shouldBe(visible).shouldHave(exactText(text));
            return this;
        }

        public PreconditionBuilder validate(SelenideElement element, Map<String, String> attributes) {

            attributes.forEach((attribute, value) -> element.shouldHave(Condition.attribute(attribute, value)));
            return this;
        }

        public PreconditionBuilder validateContainsText(SelenideElement element, String text) {

            element.shouldBe(visible).shouldHave(text(text));
            return this;
        }

        public PreconditionBuilder validateHasValue(SelenideElement element, String value) {

            element.shouldBe(visible).shouldHave(value(value));
            return this;
        }

        public PreconditionBuilder validate(ElementsCollection collection, String... texts) {

            Stream.of(texts).forEach(elementText -> assertTrue(collection.findBy(text(elementText)).isDisplayed()));
            return this;
        }

        public PreconditionBuilder validate(Condition condition, String... texts) {

            Stream.of(texts).forEach(text -> assertTrue($x(String.format(ELEMENT_BY_TEXT, text)).is(condition)
                    , String.format("/nText in '%s' has not met condition '%s'", text, condition)));
            return this;
        }

        public PreconditionBuilder validate(SelenideElement... elements) {

            Stream.of(elements).forEach(element -> assertTrue(element.exists() && element.isDisplayed()
                    , element.toString()));

            return this;
        }

        public PreconditionBuilder validate(ElementsCollection elements) {
            if (elements.isEmpty()) {
                AssertJUnit.fail("Elements collection is empty");
            } else {
                elements.forEach(element -> assertTrue(element.exists()));
            }
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

        public PreconditionBuilder waitSpecifiedTimeAndValidate(Condition condition, int seconds, ElementsCollection elements) {

            elements.forEach(element -> assertTrue(element.shouldBe(condition, Duration.ofSeconds(seconds)).is(condition)
                            , String.format("\n'%s' with condition '%s'", element, condition)));
            return this;
        }

        public PreconditionBuilder validate(Condition condition, SelenideElement... elements) {

            Stream.of(elements).forEach(element -> assertTrue(element.is(condition)
                    , String.format("\n'%s' with condition '%s'", element, condition)));
            return this;
        }

        public PreconditionBuilder validateTooltip(SelenideElement icon, String tooltip, String value) {

            icon.shouldBe(exist, visible).hover();
            $x(tooltip + String.format("[text()='%s']", value)).should(appear);
            return this;
        }

        public PreconditionBuilder validateAttribute(SelenideElement element, String attributeName, String attributeValue) {

            element.shouldBe(exist, visible).hover().shouldHave(attribute(attributeName, attributeValue));
            return this;
        }

        public PreconditionBuilder assertString(String actual, String expect) {

            assertEquals(actual, expect);
            return this;
        }

        public PreconditionBuilder validateAttribute(SelenideElement element, String attribute, String text, Boolean assertionMode) {
            if (assertionMode)
                assertTrue(element.shouldBe(visible).getAttribute(attribute).contains(text));
            if (!assertionMode)
                assertFalse(element.shouldBe(visible).getAttribute(attribute).contains(text));
            return this;
        }

        public PreconditionBuilder setValue(SelenideElement element, String value) {

            element.should(exist, visible).hover().click();
            element.setValue(value);
            return this;
        }

        public PreconditionBuilder clearField(SelenideElement element) {

            element.should(exist).hover().doubleClick().sendKeys(Keys.BACK_SPACE);
            return this;
        }

        public PreconditionBuilder setValueWithClean(SelenideElement element, String value) {

            element.should(exist).hover().doubleClick().sendKeys(Keys.BACK_SPACE);
            element.setValue(value);
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
