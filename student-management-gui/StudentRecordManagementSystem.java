import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String id;
    private int age;
    private String grade;

    public Student(String name, String id, int age, String grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void updateStudentInfo(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public void displayStudentDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}

class StudentManagement {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    public static void addStudent(String name, String id, int age, String grade) {
        if (findStudentById(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }

        Student newStudent = new Student(name, id, age, grade);
        studentList.add(newStudent);
        totalStudents++;

        System.out.println("Student added successfully.");
    }

    public static void updateStudent(String id, String name, int age, String grade) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Error: Student ID not found.");
            return;
        }

        student.updateStudentInfo(name, age, grade);
        System.out.println("Student information updated successfully.");
    }

    public static void viewStudentDetails(String id) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Error: Student ID not found.");
            return;
        }

        student.displayStudentDetails();
    }

    public static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\nTotal Students: " + totalStudents);

        for (Student student : studentList) {
            System.out.println("----------------------");
            student.displayStudentDetails();
        }
    }

    private static Student findStudentById(String id) {
        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }
}

public class StudentRecordManagementSystem {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Student Record Management System =====");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addStudentInterface();
                    break;
                case 2:
                    updateStudentInterface();
                    break;
                case 3:
                    viewStudentInterface();
                    break;
                case 4:
                    StudentManagement.viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting program. Thank you.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void addStudentInterface() {
        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student ID: ");
        String id = input.nextLine();

        int age = readValidAge();

        System.out.print("Enter student grade: ");
        String grade = input.nextLine();

        StudentManagement.addStudent(name, id, age, grade);
    }

    private static void updateStudentInterface() {
        System.out.print("Enter student ID to update: ");
        String id = input.nextLine();

        System.out.print("Enter updated name: ");
        String name = input.nextLine();

        int age = readValidAge();

        System.out.print("Enter updated grade: ");
        String grade = input.nextLine();

        StudentManagement.updateStudent(id, name, age, grade);
    }

    private static void viewStudentInterface() {
        System.out.print("Enter student ID to view: ");
        String id = input.nextLine();

        StudentManagement.viewStudentDetails(id);
    }

    private static int readValidAge() {
        while (true) {
            System.out.print("Enter student age: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Age must be a number.");
                input.nextLine();
                continue;
            }

            int age = input.nextInt();
            input.nextLine();

            if (age <= 0) {
                System.out.println("Invalid input. Age must be greater than 0.");
                continue;
            }

            return age;
        }
    }
}