package pages.admin.users;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;
import widgets.common.table.Table;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static pages.admin.users.UsersPageElements.*;
import static pages.admin.users.UsersPageElements.DEACTIVATE_USER_BUTTON;

/**
 * Keep Selectors of UI elements in {@link UsersPageElements}
 */
@Getter
public class UsersPage extends BasePage {

    private SelenideElement pageTitle = $x(USERS_PAGE_TITLE.getSelector()).as(USERS_PAGE_TITLE.getAlias());
    private ElementsCollection userItems = $$x(USER_ITEMS.getSelector()).as(USER_ITEMS.getAlias());
    private SelenideElement createUserPopUp = $x(CREATE_USER_POPUP.getSelector()).as(CREATE_USER_POPUP.getAlias());
    private SelenideElement createUserButton = $x(CREATE_USER_BUTTON.getSelector()).as(CREATE_USER_BUTTON.getAlias());
    private SelenideElement activateUserButton = $x(ACTIVATE_USER_BUTTON.getSelector()).as(ACTIVATE_USER_BUTTON.getAlias());
    private SelenideElement deactivateUserButton = $x(DEACTIVATE_USER_BUTTON.getSelector()).as(DEACTIVATE_USER_BUTTON.getAlias());
    private SelenideElement createUserPopUpTitle = $x(CREATE_USER_POPUP_TITLE.getSelector()).as(CREATE_USER_POPUP_TITLE.getAlias());
    private SelenideElement createUserPopUpClose = $x(CREATE_USER_POPUP_CLOSE.getSelector()).as(CREATE_USER_POPUP_CLOSE.getAlias());
    private SelenideElement createUserActive = $x(CREATE_USER_ACTIVE.getSelector()).as(CREATE_USER_ACTIVE.getAlias());
    private SelenideElement createUserName = $x(CREATE_USER_USERNAME.getSelector()).as(CREATE_USER_USERNAME.getAlias());
    private Table usersTable = new Table();
}
