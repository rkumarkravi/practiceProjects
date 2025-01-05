import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./auth/LoginPage.jsx";
import RegistrationPage from "./auth/RegisterPage.jsx";
import Dashboard from "./main/Dashboard.jsx";
import { Provider } from "react-redux";
import { store } from "./store/store.jsx";
import ProfileForm from "./main/ProfileForm.jsx";
import BuyerDashboard from "./main/dashboard/BuyerDashboard.jsx";
import SellerDashboard from "./main/dashboard/SellerDashboard.jsx";
import Home from "./main/Home.jsx";
import ProtectedRoute from "./auth/ProtectedRoute.jsx";
import NotFound from "./main/NotFound.jsx";
import SettingsPage from "./main/Settings.jsx";

// routes.js
createRoot(document.getElementById("root")).render(
  <Provider store={store}>
    <BrowserRouter>
      <Routes>
        <Route index element={<LoginPage />} />
        <Route path="register" element={<RegistrationPage />} />
        {/* <Route path="/dashboard" element={<Dashboard />} /> */}
        <Route path="dashboard" element={<Dashboard />}>
          <Route index element={<Home />} />
          <Route
            path="buyer"
            element={
              <ProtectedRoute roleRequired="BUYER">
                <BuyerDashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="seller"
            element={
              <ProtectedRoute roleRequired="SELLER">
                <SellerDashboard />
              </ProtectedRoute>
            }
          />
          <Route path="profile" element={<ProfileForm />} />
          <Route path="settings" element={<SettingsPage />} />
        </Route>
        <Route path="*" element={<NotFound />} />
        {/* <Route path="/profile" element={<ProfileForm />} /> */}
      </Routes>
    </BrowserRouter>
  </Provider>
);
