package net.greet.exceptions;

public class CommandNotFoundException extends Exception {
   public CommandNotFoundException(String msg) {
      super(msg);
   }
}
