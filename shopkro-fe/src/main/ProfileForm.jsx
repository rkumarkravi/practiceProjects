import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

const ProfileForm = () => {
  const profileInfo = useSelector((state) => state.global.globalState.profile);
  const [formData, setFormData] = useState(profileInfo);

  // Update formData when profileInfo changes
  useEffect(() => {
    setFormData(profileInfo);
  }, [profileInfo]);

  const handleChange = (key, value) => {
    setFormData({
      ...formData,
      [key]: value,
    });
  };

  const renderInputField = (key, value) => {
    if (Array.isArray(value)) {
      // If value is an array, show it as a comma-separated string or leave it readonly
      return <input type="text" value={value.join(', ')} readOnly className="bg-gray-200 p-2 rounded" />;
    }

    switch (typeof value) {
      case 'string':
        return (
          <input
            type="text"
            value={value || ''}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded"
          />
        );
      case 'boolean':
        return (
          <input
            type="checkbox"
            checked={value}
            onChange={(e) => handleChange(key, e.target.checked)}
            className="form-checkbox h-4 w-4 text-blue-600"
          />
        );
      case 'number':
        return (
          <input
            type="number"
            value={value || ''}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded"
          />
        );
      default:
        return <input type="text" value={value || ''} readOnly className="bg-gray-200 p-2 rounded" />;
    }
  };

  return (
    <div className="p-6 bg-white shadow-md rounded-lg">
      <h2 className="text-2xl font-bold mb-4">Profile Information</h2>
      <form>
        {Object.entries(formData).map(([key, value]) => (
          <div key={key} className="mb-4">
            <label className="block text-lg font-semibold text-gray-700">{key}</label>
            <div className="mt-2">{renderInputField(key, value)}</div>
          </div>
        ))}
      </form>
    </div>
  );
};

export default ProfileForm;
