package com.example.dbcon.dbcon.Services;

import java.util.List;

import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.Persone;
import com.example.dbcon.dbcon.repositories.PersoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersoneService {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    
    private final PersoneRepository personeRepository;

    public List<Persone> getAllPersone(Authentication connectedUser){
        logger.info("Utente connesso:" + connectedUser.getCredentials());
        return personeRepository.findAll();
    }

    public Persone insertPersone(Persone persona, Authentication connectedUser){
        return personeRepository.save(persona);
    }

    public List<Persone> getPersoneByGroup(Authentication connectedUser, String group){
        return personeRepository.GetPersoneByGroup(group);
    }

    public void editUserGroup(Long id, String group){
        personeRepository.updateGruppoById(id, group);
    }

}
