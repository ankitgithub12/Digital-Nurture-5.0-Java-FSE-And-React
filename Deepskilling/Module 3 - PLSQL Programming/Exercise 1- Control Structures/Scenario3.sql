-- Scenario3.sql
-- Description: Fetches all loans whose due date (EndDate) falls within the next 30 days
-- and prints a personalized reminder message for each customer.

SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to select loans due in the next 30 days and join with Customers for details
    CURSOR c_due_loans IS
        SELECT c.Name, l.LoanID, l.EndDate, l.LoanAmount
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
        
    v_counter NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Generating Loan Due Reminders (Next 30 Days) ===');
    
    FOR r_loan IN c_due_loans LOOP
        v_counter := v_counter + 1;
        DBMS_OUTPUT.PUT_LINE('REMINDER #' || v_counter || ':');
        DBMS_OUTPUT.PUT_LINE('  Dear ' || r_loan.Name || ',');
        DBMS_OUTPUT.PUT_LINE('  This is a reminder that your loan (Loan ID: ' || r_loan.LoanID || ')');
        DBMS_OUTPUT.PUT_LINE('  amounting to $' || r_loan.LoanAmount || ' is due for repayment on ' || 
                             TO_CHAR(r_loan.EndDate, 'YYYY-MM-DD') || '.');
        DBMS_OUTPUT.PUT_LINE('  Please ensure sufficient funds are available to avoid any penalties.');
        DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    END LOOP;
    
    IF v_counter = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans are currently due in the next 30 days.');
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('=== Process Completed Successfully ===' || CHR(10));
END;
/
