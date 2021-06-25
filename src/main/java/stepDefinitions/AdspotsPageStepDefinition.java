package stepDefinitions;

import static org.testng.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXNavOptions;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdspotsPageStepDefinition extends RXAdspotsPage {

	String isAdspotActive;
	String enteredPublisherName;
	String enteredAdSpotName;
	String enteredRelatedMedia;
	String enteredCategories;
	String enteredFilter = "";
	String enteredPosition;
	String enteredMinVideoDuration;
	String enteredMaxVideoDuration;
	String enteredDefaultSizes = "";
	String enteredBannerSizes = "";
	String enteredInBannerVideoSizes = "";
	String enteredInBannerPlayback = "";
	String enteredDefaultPrice;
	String enteredBannerPrice;
	String currencyCode;
	String enteredNativePrice;
	String enteredInBannerVideoPrice;
	String defaultPriceCurrency;
	String bannerPriceCurrency;
	String nativePriceCurrency;
	String inBannerVideoPriceCurrency;
	String enteredVideoPlacementType;

	RXAdspotsPage adspotsPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(AdspotsPageStepDefinition.class);

	public AdspotsPageStepDefinition() {
		super();
		adspotsPage = new RXAdspotsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();

	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
	private LinkedHashMap<String,String> detailsData = new LinkedHashMap<>();
//=========================================================================================================	
	// Verify if user is displayed with media list page on clicking media navigation
	// link

	@When("^Click on Adspots option under Inventory$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandInventory();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.adspotsUndrInventory));
		navOptions.adspotsUndrInventory.click();
		adSpotTypeEnteredValues.clear();
	}

	@When("^Click on Adspots sub menu$")
	public void check_for_Adspot() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(navOptions.adspotsUndrInventory));
		navOptions.adspotsUndrInventory.click();

	}

	@Then("^User displayed with Adspots page$")
	public void user_displayed_with_seats_page() throws Throwable {
		Assert.assertEquals(adspotsPage.getPageHeading(), adspotsPage.adspotsHeaderStr);
		log.info("Adspots Page Header is asserted  and it is : " + adspotsPage.getPageHeading());

	}

//=========================================================================================================
// Verify search functionality on the adspots overview page
	@Then("^Verify the search functionality with the following names$")
	public void verifySearch(DataTable dt) throws InterruptedException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String adspotName = list.get(i).get("Name");
			String columnName = list.get(i).get("ColumnName");
			adspotsPage.searchAdspots(adspotName);
			waitForPageLoaderToDisappear();
			WebElement elem = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"));
			if (elem.getText().equals("No data available")) {
				log.info("The searched adspot named as " + adspotName + "is not available");
				Assert.assertTrue(true,
						"The searched element is not available, but it is not matching with the shown message");
			} else {
				List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader(columnName);
				for (int j = 0; j < coulmnData.size(); j++) {
					Assert.assertEquals(coulmnData.get(j).getText().trim(), adspotName);
				}
			}

		}
	}

	@And("^Search for adspot")
	public void searchForAdSpot(DataTable dt) throws InterruptedException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String adspotName = list.get(i).get("Name");
			adspotsPage.searchAdspots(adspotName);
			waitForPageLoaderToDisappear();
		}
	}

	@Then("^Verify the overview page contains following columns$")
	public void verifyColumnsNameInList(DataTable dt) throws InterruptedException {
		List columnsNamePassed = new ArrayList();
		List columnsNamePresent = new ArrayList();
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String adspotName = list.get(i).get("ColumnName");
			columnsNamePassed.add(adspotName);
			Collections.sort(columnsNamePassed);

		}
		List<WebElement> numberOFHeaders = navOptions.tableHeadersList;
		for (int i = 0; i < numberOFHeaders.size(); i++) {
			columnsNamePresent.add(numberOFHeaders.get(i).getText());
			Collections.sort(columnsNamePresent);
		}
		Assert.assertEquals(columnsNamePresent, columnsNamePassed);

	}

//Verify enabling abd disabling of an adspot from the overview page

	@When("^Verify enabling and disabling of an adspot from the overview page$")
	public void verifyHEnableDisableAdspot() throws InterruptedException {
		for (int i = 0; i <= 1; i++) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
			List<WebElement> columnData = navOptions.getColumnDataMatchingHeader("Active/Inactive");
			String status = columnData.get(0).getText();
			String enableText = adspotsPage.overviewButtonsBlock.getText().replaceAll("\\s", "");
			Assert.assertEquals(enableText, "EDITADSPOTDEACTIVATEADSPOTACTIVATEADSPOT");
			switch (status) {
			case "Active":
				adspotsPage.clickOverViewDisablebutton();
				Thread.sleep(3000);
				List<WebElement> columnData1 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(columnData1.get(0).getText(), "Inactive");
				break;
			case "Inactive":
				adspotsPage.clickOverViewEnablebutton();
				Thread.sleep(3000);
				List<WebElement> columnData2 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(columnData2.get(0).getText(), "Active");
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@When("^Verify (.*) of multiple adspots from the overview page$")
	public void verifyHEnableDiableMultipleAdspots(String isEnable) throws InterruptedException {
			List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active/Inactive");
			String status1 = coulmnData.get(0).getText();
			String status2 = coulmnData.get(1).getText();
			if(status1.equals("Active")&&status2.equals("Active")) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
				Assert.assertTrue(adspotsPage.overviewEditButton.isDisplayed());
				Assert.assertTrue(adspotsPage.overviewDisableButton.isDisplayed());
				adspotsPage.clickOverViewDisablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
			}else if(status1.equals("Inactive")&&status2.equals("Inactive")) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
				Assert.assertTrue(adspotsPage.overviewEditButton.isDisplayed());
				Assert.assertTrue(adspotsPage.overviewSecondButton.isDisplayed());
				String enableText = adspotsPage.overviewSecondButton.getText().replaceAll("\\s", "");
				Assert.assertEquals(enableText, "DEACTIVATEADSPOT");
				adspotsPage.clickOverViewEnablebutton();
				Thread.sleep(3000);
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
			}
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[2]/td[1]/div//i")).click();
			if(isEnable.equalsIgnoreCase("Enable")) {
				adspotsPage.clickOverViewMultipleEnablebuttons();
				Thread.sleep(3000);
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
				Assert.assertEquals(coulmnData2.get(1).getText(), "Active");
			}else if(isEnable.equalsIgnoreCase("Disable")) {
				adspotsPage.clickOverViewMultipleDisablebuttons();
				Thread.sleep(3000);
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
				Assert.assertEquals(coulmnData2.get(0).getText(), "Inactive");
				Assert.assertEquals(coulmnData2.get(1).getText(), "Inactive");
			}


	}

//Verify sorting of the table list columns
	@Then("^Verify the sorting functionality with the following columns$")
	public void verifySort(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='ID']/parent::th"))
				.click();
		List dataInEachColumn;
		List dataInEachColumnSorted = new ArrayList();
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String columnName = list.get(i).get("ColumnName");
			String sortType = list.get(i).get("SortType");
			driver.findElement(By.xpath(
					"//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + columnName + "']/parent::th"))
					.click();
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
			if (sortType.equalsIgnoreCase("Desc")) {
				driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='"
						+ columnName + "']/parent::th")).click();
				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
			}

			String sorting = driver.findElement(By.xpath(
					"//div[@class='v-data-table__wrapper']//thead//th/span[text()='" + columnName + "']/parent::th"))
					.getAttribute("aria-sort");
			switch (sorting) {
			case "ascending":
				if(columnName.contains("Active")) {
					List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader(columnName);
					dataInEachColumnSorted = returnListOfColumnData(coulmnData2, columnName);
					dataInEachColumn = new ArrayList(dataInEachColumnSorted);
					System.out.println("Before sorting: " + dataInEachColumn);
					Collections.sort(dataInEachColumnSorted);
					Collections.reverse(dataInEachColumnSorted);
					System.out.println("After sorting: " + dataInEachColumnSorted);
					Assert.assertEquals(dataInEachColumn, dataInEachColumnSorted);

				}
				else {
				List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader(columnName);
				dataInEachColumnSorted = returnListOfColumnData(coulmnData1, columnName);
				dataInEachColumn = new ArrayList(dataInEachColumnSorted);
				System.out.println("Before sorting: " + dataInEachColumn);
				Collections.sort(dataInEachColumnSorted);
				System.out.println("After sorting: " + dataInEachColumnSorted);
				Assert.assertEquals(dataInEachColumn, dataInEachColumnSorted);
				}
				break;
			case "descending":
				if(columnName.contains("Active")) {
					List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader(columnName);
					dataInEachColumnSorted = returnListOfColumnData(coulmnData1, columnName);
					dataInEachColumn = new ArrayList(dataInEachColumnSorted);
					System.out.println("Before sorting: " + dataInEachColumn);
					Collections.sort(dataInEachColumnSorted);
					System.out.println("After sorting: " + dataInEachColumnSorted);
					Assert.assertEquals(dataInEachColumn, dataInEachColumnSorted);

				}else {
				List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader(columnName);
				dataInEachColumnSorted = returnListOfColumnData(coulmnData2, columnName);
				dataInEachColumn = new ArrayList(dataInEachColumnSorted);
				System.out.println("Before sorting: " + dataInEachColumn);
				Collections.sort(dataInEachColumnSorted);
				Collections.reverse(dataInEachColumnSorted);
				System.out.println("After sorting: " + dataInEachColumnSorted);
				Assert.assertEquals(dataInEachColumn, dataInEachColumnSorted);
				}
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	public List returnListOfColumnData(List<WebElement> coulmnData, String columnName) throws ParseException {
		List dataInEachColumn = new ArrayList();
		for (int j = 0; j < coulmnData.size(); j++) {
			if (columnName.contains("ID")) {
				dataInEachColumn.add(Integer.parseInt(coulmnData.get(j).getText()));
			}
//   	 else if(columnName.contains("Date")) {
//   		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//   		 dataInEachColumn.add(dateFormatter.parse(coulmnData.get(j).getText()));
//   	 }
			else {
				dataInEachColumn.add(coulmnData.get(j).getText().toLowerCase());
			}

		}
		return dataInEachColumn;

	}

	@When("^Click on the Adspot create button$")
	public void createAdspotButtonClick() throws InterruptedException {
		createButtonClick("Create Ad Spot", "Create Ad Spot");
	}

	@Then("^Verify following fields are mandatory for create page$")
	public void verifyMandatorFields(DataTable dt) throws InterruptedException {
		String expectedNotification;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(1000);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			if(fieldName.contains("Date")) {
				 expectedNotification = "Start date is required.";
			}else {
			 expectedNotification = "The" + " " + fieldName + " " + "field is required";
			}
			WebElement notificationMsg = driver.findElement(
					By.xpath("//div[@class='v-messages__wrapper']/div[text()='" + expectedNotification + "']"));
			Assert.assertTrue(notificationMsg.isDisplayed());

		}

	}


	@When("^Verify the following message is displayed when the publisher changed$")
	public void verifyMessageOnPublisherChange(DataTable dt) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String expectedMessage = list.get(i).get("Message");
			wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']//div[@class='v-banner__content']"))));
			String actualMessage = adspotsPage.publisherChangeBannerTxt.getText().replaceAll("\u3000", "");
			Assert.assertEquals(actualMessage, expectedMessage);

		}
	}

	@When("^Select \"(.*)\" on the publisher change banner$")
	public void clickBannerAcceptOrCancel(String action) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='v-banner__actions']"))));
		if (action.equalsIgnoreCase("Cancel")) {
			driver.findElement(By.xpath("//div[@class='v-banner__actions']/button[1]")).click();
		} else if (action.equalsIgnoreCase("Accept")) {
			driver.findElement(By.xpath("//div[@class='v-banner__actions']/button[2]")).click();
		}
		wait.until(
				ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='v-banner__actions']"))));

	}

	@When("^Click on save button$")
	public void clickSaveBtn() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(navOptions.saveButton));
		navOptions.saveButton.click();

	}

	@When("^Verify the save is failed$")
	public void verifySaveFailed() throws Throwable {
		driverWait().until(ExpectedConditions.visibilityOf(navOptions.errorPopup));
		if(!navOptions.errorPopup.isDisplayed()){
			fail("Error is not displayed.");
		}
	}

	@When("^Click on save button and wait for dialog to close$")
	public void clickSaveBtnCloseDialog() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.visibilityOf(navOptions.saveButton));
		navOptions.saveButton.click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		Thread.sleep(5000);

	}

	@When("^Select publisher name from the dropdown list item index (.*)$")
	public void selectPublisherDropDown(int index) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(adspotsPage.publisherNameDropDown));
		adspotsPage.publisherNameDropDown.click();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@role='listbox']/div[" + index + "]"))));
		WebElement dropDownValue = driver.findElement(By.xpath("//div[@role='listbox']/div[" + index + "]"));
		js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
		dropDownValue.click();
		Thread.sleep(5000);
		enteredPublisherName = adspotsPage.publisherNameField.getText();
		System.out.println("publisher entered as :" + enteredPublisherName);

	}

	@Then("^Enter the following data in the general card of adspot$")
	public void enterGenaralCard(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		waitForCreatePageHeaderToBeVisible();
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");
			String listValueIndex = list.get(i).get("ListValueIndex");

			switch (fieldName) {
			case "Publisher Name":
				WebElement dropDownPublisher;
				wait.until(ExpectedConditions.visibilityOf(adspotsPage.publisherNameDropDown));
				adspotsPage.publisherNameDropDown.click();
				if (value.equalsIgnoreCase("ListValue")) {

					wait.until(ExpectedConditions.visibilityOf(
							driver.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"))));
					dropDownPublisher = driver
							.findElement(By.xpath("//div[@role='listbox']/div[" + listValueIndex + "]"));

				} else {
					selectValueFromDropdown(value);
				}
				enteredPublisherName = adspotsPage.publisherNameField.getText();
				System.out.println("publisher entered as :" + enteredPublisherName);
				wait.until(ExpectedConditions.visibilityOf(adspotsPage.categoriesDropDown));
				break;
			case "Active":
				if (value.equalsIgnoreCase("Active")) {
					String style = driver.findElement(By.xpath("//div[text()='General']/preceding-sibling::span/div"))
							.getAttribute("class");
					if (!style.contains("active")) {
						adspotsPage.adspotEnableButton.click();
						Assert.assertTrue(
								driver.findElement(By.xpath("//div[text()='General']/preceding-sibling::span/div"))
										.getAttribute("class").contains("active"));
						isAdspotActive = "Active";
					} else {
						isAdspotActive = "Active";
					}
				} else if (value.equalsIgnoreCase("Inactive")) {
					String style = driver.findElement(By.xpath("//div[text()='General']/preceding-sibling::span/div"))
							.getAttribute("class");
					if (style.contains("active")) {
						adspotsPage.adspotEnableButton.click();
						Assert.assertTrue(
								!driver.findElement(By.xpath("//div[text()='General']/preceding-sibling::span/div"))
										.getAttribute("class").contains("active"));
						isAdspotActive = "Inactive";
					} else {
						isAdspotActive = "Inactive";
					}
				}
				break;
			case "Ad Spot Name":
				while (!adspotsPage.adSpotNameField.getAttribute("value").equals("")) {
					adspotsPage.adSpotNameField.sendKeys(Keys.BACK_SPACE);
				}
				adspotsPage.adSpotNameField.sendKeys(value);
				enteredAdSpotName = value;
				System.out.println("Entered Adspot name:" + enteredAdSpotName);
				break;
			case "Related Media":
				WebElement dropDownValueMedia;
				wait.until(ExpectedConditions.visibilityOf(adspotsPage.relatedMediaDropDown));
				adspotsPage.relatedMediaDropDown.click();
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"))));
					dropDownValueMedia = driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"));
				} else {
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
							"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div//div[contains(text(),'"
									+ value + "')]"))));
					dropDownValueMedia = driver.findElement(By.xpath(
							"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div//div[contains(text(),'"
									+ value + "')]"));
				}
				js.executeScript("arguments[0].scrollIntoView()", dropDownValueMedia);
				dropDownValueMedia.click();
				Thread.sleep(2000);
				enteredRelatedMedia = adspotsPage.relatedMediaField.getText();
				System.out.println("Related Media entered as :" + enteredRelatedMedia);
				break;
			case "Categories":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.categoriesDropDown));
//					adspotsPage.categoriesDropDown.click();
					js.executeScript("arguments[0].click()", adspotsPage.categoriesDropDown);

					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]//div[@class='v-input__slot']"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]//div[@class='v-input__slot']"));
							Thread.sleep(1000);
							js.executeScript("arguments[0].click()", dropDownValue);
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]//div[@class='v-input__slot']"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]//div[@class='v-input__slot']"));
						Thread.sleep(1000);
						js.executeScript("arguments[0].click()", dropDownValue);
					}
//				 dropDownValue.click();
					driver.findElement(By.xpath("//label[text()='Test Mode']")).click();
					Thread.sleep(2000);
					enteredCategories = adspotsPage.categoriesField.getText();
					System.out.println("Categories entered as :" + enteredCategories);
				}

				break;
			case "Filter":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.filterDropDown));
					adspotsPage.filterDropDown.click();
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"))));
					WebElement dropDownValue = driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
					dropDownValue.click();

					Thread.sleep(2000);
					enteredFilter = adspotsPage.filterField.getText();
					System.out.println("Filter entered as :" + enteredFilter);
				}
				break;
			case "Position":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.positionDropDown));
					adspotsPage.positionDropDown.click();
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"))));
					WebElement dropDownValue = driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
					dropDownValue.click();

					Thread.sleep(2000);
					enteredPosition = adspotsPage.positionField.getText();
					System.out.println("Categories entered as :" + enteredPosition);
				}
				break;
			case "Default Ad Sizes":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.defaultSizesDropDown));
					adspotsPage.defaultSizesDropDown.click();
					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"));
							js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
							Thread.sleep(1000);
							dropDownValue.click();
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"));
						Thread.sleep(1000);
						js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
						dropDownValue.click();
					}

					driver.findElement(By.xpath("//label[text()='Test Mode']")).click();
					Thread.sleep(3000);
					if(!listValueIndex.equals("1")) {
					List<WebElement> enteredSizesLsit = adspotsPage.defaultSizesField;
					enteredDefaultSizes = "";
					for (int k = 0; k < enteredSizesLsit.size(); k++) {
						enteredDefaultSizes += enteredSizesLsit.get(k).getText();
					}
					System.out.println("Default Sizes entered as :" + enteredDefaultSizes);
					}
				}
				break;
			case "Default Floor Price":
				while (!adspotsPage.defaultPriceField.getAttribute("value").equals("")) {
					adspotsPage.defaultPriceField.sendKeys(Keys.BACK_SPACE);
				}
				adspotsPage.defaultPriceField.sendKeys(value);
				Thread.sleep(2000);
				enteredDefaultPrice = adspotsPage.defaultPriceField.getAttribute("value") + ".00";
				defaultPriceCurrency = adspotsPage.defaultPriceCurrency.getText();
				System.out.println("Entered Default floor price:" + defaultPriceCurrency + enteredDefaultPrice);
				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Enter the following data in the banner card of adspot$")
	public void enterBannerCard(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");
			String listValueIndex = list.get(i).get("ListValueIndex");

			switch (fieldName) {
			case "Floor Price":
				if (value.equalsIgnoreCase("Default")) {
					bannerPriceCurrency = adspotsPage.bannerPriceCurrency.getText();
					enteredBannerPrice = "Same as default";
				}
				else {
				while (!adspotsPage.bannerPriceField.getAttribute("value").equals("")) {
					adspotsPage.bannerPriceField.sendKeys(Keys.BACK_SPACE);
				}
				adspotsPage.bannerPriceField.sendKeys(value);
				Thread.sleep(2000);
				adspotsPage.bannerPriceField.click();
				adspotsPage.adSpotNameHeader.click();
				enteredBannerPrice = adspotsPage.bannerPriceField.getAttribute("value");
				bannerPriceCurrency = adspotsPage.bannerPriceCurrency.getText();
				currencyCode = adspotsPage.getCurrencyCode(bannerPriceCurrency);
				adSpotTypeEnteredValues.put("Banner Floor Price", bannerPriceCurrency + currencyCode + enteredBannerPrice);
				System.out.println("Entered floor price for banner:" + bannerPriceCurrency + enteredBannerPrice);
				}
				break;
			case "Ad Sizes":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.bannerSizesDropDown));
					adspotsPage.bannerSizesDropDown.click();
					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"));

							js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
							Thread.sleep(1000);
							dropDownValue.click();
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"));
						js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
						Thread.sleep(1000);
						dropDownValue.click();
					}

					adspotsPage.bannerPriceField.click();
					Thread.sleep(3000);
					List<WebElement> enteredSizesLsit = adspotsPage.bannerSizesField;
					enteredBannerSizes = "";
					for (int k = 0; k < enteredSizesLsit.size(); k++) {
						enteredBannerSizes += enteredSizesLsit.get(k).getText();
					}
					adSpotTypeEnteredValues.put("Banner Ad Sizes", enteredBannerSizes);
					System.out.println("Banner Sizes entered as :" + enteredBannerSizes);
				}else if (value.equalsIgnoreCase("Default")) {
					enteredBannerSizes = "Same as default";
				}
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Enter the following data in the in-banner video card of adspot$")
	public void enterinBannerCard(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");
			String listValueIndex = list.get(i).get("ListValueIndex");

			switch (fieldName) {
			case "Floor Price":
				if (value.equalsIgnoreCase("Default")) {
					enteredInBannerVideoPrice = "Same as default";
					inBannerVideoPriceCurrency = adspotsPage.inBannerVideoPriceCurrency.getText();
				}
				else {
				while (!adspotsPage.inBannerVideoPriceField.getAttribute("value").equals("")) {
					adspotsPage.inBannerVideoPriceField.sendKeys(Keys.BACK_SPACE);
				}
				adspotsPage.inBannerVideoPriceField.sendKeys(value);
				Thread.sleep(2000);
				adspotsPage.inBannerVideoPriceField.click();
				adspotsPage.adSpotNameHeader.click();
				enteredInBannerVideoPrice = adspotsPage.inBannerVideoPriceField.getAttribute("value");
				inBannerVideoPriceCurrency = adspotsPage.inBannerVideoPriceCurrency.getText();
				currencyCode = adspotsPage.getCurrencyCode(inBannerVideoPriceCurrency);
				adSpotTypeEnteredValues.put("Video Floor Price", inBannerVideoPriceCurrency + currencyCode + enteredInBannerVideoPrice);
				System.out.println("Entered floor price for in-banner video:" + inBannerVideoPriceCurrency
						+ enteredInBannerVideoPrice);
				}
				break;
			case "Ad Sizes":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.inBannerVideoSizesDropDown));
					adspotsPage.inBannerVideoSizesDropDown.click();
					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"));
							js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
							Thread.sleep(1000);
							adSpotTypeEnteredValues.put("Video Ad Sizes", dropDownValue.getText());
							dropDownValue.click();
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"));
						js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
						Thread.sleep(1000);
						adSpotTypeEnteredValues.put("Video Ad Sizes", dropDownValue.getText());
						dropDownValue.click();
					}

					adspotsPage.inBannerVideoPriceField.click();
					Thread.sleep(3000);
					List<WebElement> enteredSizesLsit = adspotsPage.inBannerVideoSizesField;
					enteredInBannerVideoSizes = "";
					for (int k = 0; k < enteredSizesLsit.size(); k++) {
						enteredInBannerVideoSizes += enteredSizesLsit.get(k).getText();
					}
					System.out.println("InBanner Video Sizes entered as :" + enteredInBannerVideoSizes);
				}else if (value.equalsIgnoreCase("Default")) {
					enteredInBannerVideoSizes = "Same as default";
				}
				break;
			case "Minimum Video Duration":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.minVideoDurDropDown));
					adspotsPage.minVideoDurDropDown.click();
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"))));
					WebElement dropDownValue = driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
					dropDownValue.click();

					Thread.sleep(2000);
					enteredMinVideoDuration = adspotsPage.minVideoDurField.getText();
					if(!enteredMinVideoDuration.toLowerCase().equals("no limit")){
						adSpotTypeEnteredValues.put("Video Min Duration", enteredMinVideoDuration + " seconds");
					} else {
					adSpotTypeEnteredValues.put("Video Min Duration", enteredMinVideoDuration);
					}
					System.out.println("Miniumum Video Duration entered as :" + enteredMinVideoDuration);
				}
				break;
			case "Maximum Video Duration":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.maxVideoDurDropDown));
					adspotsPage.maxVideoDurDropDown.click();
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"))));
					WebElement dropDownValue = driver.findElement(
							By.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
									+ listValueIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
					dropDownValue.click();

					Thread.sleep(2000);
					enteredMaxVideoDuration = adspotsPage.maxVideoDurField.getText();
					if(!enteredMaxVideoDuration.toLowerCase().equals("no limit")){
						adSpotTypeEnteredValues.put("Video Max Duration", enteredMaxVideoDuration + " seconds");
					} else {
						adSpotTypeEnteredValues.put("Video Max Duration", enteredMaxVideoDuration);
					}
					System.out.println("Maximum Video Duration entered as :" + enteredMaxVideoDuration);
				}
				break;
			case "Playback Methods":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.playbackMethodsDropDown));
					adspotsPage.playbackMethodsDropDown.click();
					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"));
							js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
							Thread.sleep(1000);
							adSpotTypeEnteredValues.put("Video Playback Method", dropDownValue.getText());
							dropDownValue.click();
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"));
						js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
						Thread.sleep(1000);
						adSpotTypeEnteredValues.put("Video Playback Method", dropDownValue.getText());
						dropDownValue.click();
					}

					adspotsPage.minVideoDurField.click();
					Thread.sleep(3000);
					List<WebElement> enteredSizesLsit = adspotsPage.playbackMethodsField;
					enteredInBannerPlayback = "";
					for (int k = 0; k < enteredSizesLsit.size(); k++) {
						enteredInBannerPlayback += enteredSizesLsit.get(k).getText();
					}
					System.out.println("InBanner Video Playback Methods entered as :" + enteredInBannerPlayback);
				}
				break;
			case "Video Placement Type":
				if (value.equalsIgnoreCase("ListValue")) {
					wait.until(ExpectedConditions.visibilityOf(adspotsPage.videoPlacementDropDown));
					adspotsPage.videoPlacementDropDown.click();
					if (listValueIndex.contains(",")) {
						List<String> items = Arrays.asList(listValueIndex.split("\\s*,\\s*"));
						for (int j = 0; j < items.size(); j++) {
							listValueIndex = items.get(j);
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"))));
							WebElement dropDownValue = driver.findElement(By.xpath(
									"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
											+ listValueIndex + "]"));
							js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
							Thread.sleep(1000);
							dropDownValue.click();
						}
					} else {
						wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"))));
						WebElement dropDownValue = driver.findElement(By
								.xpath("//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["
										+ listValueIndex + "]"));
						js.executeScript("arguments[0].scrollIntoView()", dropDownValue);
						Thread.sleep(1000);
						dropDownValue.click();
					}

					Thread.sleep(3000);
					enteredVideoPlacementType = adspotsPage.videoPlacementField.getText();
					adSpotTypeEnteredValues.put("Video Placement Type", enteredVideoPlacementType);
					System.out.println("Video Placement Type entered as :" + enteredVideoPlacementType);

				}
				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Enter the following data in the native card of adspot$")
	public void enterNativeCard(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			String value = list.get(i).get("Value");

			switch (fieldName) {
			case "Floor Price":
				if (value.equalsIgnoreCase("Default")) {
					enteredNativePrice = "Same as default";
					nativePriceCurrency = adspotsPage.nativePriceCurrency.getText();
				}else {
				while (!adspotsPage.nativePriceField.getAttribute("value").equals("")) {
					adspotsPage.nativePriceField.sendKeys(Keys.BACK_SPACE);
				}
				adspotsPage.nativePriceField.sendKeys(value);
				Thread.sleep(2000);
				adspotsPage.nativePriceField.click();
				adspotsPage.adSpotNameHeader.click();
				enteredNativePrice = adspotsPage.nativePriceField.getAttribute("value");
				nativePriceCurrency = adspotsPage.nativePriceCurrency.getText();
				currencyCode = adspotsPage.getCurrencyCode(nativePriceCurrency);
				adSpotTypeEnteredValues.put("Native Floor Price", nativePriceCurrency + currencyCode + enteredNativePrice);
				System.out.println("Entered floor price for native:" + nativePriceCurrency + enteredNativePrice);
				}
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Verify the following columns value with the created data for the general card of adspot$")
	public void verifyGeneralCardValues(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Active":
				String style = driver.findElement(By.xpath("//div[text()='General']/preceding-sibling::span/div"))
						.getAttribute("class");
				if (style.contains("active")) {

					Assert.assertEquals(isAdspotActive, "Active");
				} else {
					Assert.assertEquals(isAdspotActive, "Inactive");
				}

				break;
			case "Ad Spot Name":
				Assert.assertEquals(adspotsPage.adSpotNameField.getAttribute("value"), enteredAdSpotName);

				break;
			case "Publisher Name":
				Assert.assertEquals(adspotsPage.publisherNameField.getText(), enteredPublisherName);

				break;
			case "Related Media":
				Assert.assertEquals(adspotsPage.relatedMediaField.getText(), enteredRelatedMedia);

				break;
			case "Categories":
				Assert.assertEquals(adspotsPage.categoriesField.getText(), enteredCategories);

				break;
			case "Filter":
				Assert.assertEquals(adspotsPage.filterField.getText(), enteredFilter);

				break;
			case "Position":
				Assert.assertEquals(adspotsPage.positionField.getText(), enteredPosition);

				break;
			case "Default Ad Sizes":
				String sizes = "";
				List<WebElement> enteredSizesLsit = adspotsPage.defaultSizesField;
				for (int k = 0; k < enteredSizesLsit.size(); k++) {
					sizes += enteredSizesLsit.get(k).getText();
				}
				Assert.assertEquals(sizes, enteredDefaultSizes);

				break;
			case "Default Floor Price":
				String price = enteredDefaultPrice;
				adspotsPage.defaultPriceField.click();
				adspotsPage.positionField.click();
				Assert.assertEquals(adspotsPage.defaultPriceField.getAttribute("value"), price);
				Assert.assertEquals(adspotsPage.defaultPriceCurrency.getText(), defaultPriceCurrency);

				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify the following columns value with the created data for the banner card of adspot$")
	public void verifyBannerCardValues(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Ad Sizes":
				if(driver
				.findElements(By.xpath(
						"//form/div[3]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
				.size() == 0) {
				String sizes = driver.findElement(By.xpath("//form/div[3]//label[text()='Ad Sizes']"
							+ "/following-sibling::div[@class='v-select__selections']/input")).getAttribute("placeholder");
					Assert.assertEquals(sizes, enteredBannerSizes);
				}else {
				String sizes = "";
				List<WebElement> enteredSizesLsit = adspotsPage.bannerSizesField;
				for (int k = 0; k < enteredSizesLsit.size(); k++) {
					sizes += enteredSizesLsit.get(k).getText();
				}
				System.out.println(sizes);
				System.out.println(enteredBannerSizes);
				Assert.assertEquals(sizes, enteredBannerSizes);
				}
				break;
			case "Floor Price":
				if(adspotsPage.bannerPriceField.getAttribute("value").isEmpty()) {
					Assert.assertEquals(adspotsPage.bannerPriceField.getAttribute("placeholder"), "");
				}else {
					adspotsPage.bannerPriceField.click();
					adspotsPage.adSpotNameHeader.click();
				Assert.assertEquals(adspotsPage.bannerPriceField.getAttribute("value"), enteredBannerPrice);
				}
				Assert.assertEquals(adspotsPage.bannerPriceCurrency.getText(), bannerPriceCurrency);

				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify the following columns value with the created data for the native card of adspot$")
	public void verifyNativeCardValues(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {

			case "Floor Price":
				if(adspotsPage.nativePriceField.getAttribute("value").isEmpty()) {
					adspotsPage.nativePriceField.click();
					Assert.assertEquals(adspotsPage.nativePriceField.getAttribute("placeholder"), enteredNativePrice);
				}else {
					adspotsPage.nativePriceField.click();
					adspotsPage.adSpotNameHeader.click();
					Assert.assertEquals(adspotsPage.nativePriceField.getAttribute("value"), enteredNativePrice);
				}

				Assert.assertEquals(adspotsPage.nativePriceCurrency.getText(), nativePriceCurrency);

				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify the following columns value with the created data for the in-banner video card of adspot$")
	public void verifyInBannerCardValues(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Ad Sizes":
				if(driver
						.findElements(By.xpath(
								"//form/div[5]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
						.size() == 0) {
							Assert.assertEquals(adspotsPage.adSizeInput.getAttribute("placeholder"), "");
						}else {
				String sizes = "";
				List<WebElement> enteredSizesLsit = adspotsPage.inBannerVideoSizesField;
				for (int k = 0; k < enteredSizesLsit.size(); k++) {
					sizes += enteredSizesLsit.get(k).getText();
				}
				Assert.assertEquals(sizes, enteredInBannerVideoSizes);
						}
				break;
			case "Playback Methods":
				String sizesMethods = "";
				List<WebElement> enteredSizesList = adspotsPage.playbackMethodsField;
				for (int k = 0; k < enteredSizesList.size(); k++) {
					sizesMethods += enteredSizesList.get(k).getText();
				}
				Assert.assertEquals(sizesMethods, enteredInBannerPlayback);

				break;
			case "Floor Price":
				if(adspotsPage.inBannerVideoPriceField.getAttribute("value").isEmpty()) {
					Assert.assertEquals(adspotsPage.inBannerVideoPriceField.getAttribute("placeholder"),
							"");
				}else {
					adspotsPage.inBannerVideoPriceField.click();
					adspotsPage.adSpotNameHeader.click();
					Assert.assertEquals(adspotsPage.inBannerVideoPriceField.getAttribute("value"),
						enteredInBannerVideoPrice);
				}
				Assert.assertEquals(adspotsPage.inBannerVideoPriceCurrency.getText(), inBannerVideoPriceCurrency);

				break;
			case "Minimum Video Duration":
				Assert.assertEquals(adspotsPage.minVideoDurField.getText(), enteredMinVideoDuration);

				break;
			case "Maximum Video Duration":
				Assert.assertEquals(adspotsPage.maxVideoDurField.getText(), enteredMaxVideoDuration);

				break;
			case "Video Placement Type":
				Assert.assertEquals(adspotsPage.videoPlacementField.getText(), enteredVideoPlacementType);

				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}
	}

	@Then("^Verify the following columns values for the general card of adspot is empty$")
	public void verifyGeneralCardValuesEmpty(DataTable dt) throws InterruptedException, ParseException {

		boolean isPresent;
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");

			switch (fieldName) {
			case "Ad Spot Name":
				Assert.assertEquals(adspotsPage.adSpotNameHeader.getText(), "Create Ad Spot");

				break;
			case "Related Media":
				isPresent = driver.findElements(By.xpath(
						"//label[text()='Related Media']/following-sibling::div[@class='v-select__selections']/div"))
						.size() > 0;
				Assert.assertEquals(isPresent, false);

				break;
			case "Categories":
				isPresent = driver.findElements(By.xpath(
						"//label[text()='Categories']/following-sibling::div[@class='v-select__selections']//span[@class='v-chip__content']"))
						.size() > 0;
				Assert.assertEquals(isPresent, false);

				break;
			case "Filter":
				isPresent = driver
						.findElements(By.xpath(
								"//label[text()='Filter']/following-sibling::div[@class='v-select__selections']/div"))
						.size() > 0;
				Assert.assertEquals(isPresent, false);

				break;
			case "Position":
				isPresent = driver
						.findElements(By.xpath(
								"//label[text()='Position']/following-sibling::div[@class='v-select__selections']/div"))
						.size() > 0;
				Assert.assertEquals(isPresent, false);

				break;
			case "Default Ad Sizes":

				isPresent = driver.findElements(By.xpath(
						"//label[text()='Default Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
						.size() > 0;
				Assert.assertEquals(isPresent, false);

				break;
			case "Default Floor Price":
				Assert.assertEquals(adspotsPage.defaultPriceField.getAttribute("value"), "");

				break;
			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}

	@When("^Verify error messages for sizes and floor price for the following cards$")
	public void verifyCardErrorMsg(DataTable dt) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String card = list.get(i).get("Card");
			String sizeErrorMsg = list.get(i).get("SizeErrorMsg");
			String priceErrorMsg = list.get(i).get("FloorPriceErrorMsg");
			switch (card) {
			case "General":
				List<WebElement> errorMsg = adspotsPage.generalSizePriceMsg;
				Assert.assertEquals(errorMsg.get(0).getText(), sizeErrorMsg);
				Assert.assertEquals(errorMsg.get(1).getText(), priceErrorMsg);

				break;
			case "Banner":
				List<WebElement> errorBannerMsg = adspotsPage.bannerSizePriceMsg;
				Assert.assertEquals(errorBannerMsg.get(0).getText(), sizeErrorMsg);
				Assert.assertEquals(errorBannerMsg.get(1).getText(), priceErrorMsg);

				break;
			case "Native":
				List<WebElement> errorNativeMsg = adspotsPage.nativeSizePriceMsg;
				Assert.assertEquals(errorNativeMsg.get(0).getText(), priceErrorMsg);

				break;
			case "InBannerVideo":
				List<WebElement> errorInBannerMsg = adspotsPage.inBannerVideoSizePriceMsg;
				Assert.assertEquals(errorInBannerMsg.get(0).getText(), sizeErrorMsg);
				Assert.assertEquals(errorInBannerMsg.get(1).getText(), priceErrorMsg);
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}
		}

	}

	@When("^\"(.*)\" the banner card$")
	public void expandCollapseBanner(String status) throws Throwable {

		if (status.equalsIgnoreCase("Expand")) {
			String style = driver.findElement(By.xpath("//form/div[3]/span[1]")).getAttribute("style");
			if (style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.bannerExpandButton);
				adspotsPage.bannerExpandButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//form/div[3]/span[1]")).getAttribute("style").contains(""));
			}
		} else if (status.equalsIgnoreCase("Collapse")) {
			String style = driver.findElement(By.xpath("//form/div[3]/span[1]")).getAttribute("style");
			if (!style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.bannerExpandButton);
				adspotsPage.bannerExpandButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//form/div[3]/span[1]")).getAttribute("style").contains("none"));
			}
		} else if (status.equalsIgnoreCase("Enable")) {
			String style = driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.bannerEnableButton);
				adspotsPage.bannerEnableButton.click();
				Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
						.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.put("Banner Ad Sizes", "Same as default");
			adSpotTypeEnteredValues.put("Banner Floor Price", "Same as default");
			adSpotTypeEnteredValues.remove("Banner");
		} else if (status.equalsIgnoreCase("Disable")) {
			String style = driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.bannerEnableButton);
				adspotsPage.bannerEnableButton.click();
				Assert.assertTrue(!driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
						.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.put("Banner", "Inactive");
			adSpotTypeEnteredValues.remove("Banner Ad Sizes");
			adSpotTypeEnteredValues.remove("Banner Floor Price");
		}

	}

	@When("^Verify the banner card is \"(.*)\"$")
	public void expandCollapseBannerCheck(String status) throws Throwable {
		if (status.equalsIgnoreCase("Enabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (status.equalsIgnoreCase("Disabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Banner']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@When("^Verify the native card is \"(.*)\"$")
	public void expandCollapseNativeCheck(String status) throws Throwable {
		if (status.equalsIgnoreCase("Enabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (status.equalsIgnoreCase("Disabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@When("^Verify the in-banner video card is \"(.*)\"$")
	public void expandCollapseInBannerCheck(String status) throws Throwable {
		if (status.equalsIgnoreCase("Enabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (status.equalsIgnoreCase("Disabled")) {
			String style = driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	@When("^\"(.*)\" the in-banner video card$")
	public void expandCollapseInBanner(String status) throws Throwable {

		if (status.equalsIgnoreCase("Expand")) {
			String style = driver.findElement(By.xpath("//form/div[5]/span[1]")).getAttribute("style");
			if (style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.inBannerVideoExpandButton);
				adspotsPage.inBannerVideoExpandButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//form/div[5]/span[1]")).getAttribute("style").contains(""));
			}
		} else if (status.equalsIgnoreCase("Collapse")) {
			String style = driver.findElement(By.xpath("//form/div[5]/span[1]")).getAttribute("style");
			if (!style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.inBannerVideoExpandButton);
				adspotsPage.inBannerVideoExpandButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//form/div[5]/span[1]")).getAttribute("style").contains("none"));
			}
		} else if (status.equalsIgnoreCase("Enable")) {
			String style = driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.inBannerVideoEnableButton);
				driverWait().until(ExpectedConditions.elementToBeClickable(adspotsPage.inBannerVideoEnableButton));
				adspotsPage.inBannerVideoEnableButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
								.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.put("Video Floor Price", "Same as default");
			adSpotTypeEnteredValues.remove("Video");
		} else if (status.equalsIgnoreCase("Disable")) {
			String style = driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.inBannerVideoEnableButton);
				adspotsPage.inBannerVideoEnableButton.click();
				Assert.assertTrue(
						!driver.findElement(By.xpath("//div[text()='Video']/preceding-sibling::span/div"))
								.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.remove("Video Floor Price");
			adSpotTypeEnteredValues.put("Video", "Inactive");
		}

	}

	@When("^\"(.*)\" the native card$")
	public void expandCollapseNative(String status) throws Throwable {

		if (status.equalsIgnoreCase("Expand")) {
			String style = driver.findElement(By.xpath("//form/div[4]/span[1]")).getAttribute("style");
			if (style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.nativeExpandButton);
				adspotsPage.nativeExpandButton.click();
				Assert.assertTrue(
						!driver.findElement(By.xpath("//form/div[4]/span[1]")).getAttribute("style").contains("none"));
			}
		} else if (status.equalsIgnoreCase("Collapse")) {
			String style = driver.findElement(By.xpath("//form/div[4]/span[1]")).getAttribute("style");
			if (!style.contains("none")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.nativeExpandButton);
				adspotsPage.nativeExpandButton.click();
				Assert.assertTrue(
						driver.findElement(By.xpath("//form/div[4]/span[1]")).getAttribute("style").contains("none"));
			}
		} else if (status.equalsIgnoreCase("Enable")) {
			String style = driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (!style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.nativeEnableButton);
				adspotsPage.nativeEnableButton.click();
				Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
						.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.put("Native Floor Price", "Same as default");
			adSpotTypeEnteredValues.remove("Native");
		} else if (status.equalsIgnoreCase("Disable")) {
			String style = driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
					.getAttribute("class");
			if (style.contains("active")) {
				js.executeScript("arguments[0].scrollIntoView()", adspotsPage.nativeEnableButton);
				adspotsPage.nativeEnableButton.click();
				Assert.assertTrue(!driver.findElement(By.xpath("//div[text()='Native']/preceding-sibling::span/div"))
						.getAttribute("class").contains("active"));
			}
			adSpotTypeEnteredValues.put("Native", "Inactive");
			adSpotTypeEnteredValues.remove("Native Floor Price");
		}

	}

	@When("^Verify the created adspot data is matching with its overview list values$")
	public void verifyOverviewValues() throws Throwable {
		String adSpotName = driver
				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[4]/a")).getText()
				.replaceAll("\\s", "");
		String entergedName = enteredAdSpotName.replaceAll("\\s", "");
		Assert.assertEquals(adSpotName, entergedName);
		String publisherName = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[5]"))
				.getText();
		Assert.assertEquals(publisherName, enteredPublisherName);
		String mediaName = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[6]"))
				.getText();
		Assert.assertEquals(mediaName, enteredRelatedMedia);
		String isActive = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[7]"))
				.getText();
		Assert.assertEquals(isActive, isAdspotActive);
//		String categoryName = driver
//				.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[7]//span[@role='button']"))
//				.getText().replaceAll("\\s", "");
//		String enterCatName = enteredCategories.replaceAll("\\s", "");
//		Assert.assertEquals(categoryName, enterCatName);
//		if (!enteredFilter.equals("")) {
//			String filter = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[8]"))
//					.getText();
//
//			Assert.assertEquals(filter, enteredFilter);
//		}
		String defaultSize = driver
				.findElement(
						By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[8]//div"))
				.getText().replaceAll("\\s", "");
		String enteredSizes = enteredDefaultSizes.replaceAll("\\s", "");
		System.out.println(defaultSize);
		System.out.println(enteredSizes);
		Assert.assertEquals(defaultSize, enteredSizes);
		String floorPrice = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[9]"))
				.getText();
		String price = enteredDefaultPrice;
		String currency = defaultPriceCurrency.substring(0, defaultPriceCurrency.indexOf(":"));
		System.out.println(price + " " + currency);
		System.out.println(floorPrice);
		Assert.assertEquals(floorPrice, price + " " + currency);

	}

	@When("^Click on the created adspotname in the overview page$")
	public void clickNameOverview() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {

			String enteredName = enteredAdSpotName.replaceAll("\\s", "");
			List<WebElement> listOfNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[4]/a"));
			for (int k = 0; k < listOfNames.size(); k++) {
				String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");

				if (enteredName.equals(reqName)) {
					listOfNames.get(k).click();
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//aside[@class='dialog']/header//div[text()='Edit Ad Spot: " + enteredAdSpotName + "']"))));
//			Thread.sleep(4000);
		} catch (NullPointerException e) {
			driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[4]/span/a")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));

		}
	}

	@Then("^Verify following fields are disabled on create/edit adspot page$")
	public void verifyLabelsDisabled(DataTable dt) throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			String fieldName = list.get(i).get("FieldName");
			switch (fieldName) {
			case "Publisher Name":
				String isPubNameDisabled = adspotsPage.publisherNameField.getAttribute("class");
				Assert.assertTrue(isPubNameDisabled.contains("disabled"));
				break;
			case "Related Media":
				String isRelatedMediaDisabled = adspotsPage.relatedMediaField.getAttribute("class");
				Assert.assertTrue(isRelatedMediaDisabled.contains("disabled"));
				break;

			default:
				Assert.assertTrue(false, "The status fields supplied does not match with the input");

			}

		}
	}

	@Then("^Verify publisher field is disabled on create/edit page$")
	public void verifyPubLabelDisabled() throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		String isPubNameDisabled = adspotsPage.publisherNameField.getAttribute("class");
	    String value = adspotsPage.publisherNameField.getText();
		Assert.assertTrue(isPubNameDisabled.contains("disabled"));
		Assert.assertFalse(value.isEmpty());
	}

	@Then("^Verify Categories filed has subcategories$")
	public void verifySubCategories() throws InterruptedException, ParseException {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		wait.until(ExpectedConditions.visibilityOf(adspotsPage.categoriesDropDown));
		adspotsPage.categoriesDropDown.click();
		js.executeScript("arguments[0].click()", adspotsPage.categoriesDropDown);
		for(int i=1;i<=2;i++) {
		WebElement firstCategoryExpand = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
						"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["+i+"]//div[contains(@class, 'append-icon')]/i"))));
		js.executeScript("arguments[0].click()", firstCategoryExpand);
		Thread.sleep(1000);
		boolean isSubCategoryPresent = driver.findElements(By.xpath(
				"//div[contains(@class, 'menuable__content__active')]/div[@role='listbox']/div["+i+"]//div[contains(@class, 'v-list-group__items')]/div"))
				.size() > 0;
		Assert.assertEquals(isSubCategoryPresent, true);
		js.executeScript("arguments[0].click()", firstCategoryExpand);
		}
		driver.findElement(By.xpath("//label[text()='Test Mode']")).click();
	}

	@When("^Hover over adspot details button$")
	public void hoverOverDealButton() {
		driver.navigate().refresh();
		adspotsPage.hoverOverDetailsButton();
		detailsData = adspotsPage.getDetailsData();
	}

	@Then("^Verify adspot details data is correct$")
	public void verifyDetailsData() {
		System.out.println(RXAdspotsPage.getAdSpotTypeEnteredValues());
		Assert.assertTrue(areEqual(RXAdspotsPage.getAdSpotTypeEnteredValues(), detailsData));
		adspotsPage.adSpotsSearchField.click();
	}

	@When("^Click in Floor Price input in \"([^\"]*)\" card$")
	public void clickInFloorPriceInputInCard(String arg0) throws Throwable {
		adspotsPage.getElementByXpathString(adspotsPage.floorPriceInputString, arg0).click();
		Thread.sleep(2000);
	}

	@And("^Click outside Floor Price input in \"([^\"]*)\" card$")
	public void clickOutsideFloorPriceInputInCard(String arg0) throws Throwable {
		adspotsPage.getElementByXpathString(adspotsPage.cardXpathString, arg0).click();
		Thread.sleep(2000);
	}

	@Then("^verify that no error is displayed below Floor Price input in \"([^\"]*)\" card$")
	public void verifyThatNoErrorIsDisplayedBelowFloorPriceInputInCard(String arg0) throws Throwable {
		Assert.assertFalse(adspotsPage.isElementPresent(String.format(adspotsPage.floorPriceMsgString,arg0)));
	}

	@Then("^Verify that specified floor price not duplicated in \"([^\"]*)\" card$")
	public void verifyThatSpecifiedFloorPriceNotDuplicatedInCard(String arg0) throws Throwable {
		Assert.assertFalse(adspotsPage.isElementPresent(String.format(adspotsPage.duplicatedFloorPriceString,arg0)));
	}

	@When("^Click on Ad Spot Name in first row in list view$")
	public void clickOnAdSpotNameInFirstRowInListView() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		adspotsPage.adSpotNameInFirstRow.click();
		wait.until(ExpectedConditions.visibilityOf(navOptions.saveButton));
	}
}
