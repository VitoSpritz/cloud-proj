# Deploy Model

Qui elencato il modello di deployment, con le specifiche di ogni componente del sistema, i loro percorsi di reti e domini cloud.

* **Postgres** è il databse scelto per lo sviluppo dell'applicativo e, nella soluzione viene utilizzato *cockroachDb* come soluzione cloud. Questo risulta essere un'ottima scelta perchè offre in modo gratuito un database di piccole dimnsioni con dei limini sul traffico abbastanza alti per il nostro utilizzo.

* **Object Storage** è il componente che deve archiviare le immagini utente, questo è stato deployato su *Scaleway*. Abbiamo utilizzato una struttura *S3* custom con il minimo indispensabile per poter soddisfare i requisiti del sistema.

* **Backend** è il componente che orchestra le chiamate alle API. Questo gestisce il traffico, restituisce le risorse e gli errori in caso di fallimento. Questo componente è deployato in cloud su *Scaleway* come container. Abbiamo utilizzato una immagine personalizzata caricata su dockerhub all'[indirizzo](https://hub.docker.com/r/vitospritz/dbcon-app).

* **Authentication Server** è il componente che permette l'accesso e autenticazione utente nell'applicativo. Questo è gestito in locale da un container *Keycloak*.

* **Fronted** è il componente che gestisce la visualizzazione dell'applicativo e permette all'utente l'interazione con le sue funzionalità. Questo componente è dipendente dall'authentication provider, quindi è presente in locale. L'immagine è disponibile su dockerhub all'[indirizzo](https://hub.docker.com/r/vitospritz/frontend).

## Tabella degli indirizzi

| Servizio | Indirizzo | Provider |
| - | - | - |
| Postgres | postgresql://test-cloud-5284.j77.aws-eu-central-1.cockroachlabs.cloud:26257/defaultdb?sslmode=verify-full&password=zJj9MHZcn8XVYlF0AXKuPA&user=cloud | [CockroachDB](https://www.cockroachlabs.com) |
| Object Storage | https://imagesutenti.s3.pl-waw.scw.cloud | [Scaleway](https://www.scaleway.com/en/) |
| Backend | https://giorgiovitosnojulv2-backend-application.functions.fnc.fr-par.scw.cloud/ | [Scaleway](https://www.scaleway.com/en/) |
| Authentication Server (locale) | http:keycloak:9180 | Docker |
| Frontend (locale) | localhost:8081 | Docker |
