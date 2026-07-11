-- TransferFunds.sql
-- Description: Stored procedure to transfer a specified amount from one account to another,
-- ensuring the source account has sufficient balance before executing the transfer.

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_account IN NUMBER,
    p_destination_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance NUMBER;
    v_dest_exists NUMBER;
    
    -- Custom Exceptions
    insufficient_funds EXCEPTION;
    invalid_amount EXCEPTION;
    account_not_found EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Initiating Fund Transfer ===');
    DBMS_OUTPUT.PUT_LINE('Source Account: ' || p_source_account || 
                         ' | Dest Account: ' || p_destination_account || 
                         ' | Amount: $' || p_amount);

    -- 1. Validate transfer amount
    IF p_amount <= 0 THEN
        RAISE invalid_amount;
    END IF;
    
    -- 2. Verify and lock the source account row (concurrency protection)
    BEGIN
        SELECT Balance INTO v_source_balance
        FROM Accounts
        WHERE AccountID = p_source_account
        FOR UPDATE; -- Locks the row until COMMIT/ROLLBACK
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE account_not_found;
    END;
    
    -- 3. Verify destination account existence
    SELECT COUNT(*) INTO v_dest_exists
    FROM Accounts
    WHERE AccountID = p_destination_account;
    
    IF v_dest_exists = 0 THEN
        RAISE account_not_found;
    END IF;
    
    -- 4. Check for sufficient balance
    IF v_source_balance < p_amount THEN
        RAISE insufficient_funds;
    END If;
    
    -- 5. Execute fund transfer
    -- Deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastUpdateDate = SYSDATE
    WHERE AccountID = p_source_account;
    
    -- Add to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastUpdateDate = SYSDATE
    WHERE AccountID = p_destination_account;
    
    DBMS_OUTPUT.PUT_LINE('Success: Transferred $' || p_amount || 
                         ' from Account ' || p_source_account || 
                         ' to Account ' || p_destination_account);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('=== Transfer Completed and Committed ===' || CHR(10));
    
EXCEPTION
    WHEN invalid_amount THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be greater than zero.');
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be positive.');
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds. Available: $' || v_source_balance);
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance in source account.');
    WHEN account_not_found THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs could not be resolved.');
        RAISE_APPLICATION_ERROR(-20004, 'One or both accounts do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected database error. Details: ' || SQLERRM);
        RAISE;
END;
/
