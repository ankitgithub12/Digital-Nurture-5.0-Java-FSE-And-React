package solid.srp;

/**
 * SRP Solution:
 * Separate the responsibilities into three distinct classes:
 * 1. Employee: Focuses only on holding employee data.
 * 2. TaxCalculator: Focuses only on calculating taxes and net salary.
 * 3. EmployeeRepository: Focuses only on persistence/database operations.
 *
 * If tax rates change, only TaxCalculator is modified.
 * If database schema changes, only EmployeeRepository is modified.
 * If employee details change, only Employee is modified.
 */
public class SRPSolution {

    // Class 1: Core Entity (Data representation only)
    public static class Employee {
        private final String employeeId;
        private final String name;
        private final double monthlySalary;

        public Employee(String employeeId, String name, double monthlySalary) {
            this.employeeId = employeeId;
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public String getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public double getMonthlySalary() { return monthlySalary; }
    }

    // Class 2: Business Logic / Calculation Service
    public static class TaxCalculator {
        public double calculateNetSalary(Employee employee) {
            double taxRate = 0.20; // Separated tax logic
            return employee.getMonthlySalary() * (1 - taxRate);
        }
    }

    // Class 3: Data Access / Persistence Service
    public static class EmployeeRepository {
        public void saveToDatabase(Employee employee) {
            System.out.println("EmployeeRepository: Connecting to database...");
            System.out.println("EmployeeRepository: Saving " + employee.getName() + " to MySQL Database...");
            System.out.println("EmployeeRepository: Write complete.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SRP Solution Demo ===");
        Employee emp = new Employee("EMP101", "John Doe", 6000.0);
        
        TaxCalculator calculator = new TaxCalculator();
        EmployeeRepository repository = new EmployeeRepository();
        
        System.out.println("Employee: " + emp.getName());
        System.out.println("Calculated Net Salary: $" + calculator.calculateNetSalary(emp));
        repository.saveToDatabase(emp);
    }
}
