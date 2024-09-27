import App from '@/App.vue'
import Contatti from '@/components/Contatti.vue'
import Home from '@/components/Home.vue'
import { keycloakService } from '@/services/keycloak'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/contatti",
      name: "Contatti",
      component: Contatti,
      meta: {requireAuth: true}
    },
    {
      path: "/home",
      name: 'Home',
      component: Home,
      meta: {requireAuth: false}
    },
    {
      path: "/",
      name: '',
      component: App,
      meta: {requireAuth: false}
    },
  ]
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.meta.requiresAuth;

  if (requiresAuth && !keycloakService.keycloak.authenticated) {
      next('/home');
  } else {
      next();
  }
});

export default router
