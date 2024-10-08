import type { Contatti } from '@/types/contatti';
import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

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
  async getRoles(): Promise<String>{
    try{
      const response = await axios.get<String>('${API_URL}/roles/roles');
      return response.data;
    } catch (error) {
      console.error('Error fetching people:', error);
      throw error;
    }
  }
};
