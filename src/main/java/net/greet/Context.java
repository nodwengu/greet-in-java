package net.greet;

import net.greet.commands.*;
import net.greet.exceptions.InputRequiredException;
import net.greet.exceptions.InvalidInputLengthException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Context {
   private String inputString;
   
   public Context (String inputString) {
      this.inputString = inputString;
   }
   
   public String getCommandEntered() {
      return inputString;
   }
}

