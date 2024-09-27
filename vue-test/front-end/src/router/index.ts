import Contatti from '@/components/Contatti.vue'
import Home from '@/components/Home.vue'
import { authGuard } from '@/guard/auth.guard'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/contatti",
      name: "Contatti",
      component: Contatti,
      beforeEnter: authGuard,
    },
    {
      path: "/",
      name: 'Home',
      component: Home,
      beforeEnter: authGuard,
    },
  ]
})

export default router
