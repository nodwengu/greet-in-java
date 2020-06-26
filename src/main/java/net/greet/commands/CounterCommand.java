package net.greet.commands;

import net.greet.Context;
import net.greet.Greet;
import net.greet.exceptions.UserNotFoundException;

public class CounterCommand implements Command {
   Greet greet = new Greet();
   
   @Override
   public String execute(Context context) {
      String[] input = context.getCommandEntered().split(" ");
      
      if (input.length == 1)
         System.out.println(greet.counter());
      System.out.println();
      
      return context.getCommandEntered();
   }
}
