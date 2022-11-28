package pages.admin.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersPageElements {

    USERS_PAGE_TITLE("'Users' Page Title", "//h1"),

    USER_ITEMS("User Items", "//tbody/tr"),
    CREATE_USER_POPUP("Create User popup", "//*[@id = 'dialog']"),
    EDIT_USER_BUTTON("'Edit User' Button", "//button//span[text()='Edit Use']"),
    CREATE_USER_BUTTON("'Create User' Button", "//button//span[contains(text(),'Create User')]"),
    CREATE_USER_POPUP_TITLE("'Create User' popup title", "//div[@class='v-toolbar__title']"),
    CREATE_USER_POPUP_CLOSE("'Create User' popup close", "//header/div/button/span[@class='v-btn__content']/i"),
    CREATE_USER_ACTIVE("'Create User' active", "//div/span/form//input[@id='input-1111']"),
    CREATE_USER_USERNAME("'Create User' name", "//div[@class='v-card__text']//input[@id='input-1137']"),
    ACTIVATE_USER_BUTTON("'Activate User' Button", "//button//span[contains(text(),'Activate USer')]"),
    DEACTIVATE_USER_BUTTON("'Deactivate User' Button", "//button//span[contains(text(),'Deactivate USer')]");

    private String alias;
    private String selector;
}