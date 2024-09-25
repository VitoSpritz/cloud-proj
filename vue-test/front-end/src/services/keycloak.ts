
import Keycloak from 'keycloak-js'

export class KeycloakService {
  private _keycloak: Keycloak | undefined;

  // Getter che inizializza Keycloak se non è stato ancora creato
  get keycloak(): Keycloak{
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9180',
        realm: 'Prova',
        clientId: 'applicazione'
      });
    }
    return this._keycloak;
  }
}

export const keycloakService = new KeycloakService();
