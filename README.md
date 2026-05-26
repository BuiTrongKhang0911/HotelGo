# HotelGo Backend

Microservices architecture for hotel booking system using Spring Boot and Spring Cloud Gateway.

## Architecture

- **API Gateway** (Port 8080) - Routes requests to microservices
- **Auth Service** (Port 8081) - User authentication and authorization
- **Common Library** - Shared utilities (JWT, exceptions, DTOs)

## Tech Stack

- Java 21
- Spring Boot 3.2.0
- Spring Cloud Gateway
- Spring Security
- PostgreSQL 15
- Redis 7
- Docker & Docker Compose
- Maven

## Quick Start

### Prerequisites

- Docker & Docker Compose
- Java 21 (for local development)
- Maven 3.9+ (for local development)

### Run with Docker

```bash
# Start all services
docker-compose up -d --build

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes (clean database)
docker-compose down -v
```

### Access Services

- **API Gateway**: http://localhost:8080
- **Auth Service**: http://localhost:8081
- **PostgreSQL**: localhost:5432
- **Redis**: localhost:6379

## API Endpoints

### Register User

**POST** `http://localhost:8080/api/auth/register`

**Request Body:**
```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "testuser"
}
```

## Database

PostgreSQL container automatically creates 3 databases:
- `auth_db` - Auth service database
- `hotel_db` - Hotel service database (future)
- `booking_db` - Booking service database (future)

**Default credentials:**
- Username: `admin`
- Password: `secretpassword`

## Configuration

Copy `application.yml.example` to `application.yml` and update:
- Database credentials
- JWT secrets
- Service ports

## Project Structure

```
HotelGo-Backend/
├── common-lib/          # Shared utilities
├── api-gateway/         # API Gateway service
├── auth-service/        # Authentication service
├── infra/
│   └── db-init/        # Database initialization scripts
├── Dockerfile          # Multi-stage build for all services
├── docker-compose.yml  # Docker orchestration
└── pom.xml            # Parent POM
```

## Development

### Build locally

```bash
mvn clean package -DskipTests
```

### Run specific service

```bash
# Auth service
mvn spring-boot:run -pl auth-service

# API Gateway
mvn spring-boot:run -pl api-gateway
```

## License

MIT
