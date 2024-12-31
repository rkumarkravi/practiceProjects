import { Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const ProtectedRoute = ({ children, roleRequired }) => {
  const role = useSelector((state) => state.global.globalState.type);

  if (!role) {
    return <Navigate to="/" />;
  }

  if (role !== roleRequired) {
    return <Navigate to="/unauthorized" />;
  }

  return children;
};

export default ProtectedRoute;
