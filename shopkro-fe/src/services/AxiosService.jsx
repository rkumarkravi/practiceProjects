import axios from "axios";


// Create an Axios instance with a base URL
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Set your base URL here
  timeout: 10000, // Optional: set a timeout for requests (in milliseconds)
  headers: {
    'Content-Type': 'application/json', // Default content type for requests
  },
});

// Add a request interceptor (optional, e.g., to add authorization headers)
axiosInstance.interceptors.request.use(
  (config) => {
    // You can modify the request before it's sent (e.g., attach a token)
    // const token = localStorage.getItem('token'); // Example: Get token from local storage
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`;
    // }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add a response interceptor (optional, e.g., for error handling)
axiosInstance.interceptors.response.use(
  (response) => {
    // Any status code in the 2xx range triggers this function
    return response;
  },
  (error) => {
    // Handle errors (e.g., log out user if unauthorized)
    return Promise.reject(error);
  }
);

export default axiosInstance;
