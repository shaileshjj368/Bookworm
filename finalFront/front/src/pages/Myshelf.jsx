import { useState } from "react";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";

export default function Myshelf({ myshelfItems, isLoggedIn }) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  
    const toggleSidebar = () => {
      setIsSidebarOpen(!isSidebarOpen);
    };
  
    const closeSidebar = () => {
      setIsSidebarOpen(false);
    };
    // console.log(myshelfItems);
    return (
      <div className="flex flex-col min-h-screen">
        <AppBar toggleSidebar={toggleSidebar} isLoggedIn={isLoggedIn} />
        <Sidebar isOpen={isSidebarOpen} closeSidebar={closeSidebar} isLoggedIn={isLoggedIn} />
        <main className="flex-grow p-4 mt-16">
          <div className="container mx-auto">
            <h1 className="text-3xl font-bold text-gray-800 mb-8">My Shelf</h1>
            {myshelfItems.length === 0 ? (
              <p className="text-gray-600">Your shelf is empty.</p>
            ) : (
              <div className="space-y-4">
                {myshelfItems.map((item) => (
                  <div key={item.id} className="bg-white p-4 rounded-lg shadow-md">
                    <h3 className="text-xl font-bold text-gray-800">{item.productEnglishName}</h3>
                    <p className="text-gray-600">Price: ${item.price}</p>
                    <p className="text-gray-600">{item.productDescriptionShort}</p>
                  </div>
                ))}
              </div>
            )}
          </div>
        </main>
        <Footer />
      </div>
    );
  }