import './assets/main.css'
import {keycloakService} from './services/keycloak'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const keycloak = keycloakService.keycloak;

keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
    if (!authenticated) {
        console.log('Non autenticato!');
    }
    createApp(App)
        .use(router)
        .mount('#app');

    console.log('Utente autenticato con successo', keycloak.token);
    }).catch((error) => {
    console.error('Errore durante l\'inizializzazione di Keycloak', error);
});