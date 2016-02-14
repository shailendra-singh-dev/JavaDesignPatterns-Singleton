package com.shail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

  private final static DatabaseManager mDatabaseManager = new DatabaseManager();
  private Connection mConnection;

  private DatabaseManager() {
    // Not accessible..
  }

  public static DatabaseManager getDatabaseManager() {
    return mDatabaseManager;
  }

  public void establishConnection() {
    System.out.println("\n establishConnection");
  }

  public void closeConnection() {
    System.out.println("\n closeConnection");
  }

  public void connect() throws Exception {
    if (mConnection != null)
      return;

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new Exception("Driver not found");
    }

    String url = String.format("jdbc:mysql://localhost:%d/patterns", 3306);

    mConnection = DriverManager.getConnection(url, "squiffy", "letmein");
  }

  public void disconnect() {
    if (mConnection != null) {
      try {
        mConnection.close();
      } catch (SQLException e) {
        System.out.println("Can't close connection");
      }
    }

    mConnection = null;
  }

}
