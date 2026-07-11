-- MainSchema.sql: Sets up the tables and inserts sample data for testing Stored Procedures.

-- 1. Clean Up Existing Tables
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Accounts';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- 2. Create Customers Table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    DOB DATE,
    Balance NUMBER(15, 2),
    IsVIP VARCHAR2(5) DEFAULT 'FALSE'
);

-- 3. Create Accounts Table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20) NOT NULL, -- 'Savings' or 'Checking'
    Balance NUMBER(15, 2) NOT NULL,
    LastUpdateDate DATE DEFAULT SYSDATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    CONSTRAINT chk_acct_type CHECK (AccountType IN ('Savings', 'Checking'))
);

-- 4. Create Employees Table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    Department VARCHAR2(50) NOT NULL,
    Salary NUMBER(15, 2) NOT NULL,
    HireDate DATE NOT NULL
);

-- 5. Seed Sample Data
-- Seed Customers
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 12000.00, 'FALSE');
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES (2, 'Jane Smith', TO_DATE('1990-08-20', 'YYYY-MM-DD'), 8500.00, 'FALSE');

-- Seed Accounts
-- Account 1001: John Doe Savings. Balance: $5,000.00
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastUpdateDate) 
VALUES (1001, 1, 'Savings', 5000.00, SYSDATE - 45);

-- Account 1002: John Doe Checking. Balance: $7,000.00
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastUpdateDate) 
VALUES (1002, 1, 'Checking', 7000.00, SYSDATE);

-- Account 1003: Jane Smith Savings. Balance: $8,500.00
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastUpdateDate) 
VALUES (1003, 2, 'Savings', 8500.00, SYSDATE - 30);

-- Seed Employees
INSERT INTO Employees (EmployeeID, Name, Department, Salary, HireDate) 
VALUES (1, 'Alice Green', 'HR', 50000.00, TO_DATE('2020-01-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Department, Salary, HireDate) 
VALUES (2, 'Bob White', 'IT', 75000.00, TO_DATE('2019-03-20', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Department, Salary, HireDate) 
VALUES (3, 'Charlie Black', 'IT', 80000.00, TO_DATE('2021-06-01', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Department, Salary, HireDate) 
VALUES (4, 'Diana Pink', 'HR', 55000.00, TO_DATE('2022-11-10', 'YYYY-MM-DD'));

COMMIT;
