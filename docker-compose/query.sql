CREATE SCHEMA IF NOT EXISTS my_schema;

CREATE TABLE IF NOT EXISTS my_schema.contatti (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    telefono VARCHAR(15),
    citta VARCHAR(50),
    sesso CHAR(1) CHECK (sesso IN ('M', 'F')),
    gruppo VARCHAR(50),
    mail VARCHAR(100) UNIQUE
);