package RXPages;

import RXBaseClass.RXBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RXDashboardPage extends RXBaseClass {

    public RXDashboardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[contains(text(), 'Publisher')]")
    public WebElement publisherLabel;

    @FindBy(xpath = "//label[contains(text(), 'Date range')]")
    public WebElement dateLabel;

    @FindBy(css = ".v-select__slot input[type=text]")
    public WebElement publisherInput;

    @FindBy(css = ".v-text-field__slot input[type=text]")
    public WebElement dateInput;

    @FindBy(xpath = "//*[text() = 'Traffic Overview']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement trafficOverviewGraph;

    @FindBy(xpath = "//*[text() = 'Revenue by Day']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement revenueByDayGraph;

    @FindBy(xpath = "//*[text() = 'Top 10 Traffic by Geo']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement topTenGraph;

    @FindBy(xpath = "//*[text() = 'Revenue by Ad Format']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement revenueByAdGraph;

    @FindBy(xpath = "//*[text() = 'eCPM Trend by Day']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement ecmpGraph;

    @FindBy(xpath = "//*[text() = 'Rates by Day']/ancestor::div[@class='highcharts-container ']//*[contains(@class, 'highcharts-yaxis-grid')]")
    public WebElement rateByDayGraph;

    @FindBy(xpath = "//span[@class='v-list-item__mask' and text()='Viki']")
    public WebElement vikiPublisher;
}
