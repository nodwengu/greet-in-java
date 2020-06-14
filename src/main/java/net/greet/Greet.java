package net.greet;

import java.sql.SQLException;
import java.util.List;

public class Greet {

   public void greetUser(String username, String lang) {
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
      //System.out.println(greeting);
      System.out.println("\u001B[32m" + greeting + "\u001B[0m");
   }
  
   public void greet(String username, String lang) {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
      
      try {
         if(personService.getByName(username) != null){
            person = personService.getByName(username);
            person.increaseCounter();
            
            personService.updateGreetCount(person, person.getGreetCount());
            //System.out.println("\u001B[32m" + "Table updated Successful! " + "\u001B[0m");
            
            greetUser(person.getUsername(), lang);
            System.out.println();

         } else {
            person = new Person();
            person.setUsername(username);
            personService.add(person);
            greetUser(person.getUsername(), lang);
            System.out.println();
//         System.out.println("added new person to the storage");
//         System.out.println();
         
         }
      } catch (SQLException e) {
         System.out.println("Message: "  + e.getMessage());
         e.printStackTrace();
      }
   }
   
   public void greeted() {
      PersonService personService = null;
      
      try{
         personService = new PersonService();
         personService.allGreeted();
         
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }
   
   public String greeted(String username) {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
      String msg = null;
      
      try {
         if(personService.getByName(username) != null) {
            person = personService.getByName(username);
         }
         
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
      
      msg = "\u001B[32m" + person.getUsername() + " has been greeted " + person.getGreetCount() + " times." + "\u001B[0m";
      
      return  msg;
   }
   
   public String counter() {
      PersonService personService = new PersonService();
      List<Person> allUsers = null;
      String msg = null;

      try {
         if(personService.getAll() != null) {
            allUsers = personService.getAll();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      msg = "\u001B[32m" + (allUsers.size() + " users has been greeted") + "\u001B[0m";
      
      return msg;
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
      System.out.println("\u001B[32m" + "Goodbye! " + "\u001B[0m");
      System.out.println();
      System.exit(0);
   }
   
   public void clear() {
      PersonService personService = null;
      
      try {
         personService = new PersonService();
         
         if (personService.getAll().size() != 0) {
            personService.delete();
            System.out.println("\u001B[32m" + "Successfully removed all users! " + "\u001B[0m");
            
         } else {
            System.out.println("\u001B[32m" + "No data! " + "\u001B[0m");
         }
        
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void clear(String username) {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
      
      try{
         if(personService.getByName(username) != null) {
            person = personService.getByName(username);
            
            if(person.getGreetCount() > 1) {
               person.decreaseCounter();
               personService.updateGreetCount(person, person.getGreetCount());
               System.out.println("\u001B[32m" + "updated greet count for " + person.getUsername()  + "\u001B[0m");
            } else {
               personService.remove(person);
               System.out.println("\u001B[32m" + "Successfully removed " + person.getUsername()  + "\u001B[0m");
            }
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }
}



















