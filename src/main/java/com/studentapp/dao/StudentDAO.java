package com.studentapp.dao;

import com.studentapp.model.Student;
import com.studentapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO student (id, name, age, course) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

