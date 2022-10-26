package tp1.p1.commands;

import static tp1.p1.view.Messages.error;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class HelpCommand extends Command {
	public HelpCommand() {
		super();
	}

//	protected String getName() {
//		return Messages.COMMAND_HELP_NAME;
//	}
//
//	protected String getShortcut() {
//		return Messages.COMMAND_HELP_SHORTCUT;
//	}
//
//	public String getDetails() {
//		return Messages.COMMAND_HELP_DETAILS;
//	}

	/**
	 * Checks to see if the command entered by the player matches that of any
	 * command class.
	 * 
	 * @param input the command entered by the user
	 * @return <code>true</code> if the command matches
	 */
	public boolean matchCommand(String[] input) {
		if (input[0].equals("help") || input[0].equals("h")) {
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
			return true;
		}
		return false;
	}

	public Command create(String[] command) {
		return new HelpCommand();
	}

	public boolean execute(Game game) {
		System.out.println(Messages.HELP);
		return false;
	}
}
