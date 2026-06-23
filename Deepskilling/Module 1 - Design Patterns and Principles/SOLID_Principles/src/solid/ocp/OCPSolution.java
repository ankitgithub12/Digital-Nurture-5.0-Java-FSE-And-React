package solid.ocp;

/**
 * OCP Solution:
 * We define a DiscountStrategy interface.
 * Each customer type is encapsulated in its own class implementing this interface.
 * The DiscountCalculator class depends on the abstraction (DiscountStrategy).
 * To add a new customer type (e.g. Premium), we simply write a new class without
 * touching any existing, tested calculator code.
 */
public class OCPSolution {

    // Abstraction (Open for Extension)
    public interface DiscountStrategy {
        double calculate(double amount);
    }

    // Concrete Extension 1
    public static class RegularDiscount implements DiscountStrategy {
        @Override
        public double calculate(double amount) {
            return amount * 0.05; // 5% discount
        }
    }

    // Concrete Extension 2
    public static class VIPDiscount implements DiscountStrategy {
        @Override
        public double calculate(double amount) {
            return amount * 0.15; // 15% discount
        }
    }

    // Concrete Extension 3 (Added without modifying existing code)
    public static class PremiumDiscount implements DiscountStrategy {
        @Override
        public double calculate(double amount) {
            return amount * 0.25; // 25% discount
        }
    }

    // Closed for Modification
    public static class DiscountCalculator {
        public double calculateDiscount(DiscountStrategy strategy, double amount) {
            return strategy.calculate(amount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== OCP Solution Demo ===");
        DiscountCalculator calculator = new DiscountCalculator();
        double amount = 1000.0;

        DiscountStrategy regular = new RegularDiscount();
        DiscountStrategy vip = new VIPDiscount();
        DiscountStrategy premium = new PremiumDiscount();

        System.out.println("Regular Discount: $" + calculator.calculateDiscount(regular, amount));
        System.out.println("VIP Discount: $" + calculator.calculateDiscount(vip, amount));
        System.out.println("Premium Discount: $" + calculator.calculateDiscount(premium, amount));
    }
}
