# Osservabilità

Per osservare il sistema e le sue metriche ci siamo inizialmente basati sull'uso massiccio del comando `docker logs container_name` nella fase di produzione in locale, utilizzando print e logs mirati ai singoli componenti durante lo sviluppo e, una volta effettuato il deployment, abbiamo utilizzato gli strumenti di monitoraggio dei cloud provider a cui ci siamo appoggiati. Ad esempio *cockroachDb* offre un ottimo sistema di monitoraggio traffico dati, oltre a vedere il totoale delle richeiste effettuate e la memoria utilizzata. Scaleway invece offre meno statistiche specifiche, ma comunque tiene traccia del traffico e dell'utilizzo della memoria.