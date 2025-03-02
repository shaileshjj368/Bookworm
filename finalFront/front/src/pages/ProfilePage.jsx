import { useState } from "react";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";

export default function ProfilePage(isLoggedIn) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  
  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  const closeSidebar = () => {
    setIsSidebarOpen(false);
  };

  const [user, setUser] = useState({
    name: "John Doe",
    email: "john@example.com",
    dob: "1990-01-01",
    pan: "ABCDE1234F",
    phoneNumber: "1234567890",
  });

  const [isEditing, setIsEditing] = useState(false);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    setIsEditing(false);
    // Save updated user details to the backend
  };

  return (
    <div className="flex flex-col min-h-screen">
      <AppBar toggleSidebar={toggleSidebar} isLoggedIn={isLoggedIn} />
                    <Sidebar isOpen={isSidebarOpen} closeSidebar={closeSidebar} isLoggedIn={isLoggedIn} />
      <main className="flex-grow p-4 mt-16">
        <div className="container mx-auto">
          <h1 className="text-3xl font-bold text-gray-800 mb-8">Profile</h1>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Name</label>
              {isEditing ? (
                <input
                  type="text"
                  value={user.name}
                  onChange={(e) => setUser({ ...user, name: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              ) : (
                <p className="text-gray-600">{user.name}</p>
              )}
            </div>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Email</label>
              {isEditing ? (
                <input
                  type="email"
                  value={user.email}
                  onChange={(e) => setUser({ ...user, email: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              ) : (
                <p className="text-gray-600">{user.email}</p>
              )}
            </div>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Date of Birth</label>
              {isEditing ? (
                <input
                  type="date"
                  value={user.dob}
                  onChange={(e) => setUser({ ...user, dob: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              ) : (
                <p className="text-gray-600">{user.dob}</p>
              )}
            </div>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">PAN</label>
              {isEditing ? (
                <input
                  type="text"
                  value={user.pan}
                  onChange={(e) => setUser({ ...user, pan: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              ) : (
                <p className="text-gray-600">{user.pan}</p>
              )}
            </div>
            <div className="mb-6">
              <label className="block text-gray-700 mb-2">Phone Number</label>
              {isEditing ? (
                <input
                  type="tel"
                  value={user.phoneNumber}
                  onChange={(e) => setUser({ ...user, phoneNumber: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              ) : (
                <p className="text-gray-600">{user.phoneNumber}</p>
              )}
            </div>
            <button
              onClick={isEditing ? handleSave : handleEdit}
              className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
            >
              {isEditing ? "Save" : "Edit"}
            </button>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
}