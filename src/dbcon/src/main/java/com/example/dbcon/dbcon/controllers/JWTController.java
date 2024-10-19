package com.example.dbcon.dbcon.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/roles")
public class JWTController {

    @GetMapping("/Admins")
    @PreAuthorize("hasRole('ROLE_Admins')")
    public String adminEndpoint() {
        return "Accesso riservato all'admin";
    }

    @GetMapping("/General")
    @PreAuthorize("hasRole('ROLE_General')")
    public String userEndpoint() {
        return "Accesso riservato all'utente";
    }

    @GetMapping("/token")
    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return "JWT: " + jwt.getTokenValue();
        }
        return "No JWT available";
    }
    
}
