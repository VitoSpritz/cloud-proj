import './assets/main.css'
import {keycloakService} from './services/keycloak'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

async function bootstrap() {
    try {
        await keycloakService.init();

        const app = createApp(App);

        app.use(router);

        app.mount('#app');
    } catch (error) {
        console.error('Errore durante l\'inizializzazione dell\'applicazione', error);
    }
}

bootstrap();
