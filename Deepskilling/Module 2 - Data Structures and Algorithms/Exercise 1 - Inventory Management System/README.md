# Exercise 1: Inventory Management System

## 📝 Problem Statement

In a large warehouse or e-commerce fulfillment center, keeping track of stock levels, pricing, and product details is vital. A typical inventory might hold thousands or even millions of distinct products.

Without efficient storage and retrieval mechanisms, standard operational tasks like:
- Checking if a product is in stock (lookup)
- Adding new products (insertion)
- Modifying price or quantity (update)
- Discontinuing a product (deletion)

would become slow, resulting in delays, lost revenue, and poor user experiences.

---

## 💡 Understanding the Problem

### 1. Why Data Structures & Algorithms are Essential for Large Inventories
When managing a large inventory, the amount of data can quickly grow beyond what is feasible to process using naive approaches (like scanning an unsorted array or list).
- **Performance Scale**: A linear search $O(n)$ on 1,000 items is fast, but on $1,000,000$ items, it is slow and CPU-intensive. Under load, these delays compound.
- **Resource Management**: Selecting the right structure minimizes memory overhead and prevents excessive garbage collection or memory page faults.
- **Data Integrity**: Efficient algorithms prevent conflicts and ensure atomic updates when multiple threads access the same inventory.

### 2. Suitable Data Structures for Inventory Management

| Data Structure | Description | Pros | Cons |
| :--- | :--- | :--- | :--- |
| **ArrayList (Dynamic Array)** | Stores products sequentially in memory. | Fast iteration, index-based access ($O(1)$ by index). | Very slow search, update, or delete by `productId` ($O(n)$) because it requires scanning the entire list. |
| **Singly/Doubly Linked List** | Stores products in nodes linked by pointers. | Fast insertion/deletion if the node reference is already known. | Slow lookup by ID ($O(n)$) because we must traverse sequentially from the head. |
| **Binary Search Tree (e.g., TreeMap)** | Stores products in sorted order (usually by ID). | Logarithmic search, insert, and delete ($O(\log n)$). Keeps inventory sorted. | Slightly slower than hash-based structures. Higher memory overhead per node. |
| **Hash-based Map (e.g., HashMap)** | Stores key-value pairs (`productId -> Product`) using hashing. | Extremely fast search, insert, and delete ($O(1)$ average time complexity). | Does not maintain order (though `LinkedHashMap` can maintain insertion order). Requires a good hash function to avoid collisions. |

---

## 🛠️ Choice of Data Structure & Implementation

For this project, we choose **`HashMap`** (`java.util.HashMap`) to store our product catalog.

### Why HashMap?
1. **Unrivaled Speed**: Warehouse operations are highly ID-centric. Retrieving, updating, or deleting a product by its unique `productId` occurs in **$O(1)$ constant time** on average.
2. **Simplicity**: Mapping a unique key (`productId`) directly to a value (`Product`) aligns naturally with the problem requirements.

---

## 📂 Project Structure

```
Exercise 1 - Inventory Management System/
├── README.md               # Explanation, analysis, and execution guide
└── src/
    └── exercise1/
        ├── Product.java    # Product model class
        ├── Inventory.java  # Inventory manager using HashMap
        └── InventoryTest.java # Driver test class to verify features
```

---

## 🚀 How to Compile and Run

Navigate to the Module 2 root directory:
```bash
cd "Deepskilling/Module 2 - Data Structures and Algorithms"
```

### 1. Compile the Source Code
Compile the Java files:
```bash
javac "Exercise 1 - Inventory Management System/src/exercise1/Product.java" "Exercise 1 - Inventory Management System/src/exercise1/Inventory.java" "Exercise 1 - Inventory Management System/src/exercise1/InventoryTest.java"
```

### 2. Run the Tester
Run the compiled `InventoryTest` class:
```bash
java -cp "Exercise 1 - Inventory Management System/src" exercise1.InventoryTest
```

---

## 📊 Complexity Analysis & Optimizations

### Time Complexity Analysis

| Operation | Chosen Structure (`HashMap`) | Alternative (`ArrayList`) |
| :--- | :--- | :--- |
| **Add Product** | **$O(1)$** (Amortized constant time to compute hash and insert) | **$O(n)$** (Must scan list first to ensure ID uniqueness, then $O(1)$ amortized insert) |
| **Update Product** | **$O(1)$** (Direct lookup by key and modification) | **$O(n)$** (Must scan list to locate matching `productId`) |
| **Delete Product** | **$O(1)$** (Direct key removal) | **$O(n)$** (Must search, remove, and shift subsequent array elements) |
| **Retrieve Product** | **$O(1)$** (Direct lookup by key) | **$O(n)$** (Linear search through list) |

### Space Complexity
- **Space Complexity**: **$O(n)$**, where $n$ is the number of products stored. Each entry is stored once, along with the bucket array of the map.

### How to Optimize Further
While `HashMap` is extremely efficient, we can optimize it for specific enterprise scenarios:
1. **Initial Capacity & Load Factor**:
   - If we know the warehouse stores around 100,000 items, we should instantiate `new HashMap<>(135000, 0.75f)`. This avoids costly **rehashing** operations as the map resizes during data loads.
2. **Concurrent Access**:
   - In a multi-threaded web application, standard `HashMap` is not thread-safe. To prevent corruption and ensure consistency, we should use **`ConcurrentHashMap`**, which uses segment-locking/fine-grained locking to allow high concurrency without locking the entire map.
3. **Tracking Insertion Order or Sorting**:
   - If we need to print products in the order they were added, we can substitute `HashMap` with **`LinkedHashMap`** (slight memory overhead, maintains insertion order).
   - If we need to display products sorted alphabetically or numerically by ID, we can use **`TreeMap`** ($O(\log n)$ operations).
