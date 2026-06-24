package exercise2.product;

/**
 * Concrete Product representing a Bike.
 */
public class Bike implements Vehicle {

    public Bike() {
        System.out.println("Bike Instance Initialized: Constructor invoked.");
    }

    @Override
    public void start() {
        System.out.println("Bike engine started. Kick-start successful!");
    }

    @Override
    public void stop() {
        System.out.println("Bike engine stopped safely.");
    }
}
