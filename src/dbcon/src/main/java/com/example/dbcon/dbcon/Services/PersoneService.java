package com.example.dbcon.dbcon.Services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.Persone;
import com.example.dbcon.dbcon.repositories.PersoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersoneService {
    
    private final PersoneRepository personeRepository;

    public List<Persone> getAllPersone(Authentication connectedUser){
        return personeRepository.findAll();
    }

    public Persone insertPersone(Persone persona, Authentication connectedUser){
        return personeRepository.save(persona);
    }

    public List<Persone> getPersoneByGroup(Authentication connectedUser, String group){
        return personeRepository.GetPersoneByGroup(group);
    }

    public List<Persone> getPeopleForIT(Authentication connectedUser){
        return personeRepository.getPeopleForIT();
    }

    public List<Persone> getPeopleForOffice(Authentication connectedUser){
        return personeRepository.GetPeopleForOffice();
    }

    public void editUserGroup(String nome, String cognome, String gruppo){
        personeRepository.updateGruppoById(nome, cognome, gruppo);
    }

}
