package api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyCloakRole {

    SYSTEM_DEFAULT_SUPERVISOR_ROLE("System Default:Supervisor:Role"),
    SYSTEM_DEFAULT_ANALYST_ROLE("System Default:Analyst:Role");

    String role;
}
