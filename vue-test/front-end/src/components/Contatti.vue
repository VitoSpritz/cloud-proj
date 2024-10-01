<template>
    <h2>Contatti</h2>
    <h3>Tabella con i contatti</h3>
    
    <table>
      <thead>
        <tr>
          <th>Nome</th>
          <th>Cognome</th>
          <th>Email</th>
          <th>Telefono</th>
          <th>Città</th>
          <th>Sesso</th>
          <th>Gruppo</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="person in people" :key="person.id">
          <td>{{ person.nome }}</td>
          <td>{{ person.cognome }}</td>
          <td>{{ person.mail }}</td>
          <td>{{ person.telefono }}</td>
          <td>{{ person.citta }}</td>
          <td>{{ person.sesso }}</td>
          <td>{{ person.gruppo }}</td>
        </tr>
      </tbody>
    </table>
</template>

<script lang="ts">
    import { defineComponent, ref, onMounted } from 'vue';
    import type { Contatti } from '@/types/contatti';
    import http from '@/services/interceptor';

    export default defineComponent({
        name: 'Home',
        setup() {
            const people = ref<Contatti[]>([]);

            onMounted(async () => {
                try{
                  const response = await http.get('/persone');
                  people.value = response.data;
                }catch (errror){
                  console.error("Errore nel fetching")
                };

                try {
                  const response = await http.get('/test');
                  const value = response.data;
                  console.log(value);
                } catch (error) {
                  console.error("Errore durante il recupero dei ruoli:", error);
                };
            });
        
            return {
                people,
            };
        },
    });
    </script>