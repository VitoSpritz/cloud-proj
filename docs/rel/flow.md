# Flow runtime

Analisi del metodo di **login** nell'applicativo:

![Alt](/imgs/sequence.png)

Per ogni utente che richiede la Home route avviene un *redirect* all'endpoint di login/register fornita da **Keycloak**. Una volta effettuato il login viene generato il *JWT* e inviato nel corpo della risposta. Questo verrà poi sempre trasportato nelle richieste utente fino a quando non effettuerà il logout, sempre gestito da un endpoint di keycloak. Una volta caricata la pagina di login l'utente potrà scegliere su quale rotta andare. Ogni rotta però ha un *livello di accesso* che può bloccare o meno l'accesso. Qualora l'utente chiamasse una route collegata a backend, viene inserito il JWT nella richiesta a backend (Spring Boot) dove viene controllato il token e, se è appartenente al gruppo gli viene restitutita la pagina richiesta.

***
Analisi della route `getContacts`:

![Alt](/imgs/getContacts.png)

Quando un utente accede alla pagina `Contatti` viene fatta una chiamata in background sull'API `/getContacts` che traduce il *JWT token*, ne controlla i permessi e crea una query parametrica in base ai ruoli. Una volta ottenuto il risultato della query i dati vengono visualizzati nel frontend.