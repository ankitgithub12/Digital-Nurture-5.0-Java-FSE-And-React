package solid.lsp;

/**
 * LSP Violation:
 * A client method expects that ANY subclass of Bird can execute fly() correctly.
 * However, Ostrich is a Bird but cannot fly, so it throws an exception.
 * This breaks the substitutability of the parent type (Bird) with its subtype (Ostrich).
 */
public class LSPViolation {

    public static class Bird {
        public void fly() {
            System.out.println("Bird is flying in the sky.");
        }
    }

    public static class Eagle extends Bird {
        @Override
        public void fly() {
            System.out.println("Eagle is soaring high!");
        }
    }

    public static class Ostrich extends Bird {
        @Override
        public void fly() {
            // Ostriches cannot fly! This violates LSP by throwing an exception
            // when substituted into code that expects flying behavior.
            throw new UnsupportedOperationException("Ostriches cannot fly.");
        }
    }

    // Client method that trusts the abstraction
    public static void travelByAir(Bird bird) {
        bird.fly();
    }

    public static void main(String[] args) {
        System.out.println("=== LSP Violation Demo ===");
        Bird eagle = new Eagle();
        Bird ostrich = new Ostrich();

        System.out.println("Testing Eagle...");
        travelByAir(eagle); // Works fine

        System.out.println("\nTesting Ostrich...");
        try {
            travelByAir(ostrich); // Crashes the program!
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Substitution failed! Ostrich broke the contract of class Bird.");
        }
    }
}
