package widgets.common.table;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.common.table.TablePaginationElements.*;

/**
 * Keep Selectors of UI elements in {@link TablePaginationElements}
 */
public class TablePagination {

    public SelenideElement getPageMenu() {

        return $x(PAGE_MENU.getSelector()).as(PAGE_MENU.getAlias());
    }

   public SelenideElement getPaginationPanel() {

        return $x(PAGINATION_PANEL.getSelector()).as(PAGINATION_PANEL.getAlias());
    }



    public SelenideElement getRowNumbersList() {

        return $x(ROW_NUMBERS_LIST.getSelector()).as(ROW_NUMBERS_LIST.getAlias());
    }

//    public void selectRowNumbers(String rowNumbers) {
//        driver.findElement(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active']"
//                + "//div[@class='v-list-item__title' and text()='" + rowNumbers + "']")).click();
//    }


    public SelenideElement getNext() {

        return $x(NEXT.getSelector()).as(NEXT.getAlias());
    }

    public SelenideElement getPrevious() {

        return $x(PREVIOUS.getSelector()).as(PREVIOUS.getAlias());
     }


}