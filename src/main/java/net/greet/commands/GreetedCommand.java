package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.UserNotFoundException;

public class GreetedCommand implements Command {
  
   @Override
   public String execute(Context context) throws CommandNotFoundException {
      Greet greet = new Greet();
      String[] input = context.getInputString().split(" ");
      
      if (input.length == 2) {
         try {
            System.out.println( greet.greeted(context.getUsername()) );
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println();
         }
      } else if (input.length == 1) {
         greet.greeted();
      } else {
         throw new CommandNotFoundException("Command Not Found");
      }
   
      return context.getInputString();
   }
}
