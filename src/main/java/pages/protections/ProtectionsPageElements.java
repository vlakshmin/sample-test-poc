package pages.protections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProtectionsPageElements {

    PROTECTIONS_PAGE_TITLE( "'Protections' Page Title", "//h1");

    private String alias;
    private String selector;
}
