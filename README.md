<h1 align="center">🚀 Social Media Backend API</h1>

<p align="center">
  <b>Spring Boot + PostgreSQL + JPA + Validation</b><br>
  A Production-Ready REST API built using Clean Layered Architecture
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Backend-SpringBoot-green?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/ORM-Hibernate-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Architecture-Layered-success?style=for-the-badge"/>
</p>

---

# 📌 Project Overview

This is a fully functional **Social Media Backend REST API** built using:

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Jakarta Validation
- Global Exception Handling

The project follows **Clean Layered Architecture**:

Controller → Service → Repository → Database

---

# 🧠 Features Implemented

✅ User Registration  
✅ Create Posts  
✅ Add Comments  
✅ Like Posts  
✅ Follow / Unfollow Users  
✅ Followers & Following Count  
✅ Input Validation  
✅ Global Exception Handling  
✅ Proper HTTP Status Codes  
✅ Unique Follow Constraint  
✅ Prevent Self Follow  

---

# 🏗️ Architecture

## 1️⃣ Controller Layer
- Handles HTTP requests
- Uses `@RestController`
- Returns `ResponseEntity`
- Uses proper HTTP methods (GET, POST, DELETE)

## 2️⃣ Service Layer
- Contains business logic
- Validates user existence
- Prevents duplicate follows
- Prevents following self
- Throws custom exceptions

## 3️⃣ Repository Layer
- Extends `JpaRepository`
- Auto-generated CRUD queries
- Custom query methods

## 4️⃣ Model Layer
- JPA Entities
- Relationship mapping
- Validation annotations

## 5️⃣ Exception Layer
- `NotFoundException`
- `BadRequestException`
- `GlobalExceptionHandler`
- Structured JSON error responses

---

# 🗄️ Database Design

## 👤 User
- id
- username (unique)
- email (unique)
- password (WRITE_ONLY)
- fullName

## 📝 Post
- id
- title
- caption
- imageUrl
- ManyToOne → User

## 💬 Comment
- id
- text
- ManyToOne → Post
- ManyToOne → User

## ❤️ Like
- id
- ManyToOne → Post
- ManyToOne → User

## 🔁 Follow
- id
- follower (User)
- following (User)
- Unique constraint (follower_id + following_id)

---

# 🔐 Validation & Security

Used:

- `@NotBlank`
- `@Email`
- `@Size`
- `@NotNull`
- `@Valid`
- `@JsonProperty(WRITE_ONLY)` for password

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/social-media-backend.git
```

## 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 3️⃣ Run Application

From Eclipse:
Right Click → Run As → Spring Boot App

Or using Maven:

```bash
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---

# 🧪 API Endpoints

## 👤 Users
POST   /users  
GET    /users  
GET    /users/email/{email}  

## 📝 Posts
POST   /posts  
GET    /posts  
GET    /posts/{id}  
GET    /posts/user/{userId}  
DELETE /posts/{id}  

## 💬 Comments
POST   /comments  
GET    /comments/post/{postId}  
GET    /comments/user/{userId}  
DELETE /comments/{id}  

## ❤️ Likes
POST   /likes  
DELETE /likes/{id}  

## 🔁 Follows
POST   /follows/follow?followerId=1&followingId=2  
DELETE /follows/unfollow?followerId=1&followingId=2  
GET    /follows/{userId}/followers-count  
GET    /follows/{userId}/following-count  

---

# 📊 HTTP Status Codes Used

- 200 OK
- 201 Created
- 400 Bad Request
- 404 Not Found
- 500 Internal Server Error

---

# 🛠️ Issues Solved During Development

✔ Fixed infinite JSON nesting depth issue  
✔ Implemented global exception handling  
✔ Fixed parameter reflection issue using `@PathVariable("name")`  
✔ Prevented duplicate follows  
✔ Prevented following self  
✔ Proper HTTP method handling  

---

# 🚀 Future Improvements

- JWT Authentication
- Role-Based Authorization
- Pagination
- DTO Layer
- Swagger Documentation
- Image Upload Feature

---

# 👨‍💻 Author

Your Name  
Java Backend Developer  

---

⭐ If you like this project, give it a star on GitHub!
