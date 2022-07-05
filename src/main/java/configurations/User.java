package configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.codec.CharEncoding;

import java.util.Collections;

@Getter
@AllArgsConstructor
public enum User {

    TEST_USER("jyothi.kotla@rakuten.com", "tUJ*WwQF_7kDHmftjPhV"),
    WRONG_EMAIL_USER("serhii.shydlovsky@rakuten.com", "Password1"),
    WRONG_PASSWORD_USER("os-serhii.shydlovsky@rakuten.com", "Password232"),
    KEYCLOAK_ADMIN("system", "P@ssw0rd");

    private String mail;
    private String password;
}
