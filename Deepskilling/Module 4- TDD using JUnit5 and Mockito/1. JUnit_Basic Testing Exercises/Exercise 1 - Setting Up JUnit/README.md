# Exercise 1: Setting Up JUnit

## Scenario
You need to set up JUnit in your Java project to start writing unit tests.

## Steps Executed
1. **Created Project Structure**: Standard Maven project layout under `Exercise 1 - Setting Up JUnit`.
2. **Added JUnit Dependency**: Added `junit:junit:4.13.2` with `<scope>test</scope>` to `pom.xml`:
   ```xml
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13.2</version>
       <scope>test</scope>
   </dependency>
   ```
3. **Created Sample Class**: Created `Calculator.java` under `src/main/java/com/example/Calculator.java`.
4. **Created Test Class**: Created `CalculatorTest.java` under `src/test/java/com/example/CalculatorTest.java` utilizing JUnit annotations (`@Test`, `@Before`, `@After`, `expected`) and assertions (`assertEquals`).

## Project Layout
```
Exercise 1 - Setting Up JUnit/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── Calculator.java
    └── test/
        └── java/
            └── com/
                └── example/
                    └── CalculatorTest.java
```

## Running the Tests
Execute the following command in terminal from this project directory:
```bash
mvn test
```
