# Exercise 3: PL/SQL Stored Procedures

## 📝 Problem Statement
In transactional systems, business procedures must run atomically, safely, and efficiently. PL/SQL Stored Procedures allow developers to write complex, multi-statement database operations that compile once and execute directly inside the SQL engine, reducing network overhead.

This exercise covers:
1. Creating savings accounts, checking accounts, and employee payroll tables.
2. Writing stored procedures with **IN** parameters to update salaries dynamically.
3. Implementing bulk updates for interest calculations on savings accounts.
4. Structuring a transactional funds transfer procedure that performs concurrency locks and custom validations.

---

## 💡 Understanding PL/SQL Stored Procedures & Parameters

### 1. What is a Stored Procedure?
A stored procedure is a schema object that groups a set of SQL statements and procedural commands. It is compiled and saved in the database catalog.
*   **Key Benefits**:
    *   *Performance*: Compiled once, reducing parse time. Runs inside the database, minimizing client-server network hops.
    *   *Security*: Users can be granted permission to run procedures without having direct read/write access to the underlying tables.
    *   *Reusability*: Standardizes core business operations (e.g., transfers, payroll updates) across all application front-ends.

### 2. Parameter Modes in PL/SQL
PL/SQL supports three types of parameter modes to communicate data between the calling block and the procedure:

*   **`IN` (Default)**: Passes a read-only value from the calling environment to the procedure. Inside the procedure, the parameter is treated as a constant and cannot be modified.
*   **`OUT`**: Returns a value from the procedure back to the calling environment. The parameter starts as uninitialized (`NULL`) inside the procedure. It must be assigned a value before the procedure exits.
*   **`IN OUT`**: Passes a value from the calling environment, allows the procedure to read/modify it, and returns the updated value to the caller.

---

## 📂 Project Structure

```
Exercise 3- Stored Procedures/
├── README.md                 # Theoretical explanations, schema, and trace outputs
├── MainSchema.sql            # DB script containing CREATE and INSERT (seed) SQL commands
├── ProcessMonthlyInterest.sql# Stored procedure to apply 1% interest to all Savings accounts
├── UpdateEmployeeBonus.sql   # Stored procedure to add percentage bonus to employee salaries
├── TransferFunds.sql         # Transactional procedure to transfer funds between accounts
└── RunProceduresTest.sql     # Test harness executing all scenarios and verifying traces
```

---

## 🚀 How to Execute Scripts

Open your SQL client (e.g., SQL*Plus, SQL Developer, DBeaver) and run the scripts in order:

### 1. Initialize Tables and Seed Data
```sql
@MainSchema.sql;
```

### 2. Compile Stored Procedures
Compile each procedure into the database:
```sql
@ProcessMonthlyInterest.sql;
@UpdateEmployeeBonus.sql;
@TransferFunds.sql;
```

### 3. Run Verification Tests
Run the test script to execute procedures and print output tables:
```sql
@RunProceduresTest.sql;
```

---

## 📊 Trace Results & Analysis

### 1. Initial State of Database

**Accounts Table:**
| AccountID | CustomerID | AccountType | Balance |
| :--- | :--- | :--- | :--- |
| 1001 | 1 | Savings | $5,000.00 |
| 1002 | 1 | Checking | $7,000.00 |
| 1003 | 2 | Savings | $8,500.00 |

**Employees Table:**
| EmployeeID | Name | Department | Salary |
| :--- | :--- | :--- | :--- |
| 1 | Alice Green | HR | $50,000.00 |
| 2 | Bob White | IT | $75,000.00 |
| 3 | Charlie Black | IT | $80,000.00 |
| 4 | Diana Pink | HR | $55,000.00 |

---

### 2. Execution Traces

#### Step 1: ProcessMonthlyInterest
*   **Action**: Apply a `1%` interest rate to savings accounts (ID 1001 and 1003). Checking accounts are unaffected.
*   **Resulting trace**:
    ```text
    ProcessMonthlyInterest: Applied 1% interest rate to 2 Savings account(s).
    ```
*   **Updated Balance State**:
    - Account 1001: $5,000.00 $\times$ 1.01 = **$5,050.00**
    - Account 1003: $8,500.00 $\times$ 1.01 = **$8,585.00**
    - Account 1002 (Checking): remains **$7,000.00**

#### Step 2: UpdateEmployeeBonus
*   **Action**: Call `UpdateEmployeeBonus('IT', 10.0)` to apply a `10%` salary increase for IT employees (Bob and Charlie).
*   **Resulting trace**:
    ```text
    UpdateEmployeeBonus: Added a 10% bonus to employees in the IT department. Total affected: 2 employee(s).
    ```
*   **Updated Salary State**:
    - Bob White (IT): $75,000.00 $\times$ 1.10 = **$82,500.00**
    - Charlie Black (IT): $80,000.00 $\times$ 1.10 = **$88,000.00**
    - HR Employees: Salaries remain unchanged.

#### Step 3: TransferFunds (Success)
*   **Action**: Transfer `$500` from Account 1001 (Savings) to Account 1002 (Checking).
*   **Resulting trace**:
    ```text
    === Initiating Fund Transfer ===
    Source Account: 1001 | Dest Account: 1002 | Amount: $500
    Success: Transferred $500 from Account 1001 to Account 1002
    === Transfer Completed and Committed ===
    ```
*   **Updated Balance State**:
    - Account 1001 (Savings): $5,050.00 $-$ 500.00 = **$4,550.00**
    - Account 1002 (Checking): $7,000.00 + 500.00 = **$7,500.00**

#### Step 4: TransferFunds (Failure - Insufficient Balance)
*   **Action**: Attempt to transfer `$99,999` from Account 1001 (Savings) to 1002.
*   **Resulting trace**:
    ```text
    === Initiating Fund Transfer ===
    Source Account: 1001 | Dest Account: 1002 | Amount: $99999
    Error: Insufficient funds. Available: $4550
    Test Exception Handler: Handled error: ORA-20003: Insufficient balance in source account.
    ```
*   **Result**: The transaction was safely rolled back, and the accounts remained unchanged.

---

## 🛡️ Concurrency & Safety Controls in TransferFunds

*   **Pessimistic Locking (`FOR UPDATE`)**: Before performing updates, the procedure runs:
    ```sql
    SELECT Balance INTO v_source_balance FROM Accounts WHERE AccountID = p_source_account FOR UPDATE;
    ```
    This locks the row corresponding to the source account ID. If another concurrent session tries to transfer money out of the same account, it will block until this transaction completes. This guarantees that double-spending or race condition over-drafting is prevented.
*   **Exception Safety**: In case of insufficient balance, invalid amount, or nonexistent accounts, the procedure issues a `ROLLBACK` to clean up any partial state changes, and throws a custom error code (`-20001` through `-20004`) via `RAISE_APPLICATION_ERROR` to notify caller applications.
