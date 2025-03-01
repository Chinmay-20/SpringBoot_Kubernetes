# Bookmark Application

## Overview
The Bookmark Application allows users to store and manage bookmarks of interesting articles. It includes features such as pagination and search functionality.

## Features
- Add new bookmarks
- View bookmarks with pagination
- Search for bookmarks
- Uses H2 database for local development
- Flyway for database migrations
- Docker & Kubernetes setup for containerization and orchestration

## Technology Stack
- **Backend**: Spring Boot
- **Database**: H2 (in-memory for local development), PostgreSQL (for production)
- **Containerization**: Docker, Docker Compose
- **Orchestration**: Kubernetes (using KIND)
- **Testing**: Testcontainers for integration testing
- **Dev Tools**: Spring Boot DevTools for auto-reloading
- **Validation**: Spring Boot Validation for bean validation
- **Lombok**: To generate getters and setters
- **CI/CD**: GitHub Actions for automated testing and Docker image creation

## Setup and Execution

### Running the Application
#### Locally
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd bookmarker-api
   ```
2. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the application:
   - **Bookmarks API**: [http://localhost:8080/api/bookmarks](http://localhost:8080/api/bookmarks)
   - **Pagination**: [http://localhost:8080/api/bookmarks?page=2](http://localhost:8080/api/bookmarks?page=2)
   - **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - **JDBC URL**: `jdbc:h2:mem:<db-instance>`
     - **User**: `sa`
     - **Password**: *(leave empty)*

#### Running Tests
To execute tests, use:
```bash
./mvnw clean verify
```

#### Running with Docker
1. Build the Docker image:
   ```bash
   ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=ck9913/bookmarker-api
   ```
2. Run the container:
   ```bash
   docker run -p 8080:8080 ck9913/bookmarker-api
   ```

### Database Migrations with Flyway
Flyway is used to manage database migrations. All migration scripts are stored in `src/main/resources/db/migration` and are executed in sequence.

**Important:**
- Once a Flyway migration script has been executed, do not modify it. Instead, create a new script for any changes.
- Flyway sorts migration scripts based on version numbers and executes them sequentially.

## Deployment with Kubernetes
Kubernetes is used for container orchestration to ensure high availability and scalability.

### Steps:
1. Create a cluster using KIND:
   ```bash
   ./kind/create-cluster.sh
   ```
2. Deploy the application:
   ```bash
   kubectl apply -f kubernetes/
   ```
3. Destroy the cluster (if needed):
   ```bash
   ./kind/destroy-cluster.sh
   ```

### Kubernetes Components Used:
- **Pods**: Deploy and manage application containers.
- **ReplicaSets**: Ensures the required number of instances are running.
- **Deployments**: Manages rollout history and upgrades.
- **Services**: Exposes the application.
- **Ingress Controller**: Routes traffic based on DNS patterns.
- **Persistent Volumes**: Ensures data persistence for databases.

## CI/CD Pipeline
A GitHub Actions workflow is configured in `.github/workflows/build.yml`. It performs the following tasks:
- Runs tests
- Builds the Docker image
- Pushes the image to Docker Hub

## Tools
- **Kubernetes GUI**: Lens for managing Kubernetes clusters.
- **PostgreSQL for Testing**: Testcontainers spin up a PostgreSQL database for integration tests.

## Next Steps
- Enhance search functionality
- Improve scalability for production

## Contribution Guidelines
- Follow best practices for Spring Boot development.
- Ensure tests are written for all new features.
- Use feature branches for development.

## License
This project is licensed under the MIT License.

---

This README provides an overview of the project, setup instructions, and development best practices.

