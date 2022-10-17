package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    MEDIA("/media"),
    AD_SPOT("/adspots"),
    PROFILE("/profile"),
    DEALS("/sales/deals"),
    DASHBIARD("/dashboard"),
    DEMAND("/admin/demand"),
    USER("/admin/publishers"),
    PROTECTIONS("/protections"),
    PUBLISHER("/admin/publishers"),
    OPEN_PRICING("/yield/open-pricing"),
    CREATE_PROTECTION("/protections/create"),
    CREATE_OPEN_PRICING("/yield/open-pricing/create");

    private String path;
}
