package net.greet;
import java.util.Scanner;

public class Greeter {
   public static void main(String[] args) throws Exception{
      Scanner scanner = new Scanner(System.in);
      String[] input = new String[3];
      String command = null, username = null, language = null;
      System.out.println();
      
      Greet greet = new Greet();
      PersonService personService = new PersonService();
   
      personService.connectToDatabase();
      //greet.greeted();
     
      System.out.print("Enter a command or help for more commands$$ ");
      input = scanner.nextLine().split(" ");
      
      if(input.length == 3) {
         command = input[0];
         username = input[1];
         language = input[2];

      } else if(input.length == 1) {
         command = input[0];

      } else if(input.length == 2) {
         command = input[0];
         username = input[1];

      } else if(input.length > 3){
         System.out.println("Error Invalid Input: length is: " + input.length);
         System.exit(1);
      }
      
      while ( !command.equalsIgnoreCase("c") ) {
         
         executeGreetCommand(command, username, language);
         
         command = null;
         username =null;
         language = null;
   
         System.out.print("Enter a command or help for more commands$$ ");
         input = scanner.nextLine().split(" ");
         
         if(input.length == 3) {
            command = input[0];
            username = input[1];
            language = input[2];

         } else if(input.length == 1) {
            command = input[0];

         } else if(input.length == 2) {
            command = input[0];
            username = input[1];

         } else if(input.length > 3){
            System.out.println("Error Invalid Input: length is: " + input.length);
            System.exit(1);
         }
      }
   }
   
   static void executeGreetCommand(String command, String username, String language) {
      Greet greet = new Greet();
      
//      System.out.println(
//              "Command: " + command + "\t" +
//              "Username: " + username + "\t" +
//              "Language: " + language + "\n"
//      );
      //System.out.println();
   
      if( command.equals("greet") && username != null && language != null ) {
         //System.out.println("greet followed by the name and the language the user is to be greeted in");
//         System.out.println();
         greet.greet(username, language);
      
      } else if( command.equals("greeted") && username != null && language == null ){
         //System.out.println("greeted followed by a username returns how many times that username have been greeted");
         System.out.println( greet.greeted(username) );
         System.out.println();
      
      } else if( command.equals("greeted") && username == null && language == null ) {
         //System.out.println("greeted should display a list of all users that has been greeted and how many time each user has been greeted,");
         System.out.println();
         greet.greeted();
      
      } else if( command.equals("counter") && username == null && language == null ) {
         // System.out.println("Count of how many unique users has been greeted,");
         System.out.println( greet.counter() );
         System.out.println();
      
      } else if( command.equals("clear") && username == null && language == null ) {
         //System.out.println("clear deletes of all users greeted and the reset the greet counter to 0");
         greet.clear();
         System.out.println();
      
      } else if( command.equals("clear") && username != null && language == null ) {
         //System.out.println("clear followed by a username delete the greet counter for the specified user and decrement the greet counter by 1");
         greet.clear(username);
         System.out.println();
      
      } else if( command.equals("exit") && username == null && language == null ) {
         //System.out.println("exit exits the application");
         greet.exit();
      
      } else if( command.equals("help") && username == null && language == null ) {
         System.out.println("Overview of all possible commands: ");
         System.out.println();
         greet.help();
      
      } else {
         System.out.println(command + ": command not found");
         System.out.println();
      
      }
   }
   
   
}


