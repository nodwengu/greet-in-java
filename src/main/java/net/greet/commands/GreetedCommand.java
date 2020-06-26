package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.UserNotFoundException;

public class GreetedCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String username = null, language = null;
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 2) {
         username = input[1];
         
         try {
            System.out.println( greet.greeted(username) );
            System.out.println();
         } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println();
         }
      
      } else if (input.length == 1) {
         greet.greeted();
      }
   
      return context.getCommandEntered();
   }
}
