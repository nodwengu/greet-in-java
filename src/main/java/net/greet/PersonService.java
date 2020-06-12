package net.greet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
   private final String JDBC_DRIVER = "org.h2.Driver";
   private final String DB_URL = "jdbc:h2:file:./Database/jdbc_greetings_db";
   private final String USER = "sa";
   private final String PASS = "";
   
   public void connectToDatabase() {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         Class.forName(JDBC_DRIVER);
         
      } catch (ClassNotFoundException e) {
         System.out.println("H2 JDBC Driver not found !!");
         return;
      }
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);;
         
         String sql =  "CREATE TABLE IF NOT EXISTS user" +
                 "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                 " username VARCHAR(255), " +
                 " greetCount INT)";
         
         stmt = conn.createStatement();
         stmt.executeUpdate(sql);
         
         stmt.close();
         conn.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void allGreeted() throws SQLException {
      List<Person> personList = new ArrayList<>();
      Connection conn = null;
      Statement stmt = null;
   
      String sql = "SELECT id, username, greetCount FROM user";
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(sql);
         
         String results = "";
         
         while (resultSet.next()) {
            Person user = new Person();
            
            user.setUsername(resultSet.getString("username"));
            user.setGreetCount(resultSet.getInt("greetCount"));
            user.setId(resultSet.getInt("id"));
            
            personList.add(user);
            
            results +=  "NAME: " + user.getUsername() + "\tCOUNT: " + user.getGreetCount() + "\tID: " + user.getId() + "\n";
         }
        
         if(personList.size() > 0) {
            System.out.println(results);
         } else {
            System.out.println("No data.......");
            System.out.println();
         }
         
         resultSet.close();
         stmt.close();
         conn.close();
         
      } catch (SQLException e ) {
         System.out.println("Exception from greeted method");
         e.printStackTrace();
      } finally {
         if (stmt != null) { stmt.close(); }
      }
   }
   
   public void add(Person person) {
      Connection conn = null;
      PreparedStatement addUserPreparedStatement = null;
   
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         String INSERT_USER_SQL = "insert into user (username, greetCount) values (?, ?)";
         
         addUserPreparedStatement = conn.prepareStatement(INSERT_USER_SQL);
         
         addUserPreparedStatement.setString(1, person.getUsername());
         addUserPreparedStatement.setInt(2, 1);
         addUserPreparedStatement.execute();
         
         conn.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void updateGreetCount (Person person, int count)  {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         String UPDATE_USER_SQL = "UPDATE user SET greetCount = ? WHERE username = ?";
         
         PreparedStatement updateUserPreparedStatement = conn.prepareStatement(UPDATE_USER_SQL);
         
         updateUserPreparedStatement.setInt(1, count);
         updateUserPreparedStatement.setString(2, person.getUsername());
         updateUserPreparedStatement.execute();
         
         conn.close();
         
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   
   public void delete() {
      Connection conn = null;
      Statement stmt = null;

      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         String selectSql = "DELETE FROM user";

         stmt = conn.createStatement();
         stmt.execute(selectSql);

         stmt.close();
         conn.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   public List<Person> getAll() throws SQLException {
      List<Person> personList = new ArrayList<>();
      Connection conn = null;
      Statement stmt = null;
   
      String sql = "SELECT id, username, greetCount FROM user";
   
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(sql);
      
         String results = "";
      
         while (resultSet.next()) {
            Person person = new Person();
            person.setUsername(resultSet.getString("username"));
            person.setGreetCount(resultSet.getInt("greetCount"));
            person.setId(resultSet.getInt("id"));
            personList.add(person);
         }
         
      } catch (SQLException e ) {
         System.out.println("Exception from greeted method");
         e.printStackTrace();
      } finally {
         if (stmt != null) { stmt.close(); }
      }
      
      return personList;
   }
   
   
   public Person getByName(String name) throws SQLException {
      PreparedStatement preparedStatement = null;
      Connection conn = null;
      Statement stmt = null;
      Person person = null;
      
      String sql = "select * from user where username=?";
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1, name);
         ResultSet resultSet = preparedStatement.executeQuery();
         
         if (resultSet.next()) {
            person = new Person();
            
            person.setUsername(resultSet.getString("username"));
            person.setGreetCount(resultSet.getInt("greetCount"));
            person.setId(resultSet.getInt("id"));
         }

         conn.close();
         
      } catch (SQLException e ) {
         System.out.println("Exception from getByName method");
         e.printStackTrace();
      } finally {
         if (stmt != null) { stmt.close(); }
      }
      
      return person;
   }
   
   
   public void remove(Person person) throws SQLException {
      PreparedStatement preparedStatement = null;
      Connection conn = null;
      
      String sql = "delete from user WHERE username=?";

      try {
   
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
   
         preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1, person.getUsername());
         //ResultSet resultSet = preparedStatement.executeQuery();

         preparedStatement.executeUpdate();
   
         conn.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   }


   
}
