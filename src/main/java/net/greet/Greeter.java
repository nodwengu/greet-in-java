package net.greet;
import net.greet.commands.*;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.InputRequiredException;
import net.greet.exceptions.InvalidLanguageException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Greeter {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      
      Map<String, Command> commandMap = new HashMap<String, Command>();
   
      commandMap.put("greet", new GreetCommand());
      commandMap.put("greeted", new GreetedCommand());
      commandMap.put("counter", new CounterCommand());
      commandMap.put("clear", new ClearCommand());
      commandMap.put("help", new HelpCommand());
      commandMap.put("exit", new ExitCommand());
      
      while (true) {
         System.out.print("Enter a command$$ ");
         String inputString = scanner.nextLine();
         
         try {
            Context context = new Context(inputString);
            Command command = commandMap.get(context.getCommand());
            if (command == null) {
               throw new InputRequiredException("Invalid input: Exception");
            }
           
            String result = command.execute(context);
           // System.out.println("Result: " + result);
           
         } catch (CommandNotFoundException e) {
            System.out.println("\u001B[32m" + e + "\u001B[0m");
            System.out.println();
         } catch (InvalidLanguageException e) {
            System.out.println("\u001B[32m" + e + "\u001B[0m");
            System.out.println();
         } catch (InputRequiredException e) {
            System.out.println("\u001B[32m" + e + "\u001B[0m");
            System.out.println();
         }
      }
   }
}
