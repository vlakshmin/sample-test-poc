package widgets.common.singlepanefilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SinglepaneNameImpl implements SinglepaneName {

    ID("Filter by ID"),
    OPTIMIZE("Filter by Optimize"),
    ALWAYS_ON("Filter by Always On"),
    PUBLISHER("Filter by Publisher"),
    DEFAULT_SIZE("Filter by Default Sizes"),
    RELATED_MEDIA("Filter by Related Media"),
    ACTIVE_INACTIVE("Filter by Active/Inactive");

    private final String name;
}
