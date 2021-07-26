package stepDefinitions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import RXPages.RXAdspotsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import RXPages.RXNavOptions;
import RXPages.RXTargetingPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TargetingPageStepsDefinition extends RXTargetingPage    {
	
	RXTargetingPage targetingPage;
	RXNavOptions navOptions;
	RXAdspotsPage adspotsPage;
	Logger log = Logger.getLogger(TargetingPageStepsDefinition.class);
	WebDriverWait wait = new WebDriverWait(driver,30);
	String enteredPublisherName;
	String enteredRuleName;
	List<String> enteredRuleNameList = new ArrayList<String>();;
	
	public TargetingPageStepsDefinition()
	{
		super();
		targetingPage = new RXTargetingPage();
		navOptions = new RXNavOptions();
		adspotsPage = new RXAdspotsPage();
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
//Verify if user is displayed with targeting list page on clicking targeting navigation link 

@When("^Click on Targeting option under Rules$")
public void click_on_targeting_option_under_Rules() throws Throwable {
	navOptions.expandRules();
	Thread.sleep(2000);
	targetingPage.clickTaretingNavMenu();;
	log.info("Clicked on Targeting option under Rules");
}

@Then("^User displayed with targeting page$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(targetingPage.getPageHeading(), targetingPage.targetingHeaderStr);
	log.info("Targeting Page Header is asserted  and it is : "+ targetingPage.getPageHeading() );

}
//=========================================================================================================


@When("^User click on table options button$")
public void clickTableOptions() throws InterruptedException {
	Thread.sleep(10000);
	wait.until(ExpectedConditions.visibilityOf(navOptions.tableOptions));
	navOptions.clickTableOptions();
}
@When("^Verify that column \"(.*)\" can be hidden and shown$")
public void verifyHideShow(String filter) throws InterruptedException {
	navOptions.tableOptionsLabel = filter;
	Thread.sleep(5000);
	navOptions.clickHideShowCheckBox();
	Thread.sleep(2000);
	String isChecked = navOptions.checkTableOptionsChkStatus();
	switch(isChecked) {
	  case "false":
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), false);
		     navOptions.clickHideShowCheckBox();
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), true);
			break;
	  case "true":
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), true);
		     navOptions.clickHideShowCheckBox();
		     Assert.assertEquals(navOptions.isColumnHeaderDisplayed(filter), false);
			break;
	   
	  default:
	    Assert.assertTrue(false, "The status fields supplied does not match with the input");
	
    }
}


@When("^Verify non default columns in list page$")
public void verifyNonDefault(DataTable dt) throws InterruptedException {
	List<Map<String, String>> list = dt.asMaps(String.class, String.class);
	for (int i = 0; i < list.size(); i++) {
		String filter = list.get(i).get("ColumnName");
	navOptions.tableOptionsLabel = filter;
	Thread.sleep(5000);
	String isChecked = navOptions.checkTableOptionsChkStatus();
	Assert.assertEquals(isChecked, "false");
	}
}
@When("^Verify that column \"(.*)\" only shows relevant rows in the table with filter \"(.*)\"$")
public void verifyShowStats(String column, String filter) throws InterruptedException {
	navOptions.tableOptionsLabel = filter;
	Thread.sleep(5000);
	navOptions.clickHideShowRadioBox();
	Thread.sleep(2000);
	String isChecked = navOptions.checkTableOptionsRadioChkStatus();
	switch(isChecked) {
	  case "true":
		     List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader(column);
		     for(int i=0;i<coulmnData.size();i++) {
		    	 Assert.assertEquals(coulmnData.get(i).getText(), filter);
				}
		    
			break;
	   
	  default:
	    Assert.assertTrue(false, "The status fields supplied does not match with the input");
	
    }

}

	@When("^Click on the Create Rule button$")
	public void click_on_the_Create_Rule_button() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(targetingPage.createRuleBtn));
		targetingPage.createRuleBtn.click();
	}

	@Then("^Click on Save Rule button$")
	public void click_on_Save_Rule_button() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(targetingPage.saveButton));
		targetingPage.saveButton.click();
	}

	@Then("^Enter the following data in the general card of Rule$")
	public void enter_the_following_data_in_the_general_card_of_Rule(DataTable dt) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");
			String listValueIndex = list.get(i).get("ListValueIndex");

			switch (fieldName) {
				case "Publisher Name":
					WebElement dropDownPublisher;
					wait.until(ExpectedConditions.visibilityOf(targetingPage.publisherNameDropDown));
					targetingPage.publisherNameDropDown.click();
					if (value.equalsIgnoreCase("ListValue")) {
						wait.until(ExpectedConditions.visibilityOf(
								driver.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"))));
						dropDownPublisher = driver
								.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"));
						js.executeScript("arguments[0].scrollIntoView()", dropDownPublisher);
						dropDownPublisher.click();
					} else {
						targetingPage.selectValueFromDropdown(value);
					}
					enteredPublisherName = adspotsPage.publisherNameField.getText();
					System.out.println("publisher entered as :" + enteredPublisherName);
					wait.until(ExpectedConditions.visibilityOf(targetingPage.ruleNameField));
					break;

				case "Name":
					while (!targetingPage.ruleNameField.getAttribute("value").equals("")) {
						targetingPage.ruleNameField.sendKeys(Keys.BACK_SPACE);
					}
					Calendar cal = Calendar.getInstance();
					targetingPage.ruleNameField.sendKeys(value + cal.getTimeInMillis());
					enteredRuleName = targetingPage.ruleNameField.getAttribute("value");
					System.out.println("Entered Auction name:" + enteredRuleName);
					break;

				default:
					Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Add Advertiser in the general card of Rule$")
	public void add_Advertiser_in_the_general_card_of_Rule() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(targetingPage.addTargetingButton));
		js.executeScript("arguments[0].scrollIntoView()", targetingPage.addTargetingButton);
		targetingPage.addTargetingButton.click();
		wait.until(ExpectedConditions.visibilityOf(targetingPage.advertisersPopup));
		targetingPage.selectAdvertiser("Slotomania™ Vegas Casino Slots");
		wait.until(ExpectedConditions.visibilityOf(targetingPage.advertiserIncludedListItem("Slotomania™ Vegas Casino Slots")));
		targetingPage.closeAdvertisersPopup.click();
	}

	@Then("^Select one \"([^\"]*)\" Rule item$")
	public void select_one_Rule_item(String active) {
		enteredRuleNameList.clear();
		List<WebElement> listActives = targetingPage.ruleActives();
		for (int k = 0; k < listActives.size(); k++) {
			String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
			if (active.equals(reqActive)) {
				targetingPage.ruleCheckBox(k+1).click();
				enteredRuleNameList.add(targetingPage.ruleName(k+1));
				break;
			}
		}
	}

	@Then("^Verify that following buttons are present in Rule list page$")
	public void verify_that_following_buttons_are_present_in_Rule_list_page(DataTable dt) {
		List<String> buttons = dt.asList(String.class);
		buttons.forEach(e -> Assert.assertTrue(targetingPage.toolbarButton(e).isDisplayed(), e + " is not present."));
	}

	@When("^Click \"([^\"]*)\" button in Rule list page$")
	public void click_button_in_Rule_list_page(String buttonName) throws Throwable {
		targetingPage.toolbarButton(buttonName).click();
		if(!buttonName.equals("Edit Rule")) {
			wait.until(ExpectedConditions.visibilityOf(targetingPage.toolbarButton("Create Rule")));
		}
	}

	@Then("^Edit Rule pop up is present$")
	public void edit_Rule_pop_up_is_present() throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(targetingPage.createOrEditRuleDialogPopup))));
		wait.until(ExpectedConditions.visibilityOf(targetingPage.getElementByXpathWithParameter(targetingPage.editRuleDialogHeader,enteredRuleNameList.get(0))));
	}

	@Then("^Verify the edited Rule data is matching with its overview list values$")
	public void verify_the_edited_Rule_data_is_matching_with_its_overview_list_values() throws Throwable {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(targetingPage.createOrEditRuleDialogPopup)));
		String ruleName = "";
		String enteredName = enteredRuleName.replaceAll("\\s", "");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(enteredName))));
		List<WebElement> listNames = targetingPage.ruleNames();
		for (int k = 0; k < listNames.size(); k++) {
			ruleName = listNames.get(k).getText().replaceAll("\\s", "");
			if (enteredName.equals(ruleName)) {
				break;
			}
		}
		Assert.assertEquals(ruleName, enteredName);
	}

	@Then("^\"([^\"]*)\" is displayed for the Rule$")
	public void is_displayed_for_the_Rule(String arg1) throws Throwable {
		List<WebElement> listOfNames = targetingPage.ruleNames();
		for(int i=0;i<enteredRuleNameList.size();i++) {
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");
				if (enteredRuleNameList.get(i).equals(reqName)) {
					Assert.assertEquals(targetingPage.ruleActive(k+1).getText(), arg1, targetingPage.ruleActive(k+1).getText() +" is displayed for the created rule");;
					break;
				}
			}
		}
	}

	@Then("^Select (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" Rule items$")
	public void select_and_Rule_items(int num1, String inactive, int num3, String active) throws Throwable {
		enteredRuleNameList.clear();
		List<WebElement> listActives = targetingPage.ruleActives();
		for (int k = 0; k < listActives.size(); k++) {
			String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
			if (active.equals(reqActive) && num3 > 0) {
				targetingPage.ruleCheckBox(k+1).click();
				enteredRuleNameList.add(targetingPage.ruleName(k+1));
				num3--;
			}
			if(inactive.equals(reqActive) && num1 > 0) {
				targetingPage.ruleCheckBox(k+1).click();
				System.out.println(targetingPage.ruleName(k+1));
				enteredRuleNameList.add(targetingPage.ruleName(k+1));
				num1--;
			}
			if(num1 == 0 && num3 == 0) {
				break;
			}
		}
		Assert.assertEquals(num1,0,num1 +" Inactive Rule is not selected.");
		Assert.assertEquals(num3,0,num3 +" Active Rule is not selected.");
	}

	@Then("^Verify the following message is not displayed when the publisher changed for targeting rule$")
	public void verifyTheFollowingMessageIsNotDisplayedWhenThePublisherChangedForTargetingRule(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
			System.out.println("Check if Banner Message is displayed >>> "+ expectedMessage);
			Assert.assertFalse(targetingPage.getElementByXpathWithParameter(targetingPage.changePubBannerMsgXpath, expectedMessage).isDisplayed());
		}
	}

	@Then("^Verify the following message is displayed when the publisher changed for targeting rule$")
	public void verifyTheFollowingMessageIsDisplayedWhenThePublisherChangedForTargetingRule(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
			System.out.println("Banner Message "+ targetingPage.getChangePublisherBannerMsg());
			Assert.assertEquals(targetingPage.getChangePublisherBannerMsg(), expectedMessage);
		}
	}

	@When("^Close \"([^\"]*)\" Rule page$")
	public void closeCreateOrEditRulePage(String arg0) {
		wait.until(ExpectedConditions.elementToBeClickable(targetingPage.closeBtn));
		targetingPage.closeBtn.click();
	}

	@When("^Enable \"([^\"]*)\" checkbox in Inventory section$")
	public void enableCheckboxInInventorySection(String arg0) {
		if(!targetingPage.getElementByXpathWithParameter(targetingPage.checkboxDivInInventoryXpath, arg0).getAttribute("class").contains("v-item--active")){
			targetingPage.getElementByXpathWithParameter(targetingPage.checkboxInInventoryXpath, arg0).click();
		}
	}

	@And("^Select \"([^\"]*)\" from Protect specific inventory popup$")
	public void selectFromProtectSpecificInventoryPopup(String arg0) {
		wait.until(ExpectedConditions.visibilityOf(targetingPage.protectSpecificInventoryPopup));
		targetingPage.getElementByXpathWithParameter(targetingPage.selectRowValueInProtectSpecificInventoryPopup, arg0).click();
		wait.until(ExpectedConditions.visibilityOf(targetingPage.getElementByXpathWithParameter(targetingPage.includedValueInProtectSpecificInventoryPopup,arg0)));
	}
}
