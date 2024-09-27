package com.example.dbcon.dbcon.Services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.dbcon.dbcon.entities.PersonaRequest;
import com.example.dbcon.dbcon.entities.PersonaResponse;
import com.example.dbcon.dbcon.entities.Persone;
import com.example.dbcon.dbcon.entities.user.User;
import com.example.dbcon.dbcon.repositories.PersoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersoneService {
    
    private final PersoneRepository personeRepository;
    private final PersoneMapper personeMapper;

    public List<Persone> getAllPersone(){
        return personeRepository.findAll();
    }

    public Long insertPersone(PersonaRequest persona, Authentication connectedUser){
        Persone p = personeMapper.toPersona(persona);
        return personeRepository.save(p).getId();
    }

}
