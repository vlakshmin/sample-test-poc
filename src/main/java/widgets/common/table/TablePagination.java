package widgets.common.table;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$$x;
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

    public ElementsCollection getRowNumbersList() {

        return $$x(ROW_NUMBERS_LIST.getSelector()).as(ROW_NUMBERS_LIST.getAlias());
    }

    public SelenideElement getNext() {

        return $x(NEXT.getSelector()).as(NEXT.getAlias());
    }

    public SelenideElement getPrevious() {

        return $x(PREVIOUS.getSelector()).as(PREVIOUS.getAlias());
     }


}