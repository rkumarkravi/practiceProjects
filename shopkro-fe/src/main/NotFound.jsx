import { Link } from 'react-router-dom';

const NotFound = () => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-CF4EDD3 text-center p-6">
      <h1 className="text-6xl font-bold text-C4C585B mb-4">404 !</h1>
      <h2 className="text-3xl font-semibold text-C7E99A3 mb-2">Page Not Found</h2>
      <p className="text-lg text-gray-600 mb-6">
        Oops! The page you are looking for doesn't exist or has been moved.
      </p>
      <Link
        to="/dashboard"
        className="bg-C4C585B text-white py-2 px-4 rounded-lg shadow-md hover:bg-C7E99A3 transition duration-300"
      >
        Go Back to Home
      </Link>
    </div>
  );
};

export default NotFound;
