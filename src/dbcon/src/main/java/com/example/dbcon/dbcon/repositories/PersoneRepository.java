package com.example.dbcon.dbcon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.example.dbcon.dbcon.entities.Persone;


public interface PersoneRepository extends JpaRepository<Persone, Long> {

    @Query("SELECT p FROM Persone p WHERE p.gruppo = :group")
    List<Persone> GetPersoneByGroup(@Param("group") String group);

    @Query("SELECT p FROM Persone p WHERE p.gruppo != 'Admin'")
    List<Persone> GetPeopleForOffice();

    @Query("SELECT p FROM Persone p WHERE p.gruppo NOT IN ('Admin', 'Office')")
    List<Persone> getPeopleForIT();

    @Modifying
    @Transactional
    @Query("UPDATE Persone p SET p.gruppo = :group WHERE p.nome = :nome and p.cognome = :cognome")
    int updateGruppoById(@Param("nome")String nome, @Param("cognome")String cognome, @Param("group") String group);

}
