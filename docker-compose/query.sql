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

INSERT INTO my_schema.my_table (nome, cognome, telefono, citta, sesso, gruppo, mail)
VALUES
    ('Mario', 'Rossi', '1234567890', 'Roma', 'M', 'Admin', 'mario.rossi@example.com'),
    ('Luigi', 'Verdi', '0987654321', 'Milano', 'M', 'User', 'luigi.verdi@example.com'),
    ('Anna', 'Bianchi', '1112223334', 'Napoli', 'F', 'Admin', 'anna.bianchi@example.com'),
    ('Carla', 'Gialli', '4445556667', 'Torino', 'F', 'User', 'carla.gialli@example.com'),
    ('Marco', 'Neri', '7778889990', 'Firenze', 'M', 'Guest', 'marco.neri@example.com');
