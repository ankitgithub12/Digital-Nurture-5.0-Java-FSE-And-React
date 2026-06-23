package exercise1;

/**
 * Tester class for the Singleton Logger.
 * Demonstrates that retrieving the Logger instance multiple times
 * always returns the exact same object reference in memory.
 */
public class LoggerTest {

    public static void main(String[] args) {
        System.out.println("=== Running Exercise 1: Singleton Logger Test ===");

        // 1. Retrieve Logger instances
        System.out.println("\nRequesting Logger Instance 1...");
        Logger logger1 = Logger.getInstance();

        System.out.println("\nRequesting Logger Instance 2...");
        Logger logger2 = Logger.getInstance();

        // 2. Perform reference comparisons
        System.out.println("\n--- Memory Reference Verification ---");
        int hash1 = System.identityHashCode(logger1);
        int hash2 = System.identityHashCode(logger2);

        System.out.println("Logger 1 Memory Reference Hash: " + hash1);
        System.out.println("Logger 2 Memory Reference Hash: " + hash2);

        boolean areEqual = (logger1 == logger2);
        System.out.println("Check: (logger1 == logger2) -> " + areEqual);

        // 3. Validation Check
        if (areEqual) {
            System.out.println("\nSUCCESS: Singleton Pattern verified successfully!");
            System.out.println("Both logger variables reference the same single instance in memory.");
        } else {
            System.out.println("\nFAILURE: Singleton Pattern broken! Multiple Logger instances were created.");
        }

        // 4. Test Logging functionality
        System.out.println("\n--- Testing Log Output ---");
        logger1.log("Transaction Module: Initializing payment portal.");
        logger2.log("Auth Module: User 'ankitgithub12' authenticated successfully.");
        logger1.log("Database Module: Query executed - SELECT * FROM users;");
        logger2.log("Logger Test: Process complete.");

        System.out.println("\nLogging test finished. Please inspect 'system.log' in the execution folder for file output.");
    }
}
