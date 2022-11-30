
package widgets.common.table.filter.activebooleanfilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActiveBooleanFilterElements {

    ACTIVE_RADIO_BUTTON("'Active' button in in 'Active/Inactive' Filter","//div[@role='menu']//label[text()='Active']/../div/div"),
    INACTIVE_RADIO_BUTTON("'Active' button in in 'Active/Inactive' Filter","//div[@role='menu']//label[text()='Inactive']/../div/div");

    private String alias;
    private String selector;
}
