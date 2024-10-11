package com.example.dbcon.dbcon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(JwtLoggingFilter.class);

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('GROUP_/Users') and hasRole('client_user')")
    public String getTesting(Authentication connectedUser){
        return "Utente connesso allo route roles";
    }

    @GetMapping("/isAdmin")
    @PreAuthorize("hasAuthority('GROUP_/Admins') and hasRole('client_admin')")
    public boolean isAdmin(Authentication connectedUser){
        return true;
    }

    @GetMapping("/canEdit")
    @PreAuthorize("hasAuthority('GROUP_/IT/Office')")
    public boolean isEditor(Authentication connectedUser){
        return true;
    }
}