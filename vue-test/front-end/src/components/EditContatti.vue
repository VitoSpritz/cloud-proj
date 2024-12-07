<template>
    <div>
      <label for="utente">Utente:</label>
      <select id="utente" v-model="selectedUser">
        <option v-for="user in people" :key="user.id" :value="user">
          {{ user.nome }}
        </option>
      </select>
  
      <label for="gruppo">Seleziona Gruppo:</label>
      <select id="gruppo" v-model="selectedGroup">
        <option v-for="group in groups" :key="group" :value="group">
          {{ group }}
        </option>
      </select>
  
      <button @click="updateGroup">Aggiorna Gruppo</button>
    </div>
  </template>

<script lang="ts">

    import { defineComponent, ref, onMounted } from 'vue';
    import type { Contatti } from '@/types/contatti';
    import http from '@/services/interceptor';

    export default defineComponent({

        name: "EditContatti",
        setup(){
            
            const people = ref<Contatti[]>([]);
            const selectedUser = ref<Contatti | null>(null);
            const selectedGroup = ref<string | null>(null);
            const groups = ref<string[]>(['User', 'IT', 'Office']);

            const fetchUserName = async () => {
                try {
                    const response = await http.get('/persone');
                    people.value = response.data;
                    selectedUser.value = response.data;
                    console.log("Perosne ottenute" + people.value);
                } catch (error) {
                    console.error("Errore durante il recupero del nome utente:", error);
                }
            };

            const updateGroup = async () => {

                if (!selectedUser.value || !selectedGroup.value) {
                    console.error("Utente o gruppo non selezionato.");
                    return;
                }

                try {
                    const group = selectedGroup.value
                    const response = await http.put(`/editUser/${selectedUser.value.nome}/${selectedUser.value.cognome}`, group);
                    console.log("Gruppo aggiornato con successo:", response.data);
                } catch (error) {
                    console.error("Errore durante l'aggiornamento del gruppo:", error);
                }
            };

            onMounted(() => {
                fetchUserName();
            });

            return {
                people,
                selectedUser,
                selectedGroup,
                groups,
                updateGroup,
            };
        }

    })

</script>