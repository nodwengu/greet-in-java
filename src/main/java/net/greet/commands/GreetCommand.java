package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.InvalidLanguageException;

public class GreetCommand implements Command {
   
   @Override
   public String execute(Context context) throws CommandNotFoundException, InvalidLanguageException {
      Greet greet = new Greet();
      
      String[] input = context.getInputString().split(" ");
      
      try {
         if (input.length == 2) {
            greet.greet(context.getUsername(), context.getLanguage());
         } else if (input.length == 3) {
            greet.greet(context.getUsername(), context.getLanguage());
         } else {
            throw new CommandNotFoundException("Command Not Found");
         }
      } catch (InvalidLanguageException e) {
         System.out.println("\u001B[32m" + e + "\u001B[0m");
         System.out.println();
      }
      
      return context.getInputString();
   }
}