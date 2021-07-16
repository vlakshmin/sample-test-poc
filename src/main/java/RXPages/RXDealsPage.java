package RXPages;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import RXUtitities.RXUtile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.NoSuchElementException;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RXDealsPage extends RXBasePage {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	public String dealHeaderStr = "Deals";

	// deals page heading
	@FindBy(xpath = "//h1[text()='Deals']")
	WebElement dealsPageHeader;

	//Edit deal
	@FindBy(css = "div.v-toolbar__title")
	public WebElement pageTitle;
	@FindBy(xpath = "//aside/header/div/button")
	public WebElement closeBtn;

	// overview buttons
	@FindBy(xpath = "//button/span[text()='Edit Deal']")
	public WebElement overviewEditbutton;
	@FindBy(xpath = "//button/span[text()='Deactivate Deal']")
	public WebElement overviewDisablebutton;
	@FindBy(xpath = "//button/span[text()='Activate Deal']")
	public WebElement overviewEnablebutton;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']")
	public WebElement overviewButtons;
	@FindAll(@FindBy(xpath = "//div[contains(@class,'vue-portal-target')]/button/span"))
	public List<WebElement> buttonsInPageHeader;
	@FindBy(xpath = "//button/span[text()='Deactivate Deals']")
	public WebElement deactivateDealsbutton;
	@FindBy(xpath = "//button/span[text()='Activate Deals']")
	public WebElement activateDealsbutton;

	@FindBy(xpath = "//i[contains(@class,'newspaper')]/parent::span")
	public WebElement createDealButton;
	@FindBy(xpath = "//div[contains(@class,'hidden') and contains(.,'Edit')]")
	private WebElement editDealMenuHeader;
	@FindBy(xpath = "//div[contains(@class,'hidden') and contains(.,'Create')]")
	private WebElement createDealMenuHeader;
	@FindBy(xpath = "//div[contains(@class,'v-text-field')][.//label[contains(@for,'input') and contains(.,'Publisher')]]")
	public WebElement publisherNameInput;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and .='Name']]")
	private WebElement nameInput;

	@FindBy(xpath="//div[contains(@class,'v-input__control') and contains(.,'Currency')]//div[contains(@class,'v-select__selection--comma')]")
	private WebElement 	currencyOption;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'Date')]]")
	private WebElement selectDateButton;
	@FindBy(xpath="//button//span[contains(.,'Save Deal')]")
	private WebElement saveDealButton;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'Private Auction')]]")
	private WebElement privateAuctionList;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'Floor Price')]]")
	private WebElement floorPriceField;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'DSP')]]")
	private WebElement dspList;
	@FindAll({@FindBy(xpath="//div[contains(@class,'v-messages__message')]")})
	public List<WebElement> requiredFieldsMessages;
	@FindBy(xpath="//label[text()='Floor Price']/parent::div/parent::div/following::div/div/div/div[@class='v-messages__message']")
	public WebElement floorPriceErrorMsg;

	@FindBy(xpath = "//div[@role='listbox']")
	private WebElement list;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__content']/button" ) 
	public WebElement closeCreatedealpage;
	
	//Deals General details

	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement publisherNamesEntered;
	@FindBy(xpath = "//label[text()='Private Auction']/following-sibling::div[@class='v-input__append-inner']/div")
	public WebElement privateAuctionDropDown;
	@FindBy(xpath = "//label[text()='Private Auction']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement privateActionFieldValue;
	@FindBy(xpath = "//label[text()='Name']/following-sibling::input") 
	public WebElement dealName;
	@FindBy(xpath = "//div[@class='row']/span[1]//div[@class='v-input--selection-controls__input']") 
	public WebElement activeToggle;
	@FindBy(xpath = "//div[@class='row']/span[2]//div[@class='v-input--selection-controls__input']") 
	public WebElement alwaysOn;
	/*
	 * @FindBy(xpath = "//label[text()='Value']/following-sibling::input" ) public
	 * WebElement value;
	 */
	@FindBy(xpath = "//label[text()='Date Range']/following-sibling::input" ) 
	public WebElement dateRange;
	@FindBy(xpath = "//label[text()='Date Range']/parent::div/parent::div/following-sibling::div/div/div/div" )
	public WebElement dateRangeErrorMsg;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__title']/div" )
	public WebElement dealHeaderName;
	@FindBy(xpath = "//label[text()='Currency']/following-sibling::div/div" ) 
	public WebElement currencyValue;
	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div[@class='v-select__selections']" ) 
	public WebElement dspDropDown;

	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div/div" )
	public WebElement dspValueDiv;
	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div/input" )
	public WebElement dspValueInput;
	@FindBy(xpath = "//label[text()='Floor Price']/following-sibling::input" ) 
	public WebElement value;
	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div[@class='v-input__append-inner']/i" )
	public WebElement dspInfoIcon;
	@FindBy(xpath = "//div[contains(@class, 'v-tooltip__content')]/span" )
	public WebElement dspInfo;

	//Deals buyers details
	@FindBy(xpath = "//div[contains(@class,'v-list-item__content')]//span[contains(@class,'mb-4')]//following-sibling::span")
	private WebElement detailsCard;
	@FindBy(xpath = "//span[@class='v-btn__content' and contains(.,'Add More Seats')]/parent::button" )
	public WebElement addMoreSeats;
	@FindBy(xpath = "//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']" ) 
	public WebElement buyerActivationToggle;
	@FindBy(xpath = "//label[text()='DSP Seat ID']/following-sibling::input" ) 
	public WebElement dSPSeatID;
	@FindBy(xpath = "//label[text()='DSP Seat Name']/following-sibling::input" ) 
	public WebElement dSPSeatName;
	@FindBy(xpath = "//label[text()='Advertiser ID']/following-sibling::input" ) 
	public WebElement AdvertiserId;
	@FindBy(xpath = "//label[text()='Advertiser Name']/following-sibling::input" )
	public WebElement advertiserName;
	@FindBy(xpath = "//label[text()='DSP Seat Passthrough String']/following-sibling::input" ) 
	public WebElement dSPSeatPassthroughString;
	@FindBy(xpath = "//label[text()='DSP Domain Advertiser Passthrough String']/following-sibling::input" ) 
	public WebElement dSPDomainAdvertiserPassthroughString;
	@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]")
	public WebElement detailsPopup;
	@FindBy(css = "div.buyers-card-label > h3")
	public WebElement buyerCardLabel;
	@FindBy(xpath = "//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']/input")
	public WebElement enableBtnInBuyerPanel;
	@FindBy(xpath = "//div[@class='buyers-card-grid']/button")
	public WebElement deleteBtnInBuyerPanel;
	@FindAll(@FindBy(css = "div.buyers-cards > div.cardPadding"))
	List<WebElement> buyerPanelList;

	/*
	 * @FindBy(xpath = "//label[text()='Related Proposal']/following-sibling::input"
	 * ) public WebElement relatedProposal;
	 */
	@FindBy(xpath = "//div[@class='buyers-card-grid'][2]/button/span" ) 
	public WebElement deleteDSPbuyer;
	@FindBy(xpath = "//button[@disabled=disabled']//span[text()='Add More Seats']" )
	public WebElement addMoreSeatsDisabled;
	// Banner Element
	@FindBy(xpath="//div[@class='v-banner__text']")
	public WebElement banner;
	@FindBy(xpath="//div[contains(@class,'v-alert__content')]")
	public WebElement alertMessage;
	//Change Publisher Banner.
	@FindBy(xpath = "//div[contains(@class,'v-banner__text') and contains(text(),'changing the Publisher')]" ) 
	public WebElement changePublisherBannerMsg;
	@FindBy(xpath = "//div[contains(text(),'changing the Publisher')]/ancestor::div[@class='v-banner__wrapper']//span[contains(text(),'CANCEL')]" ) 
	public WebElement cancelPubChangeBanner;
//	@FindBy(xpath = "//div[contains(text(),'changing the Publisher')]/ancestor::div[@class='v-banner__wrapper']//span[text()='ACCEPT']" ) 
	@FindBy(xpath = "//div[contains(text(),'changing the Publisher')]/ancestor::div[@class='v-banner__wrapper']//span[contains(text(),'ACCEPT')]" ) 
	public WebElement acceptPubChangeBanner;
	
	//Change DSP Banner.
	@FindBy(xpath = "//div[contains(@class,'v-banner__text') and contains(text(),'changing the DSP')]" ) 
	public WebElement changeDSPBannerMsg;
	@FindBy(xpath = "//div[contains(text(),'changing the DSP')]/ancestor::div[@class='v-banner__wrapper']//span[text()='CANCEL']" ) 
	public WebElement cancelDSPChangeBanner;
	@FindBy(xpath = "//div[contains(text(),'changing the DSP')]/ancestor::div[@class='v-banner__wrapper']//span[contains(text(),'ACCEPT')]" ) 
	public WebElement acceptDSPChangeBanner;

	//Change Currency Banner
	@FindBy(xpath = "//div[contains(@class,'v-banner__text') and contains(text(),'Changing the currency')]" )
	public WebElement changeCurrencyBannerMsg;


	//Save deal
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	@FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Updated')]")
	public WebElement saveButtonTxt;
	
	//Banner message after saving the deal
	@FindBy(xpath = "//div[contains(text(),'saved successfully!')]")
	public WebElement bannerMsgClipboard;
	@FindBy(xpath = "//div[contains(text(),'saved successfully!')]/button")
	private WebElement copyDealIDFromClipboard;
	@FindBy(xpath = "//div[contains(@class,'toast-item') and contains(.,'copied')]//a")
	private WebElement closeClipboardToastButton;
	
	//Search deal id
	@FindBy(xpath = "//label[text()='Search']//following-sibling::input")
	public WebElement searchDealId;


	@FindBy(xpath = "//table/tbody/tr[1]/td[4]/a")
	public WebElement dealNameInListview;
	@FindAll(@FindBy(xpath = "//table/tbody/tr/td[10]"))
	public List<WebElement> currencyColumnList;
	@FindAll(@FindBy(xpath = "//tbody/tr/td[7]"))
	public List<WebElement> statusColumnsInDealsList;

	//validation errors
	@FindBy(css = "div.v-alert__content > div")
	public WebElement validationErrorsPanel;
	public  String validationErrorsCssPath = "div.v-alert__content > div > ul > li";

	@FindBy(xpath = "//div[contains(@class,'v-list-item--highlighted')]/div/div[@class='v-list-item__title']")
	public WebElement highlightedDropdownValue;
	@FindBy(xpath = "//div[contains(@class,'v-list-item--highlighted')]/following-sibling::div/div/div[@class='v-list-item__title']")
	public WebElement valueBelowHighlightedDropdownValue;

//	String dealNameInListOne="//table/tbody/tr[1]/td[3]/span/a[contains(text(),";
	String dealNameInListOne="//table/tbody/tr[1]/td[4]/a[contains(text(),";

	//Variables
	public String enteredPrivateAuct;
	public String enteredDateRange;
	public String currencyFiledValue;
	public String EntereddealName;
	public String displayedDealHeaderName;
	public String enteredDSPValue;
	public String enteredValue;
	public String enteredDSPSeatID ;
	public String enteredDSPSeatName;
	public String enteredAdvertiserId ;
	public String enteredAdvertiserName;
	public String enteredDSPSeatPassthroughString ;
	public String enteredDSPDomainAdvertiserPassthroughString;
	private static Map<String,String> dspBuyersEnteredValues = new HashMap<>();

//	public String enteredRelatedProposal ;
	
	//Buyer Panel
	public String buyerDSPPanel="(//ancestor::div[2]//div[contains(@class,'cardPadding')])";
	public String buyerEnableDisable="(//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input'])";
	public String buyerDelete="(//button[contains(@class,'alignRight')]/span)";
	public String dSPEnable="(//ancestor::div[2]//div[contains(@class,'cardPadding')]//div[contains(@class,'v-input--is-label-active')]//input)";
	public String dSPDisable="(//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']//input[@aria-checked='false'])";

	public String currencyOptionString = "//div[@class='v-list-item__content']/div[text()='%s']";
	public String dealNameColumnString = "//table/tbody/tr[%s]/td[4]/a";
	public String trByRowNumberInDealsList = "//table/tbody/tr[%s]";
	public String checkboxByRowNum = "//tbody/tr[%s]/td[1]/div";
	public String idByRowNumber = "//tbody/tr[%s]/td[2]";
	public String statusByID = "//td[text()='%s']/parent::tr/td[7]";

	public String dropdownDivXpath = "//div[contains(@class,'menuable__content__active')]";
	public String changePubBannerMsgXpath = "//div[contains(@class,'v-banner__text') and contains(text(),'%s')]";

	// Action object
	Actions act = new Actions(driver);

	// Array for test data
	static ArrayList<String> testData = new ArrayList<String>();

	// Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 50);

	// Initialize page factory
	public RXDealsPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
		pubPage = new PublisherListPage();

	}
	public static Map<String,String> getBuyersEnteredValues() {
		return dspBuyersEnteredValues;
	}

	// Get the text of the media page
	public String getPageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(dealsPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

	}

	public static WebElement getDSPBuyerFieldElement(String name) {
		WebElement field = driver.findElement(By.xpath(String.format("//label[text()='%s']/following-sibling::input[@type='text']", name)));
		driverWait().until(ExpectedConditions.elementToBeClickable(field));
		return 	field;
	}

	public LinkedHashMap<String,String> getDealsDetailsData() {
		return detailsCard.findElements(By.xpath("//span[@class='bigger-label']")).stream()
					.collect(Collectors.toMap(WebElement::getText, e -> {
						WebElement element = e.findElement(By.xpath("./../p"));
						driverWait().until(ExpectedConditions.elementToBeClickable(element));
						return e.findElement(By.xpath("./../p")).getText();
					}, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public void clickOverViewEditbutton() {
		wait.until(ExpectedConditions.visibilityOf(overviewEditbutton));
		if (overviewEditbutton.isDisplayed()) {
			overviewEditbutton.click();
		}
	}

	public void clickOverViewEnablebutton() {
		wait.until(ExpectedConditions.visibilityOf(overviewEnablebutton));
		String enableText = overviewEnablebutton.getText().replaceAll("\\s", "");
		if (enableText.equals("ACTIVATEDEAL")) {
			overviewEnablebutton.click();
		}
	}

	public void clickOverViewDisablebutton() {
		wait.until(ExpectedConditions.visibilityOf(overviewDisablebutton));
		if (overviewDisablebutton.isDisplayed()) {
			overviewDisablebutton.click();
		}
	}

	public void clickCreateDealButton() {
		wait.until(visibilityOf(createDealButton));
		createDealButton.click();
		wait.until(visibilityOf(createDealMenuHeader));
	}
	public void enterFloorPrice(int amount) {
		floorPriceField.findElement(By.xpath(".//input")).sendKeys(String.valueOf(amount));
		System.out.println(getErrorMessageTextByField(floorPriceField));
	}

	/**
	 * Enter floor prices and check if error message is displayed.
	 * @param prices
	 * Price values that will be entered to the floor price input field.
	 * @return
	 * Returns List of boolean values, which indicate if error message is displayed.
	 */
	public List<Boolean> enterFloorPrices(List<Integer> prices) {
		List<Boolean> result = new ArrayList<>();
		WebElement priceInput = floorPriceField.findElement(By.xpath(".//input"));
		String error = "Floor price must be between 0 and 10,000.00";
		for (int price : prices) {
			wait.until(ExpectedConditions.elementToBeClickable(priceInput));
			priceInput.sendKeys(String.valueOf(price));
			result.add(floorPriceField.getAttribute("class").contains(error));
			while (priceInput.getAttribute("value").length() > 0)
			priceInput.sendKeys(Keys.BACK_SPACE);
			priceInput.sendKeys(Keys.BACK_SPACE);
		}
		return result;
	}

	public boolean isCreateDealMenuOpened() {
		return createDealMenuHeader.isDisplayed();
	}

	public boolean isEditDealMenuOpened() {
		return editDealMenuHeader.isDisplayed();
	}
	public void expandPublisherNameList() {
		publisherNameInput.click();
		//System.out.println("Publisher name attribute is: " + attributeContains(publisherNameInput, "class", "is-menu-active"));
		//wait.until(attributeContains(publisherNameInput, "class", "is-menu-active"));
	}

	public void selectPublisherByName(String name) throws Throwable {
		selectValueFromDropdown(name);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.dropdownDivXpath)));
	}

	public String getCurrencyText () {
		return currencyOption.getText();
	}

	public boolean isCurrencyNameValid (String currency) {
		return Arrays.asList(
				 "USD - Dollars"
				,"EUR - Euro"
				,"JPY - Yen",
				"RUB - Rubles").contains(currency);
	
	}

	public void clickSaveDealButton() {
		saveDealButton.click();
	}

	public boolean verifyErrorMessageForElements(WebElement... elements) {
		By errorMessageXpath = By.xpath("./ancestor::div[2]//div[contains(@class,'v-messages__message')]");

		// True if all required fields have an error message, false if not.
		// Date field has a specific text for the required message, and it is formatted accordingly
		Arrays.stream(elements).forEach(e -> wait.until(elementToBeClickable(e)));
		return Arrays.stream(elements)
				.allMatch(i ->
				getErrorMessageTextByField(i).replaceAll("\\.", "")
						.equalsIgnoreCase(("the " + i.getText() + " field is required")
								//.replaceAll("(?i).{9}range.{7}(?=is)","Start date ")
								.replaceAll("Floor Price","Value")));
	}
	public String getErrorMessageTextByField(WebElement element) {
		String text = element.findElement(By.xpath("./ancestor::div[@class = 'v-input__control']//div[contains(@class,'v-messages__message')]")).getText();
		System.out.println(text);
		return text;
	}
	public boolean verifyRequiredFields() {
		clickSaveDealButton();
		return verifyErrorMessageForElements(privateAuctionList, nameInput,
				selectDateButton, floorPriceField, dspList);
	}
	// TO DO: Check if this method works in other menus.

	public void selectPrivateAuctionByName(String name) {
		driverWait().until(ExpectedConditions.elementToBeClickable(privateAuctionDropDown));
		privateAuctionDropDown.click();
		selectValueFromDropdown(name);
		enteredDateRange=dateRange.getAttribute("value");
		currencyFiledValue=currencyValue.getText();
		enteredPrivateAuct= privateActionFieldValue.getText();
	}
	
	public void selectDSPByName(String name) {
		dspDropDown.click();
		selectValueFromDropdown(name);
		enteredDSPValue= dspValueInput.getText();
	}
	
	public void enterDealName(String EntdealName)
	{
		dealName.sendKeys(EntdealName);
		EntereddealName= dealName.getAttribute("value");
		displayedDealHeaderName= dealHeaderName.getText();
	}
	
	public void enterValue(String dealValue)
	{
		value.sendKeys(dealValue);
//		enteredValue=value.getAttribute("value");
		BigDecimal bd = new BigDecimal(dealValue);
		enteredValue = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println("Floor Price's entered value >>> " + dealValue);
		System.out.println("Format Floor Price's entered value >>> " + enteredValue);
			
	}
	public void clickBuyerActivationToggle(String action)
	{
			// XOR operator is used, if both conditions return different boolean values, button is clicked
			if (isBuyerEnabled() ^ action.equalsIgnoreCase("Enable"))
				buyerActivationToggle.click();
	}

	public boolean isBuyerEnabled()
	{
		return driver.findElement(By.xpath("//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']/input")).getAttribute("aria-checked").equalsIgnoreCase("true");
	}
	
//	public void enterDSPValues(String seatID,String seatName,String advId,String advName,String seatPassThrString,String advPassThrString,String relatProposal)
	public void enterDSPValues(String seatID,String seatName,String advId,String advName,String seatPassThrString,String advPassThrString)
	{
		
		dSPSeatID.sendKeys(seatID + rxUTL.getRandomNumberFourDigit());
		dSPSeatName.sendKeys(seatName + rxUTL.getRandomNumberFourDigit());
		AdvertiserId.sendKeys(advId + rxUTL.getRandomNumberFourDigit());
		advertiserName.sendKeys(advName + rxUTL.getRandomNumberFourDigit());
		dSPSeatPassthroughString.sendKeys(seatPassThrString + rxUTL.getRandomNumberFourDigit());
		dSPDomainAdvertiserPassthroughString.sendKeys(advPassThrString + rxUTL.getRandomNumberFourDigit());
//		relatedProposal.sendKeys(relatProposal + rxUTL.getRandomNumberFourDigit());
		
		enteredDSPSeatID = dSPSeatID.getAttribute("value");
		enteredDSPSeatName = dSPSeatName.getAttribute("value");
		enteredAdvertiserId  = AdvertiserId.getAttribute("value");
		enteredAdvertiserName  = advertiserName.getAttribute("value");
		enteredDSPSeatPassthroughString  = dSPSeatPassthroughString.getAttribute("value");
		enteredDSPDomainAdvertiserPassthroughString  = dSPDomainAdvertiserPassthroughString.getAttribute("value");
//		enteredRelatedProposal  = relatedProposal.getAttribute("value");
	}

	/**
	 * <br>Overload of {@link #enterDSPValues(Map, boolean, boolean, boolean)}.
	 * @param valuesToEnter
	 */
	public void enterDSPValues(Map<String,String> valuesToEnter)
	{
		enterDSPValues(valuesToEnter, false, false, false);
	}

	/**
	 * <br> Overload of: {@link #enterDSPValues(Map, boolean, boolean, boolean)}.
	 * @param valuesToEnter
	 * @param isOriginalValue
	 */
	public void enterDSPValues(Map<String,String> valuesToEnter, boolean isOriginalValue)
	{
		enterDSPValues(valuesToEnter, isOriginalValue, false, false);
	}
	/**
	 * <br> Overload of: {@link #enterDSPValues(Map, boolean, boolean, boolean)}.
	 * @param valuesToEnter
	 * @param isOriginalValue
	 * @param isAutofill
	 */
	public void enterDSPValues(Map<String,String> valuesToEnter, boolean isOriginalValue, boolean isAutofill)
	{
		enterDSPValues(valuesToEnter, isOriginalValue, false, false);
	}
	/**
	 * Enters values to DSP buyer fields.
	 * @param valuesToEnter
	 * <br>Map, where the key is name of the field and value is the text to be entered.
	 * @param isOriginalValue
	 * <br>When <b>true</b>, values will be taken from Map that is passed to the method.
	 * <br>When <b>false</b>, it will generate random values and save them to a map.
	 * @param isAutofill
	 * Sends a text to the input field, then selects this text from dropdown.
	 * @param isClear
	 * <br>When <b>true</b>, field will be cleared before entering text and it's value in cache map will be empty.
	 */
	public void enterDSPValues(Map<String,String> valuesToEnter, boolean isOriginalValue, boolean isAutofill, boolean isClear)
	{
		if (isClear) clearBuyersEnteredValues(valuesToEnter);
		for (Map.Entry<String,String> entry: valuesToEnter.entrySet()) {
			String key = entry.getKey(), value = entry.getValue(), text;
			text = isOriginalValue ? value : value + rxUTL.getRandomNumberFourDigit();
			dspBuyersEnteredValues.put(key, text);
			if (isAutofill) {
				enterTextToDspFieldUsingDropdown(key, text);
				continue;
			}
			else {
				getDSPBuyerFieldElement(key).sendKeys(text);
			}
		}
	}
	public void clearBuyersEnteredValues(Map<String,String> valuesToEnter) {
		valuesToEnter.entrySet()
				.stream()
				.forEach(e-> {
					int attempts = 0;
					WebElement field = getDSPBuyerFieldElement(e.getKey());
					field.clear();
					while(!field.getAttribute("value").equals("") && attempts++ < 300){
						field.sendKeys(Keys.BACK_SPACE);
					}
					dspBuyersEnteredValues.put(e.getKey(), "");
				});
	}

	public void enterTextToDspFieldUsingDropdown(String key ,String text) {
		getDSPBuyerFieldElement(key).sendKeys(text.substring(0,text.length() - 2));
		selectValueFromDropdown(text);
	}
	public void clearTextFromFields(String key) {
		getDSPBuyerFieldElement(key).clear();
	}

	public String getChangePublisherBannerMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
			wait.until(
					ExpectedConditions.visibilityOf(changePublisherBannerMsg));
			String actualMessage =changePublisherBannerMsg.getText().replaceAll("\u3000", "");
			return actualMessage;
	}
	
	public String cancelOrAcceptChangePublisherBannerMsg(String cancelOrAccept)
	{
		String pubChangeStatus="";
		if(cancelOrAccept.equalsIgnoreCase("Cancel"))
		{
			cancelPubChangeBanner.click();
		}else
		{
			acceptPubChangeBanner.click();
			pubChangeStatus="Accepted";
		}	
		return pubChangeStatus;
	}
	public String getBannerMessage () {
		wait.until(ExpectedConditions.visibilityOf(banner));
		return banner.getText();
	}
	public String getAlertMessage () {
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		return alertMessage.getText();
	}
	public String getChangeDSPBannerMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
			wait.until(
					ExpectedConditions.visibilityOf(changeDSPBannerMsg));
			String actualMessage =changeDSPBannerMsg.getText().replaceAll("\u3000", "");
			return actualMessage;
	}
	
	public String cancelOrAcceptChangeDSPBannerMsg(String cancelOrAccept)
	{
		String pubChangeStatus="";
		if(cancelOrAccept.equalsIgnoreCase("Cancel"))
		{
			cancelDSPChangeBanner.click();
		}else
		{
			acceptDSPChangeBanner.click();
			pubChangeStatus="Accepted";
		}	
		return pubChangeStatus;
	}
	
	public void saveDeal()
	{
		saveButton.click();
	}
		
	
	
	public void copyDealIDToClipBoard()
	{
		copyDealIDFromClipboard.click();
		wait.until(ExpectedConditions.visibilityOf(closeClipboardToastButton));
		closeClipboardToastButton.click();
	}
	
	public void pasteDealIdToSearch()
	{
		wait.until(ExpectedConditions.visibilityOf(searchDealId));
		searchDealId.clear();
		searchDealId.sendKeys(EntereddealName);
		
	}
	public void clickOnDealNameInListView() 
	{
		String dealNameList =dealNameInListOne+EntereddealName+")]";
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(dealNameList))));
		dealNameInListview.click();
		
	}
	
	public WebElement addedDSPPanel(int n) 
	{
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(buyerDSPPanel+"["+n+"]")));
		return driver.findElement(By.xpath(buyerDSPPanel+"["+n+"]"));
		
	}
	
	public void clickAddMoreSeats() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMoreSeats));
		System.out.println("Inner Side Count time" + 1);
		addMoreSeats.click();
	}

	public WebElement enableDSPdisable(int n) 
	{
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(buyerEnableDisable+"["+n+"]")));
		return driver.findElement(By.xpath(buyerEnableDisable+"["+n+"]"));
	}

	public WebElement buyerDelete(int n) 
	{
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(buyerDelete+"["+n+"]")));
		return driver.findElement(By.xpath(buyerDelete+"["+n+"]"));
	}
	public WebElement dSPEnable(int n) 
	{
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(dSPEnable+"["+n+"]")));
		return driver.findElement(By.xpath(dSPEnable+"["+n+"]"));
	}
	public WebElement dSPDisable(int n) 
	{
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(dSPDisable+"["+n+"]")));
		return driver.findElement(By.xpath(dSPDisable+"["+n+"]"));
	}

	public int getBuyersCardPaddingElemts(){
		return buyerPanelList.size();
	}

	public WebElement getCurrencyDropdownValue(String value){
		return driver.findElement(By.xpath(String.format(currencyOptionString, value)));
	}

	public String getChangeCurrencyBannerMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(
				ExpectedConditions.visibilityOf(changeCurrencyBannerMsg));
		String actualMessage =changeCurrencyBannerMsg.getText().replaceAll("\u3000", "");
		return actualMessage;
	}

	public boolean checkIfErrorIsDisplayed(String error){
		boolean flag = false;
		System.out.println("the expected error === " + error);
		for(WebElement elemt : driver.findElements(By.cssSelector(this.validationErrorsCssPath))){
			System.out.println("validation error >>> " + elemt.getText().trim());
			if(elemt.getText().trim().equals(error)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean isElementPresent(String path){
		try{
			driver.findElement(By.cssSelector(path));
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public void hoverOverOnDSPInfoICon(){
		new Actions(driver).moveToElement(this.dspInfoIcon).build().perform();
		wait.until(ExpectedConditions.attributeToBe(this.dspInfoIcon, "aria-expanded","true"));
	}

	public WebElement getDealNameColumnLink(int rownum){
		return driver.findElement(By.xpath(String.format(dealNameColumnString, rownum)));
	}

	public boolean verifyIfCheckboxIsChecked(int rowNum){
		boolean flag = false;
		String classAttr = driver.findElement(By.xpath(String.format(trByRowNumberInDealsList, rowNum))).getAttribute("class");
		System.out.println("tr class atrribute >>> " + classAttr);
		if(classAttr.contains("selected")){
			flag = true;
		}
		return flag;
	}

	public WebElement getElementByXpathWithParameter(String xpath, String parameter){
		return driver.findElement(By.xpath(String.format(xpath, parameter)));
	}

	public boolean verifyButtonDisplaysInPageHeader(String btnName){
		boolean flag = false;
		String button;
		System.out.println("Check if button exist >>> " + btnName);
		for(WebElement btnElemt : this.buttonsInPageHeader){
			button = btnElemt.getText().trim();
			System.out.println("btnElemt.getText().trim() >>> " + button);
			if(button.equalsIgnoreCase(btnName)){
				flag = true;
				break;
			}
		}
		return  flag;
	}
}