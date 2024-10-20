package com.example.dbcon.dbcon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.example.dbcon.dbcon.entities.Persone;


public interface PersoneRepository extends JpaRepository<Persone, Long> {
    
    /*@Query("SELECT p FROM Persone p WHERE p.citta = :citta")
    PersonaResponse findByCity(String citta);*/

    @Query("SELECT p FROM Persone p WHERE p.gruppo = :group")
    List<Persone> GetPersoneByGroup(@Param("group") String group);

    @Modifying
    @Transactional
    @Query("UPDATE Persone p SET p.gruppo = :group WHERE p.id = :id")
    int updateGruppoById(@Param("id")Long id, @Param("group") String group);

}
