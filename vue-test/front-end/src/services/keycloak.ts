import Keycloak from 'keycloak-js';
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
        clientId: 'applicazione',
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile;
  }

  async init(): Promise<void> {
    if (this._initialized) return;

    try {
      const authenticated: boolean = await this.keycloak?.init({
        onLoad: 'login-required',
        checkLoginIframe: false,
      });

      if (authenticated) {
        this._profile = (await this.keycloak?.loadUserProfile()) as UserProfile;
        this._profile.token = this.keycloak?.token;
      }
    } catch (error) {
      console.error('Errore durante l\'inizializzazione di Keycloak:', error);
    }

    this._initialized = true;
  }

  async login(): Promise<void> {
    try {
      await this.keycloak?.login();
    } catch (error) {
      console.error('Errore durante il login:', error);
    }
  }

  async logout(): Promise<void> {
    try {
      await this.keycloak?.logout({ redirectUri: 'http://localhost:8081/home' });
    } catch (error) {
      console.error('Errore durante il logout:', error);
    }
  }

  async isAuthenticated(): Promise<boolean> {
    return this.keycloak?.authenticated || false;
  }

  async refreshToken(): Promise<void> {
    try {
      await this.keycloak?.updateToken(30);
    } catch (error) {
      console.error('Errore durante l\'aggiornamento del token:', error);
    }
  }
}

export const keycloakService = new KeycloakService();
