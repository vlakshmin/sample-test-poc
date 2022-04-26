package api.services.keycloak;

import api.entities.KeyCloakRole;
import configurations.User;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.NoSuchElementException;

import static configurations.ConfigurationLoader.getConfig;
import static configurations.User.KEYCLOAK_ADMIN;
import static java.lang.String.format;

@Slf4j
public class KeyCloakService {

    private Keycloak keycloak;
    private String realmId = "thetaray_realm";
    private String clientId = "thetaray_client";

    public KeyCloakService() {
        keycloak = createKeycloak();
    }

    private Keycloak createKeycloak() {
        log.info("Creating new KeyCloak client");

        return KeycloakBuilder.builder()
                .serverUrl(format("%sauth", getConfig().getBaseUrl()))
                .realm("thetaray_realm")
                .clientId("thetaray_client")
                .clientSecret("theta123")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .username(KEYCLOAK_ADMIN.getMail())
                .password(KEYCLOAK_ADMIN.getPassword())
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    }

    public UserRepresentation getUserByUserName(String username) {
        log.info("Getting user '{}' from KeyCloak", username);

        return keycloak.realm(realmId)
                .users()
                .list().stream()
                .filter(usr -> usr.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(
                                format("A User with uername '%s' hasn't been found in KeyCloak", username)));
    }

    public void updateRequiredUserActions(UserRepresentation userRepresentation) {
        log.info("Current required user actions {}", userRepresentation.getRequiredActions().toString());

        userRepresentation.setRequiredActions(Collections.emptyList());

        keycloak.realm(realmId)
                .users()
                .get(userRepresentation.getId())
                .update(userRepresentation);

        log.info("Required User Actions for user '{}' have been updated:{}", userRepresentation.getUsername(),
                userRepresentation.getRequiredActions());
    }

    public void resetPassword(UserRepresentation userRepresentation, String password) {
        log.info("Resetting password for  user '{}' from KeyCloak", userRepresentation.getUsername());

        var credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(password);
        credentials.setTemporary(false);

        keycloak.realm(realmId)
                .users()
                .get(userRepresentation.getId())
                .resetPassword(credentials);

        log.info("The Password for  user '{}' from has been reset", userRepresentation.getUsername());
    }

    public String getGroupIdByGroupName(String groupName) {
        log.info("Getting groupId by groupName '{}' from KeyCloak", groupName);

        return keycloak.realm(realmId)
                .groups()
                .groups().stream()
                .filter(gr -> gr.getName().equalsIgnoreCase(groupName))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(
                                format("A Group with name '%s' hasn't been found in KeyCloak", groupName)))
                .getId();
    }

    public String getUserIdIdByUserName(User user) {
        log.info("Getting userId by userName '{}' from KeyCloak", user.getMail());

        return keycloak.realm(realmId)
                .users()
                .list().stream()
                .filter(usr -> usr.getUsername().equalsIgnoreCase(user.getMail()))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(
                                format("A User with name '%s' hasn't been found in KeyCloak", user.getMail())))
                .getId();
    }

    public void addUserToKCGroup(User user, String groupName) {
        log.info("Adding user '{}' to group '{}'", user.getMail(), groupName);

        String groupId = getGroupIdByGroupName(groupName);
        String userId = getUserIdIdByUserName(user);

        keycloak.realm(realmId)
                .users()
                .get(userId)
                .joinGroup(groupId);

        log.info("User '{}' has been added to group '{}'", user.getMail(), groupName);
    }

    public RoleRepresentation getRoleByName(String roleName){
        log.info("Getting  role by name '{}'", roleName);

        return keycloak.realm(realmId)
                .roles()
                .list().stream()
                .filter(roleRep -> roleRep.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(
                                format("The Role with name '%s' hasn't been found in KeyCloak", roleName)));
    }

    public void assignRoleToUser(KeyCloakRole role, User user) {
        log.info("Assigning role '{}' to user '{}'", role.getRole(), user.getMail());

        String userId = getUserIdIdByUserName(user);

        keycloak.realm(realmId)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(Collections.singletonList(getRoleByName(role.getRole())));

        log.info("The Role '{}' has been Assigned to user '{}'", role, user.getMail());
    }

    public void removeRoleFromUser(KeyCloakRole role, User user) {
        log.info("Removing role '{}' from  user '{}'", role.getRole(), user.getMail());

        String userId = getUserIdIdByUserName(user);

        keycloak.realm(realmId)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .remove(Collections.singletonList(getRoleByName(role.getRole())));

        log.info("The Role '{}' has been Removed from user '{}'", role, user.getMail());
    }
}

