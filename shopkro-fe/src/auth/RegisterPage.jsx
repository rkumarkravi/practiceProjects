import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../services/AxiosService";

const RegistrationPage = () => {
  const [isBuyer, setIsBuyer] = useState(true);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const formData={};
      for(let i=0;i<e.target.length;i++){
        if(e.target[i].name){
          console.log(e.target[i].name,e.target[i].value);
          formData[e.target[i].name]=e.target[i].value;
        }
      }
      console.log(formData)
      // Call the backend registration API
      const response = await axiosInstance.post(`/api/${isBuyer?'buyer':'seller'}/create`, formData);

      // Handle success
      console.log('Registration successful:', response.data);
      // navigate('/login'); // Redirect to login page after registration
    } catch (err) {
      // Handle error
      console.error('Registration failed:', err.response?.data || err.message);
      setError(err.response?.data?.message || 'Registration failed. Please try again.');
    }
  };

  return (
    <div className="bg-CF4EDD3 min-h-screen flex">
      {/* Branding Section */}
      <div className="hidden md:flex flex-col justify-center items-center w-1/2 bg-C4C585B text-white p-10">
        <h1 className="text-4xl font-bold mb-4">
          Join Our E-commerce Platform
        </h1>
        <p className="text-lg">
          Sign up now to start your journey as a Buyer or Seller.
        </p>
        {/* <img
          src="https://via.placeholder.com/300"
          alt="Branding Illustration"
          className="mt-6 w-3/4"
        /> */}
      </div>

      {/* Registration Section */}
      <div className="flex flex-col justify-center items-center w-full md:w-1/2 bg-white p-10 rounded-lg shadow-lg">
        <h1 className="text-2xl font-bold text-C4C585B mb-6">
          {isBuyer ? "Buyer Registration" : "Seller Registration"}
        </h1>

        {/* Tabs for Buyer/Seller */}
        <div className="flex justify-center mb-6">
          <button
            onClick={() => setIsBuyer(true)}
            className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none ${
              isBuyer
                ? "border-C7E99A3"
                : "border-transparent hover:border-C7E99A3"
            }`}
          >
            Buyer
          </button>
          <button
            onClick={() => setIsBuyer(false)}
            className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none ${
              !isBuyer
                ? "border-C7E99A3"
                : "border-transparent hover:border-C7E99A3"
            }`}
          >
            Seller
          </button>
        </div>

        {/* Buyer Registration Form */}
        {isBuyer && (
          <>
            <form className="w-full" onSubmit={handleRegister}>
              <div className="mb-4">
                <label htmlFor="buyer-name" className="block text-C4C585B mb-2">
                  Full Name
                </label>
                <input
                  type="text"
                  id="buyer-name"
                  name="buyerName"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your full name"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="buyer-email"
                  className="block text-C4C585B mb-2"
                >
                  Email
                </label>
                <input
                  type="email"
                  id="buyer-email"
                  name="email"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your email"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="buyer-password"
                  className="block text-C4C585B mb-2"
                >
                  Password
                </label>
                <input
                  type="password"
                  id="buyer-password"
                  name="password"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Create a password"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="buyer-phone"
                  className="block text-C4C585B mb-2"
                >
                  Mobile Number
                </label>
                <input
                  type="text"
                  id="buyer-phone"
                  name="mobNo"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your mobile number"
                />
              </div>
              <button
                type="submit"
                className="w-full bg-C7E99A3 text-white py-2 rounded-lg hover:bg-C4C585B"
              >
                Register as Buyer
              </button>
            </form>
            <p className="text-sm text-C4C585B mt-4">
              Already have an Account?{' '}
              <a href="/" className="text-CA5BFCC hover:underline">
                Login here
              </a>
            </p>
          </>
        )}

        {/* Seller Registration Form */}
        {!isBuyer && (
          <>
            <form className="w-full"  onSubmit={handleRegister}>
              <div className="mb-4">
                <label
                  htmlFor="seller-name"
                  className="block text-C4C585B mb-2"
                >
                  Full Name
                </label>
                <input
                  type="text"
                  id="seller-name"
                  name="sellerName"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your full name"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="seller-email"
                  className="block text-C4C585B mb-2"
                >
                  Email
                </label>
                <input
                  type="email"
                  id="seller-email"
                  name="email"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your email"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="seller-password"
                  className="block text-C4C585B mb-2"
                >
                  Password
                </label>
                <input
                  type="password"
                  id="seller-password"
                  name="password"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Create a password"
                />
              </div>
              <div className="mb-4">
                <label htmlFor="seller-gst" className="block text-C4C585B mb-2">
                  GST Number
                </label>
                <input
                  type="text"
                  id="seller-gst"
                  name="sellerGst"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your GST number"
                />
              </div>
              <div className="mb-4">
                <label
                  htmlFor="seller-phone"
                  className="block text-C4C585B mb-2"
                >
                  Phone Number
                </label>
                <input
                  type="text"
                  id="seller-phone"
                  name="phoneNumber"
                  required
                  className="w-full px-4 py-2 border rounded-lg text-C7E99A3 focus:ring-C7E99A3 focus:border-C7E99A3"
                  placeholder="Enter your phone number"
                />
              </div>
              <button
                type="submit"
                className="w-full bg-C7E99A3 text-white py-2 rounded-lg hover:bg-C4C585B"
              >
                Register as Seller
              </button>
            </form>
            <p className="text-sm text-C4C585B mt-4">
              Already have an Account?{' '}
              <a href="/" className="text-CA5BFCC hover:underline">
                Login here
              </a>
            </p>
            {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
          </>
        )}
      </div>
    </div>
  );
};

export default RegistrationPage;
