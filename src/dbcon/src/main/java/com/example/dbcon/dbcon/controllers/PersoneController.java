package com.example.dbcon.dbcon.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbcon.dbcon.Services.PersoneService;
import com.example.dbcon.dbcon.entities.Persone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Persone insertPersone(@RequestBody Persone persona){
        return personeService.insertPersone(persona);
    }

    @GetMapping("/ricerca")
    public List<Persone> getByCity(@RequestParam String citta){
        return personeService.getPersoneByCity(citta);
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, secured";
    }
    
    
}