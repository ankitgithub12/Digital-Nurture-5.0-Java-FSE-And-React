package exercise1;

public class InventoryTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Inventory Management System ===\n");

        Inventory inventory = new Inventory();

        // 1. Add Products
        System.out.println("--- 1. Adding Products ---");
        Product p1 = new Product("P001", "Laptop", 10, 999.99);
        Product p2 = new Product("P002", "Smartphone", 25, 499.99);
        Product p3 = new Product("P003", "Wireless Mouse", 50, 24.99);

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);
        System.out.println();

        // Display current inventory
        inventory.displayProducts();
        System.out.println();

        // 2. Retrieve Product
        System.out.println("--- 2. Retrieving Product ---");
        Product retrieved = inventory.getProduct("P001");
        System.out.println("Retrieved: " + retrieved);
        System.out.println();

        // 3. Update Product
        System.out.println("--- 3. Updating Product ---");
        // Update Laptop quantity and price
        inventory.updateProduct("P001", "Laptop Pro", 8, 1199.99);
        System.out.println();

        // Display inventory after update
        inventory.displayProducts();
        System.out.println();

        // 4. Test Error Handling (Duplicate ID, Not Found ID)
        System.out.println("--- 4. Testing Error Cases ---");
        System.out.println("Attempting to add duplicate product ID (P002):");
        Product duplicate = new Product("P002", "New Phone", 15, 600.0);
        inventory.addProduct(duplicate);

        System.out.println("\nAttempting to update non-existent product (P999):");
        inventory.updateProduct("P999", "Tablet", 5, 299.99);

        System.out.println("\nAttempting to delete non-existent product (P999):");
        inventory.deleteProduct("P999");
        System.out.println();

        // 5. Delete Product
        System.out.println("--- 5. Deleting Product ---");
        inventory.deleteProduct("P003");
        System.out.println();

        // Display final inventory
        inventory.displayProducts();
        System.out.println();

        System.out.println("=== Testing Completed ===");
    }
}
