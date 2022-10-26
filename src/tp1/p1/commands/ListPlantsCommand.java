package tp1.p1.commands;

import static tp1.p1.view.Messages.error;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class ListPlantsCommand extends Command {
	public ListPlantsCommand() {
		super();
	}

	/**
	 * Checks to see if the command entered by the player matches that of any
	 * command class.
	 * 
	 * @param input the command entered by the user
	 * @return <code>true</code> if the command matches
	 */
	public boolean matchCommand(String[] input) {
		if (input[0].equals("list") || input[0].equals("l")) {
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
			return true;
		}
		return false;
	}

	public Command create(String[] command) {
		return new ListPlantsCommand();

	}

	public boolean execute(Game game) {
		System.out.println(Messages.LIST);
		return false;
	}
}
