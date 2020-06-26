package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;

public class HelpCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 1)
         greet.help();
      
      return context.getCommandEntered();
   }
}
