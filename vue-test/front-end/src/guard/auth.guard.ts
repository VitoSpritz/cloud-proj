
import { keycloakService } from '@/services/keycloak';

export const authGuard = (to: any, from: any, next: any) => {
    if (keycloakService.keycloak?.isTokenExpired()) {
        next('/login');
    } else {
        next();
    }
};