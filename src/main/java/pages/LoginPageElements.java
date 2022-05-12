package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginPageElements {

    LOGIN_INPUT( "'Login' Input", "//*[@id = 'input-24']"),
    PASSWORD_INPUT( "'Password' Input", "//input[@type='password']");

    private String alias;
    private String selector;
}
