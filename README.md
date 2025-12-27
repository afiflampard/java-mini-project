# Spring Boot Boilerplate

A production-ready Spring Boot starter project with a clean architecture and common essentials:

- **Authentication** and **User management** services
- **Kafka** integration (producer sample)
- **REST APIs** with layered architecture (controller, service, repository)
- **Maven** build and dependency management
- **Config profiles** for environment-specific settings
- **Testing** with JUnit

## Prerequisites

- Java 17 or later
- Maven 3.9+ (or use your IDE's Maven wrapper)
- Optional: Docker (for local infra like Kafka), Kafka locally or in Docker

## Getting Started

1. Clone the repository
2. Open the project in your IDE (IntelliJ recommended)
3. Configure your application properties
   - Edit `src/main/resources/application.properties` (or `application.yml`) as needed
   - Set database/Kafka credentials if applicable

## Build

```bash
mvn clean package
```

## Run (Dev)

- Using Maven:

```bash
mvn spring-boot:run
```

- Or run the generated jar:

```bash
java -jar target/*.jar
```

- Optional: activate a specific Spring profile

```bash
SPRING_PROFILES_ACTIVE=local mvn spring-boot:run
```

## Test

```bash
mvn test
```

## API Documentation

If Springdoc OpenAPI is enabled, Swagger UI is typically available at:

- `http://localhost:8080/swagger-ui.html` or
- `http://localhost:8080/swagger-ui/index.html`

## Kafka

This project includes a simple Kafka producer component. To use it locally:

- Ensure a Kafka broker is running and reachable
- Configure the following properties in your `application.properties`/`application.yml`:

```properties
spring.kafka.bootstrap-servers=localhost:9092
# Additional producer/consumer config as needed
```

## Project Structure (high level)

```text
src/main/java/com/gdsc/boilerplate/
├─ controller/        # REST controllers (e.g., UserController)
├─ service/           # Business logic (e.g., AuthService, UserService)
├─ kafka/             # Kafka integration (e.g., KafkaProducer)
├─ domain/            # Entities, DTOs, etc. (if present)
└─ repository/        # Persistence layer (if present)
```

## Configuration

Common Spring configuration locations:

- `src/main/resources/application.properties` (or `.yml`)
- Profiles can be defined with `application-{profile}.properties` and activated via `SPRING_PROFILES_ACTIVE`

## Useful Maven Commands

- Clean and build: `mvn clean package`
- Run app: `mvn spring-boot:run`
- Run tests: `mvn test`
- Format (if configured): `mvn fmt:format` or `mvn spotless:apply`

## Notes

- Adjust ports and credentials as needed
- Ensure environment variables or secrets are not committed to VCS
- For Docker/Kubernetes deployment, add Dockerfile/Helm manifests as needed

---

Maintainers: update this README with environment specifics (DB choice, profile names, CI/CD, and infra setup) as the project evolves.
