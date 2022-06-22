package widgets.inventory.adSpots.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSpotSidebarElements {

    AD_SPOT_NAME("'Ad Spot Name' Input", "//label[text()='Ad Spot Name']/../input"),
    RELATED_MEDIA("'Related Media' Input", "//label[text()='Related Media']/../div"),
    PUBLISHER_NAME( "'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    CATEGORIES("'Categories' Input", "//label[text()='Categories']/../input"),
    FILTER( "'Filter' Input", "//label[text()='Filter']/../input"),
    POSITION( "'Position' Input", "//label[text()='Position']/../input"),
    DEFAULT_AD_SIZE( "'Default Ad Size' Input", "//label[text()='Default Ad Size']/../input"),
    DEFAULT_FLOOR_PRICE( "'Default Floor Price' Input", "//label[text()='Default Floor Price']/../input"),
    SAVE_BUTTON("'Save Ad Spot' Button",  "//button/span[contains(text(),'Save Ad Spot')]");

    private String alias;
    private String selector;
}
