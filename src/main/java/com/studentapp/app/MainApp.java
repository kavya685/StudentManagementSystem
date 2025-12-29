package com.studentapp.app;

import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;

public class MainApp {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        Student s1 = new Student(1, "Radha", 20, "Computer Science");

        boolean status = dao.addStudent(s1);

        if (status) {
            System.out.println("Student inserted successfully 🚀");
        } else {
            System.out.println("Insert failed 💀");
        }
    }
}
