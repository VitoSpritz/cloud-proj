package com.example.dbcon.dbcon.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.PersoneService;
import com.example.dbcon.dbcon.entities.PersonaRequest;
import com.example.dbcon.dbcon.entities.Persone;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersoneController {
    
    private final PersoneService personeService;

    @GetMapping("/persone")
    @PreAuthorize("hasRole('client_user')")
    public List<Persone> getAllPersone(Authentication connectedUser) {
        return personeService.getAllPersone(connectedUser);
    }

    @PostMapping("/insert")
    public ResponseEntity<Long> insertPersone(@Valid @RequestHeader PersonaRequest persona, Authentication connectedUser){
        return ResponseEntity.ok(personeService.insertPersone(persona, connectedUser));
    }
    
}