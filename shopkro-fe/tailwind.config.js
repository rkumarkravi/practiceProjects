/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html","./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        C4C585B: '#4C585B', // You can name it as you like
        C7E99A3: '#7E99A3',
        CA5BFCC: '#A5BFCC',
        CF4EDD3: '#F4EDD3'
      },
    },
  },
  plugins: [],
}

