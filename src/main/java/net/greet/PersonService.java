package net.greet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
   private final String JDBC_DRIVER = "org.h2.Driver";
   private final String DB_URL = "jdbc:h2:file:./Database/jdbc_greetings_db";
   private final String USER = "sa";
   private final String PASS = "";
   
   Connection conn = null;
   Statement stmt = null;
   
   public void connectToDatabase() {
      try {
         Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
         System.out.println("H2 JDBC Driver not found !!");
      }
   
      String sql =  "CREATE TABLE IF NOT EXISTS user" +
              "(id INT PRIMARY KEY AUTO_INCREMENT, " +
              " username VARCHAR(255), " +
              " greetCount INT)";
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         stmt = conn.createStatement();
         stmt.executeUpdate(sql);
         System.out.println("\u001B[32m" + "Connection established Successful! " + "\u001B[0m");
         System.out.println();
        
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }
   
   public void allGreeted() {
      List<Person> personList = new ArrayList<>();
      String results = "";
   
      String sql = "SELECT id, username, greetCount FROM user";
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(sql);
         
         while (resultSet.next()) {
            Person user = new Person();
            
            user.setUsername(resultSet.getString("username"));
            user.setGreetCount(resultSet.getInt("greetCount"));
            user.setId(resultSet.getInt("id"));
            
            personList.add(user);
            
            results +=  "Name: " + user.getUsername() + "\tCount: " + user.getGreetCount() + "\tID: " + user.getId() + "\n";
         }
        
         if(personList.size() > 0) {
            System.out.println("\u001B[32m" + results + "\u001B[0m");
         } else {
            System.out.println("\u001B[32m" + "No data.." + "\u001B[0m");
            System.out.println();
         }
         
         resultSet.close();
        
      } catch (SQLException e ) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }
   
   public void add(Person person) {
      PreparedStatement preparedStatement = null;
   
      String sql = "INSERT INTO user (username, greetCount) VALUES (?, ?)";
   
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         preparedStatement = conn.prepareStatement(sql);
         
         preparedStatement.setString(1, person.getUsername());
         preparedStatement.setInt(2, 1);
         preparedStatement.execute();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }
   
   public void updateGreetCount(Person person, int count) {
      PreparedStatement preparedStatement = null;
      
      String sql = "UPDATE user SET greetCount=? WHERE username=?";
      
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         preparedStatement = conn.prepareStatement(sql);
         
         preparedStatement.setInt(1, count);
         preparedStatement.setString(2, person.getUsername());
         preparedStatement.execute();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }
   
   public void delete() {
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         String selectSql = "DELETE FROM user";

         stmt = conn.createStatement();
         stmt.execute(selectSql);

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }

   
   public List<Person> getAll() {
      List<Person> personList = new ArrayList<>();
     
      String sql = "SELECT id, username, greetCount FROM user";
   
      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         
         stmt = conn.createStatement();
         ResultSet resultSet = stmt.executeQuery(sql);
      
         while (resultSet.next()) {
            Person person = new Person();
            person.setUsername(resultSet.getString("username"));
            person.setGreetCount(resultSet.getInt("greetCount"));
            person.setId(resultSet.getInt("id"));
            personList.add(person);
         }
      } catch (SQLException e ) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
      
      return personList;
   }
  
   public Person getByName(String name) {
      PreparedStatement preparedStatement = null;
      Person person = null;

      String sql = "SELECT id, username, greetCount FROM user WHERE username=?";

      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1, name);
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            person = new Person();

            person.setUsername(resultSet.getString("username"));
            person.setGreetCount(resultSet.getInt("greetCount"));
            person.setId(resultSet.getInt("id"));
         }
      } catch (SQLException e ) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }

      return person;
   }
   
   
   public void remove(Person person) {
      PreparedStatement preparedStatement = null;
     
      String sql = "DELETE FROM user WHERE username=?";

      try {
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
   
         preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1, person.getUsername());
    
         preparedStatement.executeUpdate();
   
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeConnectionAndStatement(stmt, conn);
      }
   }
   
   public void closeConnectionAndStatement(Statement statement, Connection connection) {
      try {
         if(statement != null)
            statement.close();
         if(connection != null)
            connection.close();
         
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }


   
}
