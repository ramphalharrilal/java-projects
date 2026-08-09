import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeStreamAnalysis {

    public static void main(String[] args) {

        // Fictional sample dataset stored in a collection
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Daniel Carter", 28, "IT Support", 45000));
        employees.add(new Employee("Sophia Bennett", 31, "Operations", 52000));
        employees.add(new Employee("John Smith", 42, "Finance", 68000));
        employees.add(new Employee("Maria Garcia", 35, "Human Resources", 58000));
        employees.add(new Employee("David Brown", 25, "Marketing", 39000));
        employees.add(new Employee("Aisha Khan", 38, "Cybersecurity", 72000));

        // Function interface to transform an Employee object into a name-department string
        Function<Employee, String> nameAndDepartmentFunction =
                employee -> employee.getName() + " - " + employee.getDepartment();

        // Stream operation to generate a new collection of concatenated strings
        List<String> employeeNameDepartments = employees.stream()
                .map(nameAndDepartmentFunction)
                .collect(Collectors.toList());

        // Stream operation to calculate average salary
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        // Filter employees above age threshold
        int ageThreshold = 30;

        List<Employee> employeesAboveThirty = employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .collect(Collectors.toList());

        // Additional feature: find employees with salary above the average
        List<Employee> employeesAboveAverageSalary = employees.stream()
                .filter(employee -> employee.getSalary() > averageSalary)
                .collect(Collectors.toList());

        // Display results
        System.out.println("===== Employee Dataset =====");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + ", Age: " + employee.getAge()
                    + ", Department: " + employee.getDepartment()
                    + ", Salary: $" + employee.getSalary());
        }

        System.out.println("\n===== Name and Department List =====");
        for (String result : employeeNameDepartments) {
            System.out.println(result);
        }

        System.out.println("\nAverage Salary: $" + averageSalary);

        System.out.println("\n===== Employees Above Age " + ageThreshold + " =====");
        for (Employee employee : employeesAboveThirty) {
            System.out.println(employee.getName() + " - Age: " + employee.getAge());
        }

        System.out.println("\n===== Employees Above Average Salary =====");
        for (Employee employee : employeesAboveAverageSalary) {
            System.out.println(employee.getName() + " - Salary: $" + employee.getSalary());
        }
    }
}
