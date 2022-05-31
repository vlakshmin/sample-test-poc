package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    PUBLISHER("/admin/publishers");

    private String path;
}
