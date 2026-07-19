# Digital Nurture 5.0 - Java FSE & React Deepskilling

Welcome to the **Digital Nurture 5.0 - Java Full Stack Engineer (FSE) & React** repository. This workspace contains comprehensive practical exercises, hands-on projects, and solution implementations covering Core Java, Design Patterns, Data Structures, PL/SQL, Test-Driven Development (TDD with JUnit 5 & Mockito), SLF4J Logging, and Spring Framework / Spring Boot.

---

## 🛠️ Technology Stack & Prerequisites

* **Java Development Kit (JDK)**: Java 17
* **Frameworks**: Spring Boot 3.2.5, Spring Framework 5.3.30 (Core, IoC, AOP, WebMVC)
* **Build Tool**: Apache Maven 3.x
* **Testing**: JUnit 5 (Jupiter), Mockito 5.x
* **Logging**: SLF4J 1.7.x with Logback Classic 1.2.x
* **Persistence & Database**: Spring Data JPA, H2 Database (In-Memory)

---

## 📂 Repository Structure & Deepskilling Modules

```text
Digital-Nurture-5.0-Java-FSE-And-React/
└── Deepskilling/
    ├── Module 1 - Design Patterns and Principles/
    ├── Module 2 - Data Structures and Algorithms/
    ├── Module 3 - PLSQL Programming/
    ├── Module 4- TDD using JUnit5 and Mockito/
    │   ├── 1. JUnit_Basic Testing Exercises/
    │   │   ├── Exercise 1 - Setting Up JUnit/
    │   │   ├── Exercise 3 - Assertions in JUnit/
    │   │   └── Exercise 4 - AAA Pattern and Fixtures/
    │   └── 3. Mockito exercises/
    │       ├── Exercise 1 - Mocking and Stubbing/
    │       └── Exercise 2 - Verifying Interactions/
    ├── Module 5 - SLF4J logging framework/
    │   └── 6. SL4J Logging exercises/
    │       └── Exercise 1 - Logging Error Messages and Warning Levels/
    └── Module 6 - Spring Core and Maven/
        └── Spring Core_Maven/
            ├── Exercise 1 - Configuring a Basic Spring Application/
            ├── Exercise 2 - Implementing Dependency Injection/
            ├── Exercise 4 - Creating and Configuring a Maven Project/
            ├── Exercise 5 - Configuring the Spring IoC Container/
            ├── Exercise 7 - Implementing Constructor and Setter Injection/
            └── Exercise 9 - Creating a Spring Boot Application/
```

---

## 🚀 Key Modules & Exercise Details

### 🧪 Module 4: TDD using JUnit 5 and Mockito
* **JUnit Basic Testing**:
  * **Exercise 1**: Setting up JUnit 5 environment and executing basic unit tests.
  * **Exercise 3**: Using JUnit assertions (`assertEquals`, `assertTrue`, `assertThrows`).
  * **Exercise 4**: Applying Arrange-Act-Assert (AAA) pattern and test fixtures (`@BeforeEach`, `@AfterEach`).
* **Mockito Exercises**:
  * **Exercise 1 (Mocking & Stubbing)**: Creating mock objects (`Mockito.mock`) and stubbing method returns (`when(...).thenReturn(...)`).
  * **Exercise 2 (Verifying Interactions)**: Verifying method calls and interaction counts (`verify(mock).method()`).

### 📝 Module 5: SLF4J Logging Framework
* **Exercise 1 (Logging Levels)**: Integrating SLF4J API with Logback Classic. Outputting `ERROR`, `WARN`, and `INFO` level logs with structured timestamps and thread identifiers.

### 🍃 Module 6: Spring Core, Maven & Spring Boot
* **Exercise 1 (Basic Spring App)**: Configuring Spring Application Context via XML (`applicationContext.xml`), initializing `ClassPathXmlApplicationContext`.
* **Exercise 2 (Dependency Injection)**: Implementing IoC and Dependency Injection (Setter Injection) between `BookService` and `BookRepository`.
* **Exercise 4 (Maven Configuration)**: Configuring Maven POM with `spring-context`, `spring-aop`, `spring-webmvc`, `aspectjweaver`, and setting compiler version to Java 1.8.
* **Exercise 5 (Spring IoC Container)**: Centralizing bean management and dependency wiring inside Spring IoC Container.
* **Exercise 7 (Constructor & Setter Injection)**: Demonstrating both `<constructor-arg>` and `<property>` injection patterns in XML configuration.
* **Exercise 9 (Spring Boot Application)**:
  * Full Spring Boot 3 web project with Spring Data JPA and H2 In-Memory Database.
  * Configured `application.properties` with server port 8080 and H2 console at `/h2-console`.
  * REST API `BookController` supporting full CRUD operations.

---

## 🌐 Spring Boot REST API Endpoints (Module 6 - Exercise 9)

Base URL: `http://localhost:8080/api/books`

| HTTP Method | Endpoint | Description | Sample Request Body |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/books` | Fetch all books | N/A |
| **GET** | `/api/books/{id}` | Fetch book by ID | N/A |
| **POST** | `/api/books` | Create a new book | `{"title": "Spring in Action", "author": "Craig Walls", "isbn": "9781617294945", "price": 45.99}` |
| **PUT** | `/api/books/{id}` | Update existing book | `{"title": "Clean Code", "author": "Robert C. Martin", "isbn": "9780132350884", "price": 39.95}` |
| **DELETE** | `/api/books/{id}` | Delete book by ID | N/A |

---

## ⚡ How to Build and Run Exercises

Navigate to any exercise folder containing a `pom.xml` file and run the following Maven commands:

### Run Unit Tests
```bash
mvn test
```

### Run Java Applications (Spring Core / SLF4J)
```bash
mvn compile exec:java
```

### Run Spring Boot Application
```bash
mvn spring-boot:run
```

---

## ✒️ Author
**Ankit**  
[GitHub Profile](https://github.com/ankitgithub12)
