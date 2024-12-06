# Sicurezza

La gestione della sicurezza avviene tramite creazione e controllo dei token JWT emessi da un authentication provider che, nel nostro caso è **Keycloak**. Questo permette all'utente di effettuare il login o una registrazione e, dopo aver effettuato l'accesso, verrà generato un token di sessione valido per un periodo $T$ oppure per la sua sessione.

Qui riportato il diagramma dell'autenticazione dell'utente e della generazione del token:

![Alt](/imgs/oauth.png)

I token vengono poi portati in tutte le richieste utetene e, una volta arrivati a backend vengono letti utilizzando la chiave pubblica richiesta tramite un endpoint di Keycloak.

All'interno del servizio di backend avviene l'*unpacking* del token dove vengono estratti i ruoli di ogni utente e poi in base alle loro autorizzazioni gli vengono servite le giuste risorse o rotte.
