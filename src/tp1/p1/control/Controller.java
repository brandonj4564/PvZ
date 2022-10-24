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
	

	/**
	 * Construct and initialize a Controller
	 * 
	 * @param game the game that the Controller controls
	 * @param scanner the Scanner used for reading user's input
	 */
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
		// Print the initial game state and board
		printGame();
		
		while(true) {
			boolean temp = game.addNewZombie();
			String[] input = getUserInput();	
			
			// Repeat getting user command if <code>add</code> command is not successfully executed
			while (input[0].equals("help")) {
				executeHelpCommand();
				input = getUserInput();
			}
			
			while (input[0].equals("list")) {
				executeListCommand();
				input = getUserInput();
			}
			
			while (input[0].equals("add") && executeAddCommand(input) == false) {
				input = getUserInput();
			}
			
			if (input[0].equals("none")) {
				executeNoneCommand();
			}
			
			if (input[0].equals("exit")) {
				printGame();
				System.out.println(Messages.GAME_OVER);
				System.out.println(Messages.PLAYER_QUITS);
				return;
			}
			
			
			if (input[0].equals("reset")) {
				executeResetCommand();
				printGame();
				continue;
			}
			

			printGame();
			
			if (game.hasFoundWinner()) {
				System.out.println(gamePrinter.endMessage());
				return;
			}
		}
	}

	
	/**
	 * Repeat getting user command until the user enters a valid command
	 * 
	 * @return user command as words
	 */
	private String[] getUserInput() {
		String[] input = prompt();
		while (CommandPreprocessor.checkValidity(input) == false) {
			input = prompt();
		}
		return input;
	}
	
	/**
	 * Execute <code>add</code> command
	 * 
	 * @param input user command as words
	 * 
	 * @return <code>true</code> if the add command is successfully executed
	 */
	private boolean executeAddCommand(String[] input) {
		String plantName = input[1];
		int col = Integer.parseInt(input[2]);
		int row = Integer.parseInt(input[3]);
		boolean addingResult = game.addNewPlant(plantName, row, col);
		if (addingResult) {
			game.update();
		}
		return addingResult;
	}
	
	/**
	 * Execute <code>update</code> command
	 */
	private void executeNoneCommand() {
		game.update();
	}
	
	/**
	 * Execute <code>help</code> command
	 */
	private void executeHelpCommand() {
		System.out.println(Messages.HELP);
	}
	
	/**
	 * Execute <code>update</code> command
	 */
	private void executeListCommand() {
		System.out.println(Messages.LIST);
	}
	
	/**
	 * Execute <code>reset</code> command
	 */
	private void executeResetCommand() {
		game.restart();
	}
	
}
