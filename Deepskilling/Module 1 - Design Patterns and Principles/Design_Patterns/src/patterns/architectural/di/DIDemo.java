package patterns.architectural.di;

/**
 * Dependency Injection (DI) Pattern:
 * A design pattern that implements Dependency Inversion Principle.
 * It passes dependency objects to the client instead of letting the client create them.
 * This decouples class instantiations from class execution logic.
 */
public class DIDemo {

    // --- Service Interface (Dependency Abstraction) ---
    public interface MessageService {
        void sendMessage(String message, String recipient);
    }

    // --- Concrete Service 1 ---
    public static class EmailService implements MessageService {
        @Override
        public void sendMessage(String message, String recipient) {
            System.out.println("EmailService: Sending Email to " + recipient);
            System.out.println("Message Content: \"" + message + "\"");
        }
    }

    // --- Concrete Service 2 ---
    public static class SMSService implements MessageService {
        @Override
        public void sendMessage(String message, String recipient) {
            System.out.println("SMSService: Sending SMS to " + recipient);
            System.out.println("Message Content: \"" + message + "\"");
        }
    }

    // --- Client Class (Depends on abstraction, gets injected) ---
    public static class MyUserApplication {
        private final MessageService msgService; // Dependency field

        // Constructor Injection
        public MyUserApplication(MessageService service) {
            this.msgService = service;
        }

        public void processMessages(String msg, String rec) {
            // High-level logic does not care whether SMS or Email is being used
            System.out.println("MyUserApplication: Processing notification request...");
            this.msgService.sendMessage(msg, rec);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Demo ===");

        String recipient = "learner@example.com";
        String alertMessage = "Your Design Patterns course has been updated!";

        // 1. Inject EmailService via Constructor Injection
        System.out.println("\n--- Deploying with Email Service ---");
        MessageService emailSvc = new EmailService();
        MyUserApplication emailApp = new MyUserApplication(emailSvc); // Injection point
        emailApp.processMessages(alertMessage, recipient);

        // 2. Inject SMSService via Constructor Injection
        System.out.println("\n--- Deploying with SMS Service ---");
        MessageService smsSvc = new SMSService();
        MyUserApplication smsApp = new MyUserApplication(smsSvc);     // Injection point
        smsApp.processMessages(alertMessage, "+1-555-0199");
    }
}
