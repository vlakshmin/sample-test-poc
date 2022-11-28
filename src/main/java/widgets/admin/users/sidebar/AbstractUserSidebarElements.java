package widgets.admin.users.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbstractUserSidebarElements {

    ACTIVE_TOGGLE("'Active' Toggle", "//label[text()='Active']/..//input[@role='switch']/../input"),
    EMAIL_INPUT("'EMail' Input", "//label[text()='Email']/../input"),
    PASSWORD_INPUT("'Password' Input", "//label[text()='Password']/../input"),
    USERNAME_INPUT("'Username' Input", "//label[text()='Username']/../input"),
    SAVE_BUTTON("'Save User' Button",  "//button/span[contains(text(),'Save User')]"),
    ADMIN_RADIOBUTTON( "'Admin' Radio Button", "//label/span[contains(text(),'Admin')]/../..//input"),
    CROSS_PUBLISHER_RADIOBUTTON("'Cross-publisher' Radio Button", "//label/span[contains(text(),'Cross-Publisher')]/../..//input"),
    SINGLE_PUBLISHER_RADIOBUTTON( "'Single publisher' Radio Button", "//label/span[contains(text(),'Single-Publisher')]/../..//input");
    private String alias;
    private String selector;
}
