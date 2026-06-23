package patterns.structural.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Proxy Pattern:
 * Provides a surrogate or placeholder for another object to control access to it.
 * Below we demonstrate a Protection Proxy that restricts connection to specific
 * blacklisted websites on the office network.
 */
public class ProxyDemo {

    // --- Subject Interface ---
    public interface Internet {
        void connectTo(String serverHost) throws Exception;
    }

    // --- Real Subject ---
    public static class RealInternet implements Internet {
        @Override
        public void connectTo(String serverHost) {
            System.out.println("RealInternet: Connected successfully to " + serverHost);
        }
    }

    // --- Proxy Subject ---
    public static class ProxyInternet implements Internet {
        private final Internet realInternet = new RealInternet();
        private static final List<String> bannedSites = new ArrayList<>();

        static {
            bannedSites.add("bannedsite.com");
            bannedSites.add("malwaresite.org");
            bannedSites.add("socialmedia.com");
        }

        @Override
        public void connectTo(String serverHost) throws Exception {
            // Control Access: Check if server is blacklisted
            if (bannedSites.contains(serverHost.toLowerCase())) {
                throw new SecurityException("Access Denied to " + serverHost + " on this network!");
            }
            // Delegate call to Real Subject if validation passes
            realInternet.connectTo(serverHost);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Demo ===");

        Internet officeNetwork = new ProxyInternet();

        // 1. Access safe sites
        try {
            System.out.println("Accessing Google...");
            officeNetwork.connectTo("google.com");
            System.out.println("Accessing Wikipedia...");
            officeNetwork.connectTo("wikipedia.org");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 2. Access blocked sites
        System.out.println("\nAccessing Restricted Sites:");
        try {
            officeNetwork.connectTo("socialmedia.com");
        } catch (Exception e) {
            System.out.println("Blocked Event: " + e.getMessage());
        }

        try {
            officeNetwork.connectTo("bannedsite.com");
        } catch (Exception e) {
            System.out.println("Blocked Event: " + e.getMessage());
        }
    }
}
