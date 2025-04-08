// axiosConfig.js
import axios from 'axios';

const baseURL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

const axiosInstance = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true, // 👈 important for cookies (if any)
});


export default axiosInstance;
