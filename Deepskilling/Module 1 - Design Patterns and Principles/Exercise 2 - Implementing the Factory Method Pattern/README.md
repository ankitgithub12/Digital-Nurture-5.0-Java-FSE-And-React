# Exercise 2: Implementing the Factory Method Pattern

This repository implements the **Factory Method Design Pattern** in Java. It follows the project organization and coding style of Exercise 1, while addressing the design issues identified during its review.

---

## 📂 Project Structure

```
Exercise 2 - Implementing the Factory Method Pattern/
├── README.md               # Analysis, Theory, Dry Run, and Interview Prep
└── src/
    └── exercise2/
        ├── Main.java       # Client application / Driver
        ├── product/
        │   ├── Vehicle.java # Product Interface
        │   ├── Car.java     # Concrete Product 1
        │   └── Bike.java     # Concrete Product 2
        └── creator/
            ├── VehicleFactory.java # Creator Base Class
            ├── CarFactory.java     # Concrete Creator 1
            └── BikeFactory.java     # Concrete Creator 2
```

---

## 📝 Part A: Analysis of Exercise 1 (Singleton Logger)

After reviewing the code in [Logger.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%201%20-%20Implementing%20the%20Singleton%20Pattern/src/exercise1/Logger.java) and [LoggerTest.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%201%20-%20Implementing%20the%20Singleton%20Pattern/src/exercise1/LoggerTest.java), here is the detailed evaluation:

### 1. What was done correctly
* **Double-Checked Locking (DCL):** Correctly implemented thread-safe lazy initialization using a `synchronized` block and checking the instance twice (`instance == null`).
* **Volatile Keyword:** Correctly used the `volatile` modifier on the static `instance` reference. This is critical in Java to prevent instruction reordering issues during instantiation, ensuring all threads see a fully initialized instance.
* **Private Constructor:** Effectively hid the constructor (`private Logger()`) to block standard instantiation with the `new` keyword.
* **Appending Mode:** The `FileWriter` is correctly configured in append mode (`new FileWriter(LOG_FILE, true)`) to prevent overwriting existing logs across program executions.

### 2. What can be improved (Shortcomings)
* **Resource Leak (No Close Mechanism):** The `PrintWriter` is opened in the constructor, but the class does not implement `AutoCloseable` or provide a `close()` method. If the application runs in a container/server environment where the classloader unloads, the underlying file descriptor stays open, leading to a file handle leak.
* **Reflection Attack Vulnerability:** The private constructor is accessible via Java reflection (`Constructor.setAccessible(true)`). It should check if `instance != null` and throw an exception to prevent duplicate instances.
* **Serialization / Cloning Vulnerability:** If the class is serialized/cloned, it could create multiple instances. Although it does not implement `Serializable` or `Cloneable`, adding safeguards (e.g. overriding `clone()` to throw `CloneNotSupportedException`) is a best practice.
* **Silent Errors on Initialization:** In the constructor, if an `IOException` occurs during `FileWriter` creation, the error is printed to `System.err`, but the constructor finishes silently. `fileWriter` remains `null`, causing future logs to silently fail writing to the file (though console printing still works).

---

## 📝 Part B: Factory Method Theory

### 1. Detailed Explanation
The **Factory Method Pattern** is a creational design pattern that defines an interface or abstract class for creating an object, but lets subclasses decide which class to instantiate. 

It solves the problem of **tight coupling** by replacing direct object creation using the `new` keyword with a polymorphic method call. 

#### Roles in the Pattern:
* **Product (`Vehicle`):** Defines the common interface/contract of the objects the creator and its subclasses will produce.
* **Concrete Product (`Car`, `Bike`):** Implements the `Product` interface.
* **Creator (`VehicleFactory`):** Declares the abstract factory method that returns a `Product` type. It may also implement default operations that rely on the products created by the factory method.
* **Concrete Creator (`CarFactory`, `BikeFactory`):** Overrides the factory method to return an instance of a specific `Concrete Product`.

### 2. Real-World Examples
1. **Document Management Systems:** An abstract `DocumentApplication` class declares a factory method `createDocument()`. Concrete subclasses like `WordApplication` and `PdfApplication` override it to create `WordDocument` and `PdfDocument` objects.
2. **Logistics App:** A transport logistics app has a `Logistics` base class. Road logistics overrides `createTransport()` to return a `Truck` object, while Sea logistics overrides it to return a `Ship` object.

### 3. Class Diagram (ASCII UML)

```
       +-------------------+
       |    <<interface>>  |
       |      Vehicle      |
       +-------------------+
       | + start() : void  |
       | + stop() : void   |
       +-------------------+
                 ^
                 |
        +--------+--------+
        |                 |
+---------------+ +---------------+
|      Car      | |     Bike      |
+---------------+ +---------------+
| + start()     | | + start()     |
| + stop()      | | + stop()      |
+---------------+ +---------------+
        ^                 ^
        .                 .
        . (instantiates)  . (instantiates)
        .                 .
+---------------------------------+
|         VehicleFactory          |
+---------------------------------+
| + createVehicle() : Vehicle     |
| + testDrive() : void            |
+---------------------------------+
        ^                 ^
        |                 |
+---------------+ +---------------+
|  CarFactory   | |  BikeFactory  |
+---------------+ +---------------+
| + createVehicle()| + createVehicle()|
+---------------+ +---------------+
```

---

## 📝 Part C: Complete Code

See the respective source files in the `src/exercise2` package.

* **Product Interface:** [Vehicle.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/product/Vehicle.java)
* **Concrete Products:** [Car.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/product/Car.java) & [Bike.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/product/Bike.java)
* **Creator Class:** [VehicleFactory.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/creator/VehicleFactory.java)
* **Concrete Creators:** [CarFactory.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/creator/CarFactory.java) & [BikeFactory.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/creator/BikeFactory.java)
* **Driver:** [Main.java](file:///d:/Java%20FSE%20And%20React/Deepskilling/Module%201%20-%20Design%20Patterns%20and%20Principles/Exercise%202%20-%20Implementing%20the%20Factory%20Method%20Pattern/src/exercise2/Main.java)

---

## 📝 Part D: Dry Run & Execution Flow

Let's trace how the program executes line-by-line:

1. **Factory Instantiation:**
   `VehicleFactory carFactory = new CarFactory();`
   * Instantiates a `CarFactory` and stores its reference in a variable of type `VehicleFactory`.
2. **Polymorphic Factory Call:**
   `Vehicle myCar = carFactory.createVehicle();`
   * The client invokes `createVehicle()`. Due to dynamic binding (polymorphism), Java executes `CarFactory.createVehicle()`.
   * Inside `CarFactory.createVehicle()`, the statement `return new Car();` is executed. The constructor of `Car` is called, printing `"Car Instance Initialized: Constructor invoked."`.
   * A reference to the new `Car` is returned as a `Vehicle` type.
3. **Core Operation Execution:**
   `myCar.start();`
   * Java looks up the runtime object type (which is `Car`) and invokes its overridden `start()` method, printing `"Car engine started. Vroom!"`.
4. **Concrete Creator Template Method Execution:**
   `carFactory.testDrive();`
   * The client calls `testDrive()` on `carFactory`. Since `testDrive()` is implemented on the base class `VehicleFactory`, it executes:
     1. Calls `createVehicle()`, which dynamically invokes `CarFactory.createVehicle()` to get a `Car` instance.
     2. Prints `"--- Initiating Factory-level Test Drive ---"`.
     3. Calls `start()` on the vehicle, printing `"Car engine started. Vroom!"`.
     4. Calls `stop()` on the vehicle, printing `"Car engine stopped safely."`.

---

## 📝 Part E: Interview Preparation

### 1. Differences: Factory Method vs. Simple Factory vs. Abstract Factory

| Feature | Simple Factory | Factory Method | Abstract Factory |
| :--- | :--- | :--- | :--- |
| **Definition** | A single helper class with static methods creating objects based on arguments. | Defines an interface for creating a single product, letting subclasses decide concrete type. | Provides an interface to create families of related/dependent products without concrete classes. |
| **Design Principle** | Violates OCP (requires modifying the factory class to add new products). | Adheres to OCP (can add new products by adding new creator subclasses). | Adheres to OCP and SRP, grouping related objects together. |
| **Inheritance vs. Composition** | Uses static parameter-based helper methods. | Relies on **Inheritance** (creator subclasses override factory method). | Relies on **Composition** (client is composed with factory object). |

### 2. Advantages and Disadvantages of Factory Method
* **Advantages:**
  * **Decoupling:** Eliminates tight coupling between client code and concrete product classes.
  * **Open-Closed Principle (OCP):** New products can be introduced without modifying existing client or factory code.
  * **Single Responsibility Principle (SRP):** Moves product instantiation code into dedicated creator classes.
* **Disadvantages:**
  * **Class Explosion:** Requires creating a new concrete subclass of the factory for every new concrete product class, significantly increasing the number of classes.

### 3. Common Interview Questions
* **Q1: Why declare the factory method abstract in the base class rather than using a simple switch-case?**
  * *A:* A switch-case (Simple Factory) violates the Open-Closed Principle because adding a new product requires modifying the switch-case code. By making it abstract, we use polymorphism to extend the application without changing existing code.
* **Q2: Can the Creator class be a concrete class instead of abstract?**
  * *A:* Yes. The Creator can provide a default factory method implementation that returns a default product, which subclasses can optionally override.
* **Q3: How does the Factory Method pattern enforce Dependency Inversion?**
  * *A:* Both high-level client classes and concrete creators depend on the high-level abstraction (`Vehicle`), rather than high-level classes depending directly on concrete low-level implementations (`Car` or `Bike`).

---

## 📝 Part F: Sample Output

```
=== Running Exercise 2: Factory Method Pattern Demo ===

--- [Car Factory Demonstration] ---
Requesting vehicle creation from Car Factory...
CarFactory: Generating a new Car product instance.
Car Instance Initialized: Constructor invoked.
Car engine started. Vroom!
Car engine stopped safely.

--- [Bike Factory Demonstration] ---
Requesting vehicle creation from Bike Factory...
BikeFactory: Generating a new Bike product instance.
Bike Instance Initialized: Constructor invoked.
Bike engine started. Kick-start successful!
Bike engine stopped safely.

--- [Template Method testDrive() Demonstration] ---
Running testDrive on Car Factory:

--- Initiating Factory-level Test Drive ---
CarFactory: Generating a new Car product instance.
Car Instance Initialized: Constructor invoked.
Car engine started. Vroom!
Car engine stopped safely.
--- Test Drive Completed Successfully ---

Running testDrive on Bike Factory:

--- Initiating Factory-level Test Drive ---
BikeFactory: Generating a new Bike product instance.
Bike Instance Initialized: Constructor invoked.
Bike engine started. Kick-start successful!
Bike engine stopped safely.
--- Test Drive Completed Successfully ---

=============================================
SUCCESS: Factory Method Pattern verified successfully!
Client is completely decoupled from concrete classes (Car, Bike).
SOLID Principles Satisfied:
  1. Open-Closed Principle (OCP): New vehicle types (e.g., Truck) can be added
     by creating new subclasses of Vehicle and VehicleFactory without modifying Main.
  2. Dependency Inversion Principle (DIP): Client programs to Vehicle and VehicleFactory
     interfaces/abstractions instead of concrete subclasses.
=============================================
```

---

## 🚀 How to Compile and Run

Navigate to the Module 1 root directory:
```bash
cd "Deepskilling/Module 1 - Design Patterns and Principles"
```

### 1. Compile the Code
```bash
javac "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/product/Vehicle.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/product/Car.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/product/Bike.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/creator/VehicleFactory.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/creator/CarFactory.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/creator/BikeFactory.java" "Exercise 2 - Implementing the Factory Method Pattern/src/exercise2/Main.java"
```

### 2. Run the Program
```bash
java -cp "Exercise 2 - Implementing the Factory Method Pattern/src" exercise2.Main
```
