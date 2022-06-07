package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    PUBLISHER("/admin/publishers"),
    MEDIA("/media"),
    AD_SPOT("/adspots");

    private String path;
}
