//package net.greet;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class GreetTest {
//   private PersonService personService = null;
//   private Greet greet = null;
//
//   @BeforeEach
//   public void shouldBeAbleToRemoveAll() {
//      personService = new PersonService();
//
//      try {
//         personService.connectToDatabase();
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//
//      personService = new PersonService();
//      personService.delete();
//   }
//
////   @Test
////   public void connect() {
////      personService = new PersonService();
////
////      try {
////         personService.connectToDatabase();
////      } catch (Exception e) {
////         e.printStackTrace();
////      }
////   }
//


////   @Test
////   public void shouldBeAbleToGreetInEnglish() {
////      personService = new PersonService();
////      greet = new Greet();
////      Person person = null;
////
////      try {
////         person = personService.getByName("thando");
////         String greeting = greet.greetUser(person.getUsername(), "english");
////
////         assertEquals("Hello thando",  greeting);
////
////      } catch (SQLException e) {
////         e.printStackTrace();
////      }
////   }
////
////   @Test
////   public void shouldReturnUserGreetCountMsg() {
////      personService = new PersonService();
////      greet = new Greet();
////      Person person = null;
////
////      try {
////         person = personService.getByName("thando");
////         assertEquals( "thando has been greeted 1 times.", greet.greeted(person.getUsername()) );
////
////      } catch (SQLException e) {
////         e.printStackTrace();
////      }
////   }
////
////   @Test
////   public void shouldReturnCountOfGreetedUsersMsg() {
////      personService = new PersonService();
////      greet = new Greet();
////      Person person = null;
////
////      assertEquals( "3 users has been greeted", greet.counter() );
////
////   }
////
//
////   @Test
////   public void shouldBeAbleToRemoveAll() {
////      personService = new PersonService();
////      personService.delete();
////   }
//
//}
