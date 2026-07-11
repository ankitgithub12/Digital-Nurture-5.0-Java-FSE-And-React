-- MainSchema.sql: Sets up the tables and inserts sample data for testing Control Structures.

-- 1. Clean Up Existing Tables
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION
    WHEN OTHERS THEN
        NULL; -- Ignore error if table doesn't exist
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
    WHEN OTHERS THEN
        NULL; -- Ignore error if table doesn't exist
END;
/

-- 2. Create Customers Table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    DOB DATE NOT NULL,
    Balance NUMBER(15, 2) NOT NULL,
    IsVIP VARCHAR2(5) DEFAULT 'FALSE' NOT NULL,
    CONSTRAINT chk_isvip CHECK (IsVIP IN ('TRUE', 'FALSE'))
);

-- 3. Create Loans Table
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER NOT NULL,
    LoanAmount NUMBER(15, 2) NOT NULL,
    InterestRate NUMBER(5, 2) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL, -- Serves as the loan due date
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- 4. Seed Sample Data
-- Reference Date for Testing is assumed to be July 11, 2026

-- Sample Customers:
-- John Doe (ID: 1): Age 71 (Over 60), Balance 12,000 (VIP candidate)
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP)
VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 12000.00, 'FALSE');

-- Jane Smith (ID: 2): Age 40 (Under 60), Balance 8,500 (Non-VIP)
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP)
VALUES (2, 'Jane Smith', TO_DATE('1985-08-20', 'YYYY-MM-DD'), 8500.00, 'FALSE');

-- Bob Johnson (ID: 3): Age 85 (Over 60), Balance 9,500 (Non-VIP)
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP)
VALUES (3, 'Bob Johnson', TO_DATE('1940-11-10', 'YYYY-MM-DD'), 9500.00, 'FALSE');

-- Alice Brown (ID: 4): Age 35 (Under 60), Balance 15,000 (VIP candidate)
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP)
VALUES (4, 'Alice Brown', TO_DATE('1990-12-05', 'YYYY-MM-DD'), 15000.00, 'FALSE');


-- Sample Loans:
-- Loan 101: Belongs to John Doe (Age > 60). Due Date: July 25, 2026 (Within 30 days of July 11, 2026).
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (101, 1, 50000.00, 7.5, TO_DATE('2024-01-01', 'YYYY-MM-DD'), TO_DATE('2026-07-25', 'YYYY-MM-DD'));

-- Loan 102: Belongs to Jane Smith (Age <= 60). Due Date: August 20, 2026 (Outside 30 days of July 11, 2026).
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (102, 2, 20000.00, 8.0, TO_DATE('2024-06-01', 'YYYY-MM-DD'), TO_DATE('2026-08-20', 'YYYY-MM-DD'));

-- Loan 103: Belongs to Bob Johnson (Age > 60). Due Date: July 30, 2026 (Within 30 days of July 11, 2026).
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (103, 3, 15000.00, 6.5, TO_DATE('2025-01-01', 'YYYY-MM-DD'), TO_DATE('2026-07-30', 'YYYY-MM-DD'));

COMMIT;
