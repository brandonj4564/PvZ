package tp1.p1.commands;

public abstract class Commands {
	/**
	 * Checks to see if the command entered by the player matches that of any
	 * command class
	 * 
	 * @param command the command entered by the user
	 * @return <code>true</code> if the command matches
	 */
	abstract boolean matchCommand(String command);

	/**
	 * Creates an instance of a command given the parameters from the player.
	 * 
	 * @param command the parameters of the command
	 */
	abstract void create(String[] command);

	/**
	 * Executes the action of the command.
	 */
	abstract void execute();
}