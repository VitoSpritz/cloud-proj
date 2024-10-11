
import axios from 'axios';
import { keycloakService } from '@/services/keycloak';

const http = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000
});

http.interceptors.request.use(
  (config) => {
    const token = (keycloakService.profile?.token)?.trim();
    console.log(token);
    
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
