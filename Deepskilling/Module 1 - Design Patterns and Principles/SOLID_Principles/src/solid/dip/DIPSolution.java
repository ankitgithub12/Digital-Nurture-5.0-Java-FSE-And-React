package solid.dip;

/**
 * DIP Solution:
 * Introduce abstractions (Keyboard and Mouse interfaces).
 * The Computer class now depends on these interfaces rather than concrete classes.
 * We inject concrete implementations (e.g. WirelessKeyboard, WiredMouse) at runtime.
 * We can change the keyboard or mouse type without modifying the Computer class.
 */
public class DIPSolution {

    // Abstractions
    public interface Keyboard {
        void type();
    }

    public interface Mouse {
        void click();
    }

    // Concrete low-level component 1 (Wired Keyboard)
    public static class WiredKeyboard implements Keyboard {
        @Override
        public void type() {
            System.out.println("WiredKeyboard: Typing...");
        }
    }

    // Concrete low-level component 2 (Wireless Keyboard)
    public static class WirelessKeyboard implements Keyboard {
        @Override
        public void type() {
            System.out.println("WirelessKeyboard: Typing via Bluetooth...");
        }
    }

    // Concrete low-level component 3 (Wired Mouse)
    public static class WiredMouse implements Mouse {
        @Override
        public void click() {
            System.out.println("WiredMouse: Click.");
        }
    }

    // Concrete low-level component 4 (Wireless Mouse)
    public static class WirelessMouse implements Mouse {
        @Override
        public void click() {
            System.out.println("WirelessMouse: Click via RF receiver.");
        }
    }

    // High-level component (Depends on abstractions only)
    public static class Computer {
        private final Keyboard keyboard; // Dependent on abstraction interface
        private final Mouse mouse;       // Dependent on abstraction interface

        // Dependencies are injected via the constructor
        public Computer(Keyboard keyboard, Mouse mouse) {
            this.keyboard = keyboard;
            this.mouse = mouse;
        }

        public void run() {
            System.out.println("Computer: Boot complete.");
            keyboard.type();
            mouse.click();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DIP Solution Demo ===");

        System.out.println("Setup 1: Wired Keyboard and Wired Mouse");
        Keyboard wiredK = new WiredKeyboard();
        Mouse wiredM = new WiredMouse();
        Computer standardPC = new Computer(wiredK, wiredM);
        standardPC.run();

        System.out.println("\nSetup 2: Upgrading to Wireless peripherals without editing Computer class");
        Keyboard wirelessK = new WirelessKeyboard();
        Mouse wirelessM = new WirelessMouse();
        Computer modernPC = new Computer(wirelessK, wirelessM);
        modernPC.run();
    }
}
