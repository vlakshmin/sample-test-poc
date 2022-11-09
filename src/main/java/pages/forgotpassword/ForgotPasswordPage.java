package pages.forgotpassword;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static pages.forgotpassword.ForgotPasswordPageElements.*;

/**
 * Keep Selectors of UI elements in {@link ForgotPasswordPageElements}
 */
@Getter
public class ForgotPasswordPage {

    private SelenideElement forgotpasswordpageTitle = $x(FORGOT_PASSWORD_PAGE_TITLE.getSelector()).as(FORGOT_PASSWORD_PAGE_TITLE.getAlias());
    private SelenideElement forgotpasswordInput = $x(FORGOT_PASSWORD_INPUT.getSelector()).as(FORGOT_PASSWORD_INPUT.getAlias());
    private SelenideElement forgotpasswordSubmitButton = $x(FORGOT_PASSWORD_BUTTON.getSelector()).as(FORGOT_PASSWORD_BUTTON.getAlias());
    private SelenideElement forgotpasswordCancelButton = $x(FORGOT_PASSWORD_CANCEL_BUTTON.getSelector()).as(FORGOT_PASSWORD_CANCEL_BUTTON.getAlias());
}
