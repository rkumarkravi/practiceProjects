import React, { useState } from "react";
import { Switch } from "@headlessui/react";

const SettingsPage = () => {
  const [notificationsEnabled, setNotificationsEnabled] = useState(true);
  const [darkMode, setDarkMode] = useState(false);
  const [dataSharing, setDataSharing] = useState(false);

  const handleSaveSettings = () => {
    // Handle saving settings (e.g., send data to backend)
    alert("Settings saved successfully!");
  };

  return (
    <div className="min-h-screen bg-CF4EDD3 p-6">
      <header className="bg-C4C585B text-white p-4 rounded-lg shadow-md mb-6">
        <h1 className="text-3xl font-bold">Settings</h1>
        <p className="text-sm">Manage your account, preferences, and security.</p>
      </header>

      <main className="bg-white rounded-lg shadow-md p-6">
        <h2 className="text-2xl font-bold mb-4">Account Settings</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* Notifications Settings */}
          <div className="flex justify-between items-center">
            <p className="text-lg font-semibold text-C4C585B">Enable Notifications</p>
            <Switch
              checked={notificationsEnabled}
              onChange={setNotificationsEnabled}
              className={`${
                notificationsEnabled ? "bg-C4C585B" : "bg-gray-300"
              } relative inline-flex h-6 w-11 items-center rounded-full`}
            >
              <span
                className={`${
                  notificationsEnabled ? "translate-x-6" : "translate-x-1"
                } inline-block h-4 w-4 transform rounded-full bg-white transition`}
              />
            </Switch>
          </div>

          {/* Theme Preferences */}
          <div className="flex justify-between items-center">
            <p className="text-lg font-semibold text-C4C585B">Dark Mode</p>
            <Switch
              checked={darkMode}
              onChange={setDarkMode}
              className={`${
                darkMode ? "bg-C4C585B" : "bg-gray-300"
              } relative inline-flex h-6 w-11 items-center rounded-full`}
            >
              <span
                className={`${
                  darkMode ? "translate-x-6" : "translate-x-1"
                } inline-block h-4 w-4 transform rounded-full bg-white transition`}
              />
            </Switch>
          </div>

          {/* Privacy Settings */}
          <div className="flex justify-between items-center">
            <p className="text-lg font-semibold text-C4C585B">Data Sharing</p>
            <Switch
              checked={dataSharing}
              onChange={setDataSharing}
              className={`${
                dataSharing ? "bg-C4C585B" : "bg-gray-300"
              } relative inline-flex h-6 w-11 items-center rounded-full`}
            >
              <span
                className={`${
                  dataSharing ? "translate-x-6" : "translate-x-1"
                } inline-block h-4 w-4 transform rounded-full bg-white transition`}
              />
            </Switch>
          </div>

          {/* Security */}
          <div className="flex flex-col">
            <p className="text-lg font-semibold text-C4C585B">Change Password</p>
            <button
              className="mt-2 bg-C4C585B text-white px-4 py-2 rounded shadow hover:bg-CF4EDD3"
              onClick={() => alert("Redirect to Change Password Page")}
            >
              Change Password
            </button>
          </div>

          {/* Delete Account */}
          <div className="flex flex-col">
            <p className="text-lg font-semibold text-red-500">Delete Account</p>
            <button
              className="mt-2 bg-red-500 text-white px-4 py-2 rounded shadow hover:bg-red-400"
              onClick={() => alert("Redirect to Account Deletion Page")}
            >
              Delete Account
            </button>
          </div>
        </div>

        <hr className="my-6" />

        {/* Save Settings Button */}
        <div className="flex justify-end">
          <button
            onClick={handleSaveSettings}
            className="bg-C4C585B text-white px-6 py-2 rounded shadow hover:bg-CF4EDD3"
          >
            Save Changes
          </button>
        </div>
      </main>
    </div>
  );
};

export default SettingsPage;
