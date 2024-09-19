package com.example.dbcon.dbcon.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.Persone;
import com.example.dbcon.dbcon.repositories.PersoneRepository;

@Service
public class PersoneService {
    
    private final PersoneRepository personeRepository;

    public PersoneService(PersoneRepository personeRepository){
        this.personeRepository = personeRepository;
    }

    public List<Persone> getAllPersone(){
        return personeRepository.findAll();
    }

    public Persone insertPersone(Persone persona){
        return personeRepository.save(persona);
    }

    public List<Persone> getPersoneByCity(String citta){
        return personeRepository.findByCity(citta);
    }
}
