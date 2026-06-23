package solid.srp;

/**
 * SRP Violation:
 * The Employee class has three distinct reasons to change:
 * 1. Changes to core employee properties (id, name).
 * 2. Changes to payroll/tax calculation rules.
 * 3. Changes to persistence mechanisms (database logic).
 */
public class SRPViolation {

    public static class Employee {
        private String employeeId;
        private String name;
        private double monthlySalary;

        public Employee(String employeeId, String name, double monthlySalary) {
            this.employeeId = employeeId;
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public String getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public double getMonthlySalary() { return monthlySalary; }

        // Responsibility 1: Calculate Salary (Business Rules)
        public double calculateNetSalary() {
            double taxRate = 0.20; // Hardcoded tax rules
            return monthlySalary * (1 - taxRate);
        }

        // Responsibility 2: Database Persistence (Storage Rules)
        public void saveToDatabase() {
            System.out.println("Database Connection established.");
            System.out.println("Saving employee " + name + " to MySQL Database table 'employees'...");
            System.out.println("Employee saved successfully.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SRP Violation Demo ===");
        Employee emp = new Employee("EMP101", "John Doe", 6000.0);
        
        System.out.println("Employee: " + emp.getName());
        System.out.println("Calculated Net Salary: $" + emp.calculateNetSalary());
        emp.saveToDatabase();
    }
}
