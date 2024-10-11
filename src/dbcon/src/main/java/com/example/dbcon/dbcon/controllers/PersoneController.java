package com.example.dbcon.dbcon.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.PersoneService;
import com.example.dbcon.dbcon.entities.PersonaDTO;
import com.example.dbcon.dbcon.entities.Persone;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersoneController {
    
    private final PersoneService personeService;

    @GetMapping("/persone")
    @PreAuthorize("hasAnyAuthority('GROUP_/Admins', 'GROUP_/IT', 'GROUP_/Users')")
    public List<Persone> getAllPersone(Authentication connectedUser) {
        if (connectedUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("GROUP_/Admins"))) {
            return personeService.getAllPersone(connectedUser);
        } else if (connectedUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("GROUP_/IT")) ||
        connectedUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("GROUP_/IT/Office"))) {
            return personeService.getPersoneByGroup(connectedUser, "IT");
        } else if (connectedUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("GROUP_/Users"))) {
            return personeService.getPersoneByGroup(connectedUser, "User");
        }
        return Collections.emptyList();
    }


    @PutMapping("/editUser/{id}")
    public ResponseEntity<String> updateGruppoUtente(@PathVariable Long id,@RequestBody String nuovoGruppo, Authentication connectedUser) {
        
        try {
            nuovoGruppo = nuovoGruppo.replace("\"", "");
            personeService.editUserGroup(id, nuovoGruppo);
            return ResponseEntity.ok("Gruppo aggiornato con successo");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento del gruppo");
        }
    }

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('GROUP_/Admins') and hasRole('client_admin')")
    public ResponseEntity<Persone> insertPersone(@RequestBody PersonaDTO personaDTO, Authentication connectedUser){

        System.out.println("DTO ricevuto: " + personaDTO.toString());

        try{
            Persone p = new Persone();
            p.setMail(personaDTO.getMail());
            p.setNome(personaDTO.getNome());
            p.setCognome(personaDTO.getCognome());
            p.setCitta(personaDTO.getCitta());
            p.setTelefono(personaDTO.getTelefono());
            p.setSesso(personaDTO.getSesso());
            p.setGruppo(personaDTO.getGruppo());

            return new ResponseEntity<>(personeService.insertPersone(p, connectedUser), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}