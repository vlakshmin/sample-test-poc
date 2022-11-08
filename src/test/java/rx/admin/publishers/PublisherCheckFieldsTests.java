package rx.admin.publishers;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import rx.BaseTest;
import widgets.admin.publisher.sidebar.CurrencyType;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import widgets.errormessages.ErrorMessages;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;


@Slf4j
@Listeners({ScreenShooter.class})
@Link("https://rakutenadvertising.atlassian.net/browse/GS-3155")
public class PublisherCheckFieldsTests extends BaseTest {

    private PublishersPage publisherPage;

    private static final String PUB_NAME = "randomAutoPubName";
    private static final String AD_OPS_PERSON = "randomAutoPubPerson";
    private static final String AD_OPS_EMAIL = "randomAutoPub@gmail.com";
    private EditPublisherSidebar editPublisherSidebar;

    public PublisherCheckFieldsTests() {
        publisherPage = new PublishersPage();
        editPublisherSidebar = new EditPublisherSidebar();
    }


    @BeforeMethod
    public void login() {

        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publisherPage.getNuxtProgress())
                .clickOnWebElement(publisherPage.getCreatePublisherButton())
                .waitSideBarOpened()
                .testEnd();
    }

    @Epic("v?/GS-3100")
    @Test(description = "Check fields by default")
    public void checkDefaultFields() {
        testStart()
                .then("Validate fields by default")
                .validate(enabled, editPublisherSidebar.getActiveToggle())
                .validateAttribute(editPublisherSidebar.getActiveToggle(), "aria-checked", "true")
                .validate(enabled, editPublisherSidebar.getNameInput())
                .validate(editPublisherSidebar.getNameInput(), "")
                .validate(enabled, editPublisherSidebar.getAdOpsEmail())
                .validate(editPublisherSidebar.getAdOpsEmail(), "")
                .validate(enabled, editPublisherSidebar.getAdOpsPerson())
                .validate(editPublisherSidebar.getAdOpsPerson(), "")
                .validate(enabled, editPublisherSidebar.getDomainInput())
                .validate(editPublisherSidebar.getDomainInput(), "")
                .validate(enabled, editPublisherSidebar.getCategoriesInput())
                .testEnd();
    }

    @Epic("v?/GS-3100")
    @Test(description = "Check required fields")
    public void checkRequiredFields() {
        var errorsList = editPublisherSidebar.getErrorAlert().getErrorsList();

        testStart()
                .then("Validate required fields")
                .and("Click 'Save' with all empty fields")
                .clickOnWebElement(editPublisherSidebar.getSaveButton())
                .then("Validate errors for all required fields in Error Panel (Publisher Name, Currency, Ad Ops Person, Ad Ops Email)")
                .waitAndValidate(visible, editPublisherSidebar.getErrorAlert().getErrorPanel())
                .validateListSize(errorsList, 4)
                .validateList(errorsList, List.of(
                        ErrorMessages.PUBLISHER_NAME_ERROR_ALERT.getText(),
                        ErrorMessages.ADD_OPS_PERSON_ERROR_ALERT.getText(),
                        ErrorMessages.ADD_OPS_EMAIL_ERROR_ALERT.getText(),
                        ErrorMessages.CURRENCY_ERROR_ALERT.getText()))
                .setValue(editPublisherSidebar.getNameInput(), PUB_NAME)
                .then("Validate error under the 'Publisher field' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Publisher Name"))
                .and("Click 'Save'")
                .clickOnWebElement(editPublisherSidebar.getSaveButton())
                .then("Validate errors for 3 required fields in Error Panel (Currency, Ad Ops Person, Ad Ops Email)")
                .validateListSize(errorsList, 3)
                .validateList(errorsList, List.of(
                        ErrorMessages.ADD_OPS_PERSON_ERROR_ALERT.getText(),
                        ErrorMessages.ADD_OPS_EMAIL_ERROR_ALERT.getText(),
                        ErrorMessages.CURRENCY_ERROR_ALERT.getText()))
                .setValue(editPublisherSidebar.getAdOpsPerson(), AD_OPS_PERSON)
                .then("Validate error under the 'Ad Ops Person field' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Ad Ops Person"))
                .and("Click 'Save'")
                .clickOnWebElement(editPublisherSidebar.getSaveButton())
                .then("Validate errors for 2 required fields in Error Panel (Currency, Ad Ops Email)")
                .validateListSize(errorsList, 2)
                .validateList(errorsList, List.of(
                        ErrorMessages.ADD_OPS_EMAIL_ERROR_ALERT.getText(),
                        ErrorMessages.CURRENCY_ERROR_ALERT.getText()))
                .setValue(editPublisherSidebar.getAdOpsEmail(), AD_OPS_EMAIL)
                .then("Validate error under the 'Ad Ops Email field' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Ad Ops Email"))
                .and("Click 'Save'")
                .clickOnWebElement(editPublisherSidebar.getSaveButton())
                .then("Validate errors for 1 required fields in Error Panel (Currency)")
                .validateListSize(errorsList, 1)
                .validateList(errorsList, List.of(
                        ErrorMessages.CURRENCY_ERROR_ALERT.getText()))
                .then("Select Currency 'USD'")
                .selectFromDropdown(editPublisherSidebar.getCurrencyDropdown(),
                        editPublisherSidebar.getCurrencyDropdownItems(), CurrencyType.USD.getType())
                .then("Validate error under the 'Currency' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Currency"))
                .then("Select Currency 'EUR'")
                .selectFromDropdown(editPublisherSidebar.getCurrencyDropdown(),
                        editPublisherSidebar.getCurrencyDropdownItems(), CurrencyType.EUR.getType())
                .then("Validate error under the 'Currency' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Currency"))
                .then("Select Currency 'JPY'")
                .selectFromDropdown(editPublisherSidebar.getCurrencyDropdown(),
                        editPublisherSidebar.getCurrencyDropdownItems(), CurrencyType.JPY.getType())
                .then("Validate error under the 'Currency' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Currency"))
                .then("Select Currency 'RUB'")
                .selectFromDropdown(editPublisherSidebar.getCurrencyDropdown(),
                        editPublisherSidebar.getCurrencyDropdownItems(), CurrencyType.RUB.getType())
                .then("Validate error under the 'Currency' disappeared")
                .waitAndValidate(not(visible), editPublisherSidebar.getErrorAlertByFieldName("Currency"))
                .testEnd();
    }

    @AfterMethod
    private void logout() {
        testStart()
                .and("Close Publisher Sidebar")
                .clickOnWebElement(editPublisherSidebar.getCloseIcon())
                .waitSideBarClosed()
                .and("Logout")
                .logOut()
                .testEnd();
    }
}
