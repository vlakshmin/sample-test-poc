package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static pages.LoginPageElements.LOGIN_INPUT;
import static pages.LoginPageElements.PASSWORD_INPUT;

/**
 * Keep Selectors of UI elements in {@link LoginPageElements}
 */
@Getter
public class LoginPage {

    private SelenideElement loginInput = $x(LOGIN_INPUT.getSelector()).as(LOGIN_INPUT.getAlias());
    private SelenideElement passwordInput = $x(PASSWORD_INPUT.getSelector()).as(PASSWORD_INPUT.getAlias());
    private SelenideElement logInButton;

}
