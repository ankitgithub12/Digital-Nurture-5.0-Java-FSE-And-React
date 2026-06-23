package solid.isp;

/**
 * ISP Solution:
 * We split the single "fat" SmartDevice interface into three distinct, small, cohesive interfaces:
 * 1. Printer (containing print())
 * 2. Scanner (containing scan())
 * 3. FaxMachine (containing fax())
 *
 * Now, classes only implement the interfaces they actually support.
 */
public class ISPSolution {

    // Small, segregated interfaces
    public interface Printer {
        void print();
    }

    public interface Scanner {
        void scan();
    }

    public interface FaxMachine {
        void fax();
    }

    // Concrete class implementing only Printer
    public static class EconomicPrinter implements Printer {
        @Override
        public void print() {
            System.out.println("EconomicPrinter: Printing document standard black/white.");
        }
    }

    // Concrete class implementing all interfaces
    public static class AllInOnePrinter implements Printer, Scanner, FaxMachine {
        @Override
        public void print() {
            System.out.println("AllInOnePrinter: Printing document high quality color.");
        }

        @Override
        public void scan() {
            System.out.println("AllInOnePrinter: Scanning document to PDF.");
        }

        @Override
        public void fax() {
            System.out.println("AllInOnePrinter: Sending fax over telephone line.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ISP Solution Demo ===");
        
        Printer basicPrinter = new EconomicPrinter();
        System.out.println("Testing Basic Printer:");
        basicPrinter.print();
        // basicPrinter.scan(); // COMPILE ERROR! No scan capability on Printer interface.

        System.out.println("\nTesting All-In-One Printer:");
        AllInOnePrinter heavyDutyPrinter = new AllInOnePrinter();
        heavyDutyPrinter.print();
        heavyDutyPrinter.scan();
        heavyDutyPrinter.fax();
    }
}
