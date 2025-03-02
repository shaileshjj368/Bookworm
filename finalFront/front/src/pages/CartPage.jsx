import { useState, useEffect } from "react";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";
import { Navigate, useNavigate } from "react-router-dom";

export default function CartPage({ cartItems, removeFromCart, proceedToPayment, isLoggedIn}) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const [selections, setSelections] = useState({});
  const [totalPrice, setTotalPrice] = useState(0);
  const navigate = useNavigate();

  const toggleSidebar = () => setIsSidebarOpen(!isSidebarOpen);
  const closeSidebar = () => setIsSidebarOpen(false);

  if (!isLoggedIn) return <Navigate to="/login" />;

  const handleSelectionChange = (productId, type) => {
    setSelections((prev) => ({
      ...prev,
      [productId]: { ...prev[productId], type, days: type === "rent" ? 1 : null },
    }));
  };

  const handleDaysChange = (productId, days) => {
    setSelections((prev) => ({
      ...prev,
      [productId]: { ...prev[productId], days: parseInt(days, 10) || 1 },
    }));
  };

  useEffect(() => {
    let total = 0;
    cartItems.forEach((item) => {
      const selection = selections[item.productId] || {};
      if (selection.type === "rent") {
        total += (item.productBasePrice / 10) * (selection.days || 1);
      } else {
        total += item.productBasePrice;
      }
    });
    setTotalPrice(total);
  }, [cartItems, selections]);

  // const handleRemove = (productId) => {
  //   setCartItems(cartItems.filter((item) => item.productId !== productId));
  // };

  // const handlePayment = () => {
  //   cartItems.forEach((item) => {
  //     addToMyShelf(item);
  //   });
  //   setCartItems([]); // Clear the cart after payment
  //   navigate("/myshelf");
  // };

  const handleProceedToPayment = () => {
    proceedToPayment(); // Clear the cart and show success toast
    navigate("/myshelf"); // Redirect to My Shelf
  };

  return (
    <div className="flex flex-col min-h-screen">
      <AppBar toggleSidebar={toggleSidebar} isLoggedIn={isLoggedIn} />
      <Sidebar isOpen={isSidebarOpen} closeSidebar={closeSidebar} isLoggedIn={isLoggedIn} />
      <main className="flex-grow p-4 mt-16">
        <div className="container mx-auto">
          <h1 className="text-3xl font-bold text-gray-800 mb-8">Your Cart</h1>
          {cartItems.length === 0 ? (
            <p className="text-gray-600">Your cart is empty.</p>
          ) : (
            <div className="space-y-4">
              {cartItems.map((item) => (
                <div key={item.productId} className="bg-white p-4 rounded-lg shadow-md">
                  <div className="flex items-center justify-between">
                    <div>
                      <h3 className="text-xl font-bold text-gray-800">{item.productEnglishName}</h3>
                      <p className="text-gray-600">Price: ${item.productBasePrice}</p>
                      <p className="text-gray-600">{item.productDescriptionShort}</p>
                    </div>
                    <div className="flex flex-col items-end space-y-2">
                      {/* Dropdown */}
                      <select
                        className="border rounded-lg px-2 py-1"
                        onChange={(e) => handleSelectionChange(item.productId, e.target.value)}
                        value={selections[item.productId]?.type || "purchase"}
                      >
                        <option value="purchase">Purchase</option>
                        <option value="rent">Rent</option>
                      </select>
                      {/* Rental Days Input */}
                      {selections[item.productId]?.type === "rent" && (
                        <input
                          type="number"
                          min="1"
                          className="border rounded-lg px-2 py-1 w-20"
                          value={selections[item.productId]?.days || 1}
                          onChange={(e) => handleDaysChange(item.productId, e.target.value)}
                        />
                      )}
                      {/* Remove Button */}
                      <button
                        onClick={() => removeFromCart(item.productId)}
                        className="text-red-500 hover:text-red-700 font-medium px-3 py-1 border border-red-500 rounded-lg"
                      >
                        Remove
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
          {/* Total Price & Payment Button */}
          {cartItems.length > 0 && (
            <div className="mt-8 p-4 bg-gray-100 rounded-lg shadow-md flex justify-between items-center">
              <h2 className="text-xl font-bold text-gray-800">Total Price: ${totalPrice.toFixed(2)}</h2>
              <button 
                onClick={handleProceedToPayment}
                className="bg-blue-500 text-white font-semibold px-4 py-2 rounded-lg hover:bg-blue-600"
              >
                Proceed to Payment
              </button>
            </div>
          )}
        </div>
      </main>
      <Footer />
    </div>
  );
}
