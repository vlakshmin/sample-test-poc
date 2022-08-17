package widgets.protections.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProtectionsSidebarElements {

    NAME("'Name' Input", "//label[text()='Name']/../input"),
    NOTES("'Notes' Text Area", "//label[text()='Notes']/../textarea"),
    PUBLISHER_NAME_DROPDOWN( "'Publisher Name' Dropdown", "//label[text()='Publisher Name']/../input"),
    PROTECTION_TYPE_DROPDOWN("'Protection Type' Dropdown", "//label[text()='Protection Type']/../input"),
    MANAGED_BY_SYSTEM_ADMIN_ONLY( "'Managed by system admins only'' Checkbox", "//label[text()='Managed by system admins only']/..//input"),
    SAVE_BUTTON("'Save Protection' Button",  "//button/span[contains(text(),'Save Publisher')]");

    private String alias;
    private String selector;
}
