package net.greet.commands;

import net.greet.Context;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.InvalidLanguageException;

public interface Command {
   public String execute(Context context) throws CommandNotFoundException, InvalidLanguageException;
}
