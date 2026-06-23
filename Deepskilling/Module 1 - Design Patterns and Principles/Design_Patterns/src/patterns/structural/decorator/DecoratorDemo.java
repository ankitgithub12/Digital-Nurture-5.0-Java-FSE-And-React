package patterns.structural.decorator;

/**
 * Decorator Pattern:
 * Attaches additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 */
public class DecoratorDemo {

    // --- Component Interface ---
    public interface Coffee {
        String getDescription();
        double getCost();
    }

    // --- Concrete Component ---
    public static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() {
            return "Plain Coffee";
        }

        @Override
        public double getCost() {
            return 2.0; // Base cost $2.00
        }
    }

    // --- Abstract Decorator Class (Implements Component, Has Component field) ---
    public static abstract class CoffeeDecorator implements Coffee {
        protected final Coffee decoratedCoffee; // Reference to the wrapped Coffee

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        @Override
        public String getDescription() {
            return decoratedCoffee.getDescription();
        }

        @Override
        public double getCost() {
            return decoratedCoffee.getCost();
        }
    }

    // --- Concrete Decorator 1 ---
    public static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Milk";
        }

        @Override
        public double getCost() {
            return super.getCost() + 0.5; // Adding Milk costs $0.50
        }
    }

    // --- Concrete Decorator 2 ---
    public static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Sugar";
        }

        @Override
        public double getCost() {
            return super.getCost() + 0.2; // Adding Sugar costs $0.20
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Demo ===");

        // 1. Order plain coffee
        Coffee order1 = new SimpleCoffee();
        System.out.println("Order 1: " + order1.getDescription() + " | Cost: $" + order1.getCost());

        // 2. Order Coffee with Milk
        Coffee order2 = new MilkDecorator(new SimpleCoffee());
        System.out.println("Order 2: " + order2.getDescription() + " | Cost: $" + order2.getCost());

        // 3. Order Coffee with Milk and Double Sugar
        Coffee order3 = new SugarDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())));
        System.out.println("Order 3: " + order3.getDescription() + " | Cost: $" + order3.getCost());
    }
}
