package tp1.p1.control;

import static tp1.p1.view.Messages.*;
import tp1.utils.CommandPreprocessor;

import java.util.Scanner;

import tp1.p1.logic.Game;
import tp1.p1.view.GamePrinter;
import tp1.p1.view.Messages;
import tp1.p1.logic.ZombiesManager;

/**
 * Accepts user input and coordinates the game execution logic.
 *
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;
	

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 * Describe the output of the game state at the end of each cycle
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		game.printGameStateInfo();
		System.out.println(gamePrinter);
		// TODO fill your code;
		while(true) {
			boolean temp = game.addNewZombie();
			String[] input = getUserInput();
			
			while (input[0].equals("add") && processAddCommand(input) == false) {
				input = getUserInput();
			}
			
			if (input[0].equals("none")) {
				processNoneCommand();
			}
			
			if (input[0].equals("exit")) {
				System.out.println(Messages.GAME_OVER);
				return;
			}
			
			game.printGameStateInfo();
			System.out.println(gamePrinter);
			
			System.out.print(gamePrinter.endMessage());
		}
	}

	
	private String[] getUserInput() {
		boolean commandValidity = true;
		String[] processedInput = null;
		do {
			System.out.println("Command > ");
			System.out.print("[DEBUG] Executing: ");
			String userInput = scanner.nextLine();
			processedInput = CommandPreprocessor.splitInput(userInput);
			commandValidity = CommandPreprocessor.checkValidity(processedInput);
		}
		while (commandValidity == false);
		return processedInput;
	}
	
	private boolean processAddCommand(String[] input) {
		String plantName = input[1];
		int col = Integer.parseInt(input[2]);
		int row = Integer.parseInt(input[3]);
		boolean addingResult = game.addPlant(plantName, row, col);
		if (addingResult) {
			System.out.println(plantName + " added at (" + col+ ", " + row + ") " + "successfully");
		}
		else {
			System.out.println(plantName + " added at (" + col + ", " + row + ") " + "unsuccessfully");
			
		}
		if (addingResult) {
			game.update();
		}
		return addingResult;
	}
	
	private void processNoneCommand() {
		game.update();
	}

}
