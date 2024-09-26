package com.example.dbcon.dbcon.Services;
// to delete
import java.util.List;
import java.util.Objects;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

import com.example.dbcon.dbcon.Services.Interfaces.NewUserService;
import com.example.dbcon.dbcon.entities.NewUser;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements NewUserService {

    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void createUser(NewUser newUser) {
        
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUser.firsName());
        userRepresentation.setLastName(newUser.lastName());
        userRepresentation.setUsername(newUser.username());
        userRepresentation.setEmail(newUser.username());
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUser.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource userResource = getUsersResource();

        Response response = userResource.create(userRepresentation);

        log.info("Status Code" + response.getStatus());

        if(!Objects.equals(201, response.getStatus())){
            throw new RuntimeException("Error status code: " + response.getStatus());
        }

        log.info("New user created");

    }
    
    private UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }

    @Override
    public void sendVerificationEmail(String clientId) {
        UsersResource userResource = getUsersResource();
        userResource.get(clientId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String clientId){
        UsersResource userResource = getUsersResource();
        userResource.delete(clientId);
    }

    @Override
    public UserResource getUser(String userId) {
        UsersResource userResource = getUsersResource();
        return userResource.get(userId);
    }

    @Override
    public List<RoleRepresentation> getUserRole(String userId) {

        return getUser(userId).roles().realmLevel().listAll();
    }

    @Override
    public List<GroupRepresentation> getUserGroup(String userId){

        return getUser(userId).groups();
    }
}