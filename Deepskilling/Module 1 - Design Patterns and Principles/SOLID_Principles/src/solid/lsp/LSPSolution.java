package solid.lsp;

/**
 * LSP Solution:
 * Instead of forcing all birds to fly, we separate the behavior.
 * We keep a general Bird base class (representing common traits, like feathers),
 * and introduce a Flyable interface only for birds that are capable of flight.
 * Eagle implements Flyable, but Ostrich does not.
 * Client methods requiring flight accept Flyable, avoiding runtime exceptions.
 */
public class LSPSolution {

    // Base Class
    public static abstract class Bird {
        private final String name;

        public Bird(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        
        // Common behavior for all birds
        public abstract void eat();
    }

    // Abstraction for flying behavior
    public interface Flyable {
        void fly();
    }

    // Subclass 1: Eagle can fly and eat
    public static class Eagle extends Bird implements Flyable {
        public Eagle() {
            super("Eagle");
        }

        @Override
        public void eat() {
            System.out.println(getName() + " is eating prey.");
        }

        @Override
        public void fly() {
            System.out.println(getName() + " is soaring high in the air!");
        }
    }

    // Subclass 2: Ostrich can eat, but does not fly
    public static class Ostrich extends Bird {
        public Ostrich() {
            super("Ostrich");
        }

        @Override
        public void eat() {
            System.out.println(getName() + " is eating plants and insects.");
        }
    }

    // Client method for flying behavior (only accepts Flyable subtypes)
    public static void travelByAir(Flyable flyingObject) {
        flyingObject.fly();
    }

    public static void main(String[] args) {
        System.out.println("=== LSP Solution Demo ===");
        
        Eagle eagle = new Eagle();
        Ostrich ostrich = new Ostrich();

        System.out.println("Testing Eagle:");
        eagle.eat();
        travelByAir(eagle); // Works fine and compile-time safe

        System.out.println("\nTesting Ostrich:");
        ostrich.eat();
        // travelByAir(ostrich); // COMPILE ERROR! Ostrich is not Flyable.
        System.out.println("Ostrich cannot fly, which is validated at compile-time instead of crashing at runtime.");
    }
}
