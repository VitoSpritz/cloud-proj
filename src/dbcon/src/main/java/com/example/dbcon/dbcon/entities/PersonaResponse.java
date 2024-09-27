package com.example.dbcon.dbcon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaResponse {
    
    private Long id;
    private String nome;
    private String cognome;
    private String telefono;
    private String citta;
    private char sesso;
    private String gruppo;
    private String mail;

}
