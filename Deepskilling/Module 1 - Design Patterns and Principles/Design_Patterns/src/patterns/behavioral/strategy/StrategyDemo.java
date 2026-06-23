package patterns.behavioral.strategy;

/**
 * Strategy Pattern:
 * Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it.
 * This completely avoids massive if-else or switch statements for selecting algorithms.
 */
public class StrategyDemo {

    // --- Strategy Interface ---
    public interface PaymentStrategy {
        void pay(double amount);
    }

    // --- Concrete Strategy 1 ---
    public static class CreditCardPayment implements PaymentStrategy {
        private final String cardNumber;
        private final String cardHolder;

        public CreditCardPayment(String cardNumber, String cardHolder) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card ending in " 
                    + cardNumber.substring(cardNumber.length() - 4) + " (Holder: " + cardHolder + ").");
        }
    }

    // --- Concrete Strategy 2 ---
    public static class PayPalPayment implements PaymentStrategy {
        private final String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal Account (" + email + ").");
        }
    }

    // --- Context Class ---
    public static class ShoppingCart {
        private double totalAmount = 0.0;

        public void addItem(String itemName, double price) {
            System.out.println("Cart: Added " + itemName + " | Price: $" + price);
            totalAmount += price;
        }

        // The checkout method accepts the algorithm strategy dynamically
        public void checkout(PaymentStrategy paymentMethod) {
            if (totalAmount <= 0) {
                System.out.println("Cart is empty! Nothing to pay.");
                return;
            }
            paymentMethod.pay(totalAmount);
            totalAmount = 0.0; // Clear cart after successful checkout
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo ===");

        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Mechanical Keyboard", 120.00);
        cart.addItem("Wireless Gaming Mouse", 80.00);

        // 1. Checkout using Credit Card strategy
        System.out.println("\nExecuting checkout with Credit Card:");
        PaymentStrategy ccStrategy = new CreditCardPayment("1234-5678-9876-5432", "Alice Smith");
        cart.checkout(ccStrategy);

        // 2. Add item and checkout using PayPal strategy
        System.out.println("\nExecuting another transaction:");
        cart.addItem("Java Design Patterns Book", 45.00);
        PaymentStrategy paypalStrategy = new PayPalPayment("alice.smith@example.com");
        cart.checkout(paypalStrategy);
    }
}
