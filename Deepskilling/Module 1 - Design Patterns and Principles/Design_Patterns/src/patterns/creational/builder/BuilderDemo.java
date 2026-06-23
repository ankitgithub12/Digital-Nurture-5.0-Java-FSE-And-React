package patterns.creational.builder;

/**
 * Builder Pattern:
 * Separates the construction of a complex object from its representation,
 * enabling step-by-step construction and solving the "Telescoping Constructor" problem
 * (which arises when there are many optional constructor parameters).
 */
public class BuilderDemo {

    // --- Complex Product ---
    public static class Computer {
        // Required parameters
        private final String HDD;
        private final String RAM;
        
        // Optional parameters
        private final boolean isGraphicsCardEnabled;
        private final boolean isBluetoothEnabled;

        public String getHDD() { return HDD; }
        public String getRAM() { return RAM; }
        public boolean isGraphicsCardEnabled() { return isGraphicsCardEnabled; }
        public boolean isBluetoothEnabled() { return isBluetoothEnabled; }

        // Private constructor so objects can only be created via the Builder
        private Computer(ComputerBuilder builder) {
            this.HDD = builder.HDD;
            this.RAM = builder.RAM;
            this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
            this.isBluetoothEnabled = builder.isBluetoothEnabled;
        }

        @Override
        public String toString() {
            return "Computer [HDD=" + HDD + ", RAM=" + RAM 
                    + ", GraphicsCard=" + isGraphicsCardEnabled 
                    + ", Bluetooth=" + isBluetoothEnabled + "]";
        }

        // --- Inner Static Builder Class ---
        public static class ComputerBuilder {
            // Required parameters
            private final String HDD;
            private final String RAM;
            
            // Optional parameters - initialized to default values
            private boolean isGraphicsCardEnabled = false;
            private boolean isBluetoothEnabled = false;

            // Constructor for mandatory fields
            public ComputerBuilder(String hdd, String ram) {
                this.HDD = hdd;
                this.RAM = ram;
            }

            // Setter-like methods returning the builder instance for chaining
            public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
                this.isGraphicsCardEnabled = isGraphicsCardEnabled;
                return this; // Return self to allow method chaining
            }

            public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
                this.isBluetoothEnabled = isBluetoothEnabled;
                return this; // Return self to allow method chaining
            }

            // Build method that instantiates the target object
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===");

        // Setup 1: High-end Gaming PC (all features enabled)
        Computer gamingPC = new Computer.ComputerBuilder("2TB NVMe SSD", "32GB DDR5")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Gaming PC: " + gamingPC);

        // Setup 2: Budget Office PC (optional features left to default: false)
        Computer officePC = new Computer.ComputerBuilder("500GB HDD", "8GB DDR4")
                .build();
        System.out.println("Office PC: " + officePC);
        
        // Setup 3: Server PC (graphics card disabled, but bluetooth enabled)
        Computer serverPC = new Computer.ComputerBuilder("10TB RAID SSD", "64GB ECC RAM")
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Server PC: " + serverPC);
    }
}
