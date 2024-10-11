import App from '@/App.vue'
import Contatti from '@/components/Contatti.vue'
import Home from '@/components/Home.vue'
import CRUD from '@/components/CRUDContatti.vue'
import Edit from '@/components/EditContatti.vue'
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
    {
      path: "/crudcontatti",
      name: 'CRUDContatti',
      component: CRUD,
      meta: {requireAuth: true}
    },
    {
      path: "/editcontatti",
      name: 'EditContatti',
      component: Edit,
      meta: {requireAuth: true}
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
