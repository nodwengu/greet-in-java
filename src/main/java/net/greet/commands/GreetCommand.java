package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;

public class GreetCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String username = null, language = null;
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 2) {
         username = input[1];
         language = null;
         greet.greet(username, language);
         
      } else if (input.length == 3) {
         username = input[1];
         language = input[2];
         greet.greet(username, language);

      } else if (input.length == 1) {
         System.out.println("EXCEPTION EXCEPTION EXCEPTION");
      }
      
      return context.getCommandEntered();
   }
   
}