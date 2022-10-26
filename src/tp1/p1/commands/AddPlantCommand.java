package tp1.p1.commands;

import static tp1.p1.view.Messages.error;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class AddPlantCommand extends Command {

	public String plant;
	public int row;
	public int col;

	public AddPlantCommand() {
		super();
		plant = "";
		row = 0;
		col = 0;
	}

	public AddPlantCommand(String addedPlant, int inputRow, int inputCol) {
		super();
		plant = addedPlant;
		row = inputRow;
		col = inputCol;
	}

	/**
	 * Checks to see if the command entered by the player matches that of any
	 * command class.
	 * 
	 * @param input the command entered by the user
	 * @return <code>true</code> if the command matches
	 */
	public boolean matchCommand(String[] input) {
		if (input[0].equals("add") || input[0].equals("a")) {
			input[0] = "add";
			if (input.length != 4) {
				System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
				return false;
			}

			String plantName = input[1];
			int col = Integer.parseInt(input[2]);
			int row = Integer.parseInt(input[3]);

			if (plantName.equals("sunflower") || plantName.equals("s")) {
				input[1] = "sunflower";
			} else if (plantName.equals("peashooter") || plantName.equals("p")) {
				input[1] = "peashooter";
			} else {
				System.out.println(error(Messages.INVALID_GAME_OBJECT));
				return false;
			}

			if (row > 3) {
				System.out.println(error(Messages.INVALID_POSITION));
				return false;
			}

			if (col > 7) {
				System.out.println(error(Messages.INVALID_POSITION));
				return false;
			}
			return true;
		}
		return false;
	}

	public Command create(String[] command) {
		int addedCol = Integer.parseInt(command[2]);
		int addedRow = Integer.parseInt(command[3]);
		AddPlantCommand apc = new AddPlantCommand(command[1], addedCol, addedRow);
		return apc;
	}

	public boolean execute(Game game) {
		boolean addingResult = game.addNewPlant(plant, row, col);
//		if (addingResult) {
//			game.update();
//		}
		return addingResult;
	}
}
