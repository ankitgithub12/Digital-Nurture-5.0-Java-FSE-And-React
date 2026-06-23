# MCQ Practice Bank: Design Patterns and Principles

This practice bank contains 30 high-yield Multiple Choice Questions (MCQs) designed to test your knowledge of **SOLID Principles**, **GoF Design Patterns**, and **Architectural Patterns**. 

Use these questions to self-assess your understanding, prepare for interviews, or study for exams. Each question is followed by the correct answer and a detailed explanation.

---

## 🧭 Table of Contents
* [Part 1: SOLID Principles (Questions 1 - 15)](#part-1-solid-principles-questions-1---15)
* [Part 2: Design & Architectural Patterns (Questions 16 - 30)](#part-2-design--architectural-patterns-questions-16---30)

---

## Part 1: SOLID Principles (Questions 1 - 15)

### Q1. Which design concept is the primary goal of the Single Responsibility Principle (SRP)?
* A) Tight coupling and low cohesion.
* B) Loose coupling and high cohesion.
* C) Maximizing code inheritance.
* D) Combining multiple behaviors into one class to minimize file count.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> SRP states that a class should have only one reason to change. This directly promotes <i>high cohesion</i> (keeping related functions together in one class) and <i>loose coupling</i> (decoupling unrelated functions like database writing from business math), which makes modules easier to modify and test.
</details>

---

### Q2. If an `Invoice` class handles invoice data, calculates invoice totals, prints the invoice layout, and saves the invoice to a SQL database, which SOLID principle is violated?
* A) Liskov Substitution Principle
* B) Dependency Inversion Principle
* C) Single Responsibility Principle
* D) Interface Segregation Principle
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> This class has multiple reasons to change: if the calculation logic changes, if the printing layout changes, or if the database type changes. Therefore, it violates the Single Responsibility Principle (SRP).
</details>

---

### Q3. How can the following code snippet be refactored to adhere to SRP?
```java
class UserSettingService {
    public void changeEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        // Direct database connection details:
        String sql = "UPDATE Users SET email = '" + newEmail + "' WHERE id = " + user.getId();
        Database.execute(sql);
    }
}
```
* A) Make the `changeEmail` method synchronized.
* B) Move the SQL execution code to a separate `UserRepository` or data access class, and inject it.
* C) Have `UserSettingService` extend the `Database` class.
* D) Change `newEmail` to a custom `Email` value object.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Currently, `UserSettingService` handles user operations and low-level SQL execution. Moving the database operations into a dedicated `UserRepository` delegator separates data access from service logic, achieving SRP.
</details>

---

### Q4. The Open-Closed Principle (OCP) suggests that software modules should be:
* A) Open for modification, closed for extension.
* B) Open for extension, closed for modification.
* C) Open to public inheritance, closed to interface implementation.
* D) Locked from modifications unless an interface changes.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> OCP states that you should be able to extend a class's behavior (open for extension) without altering its existing source code (closed for modification) to avoid breaking current tests and features.
</details>

---

### Q5. Consider the following code violating OCP. What is the standard way to fix it?
```java
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            return Math.PI * ((Circle) shape).radius * ((Circle) shape).radius;
        } else if (shape instanceof Rectangle) {
            return ((Rectangle) shape).width * ((Rectangle) shape).height;
        }
        return 0;
    }
}
```
* A) Add more `else-if` statements for new shapes.
* B) Declare the `AreaCalculator` class as `final`.
* C) Create a `Shape` interface with an abstract `double getArea()` method, make `Circle` and `Rectangle` implement it, and have `AreaCalculator` call `shape.getArea()`.
* D) Inherit `AreaCalculator` from `Rectangle`.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> By using polymorphism (defining a `Shape` interface), we make `AreaCalculator` work with any future shape class without modifying the calculator's source code, satisfying OCP.
</details>

---

### Q6. Which of the following is an example of violating the Open-Closed Principle?
* A) Creating a subclass that overrides a method to add a minor feature.
* B) Modifying a core core payment class with an if-else switch statement every time a new payment processor API is integrated.
* C) Declaring an entity class attributes as `private`.
* D) Using an abstract class to hold common logging behavior.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Modifying a core payment class directly for every new integration is a violation of OCP. We should instead use interfaces and inject payment strategy adapters.
</details>

---

### Q7. According to the Liskov Substitution Principle (LSP), which of the following statements is true?
* A) Subclasses must not call base class methods.
* B) Base classes must be replaceable by subclasses without changing the program's correctness.
* C) Subclasses must be final.
* D) Subclasses can return a broader exception list than parent classes.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> LSP states that objects of a superclass must be replaceable with objects of its subclasses without breaking the application. Subclasses must adhere to the contracts defined in the parent class.
</details>

---

### Q8. A class `Rectangle` has setters `setWidth(int)` and `setHeight(int)`. A class `Square` inherits from `Rectangle` and overrides both setters to set both fields to the same value to preserve the square shape. Why does this violate LSP?
* A) Because square doesn't have diagonal lines.
* B) A client writing code like `rect.setWidth(5); rect.setHeight(10);` expects the area to be 50. If `rect` is actually a `Square`, the area becomes 100, violating expectations.
* C) Because we cannot override public methods in subclasses.
* D) Square should not inherit from anything.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> The overridden behavior in `Square` changes the expected contract of `Rectangle` (where changing width does not alter height). A user function designed for a general `Rectangle` will yield wrong results when passed a `Square` object.
</details>

---

### Q9. When a developer writes code for a class `Ostrich` that extends `Bird` but overrides the `fly()` method to throw `UnsupportedOperationException`, which principle is violated?
* A) Single Responsibility Principle
* B) Interface Segregation Principle
* C) Liskov Substitution Principle
* D) Dependency Inversion Principle
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> Throwing `UnsupportedOperationException` when overriding parent behaviors breaks subtype substitutability. Code executing `.fly()` on a list of `Bird` objects will crash on `Ostrich`.
</details>

---

### Q10. What does the Interface Segregation Principle (ISP) advocate?
* A) Keeping all interfaces in a separate package.
* B) Creating a single large interface containing all possible method declarations.
* C) Splitting a large, bloated interface into multiple smaller, specific interfaces.
* D) Preventing classes from implementing multiple interfaces.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> ISP states that clients should not be forced to depend on interfaces they do not use. Splitting fat interfaces into thin, role-based interfaces prevents clients from implementing useless dummy methods.
</details>

---

### Q11. Look at this interface. Why does it violate ISP?
```java
interface MultiFunctionPrinter {
    void print();
    void scan();
    void fax();
}
```
* A) It has no abstract methods.
* B) A low-end printer that only supports printing is forced to implement empty `scan()` and `fax()` methods.
* C) It has no private fields.
* D) It returns void instead of an object.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> An implementation that only prints is forced to depend on methods it does not use (`scan()` and `fax()`). To satisfy ISP, we should split it into `Printer`, `Scanner`, and `Fax` interfaces.
</details>

---

### Q12. To resolve an ISP violation, a developer should:
* A) Implement the adapter pattern on the fat interface.
* B) Break the fat interface down into multiple smaller, highly cohesive interfaces.
* C) Merge implementing classes into a single master class.
* D) Suppress the compiler warnings.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Breaking down the fat interface into smaller interfaces allows each client to implement only what they need, resolving the violation.
</details>

---

### Q13. The Dependency Inversion Principle (DIP) states that:
* A) High-level modules should depend on low-level modules.
* B) Abstractions should depend on details.
* C) High-level modules and low-level modules should depend on abstractions.
* D) Lower-level details must be private final classes.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> DIP states that high-level business logic should not depend on low-level implementation details (like database connectors). Both must depend on abstractions (interfaces).
</details>

---

### Q14. In the following code, what is the source of the DIP violation?
```java
class CustomerService {
    private SQLDatabase database = new SQLDatabase(); // DIP Violation
    
    public void addCustomer(Customer c) {
        database.save(c);
    }
}
```
* A) Declaring the database field private.
* B) CustomerService directly instantiating the concrete class `SQLDatabase` using `new`.
* C) Passing a `Customer` object as an argument.
* D) SQLDatabase doesn't throw exceptions.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> `CustomerService` is tightly coupled to `SQLDatabase`. If we want to change to `MongoDatabase`, we must modify `CustomerService`. The dependency should be inverted by introducing a `Database` interface and injecting it.
</details>

---

### Q15. Which pattern or technique is primarily used to implement the Dependency Inversion Principle?
* A) Singleton Pattern
* B) Dependency Injection (DI)
* C) Proxy Pattern
* D) Adapter Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Dependency Injection (injecting dependencies via constructor, setter, or framework container) allows us to pass abstraction interfaces into high-level classes, adhering directly to DIP.
</details>

---

## Part 2: Design & Architectural Patterns (Questions 16 - 30)

### Q16. Which design pattern ensures that a class has only one instance and provides a global access point to it?
* A) Builder Pattern
* B) Prototype Pattern
* C) Singleton Pattern
* D) Factory Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> The Singleton pattern restricts class instantiation to a single object and provides a public static method (e.g. `getInstance()`) to access that same instance globally.
</details>

---

### Q17. Why is implementing a Singleton using Java's `enum` considered highly secure?
* A) Because enums are faster to compile.
* B) Enums provide automatic protection against serialization and reflection attacks at the JVM level.
* C) Enums cannot have methods.
* D) Enums don't use memory.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Enum singletons are JVM-guaranteed to be unique. They inherently prevent reflection instantiation (which can bypass private constructors) and handle serialization safety automatically.
</details>

---

### Q18. Which Creational pattern is most suitable when you need to construct a complex object step-by-step and want to avoid the "Telescoping Constructor" problem?
* A) Adapter Pattern
* B) Builder Pattern
* C) Factory Method Pattern
* D) Strategy Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> The Builder pattern isolates the configuration parameters from the actual construction of the object, allowing developers to set optional properties step-by-step using method chaining before calling `build()`.
</details>

---

### Q19. In GoF design patterns, what is the core purpose of the Factory Method pattern?
* A) Instantiating multiple singletons.
* B) Restricting subclassing.
* C) Defining an interface for object creation but letting subclasses/sub-logic decide which concrete class to instantiate.
* D) Speeding up garbage collection of newly created objects.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> The Factory Method pattern decouples the client from knowing the exact concrete class being instantiated, delegating object creation details to a factory method or subclass.
</details>

---

### Q20. Which structural pattern acts as a connector between two incompatible interfaces?
* A) Decorator Pattern
* B) Adapter Pattern
* C) Command Pattern
* D) Observer Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> The Adapter pattern converts the interface of a class into another interface that the client expects, translating calls between the two systems.
</details>

---

### Q21. When you want to add behavior to an object dynamically at runtime without using inheritance or subclassing, which pattern should you use?
* A) Proxy Pattern
* B) Strategy Pattern
* C) Decorator Pattern
* D) Singleton Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> The Decorator pattern wraps a concrete component inside decorator classes that implement the same interface, adding responsibilities dynamically without needing subclass explosion.
</details>

---

### Q22. The Java class `java.io.BufferedReader` wrapping `java.io.FileReader` is an example of which design pattern?
* A) Factory Method
* B) Decorator Pattern
* C) Proxy Pattern
* D) Command Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> In Java I/O streams, wrapping one stream with another (e.g., `new BufferedReader(new FileReader("file.txt"))`) to add buffering behaviors is the classic implementation of the Decorator pattern.
</details>

---

### Q23. What distinguishes a Proxy pattern from a Decorator pattern?
* A) Proxies are creational, whereas decorators are behavioral.
* B) A Decorator adds new behavior to an object, whereas a Proxy controls or manages access to the object (e.g., lazy loading or security verification).
* C) Decorators only wrap abstract classes.
* D) Proxies can never implement the same interface as the real subject.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> Although both wrap a target object, a Decorator adds features (like milk to coffee) while a Proxy controls access to the target (like checking permissions or loading the object lazily from a database).
</details>

---

### Q24. Which behavioral pattern is based on a "Publisher-Subscriber" model where state changes in one object automatically notify other dependent objects?
* A) Strategy Pattern
* B) Command Pattern
* C) Observer Pattern
* D) Proxy Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> The Observer pattern defines a one-to-many relationship. The publisher (Subject) holds a list of subscribers (Observers) and calls their update methods whenever its state changes.
</details>

---

### Q25. You are implementing a sorting engine that needs to switch between QuickSort, MergeSort, and HeapSort at runtime based on data size. Which pattern is best suited?
* A) Strategy Pattern
* B) Command Pattern
* C) Factory Method Pattern
* D) Adapter Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: A</b>
<br>
<b>Explanation:</b> The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime, allowing the context to swap sorting strategies dynamically.
</details>

---

### Q26. Which pattern encapsulates a request as an object, allowing you to log requests, queue them, and support undo operations?
* A) Strategy Pattern
* B) Command Pattern
* C) Observer Pattern
* D) MVC Pattern
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> The Command pattern wraps command details (receiver, arguments, actions) in a single object with an `execute()` method. A collection of these command objects can easily support histories, queues, schedules, and undo functions.
</details>

---

### Q27. In the Model-View-Controller (MVC) architectural pattern, which component is responsible for retrieving user input and updating the data representation?
* A) Model
* B) View
* C) Controller
* D) Router
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> The Controller acts as the mediator. It processes user input (like mouse clicks or text submissions), updates the Model (data state), and triggers updates to the View (user interface display).
</details>

---

### Q28. What is the primary benefit of using Dependency Injection (DI)?
* A) It makes applications run faster.
* B) It completely removes the need for interfaces.
* C) It decouples object creation from object execution, making testing easier through mock objects.
* D) It compiles code into smaller binaries.
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: C</b>
<br>
<b>Explanation:</b> DI separates class setup from run logic. By passing interface dependencies to constructors rather than instantiating them within classes, classes become easily unit-testable using mock implementations.
</details>

---

### Q29. Which design pattern family does the Builder pattern belong to?
* A) Creational
* B) Structural
* C) Behavioral
* D) Architectural
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: A</b>
<br>
<b>Explanation:</b> The Builder pattern deals with object creation, making it a member of the Creational design patterns family.
</details>

---

### Q30. If you wrap an existing legacy class in a new class to expose a cleaner, custom interface for your application, what pattern are you using?
* A) Strategy
* B) Adapter
* C) Singleton
* D) Observer
<details>
<summary><b>View Answer</b></summary>
<br>
<b>Correct Answer: B</b>
<br>
<b>Explanation:</b> The Adapter pattern wraps a legacy interface to adapt its signatures into a shape expected by modern client components.
</details>
