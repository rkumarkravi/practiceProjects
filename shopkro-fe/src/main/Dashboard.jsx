import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect } from "react";
import axiosInstance from "../services/AxiosService";
import { useDispatch } from "react-redux";
import { setKeyValue } from "../store/globalSlice";

function Dashboard() {
  const dispatch = useDispatch();
  // const profile = useSelector((state) => state.global.globalState.profile);

  useEffect(() => {
    const fetchInitData = async () => {
      try {
        const response = await axiosInstance.post("/api/init/");
        if (response.status === 200) {
          dispatch(setKeyValue({ key: "init", value: response.data }));
        }
      } catch (err) {
        console.error(err.response?.data || err.message);
      }
    };
    fetchInitData();
  }, []);

  return (
    <>
      <Navbar />
      <Outlet />
    </>
  );
}

export default Dashboard;
