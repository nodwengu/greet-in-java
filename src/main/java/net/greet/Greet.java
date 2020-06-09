package net.greet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Greet {
//   DataAccess dataAccess = new DataAccess();
//   List<Person> usersList = dataAccess.getAllUsers();
   
   private void greetUser(String username, String lang) {
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
      System.out.println(greeting);
      System.out.println();
   }
   
   public Person findByName(String name, List<Person> users) {
      Person person = null;
      
      for (Person user: users) {
         if (user.getUsername().equals(name))
            person =  user;
      }
      return person;
   }
  
   public void greet(String username, String lang) {
   
      DataAccess dataAccess = new DataAccess();
      List<Person> usersList = dataAccess.getAllUsers();
      
      Person user = findByName(username, usersList);
      
      if(user != null) {
         // Just update the userCount variable (update the database)
         System.out.println(username + " is already saved");
         greetUser(username, lang);
         
         // update the counter for this user
         user.updateCounter();
         
         try {
            dataAccess.updateGreetCount(user, user.getGreetCount());
         } catch (Exception e) {
            System.out.println("Exception in update");
            e.printStackTrace();
         }
         
      } else {
         greetUser(username, lang);
         Person newUser = new Person(username, 1);
         try {
            dataAccess.addUser(newUser);
         } catch (Exception e) {
            System.out.println("THIS IS UNBELIEVABLE...");
            e.printStackTrace();
         }
         // Adding this person to the users ArrayList
         usersList.add(newUser);
         
      }
      System.out.println();
   }
   
   public void greeted(String username) {
      DataAccess dataAccess = new DataAccess();
      List<Person> usersList = dataAccess.getAllUsers();
      
      Person user = findByName(username, usersList);
      System.out.println("usersList: " + usersList.size());
      System.out.println(user.getUsername() + " has been greeted " + user.getGreetCount() + " times.");
      System.out.println();
   }
   
   public void counter() {
      DataAccess dataAccess = new DataAccess();
      try {
         dataAccess.setArrayList();
      } catch (Exception e) {
         e.printStackTrace();
      }
      List<Person> usersList = dataAccess.getAllUsers();
      System.out.println(usersList.size() + " users has been greted");
      System.out.println();
   }
   
   public void help() {
      System.out.println("The most commonly used git commands are:");
      System.out.println( "greet [username] [language] \t Add file contents to the index \n" +
              "greeted \t Find by binary search the change that introduced a bug \n" +
              "counter \t Find by binary search the change that introduced a bug \n" +
              "clear \t Find by binary search the change that introduced a bug \n" +
              "exit \t Find by binary search the change that introduced a bug \n" +
              "help \t Find by binary search the change that introduced a bug \n" );
   }
   
   public void exit() {
      System.out.println();
      System.exit(0);
   }
   
   public void clear() {
      DataAccess dataAccess = new DataAccess();
      try {
         dataAccess.delete();
         System.out.println();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
   
   
}
