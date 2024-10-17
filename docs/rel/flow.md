# Flow runtime

Analis del metodo di **logout** nell'applicativo:

![Alt](/imgs/sequence.png)

Per ogni utente che richiede la Home route avviene un *redirect* all'endpoint di login/register fornita da **Keycloak**. Una volta effettuato il login viene generato il *JWT* e inviato nel corpo della risposta. Questo verrà poi sempre trasportato nelle richieste utente fino a quando non effettuerà il logout, sempre gestito da un endpoint di keycloak. Una volta caricata la pagina di login l'utente potrà scegliere su quale rotta andare. Ogni rotta però ha un *livello di accesso* che può bloccare o meno l'accesso. Qualora l'utente chiamasse una route collegata a backend, viene inserito il JWT nella richiesta a backend (Spring Boot) dove viene controllato il token e, se è appartenente al gruppo gli viene restitutita la pagina