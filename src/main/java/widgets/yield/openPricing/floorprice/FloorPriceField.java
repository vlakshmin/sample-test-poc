package widgets.yield.openPricing.floorprice;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.yield.openPricing.floorprice.FloorPriceFieldElements.*;


/**
 * Keep Selectors of UI elements in {@link FloorPriceFieldElements}
 */
@Getter
public class FloorPriceField {

    private SelenideElement floorPriceInput = $x(DATE_RANGE_INPUT.getSelector()).as(DATE_RANGE_INPUT.getAlias());
    private SelenideElement nextYearButton = $x(NEXT_YEAR_BUTTON.getSelector()).as(NEXT_YEAR_BUTTON.getAlias());
    private SelenideElement nextMonthButton = $x(NEXT_MONTH_BUTTON.getSelector()).as(NEXT_MONTH_BUTTON.getAlias());

}
