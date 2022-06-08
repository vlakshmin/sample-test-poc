package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Statuses {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    BOTH("Both"),
    ONBOARDING("Onboarding");

    private String status;

}
