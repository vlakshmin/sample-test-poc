
package widgets.common.table.filter.rolefilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleFilterElements {

    ADMIN_ROLE_CHECkBOX("'Admin' role checkbox in 'Role' Filter","//div[@class='v-item-group theme--light v-list-item-group']/div[1]"),
    CROSS_PUBLISHER_ROLE_CHECkBOX("'Cross-Publisher' role checkbox in 'Role' Filter","//div[@class='v-item-group theme--light v-list-item-group']/div[2]"),
    SINGLE_PUBLISHER_ROLE_CHECkBOX("'Single Publisher' role checkbox in 'Role' Filter","//div[@class='v-item-group theme--light v-list-item-group']/div[3]");

    private String alias;
    private String selector;
}
