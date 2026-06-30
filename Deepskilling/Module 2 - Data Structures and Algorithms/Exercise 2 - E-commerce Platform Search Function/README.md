# Exercise 2: E-commerce Platform Search Function

## 📝 Problem Statement

In an e-commerce platform, users continuously search for products by names, categories, or IDs. As the catalog grows to millions of products, finding the correct product efficiently becomes critical. 

This exercise covers:
1. The theory of **Asymptotic Notation** (Big O, best/average/worst cases).
2. The implementation of **Linear Search** and **Binary Search** in Java.
3. The comparison of search algorithms to decide which is best suited for an e-commerce platform.

---

## 💡 Understanding Asymptotic Notation

### 1. What is Big O Notation?
Big O notation is a mathematical notation used in computer science to describe the limiting behavior of a function when the argument tends towards a particular value or infinity. In algorithm analysis, it characterizes how the execution time or space requirements of an algorithm grow relative to the input size $n$.
- **Why it helps**: It allows developers to compare the efficiency of different algorithms objectively, independent of hardware performance, compiler choices, or programming language characteristics.

### 2. Search Operation Scenarios

*   **Best-Case Scenario**: The scenario where the algorithm performs the minimum possible number of operations.
    *   *Linear Search*: The target element is at the very first index of the array ($O(1)$).
    *   *Binary Search*: The target element is at the middle index of the array on the first check ($O(1)$).
*   **Average-Case Scenario**: The expected behavior over a random distribution of inputs.
    *   *Linear Search*: The target element is in the middle of the array ($O(n)$ operations, specifically $\frac{n}{2}$ checks).
    *   *Binary Search*: The target is found in about $\log_2(n) - 1$ steps, which is $O(\log n)$.
*   **Worst-Case Scenario**: The scenario where the algorithm performs the maximum possible number of operations.
    *   *Linear Search*: The target element is at the very end of the array, or does not exist at all ($O(n)$).
    *   *Binary Search*: The target element is at the last division boundaries (leaves of the decision tree) or does not exist at all ($O(\log n)$).

---

## 📂 Project Structure

```
Exercise 2 - E-commerce Platform Search Function/
├── README.md               # Theoretical explanations, analysis, and execution guide
└── src/
    └── exercise2/
        ├── Product.java    # Product model class (Comparable by productId)
        ├── SearchAlgorithms.java # Implementations of Linear and Binary Search
        └── SearchTest.java  # Tester class to run search operations
```

---

## 🚀 How to Compile and Run

Navigate to the Module 2 root directory:
```bash
cd "Deepskilling/Module 2 - Data Structures and Algorithms"
```

### 1. Compile the Source Code
```bash
& "C:\Users\HP\.antigravity-ide\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\javac.exe" "Exercise 2 - E-commerce Platform Search Function/src/exercise2/Product.java" "Exercise 2 - E-commerce Platform Search Function/src/exercise2/SearchAlgorithms.java" "Exercise 2 - E-commerce Platform Search Function/src/exercise2/SearchTest.java"
```

### 2. Run the Tester
```bash
& "C:\Users\HP\.antigravity-ide\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\java.exe" -cp "Exercise 2 - E-commerce Platform Search Function/src" exercise2.SearchTest
```

---

## 📊 Time Complexity Comparison

| Algorithm | Best Case | Average Case | Worst Case | Space Complexity | Requires Sorted Array? |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Linear Search** | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | **No** (Works on unsorted data) |
| **Binary Search** | $O(1)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ | **Yes** (Data must be sorted) |

### Growth of Operations ($n$)
- If $n = 1,000$: Linear search takes up to **1,000** operations, while Binary search takes at most **10** operations.
- If $n = 1,000,000$: Linear search takes up to **1,000,000** operations, while Binary search takes at most **20** operations.

---

## 🛒 Which Algorithm is More Suitable for an E-commerce Platform?

For a real-world e-commerce platform, **Binary Search** (or hash-based search/indexed search engines like Elasticsearch) is substantially more suitable than Linear Search.

### Rationale:
1. **Catalog Scale**: E-commerce platforms scale to hundreds of thousands or millions of products. Run-time lookups using $O(n)$ Linear Search will cause severe server CPU load and high latency for customers.
2. **Frequency of Read vs. Write**: Product catalogs are read far more often than they are written. We can sort the array/list once (e.g., when products are loaded or modified) and perform highly efficient $O(\log n)$ Binary Searches millions of times. The cost of sorting is amortized.
3. **User Experience**: A $O(\log n)$ search operates in microseconds, ensuring instantaneous search auto-completion and fast product rendering.
