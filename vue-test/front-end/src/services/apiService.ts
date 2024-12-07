import type { Contatti } from '@/types/contatti';
import axios from 'axios';

const API_URL = 'https://giorgiovitosnojulv2-backend-application.functions.fnc.fr-par.scw.cloud/api';

export default {
  async getPeople(): Promise<Contatti[]> {
    try {
      const response = await axios.get<Contatti[]>(`${API_URL}/persone`);
      return response.data;
    } catch (error) {
      console.error('Error fetching people:', error);
      throw error;
    }
  },
  async getRoles(): Promise<String> {
    try {
      const response = await axios.get<String>(`${API_URL}/roles/roles`);
      return response.data;
    } catch (error) {
      console.error('Error fetching roles:', error);
      throw error;
    }
  }
};
