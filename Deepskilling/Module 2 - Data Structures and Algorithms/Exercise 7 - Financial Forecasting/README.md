# Exercise 7: Financial Forecasting

## 📝 Problem Statement
In financial forecasting, predicting future values of investments, revenues, or asset values is essential for decision-making. These predictions are often built on recursive models where a value in a future period depends on the value in the preceding period adjusted by a growth rate.

This exercise covers:
1. The concept of **Recursion** and how it simplifies programming problems.
2. A recursive approach to calculate future values under constant and varying growth rates.
3. Complexity analysis of the recursive implementation.
4. Optimization techniques (memoization and iterative transformation) to prevent call-stack overhead and redundant calculations.

---

## 💡 Understanding Recursive Algorithms

### 1. What is Recursion?
Recursion is a programming technique where a method calls itself to solve a smaller instance of the same problem. Every recursive method consists of two main parts:
*   **Base Case**: The termination condition that returns a value directly without making further recursive calls. This prevents infinite recursion.
*   **Recursive Case**: The part where the method reduces the problem into a simpler version and calls itself.

### 2. How it Simplifies Problems
Recursion matches the mathematical induction paradigm. It is ideal for problems that can be naturally divided into similar sub-problems, such as:
*   **Hierarchical Data Structures**: Traversing trees (e.g., DOM, file directories) or graphs.
*   **Divide and Conquer**: Algorithms like Merge Sort, Quick Sort, and Binary Search.
*   **Inductive Math Definitions**: Fibonacci sequence, Factorials, and Compound Interest/Growth calculations.

Instead of managing complex loops and state variables manually, recursion leverages the JVM's call stack to handle the state implicitly.

---

## 📂 Project Structure

```
Exercise 7 - Financial Forecasting/
├── README.md                     # Theoretical explanations, analysis, and execution guide
└── src/
    └── exercise7/
        ├── FinancialForecasting.java # Java class with recursive, memoized, and iterative solutions
        └── ForecastingTest.java     # Tester class to verify correctness and demonstrate stack limits
```

---

## 🚀 How to Compile and Run

Navigate to the Module 2 root directory:
```bash
cd "Deepskilling/Module 2 - Data Structures and Algorithms"
```

### 1. Compile the Source Code
Compile both the implementation and test files using the JDK compiler:
```bash
& "C:\Users\HP\.antigravity-ide\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\javac.exe" -d "Exercise 7 - Financial Forecasting/src" "Exercise 7 - Financial Forecasting/src/exercise7/FinancialForecasting.java" "Exercise 7 - Financial Forecasting/src/exercise7/ForecastingTest.java"
```

### 2. Run the Tester
Execute the test program:
```bash
& "C:\Users\HP\.antigravity-ide\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\java.exe" -cp "Exercise 7 - Financial Forecasting/src" exercise7.ForecastingTest
```

---

## 📊 Complexity and Optimization Analysis

### 1. Complexity of the Basic Recursive Algorithm
For a constant growth rate over $n$ periods:
*   **Time Complexity**: $O(n)$. The recursive method makes exactly $n$ calls to calculate the value.
*   **Space Complexity (Auxiliary)**: $O(n)$ due to call stack depth. Each recursive call allocates a stack frame to store variables and return addresses.

#### The Stack Overflow Vulnerability
In languages like Java, the call stack is limited in size (typically $512\text{ KB}$ to $1\text{ MB}$ per thread). If the number of periods $n$ is very large (e.g., $25,000$ daily/monthly predictions), the basic recursive method will crash with a `java.lang.StackOverflowError` because the stack frames exceed the available memory.

---

### 2. Optimization Techniques

To avoid excessive computations and prevent stack overflows, we can optimize the implementation using the following methods:

#### A. Memoization (Caching)
If the application needs to perform multiple queries for different periods (or if the forecasting branches into multiple scenarios, like optimistic/pessimistic growth trees), recursive calls will end up calculating the same subproblems repeatedly.
*   **How it works**: We store the results of calculations in a Cache (like a HashMap or Array) keyed by the period number. Before computing $FV(n)$, the method checks if the result is already in the cache.
*   **Benefit**: Saves computation time, reducing time complexity of multiple queries from $O(n \times m)$ to $O(n + m)$.

#### B. Iteration (Loop-based)
We can transform the linear recursion into a simple iterative loop.
*   **How it works**: We compute the future value iteratively using a loop, carrying the state forward.
*   **Complexity**:
    *   **Time Complexity**: $O(n)$
    *   **Space Complexity**: $O(1)$
*   **Benefit**: Eliminates stack frames completely. It can run safely for millions of periods without any danger of stack overflow.

#### C. Tail Call Optimization (TCO)
Tail recursion occurs when the recursive call is the very last operation performed by the function.
*   **Example**:
    ```java
    public static double calculateFutureValueTail(double currentVal, double rate, int periods) {
        if (periods <= 0) return currentVal;
        return calculateFutureValueTail(currentVal * (1 + rate), rate, periods - 1);
    }
    ```
*   **How it works**: Modern compilers optimize tail-recursive calls by reusing the current stack frame instead of allocating a new one.
*   **Java limitation**: The standard HotSpot JVM does **not** support Tail Call Optimization due to security checks that require maintaining a complete stack trace. Therefore, converting to an **iterative loop** is the preferred optimization in Java.

---

## 📈 Summary of Approaches

| Approach | Time Complexity | Space Complexity | Stack Safe? | Use Case |
| :--- | :---: | :---: | :---: | :--- |
| **Basic Recursion** | $O(n)$ | $O(n)$ | **No** (Crashes for large $n$) | Short periods, clean code readability. |
| **Varying Rates Recursion** | $O(k)$ | $O(k)$ | **No** (Crashes for large arrays) | Sequential varying-rate projections over short durations. |
| **Memoized Recursion** | $O(n)$ | $O(n)$ | **No** (Crashes for large $n$) | Multi-scenario branches or frequent multi-year queries. |
| **Iterative Method** | $O(n)$ | $O(1)$ | **Yes** (Stack safe) | **Best overall** for general production environments. |
