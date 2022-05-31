package pages.admin.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersPageElements {

    USERS_PAGE_TITLE( "'Users' Page Title", "//h1");

    private String alias;
    private String selector;
}
