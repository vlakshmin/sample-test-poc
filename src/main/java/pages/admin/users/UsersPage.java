package pages.admin.users;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static pages.admin.users.UsersPageElements.USERS_PAGE_TITLE;

/**
 * Keep Selectors of UI elements in {@link UsersPageElements}
 */
@Getter
public class UsersPage extends BasePage {

    private SelenideElement pageTitle = $x(USERS_PAGE_TITLE.getSelector()).as(USERS_PAGE_TITLE.getAlias());
}
