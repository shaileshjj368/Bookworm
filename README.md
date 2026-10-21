# Bookworm

An online store for e-books and audiobooks where readers can **buy or rent** titles and keep them on a personal digital shelf. Built as my final project for the **PG-DAC course at C-DAC**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.8-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![React](https://img.shields.io/badge/React-18-61dafb)
![Vite](https://img.shields.io/badge/Vite-6-646cff)

## Features

- **Accounts:** register and log in.
- **Catalogue:** separate pages for e-books and audiobooks.
- **Search and filters:** combine title, author, language, genre and format in one search, served by a single dynamic SQL query.
- **Buy or rent:** rentable titles have a per-day price and a minimum rental period; the cart total updates as items are added.
- **Checkout:** creates an invoice with a line per item, marks each line as a purchase or a rental, then opens a fresh cart.
- **My Shelf:** bought and rented titles are added to the user's shelf; rentals record their start and end dates.
- **Discounts:** an admin endpoint sets an offer price on a product.
- **Royalties:** the data model tracks authors, beneficiaries and royalty calculations per sale.

## Tech stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.3.8, Spring Web, Spring Data JPA (Hibernate), Bean Validation, Lombok |
| Database | MySQL 8 |
| Frontend | React 18, Vite 6, Tailwind CSS 4, React Router 7, React Toastify |
| Build | Maven (wrapper included), npm |

## Project structure

```
Bookworm/
├── bookworm/                  # Spring Boot backend
│   ├── pom.xml
│   └── src/main/java/com/vita/
│       ├── Models/            # 18 JPA entities (products, carts, invoices, shelf, rentals, royalties)
│       └── bookworm/
│           ├── Controllers/   # REST controllers
│           ├── Service/       # business logic
│           ├── Repository/    # Spring Data JPA repositories
│           ├── DTO/           # request objects (login, cart, search filters, discount)
│           └── Config/        # CORS configuration
└── finalFront/front/          # React + Vite frontend
    └── src/
        ├── pages/             # Welcome, Home, Login, Signup, E-books, Audiobooks, Cart, My Shelf, Profile, About
        └── components/        # AppBar, Sidebar, Card, Footer
```

## How checkout works

1. The user adds titles to their active cart, choosing **buy** or **rent** (with the number of days) for each.
2. `GET /user/checkout/{custId}` generates an invoice and one invoice line per cart item.
3. Every item is copied to the user's shelf. Rentals also get a rental record with an end date.
4. The cart is closed and a new, empty cart is created for the user.

## Getting started

### Prerequisites

- JDK 17
- MySQL 8
- Node.js 18 or newer

### 1. Database

```sql
CREATE DATABASE bookworm;
```

Hibernate creates and updates the tables on startup (`spring.jpa.hibernate.ddl-auto=update`).

### 2. Backend

Set your MySQL username and password in `bookworm/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookworm
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

Then run:

```bash
cd bookworm
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

The API starts on `http://localhost:8080`.

### 3. Frontend

```bash
cd finalFront/front
npm install
npm run dev
```

Open `http://localhost:5173`. The backend allows requests from ports 5173 and 5174 (see `Config/WebConfig.java`).

## API endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/register` | Register a new customer |
| POST | `/login` | Log in |
| GET | `/api/products` | List all products |
| POST | `/api/products/search` | Search with any of `productName`, `authorName`, `langId`, `genreId`, `typeId` (1 = e-book, 2 = audiobook) |
| GET | `/api/languages/` | List languages |
| GET | `/api/genre/` | List genres |
| GET | `/api/mycart/{id}` | Get the items in a cart |
| POST | `/api/mycart/Cart` | Add a product to the cart (buy or rent) |
| DELETE | `/api/mycart/{cartId}/Product/{prodId}` | Remove a product from the cart |
| GET | `/user/checkout/{custId}` | Check out the active cart |
| GET | `/api/myshelf/{id}` | Get the products on a shelf |
| POST | `/admin/api/productDiscount` | Set an offer price on a product |

Example search request:

```json
POST /api/products/search
{
  "productName": "alchemist",
  "authorName": null,
  "langId": 1,
  "genreId": null,
  "typeId": 1
}
```

## Author

**Shailesh Joshi**
[LinkedIn](https://www.linkedin.com/in/shaileshjoshi368) · [GitHub](https://github.com/shaileshjj368)
