# ARTLAS-API

REST API for managing artists, artworks, and museums, built with Spring Boot and JWT authentication.

## Running locally with Docker

The `deployment/` directory contains a `docker-compose.yml` that spins up all required services: PostgreSQL, Redis, integration tests, and the application.

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/) and Docker Compose installed.

### Steps

1. Navigate to the `deployment/` directory:

   ```bash
   cd deployment
   ```

2. Start all services:

   ```bash
   docker-compose up
   ```

   Services start in the following order:
   - **db** — PostgreSQL 16 on port `5432`.
   - **cache** — Redis 7 on port `6379`.
   - **integration-tests** — Runs `mvn test` against the database before the app starts. If any test fails, the app will not start.
   - **backend-java** — The API, available at `http://localhost:8080` once all tests pass.

3. To stop and remove the containers:

   ```bash
   docker-compose down
   ```

### Services and ports

| Service    | Local port | Description          |
|------------|-----------|----------------------|
| PostgreSQL | `5432`    | Main database        |
| Redis      | `6379`    | Cache                |
| API        | `8080`    | Spring Boot app      |

### Default credentials (local only)

| Parameter       | Value                 |
|-----------------|-----------------------|
| DB host         | `localhost:5432`      |
| DB name         | `prueba_jaime_torres` |
| DB user         | `postgres`            |
| DB password     | `12345`               |
| Redis password  | `12345`               |

### Swagger UI

Once the application is running, the interactive API documentation is available at:

```
http://localhost:8080/swagger-ui/index.html
```

> All endpoints (except `/api/v1/auth/**`) require a JWT bearer token. Obtain one via `POST /api/v1/auth/register` or `POST /api/v1/auth/authenticate` and pass it as `Authorization: Bearer <token>`.
