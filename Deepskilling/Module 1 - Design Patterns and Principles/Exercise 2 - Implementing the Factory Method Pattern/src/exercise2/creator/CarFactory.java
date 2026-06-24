package exercise2.creator;

import exercise2.product.Car;
import exercise2.product.Vehicle;

/**
 * Concrete Creator that overrides the factory method to return a Car.
 */
public class CarFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        System.out.println("CarFactory: Generating a new Car product instance.");
        return new Car();
    }
}
