package net.greet.commands;

import net.greet.Context;

public interface Command {
   public String execute(Context context);
}
