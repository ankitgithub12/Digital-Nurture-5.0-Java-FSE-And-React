package patterns.behavioral.command;

/**
 * Command Pattern:
 * Encapsulates a request as an object, thereby letting you parameterize clients 
 * with different requests, queue or log requests, and support undoable operations.
 */
public class CommandDemo {

    // --- Command Interface ---
    public interface Command {
        void execute();
        void undo();
    }

    // --- Receiver (The component that knows how to perform the actual action) ---
    public static class Light {
        private final String roomName;

        public Light(String roomName) {
            this.roomName = roomName;
        }

        public void turnOn() {
            System.out.println("Light [" + roomName + "]: Turned ON.");
        }

        public void turnOff() {
            System.out.println("Light [" + roomName + "]: Turned OFF.");
        }
    }

    // --- Concrete Command 1 (Turn Light On) ---
    public static class LightOnCommand implements Command {
        private final Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }

        @Override
        public void undo() {
            light.turnOff(); // Undo of turning on is turning off
        }
    }

    // --- Concrete Command 2 (Turn Light Off) ---
    public static class LightOffCommand implements Command {
        private final Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }

        @Override
        public void undo() {
            light.turnOn(); // Undo of turning off is turning on
        }
    }

    // --- Invoker (The component that requests the command to execute) ---
    public static class RemoteControl {
        private Command currentCommand;
        private Command lastExecutedCommand; // Keeps track for undo operations

        public void setCommand(Command command) {
            this.currentCommand = command;
        }

        public void pressButton() {
            if (currentCommand != null) {
                currentCommand.execute();
                lastExecutedCommand = currentCommand;
            } else {
                System.out.println("RemoteControl: No command set.");
            }
        }

        public void pressUndo() {
            if (lastExecutedCommand != null) {
                System.out.print("RemoteControl (Undo Pressed) -> ");
                lastExecutedCommand.undo();
                lastExecutedCommand = null; // Clear after one undo
            } else {
                System.out.println("RemoteControl: Nothing to undo.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Command Pattern Demo ===");

        // Create Receiver
        Light livingRoomLight = new Light("Living Room");

        // Create Commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create Invoker
        RemoteControl remote = new RemoteControl();

        // 1. Turn light on
        System.out.println("\nPressing Turn On Button:");
        remote.setCommand(lightOn);
        remote.pressButton();

        // 2. Undo turning light on
        System.out.println("\nUndoing previous operation:");
        remote.pressUndo();

        // 3. Turn light off
        System.out.println("\nPressing Turn Off Button:");
        remote.setCommand(lightOff);
        remote.pressButton();

        // 4. Undo turning light off
        System.out.println("\nUndoing previous operation:");
        remote.pressUndo();
    }
}
