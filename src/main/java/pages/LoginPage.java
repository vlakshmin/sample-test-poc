package pages;

import lombok.Getter;

@Getter
public class LoginPage {

    private String loginInput = "//*[@id = 'input-24']";
    private String passwordInput = "//*[@id='input-28']";
    private String logInButton;

}
