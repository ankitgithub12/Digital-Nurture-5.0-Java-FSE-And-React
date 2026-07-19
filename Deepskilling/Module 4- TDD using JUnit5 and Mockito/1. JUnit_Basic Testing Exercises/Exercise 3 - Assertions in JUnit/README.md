# Exercise 3: Assertions in JUnit

## Scenario
You need to use different assertions in JUnit to validate your test results.

## Steps Executed
1. **Created Project Structure**: Standard Maven project setup under `Exercise 3 - Assertions in JUnit`.
2. **Added JUnit 4 Dependency**: Added JUnit 4.13.2 dependency to `pom.xml`.
3. **Implemented Assertions Test Suite**: Created `AssertionsTest.java` utilizing various JUnit assertions:
   - `assertEquals`: Validates expected vs actual values.
   - `assertTrue`: Asserts that a boolean condition is true.
   - `assertFalse`: Asserts that a boolean condition is false.
   - `assertNull`: Asserts that an object reference is null.
   - `assertNotNull`: Asserts that an object reference is not null.
   - `assertSame` / `assertNotSame`: Validates object identity / reference equality.
   - `assertArrayEquals`: Validates element-by-element equality of arrays.

## Project Layout
```
Exercise 3 - Assertions in JUnit/
├── pom.xml
├── README.md
└── src/
    └── test/
        └── java/
            └── com/
                └── example/
                    └── AssertionsTest.java
```

## Running the Tests
Execute the following command in terminal from this project directory:
```bash
mvn test
```
