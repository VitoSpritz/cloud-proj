package com.example.dbcon.dbcon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contatti", schema = "my_schema")
public class Persone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String telefono;
    private String citta;
    private char sesso;
    private String gruppo;
    private String mail;

    public Persone(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
    public char getSesso() {
        return sesso;
    }
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }
    public String getGruppo() {
        return gruppo;
    }
    public void setGruppo(String gurppo) {
        this.gruppo = gurppo;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String email) {
        this.mail = email;
    }
}