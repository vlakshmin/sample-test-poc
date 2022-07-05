package widgets.common.table;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;

import static com.codeborne.selenide.Selenide.$$x;

@Getter
public class TableHeader {
    private ElementsCollection allHeaders = $$x("//th").as("Table all Headers");
    private ElementsCollection allSortableHeaders = $$x("//th[contains(@class,'sortable')]").as("Table Sortable Headers");

    public SelenideElement getHeaderCellByName(ColumnNames columnName) {
        switch (columnName) {
            case ID:
                return allSortableHeaders.get(0);
            case NAME:
                return allSortableHeaders.get(1);
            case PUBLISHER:
                return allSortableHeaders.get(2);
            case ACTIVE_INACTIVE:
                return allSortableHeaders.get(3);
            case FLOOR_PRICE:
                return allSortableHeaders.get(4);

            default:
                throw new InvalidArgumentException("Not Supported Column Name");
        }
    }

    public int getHeaderIndexByName(ColumnNames columnName) {
        for (int i = 1; i < allHeaders.size(); i++) {
            if (allHeaders.get(i).find(By.tagName("span")).getText().equalsIgnoreCase(columnName.getName())) {
                return i;
            }
        }
        throw new IllegalStateException("Column name was not found: " + columnName.getName());
    }

    public SortOrder getCurrentSortingFrom(ColumnNames columnName) {
        return SortOrder.parseValue(getHeaderCellByName(columnName).getAttribute("aria-sort"));
    }

    public SortOrder changeSortOrder(ColumnNames columnName, SortOrder sortOrder) {
        int counter = 5;
        while (getCurrentSortingFrom(columnName) != sortOrder) {
            if (counter <= 0) {
                throw new IllegalStateException("failed to set requested sort order");
            }
            counter--;
            getHeaderCellByName(columnName).click();
        }
        return getCurrentSortingFrom(columnName);
    }

    @Getter
    @AllArgsConstructor
    public enum SortOrder {
        NONE("none"),
        ASCENDING("ascending"),
        DESCENDING("descending");
        private String sortingOrder;

        public static SortOrder parseValue(String value) {
            if (NONE.getSortingOrder().equalsIgnoreCase(value)) {
                return NONE;
            } else if (ASCENDING.getSortingOrder().equalsIgnoreCase(value)) {
                return ASCENDING;
            } else if (DESCENDING.getSortingOrder().equalsIgnoreCase(value)) {
                return DESCENDING;
            } else {
                throw new InvalidArgumentException("not supported value: " + value);
            }
        }
    }
}

