<template>
  <div>
    <router-link to="/contatti" class="contact-link">Torna a contatti</router-link>
    <p>Sei Admin</p>

    <form class="row g-3" @submit.prevent="registerUser">
      <div class="col-md-6">
        <label for="inputEmail4" class="form-label">Email</label>
        <input type="mail" class="form-control" id="inputEmail4" v-model="mail">
      </div>
      <div class="col-md-6">
        <label for="inputNome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="inputNome" v-model="nome">
      </div>
      <div class="col-md-6">
        <label for="inputCognome" class="form-label">Cognome</label>
        <input type="text" class="form-control" id="inputCognome" v-model="cognome">
      </div>
      <div class="col-12">
        <label for="inputCitta" class="form-label">Città</label>
        <input type="text" class="form-control" id="inputCitta" v-model="citta">
      </div>
      <div class="col-12">
        <label for="inputAddress2" class="form-label">Telefono</label>
        <input type="text" class="form-control" id="inputTelefono" v-model="telefono">
      </div>
      <div class="col-md-4">
        <label for="inputSesso" class="form-label">Sesso</label>
        <select id="inputSesso" class="form-select" v-model="sesso">
          <option disabled value="">Scegli...</option>
          <option value="M">M</option>
          <option value="F">F</option>
        </select>
      </div>
      <div class="col-md-2">
        <label for="inputGruppo" class="form-label">Gruppo</label>
        <select id="inputGruppo" class="form-select" v-model="gruppo">
          <option disabled value="">Scegli...</option>
          <option value="IT">IT</option>
          <option value="Office">Office</option>
        </select>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary">Registra</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import http from '@/services/interceptor';

export default defineComponent({
  name: 'CRUD',
  data() {
    return {
      mail: '',
      nome: '',
      cognome: '',
      citta: '',
      telefono: '',
      sesso: '',
      gruppo: ''
    };
  },
  methods: {
    async registerUser() {
      try {
        const user = {
          mail: this.mail,
          nome: this.nome,
          cognome: this.cognome,
          citta: this.citta,
          telefono: this.telefono,
          sesso: this.sesso,
          gruppo: this.gruppo
        };
        console.log(JSON.stringify(user));
        const response = await http.post('/insert', JSON.stringify(user));
        console.log('Utente registrato con successo', response.data);
      } catch (error) {
        console.error('Errore durante la registrazione dell\'utente', error);
      }
    }
  }
});
</script>