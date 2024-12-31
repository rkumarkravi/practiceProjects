import {Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import { Toaster } from "sonner";

function Dashboard() {
  // const profile = useSelector((state) => state.global.globalState.profile);

  return (
    <>
      <Navbar />
      <Outlet />
    </>
  );
}

export default Dashboard;
