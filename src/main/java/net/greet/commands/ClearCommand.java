package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.UserNotFoundException;

public class ClearCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) throws CommandNotFoundException {
      String[] input = context.getInputString().split(" ");
      
      if (input.length == 2) {
         try {
            greet.clear(context.getUsername());
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println();
         }
      } else if (input.length == 1) {
         greet.clear();
         System.out.println();
      } else {
         throw new CommandNotFoundException("Command not found");
      }
   
      return context.getInputString();
   }
}
