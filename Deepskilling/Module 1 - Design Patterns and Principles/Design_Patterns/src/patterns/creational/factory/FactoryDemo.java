package patterns.creational.factory;

/**
 * Factory Method Pattern:
 * Defines an interface for creating objects, but lets subclasses decide which class to instantiate.
 * This decouples the client code from concrete constructors.
 */
public class FactoryDemo {

    // --- Product Interface ---
    public interface Notification {
        void notifyUser();
    }

    // --- Concrete Product 1 ---
    public static class EmailNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending an Email notification containing system reports...");
        }
    }

    // --- Concrete Product 2 ---
    public static class SMSNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending an SMS notification containing OTP authorization...");
        }
    }

    // --- Concrete Product 3 ---
    public static class PushNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending a Push notification alert to mobile app...");
        }
    }

    // --- Creator / Factory (Simplifies instantiation and encapsulation) ---
    public static class NotificationFactory {
        public Notification createNotification(String channel) {
            if (channel == null || channel.isEmpty()) {
                return null;
            }
            switch (channel.toUpperCase()) {
                case "EMAIL":
                    return new EmailNotification();
                case "SMS":
                    return new SMSNotification();
                case "PUSH":
                    return new PushNotification();
                default:
                    throw new IllegalArgumentException("Unknown notification channel: " + channel);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===");

        NotificationFactory factory = new NotificationFactory();

        // Client creates notifications through the factory
        Notification n1 = factory.createNotification("EMAIL");
        n1.notifyUser();

        Notification n2 = factory.createNotification("SMS");
        n2.notifyUser();

        Notification n3 = factory.createNotification("PUSH");
        n3.notifyUser();
        
        System.out.println("\nClient doesn't know about 'EmailNotification' or 'SMSNotification' classes.");
        System.out.println("It only programs to the 'Notification' interface, enabling easy updates.");
    }
}
