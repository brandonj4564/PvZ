package tp1.p1.logic;
import tp1.p1.logic.gameobjects.SunflowerList;
import tp1.p1.view.Messages;
import static tp1.p1.view.Messages.*;
import tp1.p1.logic.gameobjects.PeashooterList;
import tp1.p1.logic.gameobjects.Sunflower;
import tp1.p1.logic.gameobjects.Peashooter;

/**
 * Manage plants in the game
 *
 */
public class PlantsManager {
	private SunflowerList sunflowers;
	private PeashooterList peashooters;
	private Game game;
	
	
	/**
	 * Construct and initialize a PlantsManager
	 * 
	 * @param game the game that the PlantsManager works for
	 */
	public PlantsManager(Game game) {
		sunflowers = new SunflowerList();
		peashooters = new PeashooterList();
		this.game = game;
	}
	
	/**
	 * Get string representation of the sunflower at position <code>(col, row)</code>
	 * 
	 * @param row position of the sunflower 
	 * @param col column position of the sunflower
	 * 
	 * @return string representation of the sunflower
	 */
	public String getSunflowerStringAt(int row, int col) {
		return sunflowers.getSunflowerStringAt(row, col);
	}
	
	/**
	 * Get string representation of the peashooter at position <code>(col, row)</code>
	 * 
	 * @param row position of the peashooter 
	 * @param col column position of the peashooter
	 * 
	 * @return string representation of peashooter
	 */
	public String getPeashooterStringAt(int row, int col) {
		return peashooters.getPeashooterStringAt(row, col);
	}
	
	/**
	 * Add a sunflower at position <code>(row, col)</code> to the current game
	 * 
	 * @param row row position of the sunflower 
	 * @param col column position of the sunflower
	 */
	public void addSunflowerAt(int row, int col) {
		Sunflower newSunflower = new Sunflower(row, col, game);
		sunflowers.addSunflower(newSunflower);
	}
	
	/**
	 * Add a peashooter at position <code>(row, col)</code> to the current game
	 * 
	 * @param row row position of the peashooter 
	 * @param col column position of the peashooter
	 */
	public void addPeashooterAt(int row, int col) {
		Peashooter newPeashooter = new Peashooter(row, col, game);
		peashooters.addPeashooter(newPeashooter);
	}
	
	/**
	 * Check if it is possible to add a new plant to game
	 * 
	 * @param plantName name of the plant
	 * @param row row position of the plant
	 * @param col column position of the plant
	 * 
	 * @return <code>true</code> if it is possible to add the plant
	 */
	
	private boolean canAdd(String plantName, int row, int col) {
		// Using short-circuit evaluation for optimization
		boolean checkResult = canPayPlant(plantName) && checkForEmptyTileAt(row, col) && game.isTileEmptyFromZombie(row, col);
		
		boolean hasEnoughCoins = canPayPlant(plantName);
		if (hasEnoughCoins == false) {
			System.out.println(error(Messages.NOT_ENOUGH_COINS));
		}
		if (checkResult == false && hasEnoughCoins == true) {
			System.out.println(error(Messages.INVALID_POSITION));
		}
		
		return checkResult;
	}
	
	
	/**
	 * Add a new plant to the current game if possible
	 * 
	 * @param plantName name of the plant 
	 * @param row row position of the plant
	 * @param col column position of the plant
	 * 
	 * @return binary value determining whether plant adding is successful
	 */
	public boolean addPlant(String plantName, int row, int col) {
		boolean canAddPlant = canAdd(plantName, row, col);
		
		if (canAddPlant) {
			if (plantName.equals("sunflower")) {
				addSunflowerAt(row, col);
			}
			else {
				addPeashooterAt(row, col);
			}
		}
		
		return canAddPlant;
		
	}
	
	/**
	 * Check if the player has enough coins to pay for a plant
	 * 
	 * @param plantName name of the plant
	 * 
	 * @return <code>true</code> if the player has enough coins to pay for plant
	 */
	private boolean canPayPlant(String plantName) {
		if (plantName.equals("sunflower")) {
			return game.hasEnoughMoney(Sunflower.COST);
		} 
		
		if (plantName.equals("peashooter")) {
			return game.hasEnoughMoney(Peashooter.COST);
		}
		return false;
	}
	

	/**
	 * Check whether a tile is not occupied by a plant
	 * 
	 * @param row row position of the tile
	 * @param col column condition of the tile
	 * 
	 * @return <code>true</code> if the tile is not occupied by a plant 
	 */
	public boolean checkForEmptyTileAt(int row, int col) {
		return sunflowers.isSunflowerAt(row, col) == false && peashooters.isPeashooterAt(row, col) == false;
	}
	
	/**
	 * Check if there is a plant which causes attack on a zombie at position <code>(row, col)</code>
	 * 
	 * @param row row position of the zombie
	 * @param col column position of the zombie
	 * 
	 * @return <code>true</code> if there is a plant which causes attack on a zombie at position <code>(row, col)</code>
	 */
	public boolean anyoneAttackZombieAt(int row, int col) {
		return peashooters.doesAttackZombieAt(row, col);
	}
	
	
	/**
	 * Update state of plants
	 */
	public void update() {
		sunflowers.update();
		peashooters.update();
	}
	
	/**
	 * Remove dead plants from game
	 */
	public void removeDeadPlants() {
		sunflowers.removeDeadSunflowers();
		peashooters.removeDeadPeashooters();
	}
	
	
	/**
	 * Restart plants to their initial state
	 */
	public void restart()
	{
		sunflowers.restart();
		peashooters.restart();
	}

}
