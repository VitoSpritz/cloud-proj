# Obiettivi e requisiti

Un'azienda IT desidera avere un applicativo che permetta la gestione di tutti gli impiegati, salvando i propri dati, oltre che a una foto rappresentativa e limitando la visibilità per gruppo di appartenenza. Solo gli utenti con privilegi, come i dipendenti d'ufficio e gli amministratori, hanno il potere di modificare gli utenti e le loro caratteristiche.

## Richieste del committente

1. Utilizzare un approccio a microservizi.
2. Utilizzare tecnologie cloud per sollevare le dipendenza dai servizi in locale.
3. Rendere l'applicativo scalabile all'aumentare delle richieste.

***

## Requisiti

Di seguito elencati i requisiti funzionali, non funzionali e i vincoli del sistema.

## Vincoli

1. La logica del backend è vincolata dallo stato del database *Postgres e dalla chiave pubblica dell'authentication server*.
2. Analisi granulare dei permessi utente.

## Requisiti funzionali

1. Deve essere possibile inserire un'immagine per utente, anche se ne esiste già una uguale per un utente differente.
2. Ci deve essere almeno un utente *Admin* all'interno dell'applicativo.

## Requisiti non funzionali

1. Il servizio deve essere sempre disponibile.
2. L'authentication provider deve sempre essere pronto ad autenticare gli utenti.
3. Il servizio S3 deve essere sempre reperibile, così come il db contatti.
