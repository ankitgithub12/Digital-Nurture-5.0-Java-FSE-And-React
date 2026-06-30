package exercise2;

import java.util.Arrays;

public class SearchTest {
    public static void main(String[] args) {
        System.out.println("=== Testing E-commerce Platform Search Function ===\n");

        // 1. Initialize dataset
        Product[] products = {
            new Product("P105", "Mechanical Keyboard", "Accessories"),
            new Product("P101", "Gaming Laptop", "Electronics"),
            new Product("P103", "Noise Cancelling Headphones", "Audio"),
            new Product("P102", "OLED Monitor", "Electronics"),
            new Product("P104", "Ergonomic Office Chair", "Furniture")
        };

        System.out.println("--- Original Product Array ---");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println();

        // 2. Test Linear Search
        System.out.println("--- 2. Testing Linear Search ---");
        String target1 = "P103";
        System.out.println("Searching for existing product (ID: " + target1 + "):");
        Product resultLinear1 = SearchAlgorithms.linearSearch(products, target1);
        System.out.println("Result: " + (resultLinear1 != null ? resultLinear1 : "Not Found"));

        String target2 = "P199";
        System.out.println("\nSearching for non-existing product (ID: " + target2 + "):");
        Product resultLinear2 = SearchAlgorithms.linearSearch(products, target2);
        System.out.println("Result: " + (resultLinear2 != null ? resultLinear2 : "Not Found"));
        System.out.println();

        // 3. Sort Array for Binary Search
        System.out.println("--- 3. Sorting Array by Product ID ---");
        Arrays.sort(products);
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println();

        // 4. Test Binary Search
        System.out.println("--- 4. Testing Binary Search ---");
        System.out.println("Searching for existing product (ID: " + target1 + "):");
        Product resultBinary1 = SearchAlgorithms.binarySearch(products, target1);
        System.out.println("Result: " + (resultBinary1 != null ? resultBinary1 : "Not Found"));

        System.out.println("\nSearching for non-existing product (ID: " + target2 + "):");
        Product resultBinary2 = SearchAlgorithms.binarySearch(products, target2);
        System.out.println("Result: " + (resultBinary2 != null ? resultBinary2 : "Not Found"));
        System.out.println();

        System.out.println("=== Testing Completed ===");
    }
}
