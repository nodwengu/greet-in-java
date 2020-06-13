package net.greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonServiceTest {
   
   @Test
   public void shouldBeAbleToConnect() {
      PersonService personService = new PersonService();
      try {
         personService.connectToDatabase();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void shouldBeAbleToAddNewUser() {
      PersonService personService = null;
      Person p1 = null, p2 = null, p3 = null;
      
      personService = new PersonService();
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
      PersonService personService = new PersonService();
      Person person = new Person();
      
      try {
         person = personService.getByName("thando");
   
         assertEquals(1, person.getGreetCount());
         
         person.increaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());
   
         assertEquals(2, person.getGreetCount());
         
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }
   
   @Test
   public void shouldBeAbleToDecreaseGreetCounter() {
      PersonService personService = new PersonService();
      Person person = new Person();
      
      try {
         person = personService.getByName("thando");
         
         person.increaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());
   
         assertEquals(3, person.getGreetCount());
   
         person.decreaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());
         
         assertEquals(2, person.getGreetCount());
         
         personService.allGreeted();
         
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }
   
   @Test
   public void shouldBeAbleToDeleteAUser() {
      PersonService personService = new PersonService();
      
      try {
         ///personService.allGreeted();
        
         System.out.println("WORKING WORKING: "+ personService.getAll().size());
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToRemoveAll");
      }
   }
   
   @Test
   public void shouldBeAbleToRemoveAll() {
      PersonService personService = new PersonService();

      try {
         personService.delete();
         System.out.println("WORKING: "+ personService.getAll().size());
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("shouldBeAbleToRemoveAll");
      }
   }

   

   




}
