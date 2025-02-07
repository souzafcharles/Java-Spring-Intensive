![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/Java-Spring-Intensive)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/Java-Spring-Intensive)
![GitHub](https://img.shields.io/github/license/souzafcharles/Java-Spring-Intensive)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/Java-Spring-Intensive)


# :coffee: Java Spring Intensive

:triangular_flag_on_post: Prof. Dr. Nelio Alves - [Dev Superior](https://devsuperior.com.br)
***
## Backend Project Stack:
| Technology            | Version          | Description                                 |
|-----------------------|------------------|---------------------------------------------|
| 📐 IntelliJ IDEA      | `2024.3`         | Integrated Development Environment (IDE)    |
| ☕ Java               | `21`             | Backend programming language                |
| 🌱 Spring Boot        | `3.4.2`          | Framework for creating Spring applications  |
| 🗄️ H2 Database Engine | `23.232`          | In-memory relational database for testing   |
| 🐘 PostgreSQL        | `14-alpine`       | Relational database management system       |
| 👩‍🚀 Postman           | `11.19`           | API testing and development tool            |
| 🐳 Docker Engine     | `27.3.1`          | Containerisation and virtualisation tool    |
| 🚅 Railway           | `Changelog #0219` | Deployment platform for applications        |

***
## Project API Rest Architecture:
![Project API Rest Architecture](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/api-rest-architecture.png)
***
## Project Logic Layered Architecture:
![Layered Architecture](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/logic-layered-architecture.png)
***
### Episode 1: [Structured Project](https://github.com/souzafcharles/Java-Spring-Intensive/tree/main/Episode_1_Structured_Project/gamelist)
#### Concepts:
- Web Systems and Resources;
- Client/Server Architecture, HTTP, and JSON;
- Designing and Implementing RESTful Web APIs;
#### Spring Rest Project Structure:
- Setting up a Spring Boot Project;
- Using Maven for Dependency Management;
#### Entities and ORM:
- Defining Entity Classes;
- Mapping Entities to Database Tables using JPA;
#### Database Seeding:
- Initializing Database with Sample Data;
- Using `import.sql` File;
#### Layered Pattern:
- Understanding Layers (Controller, Service, Repository);
- Implementing the Layered Architecture;
#### Controller, Service, and Repository:
- Creating Rest Controllers;
- Implementing Business Logic in Service Layer;
- Accessing Data with Repositories;
#### DTO Pattern:
- Using Data Transfer Objects (DTOs) for Data Manipulation;
- Mapping between Entities and DTOs.
***
### Episode 2: [Domain and Queries](https://github.com/souzafcharles/Java-Spring-Intensive/tree/main/Episode_2_Domain_and_Queries/gamelist)
#### N-N Relationships:
- Modeling Many-to-Many Relationships in JPA;
- Using Join Tables for N-N relationships;
#### Association Class and Embedded ID:
- Creating Association Classes for Complex Relationships;
- Using Embedded ID for Composite Keys;
#### SQL Queries in Spring Data JPA:
- Writing Custom Queries with `@Query`;
- Using Query Methods;
#### Projections:
- Creating Projections to Fetch Partial Data;
- Using Interfaces for JPA Projections.
***
### Episode 3: [Approval and CORS](https://github.com/souzafcharles/Java-Spring-Intensive/tree/main/Episode_3_Approval_and_CORS/gamelist)
#### Project Profiles (Dev, Test, and Prod):
- Configuring Profiles for Different Environments;
- Using `application.properties` and `application-{profile}.properties` Files;
#### Local Environment with Docker Compose:
- Setting up Local Staging Environment with Docker;
- Using Docker Compose to Manage Services;
#### Local Staging Process:
- Testing Application in Staging Environment;
- Validating Configurations Before Production Deployment;
#### CI/CD Deployment Process:
- Implementing Continuous Integration and Continuous Deployment Pipelines;
- Automating Build and Deployment with Tools: GitHub and Railway;
#### CORS Configuration:
- Setting up Cross-Origin Resource Sharing (CORS);
- Configuring CORS in Spring.
***
### Episode 4: [Special Endpoint](https://github.com/souzafcharles/Java-Spring-Intensive/tree/main/Episode_4_Special_Endpoint/gamelist)
* Design and Implementation of a Special Endpoint:
  - Creating Custom Endpoints in Spring Boot;
  - Handling Different HTTP Methods (POST and GET);
* Update Operation in Spring:
  - Performing Update Operations on Entity Data;
  - Using `@Transactional` for Atomicity;
  - Updating List Positions;
* HTTP Verb and Idempotency:
  - Understanding Idempotent and Non-Idempotent HTTP Methods.
****
### Episode 5: Summary and Learning Conclusion
* What was Learned:
  - Building a Java Project with Spring Boot:
    - Setting up and Configuring a Spring Boot Project;
  - Back-end Web and REST API:
    - Designing and Building RESTful APIs;
    - Using Spring MVC for Controller Logic;
  - Layered Development Pattern:
    - Implementing Layered Architecture (Controller, Service, Repository);
  - Implementing a Domain Model:
    - Creating and Managing Entities;
    - Using JPA for ORM;
  - Accessing a Database:
    - Managing Database Connections;
    - Performing CRUD Operations;
  - Implementing Business Logic:
    - Writing Business Logic in Service Layer;
  - Practical Application of Data Structures:
    - Using Lists, Maps, and Other Data Structures in Java;
  - Best Practices and Patterns:
    - Adopting Best Practices for Clean and Maintainable Code;
    - Using Design Patterns in Software Development;
  - Setting up a Local Environment with Docker and Docker Compose:
    - Configuring Docker for Local Development;
    - Managing Services with Docker Compose.
***
### :link: [Course Access](https://devsuperior.com.br/ijs-inscricao-org)
