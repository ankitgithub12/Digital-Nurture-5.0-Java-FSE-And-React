package exercise2;

public class SearchAlgorithms {

    /**
     * Performs a linear search on the products array to find a product by ID.
     * Time Complexity: O(n) worst/average case, O(1) best case.
     * Space Complexity: O(1).
     *
     * @param products Array of products.
     * @param targetId The product ID to search for.
     * @return The Product if found, otherwise null.
     */
    public static Product linearSearch(Product[] products, String targetId) {
        if (products == null || targetId == null) {
            return null;
        }
        for (Product product : products) {
            if (product != null && product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Performs a binary search on a sorted products array to find a product by ID.
     * Assumes the array is sorted in ascending order of productId.
     * Time Complexity: O(log n) worst/average case, O(1) best case.
     * Space Complexity: O(1).
     *
     * @param products Sorted array of products.
     * @param targetId The product ID to search for.
     * @return The Product if found, otherwise null.
     */
    public static Product binarySearch(Product[] products, String targetId) {
        if (products == null || targetId == null) {
            return null;
        }
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Product midProduct = products[mid];

            if (midProduct == null) {
                // Handle nulls if present (though ideal arrays shouldn't have them)
                high--;
                continue;
            }

            int comparison = midProduct.getProductId().compareTo(targetId);

            if (comparison == 0) {
                return midProduct;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
