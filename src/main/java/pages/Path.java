package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    CREATE_PROTECTION("/protections/create"),
    PUBLISHER("/admin/publishers"),
    USER("/admin/publishers"),
    DEALS("/sales/deals"),
    MEDIA("/media"),
    AD_SPOT("/adspots");

    private String path;
}
