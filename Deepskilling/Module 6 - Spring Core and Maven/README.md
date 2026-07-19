# Module 6: Spring Core, Maven & Spring Boot Exercises

This directory contains solutions for **Module 6 - Spring Core and Maven** under the LibraryManagement application scenario.

---

## 📋 Exercise Index

### 1. Exercise 1 - Configuring a Basic Spring Application
* **Path**: `Spring Core_Maven/Exercise 1 - Configuring a Basic Spring Application/LibraryManagement`
* **Focus**: Setting up Spring `ClassPathXmlApplicationContext` using `applicationContext.xml`.

### 2. Exercise 2 - Implementing Dependency Injection
* **Path**: `Spring Core_Maven/Exercise 2 - Implementing Dependency Injection/LibraryManagement`
* **Focus**: Wiring `BookRepository` bean into `BookService` using Spring IoC setter injection.

### 3. Exercise 4 - Creating and Configuring a Maven Project
* **Path**: `Spring Core_Maven/Exercise 4 - Creating and Configuring a Maven Project/LibraryManagement`
* **Focus**: Configuring `pom.xml` with dependencies for Spring Context, Spring AOP, Spring WebMVC, and Maven Compiler Plugin (Java 1.8).

### 4. Exercise 5 - Configuring the Spring IoC Container
* **Path**: `Spring Core_Maven/Exercise 5 - Configuring the Spring IoC Container/LibraryManagement`
* **Focus**: Central XML container configuration for bean initialization and lifecycle management.

### 5. Exercise 7 - Implementing Constructor and Setter Injection
* **Path**: `Spring Core_Maven/Exercise 7 - Implementing Constructor and Setter Injection/LibraryManagement`
* **Focus**: Demonstrating both `<constructor-arg>` (Constructor Injection) and `<property>` (Setter Injection).

### 6. Exercise 9 - Creating a Spring Boot Application
* **Path**: `Spring Core_Maven/Exercise 9 - Creating a Spring Boot Application/LibraryManagement`
* **Focus**: Complete Spring Boot REST API application with Spring Data JPA and H2 database.

---

## 🚀 Execution Instructions

Run any exercise using Maven:
```bash
# Test
mvn test

# Run Spring Core Standalone
mvn compile exec:java

# Run Spring Boot Web App (Exercise 9)
mvn spring-boot:run
```
