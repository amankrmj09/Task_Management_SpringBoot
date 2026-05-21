<div align="center">

# 📋 Task Management API

A robust **RESTful Task Management System** built with **Spring Boot**, featuring full CRUD operations, persistent storage with PostgreSQL, and containerized deployment via Docker.

[![Java](https://img.shields.io/badge/Java-25+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17+-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com)

</div>

## Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Folder Structure](#folder-structure)
- [Environment Variables](#environment-variables)
- [API Overview](#api-overview)
- [How to Run Locally](#how-to-run-locally)
- [Running with Docker](#running-with-docker)

## Features
- JWT-based Authentication & Authorization
- Role-based Access Control (Admin, Project Owner, Member)
- User, Project, and Task Management
- Join Request handling for Projects

## Tech Stack
- **Framework**: Spring Boot 3.x
- **Language**: Java
- **Build Tool**: Gradle
- **Database**: PostgreSQL / MySQL (configurable via JDBC)
- **Security**: Spring Security & JWT

## Folder Structure
```text
src/
├── main/             # Contains all application source code
│   ├── java/org/devofblue/task_management_springboot/
│   │   ├── config/       # Security, CORS, and application configurations
│   │   ├── controller/   # REST API endpoints handling HTTP requests
│   │   ├── dto/          # Data Transfer Objects (request/response models)
│   │   ├── entity/       # Database Entities (JPA Models)
│   │   ├── enums/        # Enumerations for roles, statuses, etc.
│   │   ├── exception/    # Custom exceptions and global error handling
│   │   ├── repository/   # Database access interfaces (Spring Data JPA)
│   │   ├── security/     # JWT filters, providers, and authentication logic
│   │   └── service/      # Business logic interfaces and implementations (impl)
│   └── resources/    # Application configurations (application.yaml)
└── test/             # Unit and integration tests
```

## Environment Variables
The application requires the following environment variables to be set up. You can pass them via your environment or a `.env` file.

```env
APP_ADMIN_EMAIL=admin@example.com
APP_ADMIN_NAME="Admin User"
APP_ADMIN_PASSWORD=securepassword
APP_ADMIN_SEED_ENABLED=true
CORS_ALLOWED_ORIGINS=http://localhost:5173
DB_URL=jdbc:mysql://localhost:3306/task_db
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRET=your_jwt_secret_key_here
SPRING_PROFILES_ACTIVE=dev
```
*Note: The application includes 3 configuration files in `src/main/resources`: `application.yaml` (default), `application-dev.yaml`, and `application-prod.yaml`.*

## API Overview
Base path: `/api`

### Auth (`/api/auth`)
- `POST /register` - Register a new user.
- `POST /login` - Log in and receive access/refresh tokens.
- `POST /refresh` - Refresh access token (body: `refreshToken`).
- `POST /logout` - Revoke refresh token (body: `refreshToken`).

### Users (`/api/users`)
- `GET /me` - Get current user profile.
- `PUT /me` - Update current user (name, password).
- `GET /` - List users (admin only, pageable).
- `PUT /{userId}/role` - Update user role (admin only, body: `role`).
- `DELETE /{userId}` - Delete user (admin only).

### Projects (`/api/projects`)
- `POST /` - Create project (admin only).
- `GET /` - List projects for current user (pageable).
- `GET /{projectId}` - Get project by id.
- `PUT /{projectId}` - Update project (admin only, body: `name`, `description`, `status`).
- `POST /{projectId}/members` - Add member (admin only, body: `userId`, `projectRole`).
- `DELETE /{projectId}/members/{userId}` - Remove member (admin only).
- `DELETE /{projectId}` - Delete project (admin only).

### Tasks (`/api/projects/{projectId}/tasks`)
- `POST /` - Create task.
- `GET /` - List tasks with filters (Query: `status`, `assigneeId`, `priority`, `overdue`, pagination).
- `GET /{taskId}` - Get task by id.
- `PUT /{taskId}` - Update task.
- `PATCH /{taskId}/status` - Update task status.
- `POST /{taskId}/comments` - Add comment (body: `text`).
- `DELETE /{taskId}/comments/{commentId}` - Delete comment.
- `DELETE /{taskId}` - Delete task (admin only).

### Join Requests (`/api/projects/{projectId}/join-requests`)
- `POST /` - Request to join a project (authenticated users).
- `GET /` - List join requests (project owners or system admins).
- `PATCH /{requestId}` - Approve or reject a join request (project owners or admins, body: `status`).

### Dashboard (`/api/dashboard`)
- `GET /my` - Current user dashboard.
- `GET /admin` - Admin dashboard (admin only).
- `GET /projects/{projectId}/stats` - Project stats.

## How to Run Locally

1. **Clone the repository** and navigate to this folder.
2. **Configure environment variables** (or rely on `application-dev.yaml`).
3. **Run using Gradle**:
   ```bash
   ./gradlew bootRun
   ```
4. The server will start on the configured port (default `8080`).

## Running with Docker

1. **Build the Docker image**:
   ```bash
   docker build -t task-management-backend .
   ```
2. **Run the Docker container** (pass environment variables via `--env-file` or `-e`):
   ```bash
   docker run -p 8080:8080 --env-file .env task-management-backend
   ```
