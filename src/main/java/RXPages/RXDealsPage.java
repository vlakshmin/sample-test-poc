package RXPages;

import java.util.*;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RXDealsPage extends RXBaseClass {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	public String dealHeaderStr = "Deals";
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// deals page heading
	@FindBy(xpath = "//h1[text()='Deals']")
	WebElement dealsPageHeader;

	// overview buttons
	@FindBy(xpath = "//button/span[text()='Edit Deal']")
	public WebElement overviewEditbutton;
	@FindBy(xpath = "//button/span[text()='Deactivate Deal']")
	public WebElement overviewDisablebutton;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']/button[2]/span")
	public WebElement overviewEnablebutton;

	@FindBy(xpath = "//i[contains(@class,'newspaper')]/parent::span")
	private WebElement createDealButton;
	@FindBy(xpath = "//div[contains(@class,'hidden') and contains(.,'Create')]")
	private WebElement createDealMenuHeader;
	@FindBy(xpath = "//div[contains(@class,'v-text-field')][.//label[contains(@for,'input') and contains(.,'Publisher')]]")
	public WebElement publisherNameInput;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and .='Name']]")
	private WebElement nameInput;
	@FindAll({@FindBy(xpath = "//div[contains(@role,'listbox') and contains(@class,'v-list')]/div")})
	public List<WebElement> publisherNames;
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

	@FindBy(xpath = "//div[@role='listbox']")
	private WebElement list;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__content']/button" ) 
	public WebElement closeCreatedealpage;
	
	//Deals General details

	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement publisherNamesEntered;
	@FindBy(xpath = "//label[text()='Private Auction']/following-sibling::div[@class='v-select__selections']") 
	public WebElement privateActionDropDown;
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
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__title']/div" ) 
	public WebElement dealHeaderName;
	@FindBy(xpath = "//label[text()='Currency']/following-sibling::div/div" ) 
	public WebElement currencyValue;
	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div[@class='v-select__selections']" ) 
	public WebElement dspDropDown;
	/*
	 * @FindBy(xpath = "//label[text()='DSP']/following-sibling::div[1]/div" )
	 * public WebElement dspValue;
	 */
	@FindBy(xpath = "//label[text()='DSP']/following-sibling::div[1]/input" )
	public WebElement dspValue;
	@FindBy(xpath = "//label[text()='Floor Price']/following-sibling::input" ) 
	public WebElement value;
	
	//Deals buyers details
	@FindBy(xpath = "//span[@class='v-btn__content' and text()='Add More Seats']//parent::button" )
	public WebElement addMoreSeats;
	@FindBy(xpath = "//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']" ) 
	public WebElement dsPbuyerEnabled; 
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
	/*
	 * @FindBy(xpath = "//label[text()='Related Proposal']/following-sibling::input"
	 * ) public WebElement relatedProposal;
	 */
	@FindBy(xpath = "//div[@class='buyers-card-grid'][2]/button/span" ) 
	public WebElement deleteDSPbuyer;
	@FindBy(xpath = "//button[@disabled=\"disabled\"]//span[text()='Add More Seats']" )
	public WebElement addMoreSeatsDisabled;
	// Banner Element
	@FindBy(xpath="//div[@class='v-banner__text']")
	public WebElement banner;
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

	
	//Save deal
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	@FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Updated')]")
	public WebElement saveButtonTxt;
	
	//Banner message after saving the deal
	@FindBy(xpath = "//div[contains(text(),'saved successfully!')]")
	public WebElement bannerMsgClipboard;
	@FindBy(xpath = "//div[contains(text(),'saved successfully!')]/button")
	public WebElement copyDealIDFrmclipboard;
	
	//Search deal id
	@FindBy(xpath = "//label[text()='Search']//following-sibling::input")
	public WebElement searchDealId;


	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/a")
	public WebElement dealNameInListview;
	
//	String dealNameInListOne="//table/tbody/tr[1]/td[3]/span/a[contains(text(),";
	String dealNameInListOne="//table/tbody/tr[1]/td[3]/a[contains(text(),";
	
	
	
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
//	public String enteredRelatedProposal ;
	
	//Buyer Panel
	public String buyerDSPPanel="(//ancestor::div[2]//div[contains(@class,'cardPadding')])";
	public String buyerEnableDisable="(//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input'])";
	public String buyerDelete="(//button[contains(@class,'elementPadding alignRight')]/span)";
	public String dSPEnable="(//ancestor::div[2]//div[contains(@class,'cardPadding')]//div[contains(@class,'v-input--is-label-active')]//input)";
	public String dSPDisable="(//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']//input[@aria-checked='false'])";
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

	// Get the text of the media page
	public String getPageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(dealsPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

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

	public void expandPublisherNameList() {
		publisherNameInput.click();
		System.out.println("Publisher name attribute is: " + attributeContains(publisherNameInput, "class", "is-menu-active"));
		wait.until(attributeContains(publisherNameInput, "class", "is-menu-active"));
	}

	public void selectPublisherByName(String name) throws Throwable {
		int attempt = 0;
		wait.until(attributeContains(publisherNameInput, "class", "is-menu-active"));

		// Check if list contains publisher name, scroll down if not
		do {
			js.executeScript("arguments[0].scrollIntoView(false)", publisherNames.get(publisherNames.size() - 1));
		}
		while (!publisherNames.stream()
				  .map(WebElement::getText)
				  .anyMatch(text -> name.equals(text)) && attempt++ < 20);

		// Get publisher web element by name from the method parameter
		WebElement publisherName = publisherNames.stream()
				.filter(i -> i.getText().equalsIgnoreCase(name))
				.findFirst()
				.orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(String.format("Publisher by the name %s wasn't found.", name)));
		js.executeScript("arguments[0].scrollIntoView({block: \"center\"})", publisherName);
		wait.until(ExpectedConditions.elementToBeClickable(publisherName));
		publisherName.click();
		
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
				.peek(e -> System.out.println(e.getText()))
				.allMatch(i ->
				getErrorMessageTextByField(i).replaceAll("\\.", "")
						.equalsIgnoreCase(("the " + i.getText() + " field is required")
								.replaceAll("(?i).{9}range.{7}(?=is)","Start date ")
								.replaceAll("Floor Price","Value")));
	}
	public String getErrorMessageTextByField(WebElement element) {
		return element.findElement(By.xpath("./ancestor::div[2]//div[contains(@class,'v-messages__message')]")).getText();
	}
	public boolean verifyRequiredFields() {
		clickSaveDealButton();
		return verifyErrorMessageForElements(privateAuctionList, nameInput,
				selectDateButton, floorPriceField, dspList);
	}


	public void selectPrivateAuctionByName(String name) {
		privateActionDropDown.click();
		int attempt = 0;
		
		// Check if list contains publisher name, scroll down if not
		do {
			js.executeScript("arguments[0].scrollIntoView(false)", publisherNames.get(publisherNames.size() - 1));
		}
		while (!publisherNames.stream()
				  .map(WebElement::getText)
				  .anyMatch(text -> name.equals(text)) && attempt++ < 20);

		// Get publisher web element by name from the method parameter
		WebElement privateAuctionName = publisherNames.stream()
				.filter(i -> i.getText().equalsIgnoreCase(name))
				.findFirst()
				.orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(String.format("Private Auction by the name %s wasn't found.", name)));
		js.executeScript("arguments[0].scrollIntoView({block: \"center\"})", privateAuctionName);
		wait.until(elementToBeClickable(privateAuctionName));
		privateAuctionName.click();
		enteredDateRange=dateRange.getAttribute("value");
		currencyFiledValue=currencyValue.getText();
		enteredPrivateAuct= privateActionFieldValue.getText();
	}
	
	public void selectDSPByName(String name) {
		dspDropDown.click();
		int attempt = 0;

		// Check if list contains publisher name, scroll down if not
		do {
			js.executeScript("arguments[0].scrollIntoView(false)", publisherNames.get(publisherNames.size() - 1));
		}
		while (!publisherNames.stream()
				  .map(WebElement::getText)
				  .anyMatch(text -> name.equals(text)) && attempt++ < 20);

		// Get publisher web element by name from the method parameter
		WebElement dSPName = publisherNames.stream()
				.filter(i -> i.getText().equalsIgnoreCase(name))
				.findFirst()
				.orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(String.format("Private Auction by the name %s wasn't found.", name)));
		js.executeScript("arguments[0].scrollIntoView({block: \"center\"})", dSPName);
		wait.until(elementToBeClickable(dSPName));
		dSPName.click();
		enteredDSPValue= dspValue.getText();
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
		enteredValue=value.getAttribute("value");
			
	}
	public void activeTaggle()
	{
		activeToggle.click();
	}
	public boolean dSPbuyerEnableDisable(String enableDisable)
	{
		boolean endis=false;
		if(enableDisable.equalsIgnoreCase("Enable"))
		{
			String enabDisab=driver.findElement(By.xpath("//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']/input")).getAttribute("aria-checked");
			if(enabDisab.equalsIgnoreCase("false"))
			{
			dsPbuyerEnabled.click();
			endis=true;
			}else
			{
				dsPbuyerEnabled.click();
				endis=false;
			}	
			
		}
		return endis;
	}
	
	public boolean dSPbuyerEnabledOrDisabled()
	{
		boolean endis=false;
		
			String enabDisab=driver.findElement(By.xpath("//label[text()='Enabled']/preceding-sibling::div[@class='v-input--selection-controls__input']/input")).getAttribute("aria-checked");
			if(enabDisab.equalsIgnoreCase("false"))
			{
			endis=false;
			}else
			{

				endis=true;
			}	
				
		return endis;
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
		copyDealIDFrmclipboard.click();
	}
	
	public void pasteDealIdToSearch()
	{
		wait.until(ExpectedConditions.visibilityOf(searchDealId));
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
}
