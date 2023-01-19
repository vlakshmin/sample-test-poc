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
    DASHBOARD("/dashboard"),
    DEMAND("/admin/demand"),
    USER("/admin/users"),
    PROTECTIONS("/protections"),
    PUBLISHER("/admin/publishers"),
    OPEN_PRICING("/yield/open-pricing"),
    PRIVATE_AUCTIONS("/sales/private-auctions"),
    CREATE_PROTECTION("/protections/create"),
    CREATE_OPEN_PRICING("/yield/open-pricing/create"),
    CREATE_USER("/admin/users/create");

    private String path;
}
