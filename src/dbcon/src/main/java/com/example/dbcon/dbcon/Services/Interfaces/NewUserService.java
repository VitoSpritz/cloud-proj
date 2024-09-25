package com.example.dbcon.dbcon.Services.Interfaces;

import java.util.List;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import com.example.dbcon.dbcon.entities.NewUser;

public interface NewUserService {
    
    public void createUser(NewUser newUser);

    public void sendVerificationEmail(String clientId);

    public void deleteUser(String clientId);

    UserResource getUser(String userId);

    List<RoleRepresentation> getUserRole(String userId);

    public List<GroupRepresentation> getUserGroup(String userId);
}
