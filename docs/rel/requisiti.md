# Requisiti

Di seguito elencati i requisiti funzionali, non funzionali e i vincoli del sistema.

## Vincoli

1. La logica del backend è vincolata dallo stato dei servizi *Postgres e OAuth*
2. Analisi granulare dei permessi utente

## Requisiti funzionali

1. Deve essere possibile inserire un'immagine per utente, anche se ne esiste già una uguale per un utente differente.
2. Ci deve essere almeno un utente *Admin* all'iterno dell'applicativo

## Requisiti non funzionali

1. Il servizio deve essere sempre disponibile
2. L'authentication provider deve sempre essere pronto ad autenticare gli utenti
3. Il servizio S3 deve essere sempre reperibile, così come il db contatti