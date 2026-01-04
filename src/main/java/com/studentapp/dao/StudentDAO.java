//DAO = Data Access Object
package com.studentapp.dao;

import com.studentapp.model.Student;
import com.studentapp.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    //every time you perform some CRUD operation, you open and close the DB connection because DB connection is in a separate file, also just one change in that file will affect connections in all other files

    // Insert
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (id, name, age, course) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all
    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("course")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update
    public static boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name=?, age=?, course=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public static boolean deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
