# Universidad API

## Descripción

API REST para sistema académico con Spring Boot.

## Requisitos

- Java 17
- Maven

## Instalación

1. Clona el repositorio.
2. Ejecuta `mvn clean install`
3. Ejecuta `mvn spring-boot:run`

## Uso

- Accede a http://localhost:8080/login.html
- Usuario: admin, Contraseña: password
- Swagger UI: http://localhost:8080/swagger-ui.html

## Tests

Ejecuta `mvn test`

Ver reporte Jacoco en target/site/jacoco/index.html

## Perfiles

- dev: H2
- prod: PostgreSQL
