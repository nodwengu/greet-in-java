//package net.greet;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class GreetTest {
//
//   private final String JDBC_DRIVER = "org.h2.Driver";
//   private final String DB_URL = "jdbc:h2:file:./target/jdbc_greet_db";
//   private final String USER = "sa";
//   private final String PASS = "";
//
//   public Connection getConnection() throws Exception {
//      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//      return conn;
//   }
//
//
//   @BeforeEach
//   public void cleanUpTables() {
//      try {
//         try(Connection conn = getConnection()) {
//            Statement statement = conn.createStatement();
//            statement.addBatch("delete from user");
//
//            statement.executeBatch();
//         }
//      } catch(Exception ex) {
//         System.out.println("These test will fail until the user table is created: " + ex);
//      }
//   }
//
//
//   @Test
//   public void connectToDatabase() {
//      Connection conn = null;
//      Statement stmt = null;
//
//      try {
//         Class.forName(JDBC_DRIVER);
//
//      } catch (ClassNotFoundException e) {
//         System.out.println("H2 JDBC Driver not found !!");
//         return;
//      }
//
//      try {
//         conn = DriverManager.getConnection(DB_URL, USER, PASS);;
//
//         String sql =  "CREATE TABLE IF NOT EXISTS user" +
//                 "(id INT PRIMARY KEY AUTO_INCREMENT, " +
//                 " username VARCHAR(20), " +
//                 " greetCount INT)";
//
//         stmt = conn.createStatement();
//         stmt.executeUpdate(sql);
//
//         stmt.close();
//         conn.close();
//
////         //STEP 4: Execute a query
////         System.out.println("Deleting table in given database...");
////         stmt = conn.createStatement();
////         String sql = "DROP TABLE USER ";
////         stmt.executeUpdate(sql);
////         System.out.println("Table  deleted in given database...");
//
//
//
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//
//
//   @Test
//   public void addDataUserUsingPreparedStatement() {
//
//      try {
//
//         // connect to the database
//         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//         final String sql = "insert into user (username, greetCount) values (?, ?)";
//
//         // PreparedStatement are SQL statements that can be called over and over with different parameters
//         PreparedStatement addUserPreparedStatement = conn.prepareStatement(sql);
//
//         addUserPreparedStatement.setString(1, "thando");
//         addUserPreparedStatement.setInt(2, 1);
//         addUserPreparedStatement.execute();
//
//         addUserPreparedStatement.setString(1, "xola");
//         addUserPreparedStatement.setInt(2, 1);
//         addUserPreparedStatement.execute();
//
//         ResultSet rs = conn.createStatement().executeQuery("select * from user");
//
////         Statement statement = conn.createStatement();
////         ResultSet rs = statement.executeQuery ("select count(*) as user_count from user2");
//
//         if (rs.next()) {
//            System.out.println("DATA");
//            // mmm... how many rows was actually added in the V2__add_fruit.sql migration file
//            assertEquals(1, rs.getInt("greetCount"));
//         } else {
//            System.out.println("NO DATA");
//         }
//
//         while(rs.next()) {
//            System.out.print("USERNAME: " + rs.getString("username") + "\t");
//            System.out.println("COUNTER: " + rs.getString("greetCount"));
//         }
//
//
//      } catch (Exception e) {
//         System.out.println("addDataUserUsingPreparedStatement");
//         //e.printStackTrace();
//      }
//   }
//
//
//}
