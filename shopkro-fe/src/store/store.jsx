
import { configureStore } from '@reduxjs/toolkit';
import globalReducer from './globalSlice'
export const store = configureStore({
  reducer: {
    global: globalReducer, // Add the user slice to the store
  },
});
