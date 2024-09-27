
import axios from 'axios';
import { keycloakService } from '@/services/keycloak';

const http = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 1000,
});

http.interceptors.request.use(
  (config) => {
    const token = keycloakService.profile?.token;
    
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default http;
