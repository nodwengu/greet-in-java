package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.UserNotFoundException;

public class ClearCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String username = null, language = null;
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 2) {
         username = input[1];
         
         try {
            greet.clear(username);
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println();
         }
      } else if (input.length == 1) {
         greet.clear();
         System.out.println();
      }
   
      return context.getCommandEntered();
   }
}
