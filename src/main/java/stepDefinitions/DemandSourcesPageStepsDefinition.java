package stepDefinitions;

import RXPages.PublisherListPage;
import RXPages.RXDemandSourcesPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.*;

public class DemandSourcesPageStepsDefinition extends RXDemandSourcesPage {
    RXDemandSourcesPage demandSourcesPage;
    RXNavOptions navOptions;
    PublisherListPage pubListPgs;
    Logger log = Logger.getLogger(DemandSourcesPageStepsDefinition.class);
    List<String> enteredDemandNoList = new ArrayList<String>();
    String enteredRequestAdjustmentRate;
    String enteredDemandNo;
    String enterEndpointURI;

    public DemandSourcesPageStepsDefinition(){
        super();
        demandSourcesPage = new RXDemandSourcesPage();
        navOptions = new RXNavOptions();
        pubListPgs = new PublisherListPage();
    }
    WebDriverWait wait = new WebDriverWait(driver, 30);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given("^Click on Demand Sources option under Admin$")
    public void click_on_Demand_Sources_option_under_Admin() throws Throwable {
        log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
                + pubListPgs.logodisplayed());
        Assert.assertTrue(pubListPgs.logodisplayed());
        navOptions.expandAdmin();;
        wait.until(ExpectedConditions.visibilityOf(navOptions.demandSourcesUndrAdmin));
        navOptions.demandSourcesUndrAdmin.click();
    }

    @Then("^User displayed with Demand Sources page$")
    public void user_displayed_with_Demand_Sources_page() throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.demandPageHeader));
        log.info("Seats Page Header is asserts  and it is : "+ demandSourcesPage.getUserPageHeading() );
        Assert.assertEquals(demandSourcesPage.getUserPageHeading(), "Demand");
    }

    @Then("^Select one \"([^\"]*)\" Active DSP item$")
    public void select_one_DSP_item(String active) throws Throwable {
        enteredDemandNoList.clear();
        List<WebElement> listActives = demandSourcesPage.demandActives();
        for (int k = 0; k < listActives.size(); k++) {
            String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
            if (active.equals(reqActive)) {
                demandSourcesPage.demandCheckBox(k+1).click();
                enteredDemandNoList.add(demandSourcesPage.demandNo(k+1));
                break;
            }
        }
    }

    @Then("^Verify that following buttons are present in DSP list page$")
    public void verify_that_following_buttons_are_present_in_DSP_list_page(DataTable dt) throws Throwable {
        List<String> buttons = dt.asList(String.class);
        buttons.forEach(e -> Assert.assertTrue(demandSourcesPage.toolbarButton(e).isDisplayed(), e + " is not present."));
    }

    @When("^Click \"([^\"]*)\" button in DSP list page$")
    public void click_button_in_DSP_list_page(String buttonName) throws Throwable {
        demandSourcesPage.toolbarButton(buttonName).click();
        if(!buttonName.equals("Edit")) {
            wait.until(ExpectedConditions.invisibilityOf(demandSourcesPage.toolbarButton("Edit")));
        }
    }

    @Then("^Edit DSP pop up is present$")
    public void edit_DSP_pop_up_is_present() throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//aside[@class='dialog']/header//div[contains(text(),'Edit Demand')]"))));
    }

    @Then("^Enter the following data in the general card of DSP$")
    public void enter_the_following_data_in_the_general_card_of_DSP(DataTable dt) throws Throwable {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for (int i = 0; i < list.size(); i++) {
            String fieldName = list.get(i).get("FieldName");
            switch (fieldName) {
                case "Request Adjustment Rate":
                    wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.requestAdjustmentRate));
                    demandSourcesPage.requestAdjustmentRate.sendKeys(Keys.ARROW_DOWN);
                    enteredRequestAdjustmentRate = demandSourcesPage.requestAdjustmentRate.getAttribute("value");
                    break;
                case "Endpoint URI":
                    wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.endpointURIText));
                    while (!demandSourcesPage.endpointURIText.getAttribute("value").equals("")) {
                        demandSourcesPage.endpointURIText.sendKeys(Keys.BACK_SPACE);
                    }
                    enterEndpointURI = RXUtile.getRandomNumberFourDigit()+".com";
                    demandSourcesPage.endpointURIText.sendKeys(enterEndpointURI);
                    break;
                default:
                    Assert.assertTrue(false, "The status fields supplied does not match with the input");
            }
        }
    }

    @Then("^Click on Save DSP button$")
    public void click_on_Save_DSP_button() throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.saveButton));
        demandSourcesPage.saveButton.click();
    }

    @Then("^Verify \"([^\"]*)\" is saved properly for the edited DSP data$")
    public void verify_is_saved_properly_for_the_edited_DSP_data(String arg1) throws Throwable {
        wait.until(ExpectedConditions.invisibilityOf(demandSourcesPage.editPagePresent()));
        String demandNo = "";
        String entereddemandNo = enteredDemandNoList.get(0);
        List<WebElement> listNos = demandSourcesPage.demandNos();
        for (int k = 0; k < listNos.size(); k++) {
            demandNo = listNos.get(k).getText().replaceAll("\\s", "");
            if (entereddemandNo.equals(demandNo)) {
                if(demandSourcesPage.demandItem(k+1).getAttribute("class").contains("selected")){
                    click_button_in_DSP_list_page("Edit");
                }
                wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.editPagePresent()));
                break;
            }
        }
        switch(arg1){
            case "Endpoint URI":
                Assert.assertEquals(demandSourcesPage.endpointURIText.getAttribute("value"), enterEndpointURI);
                break;
            default:
                Assert.assertTrue(false, "The status fields supplied does not match with the input");
        }

    }

    @Then("^\"([^\"]*)\" is displayed for the DSP$")
    public void is_displayed_for_the_DSP(String arg1) throws Throwable {
        List<WebElement> listNos = demandSourcesPage.demandNos();
        for(int i=0;i<enteredDemandNoList.size();i++) {
            for (int k = 0; k < listNos.size(); k++) {
                String reqNo = listNos.get(k).getText().replaceAll("\\s", "");
                if (enteredDemandNoList.get(i).equals(reqNo)) {
                    Assert.assertEquals(demandSourcesPage.demandActive(k+1).getText(), arg1, demandSourcesPage.demandActive(k+1).getText() +" is displayed for the created DSP");;
                    break;
                }
            }
        }
    }

    @Then("^Select (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" Active DSP items$")
    public void select_and_DSP_items(int num1, String inactive, int num3, String active) throws Throwable {
        enteredDemandNoList.clear();
        List<WebElement> listActives = demandSourcesPage.demandActives();
        for (int k = 0; k < listActives.size(); k++) {
            String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
            if (active.equals(reqActive) && num3 > 0) {
                demandSourcesPage.demandCheckBox(k+1).click();
                enteredDemandNoList.add(demandSourcesPage.demandNo(k+1));
                num3--;
            }
            if(inactive.equals(reqActive) && num1 > 0) {
                demandSourcesPage.demandCheckBox(k+1).click();
                enteredDemandNoList.add(demandSourcesPage.demandNo(k+1));
                num1--;
            }
            if(num1 == 0 && num3 == 0) {
                break;
            }
        }
        Assert.assertEquals(num1,0,num1 +" Inactive DSP is not selected.");
        Assert.assertEquals(num3,0,num3 +" Active DSP is not selected.");
    }

    @When("^Search \"([^\"]*)\" DSP item$")
    public void search_DSP_item(String arg1) throws Throwable {
        demandSourcesPage.searchField.sendKeys(arg1);
    }

    @When("^Click on Bidder column for \"([^\"]*)\" DSP item$")
    public void click_on_Bidder_column_for_DSP_item(String arg1) throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.getBidder_column(arg1)));
        demandSourcesPage.getBidder_column(arg1).click();
    }

    @Then("^DSP saved successfully without error message$")
    public void dsp_saved_successfully_without_error_message() throws Throwable {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
    }

    @Then("^Erase data from endpoint input$")
    public void erase_data_from_endpoint_input() throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(demandSourcesPage.endpointURIText));
        while (!demandSourcesPage.endpointURIText.getAttribute("value").equals("")) {
            demandSourcesPage.endpointURIText.sendKeys(Keys.BACK_SPACE);
        }
    }

    @Then("^Verify error \"([^\"]*)\" is present for Endpoint URI$")
    public void verify_error_is_present_for_Endpoint_URI(String arg1) throws Throwable {
        Assert.assertEquals(demandSourcesPage.endpointURIError.getText(),arg1);
    }

}
