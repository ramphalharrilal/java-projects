import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Student {
    private String id;
    private String name;
    private int age;
    private ArrayList<String> enrolledCourses;
    private HashMap<String, String> grades;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public HashMap<String, String> getGrades() {
        return grades;
    }

    public void enrollCourse(String course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            grades.put(course, "Not Assigned");
        }
    }

    public void assignGrade(String course, String grade) {
        if (enrolledCourses.contains(course)) {
            grades.put(course, grade);
        }
    }
}

public class StudentManagementGUI extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> gradeStudentComboBox;
    private JComboBox<String> gradeCourseComboBox;
    private JTextField gradeField;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private String[] courses = {
            "Programming 1",
            "Database Systems",
            "Networking",
            "Web Development"
    };

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenuBar();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Records", createStudentPanel());
        tabbedPane.addTab("Course Enrollment", createEnrollmentPanel());
        tabbedPane.addTab("Grade Management", createGradePanel());

        add(tabbedPane);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        JLabel idLabel = new JLabel("Student ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);

        tableModel = new DefaultTableModel(
                new String[]{"Student ID", "Name", "Age", "Courses", "Grades"}, 0
        );

        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Student Details"));

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Enroll Student in Course"));

        JLabel studentLabel = new JLabel("Select Student:");
        JLabel courseLabel = new JLabel("Select Course:");

        studentComboBox = new JComboBox<>();
        courseComboBox = new JComboBox<>(courses);

        JButton enrollButton = new JButton("Enroll Student");

        enrollButton.addActionListener(e -> enrollStudent());

        panel.add(studentLabel);
        panel.add(studentComboBox);
        panel.add(courseLabel);
        panel.add(courseComboBox);
        panel.add(new JLabel(""));
        panel.add(enrollButton);

        return panel;
    }

    private JPanel createGradePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Assign Student Grade"));

        JLabel studentLabel = new JLabel("Select Student:");
        JLabel courseLabel = new JLabel("Select Course:");
        JLabel gradeLabel = new JLabel("Enter Grade:");

        gradeStudentComboBox = new JComboBox<>();
        gradeCourseComboBox = new JComboBox<>();
        gradeField = new JTextField();

        JButton assignGradeButton = new JButton("Assign Grade");

        gradeStudentComboBox.addActionListener(e -> updateGradeCourseComboBox());
        assignGradeButton.addActionListener(e -> assignGrade());

        panel.add(studentLabel);
        panel.add(gradeStudentComboBox);
        panel.add(courseLabel);
        panel.add(gradeCourseComboBox);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(new JLabel(""));
        panel.add(assignGradeButton);

        return panel;
    }

    private void addStudent() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all student fields.");
                return;
            }

            if (findStudentById(id) != null) {
                JOptionPane.showMessageDialog(this, "Student ID already exists.");
                return;
            }

            int age = Integer.parseInt(ageText);

            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be greater than 0.");
                return;
            }

            Student student = new Student(id, name, age);
            students.add(student);

            clearStudentFields();
            refreshInterface();

            JOptionPane.showMessageDialog(this, "Student added successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.");
        }
    }

    private void updateStudent() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter the Student ID to update.");
                return;
            }

            Student student = findStudentById(id);

            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student ID not found.");
                return;
            }

            if (!name.isEmpty()) {
                student.setName(name);
            }

            if (!ageText.isEmpty()) {
                int age = Integer.parseInt(ageText);

                if (age <= 0) {
                    JOptionPane.showMessageDialog(this, "Age must be greater than 0.");
                    return;
                }

                student.setAge(age);
            }

            clearStudentFields();
            refreshInterface();

            JOptionPane.showMessageDialog(this, "Student information updated successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.");
        }
    }

    private void enrollStudent() {
        Student student = getSelectedStudent(studentComboBox);

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        String course = (String) courseComboBox.getSelectedItem();

        if (course == null) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        if (student.getEnrolledCourses().contains(course)) {
            JOptionPane.showMessageDialog(this, "Student is already enrolled in this course.");
            return;
        }

        student.enrollCourse(course);
        refreshInterface();

        JOptionPane.showMessageDialog(this, "Student enrolled in course successfully.");
    }

    private void assignGrade() {
        Student student = getSelectedStudent(gradeStudentComboBox);

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        String course = (String) gradeCourseComboBox.getSelectedItem();
        String grade = gradeField.getText().trim();

        if (course == null) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        if (grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a grade.");
            return;
        }

        student.assignGrade(course, grade);
        gradeField.setText("");
        refreshInterface();

        JOptionPane.showMessageDialog(this, "Grade assigned successfully.");
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    private Student getSelectedStudent(JComboBox<String> comboBox) {
        String selectedId = (String) comboBox.getSelectedItem();

        if (selectedId == null) {
            return null;
        }

        return findStudentById(selectedId);
    }

    private void refreshInterface() {
        updateStudentTable();
        updateStudentComboBoxes();
        updateGradeCourseComboBox();
    }

    private void updateStudentTable() {
        tableModel.setRowCount(0);

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getEnrolledCourses().toString(),
                    student.getGrades().toString()
            });
        }
    }

    private void updateStudentComboBoxes() {
        studentComboBox.removeAllItems();
        gradeStudentComboBox.removeAllItems();

        for (Student student : students) {
            studentComboBox.addItem(student.getId());
            gradeStudentComboBox.addItem(student.getId());
        }
    }

    private void updateGradeCourseComboBox() {
        gradeCourseComboBox.removeAllItems();

        Student student = getSelectedStudent(gradeStudentComboBox);

        if (student != null) {
            for (String course : student.getEnrolledCourses()) {
                gradeCourseComboBox.addItem(course);
            }
        }
    }

    private void clearStudentFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI app = new StudentManagementGUI();
            app.setVisible(true);
        });
    }
}