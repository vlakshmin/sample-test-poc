package widgets.admin.demand.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EditDemandSidebarElements {

    EDDPOINT_URI_INPUT("'Publisher Domain' Input", "//label[text()='Endpoint URI']/../input"),
    SYNK_URL("'Publisher Mail' Input", "//label[text()='Sync Url']/../input"),
    BIDDER( "'Publisher Name' Input", "//label[text()='Publisher Name']/../input"),
    CURRENCY("'Publisher Currency' Dropdown", "//label[text()='Currency']/../input"),
    DATA_CENTER_DROPDOWN( "'Ad Ops Person' Dropdown", "//label[text()='Ad Ops Person']/../input"),
    SAVE_BUTTON("'Save Publisher' Button",  "//button/span[contains(text(),'Save Publisher')]");

    private String alias;
    private String selector;
}
