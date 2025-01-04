import React, { useState, useEffect } from "react";
import {
  FaShoppingCart,
  FaBox,
  FaUser,
  FaSignOutAlt,
} from "react-icons/fa"; // Importing icons
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { toast, Toaster } from "sonner";

function Navbar() {
  const navigate = useNavigate();
  const profile = useSelector((state) => state.global.globalState.profile);
  const type = useSelector((state) => state.global.globalState.type);

  const [activeDropdown, setActiveDropdown] = useState(null);
  const [hideTimeout, setHideTimeout] = useState(null);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  useEffect(() => {
    toast(`Welcome ${profile.name} !`);
  }, []);

  // Function to toggle dropdowns
  const toggleDropdown = (menu) => {
    // Clear any existing hide timeout to prevent premature hiding
    if (hideTimeout) {
      clearTimeout(hideTimeout);
      setHideTimeout(null);
    }
    setActiveDropdown(menu);
  };

  // Function to hide dropdowns with delay
  const hideDropdown = () => {
    const timeout = setTimeout(() => {
      setActiveDropdown(null);
    }, 200); // Delay of 200ms
    setHideTimeout(timeout);
  };

  return (
    <>
      <Toaster />
      <header className="bg-C4C585B text-white p-4 shadow-md">
        <div className="flex justify-between items-center">
          <h1
            className="text-3xl font-bold cursor-pointer"
            onClick={() => {
              navigate("/dashboard");
            }}
          >
            ShopKro E-Comm {type}
          </h1>
          <nav>
            <ul className="flex space-x-6 relative">
              <li
                className="relative"
                onMouseEnter={() => toggleDropdown("cart")}
                onMouseLeave={hideDropdown}
              >
                <a
                  className="flex items-center text-white cursor-pointer hover:text-gray-300"
                >
                  <FaShoppingCart className="mr-2" /> Cart
                </a>
                {activeDropdown === "cart" && (
                  <div className="absolute right-0 mt-2 bg-white text-black rounded shadow-lg w-48">
                    <ul>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("/cart/view")}
                      >
                        View Cart
                      </li>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("/cart/history")}
                      >
                        Wishlist
                      </li>
                    </ul>
                  </div>
                )}
              </li>
              <li
                className="relative"
                onMouseEnter={() => toggleDropdown("orders")}
                onMouseLeave={hideDropdown}
              >
                <a
                  className="flex items-center text-white cursor-pointer hover:text-gray-300"
                >
                  <FaBox className="mr-2" /> Orders
                </a>
                {activeDropdown === "orders" && (
                  <div className="absolute right-0 mt-2 bg-white text-black rounded shadow-lg w-48">
                    <ul>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("/orders/current")}
                      >
                        Current Orders
                      </li>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("/orders/history")}
                      >
                        Order History
                      </li>
                    </ul>
                  </div>
                )}
              </li>
              <li
                className="relative"
                onMouseEnter={() => toggleDropdown("profile")}
                onMouseLeave={hideDropdown}
              >
                <a
                  className="flex items-center text-white cursor-pointer hover:text-gray-300"
                >
                  <FaUser className="mr-2" />{" "}
                  {profile ? profile.name : "Profile"}
                </a>
                {activeDropdown === "profile" && (
                  <div className="absolute right-0 mt-2 bg-white text-black rounded shadow-lg w-48">
                    <ul>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("profile",{state:{type:"view"}})}
                      >
                        View Profile
                      </li>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("profile",{state:{type:"edit"}})}
                      >
                        Edit Profile
                      </li>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={() => navigate("settings")}
                      >
                        Settings
                      </li>
                      <li
                        className="px-4 py-2 hover:bg-CF4EDD3 cursor-pointer"
                        onClick={handleLogout}
                      >
                        Logout
                      </li>
                    </ul>
                  </div>
                )}
              </li>
              {/* <li>
                <a
                  onClick={handleLogout}
                  className="flex items-center cursor-pointer text-white hover:text-gray-300"
                >
                  <FaSignOutAlt className="mr-2" /> Logout
                </a>
              </li> */}
            </ul>
          </nav>
        </div>
      </header>
    </>
  );
}

export default Navbar;
