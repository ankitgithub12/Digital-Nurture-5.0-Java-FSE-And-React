-- Scenario1.sql
-- Description: Iterates through all customers, checks their age, and if they are above 60,
-- applies a 1% discount to their current loan interest rates.

SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to select all customers
    CURSOR c_customers IS
        SELECT CustomerID, Name, DOB FROM Customers;
        
    v_age NUMBER;
    v_discount CONSTANT NUMBER := 1.0; -- 1% point discount (e.g., 7.5% -> 6.5%)
    v_loans_updated NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Processing Age-Based Interest Discounts (Age > 60) ===');
    
    FOR r_cust IN c_customers LOOP
        -- Calculate current age based on Date of Birth (DOB) and current system date
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, r_cust.DOB) / 12);
        
        -- Check if the customer is older than 60
        IF v_age > 60 THEN
            -- Update loans associated with the customer
            UPDATE Loans
            SET InterestRate = InterestRate - v_discount
            WHERE CustomerID = r_cust.CustomerID;
            
            v_loans_updated := SQL%ROWCOUNT;
            
            IF v_loans_updated > 0 THEN
                DBMS_OUTPUT.PUT_LINE('Discount Applied: Customer ' || r_cust.Name || 
                                     ' (ID: ' || r_cust.CustomerID || 
                                     ', Age: ' || v_age || ') received a ' || 
                                     v_discount || '% discount on ' || 
                                     v_loans_updated || ' active loan(s).');
            ELSE
                DBMS_OUTPUT.PUT_LINE('Note: Customer ' || r_cust.Name || 
                                     ' (ID: ' || r_cust.CustomerID || 
                                     ', Age: ' || v_age || ') is older than 60 but has no active loans.');
            END IF;
        END IF;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('=== Process Completed Successfully ===' || CHR(10));
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transaction rolled back. Details: ' || SQLERRM);
END;
/
