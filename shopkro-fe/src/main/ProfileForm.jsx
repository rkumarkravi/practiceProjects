import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "sonner";
import axiosInstance from "../services/AxiosService";
import { setKeyValue } from "../store/globalSlice";

const ProfileForm = () => {
  const dispatch = useDispatch();
  const location = useLocation();
  const { type } = location.state || {};
  const profileInfo = useSelector((state) => state.global.globalState.profile);
  const init = useSelector((state) => state.global.globalState.init);
  const [formData, setFormData] = useState(profileInfo || {});
  const [editingAllowed, setEditingAllowed] = useState(
    (init.editing_allowed && init.editing_allowed.split(",")) || []
  );
  const [editingTypes, setEditingTypes] = useState(
    (init.editing_type &&
      init.editing_type.split(",").reduce((acc, item) => {
        let c = item.split(":");
        acc[c[0]] = c[1];
        return acc;
      }, {})) ||
      []
  );
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
    let c;
    if (Array.isArray(value)) {
      c = (
        <input
          type="text"
          value={value.join(", ")}
          disabled={isDisabled}
          name={key}
          onChange={(e) => handleChange(key, e.target.value)}
          className="bg-gray-100 p-2 rounded w-full border text-C4C585B"
        />
      );
    }

    switch (typeof value) {
      case "string":
        c = (
          <input
            type="text"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded w-full text-C4C585B"
          />
        );
        break;
      case "boolean":
        c = (
          <input
            type="checkbox"
            checked={value}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.checked)}
            className="form-checkbox h-5 w-5 text-C4C585B"
          />
        );
        break;
      case "number":
        c = (
          <input
            type="number"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.value)}
            className="border p-2 rounded w-full text-C4C585B"
          />
        );
        break;
      default:
        c = (
          <input
            type="text"
            value={value || ""}
            disabled={isDisabled}
            name={key}
            onChange={(e) => handleChange(key, e.target.value)}
            className="bg-gray-100 p-2 rounded w-full border text-C4C585B"
          />
        );
    }
    if (editingTypes[key]) {
      c = (
        <input
          type={editingTypes[key]}
          value={value || ""}
          disabled={isDisabled}
          name={key}
          onChange={(e) => handleChange(key, e.target.value)}
          className="border p-2 rounded w-full text-C4C585B"
        />
      );
    }

    return c;
  };

  const handleSaveProfileData = async (e) => {
    e.preventDefault();
    try {
      for (let i = 0; i < e.target.length; i++) {
        if (e.target[i].name) {
          formData[e.target[i].name] = e.target[i].value;
        }
      }
      const response = await axiosInstance.put(
        `/api/buyer/${formData.id}/update`,
        formData
      );

      if (response.status === 200) {
        dispatch(
          setKeyValue({ key: "profile", value: response.data })
        );
        toast("Profile Update Successfully !");
      }
      navigate("",{state:{type:"view"}});
    } catch (err) {
      toast("Profile Update failed : " + (err.response?.data || err.message));
      console.error(
        "Profile Update failed:",
        err.response?.data || err.message
      );
    }
  };

  return (
    <div className="p-8 bg-CF4EDD3 shadow-md w-full mx-auto">
      <h2 className="text-3xl font-bold text-C4C585B mb-6 text-center">
        Profile Information
      </h2>
      <form
        className="grid grid-cols-1 sm:grid-cols-2 gap-6"
        onSubmit={handleSaveProfileData}
      >
        {Object.entries(formData).map(
          ([key, value]) =>
            editingAllowed.includes(key) && (
              <div key={key} className="flex flex-col">
                <label className="text-lg font-medium text-gray-700 mb-2 capitalize">
                  {key.replace(/([A-Z])/g, " $1").toLowerCase()}
                </label>
                <div>
                  {renderInputField(key, value, type === "view" ? true : false)}
                </div>
              </div>
            )
        )}
        <div className="flex flex-col justify-end"></div>
        <div className="flex flex-col justify-end"></div>
        <div className="flex flex-col justify-end">
          {type === "edit" && (
            <button
              type="submit"
              className="bg-C4C585B text-white py-2 px-6 rounded shadow-md hover:bg-C7E99A3 transition duration-300"
            >
              Save Changes
            </button>
          )}
        </div>
      </form>
    </div>
  );
};

export default ProfileForm;
