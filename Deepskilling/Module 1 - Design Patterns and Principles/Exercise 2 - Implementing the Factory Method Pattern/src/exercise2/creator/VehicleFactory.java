package exercise2.creator;

import exercise2.product.Vehicle;

/**
 * Creator class in the Factory Method Pattern.
 * Declares the factory method to create products, and provides a default
 * operation that uses the created product.
 */
public abstract class VehicleFactory {

    /**
     * Factory Method that subclasses will override to return their specific concrete product.
     * 
     * @return a Vehicle instance
     */
    public abstract Vehicle createVehicle();

    /**
     * Template/helper operation that showcases high-level logic using the factory method.
     * Demonstrates how the creator is decoupled from the concrete product subclass.
     */
    public void testDrive() {
        System.out.println("\n--- Initiating Factory-level Test Drive ---");
        // Polymorphically call the factory method to create the vehicle
        Vehicle vehicle = createVehicle();
        
        // Execute common product interface methods
        vehicle.start();
        vehicle.stop();
        System.out.println("--- Test Drive Completed Successfully ---");
    }
}
