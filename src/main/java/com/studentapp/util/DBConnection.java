package com.studentapp.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/student";
  private static final String USER = "root";
  //replace password with your local DB password

  private static Connection con;

  private DBConnection() {
    // prevent object creation
  }

  public static Connection getConnection() {
    try {
      if (con == null || con.isClosed()) {
        con = DriverManager.getConnection(URL, USER, PASSWORD);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}
