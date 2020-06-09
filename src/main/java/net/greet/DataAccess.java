package net.greet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
   private static List<Person> arrayList = new ArrayList<>();
   
   // JDBC driver name and database URL
   private final String JDBC_DRIVER = "org.h2.Driver";
   private final String DB_URL = "jdbc:h2:file:./Database/jdbc_greetings_db";
   
   //  Database credentials
   private final String USER = "sa";
   private final String PASS = "";
   
   void establish() {
      try {
         // STEP 1: Register JDBC driver
         Class.forName("org.h2.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("MySQL JDBC Driver not found !!");
         return;
      }
   }
   
   
   public void connectToDatabase() {
      Connection connection = null;
      Statement stmt = null;
      
      try {
         // STEP 1: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         
      } catch (ClassNotFoundException e) {
         System.out.println("H2 JDBC Driver not found !!");
         return;
      }
      
      try {
         // STEP 2: Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);;
         //System.out.println("H2 Connection to database established!");
   
         // STEP 3: Execute a query
         //System.out.println("Creating table in given database...");
         stmt = conn.createStatement();
         String sql =  "CREATE TABLE IF NOT EXISTS user" +
                 "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                 " username VARCHAR(255), " +
                 " greetCount INT)";
         
         stmt.executeUpdate(sql);
         //System.out.println("Created table in given database...");
         
         // STEP 4: Clean-up environment
         stmt.close();
         conn.close();
         
      } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         return;
      }
//      finally {
//         try
//         {
//            if(connection != null)
//               connection.close();
//            System.out.println("Connection closed !!");
//            System.out.println();
//
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
//      }
   }
   
   
   // public static void viewTable(Connection con, String dbName) throws SQLException {
   public void greeted() throws SQLException {
      Connection connection = null;
      Statement stmt = null;
      
      //System.out.println("H2 JDBC Driver Registered!");
      //String query = "SELECT * FROM user";
      
      try {
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);;
         //System.out.println("Getting data from a given database...");
         String selectSql = "SELECT * FROM user";
         
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(selectSql);
         
         while (resultSet.next()) {
            String username = resultSet.getString("username");
            int greetCount = resultSet.getInt("greetCount");
            int id = resultSet.getInt("id");
            
            Person user = new Person();
            user.setUsername(resultSet.getString("username"));
            user.setGreetCount(resultSet.getInt("greetCount"));
            arrayList.add(user);
   
            // Display values
            System.out.print("NAME: " + user.getUsername());
            System.out.print(",  COUNT: " + user.getGreetCount());
            System.out.println(",   ID: " + id);
            
         }
         System.out.println();
         // STEP 5: Clean-up environment
         resultSet.close();
         stmt.close();
         conn.close();
         
      } catch (SQLException e ) {
//         JDBCTutorialUtilities.printSQLException(e);
         e.printStackTrace();
      } finally {
         if (stmt != null) { stmt.close(); }
      }
   }
   
   public void addUser(Person person) {
      Connection connection = null;
      Statement stmt = null;
      
      try {
         Connection conn = DriverManager.getConnection("jdbc:h2:file:./Database/jdbc_greetings_db", "sa", "");;
         System.out.println("SQL Connection to database established!");
         
         System.out.println("Inserting data in a given database...");
         String INSERT_USER_SQL = "insert into user (username, greetCount) values (?, ?)";
         
         // PreparedStatement are SQL statements that can be called over and over with different parameters
         PreparedStatement addUserPreparedStatement = conn.prepareStatement(INSERT_USER_SQL);
         System.out.println("ADD USER ADD USER ADDUSER");
         
         System.out.println(person.getUsername());
         addUserPreparedStatement.setString(1, person.getUsername());
         addUserPreparedStatement.setInt(2, 1);
         addUserPreparedStatement.execute();
         
         // STEP 4: Clean-up environment
         stmt.close();
         conn.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Connection Failed! Check output console: addUser");
         return;
      } finally {
      
      }
   }
   
   public void updateGreetCount (Person person, int count)  {
      Connection connection = null;
      Statement stmt = null;
      
      System.out.println("USER COUNTER: " + person.getGreetCount());
      person.toString();
      //System.exit(1);
      
      try {
   
         Connection conn = DriverManager.getConnection("jdbc:h2:file:./Database/jdbc_greetings_db", "sa", "");;
         System.out.println("SQL Connection to database established!");
   
         System.out.println("Updating data in a given database...");
         String UPDATE_USER_SQL = "UPDATE user SET greetCount = ? WHERE username = ?";
   
         // PreparedStatement are SQL statements that can be called over and over with different parameters
         PreparedStatement updateUserPreparedStatement = conn.prepareStatement(UPDATE_USER_SQL);
         System.out.println("UPDATE_USER_SQL UPDATE_USER_SQL UPDATE_USER_SQL");
         //System.exit(1);
         
         updateUserPreparedStatement.setInt(1, count);
         updateUserPreparedStatement.setString(2, person.getUsername());
         updateUserPreparedStatement.execute();
   
         // STEP 4: Clean-up environment
         conn.close();
         
      } catch (SQLException se) {
         // log the exception
         System.out.println("Error UPDATING USER");
         se.printStackTrace();
      }
   }
   
   // public static void viewTable(Connection con, String dbName) throws SQLException {
   public void setArrayList() throws SQLException {
      Connection connection = null;
      Statement stmt = null;
      
      
      try {
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //System.out.println("Getting data from a given database...");
         String selectSql = "SELECT * FROM user";
         
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(selectSql);
         
         while (resultSet.next()) {
            String username = resultSet.getString("username");
            int greetCount = resultSet.getInt("greetCount");
            int id = resultSet.getInt("id");
            
            Person user = new Person();
            user.setUsername(resultSet.getString("username"));
            user.setGreetCount(resultSet.getInt("greetCount"));
            arrayList.add(user);
         }
         System.out.println();
         // STEP 5: Clean-up environment
         resultSet.close();
         stmt.close();
         conn.close();
         
      } catch (SQLException e ) {
//         JDBCTutorialUtilities.printSQLException(e);
         e.printStackTrace();
      } finally {
         if (stmt != null) { stmt.close(); }
      }
   }
   
   public void delete() {
      Connection connection = null;
      Statement stmt = null;
      try
      {
//         Class.forName("com.mysql.jdbc.Driver");
//         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCDemo", "root", "password");
         
//         stmt = connection.createStatement();
//         stmt.execute("DELETE FROM EMPLOYEE WHERE ID >= 1");
   
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         String selectSql = "DELETE FROM user";
   
         stmt = conn.createStatement();
         stmt.execute(selectSql);
   
         stmt.close();
         conn.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }finally {
         try {
            stmt.close();
            connection.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
   
   
   public int getUsersCount() {
      return arrayList.size();
   }
   
   public List<Person> getAllUsers() {
      return arrayList;
   }
   
   
}
