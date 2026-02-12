# 🚚 Smart Courier Management System

An API-based backend platform for managing courier delivery services efficiently.  
The system supports multiple user roles and enables real-time delivery tracking and parallel task assignment using multithreading.

---

## 🎯 Objective

To develop a scalable courier management system that:

- Handles multiple user roles (Admin, Manager, Delivery Agent, Customer)
- Supports real-time delivery status updates
- Assigns delivery tasks efficiently using multithreading
- Manages courier operations through REST APIs

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST APIs
- Multithreading (ExecutorService)
- @Scheduled Tasks

---

## 👥 User Roles & Responsibilities

### 🔹 Admin
- Create and manage users
- Assign roles
- Monitor system activities

### 🔹 Manager
- Assign delivery tasks to agents
- Monitor delivery progress
- Manage bulk uploads of packages

### 🔹 Delivery Agent
- View assigned deliveries
- Update delivery status (Pending, In-Transit, Delivered)
- Update current location

### 🔹 Customer
- Place courier orders
- Track package status
- View delivery history

---

## 🗄 Database Design

### 📌 Entities

- **Users**
- **Orders**
- **Packages**
- **Locations**
- **DeliveryAssignments**

### 🔁 Relationship

- DeliveryAssignments → Many-to-Many between:
  - Delivery Agents
  - Packages
- Includes timestamps for:
  - Assigned Time
  - Delivered Time

---

## ⚡ Multithreading Use Cases

### 1️⃣ Parallel Package Assignment

- Bulk package uploads
- Packages assigned to available delivery agents using:
  - ExecutorService
  - ThreadPool

Improves performance and reduces processing time.

---

### 2️⃣ Real-Time Delivery Simulation

- Uses `@Scheduled` annotation
- Runs every X minutes
- Simulates:
  - Location updates
  - Status change (In Transit → Delivered)
- Executes using background threads

---

## ⚙️ Configuration

Add in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/courierdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ▶️ How to Run
```
bash
Copy code  
git clone https://github.com/parth3git/smart-courier-MS.git
cd smart-courier-management
mvn spring-boot:run
```
Application runs on:
http://localhost:8080

# 📈 Key Features Implemented
- Role-Based Authentication
- RESTful API Architecture
- Layered Design (Controller → Service → Repository)
- Multithreading for task assignment
- Scheduled background updates
- Real-time status simulation
- Scalable database structure
