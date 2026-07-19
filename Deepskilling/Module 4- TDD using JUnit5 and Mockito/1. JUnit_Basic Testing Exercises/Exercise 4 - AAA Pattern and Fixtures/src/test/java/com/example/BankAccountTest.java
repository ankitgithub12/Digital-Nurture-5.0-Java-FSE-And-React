package com.example;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Starting BankAccountTest suite execution...");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Finished BankAccountTest suite execution.");
    }

    @Before
    public void setUp() {
        // Setup fixture: Initialize fresh BankAccount before each test
        account = new BankAccount("ACC-1001", 500.0);
    }

    @After
    public void tearDown() {
        // Teardown fixture: Clean up state after each test
        account = null;
    }

    @Test
    public void testDepositUsingAAAPattern() {
        // Arrange: Prepare test data and initial state
        double depositAmount = 250.0;
        double expectedBalance = 750.0;

        // Act: Execute the method under test
        account.deposit(depositAmount);

        // Assert: Verify the actual result against expectation
        assertEquals(expectedBalance, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawUsingAAAPattern() {
        // Arrange: Prepare withdrawal amount and expected balance
        double withdrawAmount = 200.0;
        double expectedBalance = 300.0;

        // Act: Perform withdrawal
        account.withdraw(withdrawAmount);

        // Assert: Confirm balance decreased correctly
        assertEquals(expectedBalance, account.getBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFundsUsingAAAPattern() {
        // Arrange: Excessive withdrawal amount exceeding initial balance (500.0)
        double withdrawAmount = 600.0;

        // Act: Attempt to withdraw funds (expecting exception)
        account.withdraw(withdrawAmount);

        // Assert: Handled by expected = IllegalArgumentException.class
    }
}
