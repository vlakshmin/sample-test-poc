package managers;

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
import org.testng.annotations.Listeners;
import pages.BasePage;
import pages.LoginPage;
import pages.Path;
import widgets.common.table.ColumnNames;
import widgets.common.table.Table;
import widgets.common.table.TableOptions;
import widgets.common.table.TablePagination;

import java.io.File;
import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static api.core.client.HttpClient.getToken;
import static api.core.client.HttpClient.setCredentials;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Slf4j
@Listeners({ScreenShooter.class})
public final class TestManager {

    private TestManager(TestManagerBuilder testManagerBuilder) {
    }

    public static TestManagerBuilder testStart() {

        return new TestManagerBuilder();
    }

    public static class TestManagerBuilder {

        private final String ELEMENT_BY_TEXT = "//*[contains(text(),'%s')]";
        private final BasePage basePage = new BasePage();
        private final LoginPage loginPage = new LoginPage();
        private final Table table = new Table();
        private final TableOptions tableOptions = new TableOptions();
        private final TablePagination tablePagination = new TablePagination();

        public TestManagerBuilder openUrl() {
            logEvent(format("Opening url '%s'", ConfigurationLoader.getConfig().getBaseUrl()));
            open(ConfigurationLoader.getConfig().getBaseUrl());

            return this;
        }

        public TestManagerBuilder openDirectPath(Path path) {
            logEvent(format("Opening path '%s%s'", ConfigurationLoader.getConfig().getBaseUrl(), path.getPath()));
            open(format("%s%s", ConfigurationLoader.getConfig().getBaseUrl(), path.getPath()));

            return this;
        }

        public TestManagerBuilder given() {

            return this;
        }

        public TestManagerBuilder when() {

            return this;
        }

        public TestManagerBuilder then() {

            return this;
        }

        public TestManagerBuilder and() {

            return this;
        }

        public TestManagerBuilder logIn(User user) {
            logEvent(format("Logging with user with mail '%s' and password '%s'", user.getMail(), user.getPassword()));
            loginPage.getLoginInput().should(exist, visible).setValue(user.getMail());
            loginPage.getPasswordInput().should(exist, visible).setValue(user.getPassword()).submit();
            loginPage.getPasswordInput().should(disappear);

            return this;
        }

        public TestManagerBuilder loginWithCookie(User user) {
            //Todo Implement login with cookie
            setCredentials(user);
            String token = getToken();

            //Code to set cookie here
            return this;
        }

        public TestManagerBuilder clickOnText(String text) {
            //ToDo Implement Logging Annotation
            logEvent(format("Clicking on text '%s'", text));
            $x(String.format(ELEMENT_BY_TEXT, text))
                    .as(format("'%s' Label", text)).shouldBe(exist, visible).scrollTo().hover().click();

            return this;
        }

        public TestManagerBuilder startsWithText(String text) {
            logEvent(format("Clicking on label that starts with text '%s'", text));
            $x(String.format("//*[starts-with(text(), '%s')]", text))
                    .as(format("'%s' Label", text)).should(exist, visible).click();

            return this;
        }

        public TestManagerBuilder clickOnWebElement(SelenideElement element) {
            logEvent(format("Clicking on %s", element.getAlias()));
            element.shouldBe(exist, visible).hover().click();

            return this;
        }

        public TestManagerBuilder selectCheckBox(SelenideElement checkbox) {
            if (checkbox.getAttribute("aria-checked").equals("false")) {
                checkbox.parent().click();
            }

            return this;
        }

        public TestManagerBuilder unSelectCheckBox(SelenideElement checkbox) {
            if (checkbox.getAttribute("aria-checked").equals("true"))
                checkbox.parent().click();

            return this;
        }

        public TestManagerBuilder selectRadioButton(SelenideElement radio) {
            if (radio.getAttribute("aria-checked").equals("false")) {
                radio.parent().click();
            }

            return this;
        }

        //Todo
        public TestManagerBuilder turnToggleOn(SelenideElement toggle) {
            //Todo implement realisation

            return this;
        }

        //Todo
        public TestManagerBuilder turnToggleOff(SelenideElement toggle) {
            //Todo implement realisation

            return this;
        }

        public TestManagerBuilder clickOnWebElementIfPresent(SelenideElement element) {
            logEvent(format("Clicking on  %s", element.getAlias()));
            try {
                element.shouldBe(visible, Duration.ofSeconds(5)).click();
            } catch (ElementNotFound | NoSuchElementException e) {
                logEvent(format("%s hasn't been found '%s'", element.getAlias(), element.getSearchCriteria()));
                e.printStackTrace();
            }

            return this;
        }

        public TestManagerBuilder hoverMouseOnWebElement(SelenideElement element) {
            logEvent(format("Hovering mouse on %s", element.getAlias()));
            element.shouldBe(exist, visible).hover();

            return this;
        }

        public TestManagerBuilder clickOnOneOfWebWebElements(SelenideElement elementOne, SelenideElement elementTwo) {
            try {
                logEvent(format("Clicking on %s", elementOne.getAlias()));
                elementOne.shouldBe(exist, visible).hover().click();
            } catch (ElementNotFound | NoSuchElementException e) {
                logEvent(format("%s hasn't been found. Clicking on %s", elementOne.getAlias(), elementTwo.getAlias()));
                elementTwo.shouldBe(exist, visible).hover().click();
            }

            return this;
        }

        public TestManagerBuilder validate(String... texts) {
            Stream.of(texts).forEach(text -> {
                logEvent(format("Validating Web element with text '%s' is visible on UI", text));
                assertTrue($x(String.format(ELEMENT_BY_TEXT, text)).should(exist, visible).isDisplayed()
                        , String.format("\nWeb element with text '%s' not visible on UI", text));
            });

            return this;
        }

        public TestManagerBuilder validate(SelenideElement element, String text) {
            logEvent(format("Validating %s has text '%s'", element.getAlias(), text));
            element.shouldBe(visible).shouldHave(exactText(text));

            return this;
        }

        public TestManagerBuilder validate(SelenideElement element, Map<String, String> attributes) {
            attributes.forEach((attribute, value) -> {
                logEvent(format("Validating %s has attribute '%s' with value '%s'", element.getAlias(), attribute, value));
                element.shouldHave(Condition.attribute(attribute, value));
            });

            return this;
        }

        public TestManagerBuilder validateContainsText(SelenideElement element, String text) {
            logEvent(format("Validating %s contains text '%s'", element.getAlias(), text));
            element.shouldBe(visible).shouldHave(text(text));

            return this;
        }


        public TestManagerBuilder validateList(ElementsCollection collection, String... texts) {
            logEvent(format("Validating List of %ss ", collection.first().getSearchCriteria()));
            Stream.of(texts).forEach(elementText -> {
                logEvent(format("Validating %s has text '%s'", collection.first().getAlias(), elementText));
                assertTrue(collection.findBy(text(elementText)).shouldBe(visible).isDisplayed());
            });
            collection.shouldHave(CollectionCondition.size(texts.length));

            return this;
        }

        public TestManagerBuilder validateListContainsTextOnly(ElementsCollection collection, String suffix) {
            logEvent(format("Validating List of %ss contains suffix '%s'", collection.first().getAlias(), suffix));
            collection.forEach(elementCollection -> {
                elementCollection.shouldBe(visible).shouldHave(Condition.text(suffix));
            });
            logEvent(format("All elements of List of %ss contains suffix '%s' ", collection.first().getAlias(), suffix));

            return this;
        }

        public TestManagerBuilder validateList(ElementsCollection collection, int expectedSize) {
            logEvent(format("Validating %s has size equals to %s", collection.first().getAlias(), expectedSize));
            collection.shouldHave(CollectionCondition.size(expectedSize));

            return this;
        }

        public TestManagerBuilder validate(Condition condition, String... texts) {
            logEvent(format("Validating List of texts '%s' is %s",
                    Stream.of(texts).reduce("", String::concat), condition.getName()));
            Stream.of(texts).forEach(text -> assertTrue(
                    $x(String.format(ELEMENT_BY_TEXT, text)).shouldBe(visible).is(condition)
                    , String.format("/nText in '%s' has not met condition '%s'", text, condition)));
            return this;
        }

        public TestManagerBuilder validate(SelenideElement... elements) {

            Stream.of(elements).forEach(element -> {
                        logEvent(format("Validating %s is visible on UI", element.getAlias()));
                        assertTrue(element.shouldBe(exist).exists() && element.shouldBe(visible).isDisplayed()
                                , element.toString());
                    }
            );

            return this;
        }

        public TestManagerBuilder waitAndValidate(Condition condition, SelenideElement... elements) {
            Stream.of(elements).forEach(element -> {
                logEvent(format("Validating %s is %s on UI", element.getAlias(), condition.getName()));
                assertTrue(element.shouldBe(condition).is(condition)
                        , String.format("\n'%s' with condition '%s'", element, condition));
            });

            return this;
        }

        public TestManagerBuilder waitSpecifiedTimeAndValidate(Condition condition, int seconds, SelenideElement... elements) {
            Stream.of(elements).forEach(element -> {
                logEvent(format("Waiting %s seconds and \nValidating %s is %s on UI",
                        seconds, element.getAlias(), condition.getName()));
                assertTrue(element.shouldBe(condition, Duration.ofSeconds(seconds)).is(condition)
                        , String.format("\n'%s' with condition '%s'", element, condition));
            });

            return this;
        }

        public TestManagerBuilder waitSideBarOpened() {
            logEvent("Waiting Sidebar is opened and visible");
            basePage.getSidebar().shouldBe(exist, visible);

            return this;
        }

        public TestManagerBuilder waitSideBarClosed() {
            log.info("Waiting Sidebar by Xpath '{}' is closed", basePage.getSidebar().getSearchCriteria());
            basePage.getSidebar().shouldNotBe(visible, exist);

            return this;
        }

        public TestManagerBuilder waitSpecifiedTimeAndValidate(Condition condition, int seconds, ElementsCollection elements) {
            elements.forEach(element -> {
                logEvent(format("Waiting %s seconds and \nValidating %s is %s on UI",
                        seconds, element.getAlias(), condition.getName()));
                assertTrue(element.shouldBe(condition, Duration.ofSeconds(seconds)).is(condition),
                        String.format("\n'%s' with condition '%s'", element, condition));
            });

            return this;
        }

        public TestManagerBuilder validate(Condition condition, SelenideElement... elements) {
            Stream.of(elements).forEach(element -> {
                logEvent(format("Validating %s is %s on UI", element.getAlias(), condition.getName()));
                assertTrue(element.shouldBe(condition).is(condition)
                        , String.format("\n'%s' with condition '%s'", element, condition));
            });

            return this;
        }

        public TestManagerBuilder validateTooltip(SelenideElement icon, String tooltip, String value) {
            logEvent(format("Validating %s has tooltip '%s'", icon.getAlias(), value));
            icon.shouldBe(exist, visible).hover();
            $x(tooltip + String.format("[text()='%s']", value)).should(appear);

            return this;
        }

        public TestManagerBuilder validateAttribute(SelenideElement element, String attributeName, String attributeValue) {
            logEvent(format("Validating %s  has attribute '%s' with value '%s'",
                    element.getAlias(), attributeName, attributeValue));
            element.shouldBe(exist, visible).hover().shouldHave(attribute(attributeName, attributeValue));

            return this;
        }

        public TestManagerBuilder setValue(SelenideElement element, String value) {
            logEvent(format("Input text '%s' in %s", value, element.getAlias()));
            element.should(exist, visible).hover().click();
            element.setValue(value);

            return this;
        }

        public TestManagerBuilder scrollTo(SelenideElement element) {
            logEvent(format("Scrolling to %s", element.getAlias()));
            element.should(exist, visible).scrollTo();

            return this;
        }

        public TestManagerBuilder clearField(SelenideElement element) {

            element.should(exist).hover().doubleClick().sendKeys(Keys.BACK_SPACE);
            return this;
        }

        public TestManagerBuilder setValueWithClean(SelenideElement element, String value) {
            log.info("Setting value '{}' in field '{}' withXpath '{}'",
                    value, element.getAlias(), element.getSearchCriteria());
            step(format("Setting value '%s' in field '%s' withXpath '%s'",
                    value, element.getAlias(), element.getSearchCriteria()));
            //element.should(exist,visible).hover().doubleClick().sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
            element.should(exist, visible).hover().click();
            int i = 0;
            do {
                element.sendKeys(Keys.BACK_SPACE);
                i++;
            } while (i <= 30);
            element.should(exist, visible).hover().sendKeys(value);

            return this;
        }

        public TestManagerBuilder clickBrowserBackButton() {

            Selenide.back();
            return this;
        }

        public TestManagerBuilder clickBrowserRefreshButton() {

            Selenide.refresh();
            return this;
        }

        public TestManagerBuilder clickEnterButton(SelenideElement element) {

            element.pressEnter();
            return this;
        }

        public TestManagerBuilder waiter(Condition condition, SelenideElement element) {

            element.shouldBe(condition);
            return this;
        }

        public TestManagerBuilder selectFromDropdown(SelenideElement element1, ElementsCollection list, String value) {

            element1.shouldBe(exist, visible).hover().click();
            list.stream()
                    .filter(item -> item.shouldBe(visible).getText().equalsIgnoreCase(value) &&
                            !item.shouldBe(visible).getText().equalsIgnoreCase("No data available"))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException(
                            format("Type with name '%s' haven't been found in the dropdown", value)))
                    .click();

            return this;
        }

        public TestManagerBuilder setWindowSize(int width, int height) {

            WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(width, height));
            return this;
        }

        public TestManagerBuilder perform(Supplier<SelenideElement> element) {

            element.get();
            return this;
        }

        public TestManagerBuilder actions(Supplier<Actions> actionsSupplier) {

            actionsSupplier.get().perform();
            return this;
        }

        public TestManagerBuilder uploadFileFromDialog(String relativeFilePath) {
            $("input[type='file']").uploadFile(new File(relativeFilePath));

            return this;
        }

        public TestManagerBuilder logOut() {

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

        private void logEvent(String loggerString) {
            //ToDo Implement custom Annotation to log info in Browser Console and in Allure Resport
            log.info(loggerString);
            step(loggerString);
        }

        public TestManager testEnd() {

            return new TestManager(this);
        }

        public void paginationFirstPage() {
            SelenideElement privBtn = tablePagination.getPrevious();
            while (privBtn.isEnabled()) {
                privBtn.click();
            }
        }

        public TestManagerBuilder clickOnTableCellLink(ColumnNames column, String cellValue) {
            table.getCustomCells(column)
                    .stream()
                    .filter(x -> x.getText().equals(cellValue))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException(
                            format("The table cell with value '%s' isn't presented in the column '%s'", cellValue, column.getName())))
                    .lastChild()
                    .click();

            return this;
        }

    }
}
