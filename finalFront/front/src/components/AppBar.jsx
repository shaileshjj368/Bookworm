import { Link } from "react-router-dom";

export default function AppBar({ toggleSidebar, onLogout, isLoggedIn }) {
  return (
    <header className="bg-white shadow-md fixed top-0 left-0 right-0 z-50 w-full">
      <div className="flex items-center justify-between p-4">
        {/* Left Section: Hamburger Menu */}
        <div className="flex items-center">
          <button onClick={toggleSidebar} className="text-gray-700 focus:outline-none">
            <svg
              className="w-6 h-6"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M4 6h16M4 12h16m-7 6h7"
              ></path>
            </svg>
          </button>
        </div>

        {/* Center Section: App Name */}
        <h1 className="text-xl font-bold text-gray-800 mx-auto">BookWorm</h1>

        {/* Right Section: Icons and Buttons */}
        <div className="flex items-center space-x-4">
          {/* Cart Icon */}
          <Link to="/cart" className="text-gray-700 hover:text-gray-900">
            <svg
              className="w-6 h-6"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"
              ></path>
            </svg>
          </Link>

          {/* Login/Logout Button */}
          {isLoggedIn ? (
            <button
              onClick={onLogout}
              className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600"
            >
              Logout
            </button>
          ) : (
            <>
              <Link
                to="/login"
                className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
              >
                Login
              </Link>
              <Link
                to="/register"
                className="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600"
              >
                Sign Up
              </Link>
            </>
          )}
        </div>
      </div>
    </header>
  );
}