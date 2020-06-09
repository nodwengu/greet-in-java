package net.greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GreetTest {
   final String KOANS_DATABASE_URL = "";
   
   public Connection getConnection() throws Exception {
      // TODO - add a username of "sa" and a blank password ""
      // TODO - if the db is created via default flyway config the username will be "sa" with a blank password
      // you can change this by removing the user element containing sa in the pom.xml file
      // if not be sure to delete the *.db files in your target folder before running mvn flyway:migrate the first time
      // and be sure the set the username to "sa" password blank ""
      // if your remove the user element from the pom.xml file you are use a username of "" and a password of ""
      Connection conn = DriverManager.getConnection(KOANS_DATABASE_URL, "sa", "");
      return conn;
   }
   
   @BeforeEach
   public void cleanUpTables() {
      // don't touch any code in here!!!
      try {
         try(Connection conn = getConnection()) {
            // I repeat don't touch any code in here!!!
            Statement statement = conn.createStatement();
            statement.addBatch("delete from user2 where name in ('Thando', 'James')");
            //statement.addBatch("update fruit set price = 4.75  where name = 'red apple'");
            statement.executeBatch();
            // I repeat once again don't touch any code in here!!!
         }
      } catch(Exception ex) {
         System.out.println("These test will fail until the fruit table is created: " + ex);
      }
   }
   
   @Test
   public void loadJdbcDriver() {
      
      try {
            /*
                To fix this error add a dependency for the H2 database driver
                As per this link: http://www.h2database.com/html/build.html#maven2
            */
         Class.forName("org.h2.Driver");
      } catch (ClassNotFoundException e) {
         fail(e);
      }
   }
   
   @Test
   public void connectToDatabase() {
      
      try {
         // load the jdbc driver
         Class.forName("org.h2.Driver");
   
         // set the jdbc connection string
         final String KOANS_DATABASE_URL = "jdbc:h2:file:./target/jdbc_greet_db";
   
         // connect to the database
         Connection conn = DriverManager.getConnection(KOANS_DATABASE_URL, "sa", "");
         
      } catch (Exception e) {
         fail(e);
      }
   }
   
   @Test
   public void executeSQLStatement() {
      
      try {
         
         //TODO - ensure you fixed the username & password in executeSQLStatement
         
         // set the jdbc connection string
         final String GREET_DATABASE_URL = "jdbc:h2:file:./target/jdbc_greet_db";
         
         // connect to the database
         Connection conn = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
         
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery ("select * from user");
         
         // add the flyway plugin to you maven configuration in pom.xml
         // use the correct database name - jdbc_greet_db in the flyway maven config
         
         // https://flywaydb.org/getstarted/firststeps/maven
         
         // add a V1__create_user.sql file in the src/main/resources/db/migration folder
         // add a create table script in there to create a fruit table

            /*
                create table fruit (
                    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                    name varchar(40),
                    price double
                );
             */
         
         // run the migration using the in your project root folder using:
         // mvn flyway:migrate
         
      } catch (Exception e) {
         fail(e);
      }
   }
   
   
//   @Test
//   public void addDataToFruitTableViaMigration() {
//
//      try {
//         // set the jdbc connection string
//         final String GREET_DATABASE_URL = "jdbc:h2:file:./target/jdbc_greet_db";
//
//         // connect to the database
//         Connection conn = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
//
//         Statement statement = conn.createStatement();
//         ResultSet rs = statement.executeQuery ("select count(*) as user2_count from user2");
//
//         if (rs.next()) {
//            // mmm... how many rows was actually added in the V2__add_user.sql migration file
//            assertEquals(3, rs.getInt("user2_count"));
//         }

//      } catch (Exception e) {
//         fail(e);
//      }
//   }
   
   @Test
   public void addDataUserUsingPreparedStatement() {
      
      try {
   
         // set the jdbc connection string
         final String GREET_DATABASE_URL = "jdbc:h2:file:./target/jdbc_greet_db";
   
         // connect to the database
         Connection conn = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
   
         final String INSERT_USER_SQL = "insert into user2 (name) values (?)";
         final String FIND_USER_SQL = "select name from user2 where name = ?";
         
         // PreparedStatement are SQL statements that can be called over and over with different parameters
         PreparedStatement addUserPreparedStatement = conn.prepareStatement(INSERT_USER_SQL);
         
         // use it to add 2 new users John and James
         
         // todo - add Orange
//         addUserPreparedStatement.setString(1, "Thanduxolo");
//         addUserPreparedStatement.execute();
         
         ResultSet rs = conn.createStatement().executeQuery("select * from user2");
   
//         Statement statement = conn.createStatement();
//         ResultSet rs = statement.executeQuery ("select count(*) as user_count from user2");
   
//         if (rs.next()) {
//            // mmm... how many rows was actually added in the V2__add_fruit.sql migration file
//            assertEquals(5, rs.getInt("user_count"));
//         }
   
         while(rs.next()) {
            System.out.println(rs.getString("name"));
         }
   
   
      } catch (Exception e) {
         fail(e);
      }
   }
   
   
}
