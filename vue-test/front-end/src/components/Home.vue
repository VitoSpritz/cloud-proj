
<template>
  <div>
    <h1>{{ greeting }}</h1>
    <router-link to="/contatti" class="contact-link">Vai ai Contatti</router-link>
  </div>
</template>
  
<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { keycloakService } from '@/services/keycloak';

export default defineComponent({
  name: 'Home',
  setup() {
    const greeting = ref('Ciao');

    onMounted(async () => {
      try {
        if (!keycloakService.keycloak.authenticated) {
          await keycloakService.init();
        }

        const profile = keycloakService.profile;

        if (profile && profile.firstName) {
          greeting.value = `Ciao, ${profile.firstName}`;
        } else {
          greeting.value = 'Ciao, ospite';
        }
      } catch (error) {
        console.error('Errore durante il caricamento del profilo:', error);
        greeting.value = 'Ciao, ospite';
      }
    });

    return {
      greeting,
    };
  },
});
</script>


  
<style scoped>
h1 {
  font-size: 2em;
  color: #42b983;
}
</style>
  