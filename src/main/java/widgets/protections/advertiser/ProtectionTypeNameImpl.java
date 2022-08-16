package widgets.protections.advertiser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import widgets.common.multipane.MultipaneName;

@Getter
@AllArgsConstructor
public enum ProtectionTypeNameImpl implements MultipaneName {

    ADVERTISER("Advertiser"),
    AD_CATEGORIES("Ad Categories");

    private final String name;
}
