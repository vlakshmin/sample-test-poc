package RXPages;

import RXUtitities.RXUtile;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RXAdspotsPage extends RXBasePage {
	// Utility object
	RXUtile rxUTL;
	PublisherListPage pubPage;
	public String adspotsHeaderStr = "Ad Spots";

	// Seats page heading
	@FindBy(xpath = "//h1[text()='Ad Spots']")
	WebElement adspotsPageHeader;
	// Xpath of rows to get the total number of row and column displayed in the
	// page.
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr"))
	public List<WebElement> adspotsTableRows;
	@FindAll(@FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr[1]/td"))
	public List<WebElement> adspotsTableColumns;
	@FindBy(xpath = "//*[@class='v-text-field__slot']/input")
	WebElement adSpotsSearchField;

	// overview buttons
	@FindBy(xpath = "//button/span[text()='Create AdSpot']")
	public WebElement overviewCreateButton;
	@FindBy(xpath = "//button/span[text()='Edit AdSpot']")
	public WebElement overviewEditButton;
	@FindBy(xpath = "//button/span[text()='Deactivate AdSpot']")
	public WebElement overviewDisableButton;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']/button[2]/span")
	public WebElement overviewSecondButton;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']/button[3]/span")
	public WebElement overviewThirdButton;
	@FindBy(xpath = "//div[@class='portal vue-portal-target']")
	public WebElement overviewButtonsBlock;
	@FindBy(xpath = "//button/span[text()='Activate AdSpots']")
	public WebElement overviewMultipleEnableButton;
	@FindBy(xpath = "//button/span[text()='Deactivate AdSpots']")
	public WebElement overviewMultipleDisableButton;
	@FindBy(xpath = "//*[@class='v-input__icon v-input__icon--clear']/button")
	public WebElement clearSearchButton;
	// Create/Edit Page labels
	@FindBy(xpath = "//div[@class='hidden-xs-only vue-portal-target']")
	public WebElement createPageHeader;
	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']")
	public WebElement publisherNameDropDown;
	@FindBy(xpath = "//label[text()='Publisher Name']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement publisherNameField;
	@FindBy(xpath = "//label[text()='Ad Spot Name']/following-sibling::input")
	public WebElement adSpotNameField;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__title']/div")
	public WebElement adSpotNameHeader;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-toolbar__content']/button")
	public WebElement adSpotCloseSideDialog;
	
	@FindBy(xpath = "//label[text()='Related Media']/following-sibling::div[@class='v-select__selections']")
	public WebElement relatedMediaDropDown;
	@FindBy(xpath = "//label[text()='Related Media']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement relatedMediaField;
	@FindBy(xpath = "//label[text()='Categories']/following-sibling::div[@class='v-select__selections']")
	public WebElement categoriesDropDown;
	@FindBy(xpath = "//label[text()='Categories']/following-sibling::div[@class='v-select__selections']//span[@class='v-chip__content']")
	public WebElement categoriesField;

	@FindBy(xpath = "//label[text()='Filter']/following-sibling::div[@class='v-select__selections']")
	public WebElement filterDropDown;
	@FindBy(xpath = "//label[text()='Filter']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement filterField;
	@FindBy(xpath = "//label[text()='Position']/following-sibling::div[@class='v-select__selections']")
	public WebElement positionDropDown;
	@FindBy(xpath = "//label[text()='Position']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement positionField;
	@FindBy(xpath = "//label[text()='Default Ad Sizes']/following-sibling::div[@class='v-select__selections']")
	public WebElement defaultSizesDropDown;
	@FindAll(@FindBy(xpath = "//label[text()='Default Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
	public List<WebElement> defaultSizesField;
	@FindBy(xpath = "//label[text()='Default Floor Price']/following-sibling::input")
	public WebElement defaultPriceField;
	@FindBy(xpath = "//label[text()='Default Floor Price']/following-sibling::div[1]")
	public WebElement defaultPriceCurrency;
	@FindBy(xpath = "//aside[@class='dialog']//div[@class='v-banner__content']//div[@class='v-banner__text']")
	public WebElement publisherChangeBannerTxt;
	@FindAll(@FindBy(xpath = "//form/div[2]//div[@class='v-messages__wrapper']/div"))
	public List<WebElement> generalSizePriceMsg;
	@FindAll(@FindBy(xpath = "//form/div[3]//div[@class='v-messages__wrapper']/div"))
	public List<WebElement> bannerSizePriceMsg;
	@FindAll(@FindBy(xpath = "//form/div[4]//div[@class='v-messages__wrapper']/div"))
	public List<WebElement> nativeSizePriceMsg;
	@FindAll(@FindBy(xpath = "//form/div[5]//div[@class='v-messages__wrapper']/div"))
	public List<WebElement> inBannerVideoSizePriceMsg;
	@FindBy(xpath = "//form/div[3]/div/button//i")
	public WebElement bannerExpandButton;
	@FindBy(xpath = "//form/div[4]/div/button//i")
	public WebElement nativeExpandButton;
	@FindBy(xpath = "//form/div[5]/div/button//i")
	public WebElement inBannerVideoExpandButton;
	@FindBy(xpath = "//form/div[2]/div//div[@class='v-input--selection-controls__input']")
	public WebElement adspotEnableButton;
	@FindBy(xpath = "//form/div[3]/div//div[@class='v-input--selection-controls__input']")
	public WebElement bannerEnableButton;
	@FindBy(xpath = "//form/div[4]/div//div[@class='v-input--selection-controls__input']")
	public WebElement nativeEnableButton;
	@FindBy(xpath = "//form/div[5]/div//div[@class='v-input--selection-controls__input']")
	public WebElement inBannerVideoEnableButton;

	@FindBy(xpath = "//form/div[3]//label[text()='Floor Price']/following-sibling::input")
	public WebElement bannerPriceField;
	@FindBy(xpath = "//form/div[3]//label[text()='Floor Price']/following-sibling::div[1]")
	public WebElement bannerPriceCurrency;
	@FindBy(xpath = "//form/div[3]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']")
	public WebElement bannerSizesDropDown;
	@FindAll(@FindBy(xpath = "//form/div[3]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
	public List<WebElement> bannerSizesField;
	@FindBy(xpath = "//form/div[4]//label[text()='Floor Price']/following-sibling::input")
	public WebElement nativePriceField;
	@FindBy(xpath = "//form/div[4]//label[text()='Floor Price']/following-sibling::div[1]")
	public WebElement nativePriceCurrency;
	@FindBy(xpath = "//form/div[5]//label[text()='Floor Price']/following-sibling::input")
	public WebElement inBannerVideoPriceField;
	@FindBy(xpath = "//form/div[5]//label[text()='Floor Price']/following-sibling::div[1]")
	public WebElement inBannerVideoPriceCurrency;
	@FindBy(xpath = "//form/div[5]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']")
	public WebElement inBannerVideoSizesDropDown;
	@FindAll(@FindBy(xpath = "//form/div[5]//label[text()='Ad Sizes']/following-sibling::div[@class='v-select__selections']/div"))
	public List<WebElement> inBannerVideoSizesField;
	@FindBy(xpath = "//form/div[5]//label[text()='Minimum Video Duration']/following-sibling::div[@class='v-select__selections']")
	public WebElement minVideoDurDropDown;
	@FindBy(xpath = "//form/div[5]//label[text()='Maximum Video Duration']/following-sibling::div[@class='v-select__selections']")
	public WebElement maxVideoDurDropDown;
	@FindBy(xpath = "//form/div[5]//label[text()='Minimum Video Duration']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement minVideoDurField;
	@FindBy(xpath = "//form/div[5]//label[text()='Maximum Video Duration']/following-sibling::div[@class='v-select__selections']/div")
	public WebElement maxVideoDurField;
	@FindBy(xpath = "//form/div[5]//label[text()='Video Playback Methods']/following-sibling::div[@class='v-select__selections']")
	public WebElement playbackMethodsDropDown;
	@FindAll(@FindBy(xpath = "//form/div[5]//label[text()='Playback Methods']/following-sibling::div[@class='v-select__selections']/div"))
	public List<WebElement> playbackMethodsField;
	@FindBy(xpath = "//form/div[5]//label[text()='Video Placement Type']/following-sibling::div[@class='v-select__selections']")
	public WebElement videoPlacementDropDown;
	@FindAll(@FindBy(xpath = "//form/div[5]//label[text()='Video Placement Type']/following-sibling::div[@class='v-select__selections']/div"))
	public WebElement videoPlacementField;

	// Action object
	Actions act = new Actions(driver);

	// Array for test data
	static ArrayList<String> testData = new ArrayList<String>();

	// Initialize page factory
	public RXAdspotsPage() {
		PageFactory.initElements(driver, this);
		rxUTL = new RXUtile();
		pubPage = new PublisherListPage();
	}

	// Get the text of the media page
	public String getPageHeading() {
		WebElement elem = driverWait().until(ExpectedConditions.visibilityOf(adspotsPageHeader));
		System.out.println(elem.getText());
		return elem.getText();
	}

	public void searchAdspots(String text) throws InterruptedException {
		WebElement elem = driverWait().until(ExpectedConditions.visibilityOf(adSpotsSearchField));
		if(clearSearchButton.isEnabled()) {
			clearSearchButton.click();
		}
		elem.sendKeys(text);
	}

	public void clickOverViewEditButton() {
		driverWait().until(ExpectedConditions.visibilityOf(overviewEditButton));
		if (overviewEditButton.isDisplayed()) {
			overviewEditButton.click();
		}
	}

	public void clickOverViewEnablebutton() {
		driverWait().until(ExpectedConditions.visibilityOf(overviewSecondButton));
		String enableText = overviewThirdButton.getText().replaceAll("\\s", "");
		if (enableText.equals("ACTIVATEADSPOT")) {
			overviewThirdButton.click();
		}
	}

	public void clickOverViewDisablebutton() {
		driverWait().until(ExpectedConditions.visibilityOf(overviewDisableButton));
		String enableText = overviewSecondButton.getText().replaceAll("\\s", "");
		if (enableText.equals("DEACTIVATEADSPOT")) {
			overviewSecondButton.click();
		}
	}
	
	
	public void clickOverViewMultipleEnablebuttons() {
		driverWait().until(ExpectedConditions.visibilityOf(overviewMultipleEnableButton));
		if (overviewMultipleEnableButton.isDisplayed()) {
			overviewMultipleEnableButton.click();
		}
	}

	public void clickOverViewMultipleDisablebuttons() {
		driverWait().until(ExpectedConditions.visibilityOf(overviewMultipleDisableButton));
		if (overviewMultipleDisableButton.isDisplayed()) {
			overviewMultipleDisableButton.click();
		}
	}

	public void waitForCreatePageHeaderToBeVisible() {
		driverWait().until(ExpectedConditions.visibilityOf(createPageHeader));
	}

}
