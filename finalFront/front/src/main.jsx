import React, { useState, useCallback, useMemo, useEffect } from "react";
import ReactDOM from "react-dom/client";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import App from "./App";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import HomePage from "./pages/HomePage";
import EBookPage from "./pages/EBookPage";
import AudioPage from "./pages/AudioPage";
import CartPage from "./pages/CartPage";
import ProfilePage from "./pages/ProfilePage";
import AboutUs from "./pages/AboutUs";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./index.css";
import Myshelf from "./pages/Myshelf";

function Main() {
  const [isLoggedIn, setIsLoggedIn] = useState(
    !!localStorage.getItem("authToken")
  );
  const [cartItems, setCartItems] = useState([]);
  const [myshelfItems, setMyshelfItems] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogin = useCallback((token) => {
    localStorage.setItem("authToken", token);
    setIsLoggedIn(true);
    console.log(isLoggedIn);

    console.log("logged in");
  }, []);

  const handleLogout = useCallback(() => {
    setIsLoggedIn(false);
    localStorage.removeItem("authToken");
    localStorage.removeItem("customer");
  }, []);

  const addToCart = useCallback((item) => {
    setCartItems((prevItems) => {
      if (prevItems.some((cartItem) => cartItem.id === item.id)) {
        toast.info(`${item.productEnglishName} is already in the cart!`, {
          position: "bottom-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
        });
        return prevItems; 
      }
      toast.success(`${item.productEnglishName} added to cart!`, {
        position: "bottom-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
      });
      return [...prevItems, item]; 
    });
  }, []);

  const removeFromCart = useCallback((id) => {
    setCartItems((prevItems) => prevItems.filter((item) => item.id !== id));
  }, []);

  const proceedToPayment = useCallback(() => {
    setMyshelfItems((prev) =>{
       const updatedMyshelf = [...prev, ...cartItems];
    return updatedMyshelf;
  });
    setCartItems([]);
    toast.success("Payment successful! Books added to My Shelf.", {
      position: "bottom-right",
      autoClose: 3000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
    });
  }, []);

  useEffect(() => {
    const savedMyshelf = JSON.parse(localStorage.getItem("myshelfItems")) || [];
    setMyshelfItems(savedMyshelf);
  }, []);
  
  useEffect(() => {
    localStorage.setItem("myshelfItems", JSON.stringify(myshelfItems));
  }, [myshelfItems]);

  const routes = useMemo(
    () => (
      <Routes>
        <Route
          path="/"
          element={
            isLoggedIn ? (
              <HomePage onLogout={handleLogout} isLoggedIn={isLoggedIn} />
            ) : (
              <App isLoggedIn={isLoggedIn} />
            )
          }
        />
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
        <Route path="/register" element={<SignupPage />} />
        <Route path="/profile" element={<ProfilePage isLoggedIn = {isLoggedIn}/>} />
        <Route path="/aboutus" element={<AboutUs />} />
        <Route path="/myshelf" element={<Myshelf myshelfItems={myshelfItems} isLoggedIn={isLoggedIn} />}/>
        <Route
          path="/ebook"
          element={<EBookPage addToCart={addToCart} isLoggedIn={isLoggedIn} />}
        />
        <Route
          path="/audio"
          element={<AudioPage addToCart={addToCart} isLoggedIn={isLoggedIn} />}
        />
        <Route
          path="/cart"
          element={
            isLoggedIn ? (
              <CartPage
                cartItems={cartItems}
                removeFromCart={removeFromCart}
                proceedToPayment={proceedToPayment}
                isLoggedIn={isLoggedIn}
              />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
      </Routes>
    ),
    [
      isLoggedIn,
      handleLogout,
      handleLogin,
      addToCart,
      removeFromCart,
      proceedToPayment,
      cartItems,
      myshelfItems,
    ]
  );

  return (
    <>
      <ToastContainer />
      <Router>{routes}</Router>
    </>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Main />
  </React.StrictMode>
);
