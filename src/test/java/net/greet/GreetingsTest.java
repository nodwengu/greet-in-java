package net.greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {
   //private PersonService personService = null;
   private Greet greet = null;
   
   @Test
   public void connect() {
      PersonService personService = new PersonService();
   
      personService.connectToDatabase();
      personService.delete();
   }
   
   @Test
   public void shouldBeAbleToAddNewUser() {
      PersonService personService = new PersonService();
      Person p1 = null, p2 = null, p3 = null;

      p1 = new Person();
      p2 = new Person();
      p3 = new Person();
   
      p1.setUsername("thando");
      p2.setUsername("james");
      p3.setUsername("jola");
   
      personService.add(p1);
      personService.add(p2);
      personService.add(p3);
   
      assertEquals(3, personService.getAll().size());
   }
   
   @Test
   public void shouldBeAbleToIncreaseGreetCounter() {
      PersonService personService = new PersonService();
      Person person = null;
   
      person = personService.getByName("thando");
   
      assertEquals(1, person.getGreetCount());
   
      person.increaseCounter();
      personService.updateGreetCount(person, person.getGreetCount());
   
      assertEquals(2, person.getGreetCount());
   }
   
//   @Test
//   public void shouldBeAbleToDecreaseGreetCounter() {
//      PersonService personService = new PersonService();
//      Person person = null;
//   
//      person = personService.getByName("thando");
//
//      person.increaseCounter();
//      personService.updateGreetCount(person, person.getGreetCount());
//
//      assertEquals(3, person.getGreetCount());
//
//      person.decreaseCounter();
//      personService.updateGreetCount(person, person.getGreetCount());
//
//      assertEquals(2, person.getGreetCount());
//   }

   @Test
   public void shouldBeAbleToRemoveUser() {
      PersonService personService = new PersonService();
      Person person = null;
      assertEquals(3, personService.getAll().size());
   
      person = personService.getByName("jola");
      personService.remove(person);
   
      assertEquals(2, personService.getAll().size());
   }
   
   @Test
   public void shouldBeAbleToReturnUserByName() {
      PersonService personService = new PersonService();
      Person person = null;
   
      person = personService.getByName("james");
      // personService.allGreeted();
      assertEquals("james", person.getUsername());
   
      assertEquals(3, personService.getAll().size());
   }
   
   
}
