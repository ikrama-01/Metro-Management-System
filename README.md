# 🚇 Metro Management System

Efficient, secure, and user-friendly metro ticketing and wallet system — powered by **Java Spring Boot** and **MySQL**.

---

## 📘 Project Overview

The Metro Management System is a backend-driven application that enables:
- ✅ Metro ticket booking with route & fare management
- ✅ Wallet-based fare payments
- ✅ Admin & User role operations
- ✅ Secure JWT authentication

This system is built for scalability, clean architecture, and easy future integration with a frontend (React in progress).

---

## 🧰 Tech Stack

- **Java (Spring Boot)** – REST APIs, layered backend
- **MySQL** – Relational DB for users, routes, tickets
- **Spring Security + JWT** – Auth & role management
- **React** – (Under Development) frontend integration

---

## 🔑 Features

- 🔐 **JWT Authentication** – Secure login and access control  
- 👥 **Role-based Access** – Admin/User separation  
- 🎟️ **Ticket Booking** – With route selection & fare handling  
- 💸 **Wallet System** – Recharge & auto fare deduction  
- ⚙️ **Admin Panel** – Manage routes, stations, fares, and view ticket logs  
- 📦 **Modular Structure** – MVC + DTO + Service/Repository layers  

---

## 🧩 Database Schema

```sql
users(id, username, email, password, role, wallet_balance)
stations(id, name, code)
routes(id, source_id, destination_id, fare, distance)
tickets(id, user_id, source_id, destination_id, fare, created_at)
```

- Foreign keys used for associations  
- Wallet updates during bookings/recharges  
- Ticket logs timestamped via `created_at`  

---

## 🔐 Security Implementation

- JWT token-based authentication  
- Password hashing using industry standards  
- Role-based access (Admin/User)  
- Secure endpoints for sensitive operations  

---

## 🚀 Future Enhancements

- 🖼️ React-based frontend UI  
- 📲 QR code ticketing + PDF generation  
- 📊 Admin dashboard with analytics  
- 💳 Integration with UPI/payment gateways  

---

## 🛠️ Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/metro-management-system.git
cd metro-management-system
```

### 2. Configure Database

- Create a MySQL database (e.g., `metro_db`)
- Update DB credentials in `application.properties`

### 3. Run the Spring Boot app

```bash
cd backend
./mvnw spring-boot:run
```

---

