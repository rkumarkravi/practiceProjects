import React, { useEffect, useState } from "react";
import axiosInstance from "../../services/AxiosService";
import Navbar from "../Navbar";
import { toast } from "sonner";

const BuyerDashboard = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch products from the API
    const fetchProducts = async () => {
      try {
        const response = await axiosInstance.get("/api/product/");
        setProducts(response.data);
      } catch (err) {
        setError("Failed to fetch products. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  return (
    <div className="bg-CF4EDD3 min-h-screen">
      <main>
        {loading ? (
          <p className="text-center text-C4C585B">Loading products...</p>
        ) : error ? (
          <p className="text-center text-red-500">{error}</p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-2 p-2">
            {products.map((product) => (
              <div
                key={product.id}
                className="bg-white p-4 rounded-md shadow-md"
              >
                <img
                  src={product.imageUrl}
                  alt={product.name}
                  className="w-full h-40 object-cover rounded-t-lg mb-4"
                />
                <h2 className="text-xl font-bold text-C4C585B mb-2">
                  {product.name}
                </h2>
                <p className="text-gray-600 mb-2">{product.description}</p>
                <p className="text-C7E99A3 font-bold">
                  Price: ${product.price}
                </p>
              </div>
            ))}
          </div>
        )}
      </main>
    </div>
  );
};

export default BuyerDashboard;
