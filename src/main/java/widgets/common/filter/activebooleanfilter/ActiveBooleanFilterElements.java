
package widgets.common.filter.activebooleanfilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActiveBooleanFilterElements {

    BACK_BUTTON("'Back' button in 'Active/Inactive' Filter","//div[@role='menu']//i[contains(@class,'back')]"),
    CANCEL_BUTTON("'Cancel' button in in 'Active/Inactive' Filter","//div[@role='menu']//span[text()='Cancel']/.."),
    SUBMIT_BUTTON("'Cancel' button in in 'Active/Inactive' Filter","//div[@role='menu']//span[text()='Submit']/.."),
    ACTIVE_RADIO_BUTTON("'Active' button in in 'Active/Inactive' Filter","//div[@role='menu']//label[text()='Active']/..//input"),
    FILTER_HEADER("'Filter Header Label in 'Active/Inactive' Filter", "//div[@role='menu']//div[contains(@class,'filter-title')]"),
    INACTIVE_RADIO_BUTTON("'Active' button in in 'Active/Inactive' Filter","//div[@role='menu']//label[text()='Inactive']/..//input");

    private String alias;
    private String selector;
}
