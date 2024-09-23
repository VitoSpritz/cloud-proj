package com.example.dbcon.dbcon.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KeycloakConfig {
    @Value("${app.keycloak.admin.clientId}")
    private String clientId;
    @Value("${app.keycloak.admin.clientSecret}")
    private String clientSecret;
    @Value("${app.keycloak.serverUrl}")
    private String serverUrl;
    @Value("${app.keycloak.realm}")
    private String realm;

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
            .clientSecret(clientSecret)
            .clientId(clientId)
            .grantType("credentials")
            .realm(realm)
            .serverUrl(serverUrl)
            .build();
    }
    
}