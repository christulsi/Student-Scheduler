package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect{
	
  public DBconnect(){
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:student.db","root","root");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
  
}