package com.example.dbcon.dbcon.Services;

import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.PersonaRequest;
import com.example.dbcon.dbcon.entities.PersonaResponse;
import com.example.dbcon.dbcon.entities.Persone;

@Service
public class PersoneMapper {

    public Persone toPersona(PersonaRequest request){

        return Persone.builder()
            .nome(request.nome())
            .citta(request.citta())
            .cognome(request.cognome())
            .gruppo(request.gruppo())
            .id(request.id())
            .mail(request.mail())
            .sesso(request.sesso())
            .telefono(request.telefono())
        .build();
    }

    public PersonaResponse toPersonaResponse(Persone p){
        return PersonaResponse.builder()
            .nome(p.getNome())
            .citta(p.getCitta())
            .cognome(p.getCognome())
            .gruppo(p.getGruppo())
            .id(p.getId())
            .mail(p.getMail())
            .sesso(p.getSesso())
            .telefono(p.getTelefono())
        .build();
    }
    
}
