package stepDefinitions;

import RXPages.PublisherListPage;
import RXPages.RXBasePage;
import RXPages.RXNavOptions;
import RXPages.UserRolesPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UserRolesStepDefinition extends RXBasePage {
    UserRolesPage userRolesPage;
    RXNavOptions navOptions;
    PublisherListPage pubListPage;

    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver,60);

    String pubNameInUserInfo = "";

    public UserRolesStepDefinition(){
        super();
        userRolesPage = new UserRolesPage();
        navOptions = new RXNavOptions();
        pubListPage = new PublisherListPage();
    }

    @When("^Click on user info main menu$")
    public void clickOnUserInfoMainMenu() {
        wait.until(ExpectedConditions.visibilityOf(userRolesPage.userInfo));
        userRolesPage.userInfo.click();
        wait.until(ExpectedConditions.visibilityOf(userRolesPage.logoutBtn));
    }

    @Then("^Verify user info page displays the following label$")
    public void verifyUserInfoPageDisplaysTheFollowingLabel(DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for (Map<String,String> map: list) {
            String labelTitle = map.get("Label");
            System.out.println("check if label exist >>> " + labelTitle);
            Assert.assertTrue(userRolesPage.checkIfValueExist(userRolesPage.userInfoSubTitleList, labelTitle));
            if(labelTitle.equals("Publisher")){
                this.pubNameInUserInfo = userRolesPage.pubNameLinkInUserInfo.getText().trim();
                System.out.println("userRolesPage.pubNameInUserInfo.getText().trim() >>> " + this.pubNameInUserInfo);
            }
        }
    }

    @Then("^Verify user info page displays the following title")
    public void verifyUserInfoPageDisplaysTheFollowingTitle(DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for (Map<String,String> map: list) {
            String subTitle = map.get("Title");
            System.out.println("check if sub title exist >>> " + subTitle);
            Assert.assertTrue(userRolesPage.checkIfValueExist(userRolesPage.userInfoCardTitleList, subTitle));
        }
    }

    @Then("^Verify all RX publishers are displayed in Publisher Name dropdown$")
    public void verifyAllRXPublishersAreDisplayedInPublisherNameDropdown() {
        userRolesPage.scrollDownInDropdown();
        Assert.assertTrue(userRolesPage.dropdownValues.size() > 100);
        userRolesPage.clickCloseButton();
    }

    @Then("^Verify the Publisher Name input displays$")
    public void verifyThePublisherNameInputDisplays() {
        Assert.assertTrue(userRolesPage.pubNameInput.isDisplayed());
    }

    @When("^Click on Close button$")
    public void clickOnCloseButton() {
        userRolesPage.clickCloseButton();
    }

    @Then("^Verify the following radio button is displayed in Create User page$")
    public void verifyTheFollowingRadioButtonIsDisplayedInCreateUserPage(DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for (Map<String,String> map: list) {
            String value = map.get("Radio button");
            System.out.println("check if radio button exist >>> " + value);
            Assert.assertTrue(userRolesPage.isElementPresent(String.format(userRolesPage.radioBtnString, value)));
        }
    }

    @When("^Click on Publishers main menu$")
    public void clickOnPublishersMainMenu() {
        userRolesPage.publisherNameMainMenu.click();
    }

    @Then("^Verify \"([^\"]*)\" sub menu is not displayed$")
    public void verifySubMenuIsNotDisplayed(String arg0)  {
        Assert.assertFalse(userRolesPage.isElementPresent(String.format(userRolesPage.subMenuString, arg0)));
    }

    @Then("^Verify the Publisher Name input only display my publisher$")
    public void verifyThePublisherNameInputOnlyDisplayMyPublisher() {
        String value = userRolesPage.pubNameValue.getText().trim();
        System.out.println("userRolesPage.pubNameValue.getText().trim() >>> " + value);
        Assert.assertEquals(value, this.pubNameInUserInfo);
        userRolesPage.clickCloseButton();
    }

    @When("^Click on the publisher name link in user info panel$")
    public void clickOnThePublisherNameLinkInUserInfoPanel() {
        userRolesPage.pubNameLinkInUserInfo.click();
    }

    @Then("^Verify that publisher info with a readonly form or publisher card in the profile$")
    public void verifyThatPublisherInfoWithAReadonlyFormOrPublisherCardInTheProfile() {
        Assert.assertFalse(pubListPage.adOpsEmailInput.isEnabled());
        Assert.assertFalse(pubListPage.domainInput.isEnabled());
        Assert.assertFalse(userRolesPage.currencyInput.isEnabled());
        Assert.assertFalse(userRolesPage.categoriesInput.isEnabled());
        Assert.assertFalse(userRolesPage.demandSourceInput.isEnabled());
        Assert.assertFalse(pubListPage.savePublisherBtn.isEnabled());
    }
}
