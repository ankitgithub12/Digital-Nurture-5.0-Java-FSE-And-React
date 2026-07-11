# Exercise 1: PL/SQL Control Structures

## 📝 Problem Statement
In banking databases, automated business logic is routinely executed based on customer demographics, account statuses, and loan deadlines. PL/SQL Control Structures allow developers to build structured, procedural flows directly inside the database engine.

This exercise covers:
1. Creating a sample schema with **Customers** and **Loans** tables.
2. Writing PL/SQL blocks using **Conditional Control** (`IF-THEN-ELSE`) and **Iterative Control** (Cursors with `FOR LOOP`).
3. Applying a 1% discount to loan interest rates for older customers (Age > 60).
4. Automatically promoting high-balance customers (> $10,000) to VIP status.
5. Fetching and printing due reminders for loans expiring in the next 30 days.

---

## 💡 Understanding PL/SQL Control Structures

PL/SQL extends SQL with procedural features, grouped into three categories of control structures:

### 1. Conditional Control
Enables selective execution of code blocks based on conditions.
*   `IF-THEN`: Executes a block if the condition is `TRUE`.
*   `IF-THEN-ELSE`: Offers an alternative execution path when the condition evaluates to `FALSE`.
*   `IF-THEN-ELSIF`: Chains multiple logical expressions sequentially.
*   `CASE`: Selects a block to run among multiple choices (can be a selector or evaluation expression).

### 2. Iterative Control (Loops)
Allows repeating a block of code multiple times.
*   **Simple Loop**: Repeats code until an explicit `EXIT WHEN` condition is met.
*   **While Loop**: Repeats as long as a condition remains `TRUE`.
*   **For Loop**: Iterates over a specified range of integers (e.g., `FOR i IN 1..10`) or over rows returned by a cursor (e.g., `FOR rec IN cursor_name`). Cursor FOR loops are highly optimized by Oracle because they handle opening, fetching, and closing the cursor automatically.

### 3. Sequential Control
*   `GOTO`: Unconditionally branches to a specific label within the block (discouraged in structured design).
*   `NULL`: A placeholder statement that does nothing. Used to fulfill syntactic requirements in empty branches.

---

## 📂 Project Structure

```
Exercise 1- Control Structures/
├── README.md               # Theoretical explanations, schema overview, and trace outputs
├── MainSchema.sql          # DB script containing CREATE and INSERT (seed) SQL commands
├── Scenario1.sql           # PL/SQL block applying 1% discount to interest rates of customers > 60
├── Scenario2.sql           # PL/SQL block setting IsVIP flag to TRUE for balances > $10,000
└── Scenario3.sql           # PL/SQL block printing reminders for loans due in next 30 days
```

---

## 🚀 How to Execute Scripts

To run these scripts, connect to your Oracle database using SQL*Plus, SQL Developer, or any compatible client tool:

### 1. Initialize the Schema
Run `MainSchema.sql` to clean up existing tables, create the tables, and insert sample records:
```sql
@MainSchema.sql;
```

### 2. Run Scenario 1 (Interest Rate Discount)
```sql
@Scenario1.sql;
```

### 3. Run Scenario 2 (VIP Status Promotion)
```sql
@Scenario2.sql;
```

### 4. Run Scenario 3 (Loan Payment Reminders)
```sql
@Scenario3.sql;
```

---

## 📊 Script Analysis & Expected Trace Results

### 1. Database Seed State (Before running scripts)

**Customers Table:**
| CustomerID | Name | DOB (Age as of 2026) | Balance | IsVIP |
| :--- | :--- | :--- | :--- | :--- |
| 1 | John Doe | 1955-05-15 (71 yrs) | $12,000.00 | FALSE |
| 2 | Jane Smith | 1985-08-20 (40 yrs) | $8,500.00 | FALSE |
| 3 | Bob Johnson | 1940-11-10 (85 yrs) | $9,500.00 | FALSE |
| 4 | Alice Brown | 1990-12-05 (35 yrs) | $15,000.00 | FALSE |

**Loans Table:**
| LoanID | CustomerID | LoanAmount | InterestRate | StartDate | EndDate (Due Date) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 101 | 1 | $50,000.00 | 7.50% | 2024-01-01 | 2026-07-25 (Due in 14 days) |
| 102 | 2 | $20,000.00 | 8.00% | 2024-06-01 | 2026-08-20 (Due in 40 days) |
| 103 | 3 | $15,000.00 | 6.50% | 2025-01-01 | 2026-07-30 (Due in 19 days) |

---

### 2. Execution Traces

#### Scenario 1: Interest Rate Discount
Iterates through all customers, computes age, and if `Age > 60`, updates associated loans by subtracting `1%` interest rate.
*   **Target customers**: John Doe (71) and Bob Johnson (85).
*   **Resulting trace output**:
    ```text
    === Processing Age-Based Interest Discounts (Age > 60) ===
    Discount Applied: Customer John Doe (ID: 1, Age: 71) received a 1% discount on 1 active loan(s).
    Discount Applied: Customer Bob Johnson (ID: 3, Age: 85) received a 1% discount on 1 active loan(s).
    === Process Completed Successfully ===
    ```
*   **Post-Run State**:
    - Loan 101 (John Doe) interest rate reduced from `7.50%` to `6.50%`.
    - Loan 103 (Bob Johnson) interest rate reduced from `6.50%` to `5.50%`.

#### Scenario 2: VIP Status Promotion
Checks all customer balances. If `Balance > 10,000`, sets `IsVIP = 'TRUE'`.
*   **Eligible customers**: John Doe ($12,000) and Alice Brown ($15,000).
*   **Resulting trace output**:
    ```text
    === Processing VIP Promotions (Balance > $10,000) ===
    Promoted: John Doe (ID: 1) has a balance of $12000. IsVIP flag set to TRUE.
    Did Not Qualify: Jane Smith (ID: 2) has a balance of $8500. Balance must exceed $10,000.
    Did Not Qualify: Bob Johnson (ID: 3) has a balance of $9500. Balance must exceed $10,000.
    Promoted: Alice Brown (ID: 4) has a balance of $15000. IsVIP flag set to TRUE.
    === Process Completed Successfully ===
    ```

#### Scenario 3: Loan Reminders (Next 30 days)
Fetches loans due between `SYSDATE` and `SYSDATE + 30`.
*   **Due loans**: Loan 101 (July 25, 2026) and Loan 103 (July 30, 2026).
*   **Resulting trace output**:
    ```text
    === Generating Loan Due Reminders (Next 30 Days) ===
    REMINDER #1:
      Dear John Doe,
      This is a reminder that your loan (Loan ID: 101)
      amounting to $50000 is due for repayment on 2026-07-25.
      Please ensure sufficient funds are available to avoid any penalties.
    --------------------------------------------------
    REMINDER #2:
      Dear Bob Johnson,
      This is a reminder that your loan (Loan ID: 103)
      amounting to $15000 is due for repayment on 2026-07-30.
      Please ensure sufficient funds are available to avoid any penalties.
    --------------------------------------------------
    === Process Completed Successfully ===
    ```

---

## 🛡️ Transaction Control and Exception Handling

*   **Transaction Isolation**: Scenarios 1 and 2 modify persistent data. They wrap updates in a `BEGIN-EXCEPTION-END` block. If an error occurs during loop updates, a `ROLLBACK` is triggered to ensure database integrity, otherwise a `COMMIT` registers the updates permanently.
*   **Cursor Optimization**: Cursors are declared in the `DECLARE` section and iterated via cursor `FOR` loops, ensuring clean, garbage-collected SQL statements that do not leak cursors in the database memory.
