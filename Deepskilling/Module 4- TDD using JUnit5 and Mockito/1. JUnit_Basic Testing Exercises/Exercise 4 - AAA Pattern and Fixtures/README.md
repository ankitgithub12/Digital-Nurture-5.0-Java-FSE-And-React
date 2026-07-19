# Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown Methods

## Scenario
You need to organize your tests using the **Arrange-Act-Assert (AAA)** pattern and use setup and teardown methods (`@Before`, `@After`, `@BeforeClass`, `@AfterClass`).

## AAA Pattern Breakdown
Each unit test method follows 3 distinct phases:
1. **Arrange**: Initialize objects, set up test fixtures, and configure inputs.
2. **Act**: Invoke the action/method being tested.
3. **Assert**: Verify that the actual output matches expected outcomes.

## Test Fixture Lifecycle
- `@BeforeClass`: Executed once before any tests in the class run.
- `@Before`: Executed before each `@Test` method to set up test objects.
- `@After`: Executed after each `@Test` method to release resources / clean up.
- `@AfterClass`: Executed once after all tests in the class finish.

## Project Layout
```
Exercise 4 - AAA Pattern and Fixtures/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── BankAccount.java
    └── test/
        └── java/
            └── com/
                └── example/
                    └── BankAccountTest.java
```

## Running the Tests
Execute the following command from this directory:
```bash
mvn test
```
