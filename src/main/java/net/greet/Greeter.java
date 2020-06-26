package net.greet;
import net.greet.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Greeter {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      String username = null, language = null;
      
      System.out.print("Enter a command or help for more commands$$ ");
      String inputString = scanner.nextLine();
      
      String[] input = inputString.split(" ");
      
      if (input.length == 2) {
         username = input[1];
         language = null;
      } else if (input.length == 3) {
         username = input[1];
         language = input[2];
      }
      
      // setup
      Map<String, Command> commandMap = new HashMap<String, Command>();
      
      commandMap.put("greet", new GreetCommand());
      commandMap.put("greet " + username, new GreetCommand());
      commandMap.put("greet " + username + " " + language, new GreetCommand());
      commandMap.put("greeted", new GreetedCommand());
      commandMap.put("greeted " + username, new GreetedCommand());
      commandMap.put("counter", new CounterCommand());
      commandMap.put("help", new HelpCommand());
      commandMap.put("exit", new ExitCommand());
      commandMap.put("clear " + username, new ClearCommand());
      commandMap.put("clear", new ClearCommand());
      
      while ( !inputString.equalsIgnoreCase("c") ) {
         Context context = new Context(inputString);
         Command command = commandMap.get(context.getCommandEntered());
         command.execute(context);
         //String result = command.execute(context);
         //System.out.println("Result: " + result);
         
         System.out.print("Enter a command: ");
         inputString = scanner.nextLine();
         input = inputString.split(" ");
         
         if (input.length == 2) {
            username = input[1];
            language = null;
         } else if (input.length == 3) {
            username = input[1];
            language = input[2];
         }
         
         commandMap.put("greet", new GreetCommand());
         commandMap.put("greet " + username, new GreetCommand());
         commandMap.put("greet " + username + " "+ language, new GreetCommand());
         commandMap.put("greeted", new GreetedCommand());
         commandMap.put("greeted " + username, new GreetedCommand());
         commandMap.put("counter", new CounterCommand());
         commandMap.put("help", new HelpCommand());
         commandMap.put("exit", new ExitCommand());
         commandMap.put("clear " + username, new ClearCommand());
         commandMap.put("clear", new ClearCommand());
      }
   }
   
}


