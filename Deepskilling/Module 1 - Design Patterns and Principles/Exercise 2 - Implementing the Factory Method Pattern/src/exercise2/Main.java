package exercise2;

import exercise2.creator.BikeFactory;
import exercise2.creator.CarFactory;
import exercise2.creator.VehicleFactory;
import exercise2.product.Vehicle;

/**
 * Client class/runner for the Factory Method pattern.
 * Demonstrates decoupling of product instantiation from high-level client logic.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Running Exercise 2: Factory Method Pattern Demo ===");

        // 1. Demonstrate Car creation via CarFactory
        System.out.println("\n--- [Car Factory Demonstration] ---");
        // High-level reference depends on VehicleFactory abstraction
        VehicleFactory carCreator = new CarFactory();
        
        // Polymorphic creation of Car product
        System.out.println("Requesting vehicle creation from Car Factory...");
        Vehicle car = carCreator.createVehicle();
        
        // Perform operations on Vehicle interface
        car.start();
        car.stop();

        // 2. Demonstrate Bike creation via BikeFactory
        System.out.println("\n--- [Bike Factory Demonstration] ---");
        // High-level reference depends on VehicleFactory abstraction
        VehicleFactory bikeCreator = new BikeFactory();
        
        // Polymorphic creation of Bike product
        System.out.println("Requesting vehicle creation from Bike Factory...");
        Vehicle bike = bikeCreator.createVehicle();
        
        // Perform operations on Vehicle interface
        bike.start();
        bike.stop();

        // 3. Demonstrate high-level template method testDrive()
        // Shows that the creator contains core business logic relying on the product interface.
        System.out.println("\n--- [Template Method testDrive() Demonstration] ---");
        System.out.println("Running testDrive on Car Factory:");
        carCreator.testDrive();

        System.out.println("\nRunning testDrive on Bike Factory:");
        bikeCreator.testDrive();

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Factory Method Pattern verified successfully!");
        System.out.println("Client is completely decoupled from concrete classes (Car, Bike).");
        System.out.println("SOLID Principles Satisfied:");
        System.out.println("  1. Open-Closed Principle (OCP): New vehicle types (e.g., Truck) can be added");
        System.out.println("     by creating new subclasses of Vehicle and VehicleFactory without modifying Main.");
        System.out.println("  2. Dependency Inversion Principle (DIP): Client programs to Vehicle and VehicleFactory");
        System.out.println("     interfaces/abstractions instead of concrete subclasses.");
        System.out.println("=============================================");
    }
}
