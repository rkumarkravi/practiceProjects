import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Import routing utilities
import HeroV2 from './pages/HeroV2';
import ContactMe from './pages/ContactMe';
import Portfolio from './pages/portfolio/Portfolio';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        {/* Define routes for your app */}
        <Route path="/" element={<HeroV2 />} />
        <Route path="/contact" element={<ContactMe />} />
        <Route path="/portfolio" element={<Portfolio />} />
      </Routes>
    </Router>
  </React.StrictMode>
);
