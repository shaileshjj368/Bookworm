import { useState, useEffect } from "react";
import AppBar from "../components/AppBar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";
import Card from "../components/Card";

const LANGUAGES_API_URL = "http://localhost:8080/api/languages/";
const GENRES_API_URL = "http://localhost:8080/api/genre/";
const PRODUCTS_API_URL = "http://localhost:8080/api/products/search";

export default function AudioPage({ addToCart, isLoggedIn }) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const [audioBooks, setAudioBooks] = useState([]);
  const [filteredAudioBooks, setFilteredAudioBooks] = useState([]);
  const [languages, setLanguages] = useState([]);
  const [genres, setGenres] = useState([]);
  const [languageFilter, setLanguageFilter] = useState("");
  const [genreFilter, setGenreFilter] = useState("");
  const [nameFilter, setNameFilter] = useState("");
  const [authorFilter, setAuthorFilter] = useState("");

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  const closeSidebar = () => {
    setIsSidebarOpen(false);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [languagesResponse, genresResponse] = await Promise.all([
          fetch(LANGUAGES_API_URL),
          fetch(GENRES_API_URL),
        ]);

        const languagesData = await languagesResponse.json();
        const genresData = await genresResponse.json();

        setLanguages(languagesData ?? []);
        setGenres(genresData ?? []);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchAudioBooks = async () => {
      try {
        const filter = {
          langId: languageFilter || null,
          genreId: genreFilter || null,
          typeId: 2, // Assuming typeId 2 is for audio books
          productName: nameFilter || null,
          authorName: authorFilter || null,
        };

        const response = await fetch(PRODUCTS_API_URL, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(filter),
        });

        const data = await response.json();
        setAudioBooks(data);
        setFilteredAudioBooks(data); // Set filteredAudioBooks directly
      } catch (error) {
        console.error("Error fetching audio books:", error);
      }
    };

    fetchAudioBooks();
  }, [languageFilter, genreFilter, nameFilter, authorFilter]);

  return (
    <div className="flex flex-col min-h-screen">
      <AppBar toggleSidebar={toggleSidebar} isLoggedIn={isLoggedIn} />
      <Sidebar
        isOpen={isSidebarOpen}
        closeSidebar={closeSidebar}
        isLoggedIn={isLoggedIn}
      />
      <main className="flex-grow p-4 mt-16">
        <div className="container mx-auto">
          <h1 className="text-3xl font-bold text-gray-800 mb-8">Audio Books</h1>

          {/* Filters */}
          <div className="mb-8">
            <input
              type="text"
              placeholder="Filter by product name"
              value={nameFilter}
              onChange={(e) => setNameFilter(e.target.value)}
              className="border p-2 mr-4"
            />
            <select
              value={languageFilter}
              onChange={(e) => setLanguageFilter(e.target.value)}
              className="border p-2 mr-4"
            >
              <option value="">All Languages</option>
              {languages.map((language) => (
                <option key={language.languageId} value={language.languageId}>
                  {language.languageDesc}
                </option>
              ))}
            </select>
            <select
              value={genreFilter}
              onChange={(e) => setGenreFilter(e.target.value)}
              className="border p-2 mr-4"
            >
              <option value="">All Genres</option>
              {genres.map((genre) => (
                <option key={genre.genreId} value={genre.genreId}>
                  {genre.genreDesc}
                </option>
              ))}
            </select>
            <input
              type="text"
              placeholder="Filter by author"
              value={authorFilter}
              onChange={(e) => setAuthorFilter(e.target.value)}
              className="border p-2"
            />
          </div>

          {/* Grid of Audio Books */}
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {filteredAudioBooks.map((audioBook) => (
              <div key={audioBook.productId} className="w-full">
                {console.log(audioBook.productId)}
                <Card
                  image={audioBook.path} // Assuming productImageUrl is the correct property for the image URL
                  title={audioBook.productEnglishName}
                  author={audioBook.author}
                  description={audioBook.productDescriptionShort}
                  price={audioBook.price}
                  onAddToCart={() => addToCart(audioBook)}
                />
              </div>
            ))}
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
}
