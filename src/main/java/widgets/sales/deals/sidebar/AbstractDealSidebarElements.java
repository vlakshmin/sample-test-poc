package widgets.sales.deals.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbstractDealSidebarElements {

    ACTIVE_TOGGLE("'Active' Toggle", ""),
    DSP_DROPDOWN("'Dsp' Dropdown","//label[text()='DSP']/../div"),
    NAME( "'Publisher Name' Input", "//label[text()='Name']/../input"),
    SAVE_DEAL_BUTTON("'Save Deal' button", "//span[text()='Save Deal']"),
    CLOSE_SIDEBAR_BUTTON( "'Close Sidebar' button", "//aside//button/span/i"),
    BUYERS_CARD_ITEMS("'Buyers Card' Items","//div[@class='buyers-cards']/div"),
    CURRENCY_DROPDOWN( "'Currency' Dropdown", "//label[text()='Currency']/../div"),
    FLOOR_PRICE_INPUT("'Floor Price' Input",  "//label[text()='Floor Price']/../input"),
    PUBLISHER_DROPDOWN("'Publisher' dropdown", "//label[text()='Publisher Name']/../div"),
    ADD_MORE_SEATS_BUTTON("'Add More Seats' Button", "//span[contains(text(),'Add More Seats')]/.."),
    PRIVATE_AUCTION_DROPDOWN("'Private Auction' Dropdown", "//label[text()='Private Auction']/../div"),

    DROPDOWN_ITEMS("Dropdown Items", "//div[contains(@class,'menuable__content__activ')]//div[@class='v-list-item__title']");

    private String alias;
    private String selector;
}
