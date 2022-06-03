package widgets.sales.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbstractDealSidebarElements {

    ACTIVE_TOGGLE("'Active' Toggle", ""),
    PUBLISHER_DROPDOWN("'Publisher' dropdown", "//label[text()='Publisher Name']/../div"),
    NAME( "'Publisher Name' Input", "//label[text()='Name']/../input"),
    PRIVATE_AUCTION_DROPDOWN("'Private Auction' Dropdown", "//label[text()='Private Auction']/../div"),
    CURRENCY_DROPDOWN( "'Currency' Dropdown", "//label[text()='Currency']/../div"),
    FLOOR_PRICE_INPUT("'Floor Price' Input",  "//label[text()='Floor Price']/../input"),
    DSP_DROPDOWN("'Dsp' Dropdown","//label[text()='DSP']/../div"),
    ADD_MORE_SEATS_BUTTON("'Add More Seats' Button", "//span[contains(text(),'Add More Seats')]/.."),

    DROPDOWN_ITEMS("Dropdown Items", "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']");

    private String alias;
    private String selector;
}
