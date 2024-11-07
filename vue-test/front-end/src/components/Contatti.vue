<template>
  <h2>Contatti</h2>
  <h3>Tabella con i contatti</h3>

  <router-link v-if="isAdmin" to="/crudcontatti" class="contact-link">Aggiungi un contatto</router-link>
  <router-link v-if="canEdit" to="/editContatti" class="contact-link">Aggiorna un contatto</router-link>

  <div>
    <input type="file" id="fileInput" @change="onFileSelected" />
    <button @click="uploadFile">Carica File Generale</button>
  </div>

  <table>
    <thead>
      <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
        <th>Telefono</th>
        <th>Città</th>
        <th>Sesso</th>
        <th>Gruppo</th>
        <th v-if="!isUser">Img</th>
        <th v-if="!isUser">Upload Immagine</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="person in people" :key="person.id">
        <td>{{ person.id }}</td>
        <td>{{ person.nome }}</td>
        <td>{{ person.cognome }}</td>
        <td>{{ person.mail }}</td>
        <td>{{ person.telefono }}</td>
        <td>{{ person.citta }}</td>
        <td>{{ person.sesso }}</td>
        <td>{{ person.gruppo }}</td>
        <td>
          <img :src="person.img" style="width: 100px; height: auto;" alt="User Image" v-if="person.img" />
          <span v-else>Immagine non disponibile</span>
        </td>
        <td v-if="canEdit || isAdmin">
          <input type="file" @change="onFileSelectedForUser($event, person.id)" />
          <button @click="uploadFileForUser(person.id)">Carica Immagine</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>


<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import type { Contatti } from '@/types/contatti';
import http from '@/services/interceptor';

export default defineComponent({
  name: 'Contatti',
  setup() {
    const people = ref<Contatti[]>([]);
    const isAdmin = ref(false);
    const canEdit = ref(false);
    const isUser = ref(false);
    const selectedFile = ref<File | null>(null);
    const userFileMap = ref<Record<number, File | null>>({});

    const onFileSelected = (event: Event) => {
      const target = event.target as HTMLInputElement;
      if (target.files && target.files[0]) {
        selectedFile.value = target.files[0];
      }
    };

    const onFileSelectedForUser = (event: Event, userId: number) => {
      const target = event.target as HTMLInputElement;
      if (target.files && target.files[0]) {
        userFileMap.value[userId] = target.files[0];
      }
    };

    const uploadFile = async () => {
      if (selectedFile.value) {
        const formData = new FormData();
        formData.append('file', selectedFile.value);
        try {
          const response = await http.post('/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          console.log('File caricato con successo:', response.data);
        } catch (error) {
          console.error('Errore durante il caricamento del file:');
        }
      } else {
        console.error('Nessun file selezionato.');
      }
    };

    const uploadFileForUser = async (userId: number) => {
      const file = userFileMap.value[userId];
      if (file) {
        const formData = new FormData();
        formData.append('file', file);
        try {
          const response = await http.post(`/minio/images/upload/${userId}`, formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          console.log(`File caricato con successo per l'utente ${userId}:`, response.data);
          const imageUrl = await fetchUserImage(userId);
          const person = people.value.find(p => p.id === userId);
          if (person) {
            person.img = imageUrl;
          }
        } catch (error) {
          console.error(`Errore durante il caricamento del file per l'utente ${userId}:`, error);
        }
      } else {
        console.error(`Nessun file selezionato per l'utente ${userId}.`);
      }
    };

    const fetchUserImage = async (userId: string | number) => {
      try {
        const response = await http.get(`minio/images/user/${userId}`, { responseType: 'blob' });
        const imageBlob = response.data;
        return URL.createObjectURL(imageBlob);
      } catch (error) {
        console.error("Errore durante il recupero dell'immagine per l'utente ${userId}:");
        return null;
      }
    };

    onMounted(async () => {
      try {
        const responseAdmin = await http.get('/isAdmin');
        isAdmin.value = responseAdmin.data;
      } catch (error) {
        console.error("L'utente non è admin:", error);
      }

      try {
        const responseEdit = await http.get('/canEdit');
        canEdit.value = responseEdit.data;
      } catch (error) {
        console.error("L'utente non può editare:", error);
      }
      
      try {
        const responseEdit = await http.get('/isUser');
        isUser.value = responseEdit.data;
      } catch (error) {
        console.error("L'utente non è registrato:", error);
      }

      try {
        const responsePeople = await http.get('/persone');
        people.value = responsePeople.data;

        for (const person of people.value) {
          const imageUrl = await fetchUserImage(person.id);
          person.img = imageUrl;
        }
      } catch (error) {
        console.error("Errore nel fetching dei contatti:", error);
      }
    });

    return {
      people,
      isAdmin,
      canEdit,
      isUser,
      onFileSelected,
      uploadFile,
      onFileSelectedForUser,
      uploadFileForUser,
      fetchUserImage
    };
  }
});
</script>

<style scoped>
img {
  max-width: 100%;
  height: auto;
}
</style>
