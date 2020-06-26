package net.greet;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.InputRequiredException;
import net.greet.exceptions.InvalidInputLengthException;
import net.greet.exceptions.UserNotFoundException;

import java.util.Scanner;

public class Test {
   public static void main(String[] args){
      try {
         inputData();
      } catch (InvalidInputLengthException e) {
         System.out.println(e);
      } catch (InputRequiredException e) {
         System.out.println(e);
      }
   }
   
   public static void inputData() throws InvalidInputLengthException, InputRequiredException {
      Scanner scanner = new Scanner(System.in);
      String[] input = new String[3];
      String command = null, username = null, language = null;
      System.out.println();
      
      Greet greet = new Greet();
      PersonService personService = new PersonService();
      
      personService.connectToDatabase();
      
      System.out.print("Enter a command or help for more commands$$ ");
      input = scanner.nextLine().split(" ");
      
      if( (input.length == 1) && (input[0].length() == 0) ) {
         String msg = "\u001B[32m" + "Input is required \n" + "\u001B[0m";
         throw new InputRequiredException(msg);
         
      } else if(input.length == 3) {
         command = input[0];
         username = input[1];
         language = input[2];
         
      } else if(input.length == 1) {
         command = input[0];
         
      } else if(input.length == 2) {
         command = input[0];
         username = input[1];
         
      } else if(input.length > 3){
         String message = "\u001B[32m" + "Invalid input length \n" + "\u001B[0m";
         throw new InvalidInputLengthException(message);
      }
      
      while ( !command.equalsIgnoreCase("c") ) {
         try {
            executeGreetCommand(command, username, language);
            
         } catch (CommandNotFoundException e) {
            System.out.println(e);
         }
         
         command = null;
         username =null;
         language = null;
         
         System.out.print("Enter a command or help for more commands$$ ");
         input = scanner.nextLine().split(" ");
         
         if( (input.length == 1) && (input[0].length() == 0) ) {
            String msg = "\u001B[32m" + "Input is required \n" + "\u001B[0m";
            throw new InputRequiredException(msg);
            
         } else if(input.length == 3) {
            command = input[0];
            username = input[1];
            language = input[2];
            
         } else if(input.length == 1) {
            command = input[0];
            
         } else if(input.length == 2) {
            command = input[0];
            username = input[1];
            
         } else if(input.length > 3){
            String message = "\u001B[32m" + "Invalid input length \n" + "\u001B[0m";
            throw new InvalidInputLengthException(message);
         }
      }
   }
   
   static void executeGreetCommand(String command, String username, String language) throws CommandNotFoundException {
      Greet greet = new Greet();
      
      if( command.equals("greet") && username != null && language != null ) {
         greet.greet(username, language);
         
      } else if (command.equals("greet") && username != null && language == null){
         greet.greet(username, language);
         
      } else if( command.equals("greeted") && username != null && language == null ){
         try {
            System.out.println( greet.greeted(username) );
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println();
         }
      } else if( command.equals("greeted") && username == null && language == null ) {
         System.out.println();
         greet.greeted();
         
      } else if( command.equals("counter") && username == null && language == null ) {
         System.out.println( greet.counter() );
         System.out.println();
         
      } else if( command.equals("clear") && username == null && language == null ) {
         greet.clear();
         System.out.println();
         
      } else if( command.equals("clear") && username != null && language == null ) {
         try {
            greet.clear(username);
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
         }
         
      } else if( command.equals("exit") && username == null && language == null ) {
         greet.exit();
         
      } else if( command.equals("help") && username == null && language == null ) {
         System.out.println();
         greet.help();
         
      }  else {
         String message = "\u001B[32m" + "Command not found \n" + "\u001B[0m";
         throw new CommandNotFoundException(message);
      }
   }
   
}


