package com.example.dbcon.dbcon.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

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

    @GetMapping("/isUser")
    @PreAuthorize("hasAuthority('GROUP_/Users') or hasRole('client_user')")
    public String getTesting(Authentication connectedUser){
        return "Utente connesso allo route roles";
    }
}