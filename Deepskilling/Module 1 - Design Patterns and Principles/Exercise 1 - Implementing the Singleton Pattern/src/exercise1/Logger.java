package exercise1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class utilizing the Singleton Pattern.
 * Ensures a single logging instance is shared across the entire application.
 * Thread-safe using volatile keyword and Double-Checked Locking.
 */
public class Logger {
    // Private volatile instance reference to ensure changes are flushed to main memory
    private static volatile Logger instance;
    private static final Object lock = new Object();

    // Log file configurations
    private static final String LOG_FILE = "system.log";
    private PrintWriter fileWriter;

    // 1. Private Constructor: prevents direct instantiation from outside the class
    private Logger() {
        try {
            // FileWriter is created in append mode (true) so logs are not overwritten
            this.fileWriter = new PrintWriter(new FileWriter(LOG_FILE, true), true);
            System.out.println("Logger Instance Initialized: Private constructor invoked.");
        } catch (IOException e) {
            System.err.println("Logger Error: Failed to initialize file writer. " + e.getMessage());
        }
    }

    // 2. Public Static Accessor Method: returns the unique instance of the class
    public static Logger getInstance() {
        if (instance == null) { // First check (no locking overhead)
            synchronized (lock) {
                if (instance == null) { // Second check (thread-safe validation)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Method to write a message to the logs (both console and system.log file).
     * Automatically prefixes the entry with a timestamp.
     * 
     * @param message String message to log
     */
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = "[" + timestamp + "] INFO: " + message;

        // Output to console
        System.out.println(formattedMessage);

        // Output to log file
        if (fileWriter != null) {
            fileWriter.println(formattedMessage);
        }
    }
}
