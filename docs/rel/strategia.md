# Strategia di solutioning

La strategia di solutioning prevede le seguenti fasi:

### 1. Analisi della richiesta del cliente, analisi delle funzionalità e delineazione delle operazioni base

Abbiamo analizzato la richesta e delineato le funzionalità principali, abbozzando i servizi e le loro funzionalità.

### 2. Suddivisione del dominio applicativo in funzioni autonome e indipendenti

Abbiamo suddiviso le funzionalità in componenti indipendenti, così da rispettare il vincolo di *singola responsabilità*. Ad esempio abbiamo diviso il backend in componenti indipendenti per ogni servizio come quello per il DB, per l'object storage e l'authentication server.

### 3. Disegno dell'infrastruttura applicativa generale

Abbiamo realizzato dei grafici uml per rappresentare l'infrastruttura, delineando i collegamenti tra i servizi.

### 4. Disegno dei servizi esterni necessari

Fase di disegno dei servizi esterni al solo codice backend, come la ricerca di servizi adeguati al nostro caso.

### 5. Disegno delle API

Disegno e progettazione delle API, del loro funzionamento e della loro comunicazione all'iterno dell'applicazione.

### 6. Implementazione delle funzionalità

Sviluppo delle funzionalità del backend utilizzando framework Spring Boot.

### 7. Testing delle API

Fase di testing delle funzionalità delle API tramite chiamate con *Postman* con controllo delle risposte con sistema di logging interno.

### 8. Migrazione in cloud delle funzioanlità

Ricerca e successiva migrazione dei servizi in cloud, come descritto nella fase di [deployment](/docs/rel/deploy-model.md).

### 9. Revisione del codice

Revisione del codice backend e frontend per andare ad eliminare eventuali logger e a sostituire le route con quelle in cloud, oltre a parametrizzare le proprietà con variabili di ambiente per rendere il deployment agevole.

### 10. Testing dell'applicazione nella sua interezza

Testing effettivo dell'applicazione deployata, con controllo delle metriche dei provider e logs sporadici del backend/frontend.

### 11. Deployment dell'applicativo

Finalizzazione del controllo delle funzionalità e deployment finale con rifiniture al codice.

### 13. Consegna finale

Consegna dell'applicazione con relazione del processo di sviluppo.
