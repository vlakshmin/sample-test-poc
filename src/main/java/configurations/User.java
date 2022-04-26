package configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum User {

    TEST_USER("os-mahdenko.serhii@rakuten.com", "Password1"),
    WRONG_EMAIL_USER("mahdenko.serhii@rakuten.com", "Password1"),
    WRONG_PASSWORD_USER("os-mahdenko.serhii@rakuten.com", "Password232"),
    KEYCLOAK_ADMIN("system", "P@ssw0rd");

    private String mail;
    private String password;
}