<script setup lang="ts">
import { computed } from 'vue';
import { keycloakService } from '@/services/keycloak';
import { RouterLink, RouterView } from 'vue-router';

const isAuthenticated = computed(() => keycloakService.keycloak?.authenticated);
</script>

<template>
  <div v-if="isAuthenticated">
    <button @click="logout">Logout</button>
  </div>
  <div v-else>
    <button @click="login">Login</button>
  </div>
  <div>
    <router-link to="/home" class="contact-link">Vai alla home</router-link>
    <router-link to="/contatti" class="contact-link">Vai ai Contatti</router-link>
  </div>
  <RouterView />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',
  methods: {
    async logout() {
      try {
        await keycloakService.logout();
        console.log('Logout effettuato con successo');
        
        await keycloakService.init();
      } catch (error) {
        console.error('Errore durante il logout:', error);
      }
    },
    async login() {
      try {
        await keycloakService.login();
        console.log('Login effettuato con successo');
      } catch (error) {
        console.error('Errore durante il login:', error);
      }
    },
  },
});
</script>

<!--
<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style> -->