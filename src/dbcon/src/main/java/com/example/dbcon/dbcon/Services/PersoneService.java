package com.example.dbcon.dbcon.Services;

import java.util.List;

import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.PersonaRequest;
import com.example.dbcon.dbcon.entities.Persone;
import com.example.dbcon.dbcon.repositories.PersoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersoneService {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    
    private final PersoneRepository personeRepository;
    private final PersoneMapper personeMapper;

    public List<Persone> getAllPersone(Authentication connectedUser){
        logger.info("Utente connesso:" + connectedUser.getCredentials());
        return personeRepository.findAll();
    }

    public Long insertPersone(PersonaRequest persona, Authentication connectedUser){
        
        Persone p = personeMapper.toPersona(persona);
        return personeRepository.save(p).getId();
    }

}
