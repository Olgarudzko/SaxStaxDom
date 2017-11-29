package by.rudzko.web.controller.command;

import by.rudzko.web.controller.command.implementation.DomParserCommand;
import by.rudzko.web.controller.command.implementation.SaxParserCommand;
import by.rudzko.web.controller.command.implementation.ShowNextCommand;
import by.rudzko.web.controller.command.implementation.StaxParserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
	
	private static Map <CommandName, Command> commands;
	private static  CommandProvider provider = new CommandProvider();

	public static CommandProvider getProvider() {
		return provider;
	}

	private CommandProvider() {
		commands = new HashMap<CommandName, Command>();
		commands.put(CommandName.SAX, new SaxParserCommand());
		commands.put(CommandName.STAX, new StaxParserCommand());
		commands.put(CommandName.DOM, new DomParserCommand());
		commands.put(CommandName.NEXT, new ShowNextCommand());
	}
	
	public Command getCommand (String key) {
		return commands.get(CommandName.valueOf(key.toUpperCase()));
	}
}
