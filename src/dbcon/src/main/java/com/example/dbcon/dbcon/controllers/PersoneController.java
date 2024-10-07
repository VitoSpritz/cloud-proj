package com.example.dbcon.dbcon.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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