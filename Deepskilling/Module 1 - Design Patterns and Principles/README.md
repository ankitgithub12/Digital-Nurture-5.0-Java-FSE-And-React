# Module 1: Design Patterns and Principles

Welcome to the **Design Patterns and Principles** learning module. This repository is designed to help software engineers learn, understand, and apply the foundational principles of object-oriented design and design patterns in Java.

Understanding these concepts will help you build robust, scalable, and maintainable software. Moreover, the theory and examples provided here are specifically structured to help you easily ace Multiple Choice Questions (MCQs) in exams or interviews.

---

## 📂 Module Structure

This module is organized into three main sections:

1. **[SOLID Principles](./SOLID_Principles)**
   * Theoretical and practical walkthrough of the five core SOLID principles:
     * **S**ingle Responsibility Principle (SRP)
     * **O**pen-Closed Principle (OCP)
     * **L**iskov Substitution Principle (LSP)
     * **I**nterface Segregation Principle (ISP)
     * **D**ependency Inversion Principle (DIP)
   * Detailed code examples illustrating violations vs. clean solutions.
2. **[Design Patterns](./Design_Patterns)**
   * Hands-on examples of the standard Gang of Four (GoF) design patterns:
     * **Creational**: Singleton, Factory Method, Builder
     * **Structural**: Adapter, Decorator, Proxy
     * **Behavioral**: Observer, Strategy, Command
     * **Architectural**: Model-View-Controller (MVC), Dependency Injection
   * Code implementations showing how these patterns solve real-world problems.
3. **[MCQ Practice Bank](./MCQ_Practice)**
   * A targeted collection of 30 high-yield questions covering SOLID and design patterns, complete with key definitions, code snippets, correct answers, and thorough explanations.

---

## 🛠️ How to Compile and Run the Code

All code examples in this module are written in **Java** and are structured to run as standalone programs. They do not require complex external dependencies, making them easy to test locally.

### Prerequisites
* Java SE Development Kit (JDK) 8 or higher installed.
* Command line (Terminal, PowerShell, or Git Bash) access.

### Compilation
Navigate to the root directory of this module:
```bash
cd "Deepskilling/Module 1 - Design Patterns and Principles"
```

To compile specific files, use `javac`. For example, to compile the Singleton pattern demo:
```bash
javac Design_Patterns/src/patterns/creational/singleton/SingletonDemo.java
```

### Execution
Run the compiled class file using `java`. For example:
```bash
java -cp Design_Patterns/src patterns.creational.singleton.SingletonDemo
```

---

## 📖 Quick Reference Table: "When to use What"

| Category | Principle/Pattern | Core Intent | MCQ Keyword Match |
| :--- | :--- | :--- | :--- |
| **SOLID** | **S**RP | A class should have only one reason to change. | "Single responsibility", "Cohesion" |
| **SOLID** | **O**CP | Software entities should be open for extension, closed for modification. | "Inheritance / Interfaces", "Adding new features without editing old code" |
| **SOLID** | **L**SP | Subtypes must be substitutable for their base types. | "Subclass breaking superclass behavior", "UnsupportedOperationException" |
| **SOLID** | **I**SP | Clients should not be forced to depend on methods they do not use. | "Fat interfaces", "Splitting interfaces" |
| **SOLID** | **D**IP | Depend on abstractions, not on concretions. | "Loose coupling", "Dependency injection", "Interfaces" |
| **Creational** | Singleton | Ensure a class has only one instance and provides a global point of access. | "Single instance", "Private constructor", "Database Connection pool" |
| **Creational** | Factory Method | Define an interface for creating an object, but let subclasses decide which class to instantiate. | "Object creation logic hidden", "Subclass determines type" |
| **Creational** | Builder | Separate the construction of a complex object from its representation. | "Complex object step-by-step", "Telephoning constructors", "Method chaining" |
| **Structural** | Adapter | Convert the interface of a class into another interface clients expect. | "Incompatible interfaces", "Bridge between legacy and new" |
| **Structural** | Decorator | Attach additional responsibilities to an object dynamically. | "Wrapper", "Dynamically adding behavior without subclassing" |
| **Structural** | Proxy | Provide a surrogate or placeholder for another object to control access to it. | "Access control", "Lazy loading", "Caching / Logging wrapper" |
| **Behavioral** | Observer | Define a one-to-many dependency between objects so that when one changes state, all dependents are notified. | "Publisher-Subscriber", "Event handling", "Listeners" |
| **Behavioral** | Strategy | Define a family of algorithms, encapsulate each one, and make them interchangeable. | "Interchangeable algorithms", "Avoid conditional statements (if-else/switch)" |
| **Behavioral** | Command | Encapsulate a request as an object, thereby letting you parameterize clients with different requests. | "Encapsulate request", "Undo/Redo", "Queue / log requests" |
| **Architectural** | MVC | Separate application concerns into three components: Model (Data), View (UI), and Controller (Glue). | "Separation of concerns", "UI vs Data logic decoupling" |
| **Architectural** | Dependency Injection | Pass dependency objects to the client instead of letting the client create them. | "DIP implementation", "Constructor/Setter Injection", "Testing helper" |

---

## 🔗 Useful Links and References
* [Baeldung - SOLID Principles](https://www.baeldung.com/solid-principles)
* [Refactoring.Guru - Design Patterns](https://refactoring.guru/design-patterns)
* [SourceMaking - Design Patterns](https://sourcemaking.com/design_patterns)
