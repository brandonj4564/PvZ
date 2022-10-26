package tp1.p1.commands;

import java.util.Arrays;
import java.util.List;

import tp1.p1.logic.Game;

public abstract class Command {

	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(new AddPlantCommand(),
			new ListPlantsCommand(), new ResetCommand(), new HelpCommand(), new ExitCommand(), new NoneCommand());

	public static Command parse(String[] commandWords) {
		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords)) {
				return command.create(commandWords);
			}
		}

		return null; // incorrect command
	}

	// ...

	/**
	 * Checks to see if the command entered by the player matches that of any
	 * command class.
	 * 
	 * @param command the command entered by the user
	 * @return <code>true</code> if the command matches
	 */
	abstract boolean matchCommand(String[] command);

	/**
	 * Creates an instance of a command given the parameters from the player.
	 * 
	 * @param command the parameters of the command
	 */
	public abstract Command create(String[] command);

	/**
	 * Executes the action of the command.
	 * 
	 * @param game the game object, maybe shouldn't do this
	 */
	public abstract boolean execute(Game game);
}