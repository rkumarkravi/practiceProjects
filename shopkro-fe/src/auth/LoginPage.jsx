import { useState } from "react";
import axiosInstance from "../services/AxiosService";
import { v4 as uuidv4 } from "uuid";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setKeyValue } from "../store/globalSlice";
import { toast, Toaster } from "sonner";
const LoginPage = () => {
  const [isBuyer, setIsBuyer] = useState(true);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleLogin = async (e) => {
    e.preventDefault();
    // console.log(e.target)
    try {
      const formData = {};
      for (let i = 0; i < e.target.length; i++) {
        if (e.target[i].name) {
          // console.log(e.target[i].name,e.target[i].value);
          formData[e.target[i].name] = e.target[i].value;
        }
      }
      formData.type = isBuyer ? "BUYER" : "SELLER";
      // console.log(formData)
      // Call the backend registration API
      const response = await axiosInstance.post(`/auth/login`, formData);

      // Handle success
      // console.log('Login successful:', response);
      if (response.status === 200) {
        localStorage.setItem("t", response.data.token);
        localStorage.setItem("tkn", uuidv4());
        localStorage.setItem(
          "auth",
          "eyJhbG" + uuidv4().replaceAll("-", "") + uuidv4().replaceAll("-", "")
        );
        dispatch(
          setKeyValue({ key: "profile", value: response.data.profileInfo })
        ); // Store profile globally
        dispatch(setKeyValue({ key: "t", value: response.data.token })); // Store token globally
        dispatch(setKeyValue({ key: "type", value: response.data.type }));
      }
      navigate("/dashboard"); // Redirect to login page after login
    } catch (err) {
      // Handle error
      toast("Login failed : " + (err.response?.data || err.message));
      console.error("Login failed:", err.response?.data || err.message);
    }
  };

  return (
    <>
      <Toaster />
      <div className="bg-CF4EDD3 min-h-screen flex">
        {/* Branding Section */}
        <div className="hidden md:flex flex-col justify-center items-center w-1/2 bg-C4C585B text-white p-10">
          <h1 className="text-4xl font-bold mb-4">
            Welcome to Our E-commerce Platform
          </h1>
          <p className="text-lg">
            Experience the best buying and selling platform tailored just for
            you.
          </p>
        </div>

        {/* Login/Register Section */}
        <div className="flex flex-col justify-center items-center w-full md:w-1/2 bg-white p-10 rounded-lg shadow-lg">
          <h1 className="text-2xl font-bold text-C4C585B mb-6">
            {isBuyer ? "Buyer Login" : "Seller Login"}
          </h1>

          {/* Tabs for Buyer/Seller */}
          <div className="flex justify-center mb-6">
            <button
              onClick={() => setIsBuyer(true)}
              className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none font-bold ${
                isBuyer
                  ? "border-C7E99A3"
                  : "border-transparent hover:border-C7E99A3"
              }`}
            >
              Buyer
            </button>
            <button
              onClick={() => setIsBuyer(false)}
              className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none font-bold ${
                !isBuyer
                  ? "border-C7E99A3"
                  : "border-transparent hover:border-C7E99A3"
              }`}
            >
              Seller
            </button>
          </div>

          {/* Buyer/Seller Form */}
          <form className="w-full" onSubmit={handleLogin}>
            <div className="mb-4">
              <label htmlFor="email" className="block text-C4C585B mb-2">
                Email
              </label>
              <input
                type="email"
                id="email"
                name="email"
                className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                placeholder="Enter your email"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="password" className="block text-C4C585B mb-2">
                Password
              </label>
              <input
                type="password"
                id="password"
                name="password"
                className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                placeholder="Enter your password"
              />
            </div>
            <button
              type="submit"
              className="w-full bg-C7E99A3 text-white py-2 rounded-lg hover:bg-C4C585B"
            >
              Login
            </button>
          </form>
          <p className="text-sm text-C4C585B mt-4">
            Don&apos;t have an account?{" "}
            <a href="/register" className="text-CA5BFCC hover:underline">
              Register here
            </a>
          </p>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
