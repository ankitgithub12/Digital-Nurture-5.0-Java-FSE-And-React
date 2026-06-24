package exercise2.creator;

import exercise2.product.Bike;
import exercise2.product.Vehicle;

/**
 * Concrete Creator that overrides the factory method to return a Bike.
 */
public class BikeFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        System.out.println("BikeFactory: Generating a new Bike product instance.");
        return new Bike();
    }
}
