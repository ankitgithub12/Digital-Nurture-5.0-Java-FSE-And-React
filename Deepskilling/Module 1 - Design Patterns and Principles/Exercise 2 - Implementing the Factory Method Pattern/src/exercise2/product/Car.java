package exercise2.product;

/**
 * Concrete Product representing a Car.
 */
public class Car implements Vehicle {

    public Car() {
        System.out.println("Car Instance Initialized: Constructor invoked.");
    }

    @Override
    public void start() {
        System.out.println("Car engine started. Vroom!");
    }

    @Override
    public void stop() {
        System.out.println("Car engine stopped safely.");
    }
}
