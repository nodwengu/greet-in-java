package net.greet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
   
   @Test
   void shouldBeAbleToReturnUsername() {
      Person person = new Person();
      assertEquals(null, person.getUsername());
      
      person.setUsername("Thanduxolo");
      assertEquals("Thanduxolo", person.getUsername());
      
      Person person1 = new Person();
      person1.setUsername("James");
      assertEquals("James", person1.getUsername());
   }
   
   @Test
   void shouldBeAbleToReturnGreetCount() {
      Person person = new Person();
      assertEquals(0, person.getGreetCount());
   
      person.setGreetCount(3);
      assertEquals(3, person.getGreetCount());
   }
   
   @Test
   void shouldBeAbleToReturnId() {
      Person person = new Person();
      assertEquals(0, person.getId());
      
      person.setId(567);
      assertEquals(567, person.getId());
   }
   
   @Test
   void shouldBeAbleToIncreaseCounter() {
      Person person = new Person();
      person.setGreetCount(5);
      assertEquals(5, person.getGreetCount());
      
      person.increaseCounter();
      assertEquals(6, person.getGreetCount());
   }
   
   @Test
   void shouldBeAbleToDcreaseCounter() {
      Person person = new Person();
      person.setGreetCount(5);
      assertEquals(5, person.getGreetCount());
      
      person.decreaseCounter();
      assertEquals(4, person.getGreetCount());
   }
   
}