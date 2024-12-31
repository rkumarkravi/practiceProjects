// src/features/globalSlice.js
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  globalState: {}, // Store key-value pairs
};

const globalSlice = createSlice({
  name: 'global',
  initialState,
  reducers: {
    setKeyValue: (state, action) => {
      const { key, value } = action.payload;
      state.globalState[key] = value; // Set or update the key-value pair
    },
    removeKey: (state, action) => {
      const key = action.payload;
      delete state.globalState[key]; // Remove the key-value pair
    },
    clearAll: (state) => {
      state.globalState = {}; // Clear all key-value pairs
    },
  },
});

export const { setKeyValue, removeKey, clearAll } = globalSlice.actions;
export default globalSlice.reducer;
