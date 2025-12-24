package com.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentManagementSystem {

    // DB credentials
    static final String URL = "jdbc:mysql://localhost:3306/student";
    static final String USER = "root";        // change if needed
    static final String PASS = "radhe";    // change if needed

    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            // connect to DB
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("🔥 Database connected successfully");

            int choice;

            do {
                System.out.println("\n==== STUDENT MANAGEMENT SYSTEM ====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // buffer clear

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> System.out.println("👋 Exiting...");
                    default -> System.out.println("❌ Invalid choice");
                }

            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CRUD METHODS =================

    static void addStudent() {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter course: ");
            String course = sc.nextLine();

            String sql = "INSERT INTO student (name, age, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);

            ps.executeUpdate();
            System.out.println("✅ Student added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewStudents() {
        try {
            String sql = "SELECT * FROM student";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nID | Name | Age | Course");
            System.out.println("------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("course")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateStudent() {
        try {
            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("New name: ");
            String name = sc.nextLine();

            System.out.print("New age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("New course: ");
            String course = sc.nextLine();

            String sql = "UPDATE student SET name=?, age=?, course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Student updated");
            else
                System.out.println("❌ ID not found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteStudent() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM student WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("🗑️ Student deleted");
            else
                System.out.println("❌ ID not found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
