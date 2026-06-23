package solid.ocp;

/**
 * OCP Violation:
 * The DiscountCalculator class calculates discounts based on customer types.
 * If we want to add a new customer type (e.g. PREMIUM), we must modify the existing 
 * calculateDiscount method by adding another 'case' or 'if' block.
 * This makes the class vulnerable to code modifications whenever business rules grow.
 */
public class OCPViolation {

    public enum CustomerType {
        REGULAR, VIP
    }

    public static class DiscountCalculator {
        public double calculateDiscount(CustomerType type, double amount) {
            switch (type) {
                case REGULAR:
                    return amount * 0.05; // 5% discount
                case VIP:
                    return amount * 0.15; // 15% discount
                // Adding a new type requires modifying this file!
                default:
                    return 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== OCP Violation Demo ===");
        DiscountCalculator calculator = new DiscountCalculator();
        
        double amount = 1000.0;
        System.out.println("Regular Discount: $" + calculator.calculateDiscount(CustomerType.REGULAR, amount));
        System.out.println("VIP Discount: $" + calculator.calculateDiscount(CustomerType.VIP, amount));
    }
}
