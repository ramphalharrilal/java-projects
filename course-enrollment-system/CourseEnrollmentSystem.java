import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String name;
    private String id;
    private ArrayList<Course> enrolledCourses;
    private HashMap<String, Double> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void enrollStudent(Course course) {
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, double grade) {
        grades.put(course.getCourseCode(), grade);
    }

    public void displayStudentDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);

        if (enrolledCourses.isEmpty()) {
            System.out.println("Enrolled Courses: None");
        } else {
            System.out.println("Enrolled Courses:");
            for (Course course : enrolledCourses) {
                System.out.println("- " + course.getCourseCode() + " - " + course.getCourseName());
            }
        }
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maximumCapacity;
    private int currentEnrollment;
    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maximumCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maximumCapacity = maximumCapacity;
        this.currentEnrollment = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    public boolean hasAvailableSpace() {
        return currentEnrollment < maximumCapacity;
    }

    public void addEnrollment() {
        currentEnrollment++;
        totalEnrolledStudents++;
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Maximum Capacity: " + maximumCapacity);
        System.out.println("Current Enrollment: " + currentEnrollment);
    }
}

class CourseManagement {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static HashMap<String, Double> overallCourseGrades = new HashMap<>();

    public static void addCourse(String courseCode, String courseName, int maximumCapacity) {
        if (findCourseByCode(courseCode) != null) {
            System.out.println("Error: Course code already exists.");
            return;
        }

        Course newCourse = new Course(courseCode, courseName, maximumCapacity);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }

    public static void addStudent(String name, String id) {
        if (findStudentById(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }

        Student newStudent = new Student(name, id);
        students.add(newStudent);
        System.out.println("Student added successfully.");
    }

    public static void enrollStudent(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("Error: Student or course not found.");
            return;
        }

        if (!course.hasAvailableSpace()) {
            System.out.println("Error: Course has reached maximum capacity.");
            return;
        }

        if (student.getEnrolledCourses().contains(course)) {
            System.out.println("Error: Student is already enrolled in this course.");
            return;
        }

        student.enrollStudent(course);
        course.addEnrollment();
        System.out.println("Student enrolled successfully.");
    }

    public static void assignGrade(Student student, Course course, double grade) {
        if (student == null || course == null) {
            System.out.println("Error: Student or course not found.");
            return;
        }

        if (!student.getEnrolledCourses().contains(course)) {
            System.out.println("Error: Student is not enrolled in this course.");
            return;
        }

        if (grade < 0 || grade > 100) {
            System.out.println("Error: Grade must be between 0 and 100.");
            return;
        }

        student.assignGrade(course, grade);
        System.out.println("Grade assigned successfully.");
    }

    public static double calculateOverallGrade(Student student) {
        if (student == null) {
            System.out.println("Error: Student not found.");
            return 0;
        }

        HashMap<String, Double> grades = student.getGrades();

        if (grades.isEmpty()) {
            System.out.println("No grades available for this student.");
            return 0;
        }

        double total = 0;

        for (double grade : grades.values()) {
            total += grade;
        }

        double overallGrade = total / grades.size();
        overallCourseGrades.put(student.getId(), overallGrade);

        return overallGrade;
    }

    public static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public static void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\n===== Course List =====");
        for (Course course : courses) {
            course.displayCourseDetails();
            System.out.println("----------------------");
        }

        System.out.println("Total Enrolled Students Across All Courses: " + Course.getTotalEnrolledStudents());
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n===== Student List =====");
        for (Student student : students) {
            student.displayStudentDetails();
            System.out.println("----------------------");
        }
    }
}

public class CourseEnrollmentSystem {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Course Enrollment and Grade Management System =====");
            System.out.println("1. Add New Course");
            System.out.println("2. Add New Student");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade");
            System.out.println("5. Calculate Overall Grade");
            System.out.println("6. View All Courses");
            System.out.println("7. View All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addCourseInterface();
                    break;
                case 2:
                    addStudentInterface();
                    break;
                case 3:
                    enrollStudentInterface();
                    break;
                case 4:
                    assignGradeInterface();
                    break;
                case 5:
                    calculateGradeInterface();
                    break;
                case 6:
                    CourseManagement.viewAllCourses();
                    break;
                case 7:
                    CourseManagement.viewAllStudents();
                    break;
                case 8:
                    System.out.println("Exiting program. Thank you.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void addCourseInterface() {
        System.out.print("Enter course code: ");
        String courseCode = input.nextLine();

        System.out.print("Enter course name: ");
        String courseName = input.nextLine();

        int maximumCapacity = readPositiveInteger("Enter maximum capacity: ");

        CourseManagement.addCourse(courseCode, courseName, maximumCapacity);
    }

    private static void addStudentInterface() {
        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student ID: ");
        String id = input.nextLine();

        CourseManagement.addStudent(name, id);
    }

    private static void enrollStudentInterface() {
        System.out.print("Enter student ID: ");
        String studentId = input.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = input.nextLine();

        Student student = CourseManagement.findStudentById(studentId);
        Course course = CourseManagement.findCourseByCode(courseCode);

        CourseManagement.enrollStudent(student, course);
    }

    private static void assignGradeInterface() {
        System.out.print("Enter student ID: ");
        String studentId = input.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = input.nextLine();

        double grade = readValidGrade("Enter grade: ");

        Student student = CourseManagement.findStudentById(studentId);
        Course course = CourseManagement.findCourseByCode(courseCode);

        CourseManagement.assignGrade(student, course, grade);
    }

    private static void calculateGradeInterface() {
        System.out.print("Enter student ID: ");
        String studentId = input.nextLine();

        Student student = CourseManagement.findStudentById(studentId);
        double overallGrade = CourseManagement.calculateOverallGrade(student);

        if (overallGrade > 0) {
            System.out.println("Overall Grade for " + student.getName() + ": " + overallGrade);
        }
    }

    private static int readPositiveInteger(String message) {
        while (true) {
            System.out.print(message);

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
                continue;
            }

            int number = input.nextInt();
            input.nextLine();

            if (number <= 0) {
                System.out.println("Invalid input. Number must be greater than 0.");
                continue;
            }

            return number;
        }
    }

    private static double readValidGrade(String message) {
        while (true) {
            System.out.print(message);

            if (!input.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid grade.");
                input.nextLine();
                continue;
            }

            double grade = input.nextDouble();
            input.nextLine();

            if (grade < 0 || grade > 100) {
                System.out.println("Invalid input. Grade must be between 0 and 100.");
                continue;
            }

            return grade;
        }
    }
}