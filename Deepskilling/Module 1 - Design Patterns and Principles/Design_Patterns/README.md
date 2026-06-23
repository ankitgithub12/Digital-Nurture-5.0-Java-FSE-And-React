# Software Design Patterns

A design pattern is a general, reusable solution to a commonly occurring problem in software design. It is not a finished design that can be directly converted into code, but a template or description of how to solve a problem in many different situations.

Design patterns are categorized into three main families (plus architectural patterns), as established by the Gang of Four (GoF):
1. **Creational Patterns**: Focus on object creation mechanisms, trying to create objects in a manner suitable to the situation.
2. **Structural Patterns**: Focus on class and object composition, identifying simple ways to realize relationships between entities.
3. **Behavioral Patterns**: Focus on communication between objects, identifying common communication patterns.
4. **Architectural Patterns**: Frame-level patterns that dictate the structure of the entire application.

---

## 🧭 Table of Contents
* [Creational Patterns](#creational-patterns)
  * [1. Singleton Pattern](#1-singleton-pattern)
  * [2. Factory Method Pattern](#2-factory-method-pattern)
  * [3. Builder Pattern](#3-builder-pattern)
* [Structural Patterns](#structural-patterns)
  * [1. Adapter Pattern](#1-adapter-pattern)
  * [2. Decorator Pattern](#2-decorator-pattern)
  * [3. Proxy Pattern](#3-proxy-pattern)
* [Behavioral Patterns](#behavioral-patterns)
  * [1. Observer Pattern](#1-observer-pattern)
  * [2. Strategy Pattern](#2-strategy-pattern)
  * [3. Command Pattern](#3-command-pattern)
* [Architectural Patterns](#architectural-patterns)
  * [1. Model-View-Controller (MVC)](#1-model-view-controller-mvc)
  * [2. Dependency Injection (DI)](#2-dependency-injection-di)
* [MCQ Trigger Reference Checklist](#mcq-trigger-reference-checklist)

---

## Creational Patterns

Creational patterns decouple a client from the direct instantiation of objects. Using `new` directly leads to tight coupling.

### 1. Singleton Pattern
* **Intent**: Ensure a class has only one instance and provide a global point of access to it.
* **Real-World Metaphor**: A country has only one president at a time. The president's office is the global point of access.
* **Key Implementation Methods**:
  1. *Eager*: Instantiated at class load time (wastes memory if not used).
  2. *Lazy Thread-Safe*: Instantiated only when requested; uses `synchronized` block or keyword.
  3. *Double-Checked Locking*: Checks instance twice with volatile keyword for high performance.
  4. *Bill Pugh Helper Class*: Uses an inner static helper class (highly recommended in Java).
  5. *Enum*: Simplest, safest, handles serialization and reflection attacks automatically.
* **MCQ Trigger**: "Single instance", "Thread-safe creation", "Private constructor", "Database Connection pool".
* **Code Reference**: [SingletonDemo.java](./src/patterns/creational/singleton/SingletonDemo.java)

### 2. Factory Method Pattern
* **Intent**: Define an interface for creating an object, but let subclasses decide which class to instantiate.
* **Real-World Metaphor**: A toy factory produces toys, but the exact type of toy (Car, Doll) depends on the mold used in the assembly line.
* **MCQ Trigger**: "Decouple client from concrete constructor", "Subclasses decide product type", "Create object based on parameter".
* **Code Reference**: [FactoryDemo.java](./src/patterns/creational/factory/FactoryDemo.java)

### 3. Builder Pattern
* **Intent**: Separate the construction of a complex object from its representation, allowing the same construction process to create different representations.
* **Real-World Metaphor**: Ordering a custom pizza. You start with the base, and add cheese, pepperoni, or olives step-by-step.
* **MCQ Trigger**: "Telescoping constructors (too many overloaded constructors)", "Immutability of complex objects", "Step-by-step build chain".
* **Code Reference**: [BuilderDemo.java](./src/patterns/creational/builder/BuilderDemo.java)

---

## Structural Patterns

Structural patterns deal with relationships between classes/objects, enabling them to work together cohesively.

### 1. Adapter Pattern
* **Intent**: Convert the interface of a class into another interface that the client expects, allowing incompatible classes to work together.
* **Real-World Metaphor**: A wall socket plug adapter (e.g. converting a US two-prong plug to a European round-pin socket).
* **MCQ Trigger**: "Incompatible interfaces", "Wrapper of legacy code", "Translate client calls to third-party API".
* **Code Reference**: [AdapterDemo.java](./src/patterns/structural/adapter/AdapterDemo.java)

### 2. Decorator Pattern
* **Intent**: Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.
* **Real-World Metaphor**: Buying coffee. You start with a base coffee, then "decorate" it dynamically with milk, sugar, or caramel.
* **MCQ Trigger**: "Dynamic behavior addition at runtime", "Avoid subclass explosion", "Wrapper conforming to the same interface".
* **Code Reference**: [DecoratorDemo.java](./src/patterns/structural/decorator/DecoratorDemo.java)

### 3. Proxy Pattern
* **Intent**: Provide a surrogate or placeholder for another object to control access to it (e.g., for security, lazy loading, logging, or caching).
* **Real-World Metaphor**: A credit card represents a proxy for your bank account. It checks limits and authenticates your transaction before authorizing direct funds transfer.
* **MCQ Trigger**: "Control access to object", "Lazy loading of heavy objects", "Caching/logging wrapper", "Security firewall".
* **Code Reference**: [ProxyDemo.java](./src/patterns/structural/proxy/ProxyDemo.java)

---

## Behavioral Patterns

Behavioral patterns manage algorithms, responsibilities, and communication protocols between objects.

### 1. Observer Pattern
* **Intent**: Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
* **Real-World Metaphor**: Subscribing to a newsletter. Whenever the publisher prints a new edition, it automatically mails a copy to all subscribed readers.
* **MCQ Trigger**: "Publisher-subscriber model", "Listener registration", "One-to-many state tracking".
* **Code Reference**: [ObserverDemo.java](./src/patterns/behavioral/observer/ObserverDemo.java)

### 2. Strategy Pattern
* **Intent**: Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
* **Real-World Metaphor**: Traveling to the airport. You can choose to go by Taxi, Subway, or Bus. The goal is the same, but the algorithm (strategy) changes.
* **MCQ Trigger**: "Interchangeable behavior at runtime", "Eliminate massive nested if-else/switch logic", "Family of algorithms".
* **Code Reference**: [StrategyDemo.java](./src/patterns/behavioral/strategy/StrategyDemo.java)

### 3. Command Pattern
* **Intent**: Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
* **Real-World Metaphor**: In a restaurant, a waiter takes your order on a piece of paper (Command). The paper is sent to the kitchen (Receiver). The chef doesn't need to hear the request directly from you.
* **MCQ Trigger**: "Request encapsulated as object", "Undo / Redo operations", "Queueing / scheduling commands", "Decoupling invoker from receiver".
* **Code Reference**: [CommandDemo.java](./src/patterns/behavioral/command/CommandDemo.java)

---

## Architectural Patterns

Architectural patterns affect the structure of the entire codebase and determine how business, data, and user interface layers coordinate.

### 1. Model-View-Controller (MVC)
* **Intent**: Divide software application logic into three interconnected parts to separate internal representations of information from the ways information is presented to and accepted by the user.
  * **Model**: Manages data and business logic.
  * **View**: Formats and displays data to the user.
  * **Controller**: Receives user inputs, processes them, updates the Model, and refreshes the View.
* **MCQ Trigger**: "Separation of concerns", "Decoupling UI logic from data model", "Web routing controller".
* **Code Reference**: [MVCDemo.java](./src/patterns/architectural/mvc/MVCDemo.java)

### 2. Dependency Injection (DI)
* **Intent**: Implement the Dependency Inversion Principle by passing dependency objects to a client rather than letting the client create them.
* **Types**: Constructor Injection (preferred, ensures state), Setter Injection, Interface Injection.
* **MCQ Trigger**: "Injecting dependencies", "IoC (Inversion of Control) container", "Easier unit testing with mock objects".
* **Code Reference**: [DIDemo.java](./src/patterns/architectural/di/DIDemo.java)

---

## MCQ Trigger Reference Checklist

| Question Context Clue | Pattern Choice | Why? |
| :--- | :--- | :--- |
| **"Telescoping Constructor problem"** | **Builder** | Overcoming too many constructors for optional values. |
| **"Make incompatible interfaces talk to each other"** | **Adapter** | Works as a translator between two different systems. |
| **"Dynamically add features like toppings or scrollbars"** | **Decorator** | Wraps the base object without modifying it, conforming to the same interface. |
| **"Restrict access / Cache results / Lazy-load a database resource"** | **Proxy** | Intercepts calls to the target object. |
| **"A weather station needs to update 5 separate displays when metrics shift"** | **Observer** | One-to-many subscription notification structure. |
| **"Need to swap sorting algorithms (QuickSort, BubbleSort) dynamically"** | **Strategy** | Algorithms are encapsulated in classes and swapped at runtime. |
| **"Need an Undo/Redo button in a Text Editor"** | **Command** | Command history stack stores command objects that have an `execute()` and `undo()` method. |
| **"How to avoid reflection & serialization issues in Singleton"** | **Enum Singleton** | Enums guarantee JVM level safety against serialization/reflection. |
