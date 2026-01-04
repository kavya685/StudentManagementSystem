package com.studentapp.app;

import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final StudentDAO studentDAO = new StudentDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting... peace out ✌️");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, try again");
            }
        }
    }

    private static void addStudent() {
        sc.nextLine(); // clear buffer

        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine(); // 🔥 buffer clear

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine(); // 🔥 buffer clear

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Student student = new Student(id, name, age, course);
        studentDAO.addStudent(student);
    }

    private static void viewStudents() {
        List<Student> students = StudentDAO.getAllStudents();

        System.out.println("\n--- Student List ---");
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        for (Student s : students) {
            System.out.println(
                "ID: " + s.getId() +
                " | Name: " + s.getName() +
                " | Age: " + s.getAge() +
                " | Course: " + s.getCourse()
            );
        }
    }

    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        Student student = new Student(id, name, age, course);
        studentDAO.updateStudent(student);
    }

    private static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        studentDAO.deleteStudent(id);
    }
}
