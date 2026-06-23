# Exercise 1: Implementing the Singleton Pattern

## 📝 Problem Statement
In a software application, multiple modules (such as User Authentication, Database Access, Transaction Processing, and Email Service) need to record operational messages or audit trails. Having each module instantiate its own logging instance leads to:
1. **Resource waste** (multiple file handles, memory allocations).
2. **Synchronization conflicts** when writing to the same log file.
3. **Out-of-order logs**, making system debugging extremely difficult.

To solve this, implement a centralized **Logger** class that follows the **Singleton Design Pattern**, ensuring that only one instance of the logger exists throughout the application lifecycle.

---

## 🛠️ Implementation Details

We will implement a thread-safe Singleton Logger using the **Double-Checked Locking** mechanism:
1. **Private Constructor**: Prevents other classes from instantiating the class using the `new` keyword.
2. **Private Volatile Static Reference**: Stores the single instance of the `Logger` class. `volatile` ensures memory write visibility across threads.
3. **Public Static Accessor (`getInstance()`)**: Provides a global point of access, utilizing double-checked locking with a synchronized block to instantiate the Logger lazy and thread-safe.
4. **Log Functionality**: A `log(String)` method that:
   * Adds a timestamp prefix to each entry.
   * Prints the log to the standard console.
   * Appends the log to a local file named `system.log`.

---

## 📂 Project Structure

```
Exercise 1 - Implementing the Singleton Pattern/
├── README.md               # Exercise details and execution guide
├── system.log              # Created automatically at runtime containing logged events
└── src/
    └── exercise1/
        ├── Logger.java     # Singleton Logger implementation
        └── LoggerTest.java # Tester to verify single instance constraint
```

---

## 🚀 How to Compile and Run

Navigate to the Module 1 root directory:
```bash
cd "Deepskilling/Module 1 - Design Patterns and Principles"
```

### 1. Compile the Source Code
Compile the Java files to `bin/` directory or run them directly:
```bash
javac "Exercise 1 - Implementing the Singleton Pattern/src/exercise1/Logger.java" "Exercise 1 - Implementing the Singleton Pattern/src/exercise1/LoggerTest.java"
```

### 2. Run the Tester
Run the compiled `LoggerTest` class to verify the Singleton constraints:
```bash
java -cp "Exercise 1 - Implementing the Singleton Pattern/src" exercise1.LoggerTest
```

### 3. Verify Output
1. Observe the console output. The message `"Logger Instance Initialized: Private constructor invoked."` should print **exactly once**, even though we request the instance twice.
2. Confirm that both hash references printed are identical.
3. Open `system.log` generated in the current execution folder to verify log entries were written correctly.
