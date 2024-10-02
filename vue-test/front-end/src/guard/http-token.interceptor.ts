import axios from 'axios';
import { keycloakService } from '@/services/keycloak';

const axiosInstance = axios.create();

axiosInstance.interceptors.request.use(
    async (config) => {
        const token = keycloakService.keycloak?.token;

        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response?.status === 401) {
            console.error('Token scaduto o non valido. Reindirizzamento al login.');
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;