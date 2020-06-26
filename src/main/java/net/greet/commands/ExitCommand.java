package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;

public class ExitCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 1)
         greet.exit();
  
      return context.getCommandEntered();
   }
}
