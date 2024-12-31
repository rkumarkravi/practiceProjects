import React from "react";
import { FaShoppingCart, FaBox, FaUser, FaSignOutAlt } from "react-icons/fa"; // Importing icons
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
function Navbar() {
  const navigate = useNavigate();
  const profile = useSelector((state) => state.global.globalState.profile);
  const type = useSelector((state) => state.global.globalState.type);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <header className="bg-C4C585B text-white p-4 shadow-md mb-1">
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
          <ul className="flex space-x-6">
            <li>
              <a
                href="#cart"
                className="flex items-center text-white cursor-pointer hover:text-gray-300"
              >
                <FaShoppingCart className="mr-2" /> Cart
              </a>
            </li>
            <li>
              <a
                href="#orders"
                className="flex items-center text-white cursor-pointer hover:text-gray-300"
              >
                <FaBox className="mr-2" /> Orders
              </a>
            </li>
            <li>
              <a
                onClick={() => {
                  navigate("profile");
                }}
                className="flex items-center text-white cursor-pointer hover:text-gray-300"
              >
                <FaUser className="mr-2" />{" "}
                {profile ? profile.name : "profile"}
              </a>
            </li>
            <li>
              <a
                onClick={() => {
                  handleLogout();
                }}
                className="flex items-center cursor-pointer text-white hover:text-gray-300"
              >
                <FaSignOutAlt className="mr-2" /> Logout
              </a>
            </li>
          </ul>
        </nav>
      </div>
      {/* <p className="text-sm">Manage your products and orders seamlessly.</p> */}
    </header>
  );
}

export default Navbar;
