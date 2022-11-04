package api.dto.rx.admin.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserRole {
    SINGLE_PUBLISHER(0,"Single"),
    CROSS_PUBLISHER(1, "Cross"),
    ADMIN(4, "Admin");

    private Integer role;
    private String definition;
}
