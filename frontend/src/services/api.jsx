import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api' // Adjust this based on your backend URL
});

export default api;
