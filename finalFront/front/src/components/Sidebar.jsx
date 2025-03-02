
export default function Sidebar({ isOpen, closeSidebar, isLoggedIn }) {
  return (
    <div
      className={`fixed top-0 left-0 h-full w-64 bg-white shadow-lg transform transition-transform duration-300 ease-in-out z-50 ${
        isOpen ? "translate-x-0" : "-translate-x-full"
      }`}
    >
      <div className="p-4">
        <button onClick={closeSidebar} className="text-gray-700 focus:outline-none">
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
              d="M6 18L18 6M6 6l12 12"
            ></path>
          </svg>
        </button>
        <nav className="mt-4">
          <ul className="space-y-2">
            {isLoggedIn ? (
              <>
                <li>
                  <a href="profile" className="text-gray-700 hover:text-gray-900">
                    Profile
                  </a>
                </li>
                <li>
                  <a href="/myshelf" className="text-gray-700 hover:text-gray-900">
                    Myself
                  </a>
                </li>
                <li>
                  <a href="/jukebox" className="text-gray-700 hover:text-gray-900">
                    Jukebox
                  </a>
                </li>
                <li>
                  <a href="aboutus" className="text-gray-700 hover:text-gray-900">
                    About Us
                  </a>
                </li>
                <li>
                  <a href="#" className="text-gray-700 hover:text-gray-900">
                    Contact
                  </a>
                </li>
              </>
            ) : (
              <>
                <li>
                  <a href="login" className="text-gray-700 hover:text-gray-900">
                    Home
                  </a>
                </li>
                <li>
                  <a href="aboutus" className="text-gray-700 hover:text-gray-900">
                    About
                  </a>
                </li>
                <li>
                  <a href="#" className="text-gray-700 hover:text-gray-900">
                    Contact
                  </a>
                </li>
              </>
            )}
          </ul>
        </nav>
      </div>
    </div>
  );
}