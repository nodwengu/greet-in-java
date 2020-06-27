package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.CommandNotFoundException;

public class ExitCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) throws CommandNotFoundException {
      String[] input = context.getInputString().split(" ");
      
      if (input.length == 1)
         greet.exit();
      else {
         throw new CommandNotFoundException("Command not found");
      }
  
      return context.getInputString();
   }
}
