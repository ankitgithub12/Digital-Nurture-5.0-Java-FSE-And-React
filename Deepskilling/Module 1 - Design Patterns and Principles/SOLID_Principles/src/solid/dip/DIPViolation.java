package solid.dip;

/**
 * DIP Violation:
 * The high-level WindowsComputer class directly instantiates and depends upon the 
 * concrete, low-level classes WiredKeyboard and WiredMouse.
 * If we want to upgrade to a WirelessKeyboard, we must rewrite/modify the WindowsComputer class,
 * indicating tight coupling.
 */
public class DIPViolation {

    // Low-level component 1
    public static class WiredKeyboard {
        public void type() {
            System.out.println("Typing on wired keyboard...");
        }
    }

    // Low-level component 2
    public static class WiredMouse {
        public void click() {
            System.out.println("Clicking with wired mouse...");
        }
    }

    // High-level component
    public static class WindowsComputer {
        private final WiredKeyboard keyboard; // Hardcoded concrete dependency
        private final WiredMouse mouse;       // Hardcoded concrete dependency

        public WindowsComputer() {
            // Instantiating low-level details directly
            this.keyboard = new WiredKeyboard();
            this.mouse = new WiredMouse();
        }

        public void run() {
            System.out.println("WindowsComputer: Running boot sequence.");
            keyboard.type();
            mouse.click();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DIP Violation Demo ===");
        WindowsComputer computer = new WindowsComputer();
        computer.run();
    }
}
