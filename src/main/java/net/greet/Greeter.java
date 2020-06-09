package net.greet;
import java.util.Scanner;

public class Greeter {
   public static void main(String[] args) throws Exception{
      Scanner scanner = new Scanner(System.in);
      String[] input = new String[3];
      String command = null, username = null, language = null;
      System.out.println();
      
      Greet greet = new Greet();
      DataAccess dataAccess = new DataAccess();
   
      dataAccess.greeted();
      dataAccess.connectToDatabase();
      
      System.out.print("Enter a command or help for more commands: ");
      input = scanner.nextLine().split(" ");
      
      if(input.length == 3) {
         // That means a user has provided 3 input value
         // 3 possible commands can be checked ( greet, username, language);
         // greet->username->language
         command = input[0];
         username = input[1];
         language = input[2];

      } else if(input.length == 1) {
         // That means a user has provided one input value
         // 5 possible commands can be checked ( greeted, counter, clear, exit, help );
         command = input[0];

      } else if(input.length == 2) {
         // greeted followed by a username
         // That means a user has provided 2 input values
         // 3 possible commands can be checked ( greeted, username, clear);
         // greeted->username
         // clear->username
         command = input[0];
         username = input[1];

      } else if(input.length > 3){
         System.out.println("Error Invalid Input: length is: " + input.length);
         System.exit(1);
      }
      
     // getInput(input, command = input[0], username = input[1], language = input[2]);
      
      while ( !command.equalsIgnoreCase("c") ) {
         
         if( command.equals("greet") && username != null && language != null ) {
            // Call greet function from Greet class
            // greet.greet(username, language);
            System.out.println("greet followed by the name and the language the user is to be greeted in");greet.greet(username, language);
            greet.greet(username, language);
            System.out.println();
            
         } else if( command.equalsIgnoreCase("greeted") && username != null && language == null ){
            System.out.println("greeted followed by a username returns how many times that username have been greeted");
            greet.greeted(username);
            System.out.println();
            
         } else if( command.equalsIgnoreCase("greeted") && username == null && language == null ) {
            System.out.println("greeted should display a list of all users that has been greeted and how many time each user has been greeted,");
            dataAccess.greeted();
            System.out.println();
            
         } else if( command.equalsIgnoreCase("counter") && username == null && language == null ) {
            System.out.println("counter returns a count of how many unique users has been greeted,");
            greet.counter();
            System.out.println();
            
         } else if( command.equalsIgnoreCase("clear") && username == null && language == null ) {
            System.out.println("clear deletes of all users greeted and the reset the greet counter to 0");
            greet.clear();
            System.out.println();
            
         } else if( command.equalsIgnoreCase("clear") && username != null && language == null ) {
            // Call clear method from Greet class
            // greet.clear(username);
            System.out.println("clear followed by a username delete the greet counter for the specified user and decrement the greet counter by 1");
            System.out.println();
            
         } else if( command.equalsIgnoreCase("exit") && username == null && language == null ) {
            System.out.println("exit exits the application");
            greet.exit();
            
         } else if( command.equalsIgnoreCase("help") && username == null && language == null ) {
            // Call help method from Greet class
            greet.help();
            System.out.println("help shows a user an overview of all possible commands.");
            System.out.println();
            
         } else {
            System.out.println(command + ": command not found");
            System.out.println();
            //System.exit(1);

         }
   
         //System.exit(1);
         
         System.out.print("Enter a command: ");
         input = scanner.nextLine().split(" ");

         // Accepting 3 args
         if(input.length == 3) {
            // That means a user has provided 3 input value
            // 3 possible commands can be checked ( greet, username, language);
            // greet->username->language
            command = input[0];
            username = input[1];
            language = input[2];

         } else if(input.length == 1) {
            // That means a user has provided one input value
            // 5 possible commands can be checked ( greeted, counter, clear, exit, help );
            command = input[0];

         } else if(input.length == 2) {
            // greeted followed by a username
            // That means a user has provided 2 input values
            // 3 possible commands can be checked ( greeted, username, clear);
            // greeted->username
            // clear->username
            command = input[0];
            username = input[1];

         } else if(input.length > 3){
            System.out.println("Error Invalid Input: length is: " + input.length);
            System.exit(1);
         }
   
   
//         if( command.equals("greet") && username != null && language != null ) {
//            greet.greet(username, language);
//            System.out.println("greet followed by the name and the language the user is to be greeted in");
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("greeted") && username != null && language == null ){
//            System.out.println("greeted followed by a username returns how many times that username have been greeted");
//            greet.greeted(username);
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("greeted") && username == null && language == null ) {
//            System.out.println("greeted should display a list of all users that has been greeted and how many time each user has been greeted,");
//            dataAccess.greeted();
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("counter") && username == null && language == null ) {
//            System.out.println("counter returns a count of how many unique users has been greeted,");
//            greet.counter();
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("clear") && username == null && language == null ) {
//            // Call clear method from Greet class
//            // greet.clear();
//            System.out.println("clear deletes of all users greeted and the reset the greet counter to 0");
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("clear") && username != null && language == null ) {
//            // Call clear method from Greet class
//            // greet.clear(username);
//            System.out.println("clear followed by a username delete the greet counter for the specified user and decrement the greet counter by 1");
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("exit") && username == null && language == null ) {
//            // Call exit method from Greet class
//            // greet.exit();
//            System.out.println("exit exits the application");
//            System.out.println();
//
//         } else if( command.equalsIgnoreCase("help") && username == null && language == null ) {
//            System.out.println("help shows a user an overview of all possible commands.");
//            greet.help();
//            System.out.println();
//
//         }
//         else {
//            System.out.println("Error Input value is necessary: Type help for more commands");
//            System.out.println();
//            //System.exit(1);
//
//         }
   
   
   
      }
      
   }
   
   
}


