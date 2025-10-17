package view;

import controller.StudentController;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentController controller = new StudentController();
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        while(true) {
            System.out.println("\nStudent Management Menu:");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch(choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addStudent() {
        try {
            System.out.print("StudentID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Department: ");
            String dept = sc.nextLine();
            System.out.print("Marks: ");
            int marks = Integer.parseInt(sc.nextLine());

            Student s = new Student(id, name, dept, marks);
            if(controller.addStudent(s)) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void viewStudents() {
        List<Student> students = controller.getAllStudents();
        System.out.println("ID | Name | Department | Marks");
        System.out.println("-------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void updateStudent() {
        try {
            System.out.print("StudentID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("New Name: ");
            String name = sc.nextLine();
            System.out.print("New Department: ");
            String dept = sc.nextLine();
            System.out.print("New Marks: ");
            int marks = Integer.parseInt(sc.nextLine());

            Student s = new Student(id, name, dept, marks);
            if(controller.updateStudent(s)) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void deleteStudent() {
        try {
            System.out.print("StudentID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            if(controller.deleteStudent(id)) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Failed to delete student.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}
