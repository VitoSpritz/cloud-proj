
import Keycloak from 'keycloak-js'
import type { UserProfile } from './user-profile';

export class KeycloakService {
  private _keycloak: Keycloak | undefined;
  private _profile: UserProfile | undefined;

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

  get profile(): UserProfile | undefined{
    console.log(this._profile);
    return this._profile;
  }

  async init(){
    const authenticated:boolean = await this.keycloak?.init({
      onLoad: 'login-required'
    });

    if(authenticated){
      this._profile = (await this.keycloak?.loadUserProfile() as UserProfile);
      this._profile.token = this.keycloak?.token;
    }
  }

  login(){
    return this.keycloak?.login();
  }

  logout(){
    return this.keycloak?.login({redirectUri: "http:localhost:8081"});
  }

}

export const keycloakService = new KeycloakService();
