package widgets.admin.users.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AbstractUserSidebarElements {

    EMAIL_INPUT("'EMail' Input", "//label[text()='Email']/../input"),
    PASSWORD_INPUT("'Password' Input", "//label[text()='Password']/../input"),
    USERNAME_INPUT("'Username' Input", "//label[text()='Username']/../input"),
    USER_SIDEBAR("Create User popup", "//*[@id = 'dialog']"),
    USER_SIDEBAR_TITLE("'Create User' popup title", "//div[@class='v-toolbar__title']"),
    USER_SIDEBAR_CLOSE("'Create User' popup close", "//header/div/button/span[@class='v-btn__content']/i"),
    SAVE_BUTTON("'Save User' Button",  "//button/span[contains(text(),'Save User')]"),
    ADMIN_RADIOBUTTON( "'Admin' Radio Button", "//label/span[contains(text(),'Admin')]/../..//input"),
    CROSS_PUBLISHER_RADIOBUTTON("'Cross-publisher' Radio Button", "//label/span[contains(text(),'Cross-Publisher')]/../..//input"),
    SINGLE_PUBLISHER_RADIOBUTTON( "'Single publisher' Radio Button", "//label/span[contains(text(),'Single-Publisher')]/../..//input");

    private String alias;
    private String selector;
}
