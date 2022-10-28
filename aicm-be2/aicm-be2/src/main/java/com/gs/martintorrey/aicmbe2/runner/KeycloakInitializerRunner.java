package com.gs.martintorrey.aicmbe2.runner;

import com.gs.martintorrey.aicmbe2.security.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class KeycloakInitializerRunner implements CommandLineRunner {

    private final Keycloak keycloakAdmin;

    @Override
    public void run(String... args) {
        log.info("Initializing '{}' realm in Keycloak ...", REALM_NAME);

        Optional<RealmRepresentation> representationOptional = keycloakAdmin.realms()
                .findAll()
                .stream()
                .filter(r -> r.getRealm().equals(REALM_NAME))
                .findAny();
        if (representationOptional.isPresent()) {
            log.info("Removing already pre-configured '{}' realm", REALM_NAME);
            keycloakAdmin.realm(REALM_NAME).remove();
        }

        // Realm
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(REALM_NAME);
        realmRepresentation.setEnabled(true);
        realmRepresentation.setRegistrationAllowed(true);

        // Client
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(CLIENT_ID);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setPublicClient(true);
        clientRepresentation.setRedirectUris(Collections.singletonList(REDIRECT_URL));
        clientRepresentation.setDefaultRoles(new String[]{WebSecurityConfig.USER});
        realmRepresentation.setClients(Collections.singletonList(clientRepresentation));

        // Users
        List<UserRepresentation> userRepresentations = APP_USERS.stream()
                .map(userPass -> {
                    // User Credentials
                    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                    credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
                    credentialRepresentation.setValue(userPass.password());

                    // User
                    UserRepresentation userRepresentation = new UserRepresentation();
                    userRepresentation.setUsername(userPass.username());
                    userRepresentation.setEnabled(true);
                    userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
                    userRepresentation.setClientRoles(getClientRoles(userPass));

                    return userRepresentation;
                })
                .collect(Collectors.toList());
        realmRepresentation.setUsers(userRepresentations);

        // Create Realm
        keycloakAdmin.realms().create(realmRepresentation);

        // Testing
        UserPass admin = APP_USERS.get(0);
        log.info("Testing getting token for '{}' ...", admin.username());

        Keycloak keycloakMovieApp = KeycloakBuilder.builder().serverUrl(KEYCLOAK_SERVER_URL)
                .realm(REALM_NAME).username(admin.username()).password(admin.password())
                .clientId(CLIENT_ID).build();

        log.info("'{}' token: {}", admin.username(), keycloakMovieApp.tokenManager().grantToken().getToken());
        log.info("'{}' initialization completed successfully!", REALM_NAME);
    }

    private Map<String, List<String>> getClientRoles(UserPass userPass) {
        List<String> roles = new ArrayList<>();
        roles.add(WebSecurityConfig.USER);
        if ("admin".equals(userPass.username())) {
            roles.add(WebSecurityConfig.ADMIN);
        }
        return Map.of(CLIENT_ID, roles);
    }

    private static final String KEYCLOAK_SERVER_URL = "http://localhost:8180";
    private static final String REALM_NAME = "aicm2";
    private static final String CLIENT_ID = "aicm-client";
    private static final String REDIRECT_URL = "http://localhost:3000/*";
    private static final List<UserPass> APP_USERS = Arrays.asList(
            new UserPass("admin", "admin"),
            new UserPass("user", "user"));

    private record UserPass(String username, String password) {
    }
}