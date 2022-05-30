package widgets.common.multipane;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.multipane.MultipaneElements.INCLUDE_ALL_BUTTON;

/**
 * Keep Selectors of UI elements in {@link MultipaneElements}
 */
public class Multipane {


    public SelenideElement getIncludeAllButton(String panelName) {

        return $x(String.format(INCLUDE_ALL_BUTTON.getSelector(), panelName)).as(INCLUDE_ALL_BUTTON.getAlias());
    }
    // Todo refactor as getIncludeAllButton. Keep selectors in MultipaneElements enumeration
    /*
    public WebElement getClearAllButton(String panelName) {
        return driver.findElement
                (By.xpath(String.format("//button/h3[text()='%s']/../../div/div/span//div[@class='multipane']/div/div[2]//span[text()='Clear All']", panelName)));
    }

    public List<WebElement> getPanels() {
        return driver.findElements
                (By.xpath("//div[@class='expansionPanels v-item-group theme--light v-expansion-panels']/div"));
    }

    public WebElement getIncludedPanel(String panelName) {
        return driver.findElement
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='included-table']", panelName)));
    }

    public List<WebElement> getIncludedItems(String panelName) {

        Assert.assertNotEquals(getIncludedPanel(panelName).getAttribute("innerHTML"), " <!----> <!---->", "Included Items list is empty!");

        return driver.findElements
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='included-table']/tr/td/div", panelName)));
    }

    public Boolean isIncludedListEmpty(String panelName) {

        return getIncludedPanel(panelName).getAttribute("innerHTML").equals(" <!----> <!---->");
    }

    public List<WebElement> getSelectedItems(String panelName) {
        WebElement includedItems = driver.findElement(By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='select-table']/tbody", panelName)));
        Assert.assertNotEquals(includedItems.getAttribute("innerHTML"), "", "Selected Items list is empty!");
        return driver.findElements
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='select-table']/tbody/tr/td[2]/div", panelName)));
    }

    public WebElement getItem(String panelName, String item) {
        return driver.findElement
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='select-table']/tbody/tr/td[2]/div[contains(text(),'%s')]",
                        panelName, item)));

    }

    public WebElement getIncludedItem(String panelName, String item) {

        return driver.findElement
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='included-table']/tr/td/div[contains(text(),'%s')]",
                        panelName, item)));

    }

    public WebElement getRemoveIncludedItem(String panelName, String item) {

        return driver.findElement
                (By.xpath(String.format("//button/h3[text()='%s']/../..//*[@class='included-table']/tr/td/div[contains(text(),'%s')]/../..//button",
                        panelName, item)));
    }
*/
}