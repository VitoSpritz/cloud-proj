package com.example.dbcon.dbcon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.dbcon.dbcon.entities.PersonaResponse;
import com.example.dbcon.dbcon.entities.Persone;

public interface PersoneRepository extends JpaRepository<Persone, Long> {
    
    /*@Query("SELECT p FROM Persone p WHERE p.citta = :citta")
    PersonaResponse findByCity(String citta);*/
}
