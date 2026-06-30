package exercise1;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    /**
     * Adds a product to the inventory.
     * @param product The product to be added.
     * @return true if added successfully, false if product ID already exists.
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            System.out.println("Cannot add null product.");
            return false;
        }
        if (products.containsKey(product.getProductId())) {
            System.out.println("Error: Product with ID " + product.getProductId() + " already exists.");
            return false;
        }
        products.put(product.getProductId(), product);
        System.out.println("Product added successfully: " + product.getProductName());
        return true;
    }

    /**
     * Updates an existing product in the inventory.
     * @param productId The ID of the product to update.
     * @param productName The new name.
     * @param quantity The new quantity.
     * @param price The new price.
     * @return true if updated successfully, false if product not found.
     */
    public boolean updateProduct(String productId, String productName, int quantity, double price) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Error: Product with ID " + productId + " not found.");
            return false;
        }
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);
        System.out.println("Product updated successfully: " + product);
        return true;
    }

    /**
     * Deletes a product from the inventory by its ID.
     * @param productId The ID of the product to delete.
     * @return true if deleted successfully, false if product not found.
     */
    public boolean deleteProduct(String productId) {
        if (!products.containsKey(productId)) {
            System.out.println("Error: Product with ID " + productId + " not found.");
            return false;
        }
        Product removedProduct = products.remove(productId);
        System.out.println("Product deleted successfully: " + removedProduct.getProductName());
        return true;
    }

    /**
     * Retrieves a product by its ID.
     * @param productId The ID of the product.
     * @return The product object, or null if not found.
     */
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    /**
     * Displays all products currently in the inventory.
     */
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("--- Current Inventory ---");
        for (Product product : products.values()) {
            System.out.println(product);
        }
        System.out.println("-------------------------");
    }
}
