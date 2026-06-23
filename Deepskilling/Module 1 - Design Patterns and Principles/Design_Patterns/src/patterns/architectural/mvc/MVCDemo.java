package patterns.architectural.mvc;

/**
 * Model-View-Controller (MVC) Pattern:
 * Separates an application's concerns into three distinct components:
 * 1. Model: Manages the data, business rules, and state of the application.
 * 2. View: The visual display interface presented to the user (e.g. Console output).
 * 3. Controller: Intercepts user inputs, updates the Model, and updates the View.
 */
public class MVCDemo {

    // --- Model (Business Data & Rules) ---
    public static class Student {
        private String rollNo;
        private String name;

        public String getRollNo() { return rollNo; }
        public void setRollNo(String rollNo) { this.rollNo = rollNo; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    // --- View (UI / Output Format) ---
    public static class StudentView {
        public void printStudentDetails(String studentName, String studentRollNo) {
            System.out.println("------------------------------------");
            System.out.println("STUDENT DETAILS DISPLAY:");
            System.out.println("Name   : " + studentName);
            System.out.println("Roll No: " + studentRollNo);
            System.out.println("------------------------------------");
        }
    }

    // --- Controller (Coordinator / Glue) ---
    public static class StudentController {
        private final Student model;
        private final StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        // Methods to change Model details
        public void setStudentName(String name) {
            model.setName(name);
        }

        public String getStudentName() {
            return model.getName();
        }

        public void setStudentRollNo(String rollNo) {
            model.setRollNo(rollNo);
        }

        public String getStudentRollNo() {
            return model.getRollNo();
        }

        // Method to refresh the View with current Model state
        public void updateView() {
            view.printStudentDetails(model.getName(), model.getRollNo());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MVC Architectural Pattern Demo ===");

        // Fetch student record from database / mock service
        Student model = retrieveStudentFromDatabase();

        // Create a view to output student details to console
        StudentView view = new StudentView();

        // Initialize the controller
        StudentController controller = new StudentController(model, view);

        // Display initial model details
        System.out.println("Initial State:");
        controller.updateView();

        // Controller updates model state based on user input / business events
        System.out.println("\nUpdating student details via Controller...");
        controller.setStudentName("Bob Martin");
        controller.setStudentRollNo("FSE2026-99");

        // Display updated model details
        System.out.println("Updated State:");
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        Student student = new Student();
        student.setName("Alice Cooper");
        student.setRollNo("FSE2026-01");
        return student;
    }
}
