# SOLID Principles of Object-Oriented Programming

SOLID is a set of five design principles introduced by Robert C. Martin (Uncle Bob) to make software designs more understandable, flexible, and maintainable. Applying these principles prevents code rot, simplifies testing, and establishes robust software architectures.

This guide breaks down each principle, provides real-world analogies, highlights key MCQ-oriented points, and references the accompanying Java examples.

---

## 🧭 Table of Contents
1. [S - Single Responsibility Principle (SRP)](#1-s---single-responsibility-principle-srp)
2. [O - Open-Closed Principle (OCP)](#2-o---open-closed-principle-ocp)
3. [L - Liskov Substitution Principle (LSP)](#3-l---liskov-substitution-principle-lsp)
4. [I - Interface Segregation Principle (ISP)](#4-i---interface-segregation-principle-isp)
5. [D - Dependency Inversion Principle (DIP)](#5-d---dependency-inversion-principle-dip)
6. [MCQ Quick Cheat-Sheet](#mcq-quick-cheat-sheet)

---

## 1. S - Single Responsibility Principle (SRP)

> **"A class should have one, and only one, reason to change."**

### Core Concept
A class should perform only a single, well-defined function or hold a single responsibility. If a class has multiple responsibilities, they become coupled. Changing one responsibility might break or compile-bind the other responsibilities.

* **High Cohesion**: Classes should contain closely related operations.
* **Low Coupling**: Minimizing dependencies between distinct layers.

### Real-World Metaphor
A Swiss Army Knife has many tools, but if one blade breaks, you might have to replace the whole knife. In contrast, a specialized kitchen knife is easier to replace, clean, and maintain independently.

### Code Walkthrough (Java)
* **Violation ([SRPViolation.java](./src/solid/srp/SRPViolation.java))**:
  An `Employee` class that holds employee details, calculates their salary based on tax regulations, **and** writes employee records to the database. If the database schema changes, the class changes. If tax rules change, the class changes. If employee attributes change, the class changes.
* **Correction ([SRPSolution.java](./src/solid/srp/SRPSolution.java))**:
  Decoupled into three distinct classes:
  1. `Employee`: Plain Java Object representing the model attributes.
  2. `SalaryCalculator`: Focuses solely on payroll calculation logic.
  3. `EmployeeRepository`: Handles database persistence operations.

### MCQ Tips
* Look for classes that do **computations**, **database operations**, and **logging/UI rendering** all in one.
* Key term: *"Reason to change"*. If a change in business logic *and* a change in database storage force the same class to be modified, it violates SRP.

---

## 2. O - Open-Closed Principle (OCP)

> **"Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."**

### Core Concept
You should be able to add new functionality to a class without modifying its existing source code. Modifying existing code introduces risks of breaking current tests and features. Instead, you design classes using inheritance or polymorphism (interfaces) to extend behavior.

### Real-World Metaphor
A wall socket is closed for modification (you don't break open the wall to change the wiring), but open for extension (you can plug in a hairdryer, laptop charger, or vacuum cleaner using standard plugs).

### Code Walkthrough (Java)
* **Violation ([OCPViolation.java](./src/solid/ocp/OCPViolation.java))**:
  A `DiscountCalculator` class with a method containing a large `if-else` or `switch` block check for user type (e.g. `Regular`, `VIP`, `SuperVIP`). Adding a new customer type (`Premium`) requires editing the existing `DiscountCalculator` class, violating OCP.
* **Correction ([OCPSolution.java](./src/solid/ocp/OCPSolution.java))**:
  Define a `DiscountStrategy` interface with a `calculateDiscount` method. Each customer type is a class implementing this interface (`RegularDiscount`, `VIPDiscount`, `PremiumDiscount`). `DiscountCalculator` simply executes the interface method, meaning we can add infinite customer types without modifying the calculator class.

### MCQ Tips
* A method containing large `switch` statements or `if-else` chains checking class types is the classic OCP violation.
* Key technique to solve OCP violations: **Polymorphism** and **Strategy Pattern** (programming to interfaces).

---

## 3. L - Liskov Substitution Principle (LSP)

> **"Subclasses should be substitutable for their base classes without altering the correctness of the program."**

### Core Concept
If class `B` is a subclass of class `A`, then we should be able to replace objects of `A` with objects of `B` without breaking the program's behavior. A subclass must extend the base class's behavior, not ruin or restrict it.

### Real-World Metaphor
A toy duck looks like a duck and squeaks, but it needs batteries. If you replace a real duck with a toy duck in a pond that expects real ducks to eat bread, the program breaks because the toy duck cannot eat.

### Code Walkthrough (Java)
* **Violation ([LSPViolation.java](./src/solid/lsp/LSPViolation.java))**:
  A base class `Bird` has a `fly()` method. The subclass `Ostrich` inherits from `Bird` but cannot fly. It implements `fly()` by throwing an `UnsupportedOperationException`. This breaks code that expects any `Bird` to be able to fly.
* **Correction ([LSPSolution.java](./src/solid/lsp/LSPSolution.java))**:
  Create a hierarchy where we separate concerns. We can have a base class `Bird`. We create a sub-interface `Flyable` for birds that fly. `Ostrich` extends `Bird` but does not implement `Flyable`. `Eagle` extends `Bird` and implements `Flyable`. Code expecting flyable birds now takes `Flyable` parameters, avoiding runtime exceptions.

### MCQ Tips
* Subclasses that override methods to **throw `UnsupportedOperationException`** or **return null/do nothing** because the behavior doesn't apply to them are direct violations of LSP.
* Key term: *"Is-a"* relationship can be misleading. An Ostrich *is a* bird, but in OOP, substitution of behavior is what matters.

---

## 4. I - Interface Segregation Principle (ISP)

> **"Clients should not be forced to depend on methods they do not use."**

### Core Concept
Avoid creating "fat" or "bloated" interfaces that contain too many methods. Instead, split them into smaller, more specific interfaces so that implementing classes only need to write logic for methods that are relevant to them.

### Real-World Metaphor
Imagine a restaurant menu where you can only order a combo containing burgers, fries, and a milkshake. If a customer is vegan and only wants fries, they are forced to pay for and deal with the burger. A better design is an a-la-carte menu where items are separated.

### Code Walkthrough (Java)
* **Violation ([ISPViolation.java](./src/solid/isp/ISPViolation.java))**:
  A `Worker` interface with `work()`, `eat()`, and `sleep()`. A concrete class `HumanWorker` implements all three. But a `RobotWorker` class is forced to implement `eat()` and `sleep()` as empty methods or throw exceptions because robots don't eat or sleep.
* **Correction ([ISPSolution.java](./src/solid/isp/ISPSolution.java))**:
  Split `Worker` into smaller interfaces: `Workable` (with `work()`), `Feedable` (with `eat()`), and `Sleepable` (with `sleep()`). `HumanWorker` implements all three, while `RobotWorker` only implements `Workable`.

### MCQ Tips
* Look for interfaces with many unrelated methods, leading to implementing classes having empty method blocks (e.g., `// do nothing`) or throwing `UnsupportedOperationException`.
* Solve ISP violations by **refactoring a single fat interface into multiple thin, cohesive interfaces**.

---

## 5. D - Dependency Inversion Principle (DIP)

> **"1. High-level modules should not depend on low-level modules. Both should depend on abstractions."**
> **"2. Abstractions should not depend on details. Details should depend on abstractions."**

### Core Concept
This principle is about **decoupling**. High-level business logic should not be tightly coupled with low-level implementation details (like databases, network protocols, or specific hardware). Instead, both should rely on interfaces (abstractions).

* **Inversion**: Instead of the high-level class creating its low-level dependencies directly, the dependencies are "injected" into it (often via constructors or setters).

### Real-World Metaphor
Your computer has a USB port (abstraction). It doesn't care whether you plug in a mouse, a keyboard, or a flash drive (low-level details), as long as they adhere to the USB protocol. The computer does not hardcode support for a specific Logitech mouse model.

### Code Walkthrough (Java)
* **Violation ([DIPViolation.java](./src/solid/dip/DIPViolation.java))**:
  A high-level `Car` class directly instantiates a concrete `PetrolEngine` inside its constructor: `this.engine = new PetrolEngine();`. The `Car` is now tightly coupled to `PetrolEngine`. It is impossible to swap it with an `ElectricEngine` without editing the `Car` class.
* **Correction ([DIPSolution.java](./src/solid/dip/DIPSolution.java))**:
  Define an `Engine` interface. Make `PetrolEngine` and `ElectricEngine` implement it. The `Car` class takes an `Engine` interface via its constructor: `public Car(Engine engine) { this.engine = engine; }`. Now, we can pass any engine type to the car at runtime.

### MCQ Tips
* Look for classes instantiating other classes using the `new` keyword inside their constructors or methods (e.g., `new SQLDatabase()`, `new EmailSender()`).
* The solution to DIP is **Dependency Injection (DI)** and programming to interfaces.

---

## MCQ Quick Cheat-Sheet

To solve MCQs instantly, look for these triggers:

| Scenario / Description | Principle Violated | Corrective Action |
| :--- | :--- | :--- |
| "A class logs to console, writes to DB, and handles business calculations." | **SRP** | Split into Logger, DAO/Repository, and Business Logic classes. |
| "Adding a new Payment Type (like Apple Pay) requires editing an existing `switch` statement in the checkout code." | **OCP** | Define a `PaymentStrategy` interface and implement one class per payment type. |
| "A subclass throws `UnsupportedOperationException` in an inherited method because it doesn't support that action." | **LSP** | Redesign the inheritance hierarchy; split interfaces or extract common base capabilities. |
| "An interface defines `print()`, `scan()`, and `fax()`. A simple InkjetPrinter is forced to implement `fax()` as blank." | **ISP** | Split the fat interface into `Printer`, `Scanner`, and `Fax` interfaces. |
| "A `Manager` class creates concrete `Developer` and `Designer` objects using `new` directly inside its constructor." | **DIP** | Pass an interface `Employee` to the `Manager` constructor (Dependency Injection). |
