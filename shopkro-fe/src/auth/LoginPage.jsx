import React, { useState } from 'react';

const LoginPage = () => {
  const [isBuyer, setIsBuyer] = useState(true);

  return (
    <div className="bg-CF4EDD3 min-h-screen flex">
      {/* Branding Section */}
      <div className="hidden md:flex flex-col justify-center items-center w-1/2 bg-C4C585B text-white p-10">
        <h1 className="text-4xl font-bold mb-4">Welcome to Our E-commerce Platform</h1>
        <p className="text-lg">Experience the best buying and selling platform tailored just for you.</p>
      </div>

      {/* Login/Register Section */}
      <div className="flex flex-col justify-center items-center w-full md:w-1/2 bg-white p-10 rounded-lg shadow-lg">
        <h1 className="text-2xl font-bold text-C4C585B mb-6">
          {isBuyer ? 'Buyer Login' : 'Seller Login'}
        </h1>

        {/* Tabs for Buyer/Seller */}
        <div className="flex justify-center mb-6">
          <button
            onClick={() => setIsBuyer(true)}
            className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none font-bold ${
              isBuyer ? 'border-C7E99A3' : 'border-transparent hover:border-C7E99A3'
            }`}
          >
            Buyer
          </button>
          <button
            onClick={() => setIsBuyer(false)}
            className={`px-4 py-2 text-C4C585B border-b-2 focus:outline-none font-bold ${
              !isBuyer ? 'border-C7E99A3' : 'border-transparent hover:border-C7E99A3'
            }`}
          >
            Seller
          </button>
        </div>

        {/* Buyer/Seller Form */}
        <form className="w-full">
          <div className="mb-4">
            <label htmlFor="email" className="block text-C4C585B mb-2">
              Email
            </label>
            <input
              type="email"
              id="email"
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
          Don&apos;t have an account?{' '}
          <a href="/register" className="text-CA5BFCC hover:underline">
            Register here
          </a>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
