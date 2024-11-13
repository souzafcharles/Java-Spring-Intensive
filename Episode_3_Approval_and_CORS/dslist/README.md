# Project Requirements and Configurations
## Episode 3: Approval and CORS
### Steps:
#### Project Profiles:
- **1.** Development and Testing Profile:
  - test;
  - H2 Database.
- **2.** Staging Profile:
  - dev; 
  - Staging Postgres Database.
- **3.** Production Profile:
  - prod;
  - Production Postgres Database.

#### Staging Steps:

- **1.** Environment Preparation
  - Docker or
  - Postgresql + pgAdmin or DBeaver.
- **2.** Local Staging: 
  - 2.1 Create project profiles system.properties ;
  - 2.2 Generate database script delete generated file;
  - 2.3 Create staging database;
  - 2.4 Run app in dev mode and validate;

#### CI/CD Deployment Steps:

- **1.** Prerequisites: 
  - 1.1 [Railway](https://railway.app/) account; 
  - 1.2 GitHub account older than 90 days; 
  - 1.3 Spring Boot project saved on GitHub; 
  - 1.4 SQL script for database creation and seeding; 
  - 1.5 Installed database management application (pgAdmin or DBeaver).

#### Railway Steps:
- Provision a database server;
- Create and seed the database;
- Create a Railway application linked to a GitHub repository;
- Configure environment variables:

```properties
APP_PROFILE
Copy code
DB_URL (Format: jdbc:postgresql://host:port/databasename)
DB_USERNAME
DB_PASSWORD
CORS_ORIGINS
```
- Configure the public domain for the application;
- Test app with Postman;
- Test the CI/CD pipeline.

### application.properties
```properties
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false

cors.origins=${CORS_ORIGINS:http://localhost:5173,http://localhost:3000}
```

## Project Profiles (Dev, Test and Prod)
### application-test.properties
```properties
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
### application-dev.properties
```properties
#spring.jpa.properties.jakarta.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-target=create.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.url=jdbc:postgresql://localhost:5433/dslist
spring.datasource.username=postgres
spring.datasource.password=1234567

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```
### application-prod.properties
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```
### system.properties
```properties
java.runtime.version=21
```
### WebConfig
```java
@Configuration
public class WebConfig {

	@Value("${cors.origins}")
	private String corsOrigins;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins(corsOrigins);
			}
		};
	}
	
}
```

### Script Docker Compose
```yml
version: "37.3.1"
services:
  # ====================================================================================================================
  # POSTGRES SERVER
  # ====================================================================================================================
  pg-docker:
    image: postgres:14-alpine
    container_name: dev-postgresql
    environment:
      POSTGRES_DB: mydatabase
      POSTGRES_PASSWORD: 1234567
    ports:
      - 5433:5432
    volumes:
      - ./.data/postgresql/data:/var/lib/postgresql/data
    networks:
      - dev-network
  # ====================================================================================================================
  # PGADMIN
  # ====================================================================================================================
  pgadmin-docker:
    image: dpage/pgadmin4
    container_name: dev-pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: me@example.com
      PGADMIN_DEFAULT_PASSWORD: 1234567
    ports:
      - 5050:80
    volumes:
      - ./.data/pgadmin:/var/lib/pgadmin
    depends_on:
      - pg-docker
    networks:
      - dev-network
# ======================================================================================================================
# REDE
# ======================================================================================================================
networks:
  dev-network:
    driver: bridge
```
