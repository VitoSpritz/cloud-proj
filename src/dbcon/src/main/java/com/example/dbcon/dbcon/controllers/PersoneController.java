package com.example.dbcon.dbcon.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.PersoneService;
import com.example.dbcon.dbcon.entities.Persone;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
public class PersoneController {
    
    private final PersoneService personeService;

    public PersoneController(PersoneService personeService){
        this.personeService = personeService;
    }

    @GetMapping("/persone")
    public List<Persone> getAllPersone() {
        return personeService.getAllPersone();
    }

    @PostMapping("/insert")
    public Persone insertPersone(@RequestHeader Persone persona){
        return personeService.insertPersone(persona);
    }

    @GetMapping("/ricerca")
    public List<Persone> getByCity(@RequestParam String citta){
        return personeService.getPersoneByCity(citta);
    }

    @GetMapping("/secured-2")
    @PreAuthorize("hasrole('client_admin')")
    public String secured(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String name = authentication.getName();
            if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
                Jwt jwt = (Jwt) authentication.getPrincipal();
                // Accedi al contenuto del JWT
                String username = jwt.getClaimAsString("preferred_username");
                String email = jwt.getClaimAsString("email");
                return "Username: " + username + ", Email: " + email;
            }
            return "Not Hello, " + name + " " + authentication.getPrincipal();
        }
        
        return "Hello, Guest";
    }

    @GetMapping("/secured")
    @PreAuthorize("hasrole('client_user')")
    public String secured() {

        return "Hello, Guest";
    }
    
}