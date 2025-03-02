import { useState } from "react";
import { Link } from "react-router-dom";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";
import EBookImage from "../assets/e-book.jpg"; 
import AudioImage from "../assets/audio.jpg"; 

export default function WelcomePage({ isLoggedIn }) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  const closeSidebar = () => {
    setIsSidebarOpen(false);
  };

  return (
    <div className="flex flex-col min-h-screen">
      <AppBar toggleSidebar={toggleSidebar} isLoggedIn={isLoggedIn} />
      <Sidebar isOpen={isSidebarOpen} closeSidebar={closeSidebar} isLoggedIn={isLoggedIn} />
      <main className="flex-grow p-4 mt-16">
        <div className="container mx-auto">
          <h2 className="text-2xl font-bold text-gray-10000">Welcome to Bookworm</h2>
          <p className="mt-4 text-gray-600">This is the welcome page of the website.</p>

          {/* Boxes Section */}
          <div className="mt-8 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {/* Box 1: E-Book */}
            <Link to="/ebook" className="bg-white p-6 rounded-lg shadow-md text-center hover:shadow-lg transition-shadow">
            <img src={EBookImage} alt="E-Book" className="w-32 h-32 mx-auto rounded-full" />

              <h3 className="mt-4 text-xl font-bold text-gray-800">E-Book</h3>
              <p className="mt-2 text-red-600">Explore our collection of e-books.</p>
              <div className="mt-4">
                <span className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm">
                  E-Book
                </span>
              </div>
            </Link>

            {/* Box 2: Audio */}
            <Link to="/audio" className="bg-white p-6 rounded-lg shadow-md text-center hover:shadow-lg transition-shadow">
            <img src={AudioImage} alt="Audio" className="w-32 h-32 mx-auto rounded-full" />
              <h3 className="mt-4 text-xl font-bold text-gray-800">Audio</h3>
              <p className="mt-2 text-gray-600">Listen to our audio collection.</p>
              <div className="mt-4">
                <span className="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm">
                  Audio
                </span>
              </div>
            </Link>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
}