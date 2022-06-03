package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    PUBLISHER("/admin/publishers"),
    USER("/admin/publishers"),
    DEALS("/sales/deals");

    private String path;
}
