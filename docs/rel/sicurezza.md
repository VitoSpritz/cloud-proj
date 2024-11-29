# Sicurezza

La gestione della sicurezza avviene tramite creazione e controllo dei token JWT emessi da un authentication provider che, nel nostro caso è **Keycloak**. Questo permette all'utente di effettuare il login o una registrazione e, dopo aver effettuato l'accesso, verrà generato un token di sessione valido per un periodo $T$ oppure per la sua sessione.

Qui riportato il diagramma dell'autenticazione dell'utente el a generazione del token

![Alt](/imgs/oauth.png)
