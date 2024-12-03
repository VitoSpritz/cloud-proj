# Deploy Model

Qui elencato il modello di deployment, con le specfiche di ogni componentedel sistema, i loro percorsi di reti e domini cloud.

* **Postgres** è il databse scelto per lo sviluppo dell'applicativo e, nella soluzione viene utilizzato *cockroachDb* come soluzione cloud. Questo risulta essere un'ottima scelta perchè offre in modo gratuito un database di piccole dimnsioni con dei limini sul traffico abbastanza alti per il nostro utilizzo.

* **Object Storage** è il componente che deve archiviare le immagini utente, questo è stato deployato su *Scaleway*. Abbiamo utilizzato una struttura custom con il minimo indispensabile per poter soddisfare i requisiti del sistema.

* **Backend** è il componente che orchestra le chiamate alle api. Questo gestisce il traffico, restituisce le risorse e gli errori in caso di fallimento. Questo componente è deployato in cloud su *Scaleway* come container.

* **Authentication Server** è il componente che permette l'accesso e autenticazione utente nell'applicativo. Questo è gestito in locale da un container *Keycloak*.

* **Fronted** è il componente che gestisce la visualizzazione dell'applicativo e permette all'utente l'interazione con le sue funzionalità. Questo componente è dipendente dall'authentication provider, quindi è presente in locale.
