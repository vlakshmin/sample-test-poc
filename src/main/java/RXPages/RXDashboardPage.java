package RXPages;

import RXBaseClass.RXBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class RXDashboardPage extends RXBaseClass {

    public RXDashboardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[contains(text(), 'Publisher')]")
    public WebElement publisherLabel;

    @FindBy(xpath = "//label[contains(text(), 'Date range')]")
    public WebElement dateLabel;

    @FindBy(xpath = "//label[contains(text(), 'Date range')]/ancestor::div[@class = 'v-input__slot']")
    public WebElement dateRangeContainer;

    @FindBy(css = ".v-select__slot input[type=text]")
    public WebElement publisherInput;

    @FindBy(css = ".v-text-field__slot input[type=text]")
    public WebElement dateInput;

    @FindBy(xpath = "//*[text() = 'Traffic Overview']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement trafficOverviewGraph;

    @FindBy(xpath = "//*[text() = 'Revenue by Day']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement revenueByDayGraph;

    @FindBy(xpath = "//*[text() = 'Top 10 Traffic by Geo']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement topTenGraph;

    @FindBy(xpath = "//*[text() = 'Revenue by Ad Spot']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement revenueByAdGraph;

    @FindBy(xpath = "//*[text() = 'eCPM Trend by Day']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement ecmpGraph;

    @FindBy(xpath = "//*[text() = 'Rates by Day']/ancestor::div[@class='highcharts-container ']" +
            "//*[contains(@class, 'highcharts-yaxis-grid')]/*[@class = 'highcharts-grid-line']")
    public WebElement rateByDayGraph;

    @FindBy(xpath = "//span[@class='v-list-item__mask' and text()='Viki']")
    public WebElement vikiPublisher;

    @FindBy(xpath = "//div[@class='v-list-item__title' and text()='No data available']")
    public WebElement noDataAvailable;

    @FindBy(xpath = "//button[@aria-label = 'Previous month']")
    public WebElement previousMonthBtn;

    @FindBy(xpath = "//button[@aria-label = 'Next month']")
    public WebElement nextMonthBtn;

    @FindBy(xpath = "//div[@class = 'v-date-picker-header__value']")
    public WebElement datePickerHeader;

    public WebElement specificDayOfMonth(String dayOfMonth){
        return driver.findElement(By.xpath("//div[@class = 'v-btn__content' and text() = '" + dayOfMonth + "']/ancestor::button"));
    }

    public void selectSpecificDateRange(String startDate, String endDate){
        specificDayOfMonth(startDate).click();
        specificDayOfMonth(endDate).click();
        driverWait().until(ExpectedConditions.attributeContains(specificDayOfMonth(endDate), "class", "light-blue"));
    }

    public void selectPreviousMonth() {
        driverWait().until(ExpectedConditions.visibilityOf(previousMonthBtn));
        previousMonthBtn.click();
        driverWait().until(ExpectedConditions.visibilityOf(rateByDayGraph));
    }

    public void selectNextMonth() {
        driverWait().until(ExpectedConditions.visibilityOf(nextMonthBtn));
        nextMonthBtn.click();
        driverWait().until(ExpectedConditions.visibilityOf(rateByDayGraph));
    }

    public void selectFifteenDaysRangeInPreviousMonth() {
        openDatePicker();
        selectPreviousMonth();
        selectSpecificDateRange("1", "15");
        dateRangeContainer.click();
    }

    public void openDatePicker() {
        driverWait().until(ExpectedConditions.visibilityOf(dateInput));
        dateRangeContainer.click();
        driverWait().until(ExpectedConditions.visibilityOf(previousMonthBtn));
    }
}
