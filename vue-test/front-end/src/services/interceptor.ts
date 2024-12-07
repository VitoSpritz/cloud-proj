
import axios from 'axios';
import { keycloakService } from '@/services/keycloak';

const http = axios.create({
  baseURL: 'https://giorgiovitosnojulv2-backend-application.functions.fnc.fr-par.scw.cloud/api',
  timeout: 5000
});

http.interceptors.request.use(
  (config) => {
    const token = (keycloakService.profile?.token)?.trim();
    
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (!config.headers['Content-Type']) {
      config.headers['Content-Type'] = 'application/json';
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
export default http;
