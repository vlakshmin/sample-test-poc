package RXPages;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RXDealsPage extends RXBaseClass {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	public String dealHeaderStr = "Deals";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	// Seats page heading
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
	private WebElement publisherNameInput;
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
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'Value')]]")
	private WebElement valueInput;
	@FindBy(xpath="//div[./label[contains(@class,'v-label') and contains(.,'DSP')]]")
	private WebElement dspList;
	@FindAll({@FindBy(xpath="//div[contains(@class,'v-messages__message')]")})
	public List<WebElement> requiredFieldsMessages;

	// Action object
	Actions act = new Actions(driver);

	// Array for test data
	static ArrayList<String> testData = new ArrayList<String>();

	// Explicit Wait
	WebDriverWait wait = new WebDriverWait(driver, 30);

	// Initialize page factory
	public RXDealsPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
		pubPage = new PublisherListPage();

	}

	// Get the text of the media page
	public String getPageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(dealsPageHeader));
		System.out.println(elem.getText());
		return elem.getText();

	}

	private String getFieldXpath(String fieldName) {
		return String.format("//div[./label[contains(@class,'v-label') and contains(.,'')]];", fieldName);
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

	public boolean isCreateDealMenuOpened() {
		return createDealMenuHeader.isDisplayed();
	}

	public void expandPublisherNameList() {
		publisherNameInput.click();
		System.out.println("Publisher name attribute is: " + attributeContains(publisherNameInput, "class", "is-menu-active"));
		wait.until(attributeContains(publisherNameInput, "class", "is-menu-active"));
	}

	public void selectPublisherByName(String name) {
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
		wait.until(elementToBeClickable(publisherName));
		publisherName.click();
	}

	public String getCurrencyText () {
		return currencyOption.getText();
	}

	public boolean isCurrencyNameValid (String currency) {
		clickSaveDealButton();
		verifyRequiredFields();
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

		// True if all required fields have an error message, false if not
		return Arrays.stream(elements)
				.allMatch(i ->
					i.findElement(errorMessageXpath)
						.getText().replaceAll("\\.", "")
						.equalsIgnoreCase(("the " + i.getText() + " field is required")
								.replaceAll("(?i).{9}range.{7}(?=is)","Start date ")));
	}

	public boolean verifyRequiredFields() {
		return verifyErrorMessageForElements(privateAuctionList, nameInput,
				selectDateButton, valueInput,dspList);
	}

}
