import Keycloak from 'keycloak-js'
import type { UserProfile } from './user-profile';

export class KeycloakService {
  private _keycloak: Keycloak | undefined;
  private _profile: UserProfile | undefined;
  private _initialized = false;

  get keycloak(): Keycloak {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9180',
        realm: 'Prova',
        clientId: 'applicazione'
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile;
  }

  get token(): String | undefined {
    return this._profile?.token;
  }

  async init() {
    if (this._initialized) return;

    const authenticated: boolean = await this.keycloak?.init({
      onLoad: 'login-required'
    });

    if (authenticated) {
      this._profile = (await this.keycloak?.loadUserProfile() as UserProfile);
      this._profile.token = this.keycloak?.token;
    }

    this._initialized = true;
  }

  login() {
    return this.keycloak?.login();
  }

  logout() {
    return this.keycloak?.logout({ redirectUri: "http://localhost:8081" });
  }
}

export const keycloakService = new KeycloakService();
