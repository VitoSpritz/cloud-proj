package com.example.dbcon.dbcon.entities;

public record PersonaRequest(

    Long id,
    String nome,
    String cognome,
    String telefono,
    String citta,
    char sesso,
    String gruppo,
    String mail

) {
}
