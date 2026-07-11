-- RunProceduresTest.sql
-- Description: Driver script to test the three PL/SQL Stored Procedures
-- and observe state changes in the database.

SET SERVEROUTPUT ON;

PROMPT ==================================================
PROMPT =        INITIAL STATE OF DATABASE TABLES        =
PROMPT ==================================================
PROMPT
PROMPT --- Accounts ---
SELECT AccountID, CustomerID, AccountType, Balance, TO_CHAR(LastUpdateDate, 'YYYY-MM-DD') AS LastUpdate FROM Accounts;
PROMPT
PROMPT --- Employees ---
SELECT EmployeeID, Name, Department, Salary FROM Employees;
PROMPT

PROMPT ==================================================
PROMPT =      1. TESTING ProcessMonthlyInterest         =
PROMPT ==================================================
BEGIN
    ProcessMonthlyInterest;
END;
/
PROMPT --- Accounts after Interest Applied ---
SELECT AccountID, AccountType, Balance, TO_CHAR(LastUpdateDate, 'YYYY-MM-DD') AS LastUpdate FROM Accounts;
PROMPT

PROMPT ==================================================
PROMPT =      2. TESTING UpdateEmployeeBonus            =
PROMPT ==================================================
PROMPT Calling UpdateEmployeeBonus for IT department with 10% bonus...
BEGIN
    UpdateEmployeeBonus('IT', 10.0);
END;
/
PROMPT --- Employees after IT Department Bonus ---
SELECT EmployeeID, Name, Department, Salary FROM Employees;
PROMPT

PROMPT ==================================================
PROMPT =      3. TESTING TransferFunds (SUCCESS)        =
PROMPT ==================================================
PROMPT Transferring $500 from Account 1001 (Savings) to Account 1002 (Checking)...
BEGIN
    TransferFunds(1001, 1002, 500.00);
END;
/
PROMPT --- Accounts after Successful Transfer ---
SELECT AccountID, AccountType, Balance FROM Accounts;
PROMPT

PROMPT ==================================================
PROMPT =      4. TESTING TransferFunds (FAIL: BALANCE)  =
PROMPT ==================================================
PROMPT Attempting to transfer $99,999 from Account 1001 (Savings) to Account 1002 (Checking)...
BEGIN
    TransferFunds(1001, 1002, 99999.00);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Test Exception Handler: Handled error: ' || SQLERRM);
END;
/
PROMPT --- Accounts remain unchanged ---
SELECT AccountID, AccountType, Balance FROM Accounts;
PROMPT
PROMPT ==================================================
