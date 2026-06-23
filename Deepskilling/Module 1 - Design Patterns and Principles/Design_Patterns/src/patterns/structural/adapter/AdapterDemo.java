package patterns.structural.adapter;

/**
 * Adapter Pattern:
 * Converts the interface of a class into another interface that clients expect.
 * It lets classes work together that couldn't otherwise because of incompatible interfaces.
 */
public class AdapterDemo {

    // --- Client Target Interface (Injected / Expected standard) ---
    public interface USBTypeCDevice {
        void chargeWithTypeC();
    }

    // --- Concrete Target Class 1 ---
    public static class AndroidPhone implements USBTypeCDevice {
        @Override
        public void chargeWithTypeC() {
            System.out.println("Android Phone: Charging at high speed using USB-C cable.");
        }
    }

    // --- Adaptee (Incompatible Class to adapt) ---
    public static class ApplePhone {
        public void chargeWithLightning() {
            System.out.println("iPhone: Charging using Apple Lightning cable.");
        }
    }

    // --- Adapter (Implements expected interface, delegates to Adaptee) ---
    public static class LightningToUSBTypeCAdapter implements USBTypeCDevice {
        private final ApplePhone iphone;

        public LightningToUSBTypeCAdapter(ApplePhone iphone) {
            this.iphone = iphone;
        }

        @Override
        public void chargeWithTypeC() {
            System.out.print("Adapter translates USB-C connection -> ");
            iphone.chargeWithLightning();
        }
    }

    // Client workstation / Charger
    public static class USBCPowerOutlet {
        public void plugInAndCharge(USBTypeCDevice device) {
            System.out.println("Charger: Device plugged in. Supplying power...");
            device.chargeWithTypeC();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Demo ===");

        USBCPowerOutlet wallCharger = new USBCPowerOutlet();

        // 1. Charge standard Type-C device (Android Phone)
        System.out.println("\nCharging Android phone directly:");
        USBTypeCDevice pixelPhone = new AndroidPhone();
        wallCharger.plugInAndCharge(pixelPhone);

        // 2. Try to charge iPhone (Lightning connector) directly in Type-C wall socket.
        // ApplePhone iphone = new ApplePhone();
        // wallCharger.plugInAndCharge(iphone); // COMPILE ERROR: ApplePhone doesn't implement USBTypeCDevice!

        // 3. Charge iPhone using Adapter
        System.out.println("\nCharging iPhone through Lightning-to-USB-C Adapter:");
        ApplePhone iphone = new ApplePhone();
        USBTypeCDevice adapter = new LightningToUSBTypeCAdapter(iphone);
        wallCharger.plugInAndCharge(adapter); // Success! Works because Adapter implements USBTypeCDevice.
    }
}
