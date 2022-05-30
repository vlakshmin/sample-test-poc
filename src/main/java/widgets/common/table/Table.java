package widgets.common.table;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TableElements.CHECKBOX;

/**
 * Keep Selectors of UI elements in {@link TableElements}
 */
public class Table {

    public SelenideElement getCheckbox(int row) {

        return $x(String.format(CHECKBOX.getSelector(), row)).as(CHECKBOX.getAlias());
    }

    // Todo refactor as getCheckbox. Keep selectors in TableComponentElements enumeration
    /*
    public List<WebElement> getDataRows() {
        return driver.findElements(By.xpath("//tbody/tr"));
    }

    public List<WebElement> getColumns() {
        return driver.findElements(By.xpath("//thead/tr/th"));
    }

    private PaginationComponent paginationComponent = new PaginationComponent();

    public WebElement getTableBody() {
        return driver.findElement(By.xpath("//body//table[1]"));
    }

    public List<String> getColumnsName() {
        List<WebElement> columns = driver.findElements(By.xpath("//thead/tr/th/span"));
        List<String> columnsName = new ArrayList<>();
        for (WebElement element : columns) {
            columnsName.add(element.getText());
        }
        return columnsName;
    }

    public List<WebElement> getCustomCells(String columnName) {
        int columnId = getColumnsName().indexOf(columnName) + 2;
        return driver.findElements(By.xpath("//tbody/tr/td[" + columnId + "]"));
    }

    public List<String> getCustomCellsValues(String columnName) {
        int columnId = getColumnsName().indexOf(columnName) + 2;
        List<WebElement> values = driver.findElements(By.xpath("//tbody/tr/td[" + columnId + "]"));
        List<String> columnsName = new ArrayList<>();
        for (WebElement element : values) {
            columnsName.add(element.getText());
        }
        return columnsName;
    }

    public List<WebElement> getCustomCellsLinks(String columnName) {
        int columnId = getColumnsName().indexOf(columnName) + 2;
        return driver.findElements(By.xpath("//tbody/tr/td[" + columnId + "]/a"));
    }

    public WebElement getColumnHeader(String columnName) {
        return driver.findElement
                (By.xpath(String.format("//div[@class='v-data-table__wrapper']//thead//th/span[text()='%s']/parent::th", columnName)));
    }



    public WebElement getBtnWithCaption(String caption) {
        return driver.findElement(By.xpath(String.format("//span[@class='v-btn__content' and text()='%s']/..", caption)));
    }
*/
}