-- UpdateEmployeeBonus.sql
-- Description: Stored procedure to update the salary of employees in a given department
-- by adding a bonus percentage passed as a parameter.

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
    v_rows_updated NUMBER := 0;
    invalid_bonus_rate EXCEPTION;
BEGIN
    -- Validation: Bonus rate cannot be negative
    IF p_bonus_percentage < 0 THEN
        RAISE invalid_bonus_rate;
    END IF;
    
    -- Update the salary of employees in the target department
    UPDATE Employees
    SET Salary = Salary * (1 + (p_bonus_percentage / 100))
    WHERE Department = p_department;
    
    v_rows_updated := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('UpdateEmployeeBonus: Added a ' || p_bonus_percentage || 
                         '% bonus to employees in the ' || p_department || 
                         ' department. Total affected: ' || v_rows_updated || ' employee(s).');
    
    COMMIT;
EXCEPTION
    WHEN invalid_bonus_rate THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('UpdateEmployeeBonus Error: Bonus percentage must be non-negative.');
        RAISE_APPLICATION_ERROR(-20001, 'Bonus percentage must be non-negative.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('UpdateEmployeeBonus Error: Transaction rolled back. Details: ' || SQLERRM);
        RAISE;
END;
/
