package net.greet;

import java.sql.SQLException;
import java.util.List;

public class Greet {

   public String greetUser(String username, String lang) {
      String greeting = null;
      
      if(lang.equalsIgnoreCase("english")) {
         greeting = "Hello " + username;
      } else if (lang.equalsIgnoreCase("xhosa")) {
         greeting = "Mholo " + username;
      } else if (lang.equalsIgnoreCase("afrikaans")) {
         greeting = "Goe more " + username;
      } else {
         System.out.println("Invalid language");
         System.out.println();
      }
//      System.out.println(greeting);
//      System.out.println();
      
      return greeting;
   }
  
   public void greet(String username, String lang) {
      PersonService personService = new PersonService();
      Person person = new Person();
      person.setUsername(username);
      
      try {
         if(personService.getByName(person.getUsername()) != null){
            person = personService.getByName(person.getUsername());
         } else {
            person = null;
         }
      } catch (SQLException e) {
         System.out.println("Message: "  + e.getMessage());
         e.printStackTrace();
      }
      
      if (person != null) {
         person.increaseCounter();
         personService.updateGreetCount(person, person.getGreetCount());
         System.out.println( greetUser(person.getUsername(), lang) );
         System.out.println();
//         System.out.println("updated person from the storage");
//         System.out.println();
         
      } else {
         Person user = new Person();
         user.setUsername(username);
         personService.add(user);
         System.out.println( greetUser(person.getUsername(), lang) );
         System.out.println();
//         System.out.println("added new person to the storage");
//         System.out.println();
      }
   }
   
   public void greeted() {
      try{
         new PersonService().allGreeted();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }
   
   public String greeted(String username) {
      PersonService personService = new PersonService();
      Person person = null;
      
      try {
         Person newPerson = personService.getByName(username);
         
         if(newPerson != null) {
            person = new Person();
            person = newPerson;
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
      
      return  (person.getUsername() + " has been greeted " + person.getGreetCount() + " times.");
    
   }
   
   public String counter() {
      PersonService personService = new PersonService();
      List<Person> allUsers = null;

      try {
         if(personService.getAll() != null) {
            allUsers = personService.getAll();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return (allUsers.size() + " users has been greeted");
     
   }
   
   public void help() {
      System.out.println(
              "greet [username] [language] \t Greet a specified user with a given language \n" +
              "greeted \t Display a list of all users that has been greeted and how many time each user has been greeted \n" +
              "greeted [username] \t Returns how many times that username have been greeted \n" +
              "counter \t Returns a count of how many unique users has been greeted \n" +
              "clear \t Deletes all users greeted and the reset the greet counter to 0 \n" +
              "clear [username] \t Delete the greet counter for the specified user and decrement the greet counter by 1 \n" +
              "exit \t Exits the application \n" +
              "help \t shows a user an overview of all possible commands \n"
      );
   }
   
   public void exit() {
      System.out.println();
      System.exit(0);
   }
   
   public void clear() {
      new PersonService().delete();
   }
   
   // LETS CLEAR THE GREET COUNT BY ONE
   // SEE WHAT HAPPENS AFTER ZERO (EXCITING)
   public void clear(String username) {
      PersonService personService = new PersonService();
      Person person = null;
      
      try{
         if(personService.getByName(username) != null) {
            person = personService.getByName(username);
            
            if(person.getGreetCount() > 1) {
               person.decreaseCounter();
               personService.updateGreetCount(person, person.getGreetCount());
            } else {
               personService.remove(person);
            }
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }
}



















