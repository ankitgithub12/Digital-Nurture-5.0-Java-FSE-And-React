-- Scenario2.sql
-- Description: Iterates through all customers and sets the IsVIP flag to 'TRUE' 
-- for those who have a balance exceeding $10,000.

SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to select all customers
    CURSOR c_customers IS
        SELECT CustomerID, Name, Balance, IsVIP FROM Customers;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Processing VIP Promotions (Balance > $10,000) ===');
    
    FOR r_cust IN c_customers LOOP
        -- Check if the balance is strictly over $10,000
        IF r_cust.Balance > 10000.00 THEN
            -- Check if customer is not already VIP to avoid redundant updates
            IF r_cust.IsVIP <> 'TRUE' THEN
                UPDATE Customers
                SET IsVIP = 'TRUE'
                WHERE CustomerID = r_cust.CustomerID;
                
                DBMS_OUTPUT.PUT_LINE('Promoted: ' || r_cust.Name || 
                                     ' (ID: ' || r_cust.CustomerID || 
                                     ') has a balance of $' || r_cust.Balance || 
                                     '. IsVIP flag set to TRUE.');
            ELSE
                DBMS_OUTPUT.PUT_LINE('Already VIP: ' || r_cust.Name || 
                                     ' (ID: ' || r_cust.CustomerID || 
                                     ') already has VIP status.');
            END IF;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Did Not Qualify: ' || r_cust.Name || 
                                 ' (ID: ' || r_cust.CustomerID || 
                                 ') has a balance of $' || r_cust.Balance || 
                                 '. Balance must exceed $10,000.');
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
