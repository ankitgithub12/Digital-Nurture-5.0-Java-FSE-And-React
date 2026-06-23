package solid.isp;

/**
 * ISP Violation:
 * The SmartDevice interface is "fat" or "bloated". It forces every implementation 
 * to provide a print, scan, and fax method.
 * An EconomicPrinter only prints, yet it is forced to implement scan() and fax()
 * by throwing exceptions or leaving them empty.
 */
public class ISPViolation {

    public interface SmartDevice {
        void print();
        void scan();
        void fax();
    }

    public static class AllInOnePrinter implements SmartDevice {
        @Override
        public void print() {
            System.out.println("Printing document...");
        }

        @Override
        public void scan() {
            System.out.println("Scanning document...");
        }

        @Override
        public void fax() {
            System.out.println("Faxing document...");
        }
    }

    public static class EconomicPrinter implements SmartDevice {
        @Override
        public void print() {
            System.out.println("Economic printing document...");
        }

        @Override
        public void scan() {
            // Economic printers don't scan!
            throw new UnsupportedOperationException("Scanning not supported.");
        }

        @Override
        public void fax() {
            // Economic printers don't fax!
            throw new UnsupportedOperationException("Faxing not supported.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ISP Violation Demo ===");
        SmartDevice cheapPrinter = new EconomicPrinter();
        
        cheapPrinter.print();
        
        try {
            cheapPrinter.scan(); // Throws exception at runtime!
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
