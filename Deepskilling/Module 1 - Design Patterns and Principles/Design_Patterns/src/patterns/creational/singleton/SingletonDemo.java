package patterns.creational.singleton;

/**
 * Singleton Pattern:
 * Restricts the instantiation of a class to one "single" instance.
 * Below we demonstrate three thread-safe approaches in Java:
 * 1. Double-Checked Locking (Lazy Initialization with volatile)
 * 2. Bill Pugh Singleton (Lazy initialization using an inner static helper class)
 * 3. Enum Singleton (Thread-safe, reflection-safe, and serialization-safe)
 */
public class SingletonDemo {

    // --- Approach 1: Double-Checked Locking ---
    public static class DoubleCheckedSingleton {
        // volatile keyword ensures that multiple threads handle the instance variable correctly
        private static volatile DoubleCheckedSingleton instance;

        private DoubleCheckedSingleton() {
            System.out.println("DoubleCheckedSingleton: Constructor called.");
        }

        public static DoubleCheckedSingleton getInstance() {
            if (instance == null) { // First check (no locking)
                synchronized (DoubleCheckedSingleton.class) {
                    if (instance == null) { // Second check (with locking)
                        instance = new DoubleCheckedSingleton();
                    }
                }
            }
            return instance;
        }

        public void showMessage() {
            System.out.println("Hello from Double-Checked Locking Singleton!");
        }
    }

    // --- Approach 2: Bill Pugh Singleton (Inner Static Helper) ---
    public static class BillPughSingleton {
        private BillPughSingleton() {
            System.out.println("BillPughSingleton: Constructor called.");
        }

        // Inner class is loaded only when getInstance() is invoked, providing lazy loading.
        private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }

        public void showMessage() {
            System.out.println("Hello from Bill Pugh Singleton!");
        }
    }

    // --- Approach 3: Enum Singleton (Recommended by Joshua Bloch) ---
    public enum EnumSingleton {
        INSTANCE;

        // Enums can have fields, constructors, and methods
        private int value = 0;

        public int getValue() { return value; }
        public void setValue(int val) { this.value = val; }

        public void showMessage() {
            System.out.println("Hello from Enum Singleton! Value: " + value);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===");

        // Test Double-Checked Locking
        System.out.println("\nTesting Double-Checked Locking Singleton:");
        DoubleCheckedSingleton s1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton s2 = DoubleCheckedSingleton.getInstance();
        System.out.println("Are both instances the same? " + (s1 == s2));
        s1.showMessage();

        // Test Bill Pugh
        System.out.println("\nTesting Bill Pugh Singleton:");
        BillPughSingleton b1 = BillPughSingleton.getInstance();
        BillPughSingleton b2 = BillPughSingleton.getInstance();
        System.out.println("Are both instances the same? " + (b1 == b2));
        b1.showMessage();

        // Test Enum Singleton
        System.out.println("\nTesting Enum Singleton:");
        EnumSingleton e1 = EnumSingleton.INSTANCE;
        EnumSingleton e2 = EnumSingleton.INSTANCE;
        e1.setValue(42);
        System.out.println("Are both instances the same? " + (e1 == e2));
        e2.showMessage(); // Will output value 42
    }
}
