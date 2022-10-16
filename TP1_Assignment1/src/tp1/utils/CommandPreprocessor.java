package tp1.utils;

import static tp1.p1.view.Messages.*;

import tp1.p1.view.Messages;;

/**
 * Util class to preprocess user commands 
 *
 */
public class CommandPreprocessor {
	/**
	 * Check the validity of input command, displaying specific errors if necessary and tranform
	 * equivalent representations of the command into one single representation
	 * 
	 * @param input input command as words
	 * 
	 * @return user commands as words after being preprocessed
	 */
	public static boolean checkValidity(String[] input) {
		if (input[0].equals("add") || input[0].equals("a")) {
			input[0] = "add";
			if (input.length < 4) {
				System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
				return false;
			} 
			
			if (input.length > 4) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
			
			String plantName = input[1];
			
			if (plantName.equals("sunflower") || plantName.equals("s")) {
				input[1] = "sunflower";
			} else if (plantName.equals("peashooter") || plantName.equals("p")) {
				input[1] = "peashooter";
			} else {
				System.out.println(error(Messages.INVALID_GAME_OBJECT));
				return false;
			}
			
			int col = CommandPreprocessor.parseIntWithExcptionCatching(input[2], -1);
			int row = CommandPreprocessor.parseIntWithExcptionCatching(input[3], -1);
			
			if (row > 3 || row < 0) {
				System.out.println(error(Messages.INVALID_POSITION));
				return false;
			}
			
			if (col > 7 || col < 0) {
				System.out.println(error(Messages.INVALID_POSITION));
				return false;
			}
		}
		
		else if (input[0].equals("none") || input[0].equals("n") || input[0].isEmpty()) {
			input[0] = "none";
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
		}
		
		else if (input[0].equals("exit") || input[0].equals("e")) {
			input[0] = "exit";
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
			
		} 
		
		else if (input[0].equals("list") || input[0].equals("l")) {
			input[0] = "list";
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
		} 
		
		else if (input[0].equals("help") || input[0].equals("h")) {
			input[0] = "help";
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
		}
		
		else if (input[0].equals("reset") || input[0].equals("r")) {
			input[0] = "reset";
			if (input.length != 1) {
				System.out.println(error(Messages.INVALID_COMMAND));
				return false;
			}
		}
		else {
			System.out.println(error(Messages.UNKNOWN_COMMAND));
			return false;
		}
		
		return true;
	}
	
	/**
	 * Handle NumberFormatException 
	 * 
	 * @param inputInt string representation of input integer
	 * @param defaultInt default return integer value
	 * 
	 * @return an integer extracted from inputInt or defaultInt if NumberFormatException happens
	 */
	public static int parseIntWithExcptionCatching(String inputInt, int defaultInt) {
		try {
			return Integer.parseInt(inputInt);
		} catch (NumberFormatException e) {
			return defaultInt;
		}
	}
}
