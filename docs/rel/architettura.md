# Architettura

## Architettura logica

Di seguito riportato il diagramma della soluzione architetturale
![Alt](../imgs/progetto.png "Architettura")

## Elenco componenti

| Componente | Descrizione | Dipendenze |
|---|---|---|
| **Database contatti** | È il database che conterrà i dati dei contatti | Nessuna |
| **Contatti base frontend** | È il componente per il frontend che restituirà i risultati delle chiamate al backend | Backend, Authentication server |
| **Contatti logic** | È il componente di backend per la gestione dei servizi e orchestrazione delle chiamate | Database, Authentication Server, Object Storage |
| **Authentication server (OAuth)** | Authentication server che permette agli utenti di fare il login e poi di accedere a risorse specifiche | Nessuna |
|  **Object Storage** |  Servizio che mantiene le immagini e le restituisce su chiamata | Nessuna |

## Tecnologia e servizi

Di seguito sono riportate le tecnologie utilizzate per lo sviluppo del progetto.

| Componente | Descrizione | Componenti |
|---|---|---|
| Spring Boot | Framework Java per lo sviluppo di microservizi | Contatti, Orchestratore contatti |
| Keycloak | Componente per la gestione degli accesssi, gestione dei privilegi e dei JWT | Authentication server |
| Postgres | Database cloud per l'archiviazione dei contatti | CockroachDb |
| S3 Scaleway | Object storage per mantenere le immagini delle utenze | Scaleway Object Storage |
| Frontend | Componente frontend sviluppato con il framework *Vue.js* e *Typescript* per gestire l'interfaccia e l'interazione con gli utenti | Contatti base frontend |
