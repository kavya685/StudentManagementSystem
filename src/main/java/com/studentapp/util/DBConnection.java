package com.studentapp.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/student";
  private static final String USER = "root";
  private static final String PASSWORD = "radhe";

  private static Connection con;

  private DBConnection() {
    // prevent object creation
  }

  //getConnection is user defined
  public static Connection getConnection() {
    try {
      //getConnection() & getConnection(URL, USER, PASSWORD) are different
      if (con == null || con.isClosed()) {
        con = DriverManager.getConnection(URL, USER, PASSWORD);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}
