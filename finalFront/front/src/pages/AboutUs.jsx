import { useState } from "react";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";

export default function AboutUs(isLoggedIn) {
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
            <h1 className="text-3xl font-bold text-gray-800 mb-8">About Us</h1>
            <p className="text-gray-600">
            Welcome to Bookworm, a platform designed to revolutionize the way book lovers discover, organize, and explore their favorite reads. Our mission is to create a seamless and engaging experience for readers by providing a user-friendly interface and powerful backend services.

The project is led by Saurabh, ensuring a clear vision and smooth execution. The robust backend, powered by Spring Boot, is developed by Rohan and Pritam, ensuring high performance and scalability. The intuitive and dynamic frontend is crafted by Shailesh using modern web technologies to deliver an exceptional user experience.

Together, we are dedicated to making Bookworm a go-to platform for every book enthusiast. Stay tuned as we continue to innovate and enhance the reading experience!
            </p>
          </div>
        </main>
        <Footer />
      </div>
    );
  }