import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api', // Ensure base URL is correct for your backend container
  timeout: 5000
});

// Add any interceptors or other configuration here

export default axiosInstance;
