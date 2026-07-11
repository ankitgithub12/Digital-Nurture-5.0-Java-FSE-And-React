-- ProcessMonthlyInterest.sql
-- Description: Stored procedure to calculate and update the balance of all
-- savings accounts by applying an interest rate of 1% to the current balance.

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_rows_updated NUMBER := 0;
BEGIN
    -- Apply 1% interest to all Savings accounts
    UPDATE Accounts
    SET Balance = Balance * 1.01,
        LastUpdateDate = SYSDATE
    WHERE AccountType = 'Savings';
    
    v_rows_updated := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('ProcessMonthlyInterest: Applied 1% interest rate to ' || 
                         v_rows_updated || ' Savings account(s).');
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ProcessMonthlyInterest Error: Transaction rolled back. Details: ' || SQLERRM);
        RAISE;
END;
/
