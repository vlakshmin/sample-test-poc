package widgets.admin.publisher;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EditPublisherSidebarElements {

    DOMAIN("'Publisher Domain' Input", "//label[text()='Domain']/../input"),
    MAIL("'Publisher Mail' Input", "//label[text()='Ad Ops Email']/../input"),
    NAME( "'Publisher Name' Input", "//label[text()='Publisher Name']/../input"),
    CURRENCY("'Publisher Currency' Input", "//label[text()='Currency']/../input"),
    AD_OPS_PERSON( "'Ad Ops Person' Input", "//label[text()='Ad Ops Person']/../input"),
    SAVE_BUTTON("'Save Publisher' Button",  "//button/span[contains(text(),'Save Publisher')]");

    private String alias;
    private String selector;
}
