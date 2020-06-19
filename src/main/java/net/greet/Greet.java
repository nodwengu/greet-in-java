package net.greet;

import net.greet.exceptions.UserNotFoundException;

import java.util.List;

public class Greet {

   public void greetUser(String username, String lang) {
      Languange[] languanges = Languange.values();
      lang = lang.toUpperCase();
      
      for (Languange languange: languanges) {
         if (languange.toString().equals(lang))
            System.out.println("\u001B[32m" + languange.getGreet() + username + "\u001B[0m");
      }
   }
  
   public void greet(String username, String lang) {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
      
      if (lang == null) {
         lang = Languange.ENGLISH.toString();
      }
   
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
      }
   }
   
   public void greeted() {
      PersonService personService = null;
   
      personService = new PersonService();
      personService.allGreeted();
   }
   
   public String greeted(String username) throws UserNotFoundException {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
      String msg = null;
   
      if(personService.getByName(username) != null) {
         person = personService.getByName(username);
         
      } else {
         String message = "\u001B[32m" + "Username does not exists" + "\u001B[0m";
         throw new UserNotFoundException(message);
      }
      
      msg = "\u001B[32m" + person.getUsername() + " has been greeted " + person.getGreetCount() + " times." + "\u001B[0m";
      
      return  msg;
   }
   
   public String counter() {
      PersonService personService = new PersonService();
      List<Person> allUsers = null;
      String msg = null;
   
      if(personService.getAll() != null)
         allUsers = personService.getAll();
      
      msg = "\u001B[32m" + (allUsers.size() + " users has been greeted") + "\u001B[0m";
      
      return msg;
   }
   
   public void help() {
      System.out.println(
              "\u001B[32m" + "greet " + "\u001B[0m" + "[username] [language] \t Greet a specified user with a given language \n" +
                      "\u001B[32m" + "greeted " + "\u001B[0m" + "\t \t \t Display a list of all users that has been greeted and how many time each user has been greeted \n" +
                      "\u001B[32m" + "greeted " + "\u001B[0m" + "[username] \t \t Returns how many times that username have been greeted \n" +
                      "\u001B[32m" + "counter " + "\u001B[0m" + "\t \t \t Returns a count of how many unique users has been greeted \n" +
                      "\u001B[32m" + "clear " + "\u001B[0m" + "\t \t \t \t Deletes all users greeted and the reset the greet counter to 0 \n" +
                      "\u001B[32m" + "clear " + "\u001B[0m" + "[username] \t \t Delete the greet counter for the specified user and decrement the greet counter by 1 \n" +
                      "\u001B[32m" + "exit " + "\u001B[0m" + "\t \t \t \t Exits the application \n" +
                      "\u001B[32m" + "help " + "\u001B[0m" + "\t \t \t \t shows a user an overview of all possible commands \n"
      );
   }
   
   public void exit() {
      System.out.println("\u001B[32m" + "Goodbye! " + "\u001B[0m");
      System.out.println();
      System.exit(0);
   }
   
   public void clear() {
      PersonService personService = null;
   
      personService = new PersonService();
   
      if (personService.getAll().size() != 0) {
         personService.delete();
         System.out.println("\u001B[32m" + "Successfully removed all users! " + "\u001B[0m");
      
      } else {
         System.out.println("\u001B[32m" + "No data! " + "\u001B[0m");
      }
   }
   
   public void clear(String username) throws UserNotFoundException {
      username = username.toLowerCase();
      username = username.substring(0, 1).toUpperCase() + username.substring(1);
      
      PersonService personService = new PersonService();
      Person person = null;
   
      person = personService.getByName(username);
      
      if (person != null) {
         personService.remove(person);
         System.out.println("\u001B[32m" + "Successfully removed " + person.getUsername()  + "\u001B[0m");
      } else {
         String message = "\u001B[32m" + "Username does not exists \n" + "\u001B[0m";
         throw new UserNotFoundException(message);
      }
      
   }
}























