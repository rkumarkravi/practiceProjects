import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

function Home() {
  const routes = {
    BUYER: "/dashboard/buyer",
    SELLER: "/dashboard/seller",
  };
  const userType = useSelector((state) => state.global.globalState.type);
  const navigate = useNavigate();

  useEffect(() => {
    if (userType && routes[userType]) {
      navigate(routes[userType]); // Redirect based on role
    } else {
      navigate("/"); // Redirect to login if no role
    }
  }, [userType, navigate]);

  return null; // Render nothing
}

export default Home;
