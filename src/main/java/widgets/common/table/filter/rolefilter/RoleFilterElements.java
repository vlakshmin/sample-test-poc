
package widgets.common.table.filter.rolefilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleFilterElements {

    ADMIN_ROLE_CHECkBOX("'Admin' role checkbox in 'Role' Filter","//div[contains(text(),'Admin')]/../..//input/../div"),
    CROSS_PUBLISHER_ROLE_CHECkBOX("'Cross-Publisher' role checkbox in 'Role' Filter","//div[contains(text(),'Cross')]/../..//input/../div"),
    SINGLE_PUBLISHER_ROLE_CHECkBOX("'Single Publisher' role checkbox in 'Role' Filter","//div[contains(text(),'Single')]/../..//input/../div");

    private String alias;
    private String selector;
}
