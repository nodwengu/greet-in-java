package net.greet;

import net.greet.exceptions.InputRequiredException;

public class Context {
   private String inputString;
   private String username;
   private String language;
   private String command;
   
   public Context (String inputString) throws InputRequiredException {
      this.inputString = inputString;
   
      String[] inputArr = inputString.split(" ");
      
      if (inputArr[0].length() == 0) {
         throw new InputRequiredException("Input required");
      }
      
      if (inputArr.length == 1) {
         command = inputArr[0];
      } else if (inputArr.length == 2) {
         command = inputArr[0];
         username = inputArr[1];
      } else if (inputArr.length == 3) {
         command = inputArr[0];
         username = inputArr[1];
         language = inputArr[2];
      }
   }
   
   public String getInputString() {
      return inputString;
   }
   
   public String getCommand() {
      return command;
   }
   
   public String getUsername() {
      return username;
   }
   
   public String getLanguage() {
      return language;
   }
}

