package pages.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginPageElements {

    LOGIN_INPUT( "'Login' Input", "//*[@id = 'input-24']"),
    PASSWORD_INPUT( "'Password' Input", "//input[@type='password']"),
    LOGIN_BUTTON("'Login' Button", "//button[@type='submit']"),
    LOGIN_RESULT_BUTTON("'Failed' Button", "//div[@class='v-card__actions']/button[@type='button']/span"),
    SIGNUP_BUTTON("'Sign Up' Button", "//div/button[2]"),
    FORGOT_PASSWORD("'Forgot Password' Link", "//a[@href='/reset-password']/button");

    private String alias;
    private String selector;
}
