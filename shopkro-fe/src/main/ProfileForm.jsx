import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "sonner";
import axiosInstance from "../services/AxiosService";

const ProfileForm = () => {
  const location = useLocation();
  const { type } = location.state || {};
  const profileInfo = useSelector((state) => state.global.globalState.profile);
  const [formData, setFormData] = useState(profileInfo || {});
  const navigate = useNavigate();

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

  const renderInputField = (key, value, isDisabled = true) => {
    if (Array.isArray(value)) {
      return (
        <input
          type="text"
          value={value.join(", ")}
          disabled={isDisabled}
          name={key}
          readOnly
          className="bg-gray-100 p-2 rounded w-full border text-C4C585B"
        />
      );
    }

    switch (typeof value) {
      case "string":
        return (
          <input
            type="text"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded w-full text-C4C585B"
          />
        );
      case "boolean":
        return (
          <input
            type="checkbox"
            checked={value}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.checked)}
            className="form-checkbox h-5 w-5 text-C4C585B"
          />
        );
      case "number":
        return (
          <input
            type="number"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded w-full text-C4C585B"
          />
        );
      default:
        return (
          <input
            type="text"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            readOnly
            className="bg-gray-100 p-2 rounded w-full border text-C4C585B"
          />
        );
    }
  };

  const handleSaveProfileData = async (e) => {
    e.preventDefault();
    try {
      const formData = {};
      for (let i = 0; i < e.target.length; i++) {
        if (e.target[i].name) {
          formData[e.target[i].name] = e.target[i].value;
        }
      }
      const response = await axiosInstance.post(`/api/seller/${formData.id}/update`, formData);

      if (response.status === 200) {
        toast("Profile Update Successfully !");
      }
      navigate("profile");
    } catch (err) {
      toast("Profile Update failed : " + (err.response?.data || err.message));
      console.error(
        "Profile Update failed:",
        err.response?.data || err.message
      );
    }
  };

  return (
    <div className="p-8 bg-CF4EDD3 shadow-md rounded-lg max-w-4xl mx-auto">
      <h2 className="text-3xl font-bold text-C4C585B mb-6 text-center">
        Profile Information
      </h2>
      <form
        className="grid grid-cols-1 sm:grid-cols-2 gap-6"
        onSubmit={handleSaveProfileData}
      >
        {Object.entries(formData).map(([key, value]) => (
          <div key={key} className="flex flex-col">
            <label className="text-lg font-medium text-gray-700 mb-2 capitalize">
              {key.replace(/([A-Z])/g, " $1").toLowerCase()}
            </label>
            <div>
              {renderInputField(key, value, type === "view" ? true : false)}
            </div>
          </div>
        ))}
        <div className="text-center mt-6">
          { type === "edit" &&
            <button
              type="submit"
              className="bg-C4C585B text-white py-2 px-6 rounded shadow-md hover:bg-C7E99A3 transition duration-300"
            >
              Save Changes
            </button>
          }
        </div>
      </form>
    </div>
  );
};

export default ProfileForm;
