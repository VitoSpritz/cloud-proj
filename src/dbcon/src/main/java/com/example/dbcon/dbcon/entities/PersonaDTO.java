package com.example.dbcon.dbcon.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class PersonaDTO {
    @Email
    @NotEmpty
    @JsonProperty("mail")
    private String mail;
    
    @NotEmpty
    @JsonProperty("nome")
    private String nome;
    
    @NotEmpty
    @JsonProperty("cognome")
    private String cognome;

    @JsonProperty("citta")
    private String citta;
    @JsonProperty("telefono")
    private String telefono;
    @JsonProperty("sesso")
    private String sesso;
    @JsonProperty("gruppo")
    private String gruppo;

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "mail='" + mail + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", citta='" + citta + '\'' +
                ", telefono='" + telefono + '\'' +
                ", sesso='" + sesso + '\'' +
                ", gruppo='" + gruppo + '\'' +
                '}';
    }
}
