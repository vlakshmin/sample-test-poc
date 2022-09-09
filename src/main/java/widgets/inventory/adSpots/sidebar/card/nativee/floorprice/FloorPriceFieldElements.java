
package widgets.inventory.adSpots.sidebar.card.nativee.floorprice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FloorPriceFieldElements {

    //ToDo Add Selectors
    FLOOR_PRICE_UP_BUTTON("'Floor Price Up' button","//todo add Selector"),
    FLOOR_PRICE_DOWN_BUTTON("'Floor Price Down' button","//todo add Selector"),
    FLOOR_PRICE_PREFIX("''Floor Price' Field Currency",
            "//div[text()='Native']/../..//label[text()='Floor Price']/../../div/div"),
    FLOOR_PRICE_INPUT("'Floor Price' Input Field",
            "//div[text()='Native']/../..//label[text()='Floor Price']/../../div/input");

    private String alias;
    private String selector;
}
