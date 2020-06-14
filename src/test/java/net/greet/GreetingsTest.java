package net.greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {
   private PersonService personService = null;
   private Greet greet = null;
   
   @Test
   public void connect() {
      personService = null;

      try {
         personService = new PersonService();
         personService.connectToDatabase();
         personService.delete();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void shouldBeAbleToAddNewUser() {
      personService = new PersonService();
      Person p1 = null, p2 = null, p3 = null;

      p1 = new Person();
      p2 = new Person();
      p3 = new Person();

      try {
         assertEquals(0, personService.getAll().size());

         p1.setUsername("thando");
         p2.setUsername("james");
         p3.setUsername("jola");

         personService.add(p1);
         personService.add(p2);
         personService.add(p3);

         assertEquals(3, personService.getAll().size());

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToAddNewUser");
      }
   }
   
   @Test
   public void shouldBeAbleToIncreaseGreetCounter() {
      personService = new PersonService();
      Person person = null;
//      try {
//         personService.allGreeted();
//      } catch (SQLException e) {
//
//      }

      try {
         person = personService.getByName("thando");
        
         assertEquals(1, person.getGreetCount());

         person.increaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());

         assertEquals(2, person.getGreetCount());

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToIncreaseGreetCounter");
      }
   }
   
   @Test
   public void shouldBeAbleToDecreaseGreetCounter() {
      personService = new PersonService();
      Person person = null;

      try {
         person = personService.getByName("thando");

         person.increaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());

         assertEquals(3, person.getGreetCount());

         person.decreaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());

         assertEquals(2, person.getGreetCount());

      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      } finally { }
   }

   @Test
   public void shouldBeAbleToRemoveUser() {
      personService = new PersonService();
      Person person = null;

      try {
         assertEquals(3, personService.getAll().size());
         
         person = personService.getByName("jola");
         personService.remove(person);
   
         assertEquals(2, personService.getAll().size());
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToRemoveAll");
      }
   }
   
   @Test
   public void shouldBeAbleToReturnUserByName() {
      personService = new PersonService();
      Person person = null;
      
      try {
         person = personService.getByName("james");
         // personService.allGreeted();
         assertEquals("james", person.getUsername());
         
         assertEquals(3, personService.getAll().size());
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToRemoveAll");
      }
   }

//   @Test
//   public void shouldBeAbleToGreetByLanguage() {
//      personService = new PersonService();
//      greet = new Greet();
//      Person person = null;
//
//      assertEquals("Mholo thando", greet.greetUser("thando", "Xhosa"));
//
//      //assertEquals("Mholo thando", greet.greetUser("thando", "afrikaans"));
//
//      //assertEquals("Mholo thando", greet.greetUser("thando", "english"));
//
//   }



//   @Test
//   public void shouldBeAbleToGreetInXhos() {
//      personService = new PersonService();
//      greet = new Greet();
//
//      try {
//         personService.allGreeted();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//
//      //greet.greet("thando", "xhosa");
//
//
//
//      //
//   }
   
   
   
   
   
   
//
//   @Test
//   public void shouldBeAbleToRemoveAll() {
//      PersonService personService = new PersonService();
//      personService.delete();
//   }








}
